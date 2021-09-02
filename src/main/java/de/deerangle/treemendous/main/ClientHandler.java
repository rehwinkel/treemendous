package de.deerangle.treemendous.main;

import de.deerangle.treemendous.blockentity.render.ChoppingBlockRenderer;
import de.deerangle.treemendous.blockentity.render.CustomChestRenderer;
import de.deerangle.treemendous.entity.render.CustomBoatRenderer;
import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.Tree;
import de.deerangle.treemendous.util.WoodColors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.BlockPos;
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
        BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        ItemColors itemColors = Minecraft.getInstance().getItemColors();

        blockColors.register((state, blockAndTintGetter, pos, tindId) -> WoodColors.MAPLE_RED_LEAVES, ExtraRegistry.MAPLE_RED_LEAVES.get());
        blockColors.register((state, blockAndTintGetter, pos, tindId) -> WoodColors.MAPLE_BROWN_LEAVES, ExtraRegistry.MAPLE_BROWN_LEAVES.get());
        itemColors.register((stack, tintId) -> WoodColors.MAPLE_RED_LEAVES, ExtraRegistry.MAPLE_RED_LEAVES.get());
        itemColors.register((stack, tintId) -> WoodColors.MAPLE_BROWN_LEAVES, ExtraRegistry.MAPLE_BROWN_LEAVES.get());

        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues()) {
            if (!regTree.isFake()) {
                Tree tree = regTree.getTree();
                event.enqueueWork(() -> Sheets.addWoodType(tree.getWoodType()));
                blockColors.register((state, blockAndTintGetter, pos, tindId) -> tree.getLeavesColor().getColor(pos), tree.getLeaves());
                itemColors.register((stack, tintId) -> tree.getLeavesColor().getColor(BlockPos.ZERO), tree.getLeaves());
                ItemBlockRenderTypes.setRenderLayer(tree.getTrapdoor(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(tree.getDoor(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(tree.getLeaves(), RenderType.cutout());
                for (String saplingName : tree.getSaplingNames()) {
                    ItemBlockRenderTypes.setRenderLayer(tree.getSapling(saplingName), RenderType.cutout());
                    ItemBlockRenderTypes.setRenderLayer(tree.getPottedSapling(saplingName), RenderType.cutout());
                }
                ItemBlockRenderTypes.setRenderLayer(tree.getCraftingTable(), RenderType.cutout());
            }
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
        BlockEntityRenderers.register(ExtraRegistry.CHOPPING_BLOCK_BE.get(), ChoppingBlockRenderer::new);
        EntityRenderers.register(ExtraRegistry.BOAT.get(), CustomBoatRenderer::new);
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if (!event.getMap().location().equals(Sheets.CHEST_SHEET)) {
            return;
        }

        addChestSprites(event, "crimson");
        addChestSprites(event, "warped");

        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues()) {
            String woodName = regTree.getRegistryName().getPath();
            if (!"oak".equals(woodName)) {
                addChestSprites(event, woodName);
            }
        }
    }

    private static void addChestSprites(TextureStitchEvent.Pre event, String woodName) {
        event.addSprite(new ResourceLocation(Treemendous.MODID, "entity/chest/" + woodName));
        event.addSprite(new ResourceLocation(Treemendous.MODID, "entity/chest/" + woodName + "_left"));
        event.addSprite(new ResourceLocation(Treemendous.MODID, "entity/chest/" + woodName + "_right"));
    }

}
