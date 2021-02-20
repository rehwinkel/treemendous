package deerangle.treemendous.config;

import deerangle.treemendous.tree.RegisteredTree;
import deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.HashMap;
import java.util.Map;

public class TreemendousConfig {
    public static BiomeSpawnRate biomeSpawnRateConfig;
    public static final ForgeConfigSpec CONFIG_SPEC = makeConfigSpec();

    private static ForgeConfigSpec makeConfigSpec() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        biomeSpawnRateConfig = new BiomeSpawnRate(builder);
        return builder.build();
    }

    public static class BiomeSpawnRate {
        public final ForgeConfigSpec.ConfigValue<Integer> mixedMapleForestWeight;
        public final ForgeConfigSpec.ConfigValue<Integer> mixedForestWeight;
        public final ForgeConfigSpec.ConfigValue<Integer> mixedForestVanillaWeight;
        public final ForgeConfigSpec.ConfigValue<Integer> needleForestWeight;
        public final ForgeConfigSpec.ConfigValue<Integer> needleForestSnowWeight;
        public final Map<RegistryKey<Biome>, ForgeConfigSpec.ConfigValue<Integer>> treeWeights;

        public BiomeSpawnRate(ForgeConfigSpec.Builder builder) {
            builder.push("biomes");
            treeWeights = new HashMap<>();
            mixedMapleForestWeight = builder.comment("Spawn weight of mixed maple forest biome (higher number means more frequent)").define("mixed_maple_forest_weight", 6);
            mixedForestWeight = builder.comment("Spawn weight of mixed forest biome (higher number means more frequent)").define("mixed_forest_weight", 6);
            mixedForestVanillaWeight = builder.comment("Spawn weight of mixed forest biome with vanilla trees (higher number means more frequent)").define("mixed_forest_vanilla_weight", 6);
            needleForestWeight = builder.comment("Spawn weight of needle forest biome (higher number means more frequent)").define("needle_forest_weight", 6);
            needleForestSnowWeight = builder.comment("Spawn weight of needle forest biome with snow (higher number means more frequent)").define("needle_forest_snow_weight", 6);
            builder.push("single_tree");
            for (RegisteredTree tree : TreeRegistry.TREES) {
                for (RegistryKey<Biome> b : tree.getFrostyBiomes()) {
                    treeWeights.put(b, builder.comment("Spawn weight of " + tree.getEnglishName() + " (" + b.getLocation() + ") biome with snow (higher number means more frequent)").define(b.getLocation().getPath() + "_weight", 8));
                }
                for (RegistryKey<Biome> b : tree.getBiomes()) {
                    treeWeights.put(b, builder.comment("Spawn weight of " + tree.getEnglishName() + " (" + b.getLocation() + ") biome (higher number means more frequent)").define(b.getLocation().getPath() + "_weight", 8));
                }
            }
            builder.pop();
            builder.pop();
        }
    }
}
