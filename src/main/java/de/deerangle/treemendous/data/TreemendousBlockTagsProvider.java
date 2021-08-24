package de.deerangle.treemendous.data;

import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.Tree;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryManager;

import javax.annotation.Nullable;

public class TreemendousBlockTagsProvider extends BlockTagsProvider {

    public TreemendousBlockTagsProvider(DataGenerator generator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues()) {
            Tree tree = regTree.getTree();
            this.tag(BlockTags.PLANKS).add(tree.getPlanks());
            this.tag(BlockTags.WOODEN_BUTTONS).add(tree.getButton());
            this.tag(BlockTags.WOODEN_DOORS).add(tree.getDoor());
            this.tag(BlockTags.WOODEN_STAIRS).add(tree.getStairs());
            this.tag(BlockTags.WOODEN_SLABS).add(tree.getSlab());
            this.tag(BlockTags.WOODEN_FENCES).add(tree.getFence());
            this.tag(BlockTags.SAPLINGS).add(tree.getSapling());
            this.tag(tree.getLogsBlockTag()).add(tree.getLog(), tree.getWood(), tree.getStrippedLog(), tree.getStrippedWood());
            this.tag(BlockTags.LOGS_THAT_BURN).addTag(tree.getLogsBlockTag());
            this.tag(BlockTags.FLOWER_POTS).add(tree.getPottedSapling());
            this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(tree.getPressurePlate());
            this.tag(BlockTags.LEAVES).add(tree.getLeaves());
            this.tag(BlockTags.WOODEN_TRAPDOORS).add(tree.getTrapdoor());
            this.tag(BlockTags.STANDING_SIGNS).add(tree.getSign());
            this.tag(BlockTags.WALL_SIGNS).add(tree.getWallSign());
            this.tag(BlockTags.FENCE_GATES).add(tree.getFenceGate());
            this.tag(BlockTags.MINEABLE_WITH_AXE).add(tree.getCraftingTable());
            this.tag(BlockTags.MINEABLE_WITH_HOE).add(tree.getLeaves());
        }
    }
}
