package deerangle.treemendous.tree.foliage;

import com.mojang.datafixers.Dynamic;
import deerangle.treemendous.util.FeatureSpread;
import deerangle.treemendous.world.TreeWorldGenRegistry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;

import java.util.Random;
import java.util.Set;

public class BallFoliagePlacer extends FoliagePlacer {

    private final FeatureSpread size;

    public BallFoliagePlacer(FeatureSpread ballSize) {
        super(ballSize.getBase(), ballSize.getVariance(), TreeWorldGenRegistry.BALL_FOLIAGE_PLACER);
        this.size = ballSize;
    }

    public <T> BallFoliagePlacer(Dynamic<T> dyn) {
        this(FeatureSpread.fromDynamic(dyn, "radius"));
    }

    @Override
    public void func_225571_a_(IWorldGenerationReader worldGenerationReader, Random random, TreeFeatureConfig featureConfig, int startY, int trunk, int foliage, BlockPos pos, Set<BlockPos> resultingBlocks) {
        int size = this.size.func_242259_a(random);
        for (int i = size; i >= -size; --i) {
            this.func_227384_a_(worldGenerationReader, random, featureConfig, startY, pos, i, size, resultingBlocks);
        }
    }

    @Override
    public int func_225573_a_(Random p_225573_1_, int p_225573_2_, int p_225573_3_, TreeFeatureConfig p_225573_4_) {
        return 0;
    }

    @Override
    public int func_225570_a_(int p_225570_1_, int p_225570_2_, int p_225570_3_, int r) {
        return r == 0 ? 0 : 1;
    }

    @Override
    protected boolean func_225572_a_(Random random, int x, int y, int z, int size, int p_225572_6_) {
        double sqrt = Math.sqrt(x * x + y * y + z * z);
        return sqrt > size || (sqrt > size * 0.9 && random.nextBoolean());
    }
}
