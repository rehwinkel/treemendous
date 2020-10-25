package deerangle.treemendous.tree;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.LogBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractSmallTreeFeature;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class CrossTreeFeature extends AbstractSmallTreeFeature<CrossTreeFeatureConfig> {

    public CrossTreeFeature(Function<Dynamic<?>, ? extends CrossTreeFeatureConfig> deserializer) {
        super(deserializer);
    }

    @Override
    protected boolean place(IWorldGenerationReader generationReader, Random rand, BlockPos positionIn, Set<BlockPos> p_225557_4_, Set<BlockPos> resultingBlocks, MutableBoundingBox boundingBoxIn, CrossTreeFeatureConfig configIn) {
        int i = configIn.baseHeight + rand.nextInt(configIn.heightRandA + 1) + rand.nextInt(configIn.heightRandB + 1);
        int j = i - (configIn.trunkHeight + rand.nextInt(configIn.trunkHeightRandom + 1) + 1);
        int k = configIn.foliagePlacer.func_225573_a_(rand, j, i, configIn);

        Optional<BlockPos> optional = this.func_227212_a_(generationReader, i, j, k, positionIn, configIn);
        if (!optional.isPresent()) {
            return false;
        } else {
            BlockPos blockpos = optional.get();
            this.setDirtAt(generationReader, blockpos.down(), blockpos);
            configIn.foliagePlacer
                    .func_225571_a_(generationReader, rand, configIn, i + 2, j + 1, k, blockpos, resultingBlocks);
            this.placeTrunk(generationReader, rand, i, j, blockpos,
                    configIn.trunkTopOffset + rand.nextInt(configIn.trunkTopOffsetRandom + 1), p_225557_4_,
                    boundingBoxIn, configIn);
            return true;
        }
    }

    protected boolean setRotatedLog(IWorldGenerationReader generationReader, Random rand, BlockPos pos, Set<BlockPos> resultingBlocks, MutableBoundingBox boundingBox, BaseTreeFeatureConfig featureConfig, Direction.Axis axis) {
        if (!isAirOrLeaves(generationReader, pos) && !isTallPlants(generationReader, pos) && !isWater(generationReader,
                pos)) {
            return false;
        } else {
            this.setBlockState(generationReader, pos,
                    featureConfig.trunkProvider.getBlockState(rand, pos).with(LogBlock.AXIS, axis), boundingBox);
            resultingBlocks.add(pos.toImmutable());
            return true;
        }
    }

    protected void placeTrunk(IWorldGenerationReader generationReader, Random rand, int baseHeight, int trunkHeight, BlockPos pos, int trunkOffset, Set<BlockPos> resultingBlocks, MutableBoundingBox bounds, TreeFeatureConfig featureConfig) {
        CrossTreeFeatureConfig configIn = (CrossTreeFeatureConfig) featureConfig;
        int[] branchOffsets = new int[]{configIn.branchTopOffset.func_242259_a(
                rand), configIn.branchTopOffset.func_242259_a(rand), configIn.branchTopOffset.func_242259_a(
                rand), configIn.branchTopOffset.func_242259_a(rand)};

        int height = baseHeight - trunkOffset;
        for (int i = 0; i < height; ++i) {
            this.setLog(generationReader, rand, pos.up(i), resultingBlocks, bounds, featureConfig);
            for (Direction dir : Direction.Plane.HORIZONTAL) {
                if (i + 1 + branchOffsets[dir.getHorizontalIndex()] == height) {
                    int thisBranchLen = configIn.branchLength.func_242259_a(rand);
                    for (int branch = 1; branch <= thisBranchLen; branch++) {
                        BlockPos p = pos.up(i).offset(dir, branch);
                        this.setRotatedLog(generationReader, rand, p, resultingBlocks, bounds, featureConfig,
                                dir.getAxis());
                        if (branch == thisBranchLen && configIn.leavesAtEnd) {
                            int a = rand.nextInt(2);
                            int b = rand.nextInt(2);
                            configIn.foliagePlacer
                                    .func_225571_a_(generationReader, rand, configIn, i + 1 + a, 3 + a + b, 2,
                                            pos.offset(dir, branch), resultingBlocks);
                        }
                    }
                }
            }
        }
    }

}
