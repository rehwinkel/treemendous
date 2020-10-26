package deerangle.treemendous.world;

import com.google.common.collect.ImmutableList;
import deerangle.treemendous.main.Treemendous;
import deerangle.treemendous.tree.RegisteredTree;
import deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class BiomeMaker {

    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES,
            Treemendous.MODID);

    public static final RegistryObject<Biome> MIXED_MAPLE_FOREST = BIOMES.register("mixed_maple_forest", () -> {
        TreeWorldGenRegistry.registerFeatures();
        TreeRegistry.maple.registerFeature();
        TreeRegistry.red_maple.registerFeature();
        TreeRegistry.brown_maple.registerFeature();

        return new MultiTreeForestBiome(0.6f, false, false, false, ImmutableList
                .of(TreeRegistry.maple.getConfiguredFeature(), TreeRegistry.red_maple.getConfiguredFeature(),
                        TreeRegistry.brown_maple.getConfiguredFeature()), 8);
    });

    public static final RegistryObject<Biome> MIXED_FOREST = BIOMES.register("mixed_forest", () -> {
        TreeWorldGenRegistry.registerFeatures();
        List<ConfiguredFeature<?, ?>> features = new ArrayList<>();
        for (RegisteredTree tree : TreeRegistry.TREES) {
            tree.registerFeature();
            features.add(tree.getConfiguredFeature());
        }

        return new MultiTreeForestBiome(0.6f, false, false, false, features, 8);
    });

    public static final RegistryObject<Biome> MIXED_FOREST_VANILLA = BIOMES.register("mixed_forest_vanilla", () -> {
        TreeWorldGenRegistry.registerFeatures();
        List<ConfiguredFeature<?, ?>> features = new ArrayList<>();
        features.add(Feature.ACACIA_TREE.withConfiguration(DefaultBiomeFeatures.ACACIA_TREE_CONFIG));
        features.add(Feature.DARK_OAK_TREE.withConfiguration(DefaultBiomeFeatures.DARK_OAK_TREE_CONFIG));
        features.add(Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.BIRCH_TREE_CONFIG));
        features.add(Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.OAK_TREE_CONFIG));
        features.add(Feature.FANCY_TREE.withConfiguration(DefaultBiomeFeatures.FANCY_TREE_CONFIG));
        features.add(Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.SPRUCE_TREE_CONFIG));
        features.add(Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.PINE_TREE_CONFIG));
        features.add(Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.JUNGLE_TREE_CONFIG));
        features.add(Feature.MEGA_JUNGLE_TREE.withConfiguration(DefaultBiomeFeatures.MEGA_JUNGLE_TREE_CONFIG));
        for (RegisteredTree tree : TreeRegistry.TREES) {
            tree.registerFeature();
            features.add(tree.getConfiguredFeature());
        }

        return new MultiTreeForestBiome(0.6f, false, false, false, features, 8);
    });

    public static final RegistryObject<Biome> NEEDLE_FOREST = BIOMES.register("needle_forest", () -> {
        TreeWorldGenRegistry.registerFeatures();
        TreeRegistry.fir.registerFeature();
        TreeRegistry.douglas.registerFeature();
        TreeRegistry.larch.registerFeature();
        TreeRegistry.pine.registerFeature();
        TreeRegistry.cedar.registerFeature();
        TreeRegistry.juniper.registerFeature();

        return new MultiTreeForestBiome(0.4f, false, false, false, ImmutableList
                .of(TreeRegistry.fir.getConfiguredFeature(), TreeRegistry.douglas.getConfiguredFeature(),
                        TreeRegistry.larch.getConfiguredFeature(), TreeRegistry.pine.getConfiguredFeature(),
                        TreeRegistry.cedar.getConfiguredFeature(), TreeRegistry.juniper.getConfiguredFeature()), 8);
    });

    public static final RegistryObject<Biome> NEEDLE_FOREST_SNOW = BIOMES.register("needle_forest_snow", () -> {
        TreeWorldGenRegistry.registerFeatures();
        TreeRegistry.fir.registerFeature();
        TreeRegistry.douglas.registerFeature();
        TreeRegistry.larch.registerFeature();
        TreeRegistry.pine.registerFeature();
        TreeRegistry.cedar.registerFeature();
        TreeRegistry.juniper.registerFeature();

        return new MultiTreeForestBiome(0.4f, false, true, false, ImmutableList
                .of(TreeRegistry.fir.getConfiguredFeature(), TreeRegistry.douglas.getConfiguredFeature(),
                        TreeRegistry.larch.getConfiguredFeature(), TreeRegistry.pine.getConfiguredFeature(),
                        TreeRegistry.cedar.getConfiguredFeature(), TreeRegistry.juniper.getConfiguredFeature()), 8);
    });

    public static void addBiomesToOverworld() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(MIXED_MAPLE_FOREST.get(), 8));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(MIXED_FOREST.get(), 8));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(MIXED_FOREST_VANILLA.get(), 8));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(NEEDLE_FOREST.get(), 8));
        BiomeManager.addBiome(BiomeManager.BiomeType.ICY, new BiomeManager.BiomeEntry(NEEDLE_FOREST_SNOW.get(), 8));
        for (RegisteredTree tree : TreeRegistry.TREES) {
            for (RegistryObject<Biome> b : tree.getFrostyBiomes()) {
                BiomeManager.addBiome(BiomeManager.BiomeType.ICY, new BiomeManager.BiomeEntry(b.get(), 10));
            }
            for (RegistryObject<Biome> b : tree.getBiomes()) {
                BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(b.get(), 10));
            }
        }
    }

}
