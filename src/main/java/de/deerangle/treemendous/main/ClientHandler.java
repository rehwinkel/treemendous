package de.deerangle.treemendous.main;

import de.deerangle.treemendous.blockentity.render.ChoppingBlockRenderer;
import de.deerangle.treemendous.blockentity.render.CustomChestRenderer;
import de.deerangle.treemendous.entity.render.CustomBoatRenderer;
import de.deerangle.treemendous.entity.render.WoodpeckerModel;
import de.deerangle.treemendous.entity.render.WoodpeckerRenderer;
import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.Tree;
import de.deerangle.treemendous.util.WoodColors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.block.BlockTintCache;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.model.geom.ModelLayerLocation;
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
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryManager;

import java.awt.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = Treemendous.MOD_ID)
public class ClientHandler
{

    public static final ModelLayerLocation WOODPECKER_LAYER = createLayerLocation("woodpecker");
    private static final ColorResolverWithBase FOLIAGE_COLOR_RESOLVER = new ColorResolverWithBase();

    private static ModelLayerLocation createLayerLocation(String name)
    {
        return new ModelLayerLocation(new ResourceLocation(Treemendous.MOD_ID, name), "main");
    }

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

        blockColors.register((state, blockAndTintGetter, pos, tintId) -> WoodColors.MAPLE_RED_LEAVES, ExtraRegistry.MAPLE_RED_LEAVES.get());
        blockColors.register((state, blockAndTintGetter, pos, tintId) -> WoodColors.MAPLE_BROWN_LEAVES, ExtraRegistry.MAPLE_BROWN_LEAVES.get());
        itemColors.register((stack, tintId) -> WoodColors.MAPLE_RED_LEAVES, ExtraRegistry.MAPLE_RED_LEAVES.get());
        itemColors.register((stack, tintId) -> WoodColors.MAPLE_BROWN_LEAVES, ExtraRegistry.MAPLE_BROWN_LEAVES.get());

        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues())
        {
            if (regTree.isNotFake())
            {
                Tree tree = regTree.getTree();
                event.enqueueWork(() -> Sheets.addWoodType(tree.getWoodType()));
                blockColors.register((state, blockAndTintGetter, pos, tintId) -> getLeavesColor(tree, blockAndTintGetter, pos), tree.getLeaves());
                itemColors.register((stack, tintId) -> tree.getLeavesColor().getColor(), tree.getLeaves());
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
        EntityRenderers.register(ExtraRegistry.WOODPECKER.get(), WoodpeckerRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(WOODPECKER_LAYER, WoodpeckerModel::createBodyLayer);
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
            assert regTree.getRegistryName() != null;
            String woodName = regTree.getRegistryName().getPath();
            if (!"oak".equals(woodName))
            {
                addChestSprites(event, woodName);
            }
        }
    }

    private static void addChestSprites(TextureStitchEvent.Pre event, String woodName)
    {
        event.addSprite(new ResourceLocation(Treemendous.MOD_ID, "entity/chest/" + woodName));
        event.addSprite(new ResourceLocation(Treemendous.MOD_ID, "entity/chest/" + woodName + "_left"));
        event.addSprite(new ResourceLocation(Treemendous.MOD_ID, "entity/chest/" + woodName + "_right"));
    }

    private static class ColorResolverWithBase implements ColorResolver
    {

        /**
         * This function interpolates a value using an exponential function, making values closer to 0 lower and values closer to 1 higher.
         *
         * @param value The value to be interpolated, between 0 and 1
         * @return The interpolated value
         */
        private static float interpolateExp(float value)
        {
            float base = (float) Math.sqrt(1.5);
            float max = 13.0269f;
            return ((float) Math.pow(base, value * max) - 1) / max;
        }

        private static int getFoliageColor(Biome biome, int baseColor)
        {
            return biome.getSpecialEffects().getFoliageColorOverride().orElseGet(() -> {
                double temperature = Mth.clamp(biome.climateSettings.temperature, 0.0F, 1.0F);
                double downfall = Mth.clamp(biome.climateSettings.downfall, 0.0F, 1.0F);
                float[] hsb = new float[3];
                Color.RGBtoHSB((baseColor >> 16) & 0xFF, (baseColor >> 8) & 0xFF, baseColor & 0xFF, hsb);

                float hue = hsb[0];
                float saturation = hsb[1];
                if (temperature < 0.6)
                {
                    // linear interpolation for cold climates
                    float factor = 1 - (float) temperature / 0.6f;
                    hue += factor * 0.04f;
                } else
                {
                    // exponential interpolation for warm climates
                    float factor = ((float) temperature - 0.6f) / 0.4f;
                    factor = interpolateExp(factor);
                    hue -= factor * 0.08f;
                    // if the downfall is over a certain threshold, mix the "jungle color" with the leaves color to varying degrees
                    float downfallFactor = ((float) downfall - 0.4f) / 0.6f;
                    if (downfallFactor > 0)
                    {
                        downfallFactor = interpolateExp(downfallFactor);
                        hue = downfallFactor * 0.28f + (1 - downfallFactor) * hue;
                        saturation = Math.max(saturation, downfallFactor * 0.85f + (1 - downfallFactor) * saturation);
                    }
                }
                return Color.HSBtoRGB(hue, saturation, hsb[2]);
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
