package de.deerangle.treemendous.main;

import de.deerangle.treemendous.blockentity.render.ChoppingBlockRenderer;
import de.deerangle.treemendous.blockentity.render.CustomChestRenderer;
import de.deerangle.treemendous.entity.render.CustomBoatRenderer;
import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.Tree;
import de.deerangle.treemendous.util.WoodColors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.block.BlockTintCache;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.chunk.RenderChunkRegion;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryManager;

import java.awt.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = Treemendous.MODID)
public class ClientHandler
{

    private static final ColorResolverWithBase FOLIAGE_COLOR_RESOLVER = new ColorResolverWithBase();

    public static int getAverageFoliageColor(BlockAndTintGetter tintGetter, BlockPos pos, int baseColor)
    {
        ClientLevel level;
        if (tintGetter instanceof ClientLevel)
        {
            level = (ClientLevel) tintGetter;
        } else if (tintGetter instanceof RenderChunkRegion region)
        {
            level = (ClientLevel) region.level;
        } else
        {
            level = null;
        }
        if (level != null)
        {
            BlockTintCache blockTintCache = level.tintCaches.get(FOLIAGE_COLOR_RESOLVER);
            if (blockTintCache == null)
            {
                level.tintCaches.put(FOLIAGE_COLOR_RESOLVER, new BlockTintCache());
                blockTintCache = level.tintCaches.get(FOLIAGE_COLOR_RESOLVER);
            }
            return blockTintCache.getColor(pos, () -> level.calculateBlockTint(pos, (biome, x, y) -> FOLIAGE_COLOR_RESOLVER.getColorWithBase(biome, baseColor)));
        } else
        {
            return tintGetter.getBlockTint(pos, BiomeColors.FOLIAGE_COLOR_RESOLVER);
        }
    }

    private static int getLeavesColor(Tree tree, BlockAndTintGetter blockAndTintGetter, BlockPos pos)
    {
        int baseColor = tree.getLeavesColor().getColor(pos);
        if (tree.getLeavesColor().isBiomeDependent())
        {
            return blockAndTintGetter != null && pos != null ? getAverageFoliageColor(blockAndTintGetter, pos, baseColor) : baseColor;
        } else
        {
            return baseColor;
        }
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        ItemColors itemColors = Minecraft.getInstance().getItemColors();

        blockColors.register((state, blockAndTintGetter, pos, tindId) -> WoodColors.MAPLE_RED_LEAVES, ExtraRegistry.MAPLE_RED_LEAVES.get());
        blockColors.register((state, blockAndTintGetter, pos, tindId) -> WoodColors.MAPLE_BROWN_LEAVES, ExtraRegistry.MAPLE_BROWN_LEAVES.get());
        itemColors.register((stack, tintId) -> WoodColors.MAPLE_RED_LEAVES, ExtraRegistry.MAPLE_RED_LEAVES.get());
        itemColors.register((stack, tintId) -> WoodColors.MAPLE_BROWN_LEAVES, ExtraRegistry.MAPLE_BROWN_LEAVES.get());

        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues())
        {
            if (!regTree.isFake())
            {
                Tree tree = regTree.getTree();
                event.enqueueWork(() -> Sheets.addWoodType(tree.getWoodType()));
                blockColors.register((state, blockAndTintGetter, pos, tindId) -> getLeavesColor(tree, blockAndTintGetter, pos), tree.getLeaves());
                itemColors.register((stack, tintId) -> tree.getLeavesColor().getColor(BlockPos.ZERO), tree.getLeaves());
                ItemBlockRenderTypes.setRenderLayer(tree.getTrapdoor(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(tree.getDoor(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(tree.getLeaves(), RenderType.cutout());
                for (String saplingName : tree.getSaplingNames())
                {
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
    public static void onTextureStitch(TextureStitchEvent.Pre event)
    {
        if (!event.getMap().location().equals(Sheets.CHEST_SHEET))
        {
            return;
        }

        addChestSprites(event, "crimson");
        addChestSprites(event, "warped");

        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues())
        {
            String woodName = regTree.getRegistryName().getPath();
            if (!"oak".equals(woodName))
            {
                addChestSprites(event, woodName);
            }
        }
    }

    private static void addChestSprites(TextureStitchEvent.Pre event, String woodName)
    {
        event.addSprite(new ResourceLocation(Treemendous.MODID, "entity/chest/" + woodName));
        event.addSprite(new ResourceLocation(Treemendous.MODID, "entity/chest/" + woodName + "_left"));
        event.addSprite(new ResourceLocation(Treemendous.MODID, "entity/chest/" + woodName + "_right"));
    }

    private static class ColorResolverWithBase implements ColorResolver
    {

        private static int getFoliageColor(Biome biome, int baseColor)
        {
            return biome.getSpecialEffects().getFoliageColorOverride().orElseGet(() -> {
                double temperature = Mth.clamp(biome.climateSettings.temperature, 0.0F, 1.0F);
                double downfall = Mth.clamp(biome.climateSettings.downfall, 0.0F, 1.0F);
                float[] hsb = new float[3];
                Color.RGBtoHSB((baseColor >> 16) & 0xFF, (baseColor >> 8) & 0xFF, baseColor & 0xFF, hsb);
                //TODO: use base color
                /*
                // hot
                [0.28237793, 0.8674033, 0.70980394]
                [0.15404041, 0.7586207, 0.68235296]

                // moist
                [0.28237793, 0.8674033, 0.70980394]
                [0.29829547, 0.9411765, 0.73333335]
                 */
                return Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
            });
        }

        @Override
        public int getColor(Biome biome, double x, double y)
        {
            return 0;
        }

        public int getColorWithBase(Biome biome, int baseColor)
        {
            return getFoliageColor(biome, baseColor);
        }

    }
}
