package de.deerangle.treemendous.world;

import de.deerangle.treemendous.world.trunk.CrossTrunkPlacer;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class TreeMaker extends BaseTreeMaker
{

    private static BlockStateProvider provider(Block b)
    {
        return new SimpleStateProvider(b.defaultBlockState());
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeDouglasTree(Block leaves, Block wood, Block sapling)
    {
        return makeNeedleTree(provider(leaves), provider(wood), provider(sapling), 1, 6, 2);
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeMapleTree(Block leaves, Block wood, Block sapling)
    {
        return makeParabolicTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeJapaneseTree(Block leaves, Block wood, Block sapling)
    {
        return makeLeafTree(provider(leaves), provider(wood), provider(sapling), 5, 2, 2, 3);
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makePineTree(Block leaves, Block wood, Block sapling)
    {
        return makePineTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeLarchTree(Block leaves, Block wood, Block sapling)
    {
        return makeNeedleTree(provider(leaves), provider(wood), provider(sapling), 2, 7, 3);
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeFirTree(Block leaves, Block wood, Block sapling)
    {
        return makeNeedleTree(provider(leaves), provider(wood), provider(sapling), 1, 6, 2);
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeBeechTree(Block leaves, Block wood, Block sapling)
    {
        return makeRoundedTree(provider(leaves), provider(wood), provider(sapling), 5, 2, 3);
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeCherryTree(Block leaves, Block wood, Block sapling)
    {
        return makeRoundedTree(provider(leaves), provider(wood), provider(sapling), 6, 2, 4);
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeAlderTree(Block leaves, Block wood, Block sapling)
    {
        return makeRoundedTree(provider(leaves), provider(wood), provider(sapling), 5, 2, 2);
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeChestnutTree(Block leaves, Block wood, Block sapling)
    {
        return makeLeafTree(provider(leaves), provider(wood), provider(sapling), 5, 3, 2, 4);
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makePlaneTree(Block leaves, Block wood, Block sapling)
    {
        return makePlaneTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeLindenTree(Block leaves, Block wood, Block sapling)
    {
        return makeFancyTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeRobiniaTree(Block leaves, Block wood, Block sapling)
    {
        return makeAcaciaTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeWillowTree(Block leaves, Block wood, Block sapling)
    {
        return makeWillowTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeCedarTree(Block leaves, Block wood, Block sapling)
    {
        return makePointyTree(provider(leaves), provider(wood), provider(sapling), ConstantInt.of(37), ConstantInt.of(100), 1, new StraightTrunkPlacer(8, 5, 1));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeJuniperTree(Block leaves, Block wood, Block sapling)
    {
        return makePointyTree(provider(leaves), provider(wood), provider(sapling), ConstantInt.of(42), ConstantInt.of(100), 2, new CrossTrunkPlacer(12, 6, 1, ConstantInt.of(1), ConstantInt.of(6), ConstantInt.of(0), false));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeRainbowEucalyptusTree(Block leaves, Block wood, Block sapling)
    {
        return makeCrossBallTree(provider(leaves), provider(wood), provider(sapling), 9, 3, UniformInt.of(2, 4), UniformInt.of(1, 3), ConstantInt.of(0), UniformInt.of(2, 3));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeMagnoliaTree(Block leaves, Block wood, Block sapling)
    {
        return makeCrossBallTree(provider(leaves), provider(wood), provider(sapling), 5, 3, UniformInt.of(2, 3), UniformInt.of(1, 2), ConstantInt.of(1), UniformInt.of(2, 3));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeWalnutTree(Block leaves, Block wood, Block sapling)
    {
        return makeFingerTree(provider(leaves), provider(wood), provider(sapling));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeAshTree(Block leaves, Block wood, Block sapling)
    {
        return makeRizoniTree(provider(leaves), provider(wood), provider(sapling), 8, 5, 2, UniformInt.of(4, 6), ConstantInt.of(2), ConstantInt.of(3), 1.2f);
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makePomegranateTree(Block leaves, Block wood, Block sapling)
    {
        return makeRoundedTree(provider(leaves), provider(wood), provider(sapling), 4, 2, 1);
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makePoplarTree(Block leaves, Block wood, Block sapling)
    {
        return makeRizoniTree(provider(leaves), provider(wood), provider(sapling), 12, 5, 3, UniformInt.of(3, 5), ConstantInt.of(1), ConstantInt.of(2), 2.0f);
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeElmTree(Block leaves, Block wood, Block sapling)
    {
        return makeCrossBallTree(provider(leaves), provider(wood), provider(sapling), 9, 4, UniformInt.of(3, 4), UniformInt.of(2, 4), ConstantInt.of(0), UniformInt.of(2, 3));
    }

}
