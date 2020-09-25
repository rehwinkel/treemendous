package deerangle.treemendous.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import deerangle.treemendous.main.TreeRegistry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

import java.util.Random;
import java.util.Set;

public class WillowFoliagePlacer extends FoliagePlacer {
    public static final Codec<WillowFoliagePlacer> CODEC = RecordCodecBuilder
            .create((placerInstance) -> func_242830_b(placerInstance)
                    .and(FeatureSpread.func_242254_a(0, 8, 8).fieldOf("branch_lengeth")
                            .forGetter((inst) -> inst.branchLength)).apply(placerInstance, WillowFoliagePlacer::new));

    private final FeatureSpread branchLength;

    public WillowFoliagePlacer(FeatureSpread radius, FeatureSpread offset, FeatureSpread branchLength) {
        super(radius, offset);
        this.branchLength = branchLength;
    }

    @Override
    protected FoliagePlacerType<?> func_230371_a_() {
        return TreeRegistry.WILLOW_FOLIAGE_PLACER;
    }

    @Override
    protected void func_230372_a_(IWorldGenerationReader worldGenerationReader, Random random, BaseTreeFeatureConfig featureConfig, int p_230372_4_, Foliage foliage, int offset, int radius, Set<BlockPos> resultingBlocks, int startY, MutableBoundingBox boundingBox) {
        for (int i = 0; i < 4; i++) {
            this.func_236753_a_(worldGenerationReader, random, featureConfig, foliage.func_236763_a_(),
                    Math.min(i + 1, 3), resultingBlocks, startY - i, foliage.func_236765_c_(), boundingBox);
        }

        growBranchDown(2, startY - 4, 2, branchLength.func_242259_a(random), worldGenerationReader, random,
                featureConfig, foliage, resultingBlocks, boundingBox);
        growBranchDown(-2, startY - 4, -2, branchLength.func_242259_a(random), worldGenerationReader, random,
                featureConfig, foliage, resultingBlocks, boundingBox);
        growBranchDown(-2, startY - 4, 2, branchLength.func_242259_a(random), worldGenerationReader, random,
                featureConfig, foliage, resultingBlocks, boundingBox);
        growBranchDown(2, startY - 4, -2, branchLength.func_242259_a(random), worldGenerationReader, random,
                featureConfig, foliage, resultingBlocks, boundingBox);
        growBranchDown(3, startY - 4, 0, branchLength.func_242259_a(random), worldGenerationReader, random,
                featureConfig, foliage, resultingBlocks, boundingBox);
        growBranchDown(-3, startY - 4, 0, branchLength.func_242259_a(random), worldGenerationReader, random,
                featureConfig, foliage, resultingBlocks, boundingBox);
        growBranchDown(0, startY - 4, 3, branchLength.func_242259_a(random), worldGenerationReader, random,
                featureConfig, foliage, resultingBlocks, boundingBox);
        growBranchDown(0, startY - 4, -3, branchLength.func_242259_a(random), worldGenerationReader, random,
                featureConfig, foliage, resultingBlocks, boundingBox);
    }

    private void growBranchDown(int x, int y, int z, int length, IWorldGenerationReader worldGenerationReader, Random random, BaseTreeFeatureConfig featureConfig, Foliage foliage, Set<BlockPos> resultingBlocks, MutableBoundingBox boundingBox) {
        BlockPos.Mutable mutablePos = new BlockPos.Mutable();
        for (int i = 0; i < length; i++) {
            mutablePos.setAndOffset(foliage.func_236763_a_(), x, y - i, z);
            if (TreeFeature.isReplaceableAt(worldGenerationReader, mutablePos)) {
                worldGenerationReader
                        .setBlockState(mutablePos, featureConfig.leavesProvider.getBlockState(random, mutablePos), 19);
                boundingBox.expandTo(new MutableBoundingBox(mutablePos, mutablePos));
                resultingBlocks.add(mutablePos.toImmutable());
            }
        }
    }

    @Override
    public int func_230374_a_(Random p_230374_1_, int p_230374_2_, BaseTreeFeatureConfig p_230374_3_) {
        return 5;
    }

    @Override
    protected boolean func_230373_a_(Random rand, int x, int y, int z, int p_230373_5_, boolean p_230373_6_) {
        double realSize = new double[]{1.5, 2.5, 3, 3.5}[-y];
        double distance = Math.sqrt(x * x + z * z) - realSize;
        return distance > 0;
    }
}
