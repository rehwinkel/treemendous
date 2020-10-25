package deerangle.treemendous.data;

import deerangle.treemendous.tree.RegisteredTree;
import deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

public class ItemTagsProvider extends net.minecraft.data.ItemTagsProvider {

    public ItemTagsProvider(DataGenerator gen, String modId, ExistingFileHelper existingFileHelper) {
        super(gen);
    }

    @Override
    protected void registerTags() {
        for (RegisteredTree tree : TreeRegistry.TREES) {
            getBuilder(ItemTags.SAPLINGS).add(tree.sapling_item.get());
            getBuilder(ItemTags.LEAVES).add(tree.leaves_item.get());

            if (tree.isNotInherited()) {
                getBuilder(tree.logsItemTag).add(tree.log_item.get()).add(tree.stripped_log_item.get())
                        .add(tree.wood_item.get()).add(tree.stripped_wood_item.get());
                getBuilder(ItemTags.WOODEN_TRAPDOORS).add(tree.trapdoor_item.get());
                getBuilder(ItemTags.WOODEN_DOORS).add(tree.door_item.get());
                getBuilder(ItemTags.WOODEN_FENCES).add(tree.fence_item.get());
                getBuilder(ItemTags.LOGS).add(tree.logsItemTag);
                getBuilder(ItemTags.PLANKS).add(tree.planks_item.get());
                getBuilder(ItemTags.WOODEN_PRESSURE_PLATES).add(tree.pressure_plate_item.get());
                getBuilder(ItemTags.WOODEN_SLABS).add(tree.slab_item.get());
                getBuilder(ItemTags.WOODEN_STAIRS).add(tree.stairs_item.get());
                getBuilder(ItemTags.WOODEN_BUTTONS).add(tree.button_item.get());
                getBuilder(ItemTags.SIGNS).add(tree.sign_item.get());
                getBuilder(ItemTags.BOATS).add(tree.boat_item.get());
            }
        }
    }
}
