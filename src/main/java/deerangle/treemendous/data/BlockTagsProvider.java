package deerangle.treemendous.data;

import deerangle.treemendous.tree.RegisteredTree;
import deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

public class BlockTagsProvider extends net.minecraft.data.BlockTagsProvider {

    public BlockTagsProvider(DataGenerator gen, String modId, ExistingFileHelper existingFileHelper) {
        super(gen);
    }

    @Override
    protected void registerTags() {
        for (RegisteredTree tree : TreeRegistry.TREES) {
            getBuilder(BlockTags.SAPLINGS).add(tree.sapling.get());
            getBuilder(BlockTags.LEAVES).add(tree.leaves.get());

            if (tree.isNotInherited()) {
                getBuilder(BlockTags.WOODEN_TRAPDOORS).add(tree.trapdoor.get());
                getBuilder(BlockTags.WOODEN_DOORS).add(tree.door.get());
                getBuilder(tree.logsBlockTag).add(tree.log.get()).add(tree.stripped_log.get()).add(tree.wood.get())
                        .add(tree.stripped_wood.get());
                getBuilder(BlockTags.WOODEN_FENCES).add(tree.fence.get());
                getBuilder(BlockTags.LOGS).add(tree.logsBlockTag);
                getBuilder(BlockTags.PLANKS).add(tree.planks.get());

                getBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(tree.planks.get());
                getBuilder(BlockTags.WOODEN_SLABS).add(tree.slab.get());
                getBuilder(BlockTags.WOODEN_STAIRS).add(tree.stairs.get());
                getBuilder(BlockTags.WOODEN_BUTTONS).add(tree.button.get());
                getBuilder(BlockTags.FLOWER_POTS).add(tree.potted_sapling.get());
                getBuilder(BlockTags.WALL_SIGNS).add(tree.wall_sign.get());
                getBuilder(BlockTags.STANDING_SIGNS).add(tree.sign.get());
            }
        }
    }
}
