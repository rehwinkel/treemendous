package de.deerangle.treemendous.main;

import de.deerangle.treemendous.tree.RegisteredTree;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Treemendous.MODID)
public class CommonHandler {

    public static RegisteredTree JUNIPER_TREE;

    @SubscribeEvent
    public static void registerTrees(RegistryEvent.Register<RegisteredTree> event) {
        IForgeRegistry<RegisteredTree> registry = event.getRegistry();
        JUNIPER_TREE = new RegisteredTree(TreeRegistry.JUNIPER_TREE);
        JUNIPER_TREE.setRegistryName(new ResourceLocation(Treemendous.MODID, TreeRegistry.JUNIPER_TREE.getConfig().registryName()));
        registry.registerAll(JUNIPER_TREE);
    }

}
