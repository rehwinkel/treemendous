package deerangle.treemendous.data;

import deerangle.treemendous.tree.RegisteredTree;
import deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagsProvider extends net.minecraft.data.BlockTagsProvider {

    public BlockTagsProvider(DataGenerator gen, String modId, ExistingFileHelper existingFileHelper) {
        super(gen, modId, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        for (RegisteredTree tree : TreeRegistry.TREES) {
            getOrCreateBuilder(BlockTags.SAPLINGS).add(tree.sapling.get());
            getOrCreateBuilder(BlockTags.LEAVES).add(tree.leaves.get());

            if (tree.isNotInherited()) {
                getOrCreateBuilder(BlockTags.WOODEN_TRAPDOORS).add(tree.trapdoor.get());
                getOrCreateBuilder(BlockTags.WOODEN_DOORS).add(tree.door.get());
                getOrCreateBuilder(tree.logsBlockTag).add(tree.log.get()).add(tree.stripped_log.get())
                        .add(tree.wood.get()).add(tree.stripped_wood.get());
                getOrCreateBuilder(BlockTags.FENCE_GATES).add(tree.fence_gate.get());
                getOrCreateBuilder(BlockTags.WOODEN_FENCES).add(tree.fence.get());
                getOrCreateBuilder(BlockTags.LOGS_THAT_BURN).addTag(tree.logsBlockTag);
                getOrCreateBuilder(BlockTags.PLANKS).add(tree.planks.get());

                getOrCreateBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(tree.planks.get());
                getOrCreateBuilder(BlockTags.WOODEN_SLABS).add(tree.slab.get());
                getOrCreateBuilder(BlockTags.WOODEN_STAIRS).add(tree.stairs.get());
                getOrCreateBuilder(BlockTags.WOODEN_BUTTONS).add(tree.button.get());
                getOrCreateBuilder(BlockTags.FLOWER_POTS).add(tree.potted_sapling.get());
                getOrCreateBuilder(BlockTags.WALL_SIGNS).add(tree.wall_sign.get());
                getOrCreateBuilder(BlockTags.STANDING_SIGNS).add(tree.sign.get());
            }
        }
    }
}
