package deerangle.treemendous.tree.foliage;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import deerangle.treemendous.util.FeatureSpread;
import deerangle.treemendous.world.TreeWorldGenRegistry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;

import java.util.Random;
import java.util.Set;

public class WillowFoliagePlacer extends FoliagePlacer {

    private final FeatureSpread branchLength;

    public WillowFoliagePlacer(FeatureSpread radius, FeatureSpread branchLength) {
        super(radius.getBase(), radius.getVariance(), TreeWorldGenRegistry.WILLOW_FOLIAGE_PLACER);
        this.branchLength = branchLength;
    }

    public <T> WillowFoliagePlacer(Dynamic<T> p_i225847_1_) {
        this(FeatureSpread.create(p_i225847_1_.get("radius").asInt(0), p_i225847_1_.get("radius_random").asInt(0)),
                FeatureSpread.create(p_i225847_1_.get("branch").asInt(0), p_i225847_1_.get("branch_random").asInt(0)));
    }

    private void growBranchDown(int x, int y, int z, int length, IWorldGenerationReader worldGenerationReader, Random random, BaseTreeFeatureConfig featureConfig, BlockPos foliage, Set<BlockPos> resultingBlocks) {
        BlockPos.Mutable mutablePos = new BlockPos.Mutable();
        for (int i = 0; i < length; i++) {
            mutablePos.setPos(foliage);
            mutablePos.move(x, y - i, z);
            if (TreeFeature.isAirOrLeaves(worldGenerationReader, mutablePos)) {
                worldGenerationReader
                        .setBlockState(mutablePos, featureConfig.leavesProvider.getBlockState(random, mutablePos), 19);
                resultingBlocks.add(mutablePos.toImmutable());
            }
        }
    }

    @Override
    public void func_225571_a_(IWorldGenerationReader worldGenerationReader, Random random, TreeFeatureConfig featureConfig, int startY, int trunk, int foliage, BlockPos pos, Set<BlockPos> resultingBlocks) {
        for (int i = 0; i < 4; i++) {
            this.func_227384_a_(worldGenerationReader, random, featureConfig, startY - 2, pos, startY - i - 2,
                    Math.min(i + 1, 3), resultingBlocks);
        }

        growBranchDown(2, startY - 6, 2, branchLength.func_242259_a(random), worldGenerationReader, random,
                featureConfig, pos, resultingBlocks);
        growBranchDown(-2, startY - 6, -2, branchLength.func_242259_a(random), worldGenerationReader, random,
                featureConfig, pos, resultingBlocks);
        growBranchDown(-2, startY - 6, 2, branchLength.func_242259_a(random), worldGenerationReader, random,
                featureConfig, pos, resultingBlocks);
        growBranchDown(2, startY - 6, -2, branchLength.func_242259_a(random), worldGenerationReader, random,
                featureConfig, pos, resultingBlocks);
        growBranchDown(3, startY - 6, 0, branchLength.func_242259_a(random), worldGenerationReader, random,
                featureConfig, pos, resultingBlocks);
        growBranchDown(-3, startY - 6, 0, branchLength.func_242259_a(random), worldGenerationReader, random,
                featureConfig, pos, resultingBlocks);
        growBranchDown(0, startY - 6, 3, branchLength.func_242259_a(random), worldGenerationReader, random,
                featureConfig, pos, resultingBlocks);
        growBranchDown(0, startY - 6, -3, branchLength.func_242259_a(random), worldGenerationReader, random,
                featureConfig, pos, resultingBlocks);
    }

    @Override
    public int func_225573_a_(Random p_225573_1_, int p_225573_2_, int p_225573_3_, TreeFeatureConfig p_225573_4_) {
        return 6;
    }

    @Override
    protected boolean func_225572_a_(Random random, int height, int x, int y, int z, int size) {
        try {
            double realSize = new double[]{1.5, 2.5, 3, 3.5}[-y + height];
            double distance = Math.sqrt(x * x + z * z) - realSize;
            return distance > 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    @Override
    public int func_225570_a_(int p_225570_1_, int p_225570_2_, int p_225570_3_, int r) {
        return r == 0 ? 0 : 1;
    }

    @Override
    public <T> T serialize(DynamicOps<T> p_218175_1_) {
        ImmutableMap.Builder<T, T> builder = ImmutableMap.builder();
        builder.put(p_218175_1_.createString("type"),
                p_218175_1_.createString(Registry.FOLIAGE_PLACER_TYPE.getKey(this.field_227383_c_).toString()))
                .put(p_218175_1_.createString("radius"), p_218175_1_.createInt(this.field_227381_a_))
                .put(p_218175_1_.createString("radius_random"), p_218175_1_.createInt(this.field_227382_b_))
                .put(p_218175_1_.createString("branch"), p_218175_1_.createInt(this.branchLength.getBase()))
                .put(p_218175_1_.createString("branch_random"), p_218175_1_.createInt(this.branchLength.getVariance()));
        return (new Dynamic<>(p_218175_1_, p_218175_1_.createMap(builder.build()))).getValue();
    }

}
