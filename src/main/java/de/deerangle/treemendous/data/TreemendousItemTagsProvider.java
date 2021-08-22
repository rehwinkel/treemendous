package de.deerangle.treemendous.data;


import de.deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class TreemendousItemTagsProvider extends ItemTagsProvider {

    private final BlockTagsProvider blockTagsProvider;

    public TreemendousItemTagsProvider(DataGenerator generator, BlockTagsProvider provider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, provider, modId, existingFileHelper);
        this.blockTagsProvider = provider;
    }

    @Override
    protected void addTags() {
        this.copy(TreeRegistry.JUNIPER_TREE.getLogsBlockTag(), TreeRegistry.JUNIPER_TREE.getLogsItemTag());
        this.tag(ItemTags.BOATS).add(TreeRegistry.JUNIPER_TREE.getBoatItem());
    }
}
