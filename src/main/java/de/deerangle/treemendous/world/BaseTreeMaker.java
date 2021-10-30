package de.deerangle.treemendous.world;

import de.deerangle.treemendous.world.foliage.BallFoliagePlacer;
import de.deerangle.treemendous.world.foliage.ParabolicFoliagePlacer;
import de.deerangle.treemendous.world.foliage.PointyFoliagePlacer;
import de.deerangle.treemendous.world.foliage.RoundedFoliagePlacer;
import de.deerangle.treemendous.world.foliage.WillowFoliagePlacer;
import de.deerangle.treemendous.world.trunk.AshTrunkPlacer;
import de.deerangle.treemendous.world.trunk.CrossTrunkPlacer;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.PineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

import java.util.OptionalInt;

public class BaseTreeMaker
{

    protected static ConfiguredFeature<TreeConfiguration, ?> makeBaseOakTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling)
    {
        return Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(wood, new StraightTrunkPlacer(4, 2, 0), leaves, sapling, new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build());
    }

    protected static ConfiguredFeature<TreeConfiguration, ?> makeAcaciaTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling)
    {
        return Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        wood,
                        new ForkingTrunkPlacer(4, 1, 2),
                        leaves,
                        sapling,
                        new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 0, 2)
                ).ignoreVines().build());
    }

    protected static ConfiguredFeature<TreeConfiguration, ?> makeFancyTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling)
    {
        return Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        wood,
                        new FancyTrunkPlacer(5, 3, 1),
                        leaves,
                        sapling,
                        new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))
                ).ignoreVines().build());
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeWillowTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling)
    {
        return Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        wood,
                        new CrossTrunkPlacer(6, 2, 0, UniformInt.of(1, 2), UniformInt.of(1, 2), ConstantInt.of(1), false),
                        leaves,
                        sapling,
                        new WillowFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), UniformInt.of(2, 3)),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).ignoreVines().build());
    }

    protected static ConfiguredFeature<TreeConfiguration, ?> makeBlobulousTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling, int baseHeight, int extraHeight, IntProvider leavesSideOffset, IntProvider minLeavesHeight, IntProvider leavesSize)
    {
        return Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        wood,
                        new AshTrunkPlacer(baseHeight, extraHeight, 1, leavesSideOffset, minLeavesHeight),
                        leaves,
                        sapling,
                        new BallFoliagePlacer(leavesSize),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))
                ).ignoreVines().build());
    }

    protected static ConfiguredFeature<TreeConfiguration, ?> makeCrossBallTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling, int baseHeight, int extraHeight, IntProvider branchLength, IntProvider branchTopOffset, IntProvider crownOffset, IntProvider leafSize)
    {
        return Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        wood,
                        new CrossTrunkPlacer(baseHeight, extraHeight, 0, branchLength, branchTopOffset, crownOffset, true),
                        leaves,
                        sapling,
                        new BallFoliagePlacer(leafSize),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).ignoreVines().build());
    }

    protected static ConfiguredFeature<TreeConfiguration, ?> makeParabolicTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling)
    {
        return Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        wood,
                        new StraightTrunkPlacer(6, 2, 0),
                        leaves,
                        sapling,
                        new ParabolicFoliagePlacer(ConstantInt.of(3), UniformInt.of(0, 1), 4, 3),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).ignoreVines().build());
    }


    protected static ConfiguredFeature<TreeConfiguration, ?> makeLeafTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling, int baseHeight, int extraHeight, int crownWidth, int crownHeight)
    {
        return Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        wood,
                        new StraightTrunkPlacer(baseHeight, extraHeight, 0),
                        leaves,
                        sapling,
                        new BlobFoliagePlacer(ConstantInt.of(crownWidth), ConstantInt.of(0), crownHeight),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).ignoreVines().build());
    }

    protected static ConfiguredFeature<TreeConfiguration, ?> makeNeedleTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling, int trunkBase, int baseHeight, int extraHeight)
    {
        return Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        wood,
                        new StraightTrunkPlacer(baseHeight, extraHeight, 1),
                        leaves,
                        sapling,
                        new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(trunkBase, trunkBase + 1)),
                        new TwoLayersFeatureSize(2, 0, 2)
                ).ignoreVines().build());
    }

    protected static ConfiguredFeature<TreeConfiguration, ?> makePineTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling)
    {
        return Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        wood,
                        new StraightTrunkPlacer(4, 3, 2),
                        leaves,
                        sapling,
                        new PineFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), UniformInt.of(3, 4)),
                        new TwoLayersFeatureSize(2, 0, 2)
                ).ignoreVines().build());
    }

    protected static ConfiguredFeature<TreeConfiguration, ?> makeRoundedTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling, int baseHeight, int extraHeight, int roundedIndex)
    {
        return Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        wood,
                        new StraightTrunkPlacer(baseHeight, extraHeight, 0),
                        leaves,
                        sapling,
                        new RoundedFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), roundedIndex),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).ignoreVines().build());
    }

    protected static ConfiguredFeature<TreeConfiguration, ?> makePlaneTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling)
    {
        return Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        wood,
                        new CrossTrunkPlacer(7, 3, 0, UniformInt.of(1, 3), UniformInt.of(1, 2), ConstantInt.of(-2), true),
                        leaves,
                        sapling,
                        new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))
                ).ignoreVines().build());
    }

    protected static ConfiguredFeature<TreeConfiguration, ?> makePointyTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling, IntProvider topSpread, IntProvider bottomSpread, int bottomOffset, TrunkPlacer trunkPlacer)
    {
        return Feature.TREE.configured(
                new TreeConfiguration.TreeConfigurationBuilder(
                        wood,
                        trunkPlacer,
                        leaves,
                        sapling,
                        new PointyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), topSpread, bottomSpread, UniformInt.of(bottomOffset, bottomOffset + 1)),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).ignoreVines().build());
    }

}
