package deerangle.treemendous.main;

import deerangle.treemendous.entity.render.CustomBoatRenderer;
import deerangle.treemendous.tree.RegisteredTree;
import deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.item.BlockItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = Treemendous.MODID)
public class ClientProxy implements Proxy {

    @SubscribeEvent
    public static void onBlockColors(ColorHandlerEvent.Block event) {
        for (RegisteredTree tree : TreeRegistry.TREES) {
            event.getBlockColors()
                    .register((blockState, world, blockPos, tintIndex) -> tree.getLeavesColor().getColor(blockPos),
                            tree.leaves.get());
        }
    }

    @SubscribeEvent
    public static void onItemColors(ColorHandlerEvent.Item event) {
        for (RegisteredTree tree : TreeRegistry.TREES) {
            event.getItemColors().register((stack, tintIndex) -> {
                BlockState blockstate = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
                return event.getBlockColors().getColor(blockstate, null, null, tintIndex);
            }, tree.leaves.get());
        }
    }

    @Override
    public void clientSetup() {
        for (RegisteredTree tree : TreeRegistry.TREES) {
            RenderTypeLookup.setRenderLayer(tree.trapdoor.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(tree.door.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(tree.leaves.get(), RenderType.getCutoutMipped());
            RenderTypeLookup.setRenderLayer(tree.sapling.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(tree.potted_sapling.get(), RenderType.getCutout());
            ClientRegistry.bindTileEntityRenderer(ExtraRegistry.SIGN.get(), SignTileEntityRenderer::new);
            RenderingRegistry.registerEntityRenderingHandler(ExtraRegistry.BOAT.get(), CustomBoatRenderer::new);
        }
    }

}
