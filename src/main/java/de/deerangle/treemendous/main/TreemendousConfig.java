package de.deerangle.treemendous.main;

import com.google.common.collect.ImmutableList;
import de.deerangle.treemendous.tree.Tree;
import de.deerangle.treemendous.world.TreemendousBiomes;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TreemendousConfig
{

    public static final Server SERVER;
    static final ForgeConfigSpec serverSpec;

    static
    {
        final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Server::new);
        serverSpec = specPair.getRight();
        SERVER = specPair.getLeft();
    }

    public static int getSpawnWeightForBiome(String biomeName)
    {
        return SERVER.weightConfigs.get(biomeName).get();
    }

    public static BiomeDictionary.Type[] getDictionaryTypesForBiome(String biomeName)
    {
        List<?> typeNames = SERVER.typesConfigs.get(biomeName).get();
        return typeNames.stream().map(name -> BiomeDictionary.Type.getType((String) name)).toArray(BiomeDictionary.Type[]::new);
    }

    public static int getTreeDensity(String treeName)
    {
        return SERVER.densityConfigs.get(treeName).get();
    }

    private static class Server
    {
        private static final String ALL_DICT_TYPE_NAMES = BiomeDictionary.Type.getAll().stream().map(BiomeDictionary.Type::getName).collect(Collectors.joining(", "));
        private final Map<String, ForgeConfigSpec.IntValue> weightConfigs = new HashMap<>();
        private final Map<String, ForgeConfigSpec.IntValue> densityConfigs = new HashMap<>();
        private final Map<String, ForgeConfigSpec.ConfigValue<List<?>>> typesConfigs = new HashMap<>();

        private Server(ForgeConfigSpec.Builder builder)
        {
            builder.comment("Biome spawn rate settings")
                    .push("trees");

            makeConfigForTree(builder, TreeRegistry.DOUGLAS_TREE, TreemendousBiomes.BiomeKind.TAIGA, 10);
            makeConfigForTree(builder, TreeRegistry.PINE_TREE, TreemendousBiomes.BiomeKind.TAIGA, 9);
            makeConfigForTree(builder, TreeRegistry.LARCH_TREE, TreemendousBiomes.BiomeKind.TAIGA, 10);
            makeConfigForTree(builder, TreeRegistry.FIR_TREE, TreemendousBiomes.BiomeKind.TAIGA, 10);
            makeConfigForTree(builder, TreeRegistry.MAPLE_TREE, TreemendousBiomes.BiomeKind.FOREST, 8);
            makeConfigForTree(builder, TreeRegistry.MAPLE_TREE, "red", TreemendousBiomes.BiomeKind.FOREST, 8);
            makeConfigForTree(builder, TreeRegistry.MAPLE_TREE, "brown", TreemendousBiomes.BiomeKind.FOREST, 8);
            makeConfigForTree(builder, TreeRegistry.JAPANESE_TREE, TreemendousBiomes.BiomeKind.FOREST, 10);
            makeConfigForTree(builder, TreeRegistry.BEECH_TREE, TreemendousBiomes.BiomeKind.FOREST, 8);
            makeConfigForTree(builder, TreeRegistry.CHERRY_TREE, TreemendousBiomes.BiomeKind.FOREST, 6);
            makeConfigForTree(builder, TreeRegistry.ALDER_TREE, TreemendousBiomes.BiomeKind.FOREST, 8);
            makeConfigForTree(builder, TreeRegistry.CHESTNUT_TREE, TreemendousBiomes.BiomeKind.FOREST, 8);
            makeConfigForTree(builder, TreeRegistry.PLANE_TREE, TreemendousBiomes.BiomeKind.FOREST, 5);
            makeConfigForTree(builder, TreeRegistry.ASH_TREE, TreemendousBiomes.BiomeKind.FOREST, 8);
            makeConfigForTree(builder, TreeRegistry.LINDEN_TREE, TreemendousBiomes.BiomeKind.FOREST, 8);
            makeConfigForTree(builder, TreeRegistry.ROBINIA_TREE, TreemendousBiomes.BiomeKind.SAVANNA, 2);
            makeConfigForTree(builder, TreeRegistry.WILLOW_TREE, TreemendousBiomes.BiomeKind.FOREST, 7);
            makeConfigForTree(builder, TreeRegistry.POMEGRANATE_TREE, TreemendousBiomes.BiomeKind.SAVANNA, 2);
            makeConfigForTree(builder, TreeRegistry.WALNUT_TREE, TreemendousBiomes.BiomeKind.FOREST, 4);
            makeConfigForTree(builder, TreeRegistry.CEDAR_TREE, TreemendousBiomes.BiomeKind.TAIGA, 7);
            makeConfigForTree(builder, TreeRegistry.POPLAR_TREE, TreemendousBiomes.BiomeKind.FOREST, 7);
            makeConfigForTree(builder, TreeRegistry.ELM_TREE, TreemendousBiomes.BiomeKind.FOREST, 5);
            makeConfigForTree(builder, TreeRegistry.JUNIPER_TREE, TreemendousBiomes.BiomeKind.TAIGA, 5);
            makeConfigForTree(builder, TreeRegistry.MAGNOLIA_TREE, TreemendousBiomes.BiomeKind.FOREST, 4);
            makeConfigForTree(builder, TreeRegistry.RAINBOW_EUCALYPTUS_TREE, TreemendousBiomes.BiomeKind.FOREST, 5);

            builder.pop();
        }

        private void makeConfigForTree(ForgeConfigSpec.Builder builder, Tree tree, TreemendousBiomes.BiomeKind biomeKind, int density)
        {
            this.makeConfigForTree(builder, tree, null, biomeKind, density);
        }

        private void makeConfigForTree(ForgeConfigSpec.Builder builder, Tree tree, String saplingName, TreemendousBiomes.BiomeKind biomeKind, int density)
        {
            String treeName = saplingName != null ? String.format("%s_%s", tree.getRegistryName(), saplingName) : tree.getRegistryName();
            builder.comment("This section defined the world generation for this tree").push(treeName);
            ForgeConfigSpec.IntValue densityConfig = builder.comment("The amount of trees per chunk").defineInRange("density", density, 0, 16);
            this.densityConfigs.put(treeName, densityConfig);

            switch (biomeKind)
            {
                case TAIGA -> {
                    makeConfigForBiome(builder, tree, treeName + "_taiga", BiomeManager.BiomeType.COOL, TreemendousBiomes.BiomeTerrain.FLAT);
                    makeConfigForBiome(builder, tree, treeName + "_taiga_hills", BiomeManager.BiomeType.COOL, TreemendousBiomes.BiomeTerrain.HILLS);
                    makeConfigForBiome(builder, tree, treeName + "_taiga_mountains", BiomeManager.BiomeType.COOL, TreemendousBiomes.BiomeTerrain.MOUNTAINS);
                    makeConfigForBiome(builder, tree, treeName + "_snowy_taiga", BiomeManager.BiomeType.ICY, TreemendousBiomes.BiomeTerrain.FLAT);
                    makeConfigForBiome(builder, tree, treeName + "_snowy_taiga_hills", BiomeManager.BiomeType.ICY, TreemendousBiomes.BiomeTerrain.HILLS);
                    makeConfigForBiome(builder, tree, treeName + "_snowy_taiga_mountains", BiomeManager.BiomeType.ICY, TreemendousBiomes.BiomeTerrain.MOUNTAINS);
                }
                case SAVANNA -> {
                    makeConfigForBiome(builder, tree, treeName + "_savanna", BiomeManager.BiomeType.DESERT, TreemendousBiomes.BiomeTerrain.FLAT);
                    makeConfigForBiome(builder, tree, treeName + "_savanna_plateau", BiomeManager.BiomeType.DESERT, TreemendousBiomes.BiomeTerrain.FLAT);
                    makeConfigForBiome(builder, tree, treeName + "_shattered_savanna", BiomeManager.BiomeType.DESERT, TreemendousBiomes.BiomeTerrain.MOUNTAINS);
                }
                case FOREST -> {
                    makeConfigForBiome(builder, tree, treeName + "_forest", BiomeManager.BiomeType.WARM, TreemendousBiomes.BiomeTerrain.FLAT);
                    makeConfigForBiome(builder, tree, treeName + "_forest_hills", BiomeManager.BiomeType.WARM, TreemendousBiomes.BiomeTerrain.HILLS);
                }
            }
            builder.pop();
        }

        private void makeConfigForBiome(ForgeConfigSpec.Builder builder, Tree tree, String biomeName, BiomeManager.BiomeType biomeType, TreemendousBiomes.BiomeTerrain biomeTerrain)
        {
            builder.comment("Config for this biome").push(biomeName);
            ForgeConfigSpec.IntValue weightConfig = builder.comment("How much this biome spawns, plains biome has weight 10. 0 disables spawning.").defineInRange("spawn_weight", 4, 0, 50);
            List<String> types = getDefaultBiomeTypes(biomeType, biomeTerrain).stream().map(BiomeDictionary.Type::getName).toList();
            ForgeConfigSpec.ConfigValue<List<?>> typesConfig = builder.comment("Biome dictionary types, must be one of: " + ALL_DICT_TYPE_NAMES).defineList("biome_types", types, name -> name instanceof String && BiomeDictionary.Type.hasType((String) name));
            this.weightConfigs.put(biomeName, weightConfig);
            this.typesConfigs.put(biomeName, typesConfig);
            builder.pop();
        }

        private static List<BiomeDictionary.Type> getDefaultBiomeTypes(BiomeManager.BiomeType type, TreemendousBiomes.BiomeTerrain terrain)
        {
            ImmutableList.Builder<BiomeDictionary.Type> biomeTypes = new ImmutableList.Builder<>();
            biomeTypes.add(BiomeDictionary.Type.OVERWORLD);
            switch (terrain)
            {
                case FLAT -> {
                    if (type == BiomeManager.BiomeType.ICY)
                    {
                        biomeTypes.add(BiomeDictionary.Type.SNOWY);
                    } else if (type == BiomeManager.BiomeType.COOL)
                    {
                        biomeTypes.add(BiomeDictionary.Type.FOREST);
                    }
                }
                case HILLS -> biomeTypes.add(BiomeDictionary.Type.HILLS);
                case MOUNTAINS -> biomeTypes.add(BiomeDictionary.Type.MOUNTAIN);
            }
            if (type == BiomeManager.BiomeType.ICY)
            {
                biomeTypes.add(BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.COLD);
            } else if (type == BiomeManager.BiomeType.DESERT)
            {
                biomeTypes.add(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.HOT, BiomeDictionary.Type.SPARSE);
            } else if (type == BiomeManager.BiomeType.COOL)
            {
                biomeTypes.add(BiomeDictionary.Type.CONIFEROUS);
            } else if (type == BiomeManager.BiomeType.WARM)
            {
                biomeTypes.add(BiomeDictionary.Type.FOREST);
            }
            return biomeTypes.build();
        }
    }

}
