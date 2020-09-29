package deerangle.treemendous.tree;

import deerangle.treemendous.tree.foliage.ParabolaFoliagePlacer;
import deerangle.treemendous.tree.foliage.PointyFoliagePlacer;
import deerangle.treemendous.tree.foliage.RoundedFoliagePlacer;
import deerangle.treemendous.tree.foliage.WillowFoliagePlacer;
import deerangle.treemendous.tree.trunk.AshTrunkPlacer;
import deerangle.treemendous.tree.trunk.CrossTrunkPlacer;
import net.minecraft.block.Block;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.*;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

import java.util.OptionalInt;

public class TreeMaker {

    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeNeedleTree(Block log, Block leaves, int trunkBase, int baseHeight, int extraHeight) {
        return Feature.TREE.withConfiguration(
                new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new SpruceFoliagePlacer(FeatureSpread.func_242253_a(2, 1), FeatureSpread.func_242253_a(0, 2),
                                FeatureSpread.func_242253_a(trunkBase, 1)),
                        new StraightTrunkPlacer(baseHeight, extraHeight, 1), new TwoLayerFeature(2, 0, 2))
                        .setIgnoreVines().build());
    }

    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> makePineTree(Block log, Block leaves) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new PineFoliagePlacer(FeatureSpread.func_242252_a(1), FeatureSpread.func_242252_a(1),
                                FeatureSpread.func_242253_a(3, 1)), new StraightTrunkPlacer(4, 3, 2),
                        new TwoLayerFeature(2, 0, 2))).setIgnoreVines().build());
    }

    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeSmallLeafTree(Block log, Block leaves, int baseHeight, int extraHeight, int crownWidth, int crownHeight) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new BlobFoliagePlacer(FeatureSpread.func_242252_a(crownWidth), FeatureSpread.func_242252_a(0),
                                crownHeight), new StraightTrunkPlacer(baseHeight, extraHeight, 0),
                        new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build());
    }

    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeCrossBlobTree(Block log, Block leaves, int baseHeight, int extraHeight, int crownWidth, int crownHeight) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new BlobFoliagePlacer(FeatureSpread.func_242252_a(crownWidth), FeatureSpread.func_242252_a(0),
                                crownHeight),
                        new CrossTrunkPlacer(baseHeight, extraHeight, 0, FeatureSpread.func_242253_a(2, 1),
                                FeatureSpread.func_242253_a(1, 1), FeatureSpread.func_242252_a(1), true),
                        new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build());
    }

    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeCrossRoundTree(Block log, Block leaves, int baseHeight, int extraHeight, int crownWidth, FeatureSpread branchLength, int roundIndex) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new RoundedFoliagePlacer(FeatureSpread.func_242252_a(crownWidth),
                                FeatureSpread.func_242252_a(0), roundIndex),
                        new CrossTrunkPlacer(baseHeight, extraHeight, 0, branchLength,
                                FeatureSpread.func_242253_a(2, 1), FeatureSpread.func_242253_a(1, 2), true),
                        new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build());
    }

    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeRoundedLeafTree(Block log, Block leaves, int baseHeight, int extraHeight, int roundedIndex) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new RoundedFoliagePlacer(FeatureSpread.func_242252_a(4), FeatureSpread.func_242252_a(0),
                                roundedIndex), new StraightTrunkPlacer(baseHeight, extraHeight, 0),
                        new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build());
    }

    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeAcaciaLeafTree(Block log, Block leaves) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new AcaciaFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0)),
                        new ForkyTrunkPlacer(4, 1, 2), new TwoLayerFeature(1, 0, 2))).setIgnoreVines().build());
    }

    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeLeafTree(Block log, Block leaves) {
        return makeSmallLeafTree(log, leaves, 5, 2, 2, 3);
    }

    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeMapleTree(Block log, Block leaves) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new ParabolaFoliagePlacer(FeatureSpread.func_242252_a(3), FeatureSpread.func_242253_a(0, 1), 4,
                                3), new StraightTrunkPlacer(6, 2, 0), new TwoLayerFeature(1, 0, 1))).setIgnoreVines()
                        .build());
    }

    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeFancyLeafTree(Block log, Block leaves) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new FancyFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(4), 4),
                        new FancyTrunkPlacer(5, 3, 1), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4))))
                        .setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build());
    }

    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeAshTree(Block log, Block leaves, int baseHeight, int extraHeight, FeatureSpread leavesSideOffset, FeatureSpread minLeavesHeight, FeatureSpread leavesSize) {
        //TODO: fix this bad tree
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new FancyFoliagePlacer(leavesSize, FeatureSpread.func_242252_a(4), 4),
                        new AshTrunkPlacer(baseHeight, extraHeight, 1, leavesSideOffset, minLeavesHeight),
                        new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).setIgnoreVines()
                        .func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build());
    }

    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> makePlaneTree(Block log, Block leaves) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new FancyFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(4), 4),
                        new CrossTrunkPlacer(7, 3, 0, FeatureSpread.func_242253_a(1, 2),
                                FeatureSpread.func_242253_a(1, 1), FeatureSpread.func_242252_a(-2), true),
                        new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).setIgnoreVines()
                        .func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build());
    }

    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeWillowLeafTree(Block log, Block leaves) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new WillowFoliagePlacer(FeatureSpread.func_242252_a(3), FeatureSpread.func_242252_a(0),
                                FeatureSpread.func_242253_a(2, 1)),
                        new CrossTrunkPlacer(6, 2, 0, FeatureSpread.func_242253_a(1, 1), FeatureSpread.func_242252_a(2),
                                FeatureSpread.func_242252_a(0), false), new TwoLayerFeature(1, 0, 1))).setIgnoreVines()
                        .build());
    }

    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> makePointyTree(Block log, Block leaves) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new PointyFoliagePlacer(FeatureSpread.func_242252_a(3), FeatureSpread.func_242252_a(2),
                                FeatureSpread.func_242252_a(37), FeatureSpread.func_242252_a(100),
                                FeatureSpread.func_242253_a(1, 1)), new StraightTrunkPlacer(8, 6, 0),
                        new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build());
    }
}
