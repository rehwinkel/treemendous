package de.deerangle.treemendous.data;

import de.deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class TreemendousBlockTagsProvider extends BlockTagsProvider {

    public TreemendousBlockTagsProvider(DataGenerator generator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(BlockTags.PLANKS).add(TreeRegistry.JUNIPER_TREE.getPlanks());
        this.tag(BlockTags.WOODEN_BUTTONS).add(TreeRegistry.JUNIPER_TREE.getButton());
        this.tag(BlockTags.WOODEN_DOORS).add(TreeRegistry.JUNIPER_TREE.getDoor());
        this.tag(BlockTags.WOODEN_STAIRS).add(TreeRegistry.JUNIPER_TREE.getStairs());
        this.tag(BlockTags.WOODEN_SLABS).add(TreeRegistry.JUNIPER_TREE.getSlab());
        this.tag(BlockTags.WOODEN_FENCES).add(TreeRegistry.JUNIPER_TREE.getFence());
        this.tag(BlockTags.SAPLINGS).add(TreeRegistry.JUNIPER_TREE.getSapling());
        this.tag(TreeRegistry.JUNIPER_TREE.getLogsBlockTag()).add(TreeRegistry.JUNIPER_TREE.getLog(), TreeRegistry.JUNIPER_TREE.getWood(), TreeRegistry.JUNIPER_TREE.getStrippedLog(), TreeRegistry.JUNIPER_TREE.getStrippedWood());
        this.tag(BlockTags.LOGS_THAT_BURN).addTag(TreeRegistry.JUNIPER_TREE.getLogsBlockTag());
        this.tag(BlockTags.FLOWER_POTS).add(TreeRegistry.JUNIPER_TREE.getPottedSapling());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(TreeRegistry.JUNIPER_TREE.getPressurePlate());
        this.tag(BlockTags.LEAVES).add(TreeRegistry.JUNIPER_TREE.getLeaves());
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(TreeRegistry.JUNIPER_TREE.getTrapdoor());
        this.tag(BlockTags.STANDING_SIGNS).add(TreeRegistry.JUNIPER_TREE.getSign());
        this.tag(BlockTags.WALL_SIGNS).add(TreeRegistry.JUNIPER_TREE.getWallSign());
        this.tag(BlockTags.FENCE_GATES).add(TreeRegistry.JUNIPER_TREE.getFenceGate());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(TreeRegistry.JUNIPER_TREE.getCraftingTable());
        this.tag(BlockTags.MINEABLE_WITH_HOE).add(TreeRegistry.JUNIPER_TREE.getLeaves());
    }
}
