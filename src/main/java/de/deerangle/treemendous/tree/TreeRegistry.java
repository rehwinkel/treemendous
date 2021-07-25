package de.deerangle.treemendous.tree;

import de.deerangle.treemendous.main.Treemendous;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TreeRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Treemendous.MODID);
    public static final DeferredRegister<Block> ITEMS = DeferredRegister.create(ForgeRegistries.BLOCKS, Treemendous.MODID);

    private static final TreeConfig JUNIPER_CONFIG = new TreeConfigBuilder("juniper").setLeavesColor(0x00ff00).createTreeConfig();
    public static final Tree JUNIPER_TREE = Tree.fromConfig(BLOCKS, ITEMS, JUNIPER_CONFIG);

}
