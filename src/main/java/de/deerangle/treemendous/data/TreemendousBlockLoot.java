package de.deerangle.treemendous.data;

import de.deerangle.treemendous.main.ExtraRegistry;
import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.Tree;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryManager;

import java.util.List;
import java.util.stream.Collectors;

public class TreemendousBlockLoot extends BlockLoot {

    private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

    @Override
    protected void addTables() {
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

        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues()) {
            Tree tree = regTree.getTree();
            this.dropSelf(tree.getButton());
            this.dropSelf(tree.getChest());
            this.dropSelf(tree.getCraftingTable());
            this.dropSelf(tree.getDoor());
            this.dropSelf(tree.getFence());
            this.dropSelf(tree.getFenceGate());
            this.dropSelf(tree.getLog());
            this.dropSelf(tree.getStrippedLog());
            this.dropSelf(tree.getWood());
            this.dropSelf(tree.getStrippedWood());
            this.dropSelf(tree.getPlanks());
            this.dropSelf(tree.getPressurePlate());
            for (int i = 0; i < tree.getSaplings(); i++) {
                this.dropSelf(tree.getSapling(i));
                this.dropPottedContents(tree.getPottedSapling(i));
            }
            this.dropSelf(tree.getSign());
            this.dropSelf(tree.getStairs());
            this.dropSelf(tree.getSlab());
            this.dropSelf(tree.getTrapdoor());
            this.dropSelf(tree.getWallSign());
            this.add(tree.getLeaves(), (drops) -> createLeavesDrops(drops, tree.getDefaultSapling(), NORMAL_LEAVES_SAPLING_CHANCES));
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected Iterable<Block> getKnownBlocks() {
        List<Block> treeBlocks = RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues().stream().flatMap(tree -> tree.getAllBlocks().stream()).collect(Collectors.toList());
        treeBlocks.add(ExtraRegistry.BIRCH_CHEST.get());
        treeBlocks.add(ExtraRegistry.JUNGLE_CHEST.get());
        treeBlocks.add(ExtraRegistry.SPRUCE_CHEST.get());
        treeBlocks.add(ExtraRegistry.ACACIA_CHEST.get());
        treeBlocks.add(ExtraRegistry.DARK_OAK_CHEST.get());
        treeBlocks.add(ExtraRegistry.CRIMSON_CHEST.get());
        treeBlocks.add(ExtraRegistry.WARPED_CHEST.get());
        treeBlocks.add(ExtraRegistry.BIRCH_CRAFTING_TABLE.get());
        treeBlocks.add(ExtraRegistry.JUNGLE_CRAFTING_TABLE.get());
        treeBlocks.add(ExtraRegistry.SPRUCE_CRAFTING_TABLE.get());
        treeBlocks.add(ExtraRegistry.ACACIA_CRAFTING_TABLE.get());
        treeBlocks.add(ExtraRegistry.DARK_OAK_CRAFTING_TABLE.get());
        treeBlocks.add(ExtraRegistry.CRIMSON_CRAFTING_TABLE.get());
        treeBlocks.add(ExtraRegistry.WARPED_CRAFTING_TABLE.get());
        return treeBlocks;
    }

}