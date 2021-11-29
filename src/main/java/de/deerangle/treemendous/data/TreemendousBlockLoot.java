package de.deerangle.treemendous.data;

import de.deerangle.treemendous.main.ExtraRegistry;
import de.deerangle.treemendous.main.TreeRegistry;
import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.Tree;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryManager;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TreemendousBlockLoot extends BlockLoot
{

    private static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    private static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
    private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();
    private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

    protected static LootTable.Builder createCustomLeavesDrops(Block block, Block sapling, Supplier<Item> fruitSupplier, float... chances)
    {
        Item fruit = fruitSupplier.get();
        if (fruit != null)
        {
            return createLeavesDrops(block, sapling, chances).withPool(
                    LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .when(HAS_NO_SHEARS_OR_SILK_TOUCH)
                            .add(applyExplosionCondition(block, LootItem.lootTableItem(fruit))
                                    .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))
                            )
            );
        } else
        {
            return createLeavesDrops(block, sapling, chances);
        }
    }

    @Override
    protected void addTables()
    {
        this.add(ExtraRegistry.MAPLE_RED_LEAVES.get(), (drops) -> createLeavesDrops(drops, TreeRegistry.MAPLE_TREE.getSapling("red"), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ExtraRegistry.MAPLE_BROWN_LEAVES.get(), (drops) -> createLeavesDrops(drops, TreeRegistry.MAPLE_TREE.getSapling("brown"), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ExtraRegistry.BIRCH_CHEST.get());
        this.dropSelf(ExtraRegistry.JUNGLE_CHEST.get());
        this.dropSelf(ExtraRegistry.SPRUCE_CHEST.get());
        this.dropSelf(ExtraRegistry.ACACIA_CHEST.get());
        this.dropSelf(ExtraRegistry.DARK_OAK_CHEST.get());
        this.dropSelf(ExtraRegistry.CRIMSON_CHEST.get());
        this.dropSelf(ExtraRegistry.WARPED_CHEST.get());
        this.dropSelf(ExtraRegistry.BIRCH_CRAFTING_TABLE.get());
        this.dropSelf(ExtraRegistry.JUNGLE_CRAFTING_TABLE.get());
        this.dropSelf(ExtraRegistry.SPRUCE_CRAFTING_TABLE.get());
        this.dropSelf(ExtraRegistry.ACACIA_CRAFTING_TABLE.get());
        this.dropSelf(ExtraRegistry.DARK_OAK_CRAFTING_TABLE.get());
        this.dropSelf(ExtraRegistry.CRIMSON_CRAFTING_TABLE.get());
        this.dropSelf(ExtraRegistry.WARPED_CRAFTING_TABLE.get());

        this.dropSelf(ExtraRegistry.CHOPPING_BLOCK.get());

        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues())
        {
            if (regTree.isNotFake())
            {
                Tree tree = regTree.getTree();
                this.dropSelf(tree.getButton());
                this.dropSelf(tree.getChest());
                this.dropSelf(tree.getCraftingTable());
                this.add(tree.getDoor(), BlockLoot::createDoorTable);
                this.dropSelf(tree.getFence());
                this.dropSelf(tree.getFenceGate());
                this.dropSelf(tree.getLog());
                this.dropSelf(tree.getStrippedLog());
                this.dropSelf(tree.getWood());
                this.dropSelf(tree.getStrippedWood());
                this.dropSelf(tree.getPlanks());
                this.dropSelf(tree.getPressurePlate());
                for (String saplingName : tree.getSaplingNames())
                {
                    this.dropSelf(tree.getSapling(saplingName));
                    this.dropPottedContents(tree.getPottedSapling(saplingName));
                }
                this.dropSelf(tree.getSign());
                this.dropSelf(tree.getStairs());
                this.dropSelf(tree.getSlab());
                this.dropSelf(tree.getTrapdoor());
                this.dropSelf(tree.getWallSign());
                this.add(tree.getLeaves(), (drops) -> createCustomLeavesDrops(drops, tree.getDefaultSapling(), tree::getFruit, NORMAL_LEAVES_SAPLING_CHANCES));
            }
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        List<Block> treeBlocks = RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues().stream().flatMap(tree -> tree.getAllBlocks().stream()).collect(Collectors.toList());
        treeBlocks.add(ExtraRegistry.BIRCH_CHEST.get());
        treeBlocks.add(ExtraRegistry.JUNGLE_CHEST.get());
        treeBlocks.add(ExtraRegistry.SPRUCE_CHEST.get());
        treeBlocks.add(ExtraRegistry.ACACIA_CHEST.get());
        treeBlocks.add(ExtraRegistry.DARK_OAK_CHEST.get());
        treeBlocks.add(ExtraRegistry.BIRCH_CRAFTING_TABLE.get());
        treeBlocks.add(ExtraRegistry.JUNGLE_CRAFTING_TABLE.get());
        treeBlocks.add(ExtraRegistry.SPRUCE_CRAFTING_TABLE.get());
        treeBlocks.add(ExtraRegistry.ACACIA_CRAFTING_TABLE.get());
        treeBlocks.add(ExtraRegistry.DARK_OAK_CRAFTING_TABLE.get());
        treeBlocks.add(ExtraRegistry.CRIMSON_CHEST.get());
        treeBlocks.add(ExtraRegistry.WARPED_CHEST.get());
        treeBlocks.add(ExtraRegistry.CRIMSON_CRAFTING_TABLE.get());
        treeBlocks.add(ExtraRegistry.WARPED_CRAFTING_TABLE.get());
        treeBlocks.add(ExtraRegistry.MAPLE_RED_LEAVES.get());
        treeBlocks.add(ExtraRegistry.MAPLE_BROWN_LEAVES.get());
        treeBlocks.add(ExtraRegistry.CHOPPING_BLOCK.get());
        return treeBlocks;
    }

}
