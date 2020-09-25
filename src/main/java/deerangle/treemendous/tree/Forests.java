package deerangle.treemendous.tree;

import com.google.common.collect.ImmutableList;
import deerangle.treemendous.main.TreeRegistry;
import deerangle.treemendous.main.Treemendous;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Forests {

    static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> registerConfiguredFeature(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry
                .register(WorldGenRegistries.CONFIGURED_FEATURE, Treemendous.MODID + ":" + key, configuredFeature);
    }

    static Biome makeForestBiome(float depth, float scale, float temperature, boolean snowy, boolean dry, MobSpawnInfo.Builder mobSpawnBuilder, ConfiguredFeature<?, ?> tree) {
        BiomeGenerationSettings.Builder genSettings = new BiomeGenerationSettings.Builder()
                .withSurfaceBuilder(ConfiguredSurfaceBuilders.field_244178_j);
        DefaultBiomeFeatures.withStrongholdAndMineshaft(genSettings);
        genSettings.withStructure(StructureFeatures.field_244159_y);
        DefaultBiomeFeatures.withCavesAndCanyons(genSettings);
        DefaultBiomeFeatures.withLavaAndWaterLakes(genSettings);
        DefaultBiomeFeatures.withMonsterRoom(genSettings);
        DefaultBiomeFeatures.withDisks(genSettings);
        DefaultBiomeFeatures.withAllForestFlowerGeneration(genSettings);
        DefaultBiomeFeatures.withDefaultFlowers(genSettings);
        DefaultBiomeFeatures.withForestGrass(genSettings);
        DefaultBiomeFeatures.withCommonOverworldBlocks(genSettings);
        DefaultBiomeFeatures.withOverworldOres(genSettings);
        DefaultBiomeFeatures.withNormalMushroomGeneration(genSettings);
        DefaultBiomeFeatures.withSugarCaneAndPumpkins(genSettings);
        DefaultBiomeFeatures.withLavaAndWaterSprings(genSettings);
        DefaultBiomeFeatures.withFrozenTopLayer(genSettings);
        genSettings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, tree);

        return new Biome.Builder()
                .precipitation(snowy ? Biome.RainType.SNOW : (dry ? Biome.RainType.NONE : Biome.RainType.RAIN))
                .category(Biome.Category.FOREST).depth(depth).scale(scale).temperature(snowy ? -0.5F : temperature)
                .downfall(snowy ? 0.4F : (dry ? 0.0F : 0.8F)).setEffects(
                        (new BiomeAmbience.Builder()).setWaterColor(snowy ? 4020182 : 4159204).setWaterFogColor(329011)
                                .setFogColor(12638463).withSkyColor(MathHelper.hsvToRGB(0.6105556f, 0.5233333f, 1.0F))
                                .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build())
                .withMobSpawnSettings(mobSpawnBuilder.copy()).withGenerationSettings(genSettings.build()).build();
    }

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister
            .create(ForgeRegistries.BIOMES, Treemendous.MODID);

    static {
        BIOMES.register("mixed_maple_forest", () -> {
            TreeRegistry.maple.registerFeature();
            TreeRegistry.red_maple.registerFeature();
            TreeRegistry.brown_maple.registerFeature();

            ConfiguredFeature<?, ?> treesFeature = registerConfiguredFeature("trees_mixed_maple",
                    Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList
                            .of(TreeRegistry.red_maple.getFeature().withChance(0.3F),
                                    TreeRegistry.brown_maple.getFeature().withChance(0.3F)),
                            TreeRegistry.maple.getFeature())).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                            .withPlacement(
                                    Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(10, 0.1F, 2))));
            return makeForestBiome(0.2f, 0.4f, 0.6f, false, false, new MobSpawnInfo.Builder(), treesFeature);
        });

        BIOMES.register("mixed_forest", () -> {
            for (RegisteredTree tree : TreeRegistry.trees) {
                tree.registerFeature();
            }

            ConfiguredFeature<?, ?> treesFeature = registerConfiguredFeature("trees_mixed",
                    Feature.SIMPLE_RANDOM_SELECTOR.withConfiguration(new SingleRandomFeature(TreeRegistry.trees.stream()
                            .map(tree -> (Supplier<ConfiguredFeature<?, ?>>) tree::getFeature)
                            .collect(Collectors.toList()))).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                            .withPlacement(
                                    Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(10, 0.1F, 2))));
            return makeForestBiome(0.2f, 0.4f, 0.6f, false, false, new MobSpawnInfo.Builder(), treesFeature);
        });

        BIOMES.register("mixed_forest_vanilla", () -> {
            for (RegisteredTree tree : TreeRegistry.trees) {
                tree.registerFeature();
            }

            ConfiguredFeature<?, ?> treesFeature = registerConfiguredFeature("trees_mixed_vanilla",
                    Feature.SIMPLE_RANDOM_SELECTOR.withConfiguration(new SingleRandomFeature(Stream.concat(
                            TreeRegistry.trees.stream()
                                    .map(tree -> (Supplier<ConfiguredFeature<?, ?>>) tree::getFeature), ImmutableList
                                    .of(Features.OAK, Features.SPRUCE, Features.BIRCH, Features.JUNGLE_TREE,
                                            Features.ACACIA, Features.DARK_OAK).stream().map(tree -> () -> tree))
                            .collect(Collectors.toList()))).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                            .withPlacement(
                                    Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(10, 0.1F, 2))));
            return makeForestBiome(0.2f, 0.4f, 0.6f, false, false, new MobSpawnInfo.Builder(), treesFeature);
        });
    }

}
