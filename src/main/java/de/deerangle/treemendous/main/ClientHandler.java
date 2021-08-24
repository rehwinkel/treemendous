package de.deerangle.treemendous.main;

import de.deerangle.treemendous.blockentity.render.CustomChestRenderer;
import de.deerangle.treemendous.entity.render.CustomBoatRenderer;
import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.Tree;
import de.deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryManager;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = Treemendous.MODID)
public class ClientHandler {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues()) {
            Tree tree = regTree.getTree();
            event.enqueueWork(() -> Sheets.addWoodType(tree.getWoodType()));
            ItemBlockRenderTypes.setRenderLayer(tree.getTrapdoor(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(tree.getDoor(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(tree.getLeaves(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(tree.getSapling(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(tree.getPottedSapling(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(tree.getCraftingTable(), RenderType.cutout());
        }

        BlockEntityRenderers.register(TreeRegistry.SIGN.get(), SignRenderer::new);
        BlockEntityRenderers.register(TreeRegistry.CHEST.get(), CustomChestRenderer::new);
        EntityRenderers.register(TreeRegistry.BOAT.get(), CustomBoatRenderer::new);
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if (!event.getMap().location().equals(Sheets.CHEST_SHEET)) {
            return;
        }

        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues()) {
            Tree tree = regTree.getTree();
            event.addSprite(new ResourceLocation(Treemendous.MODID, "entity/chest/" + tree.getConfig().registryName()));
            event.addSprite(new ResourceLocation(Treemendous.MODID, "entity/chest/" + tree.getConfig().registryName() + "_left"));
            event.addSprite(new ResourceLocation(Treemendous.MODID, "entity/chest/" + tree.getConfig().registryName() + "_right"));
        }
    }

}
