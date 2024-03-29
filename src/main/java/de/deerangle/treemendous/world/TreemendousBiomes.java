package de.deerangle.treemendous.world;

import de.deerangle.treemendous.main.Treemendous;
import de.deerangle.treemendous.tree.Tree;
import de.deerangle.treemendous.tree.config.SaplingConfig;
import de.deerangle.treemendous.tree.util.WeightedTreeMaker;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Features;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.data.worldgen.SurfaceBuilders;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TreemendousBiomes
{

    private static final Map<String, ConfiguredFeature<?, ?>> forestOrTaigaTrees = new HashMap<>();
    private static final Map<String, ConfiguredFeature<?, ?>> savannaTrees = new HashMap<>();
    private static final Map<String, ConfiguredFeature<?, ?>> shatteredSavannaTrees = new HashMap<>();

    private static int calculateSkyColor(float temperature)
    {
        float f = Mth.clamp(temperature / 3.0F, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }

    private static Biome baseForestBiome(float depth, float scale, ConfiguredFeature<?, ?> treesFeature)
    {
        MobSpawnSettings.Builder mobSettings1 = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobSettings1);
        BiomeDefaultFeatures.commonSpawns(mobSettings1);
        MobSpawnSettings.Builder mobSettings = mobSettings1.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4)).setPlayerCanSpawn();
        BiomeGenerationSettings.Builder genSettings = (new BiomeGenerationSettings.Builder()).surfaceBuilder(SurfaceBuilders.GRASS);
        BiomeDefaultFeatures.addDefaultOverworldLandStructures(genSettings);
        genSettings.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);
        BiomeDefaultFeatures.addDefaultCarvers(genSettings);
        BiomeDefaultFeatures.addDefaultLakes(genSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(genSettings);
        BiomeDefaultFeatures.addForestFlowers(genSettings);

        BiomeDefaultFeatures.addDefaultUndergroundVariety(genSettings);
        BiomeDefaultFeatures.addDefaultOres(genSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(genSettings);
        genSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, treesFeature);
        BiomeDefaultFeatures.addDefaultFlowers(genSettings);
        BiomeDefaultFeatures.addForestGrass(genSettings);

        BiomeDefaultFeatures.addDefaultMushrooms(genSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(genSettings);
        BiomeDefaultFeatures.addDefaultSprings(genSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(genSettings);
        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.RAIN)
                .biomeCategory(Biome.BiomeCategory.FOREST)
                .depth(depth)
                .scale(scale)
                .temperature(0.7F)
                .downfall(0.8F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(12638463)
                        .skyColor(calculateSkyColor(0.7F))
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build()
                )
                .mobSpawnSettings(mobSettings.build())
                .generationSettings(genSettings.build())
                .build();
    }

    private static Biome baseTaigaBiome(float depth, float scale, boolean snowy, boolean mountain, boolean hasVillage, boolean hasIgloo, ConfiguredFeature<?, ?> treesFeature)
    {
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobSettings);
        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 4, 4)).addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3)).addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));
        if (!snowy && !mountain)
        {
            mobSettings.setPlayerCanSpawn();
        }

        BiomeDefaultFeatures.commonSpawns(mobSettings);
        float f = snowy ? -0.5F : 0.25F;
        BiomeGenerationSettings.Builder genSettings = (new BiomeGenerationSettings.Builder()).surfaceBuilder(SurfaceBuilders.GRASS);
        if (hasVillage)
        {
            genSettings.addStructureStart(StructureFeatures.VILLAGE_TAIGA);
            genSettings.addStructureStart(StructureFeatures.PILLAGER_OUTPOST);
        }

        if (hasIgloo)
        {
            genSettings.addStructureStart(StructureFeatures.IGLOO);
        }

        BiomeDefaultFeatures.addDefaultOverworldLandStructures(genSettings);
        genSettings.addStructureStart(mountain ? StructureFeatures.RUINED_PORTAL_MOUNTAIN : StructureFeatures.RUINED_PORTAL_STANDARD);
        BiomeDefaultFeatures.addDefaultCarvers(genSettings);
        BiomeDefaultFeatures.addDefaultLakes(genSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(genSettings);
        BiomeDefaultFeatures.addFerns(genSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genSettings);
        BiomeDefaultFeatures.addDefaultOres(genSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(genSettings);
        genSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, treesFeature);
        BiomeDefaultFeatures.addDefaultFlowers(genSettings);
        BiomeDefaultFeatures.addTaigaGrass(genSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(genSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(genSettings);
        BiomeDefaultFeatures.addDefaultSprings(genSettings);
        if (snowy)
        {
            BiomeDefaultFeatures.addBerryBushes(genSettings);
        } else
        {
            BiomeDefaultFeatures.addSparseBerryBushes(genSettings);
        }

        BiomeDefaultFeatures.addSurfaceFreezing(genSettings);
        return (new Biome.BiomeBuilder())
                .precipitation(snowy ? Biome.Precipitation.SNOW : Biome.Precipitation.RAIN)
                .biomeCategory(Biome.BiomeCategory.TAIGA)
                .depth(depth)
                .scale(scale)
                .temperature(f)
                .downfall(snowy ? 0.4F : 0.8F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(snowy ? 4020182 : 4159204)
                        .waterFogColor(329011)
                        .fogColor(12638463)
                        .skyColor(calculateSkyColor(f))
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build()
                )
                .mobSpawnSettings(mobSettings.build())
                .generationSettings(genSettings.build())
                .build();
    }

    private static Biome baseSavannaBiome(float depth, float scale, float temperature, boolean mountain, boolean shattered, boolean addLlama, ConfiguredFeature<?, ?> treesFeature)
    {
        MobSpawnSettings.Builder mobSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobSettings);
        mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.HORSE, 1, 2, 6)).addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.DONKEY, 1, 1, 1));
        BiomeDefaultFeatures.commonSpawns(mobSettings);
        if (addLlama)
        {
            mobSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.LLAMA, 8, 4, 4));
        }
        BiomeGenerationSettings.Builder genSettings = (new BiomeGenerationSettings.Builder()).surfaceBuilder(shattered ? SurfaceBuilders.SHATTERED_SAVANNA : SurfaceBuilders.GRASS);
        if (!mountain && !shattered)
        {
            genSettings.addStructureStart(StructureFeatures.VILLAGE_SAVANNA).addStructureStart(StructureFeatures.PILLAGER_OUTPOST);
        }

        BiomeDefaultFeatures.addDefaultOverworldLandStructures(genSettings);
        genSettings.addStructureStart(mountain ? StructureFeatures.RUINED_PORTAL_MOUNTAIN : StructureFeatures.RUINED_PORTAL_STANDARD);
        BiomeDefaultFeatures.addDefaultCarvers(genSettings);
        BiomeDefaultFeatures.addDefaultLakes(genSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(genSettings);
        if (!shattered)
        {
            BiomeDefaultFeatures.addSavannaGrass(genSettings);
        }

        BiomeDefaultFeatures.addDefaultUndergroundVariety(genSettings);
        BiomeDefaultFeatures.addDefaultOres(genSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(genSettings);
        genSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, treesFeature);
        if (shattered)
        {
            BiomeDefaultFeatures.addDefaultFlowers(genSettings);
            BiomeDefaultFeatures.addShatteredSavannaGrass(genSettings);
        } else
        {
            BiomeDefaultFeatures.addWarmFlowers(genSettings);
            BiomeDefaultFeatures.addSavannaExtraGrass(genSettings);
        }

        BiomeDefaultFeatures.addDefaultMushrooms(genSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(genSettings);
        BiomeDefaultFeatures.addDefaultSprings(genSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(genSettings);
        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.NONE)
                .biomeCategory(Biome.BiomeCategory.SAVANNA)
                .depth(depth)
                .scale(scale)
                .temperature(temperature)
                .downfall(0.0F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(12638463)
                        .skyColor(calculateSkyColor(temperature))
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build()
                )
                .mobSpawnSettings(mobSettings.build())
                .generationSettings(genSettings.build())
                .build();
    }

    public static Biome taigaBiome(Supplier<ConfiguredFeature<?, ?>> treesFeatureSupplier)
    {
        ConfiguredFeature<?, ?> taigaTrees = treesFeatureSupplier.get();
        return baseTaigaBiome(0.2F, 0.2F, false, false, true, false, taigaTrees);
    }

    public static Biome taigaHillsBiome(Supplier<ConfiguredFeature<?, ?>> treesFeatureSupplier)
    {
        ConfiguredFeature<?, ?> taigaTrees = treesFeatureSupplier.get();
        return baseTaigaBiome(0.45F, 0.3F, false, false, false, false, taigaTrees);
    }

    public static Biome taigaMountainsBiome(Supplier<ConfiguredFeature<?, ?>> treesFeatureSupplier)
    {
        ConfiguredFeature<?, ?> taigaTrees = treesFeatureSupplier.get();
        return baseTaigaBiome(0.3F, 0.4F, false, true, false, false, taigaTrees);
    }

    public static Biome snowyTaigaBiome(Supplier<ConfiguredFeature<?, ?>> treesFeatureSupplier)
    {
        ConfiguredFeature<?, ?> taigaTrees = treesFeatureSupplier.get();
        return baseTaigaBiome(0.2F, 0.2F, true, false, false, true, taigaTrees);
    }

    public static Biome snowyTaigaHillsBiome(Supplier<ConfiguredFeature<?, ?>> treesFeatureSupplier)
    {
        ConfiguredFeature<?, ?> taigaTrees = treesFeatureSupplier.get();
        return baseTaigaBiome(0.45F, 0.3F, true, false, false, false, taigaTrees);
    }

    public static Biome snowyTaigaMountainsBiome(Supplier<ConfiguredFeature<?, ?>> treesFeatureSupplier)
    {
        ConfiguredFeature<?, ?> taigaTrees = treesFeatureSupplier.get();
        return baseTaigaBiome(0.3F, 0.4F, true, true, false, false, taigaTrees);
    }

    public static Biome forestHillsBiome(Supplier<ConfiguredFeature<?, ?>> treesFeatureSupplier)
    {
        ConfiguredFeature<?, ?> forestTrees = treesFeatureSupplier.get();
        return baseForestBiome(0.45F, 0.3F, forestTrees);
    }

    public static Biome forestBiome(Supplier<ConfiguredFeature<?, ?>> treesFeatureSupplier)
    {
        ConfiguredFeature<?, ?> forestTrees = treesFeatureSupplier.get();
        return baseForestBiome(0.1F, 0.2F, forestTrees);
    }

    public static Biome savannaBiome(Supplier<ConfiguredFeature<?, ?>> treesFeatureSupplier)
    {
        ConfiguredFeature<?, ?> savannaTrees = treesFeatureSupplier.get();
        return baseSavannaBiome(0.125F, 0.05F, 1.2F, false, false, false, savannaTrees);
    }

    public static Biome savannaPlateauBiome(Supplier<ConfiguredFeature<?, ?>> treesFeatureSupplier)
    {
        ConfiguredFeature<?, ?> savannaTrees = treesFeatureSupplier.get();
        return baseSavannaBiome(1.5F, 0.025F, 1.0F, true, false, true, savannaTrees);
    }

    public static Biome shatteredSavannaBiome(Supplier<ConfiguredFeature<?, ?>> treesFeatureSupplier)
    {
        ConfiguredFeature<?, ?> shatteredTrees = treesFeatureSupplier.get();
        return baseSavannaBiome(0.3625F, 1.225F, 1.1F, true, true, false, shatteredTrees);
    }

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> registerConfiguredFeature(String name, ConfiguredFeature<FC, ?> feature)
    {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Treemendous.MOD_ID, name), feature);
    }

    public static ConfiguredFeature<?, ?> getForestOrTaigaTrees(Tree tree, String sapling, int density)
    {
        String name = sapling == null ? tree.getRegistryName() : tree.getRegistryName() + "_" + sapling;
        ConfiguredFeature<?, ?> feature = forestOrTaigaTrees.get(name);
        if (feature == null)
        {
            String name1 = (sapling == null ? tree.getRegistryName() : tree.getRegistryName() + "_" + sapling) + "_trees";
            ConfiguredFeature<?, ?> newFeature = registerConfiguredFeature(name1, getTreesFeature(tree, sapling, maker -> !maker.mega())
                    .decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(density, 0.1F, 1)))
            );
            forestOrTaigaTrees.put(name, newFeature);
            feature = newFeature;
        }
        return feature;
    }

    public static ConfiguredFeature<?, ?> getSavannaTrees(Tree tree, String sapling, int density)
    {
        String name = sapling == null ? tree.getRegistryName() : tree.getRegistryName() + "_" + sapling;
        ConfiguredFeature<?, ?> feature = savannaTrees.get(name);
        if (feature == null)
        {
            String name1 = (sapling == null ? tree.getRegistryName() : tree.getRegistryName() + "_" + sapling) + "_savanna_trees";
            ConfiguredFeature<?, ?> newFeature = registerConfiguredFeature(name1, getTreesFeature(tree, sapling, maker -> !maker.mega())
                    .decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(density, 0.1F, 1)))
            );
            savannaTrees.put(name, newFeature);
            feature = newFeature;
        }
        return feature;
    }

    public static ConfiguredFeature<?, ?> getShatteredSavannaTrees(Tree tree, String sapling, int density)
    {
        String name = sapling == null ? tree.getRegistryName() : tree.getRegistryName() + "_" + sapling;
        ConfiguredFeature<?, ?> feature = shatteredSavannaTrees.get(name);
        if (feature == null)
        {
            String name1 = (sapling == null ? tree.getRegistryName() : tree.getRegistryName() + "_" + sapling) + "_shattered_savanna_trees";
            ConfiguredFeature<?, ?> newFeature = registerConfiguredFeature(name1, getTreesFeature(tree, sapling, maker -> !maker.mega())
                    .decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(density, 0.1F, 1)))
            );
            shatteredSavannaTrees.put(name, newFeature);
            feature = newFeature;
        }
        return feature;
    }

    private static ConfiguredFeature<?, ?> getTreesFeature(Tree tree, String saplingName, Predicate<WeightedTreeMaker> use)
    {
        SaplingConfig saplingConfig = tree.getSaplingConfig(saplingName);
        List<WeightedTreeMaker> treeMakers = saplingConfig.getTreeMakers();
        List<Supplier<ConfiguredFeature<?, ?>>> configuredTrees = new ArrayList<>();
        for (WeightedTreeMaker treeMaker : treeMakers)
        {
            if (use.test(treeMaker))
            {
                ConfiguredFeature<?, ?> configuredTree = saplingConfig.makeTree(treeMaker.treeMaker(), tree);
                Supplier<ConfiguredFeature<?, ?>> treeSupplier = () -> configuredTree;
                for (int i = 0; i < treeMaker.weight(); i++)
                {
                    configuredTrees.add(treeSupplier);
                }
            }
        }
        return Feature.SIMPLE_RANDOM_SELECTOR
                .configured(new SimpleRandomFeatureConfiguration(configuredTrees))
                .decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED);
    }

    public static Collection<RegisteredBiome> registerBiomes(DeferredRegister<Biome> biomes, Tree tree, String saplingName, BiomeKind kind, int density)
    {
        List<RegisteredBiome> biomeList = new ArrayList<>();
        String treeName = saplingName != null ? String.format("%s_%s", tree.getRegistryName(), saplingName) : tree.getRegistryName();
        switch (kind)
        {
            case TAIGA -> {
                Supplier<ConfiguredFeature<?, ?>> treesFeature = () -> getForestOrTaigaTrees(tree, saplingName, density);
                biomeList.add(new RegisteredBiome(tree, treeName, biomes.register(treeName + "_taiga", () -> taigaBiome(treesFeature)), BiomeManager.BiomeType.COOL));
                biomeList.add(new RegisteredBiome(tree, treeName, biomes.register(treeName + "_taiga_hills", () -> taigaHillsBiome(treesFeature)), BiomeManager.BiomeType.COOL));
                biomeList.add(new RegisteredBiome(tree, treeName, biomes.register(treeName + "_taiga_mountains", () -> taigaMountainsBiome(treesFeature)), BiomeManager.BiomeType.COOL));
                biomeList.add(new RegisteredBiome(tree, treeName, biomes.register(treeName + "_snowy_taiga", () -> snowyTaigaBiome(treesFeature)), BiomeManager.BiomeType.ICY));
                biomeList.add(new RegisteredBiome(tree, treeName, biomes.register(treeName + "_snowy_taiga_hills", () -> snowyTaigaHillsBiome(treesFeature)), BiomeManager.BiomeType.ICY));
                biomeList.add(new RegisteredBiome(tree, treeName, biomes.register(treeName + "_snowy_taiga_mountains", () -> snowyTaigaMountainsBiome(treesFeature)), BiomeManager.BiomeType.ICY));
            }
            case SAVANNA -> {
                Supplier<ConfiguredFeature<?, ?>> treesFeature = () -> getSavannaTrees(tree, saplingName, density);
                Supplier<ConfiguredFeature<?, ?>> shatteredTreesFeature = () -> getShatteredSavannaTrees(tree, saplingName, density / 2);
                biomeList.add(new RegisteredBiome(tree, treeName, biomes.register(treeName + "_savanna", () -> savannaBiome(treesFeature)), BiomeManager.BiomeType.DESERT));
                biomeList.add(new RegisteredBiome(tree, treeName, biomes.register(treeName + "_savanna_plateau", () -> savannaPlateauBiome(treesFeature)), BiomeManager.BiomeType.DESERT));
                biomeList.add(new RegisteredBiome(tree, treeName, biomes.register(treeName + "_shattered_savanna", () -> shatteredSavannaBiome(shatteredTreesFeature)), BiomeManager.BiomeType.DESERT));
            }
            case FOREST -> {
                Supplier<ConfiguredFeature<?, ?>> treesFeature = () -> getForestOrTaigaTrees(tree, saplingName, density);
                biomeList.add(new RegisteredBiome(tree, treeName, biomes.register(treeName + "_forest", () -> forestBiome(treesFeature)), BiomeManager.BiomeType.WARM));
                biomeList.add(new RegisteredBiome(tree, treeName, biomes.register(treeName + "_forest_hills", () -> forestHillsBiome(treesFeature)), BiomeManager.BiomeType.WARM));
            }
        }
        return biomeList;
    }

    //TODO: mega taiga

    public enum BiomeKind
    {
        TAIGA,
        SAVANNA,
        FOREST
    }

    public enum BiomeTerrain
    {
        FLAT,
        HILLS,
        MOUNTAINS
    }

    public static class RegisteredBiome
    {

        private final Tree tree;
        private final String treeName;
        private final ResourceKey<Biome> biomeKey;
        private final BiomeManager.BiomeType biomeType;

        public RegisteredBiome(Tree tree, String treeName, RegistryObject<Biome> biome, BiomeManager.BiomeType biomeType)
        {
            this.tree = tree;
            this.treeName = treeName;
            this.biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, biome.getId());
            this.biomeType = biomeType;
        }

        public Tree getTree()
        {
            return tree;
        }

        public String getTreeName()
        {
            return treeName;
        }

        public BiomeManager.BiomeType getBiomeType()
        {
            return biomeType;
        }

        public ResourceKey<Biome> getBiomeKey()
        {
            return biomeKey;
        }

    }

}
