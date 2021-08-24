package de.deerangle.treemendous.main;

import de.deerangle.treemendous.data.WoodColors;
import de.deerangle.treemendous.tree.Tree;
import de.deerangle.treemendous.tree.TreeConfig;
import de.deerangle.treemendous.tree.TreeConfigBuilder;
import de.deerangle.treemendous.tree.TreeTexture;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TreeRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Treemendous.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Treemendous.MODID);

    private static final TreeConfig JUNIPER_CONFIG = new TreeConfigBuilder("juniper").setAppearance(new TreeTexture(WoodColors.JUNIPER_WOOD, WoodColors.JUNIPER_WOOD, 0)).setLeavesColor(WoodColors.JUNIPER_LEAVES).createTreeConfig();
    public static final Tree JUNIPER_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, JUNIPER_CONFIG));

}
