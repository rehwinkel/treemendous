package de.deerangle.treemendous.village;

import de.deerangle.treemendous.tree.FakeTree;
import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.Tree;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraftforge.registries.RegistryManager;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class TreemendousTrades {

    private static List<Tree> getAllTrees() {
        ArrayList<Tree> trees = RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues().stream().map(RegisteredTree::getTree).collect(Collectors.toCollection(ArrayList::new));
        trees.add(new FakeTree(Blocks.OAK_LOG, Blocks.OAK_SAPLING, Blocks.STRIPPED_OAK_WOOD));
        trees.add(new FakeTree(Blocks.BIRCH_LOG, Blocks.BIRCH_SAPLING, Blocks.STRIPPED_BIRCH_WOOD));
        trees.add(new FakeTree(Blocks.SPRUCE_LOG, Blocks.SPRUCE_SAPLING, Blocks.STRIPPED_SPRUCE_WOOD));
        trees.add(new FakeTree(Blocks.JUNGLE_LOG, Blocks.JUNGLE_SAPLING, Blocks.STRIPPED_JUNGLE_WOOD));
        trees.add(new FakeTree(Blocks.ACACIA_LOG, Blocks.ACACIA_SAPLING, Blocks.STRIPPED_ACACIA_WOOD));
        trees.add(new FakeTree(Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_SAPLING, Blocks.STRIPPED_DARK_OAK_WOOD));
        return trees;
    }

    public static class EmeraldForItems implements VillagerTrades.ItemListing {

        private final Item item;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public EmeraldForItems(ItemLike item, int cost, int maxUses, int xp) {
            this.item = item.asItem();
            this.cost = cost;
            this.maxUses = maxUses;
            this.villagerXp = xp;
            this.priceMultiplier = 0.05F;
        }

        @SuppressWarnings("NullableProblems")
        public MerchantOffer getOffer(Entity entity, Random rand) {
            ItemStack itemstack = new ItemStack(this.item, this.cost);
            return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD), this.maxUses, this.villagerXp, this.priceMultiplier);
        }

    }

    public static class EmeraldForRandomItems implements VillagerTrades.ItemListing {

        private final List<Item> items;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public EmeraldForRandomItems(List<Item> items, int cost, int maxUses, int xp) {
            this.items = items;
            this.cost = cost;
            this.maxUses = maxUses;
            this.villagerXp = xp;
            this.priceMultiplier = 0.05F;
        }

        @SuppressWarnings("NullableProblems")
        public MerchantOffer getOffer(Entity entity, Random rand) {
            ItemStack itemstack = new ItemStack(this.items.get(rand.nextInt(this.items.size())), this.cost);
            return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD), this.maxUses, this.villagerXp, this.priceMultiplier);
        }

    }

    public static class ItemsForEmeralds implements VillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final IntProvider emeraldCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForEmeralds(Item item, IntProvider emeraldCost, int numberItems, int maxUses, int xp) {
            this(new ItemStack(item), emeraldCost, numberItems, maxUses, xp);
        }

        public ItemsForEmeralds(ItemStack itemStack, IntProvider emeraldCost, int numberItems, int maxUses, int xp) {
            this(itemStack, emeraldCost, numberItems, maxUses, xp, 0.05F);
        }

        public ItemsForEmeralds(ItemStack itemStack, IntProvider emeraldCost, int numberItems, int maxUses, int xp, float priceMultiplier) {
            this.itemStack = itemStack;
            this.emeraldCost = emeraldCost;
            this.numberOfItems = numberItems;
            this.maxUses = maxUses;
            this.villagerXp = xp;
            this.priceMultiplier = priceMultiplier;
        }

        @SuppressWarnings("NullableProblems")
        public MerchantOffer getOffer(Entity entity, Random rand) {
            int cost = this.emeraldCost.sample(rand);
            return new MerchantOffer(new ItemStack(Items.EMERALD, cost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    public static class TreeItemForEmeralds implements VillagerTrades.ItemListing {
        private final BiFunction<Tree, Random, ItemLike> itemForTree;
        private final int emeraldCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public TreeItemForEmeralds(BiFunction<Tree, Random, ItemLike> itemForTree, int emeraldCost, int numberItems, int maxUses, int xp, float priceMultiplier) {
            this.itemForTree = itemForTree;
            this.emeraldCost = emeraldCost;
            this.numberOfItems = numberItems;
            this.maxUses = maxUses;
            this.villagerXp = xp;
            this.priceMultiplier = priceMultiplier;
        }

        @SuppressWarnings("NullableProblems")
        public MerchantOffer getOffer(Entity entity, Random rand) {
            List<Tree> trees = getAllTrees();
            Tree tree = trees.get(rand.nextInt(trees.size()));
            return new MerchantOffer(
                    new ItemStack(Items.EMERALD, this.emeraldCost),
                    new ItemStack(this.itemForTree.apply(tree, rand).asItem(), this.numberOfItems),
                    this.maxUses,
                    this.villagerXp,
                    this.priceMultiplier
            );
        }

    }

    public static class EmeraldsForTreeItems implements VillagerTrades.ItemListing {

        private final BiFunction<Tree, Random, ItemLike> itemForTree;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;
        private final int emeralds;

        public EmeraldsForTreeItems(BiFunction<Tree, Random, ItemLike> itemForTree, int cost, int emeralds, int maxUses, int xp) {
            this.itemForTree = itemForTree;
            this.cost = cost;
            this.emeralds = emeralds;
            this.maxUses = maxUses;
            this.villagerXp = xp;
            this.priceMultiplier = 0.05F;
        }

        @SuppressWarnings("NullableProblems")
        public MerchantOffer getOffer(Entity entity, Random rand) {
            List<Tree> trees = getAllTrees();
            Tree tree = trees.get(rand.nextInt(trees.size()));
            ItemStack itemstack = new ItemStack(this.itemForTree.apply(tree, rand).asItem(), this.cost);
            return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD, this.emeralds), this.maxUses, this.villagerXp, this.priceMultiplier);
        }

    }

    public static class TreasureMapForEmeralds implements VillagerTrades.ItemListing {
        private final int emeraldCost;
        private final StructureFeature<?> destination;
        private final MapDecoration.Type destinationType;
        private final int maxUses;
        private final int villagerXp;

        public TreasureMapForEmeralds(int emeraldCost, StructureFeature<?> destination, MapDecoration.Type destinationType, int maxUses, int xp) {
            this.emeraldCost = emeraldCost;
            this.destination = destination;
            this.destinationType = destinationType;
            this.maxUses = maxUses;
            this.villagerXp = xp;
        }

        @SuppressWarnings("NullableProblems")
        @Nullable
        public MerchantOffer getOffer(Entity entity, Random rand) {
            if (entity.level instanceof ServerLevel serverlevel) {
                BlockPos blockpos = serverlevel.findNearestMapFeature(this.destination, entity.blockPosition(), 100, true);
                if (blockpos != null) {
                    ItemStack itemstack = MapItem.create(serverlevel, blockpos.getX(), blockpos.getZ(), (byte) 2, true, true);
                    MapItem.renderBiomePreviewMap(serverlevel, itemstack);
                    MapItemSavedData.addTargetDecoration(itemstack, blockpos, "+", this.destinationType);
                    itemstack.setHoverName(new TranslatableComponent("filled_map." + this.destination.getFeatureName().toLowerCase(Locale.ROOT)));
                    return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(Items.COMPASS), itemstack, this.maxUses, this.villagerXp, 0.2F);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }

    }

}
