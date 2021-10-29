package de.deerangle.treemendous.world;

import com.google.common.collect.ImmutableList;
import de.deerangle.treemendous.main.CommonHandler;
import de.deerangle.treemendous.main.ExtraRegistry;
import de.deerangle.treemendous.main.Treemendous;
import de.deerangle.treemendous.tree.RegisteredTree;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreemendousConfiguredFeatures
{

    private static final Map<ResourceLocation, ConfiguredStructureFeature<?, ?>> BIOME_STRUCTURE_MAP = new HashMap<>();

    public static void registerConfiguredStructures()
    {
        register("douglas_ranger_house", CommonHandler.DOUGLAS_TREE, TreemendousBiomes.BiomeKind.TAIGA);
        register("pine_ranger_house", CommonHandler.PINE_TREE, TreemendousBiomes.BiomeKind.TAIGA);
        register("larch_ranger_house", CommonHandler.LARCH_TREE, TreemendousBiomes.BiomeKind.TAIGA);
        register("fir_ranger_house", CommonHandler.FIR_TREE, TreemendousBiomes.BiomeKind.TAIGA);
        register("maple_ranger_house", CommonHandler.MAPLE_TREE, TreemendousBiomes.BiomeKind.FOREST);
        register("japanese_ranger_house", CommonHandler.JAPANESE_TREE, TreemendousBiomes.BiomeKind.FOREST);
        register("beech_ranger_house", CommonHandler.BEECH_TREE, TreemendousBiomes.BiomeKind.FOREST);
        register("cherry_ranger_house", CommonHandler.CHERRY_TREE, TreemendousBiomes.BiomeKind.FOREST);
        register("alder_ranger_house", CommonHandler.ALDER_TREE, TreemendousBiomes.BiomeKind.FOREST);
        register("chestnut_ranger_house", CommonHandler.CHESTNUT_TREE, TreemendousBiomes.BiomeKind.FOREST);
        register("plane_ranger_house", CommonHandler.PLANE_TREE, TreemendousBiomes.BiomeKind.FOREST);
        register("ash_ranger_house", CommonHandler.ASH_TREE, TreemendousBiomes.BiomeKind.FOREST);
        register("linden_ranger_house", CommonHandler.LINDEN_TREE, TreemendousBiomes.BiomeKind.FOREST);
        register("robinia_ranger_house", CommonHandler.ROBINIA_TREE, TreemendousBiomes.BiomeKind.SAVANNA);
        register("willow_ranger_house", CommonHandler.WILLOW_TREE, TreemendousBiomes.BiomeKind.FOREST);
        register("pomegranate_ranger_house", CommonHandler.POMEGRANATE_TREE, TreemendousBiomes.BiomeKind.SAVANNA);
        register("walnut_ranger_house", CommonHandler.WALNUT_TREE, TreemendousBiomes.BiomeKind.FOREST);
        register("cedar_ranger_house", CommonHandler.CEDAR_TREE, TreemendousBiomes.BiomeKind.TAIGA);
        register("poplar_ranger_house", CommonHandler.POPLAR_TREE, TreemendousBiomes.BiomeKind.FOREST);
        register("elm_ranger_house", CommonHandler.ELM_TREE, TreemendousBiomes.BiomeKind.FOREST);
        register("juniper_ranger_house", CommonHandler.JUNIPER_TREE, TreemendousBiomes.BiomeKind.TAIGA);
        register("magnolia_ranger_house", CommonHandler.MAGNOLIA_TREE, TreemendousBiomes.BiomeKind.FOREST);
        register("rainbow_eucalyptus_ranger_house", CommonHandler.RAINBOW_EUCALYPTUS_TREE, TreemendousBiomes.BiomeKind.FOREST);

        register("oak_ranger_house", CommonHandler.OAK_TREE, Biomes.FOREST.getRegistryName());
        register("birch_ranger_house", CommonHandler.BIRCH_TREE, Biomes.BIRCH_FOREST.getRegistryName());
        register("jungle_ranger_house", CommonHandler.JUNGLE_TREE, Biomes.JUNGLE.getRegistryName());
        register("spruce_ranger_house", CommonHandler.SPRUCE_TREE, Biomes.TAIGA.getRegistryName());
        register("acacia_ranger_house", CommonHandler.ACACIA_TREE, Biomes.SAVANNA.getRegistryName());
        register("dark_oak_ranger_house", CommonHandler.DARK_OAK_TREE, Biomes.DARK_FOREST.getRegistryName());
    }

    private static void register(String name, RegisteredTree tree, TreemendousBiomes.BiomeKind biomeKind)
    {
        List<ResourceLocation> biomes = new ArrayList<>();
        for (String saplingName : tree.getTree().getSaplingNames())
        {
            String treeName = saplingName != null ? String.format("%s_%s", tree.getTree().getRegistryName(), saplingName) : tree.getTree().getRegistryName();
            switch (biomeKind)
            {
                case TAIGA -> {
                    biomes.add(new ResourceLocation(Treemendous.MODID, treeName + "_taiga"));
                }
                case SAVANNA -> {
                    biomes.add(new ResourceLocation(Treemendous.MODID, treeName + "_savanna"));
                    biomes.add(new ResourceLocation(Treemendous.MODID, treeName + "_savanna_plateau"));
                }
                case FOREST -> {
                    biomes.add(new ResourceLocation(Treemendous.MODID, treeName + "_forest"));
                }
            }
        }
        register(name, tree, biomes);
    }

    private static void register(String name, RegisteredTree tree, ResourceLocation biome)
    {
        register(name, tree, ImmutableList.of(biome));
    }

    private static void register(String name, RegisteredTree tree, Collection<ResourceLocation> biomes)
    {
        ConfiguredStructureFeature<?, ?> feature = getRangerHouse(tree);
        BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new ResourceLocation(Treemendous.MODID, name), feature);
        for (ResourceLocation biome : biomes)
        {
            BIOME_STRUCTURE_MAP.put(biome, feature);
        }
        FlatLevelGeneratorSettings.STRUCTURE_FEATURES.put(ExtraRegistry.RANGER_HOUSE_FEATURE.get(), feature);
    }

    private static ConfiguredStructureFeature<?, ?> getRangerHouse(RegisteredTree tree)
    {
        return ExtraRegistry.RANGER_HOUSE_FEATURE.get().configured(new RangerHouseConfiguration(tree));
    }

    public static ConfiguredStructureFeature<?, ?> getRangerHouseForBiome(ResourceLocation biome)
    {
        return BIOME_STRUCTURE_MAP.get(biome);
    }

}
