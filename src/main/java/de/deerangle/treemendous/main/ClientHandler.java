package de.deerangle.treemendous.main;

import de.deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = Treemendous.MODID)
public class ClientHandler {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            Sheets.addWoodType(TreeRegistry.JUNIPER_TREE.getWoodType());
        });
        ItemBlockRenderTypes.setRenderLayer(TreeRegistry.JUNIPER_TREE.getTrapdoor(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(TreeRegistry.JUNIPER_TREE.getDoor(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(TreeRegistry.JUNIPER_TREE.getLeaves(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(TreeRegistry.JUNIPER_TREE.getSapling(), RenderType.cutout());
    }

}
