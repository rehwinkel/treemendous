package de.deerangle.treemendous.data;


import de.deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
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
        this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
        this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
        this.copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        this.copy(BlockTags.LEAVES, ItemTags.LEAVES);

        this.copy(TreeRegistry.JUNIPER_TREE.getLogsBlockTag(), TreeRegistry.JUNIPER_TREE.getLogsItemTag());
        this.tag(ItemTags.BOATS).add(TreeRegistry.JUNIPER_TREE.getBoatItem());
    }
}
