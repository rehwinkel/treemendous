package deerangle.treemendous.tree;

import deerangle.treemendous.tree.foliage.*;
import deerangle.treemendous.util.FeatureSpread;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.PineFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;

public class TreeMaker {

    public static TreeFeatureConfig makeNeedleTree(Block log, Block leaves, Block sapling, int trunkBase, int baseHeight, int extraHeight) {
        return (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                new SimpleBlockStateProvider(leaves.getDefaultState()), new SpruceFoliagePlacer(2, 1)))
                .baseHeight(baseHeight).heightRandA(extraHeight).trunkHeight(trunkBase).trunkHeightRandom(1)
                .trunkTopOffsetRandom(2).ignoreVines().setSapling((net.minecraftforge.common.IPlantable) sapling)
                .build();
    }

    public static TreeFeatureConfig makePineTree(Block log, Block leaves, Block sapling) {
        return (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                new SimpleBlockStateProvider(leaves.getDefaultState()), new PineFoliagePlacer(1, 0))).baseHeight(6)
                .heightRandA(3).heightRandB(3).trunkTopOffset(1).foliageHeight(3).foliageHeightRandom(1).ignoreVines()
                .setSapling((net.minecraftforge.common.IPlantable) sapling).build();
    }

    public static TreeFeatureConfig makeMapleTree(Block log, Block leaves, Block sapling) {
        return (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                new SimpleBlockStateProvider(leaves.getDefaultState()),
                new ParabolaFoliagePlacer(FeatureSpread.create(3), 3, 4))).baseHeight(6).heightRandA(2).heightRandB(0)
                .trunkHeight(0).ignoreVines().setSapling((net.minecraftforge.common.IPlantable) sapling).build();
    }

    public static TreeFeatureConfig makeLeafTree(Block log, Block leaves, Block sapling) {
        return makeSmallLeafTree(log, leaves, sapling, 5, 2, 2, 3);
    }

    public static TreeFeatureConfig makeRoundedLeafTree(Block log, Block leaves, Block sapling, int baseHeight, int extraHeight, int roundedIndex) {
        return (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                new SimpleBlockStateProvider(leaves.getDefaultState()),
                new RoundedFoliagePlacer(FeatureSpread.create(4), roundedIndex))).baseHeight(baseHeight)
                .heightRandA(extraHeight).heightRandB(0).trunkHeight(0).ignoreVines()
                .setSapling((net.minecraftforge.common.IPlantable) sapling).build();
    }

    public static TreeFeatureConfig makeSmallLeafTree(Block log, Block leaves, Block sapling, int baseHeight, int extraHeight, int crownWidth, int crownHeight) {
        return new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                new SimpleBlockStateProvider(leaves.getDefaultState()), new BlobFoliagePlacer(crownWidth, 0))
                .baseHeight(baseHeight).heightRandA(extraHeight).heightRandB(0).foliageHeight(crownHeight).ignoreVines()
                .setSapling((net.minecraftforge.common.IPlantable) sapling).build();
    }

    public static TreeFeatureConfig makePlaneTreeCross(Block block, Block block1, Block sapling) {
        return new CrossTreeFeatureConfig(block, block1, new BallFoliagePlacer(FeatureSpread.create(2, 1)),
                FeatureSpread.create(7, 3), FeatureSpread.create(0), FeatureSpread.create(1, 2),
                FeatureSpread.create(1, 1), FeatureSpread.create(0), true);
    }

    public static TreeFeatureConfig makeFancyLeafTree(Block log, Block leaves, Block sapling) {
        return (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                new SimpleBlockStateProvider(leaves.getDefaultState()), new BlobFoliagePlacer(0, 0)))
                .setSapling((net.minecraftforge.common.IPlantable) Blocks.OAK_SAPLING).baseHeight(5).heightRandA(3)
                .heightRandB(1).build();
    }

    public static TreeFeatureConfig makeAcaciaLeafTree(Block log, Block leaves, Block sapling) {
        return (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                new SimpleBlockStateProvider(leaves.getDefaultState()), new AcaciaFoliagePlacer(2, 0))).baseHeight(4)
                .heightRandA(1).heightRandB(2).trunkHeight(0).ignoreVines()
                .setSapling((net.minecraftforge.common.IPlantable) sapling).build();
    }

    public static TreeFeatureConfig makeWillowTreeCross(Block log, Block leaves, Block sapling) {
        return new CrossTreeFeatureConfig(log, leaves,
                new WillowFoliagePlacer(FeatureSpread.create(3), FeatureSpread.create(2, 1)),
                FeatureSpread.create(6, 2), FeatureSpread.create(0), FeatureSpread.create(1, 1),
                FeatureSpread.create(1, 1), FeatureSpread.create(1), false);
    }

    public static TreeFeatureConfig makeBlobTreeCross(Block log, Block leaves, Block sapling, int baseHeight, int extraHeight, int crownWidth) {
        return new CrossTreeFeatureConfig(log, leaves, new BlobFoliagePlacer(crownWidth, 0),
                FeatureSpread.create(baseHeight, extraHeight), FeatureSpread.create(3, 1), FeatureSpread.create(2, 1),
                FeatureSpread.create(1, 1), FeatureSpread.create(1), true);
    }

    public static TreeFeatureConfig makeRoundTreeCross(Block log, Block leaves, Block sapling, int baseHeight, int extraHeight, int crownWidth, FeatureSpread branchLength, int roundIndex) {
        return new CrossTreeFeatureConfig(log, leaves,
                new RoundedFoliagePlacer(FeatureSpread.create(crownWidth), roundIndex),
                FeatureSpread.create(baseHeight, extraHeight), FeatureSpread.create(0), branchLength,
                FeatureSpread.create(2, 1), FeatureSpread.create(1, 2), true);
    }

    public static TreeFeatureConfig makePointyTree(Block log, Block leaves, Block sapling, FeatureSpread topSpread, FeatureSpread bottomSpread, int bottomOffset, int baseHeight, int extraHeight) {
        return (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                new SimpleBlockStateProvider(leaves.getDefaultState()),
                new PointyFoliagePlacer(FeatureSpread.create(3), topSpread, bottomSpread,
                        FeatureSpread.create(bottomOffset, 1)))).baseHeight(baseHeight).heightRandA(extraHeight)
                .heightRandB(0).trunkHeight(0).ignoreVines().setSapling((net.minecraftforge.common.IPlantable) sapling)
                .build();
    }

    public static TreeFeatureConfig makeBallTreeCross(Block log, Block leaves, Block sapling) {
        return new CrossTreeFeatureConfig(log, leaves, new BallFoliagePlacer(FeatureSpread.create(2, 2)),
                FeatureSpread.create(7, 3), FeatureSpread.create(0), FeatureSpread.create(2, 2),
                FeatureSpread.create(1, 2), FeatureSpread.create(0), true);
    }

}
