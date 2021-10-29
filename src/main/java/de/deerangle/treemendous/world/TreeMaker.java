package de.deerangle.treemendous.world;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;

public class TreeMaker extends BaseTreeMaker
{

    private static BlockStateProvider provider(Block b)
    {
        return new SimpleStateProvider(b.defaultBlockState());
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeCherryTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseFancyTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeDouglasTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseSpruceTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeMegaPineTree(Block leaves, Block wood, Block sapling)
    {
        return makeMegaTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeMapleTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseOakTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makePineTree(Block leaves, Block wood, Block sapling)
    {
        return makeBasePineTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeLarchTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseSpruceTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeFirTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseSpruceTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeBeechTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseOakTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeAlderTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseOakTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeChestnutTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseOakTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makePlaneTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseFancyTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeAshTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseOakTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeLindenTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseOakTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeRobiniaTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseAcaciaTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeWillowTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseOakTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makePomegranateTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseOakTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeWalnutTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseOakTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeCedarTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseSpruceTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makePoplarTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseOakTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeElmTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseFancyTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeJuniperTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseSpruceTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeMagnoliaTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseOakTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeRainbowEucalyptusTree(Block leaves, Block wood, Block sapling)
    {
        return makeBaseFancyTree(provider(leaves), provider(wood), provider(sapling));
    }

}
