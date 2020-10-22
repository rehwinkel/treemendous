package deerangle.treemendous.data;

import deerangle.treemendous.tree.RegisteredTree;
import deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagsProvider extends net.minecraft.data.ItemTagsProvider {

    public ItemTagsProvider(DataGenerator gen, String modId, ExistingFileHelper existingFileHelper) {
        super(gen, new BlockTagsProvider(gen, modId, existingFileHelper), modId, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        for (RegisteredTree tree : TreeRegistry.TREES) {
            getOrCreateBuilder(ItemTags.SAPLINGS).add(tree.sapling_item.get());
            getOrCreateBuilder(ItemTags.LEAVES).add(tree.leaves_item.get());

            if (tree.isNotInherited()) {
                getOrCreateBuilder(tree.logsItemTag).add(tree.log_item.get()).add(tree.stripped_log_item.get())
                        .add(tree.wood_item.get()).add(tree.stripped_wood_item.get());
                getOrCreateBuilder(ItemTags.WOODEN_TRAPDOORS).add(tree.trapdoor_item.get());
                getOrCreateBuilder(ItemTags.WOODEN_DOORS).add(tree.door_item.get());
                getOrCreateBuilder(ItemTags.WOODEN_FENCES).add(tree.fence_item.get());
                getOrCreateBuilder(ItemTags.LOGS_THAT_BURN).addTag(tree.logsItemTag);
                getOrCreateBuilder(ItemTags.PLANKS).add(tree.planks_item.get());
                getOrCreateBuilder(ItemTags.WOODEN_PRESSURE_PLATES).add(tree.pressure_plate_item.get());
                getOrCreateBuilder(ItemTags.WOODEN_SLABS).add(tree.slab_item.get());
                getOrCreateBuilder(ItemTags.WOODEN_STAIRS).add(tree.stairs_item.get());
                getOrCreateBuilder(ItemTags.WOODEN_BUTTONS).add(tree.button_item.get());
                getOrCreateBuilder(ItemTags.SIGNS).add(tree.sign_item.get());
                getOrCreateBuilder(ItemTags.BOATS).add(tree.boat_item.get());
            }
        }
    }
}
