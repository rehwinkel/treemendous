package de.deerangle.treemendous.data;


import de.deerangle.treemendous.main.ExtraRegistry;
import de.deerangle.treemendous.main.Treemendous;
import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.Tree;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryManager;

import javax.annotation.Nullable;

public class TreemendousItemTagsProvider extends ItemTagsProvider
{

    public static final Tag.Named<Item> FITS_IN_CHOPPING_BLOCK = ItemTags.bind(Treemendous.MOD_ID + ":fits_in_chopping_block");

    public TreemendousItemTagsProvider(DataGenerator generator, BlockTagsProvider provider, String modId, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(generator, provider, modId, existingFileHelper);
    }

    @Override
    protected void addTags()
    {
        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues())
        {
            if (regTree.isNotFake())
            {
                Tree tree = regTree.getTree();
                this.copy(tree.getLogsBlockTag(), tree.getLogsItemTag());
                this.tag(ItemTags.BOATS).add(tree.getBoatItem());
            }
        }

        this.tag(FITS_IN_CHOPPING_BLOCK).add(Items.IRON_AXE, Items.WOODEN_AXE, Items.STONE_AXE, Items.GOLDEN_AXE, Items.DIAMOND_AXE, Items.NETHERITE_AXE);
        this.tag(FITS_IN_CHOPPING_BLOCK).add(ExtraRegistry.IRON_LUMBER_AXE.get(), ExtraRegistry.GOLDEN_LUMBER_AXE.get(), ExtraRegistry.DIAMOND_LUMBER_AXE.get(), ExtraRegistry.NETHERITE_LUMBER_AXE.get());

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
    }
}
