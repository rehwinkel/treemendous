package deerangle.treemendous.tree;

import deerangle.treemendous.main.Treemendous;
import deerangle.treemendous.tree.foliage.BallFoliagePlacer;
import deerangle.treemendous.tree.foliage.ParabolaFoliagePlacer;
import deerangle.treemendous.tree.foliage.WillowFoliagePlacer;
import deerangle.treemendous.util.FeatureSpread;
import net.minecraft.block.Block;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TreeMaker {

    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES,
            Treemendous.MODID);

    public static TreeFeatureConfig makeNeedleTree(Block log, Block leaves, Block sapling, int i, int i1, int i2) {
        return null;
    }

    public static TreeFeatureConfig makePineTree(Block block, Block block1, Block sapling) {
        return null;
    }

    public static TreeFeatureConfig makeMapleTree(Block log, Block leaves, Block sapling) {
        return (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                new SimpleBlockStateProvider(leaves.getDefaultState()),
                new ParabolaFoliagePlacer(FeatureSpread.func_242252_a(3), 4, 3))).baseHeight(6).heightRandA(2)
                .heightRandB(0).trunkHeight(0).ignoreVines().setSapling((net.minecraftforge.common.IPlantable) sapling)
                .build();
    }

    public static TreeFeatureConfig makeLeafTree(Block block, Block block1, Block sapling) {
        return null;
    }

    public static TreeFeatureConfig makeRoundedLeafTree(Block log, Block leaves, Block sapling, int i, int i1, int i2) {
        return null;
    }

    public static TreeFeatureConfig makeSmallLeafTree(Block log, Block leaves, Block sapling, int i, int i1, int i2, int i3) {
        return null;
    }

    public static TreeFeatureConfig makePlaneTree(Block block, Block block1, Block sapling) {
        return null;
    }

    public static TreeFeatureConfig makeAshTree(Block log, Block leaves, Block sapling, int i, int i1, FeatureSpread func_242253_a, FeatureSpread func_242253_a1, FeatureSpread func_242253_a2) {
        return null;
    }

    public static TreeFeatureConfig makeFancyLeafTree(Block block, Block block1, Block sapling) {
        return null;
    }

    public static TreeFeatureConfig makeAcaciaLeafTree(Block block, Block block1, Block sapling) {
        return null;
    }

    public static TreeFeatureConfig makeWillowLeafTree(Block log, Block leaves, Block sapling) {
        return (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                new SimpleBlockStateProvider(leaves.getDefaultState()),
                new WillowFoliagePlacer(FeatureSpread.func_242252_a(3), FeatureSpread.func_242253_a(2, 1))))
                .baseHeight(6).heightRandA(2).heightRandB(0).trunkHeight(0).ignoreVines()
                .setSapling((net.minecraftforge.common.IPlantable) sapling).build();
    }

    public static TreeFeatureConfig makeCrossBlobTree(Block log, Block leaves, Block sapling, int i, int i1, int i2, int i3) {
        return null;
    }

    public static TreeFeatureConfig makeCrossRoundTree(Block log, Block leaves, Block sapling, int i, int i1, int i2, FeatureSpread func_242253_a, int i3) {
        return null;
    }

    public static TreeFeatureConfig makePointyTree(Block log, Block leaves, Block sapling, FeatureSpread func_242252_a, FeatureSpread func_242252_a1, int i, int i1, int i2) {
        return null;
    }

    public static TreeFeatureConfig makeBallTree(Block log, Block leaves, Block sapling) {
        return (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                new SimpleBlockStateProvider(leaves.getDefaultState()),
                new BallFoliagePlacer(FeatureSpread.func_242253_a(2, 2)))).baseHeight(9).heightRandA(2).heightRandB(1)
                .trunkHeight(0).ignoreVines().setSapling((net.minecraftforge.common.IPlantable) sapling).build();
    }

    public static TreeFeatureConfig makeJuniperTree(Block log, Block leaves, Block sapling, FeatureSpread func_242252_a, FeatureSpread func_242252_a1, int i, int i1, int i2) {
        return null;
    }

    /*
    public static TreeFeatureConfig makeNeedleTree(Block log, Block leaves, int trunkBase, int baseHeight, int extraHeight) {
        return Feature.TREE.withConfiguration(
                new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new SpruceFoliagePlacer(FeatureSpread.func_242253_a(2, 1), FeatureSpread.func_242253_a(0, 2),
                                FeatureSpread.func_242253_a(trunkBase, 1)),
                        new StraightTrunkPlacer(baseHeight, extraHeight, 1), new TwoLayerFeature(2, 0, 2))
                        .setIgnoreVines().build());
    }

    public static TreeFeatureConfig makePineTree(Block log, Block leaves) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new PineFoliagePlacer(FeatureSpread.func_242252_a(1), FeatureSpread.func_242252_a(1),
                                FeatureSpread.func_242253_a(3, 1)), new StraightTrunkPlacer(4, 3, 2),
                        new TwoLayerFeature(2, 0, 2))).setIgnoreVines().build());
    }

    public static TreeFeatureConfig makeSmallLeafTree(Block log, Block leaves, int baseHeight, int extraHeight, int crownWidth, int crownHeight) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new BlobFoliagePlacer(FeatureSpread.func_242252_a(crownWidth), FeatureSpread.func_242252_a(0),
                                crownHeight), new StraightTrunkPlacer(baseHeight, extraHeight, 0),
                        new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build());
    }

    public static TreeFeatureConfig makeCrossBlobTree(Block log, Block leaves, int baseHeight, int extraHeight, int crownWidth, int crownHeight) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new BlobFoliagePlacer(FeatureSpread.func_242252_a(crownWidth), FeatureSpread.func_242252_a(0),
                                crownHeight),
                        new CrossTrunkPlacer(baseHeight, extraHeight, 0, FeatureSpread.func_242253_a(2, 1),
                                FeatureSpread.func_242253_a(1, 1), FeatureSpread.func_242252_a(1), true),
                        new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build());
    }

    public static TreeFeatureConfig makeCrossRoundTree(Block log, Block leaves, int baseHeight, int extraHeight, int crownWidth, FeatureSpread branchLength, int roundIndex) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new RoundedFoliagePlacer(FeatureSpread.func_242252_a(crownWidth),
                                FeatureSpread.func_242252_a(0), roundIndex),
                        new CrossTrunkPlacer(baseHeight, extraHeight, 0, branchLength,
                                FeatureSpread.func_242253_a(2, 1), FeatureSpread.func_242253_a(1, 2), true),
                        new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build());
    }

    public static TreeFeatureConfig makeRoundedLeafTree(Block log, Block leaves, int baseHeight, int extraHeight, int roundedIndex) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new RoundedFoliagePlacer(FeatureSpread.func_242252_a(4), FeatureSpread.func_242252_a(0),
                                roundedIndex), new StraightTrunkPlacer(baseHeight, extraHeight, 0),
                        new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build());
    }

    public static TreeFeatureConfig makeAcaciaLeafTree(Block log, Block leaves) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new AcaciaFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0)),
                        new ForkyTrunkPlacer(4, 1, 2), new TwoLayerFeature(1, 0, 2))).setIgnoreVines().build());
    }

    public static TreeFeatureConfig makeLeafTree(Block log, Block leaves) {
        return makeSmallLeafTree(log, leaves, 5, 2, 2, 3);
    }

    public static TreeFeatureConfig makeMapleTree(Block log, Block leaves) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new ParabolaFoliagePlacer(FeatureSpread.func_242252_a(3), 4,
                                3), new StraightTrunkPlacer(6, 2, 0), new TwoLayerFeature(1, 0, 1))).setIgnoreVines()
                        .build());
    }

    public static TreeFeatureConfig makeFancyLeafTree(Block log, Block leaves) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new FancyFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(4), 4),
                        new FancyTrunkPlacer(5, 3, 1), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4))))
                        .setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build());
    }

    public static TreeFeatureConfig makeAshTree(Block log, Block leaves, int baseHeight, int extraHeight, FeatureSpread leavesSideOffset, FeatureSpread minLeavesHeight, FeatureSpread leavesSize) {
        //TODO: fix this bad tree
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()), new BallFoliagePlacer(leavesSize),
                        new AshTrunkPlacer(baseHeight, extraHeight, 1, leavesSideOffset, minLeavesHeight),
                        new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).setIgnoreVines()
                        .func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build());
    }

    public static TreeFeatureConfig makePlaneTree(Block log, Block leaves) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new FancyFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(4), 4),
                        new CrossTrunkPlacer(7, 3, 0, FeatureSpread.func_242253_a(1, 2),
                                FeatureSpread.func_242253_a(1, 1), FeatureSpread.func_242252_a(-2), true),
                        new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).setIgnoreVines()
                        .func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build());
    }

    public static TreeFeatureConfig makeWillowLeafTree(Block log, Block leaves) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new WillowFoliagePlacer(FeatureSpread.func_242252_a(3), FeatureSpread.func_242252_a(0),
                                FeatureSpread.func_242253_a(2, 1)),
                        new CrossTrunkPlacer(6, 2, 0, FeatureSpread.func_242253_a(1, 1),
                                FeatureSpread.func_242253_a(1, 1), FeatureSpread.func_242252_a(1), false),
                        new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build());
    }

    public static TreeFeatureConfig makePointyTree(Block log, Block leaves, FeatureSpread topSpread, FeatureSpread bottomSpread, int bottomOffset, int baseHeight, int extraHeight) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new PointyFoliagePlacer(FeatureSpread.func_242252_a(3), FeatureSpread.func_242252_a(2),
                                topSpread, bottomSpread, FeatureSpread.func_242253_a(bottomOffset, 1)),
                        new StraightTrunkPlacer(baseHeight, extraHeight, 1), new TwoLayerFeature(1, 0, 1)))
                        .setIgnoreVines().build());
    }

    public static TreeFeatureConfig makeJuniperTree(Block log, Block leaves, FeatureSpread topSpread, FeatureSpread bottomSpread, int bottomOffset, int baseHeight, int extraHeight) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new PointyFoliagePlacer(FeatureSpread.func_242252_a(3), FeatureSpread.func_242252_a(2),
                                topSpread, bottomSpread, FeatureSpread.func_242253_a(bottomOffset, 1)),
                        new CrossTrunkPlacer(baseHeight, extraHeight, 1, FeatureSpread.func_242252_a(1),
                                FeatureSpread.func_242252_a(6), FeatureSpread.func_242252_a(0), false),
                        new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build());
    }

    public static TreeFeatureConfig makeBallTree(Block log, Block leaves) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new BallFoliagePlacer(FeatureSpread.func_242253_a(2, 2)),
                        new CrossTrunkPlacer(9, 2, 1, FeatureSpread.func_242253_a(2, 2),
                                FeatureSpread.func_242253_a(1, 2), FeatureSpread.func_242252_a(0), true),
                        new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build());
    }
    */

}
