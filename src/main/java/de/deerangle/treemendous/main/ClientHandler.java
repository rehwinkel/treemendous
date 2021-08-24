package de.deerangle.treemendous.main;

import de.deerangle.treemendous.blockentity.render.CustomChestRenderer;
import de.deerangle.treemendous.entity.render.CustomBoatRenderer;
import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.Tree;
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

        ItemBlockRenderTypes.setRenderLayer(ExtraRegistry.BIRCH_CRAFTING_TABLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ExtraRegistry.JUNGLE_CRAFTING_TABLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ExtraRegistry.SPRUCE_CRAFTING_TABLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ExtraRegistry.ACACIA_CRAFTING_TABLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ExtraRegistry.DARK_OAK_CRAFTING_TABLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ExtraRegistry.CRIMSON_CRAFTING_TABLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ExtraRegistry.WARPED_CRAFTING_TABLE.get(), RenderType.cutout());

        BlockEntityRenderers.register(ExtraRegistry.SIGN.get(), SignRenderer::new);
        BlockEntityRenderers.register(ExtraRegistry.CHEST.get(), CustomChestRenderer::new);
        EntityRenderers.register(ExtraRegistry.BOAT.get(), CustomBoatRenderer::new);
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if (!event.getMap().location().equals(Sheets.CHEST_SHEET)) {
            return;
        }

        addChestSprites(event, "birch");
        addChestSprites(event, "spruce");
        addChestSprites(event, "jungle");
        addChestSprites(event, "acacia");
        addChestSprites(event, "dark_oak");
        addChestSprites(event, "crimson");
        addChestSprites(event, "warped");

        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues()) {
            Tree tree = regTree.getTree();
            addChestSprites(event, tree.getConfig().registryName());
        }
    }

    private static void addChestSprites(TextureStitchEvent.Pre event, String woodName) {
        event.addSprite(new ResourceLocation(Treemendous.MODID, "entity/chest/" + woodName));
        event.addSprite(new ResourceLocation(Treemendous.MODID, "entity/chest/" + woodName + "_left"));
        event.addSprite(new ResourceLocation(Treemendous.MODID, "entity/chest/" + woodName + "_right"));
    }

}
