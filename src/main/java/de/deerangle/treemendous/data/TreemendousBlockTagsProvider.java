package de.deerangle.treemendous.data;

import de.deerangle.treemendous.main.ExtraRegistry;
import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.Tree;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryManager;

import javax.annotation.Nullable;

public class TreemendousBlockTagsProvider extends BlockTagsProvider {

    public TreemendousBlockTagsProvider(DataGenerator generator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(BlockTags.LEAVES).add(ExtraRegistry.MAPLE_RED_LEAVES.get(), ExtraRegistry.MAPLE_BROWN_LEAVES.get());
        this.tag(BlockTags.GUARDED_BY_PIGLINS).add(ExtraRegistry.BIRCH_CHEST.get(), ExtraRegistry.SPRUCE_CHEST.get(), ExtraRegistry.JUNGLE_CHEST.get(), ExtraRegistry.ACACIA_CHEST.get(), ExtraRegistry.DARK_OAK_CHEST.get(), ExtraRegistry.CRIMSON_CHEST.get(), ExtraRegistry.WARPED_CHEST.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ExtraRegistry.BIRCH_CRAFTING_TABLE.get(), ExtraRegistry.SPRUCE_CRAFTING_TABLE.get(), ExtraRegistry.JUNGLE_CRAFTING_TABLE.get(), ExtraRegistry.ACACIA_CRAFTING_TABLE.get(), ExtraRegistry.DARK_OAK_CRAFTING_TABLE.get(), ExtraRegistry.CRIMSON_CRAFTING_TABLE.get(), ExtraRegistry.WARPED_CRAFTING_TABLE.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ExtraRegistry.BIRCH_CHEST.get(), ExtraRegistry.SPRUCE_CHEST.get(), ExtraRegistry.JUNGLE_CHEST.get(), ExtraRegistry.ACACIA_CHEST.get(), ExtraRegistry.DARK_OAK_CHEST.get(), ExtraRegistry.CRIMSON_CHEST.get(), ExtraRegistry.WARPED_CHEST.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ExtraRegistry.CHOPPING_BLOCK.get());
        this.tag(BlockTags.FEATURES_CANNOT_REPLACE).add(ExtraRegistry.BIRCH_CHEST.get(), ExtraRegistry.SPRUCE_CHEST.get(), ExtraRegistry.JUNGLE_CHEST.get(), ExtraRegistry.ACACIA_CHEST.get(), ExtraRegistry.DARK_OAK_CHEST.get(), ExtraRegistry.CRIMSON_CHEST.get(), ExtraRegistry.WARPED_CHEST.get());

        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues()) {
            Tree tree = regTree.getTree();
            this.tag(BlockTags.PLANKS).add(tree.getPlanks());
            this.tag(BlockTags.WOODEN_BUTTONS).add(tree.getButton());
            this.tag(BlockTags.WOODEN_DOORS).add(tree.getDoor());
            this.tag(BlockTags.WOODEN_STAIRS).add(tree.getStairs());
            this.tag(BlockTags.WOODEN_SLABS).add(tree.getSlab());
            this.tag(BlockTags.WOODEN_FENCES).add(tree.getFence());
            TagAppender<Block> saplingsTag = this.tag(BlockTags.SAPLINGS);
            TagAppender<Block> pottedSaplingsTag = this.tag(BlockTags.FLOWER_POTS);
            for (int i = 0; i < tree.getSaplings(); i++) {
                saplingsTag.add(tree.getSapling(i));
                pottedSaplingsTag.add(tree.getPottedSapling(i));
            }
            this.tag(BlockTags.GUARDED_BY_PIGLINS).add(tree.getChest());
            this.tag(tree.getLogsBlockTag()).add(tree.getLog(), tree.getWood(), tree.getStrippedLog(), tree.getStrippedWood());
            this.tag(BlockTags.LOGS_THAT_BURN).addTag(tree.getLogsBlockTag());
            this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(tree.getPressurePlate());
            this.tag(BlockTags.LEAVES).add(tree.getLeaves());
            this.tag(BlockTags.WOODEN_TRAPDOORS).add(tree.getTrapdoor());
            this.tag(BlockTags.STANDING_SIGNS).add(tree.getSign());
            this.tag(BlockTags.WALL_SIGNS).add(tree.getWallSign());
            this.tag(BlockTags.FENCE_GATES).add(tree.getFenceGate());
            this.tag(BlockTags.MINEABLE_WITH_AXE).add(tree.getCraftingTable(), tree.getChest());
            this.tag(BlockTags.MINEABLE_WITH_HOE).add(tree.getLeaves());
            this.tag(BlockTags.FEATURES_CANNOT_REPLACE).add(tree.getChest());
        }
    }
}
