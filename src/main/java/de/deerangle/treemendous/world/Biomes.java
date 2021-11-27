package de.deerangle.treemendous.world;

import de.deerangle.treemendous.main.TreeRegistry;
import de.deerangle.treemendous.main.Treemendous;
import de.deerangle.treemendous.main.TreemendousConfig;
import de.deerangle.treemendous.tree.Tree;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Biomes
{

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Treemendous.MODID);
    private static final List<TreemendousBiomes.RegisteredBiome> ALL_BIOMES = new ArrayList<>();

    public static void registerTrees()
    {
        // TODO: squirrels and woodpeckers?
        registerBiomes(TreeRegistry.DOUGLAS_TREE, TreemendousBiomes.BiomeKind.TAIGA);
        registerBiomes(TreeRegistry.PINE_TREE, TreemendousBiomes.BiomeKind.TAIGA);
        registerBiomes(TreeRegistry.LARCH_TREE, TreemendousBiomes.BiomeKind.TAIGA);
        registerBiomes(TreeRegistry.FIR_TREE, TreemendousBiomes.BiomeKind.TAIGA);
        registerBiomes(TreeRegistry.MAPLE_TREE, TreemendousBiomes.BiomeKind.FOREST);
        registerBiomes(TreeRegistry.MAPLE_TREE, "red", TreemendousBiomes.BiomeKind.FOREST);
        registerBiomes(TreeRegistry.MAPLE_TREE, "brown", TreemendousBiomes.BiomeKind.FOREST);
        registerBiomes(TreeRegistry.JAPANESE_TREE, TreemendousBiomes.BiomeKind.FOREST);
        registerBiomes(TreeRegistry.BEECH_TREE, TreemendousBiomes.BiomeKind.FOREST);
        registerBiomes(TreeRegistry.CHERRY_TREE, TreemendousBiomes.BiomeKind.FOREST);
        registerBiomes(TreeRegistry.ALDER_TREE, TreemendousBiomes.BiomeKind.FOREST);
        registerBiomes(TreeRegistry.CHESTNUT_TREE, TreemendousBiomes.BiomeKind.FOREST);
        registerBiomes(TreeRegistry.PLANE_TREE, TreemendousBiomes.BiomeKind.FOREST);
        registerBiomes(TreeRegistry.ASH_TREE, TreemendousBiomes.BiomeKind.FOREST);
        registerBiomes(TreeRegistry.LINDEN_TREE, TreemendousBiomes.BiomeKind.FOREST);
        registerBiomes(TreeRegistry.ROBINIA_TREE, TreemendousBiomes.BiomeKind.SAVANNA);
        registerBiomes(TreeRegistry.WILLOW_TREE, TreemendousBiomes.BiomeKind.FOREST);
        registerBiomes(TreeRegistry.POMEGRANATE_TREE, TreemendousBiomes.BiomeKind.SAVANNA);
        registerBiomes(TreeRegistry.WALNUT_TREE, TreemendousBiomes.BiomeKind.FOREST);
        registerBiomes(TreeRegistry.CEDAR_TREE, TreemendousBiomes.BiomeKind.TAIGA);
        registerBiomes(TreeRegistry.POPLAR_TREE, TreemendousBiomes.BiomeKind.FOREST);
        registerBiomes(TreeRegistry.ELM_TREE, TreemendousBiomes.BiomeKind.FOREST);
        registerBiomes(TreeRegistry.JUNIPER_TREE, TreemendousBiomes.BiomeKind.TAIGA);
        registerBiomes(TreeRegistry.MAGNOLIA_TREE, TreemendousBiomes.BiomeKind.FOREST);
        registerBiomes(TreeRegistry.RAINBOW_EUCALYPTUS_TREE, TreemendousBiomes.BiomeKind.FOREST);
    }

    private static void registerBiomes(Tree tree, TreemendousBiomes.BiomeKind kind)
    {
        registerBiomes(tree, null, kind);
    }

    private static void registerBiomes(Tree tree, String saplingName, TreemendousBiomes.BiomeKind kind)
    {
        String treeName = saplingName != null ? String.format("%s_%s", tree.getRegistryName(), saplingName) : tree.getRegistryName();
        int treeDensity = TreemendousConfig.getTreeDensity(treeName);

        Collection<TreemendousBiomes.RegisteredBiome> registeredBiomes = TreemendousBiomes.registerBiomes(BIOMES, tree, saplingName, kind, treeDensity);
        ALL_BIOMES.addAll(registeredBiomes);
    }

    public static void addBiomesToOverworld()
    {
        for (TreemendousBiomes.RegisteredBiome biome : ALL_BIOMES)
        {
            int weight = TreemendousConfig.getSpawnWeightForBiome(biome.getBiomeKey().location().getPath());
            if (weight > 0)
            {
                BiomeManager.addBiome(biome.getBiomeType(), new BiomeManager.BiomeEntry(biome.getBiomeKey(), weight));
            }
        }
    }

    public static void addBiomesToDictionary()
    {
        for (TreemendousBiomes.RegisteredBiome biome : ALL_BIOMES)
        {
            BiomeDictionary.Type[] types = TreemendousConfig.getDictionaryTypesForBiome(biome.getBiomeKey().location().getPath());
            if (types.length > 0)
            {
                BiomeDictionary.addTypes(biome.getBiomeKey(), types);
            }
        }
    }

    public static List<TreemendousBiomes.RegisteredBiome> getAllBiomes()
    {
        return ALL_BIOMES;
    }

}
