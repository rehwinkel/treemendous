package de.deerangle.treemendous.village;

import de.deerangle.treemendous.tree.FakeTree;
import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.Tree;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.RegistryManager;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;

public class TreemendousTrades {

    private static List<Tree> getAllTrees() {
        List<Tree> trees = RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues().stream().map(RegisteredTree::getTree).toList();
        trees.add(new FakeTree(Blocks.OAK_LOG, Blocks.OAK_SAPLING));
        trees.add(new FakeTree(Blocks.BIRCH_LOG, Blocks.BIRCH_SAPLING));
        trees.add(new FakeTree(Blocks.SPRUCE_LOG, Blocks.SPRUCE_SAPLING));
        trees.add(new FakeTree(Blocks.JUNGLE_LOG, Blocks.JUNGLE_SAPLING));
        trees.add(new FakeTree(Blocks.ACACIA_LOG, Blocks.ACACIA_SAPLING));
        trees.add(new FakeTree(Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_SAPLING));
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

    public static class ItemsAndEmeraldsToItems implements VillagerTrades.ItemListing {
        private final ItemStack fromItem;
        private final int fromCount;
        private final int emeraldCost;
        private final ItemStack toItem;
        private final int toCount;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsAndEmeraldsToItems(ItemLike p_35725_, int p_35726_, Item p_35727_, int p_35728_, int p_35729_, int p_35730_) {
            this(p_35725_, p_35726_, 1, p_35727_, p_35728_, p_35729_, p_35730_);
        }

        public ItemsAndEmeraldsToItems(ItemLike p_35717_, int p_35718_, int p_35719_, Item p_35720_, int p_35721_, int p_35722_, int p_35723_) {
            this.fromItem = new ItemStack(p_35717_);
            this.fromCount = p_35718_;
            this.emeraldCost = p_35719_;
            this.toItem = new ItemStack(p_35720_);
            this.toCount = p_35721_;
            this.maxUses = p_35722_;
            this.villagerXp = p_35723_;
            this.priceMultiplier = 0.05F;
        }

        @SuppressWarnings("NullableProblems")
        @Nullable
        public MerchantOffer getOffer(Entity p_35732_, Random p_35733_) {
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(this.fromItem.getItem(), this.fromCount), new ItemStack(this.toItem.getItem(), this.toCount), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    public static class ItemsForEmeralds implements VillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int emeraldCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForEmeralds(Block block, int emeraldCost, int numberItems, int maxUses, int xp) {
            this(new ItemStack(block), emeraldCost, numberItems, maxUses, xp);
        }

        public ItemsForEmeralds(Item item, int emeraldCost, int numberItems, int xp) {
            this(new ItemStack(item), emeraldCost, numberItems, 12, xp);
        }

        public ItemsForEmeralds(Item item, int emeraldCost, int numberItems, int maxUses, int xp) {
            this(new ItemStack(item), emeraldCost, numberItems, maxUses, xp);
        }

        public ItemsForEmeralds(ItemStack itemStack, int emeraldCost, int numberItems, int maxUses, int xp) {
            this(itemStack, emeraldCost, numberItems, maxUses, xp, 0.05F);
        }

        public ItemsForEmeralds(ItemStack itemStack, int emeraldCost, int numberItems, int maxUses, int xp, float priceMultiplier) {
            this.itemStack = itemStack;
            this.emeraldCost = emeraldCost;
            this.numberOfItems = numberItems;
            this.maxUses = maxUses;
            this.villagerXp = xp;
            this.priceMultiplier = priceMultiplier;
        }

        @SuppressWarnings("NullableProblems")
        public MerchantOffer getOffer(Entity entity, Random rand) {
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
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

    public static class EmeraldForTreeItems implements VillagerTrades.ItemListing {

        private final BiFunction<Tree, Random, ItemLike> itemForTree;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public EmeraldForTreeItems(BiFunction<Tree, Random, ItemLike> itemForTree, int cost, int maxUses, int xp) {
            this.itemForTree = itemForTree;
            this.cost = cost;
            this.maxUses = maxUses;
            this.villagerXp = xp;
            this.priceMultiplier = 0.05F;
        }

        @SuppressWarnings("NullableProblems")
        public MerchantOffer getOffer(Entity entity, Random rand) {
            List<Tree> trees = getAllTrees();
            Tree tree = trees.get(rand.nextInt(trees.size()));
            ItemStack itemstack = new ItemStack(this.itemForTree.apply(tree, rand).asItem(), this.cost);
            return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD), this.maxUses, this.villagerXp, this.priceMultiplier);
        }

    }

}
