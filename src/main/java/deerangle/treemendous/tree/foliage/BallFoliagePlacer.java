package deerangle.treemendous.tree.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import deerangle.treemendous.main.TreeRegistry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

import java.util.Random;
import java.util.Set;

public class BallFoliagePlacer extends FoliagePlacer {
    public static final Codec<BallFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> instance
            .group(FeatureSpread.func_242254_a(0, 8, 8).fieldOf("size").forGetter(inst -> inst.size))
            .apply(instance, BallFoliagePlacer::new));

    private final FeatureSpread size;

    public BallFoliagePlacer(FeatureSpread ballSize) {
        super(ballSize, FeatureSpread.func_242252_a(0));
        this.size = ballSize;
    }

    @Override
    protected FoliagePlacerType<?> func_230371_a_() {
        return TreeRegistry.BALL_FOLIAGE_PLACER;
    }

    @Override
    protected void func_230372_a_(IWorldGenerationReader worldGenerationReader, Random random, BaseTreeFeatureConfig p_230372_3_, int trunkHeight, Foliage p_230372_5_, int crownOffset, int p_230372_7_, Set<BlockPos> p_230372_8_, int startY, MutableBoundingBox p_230372_10_) {
        int size = this.size.func_242259_a(random);
        for (int i = size; i >= -size; --i) {
            this.func_236753_a_(worldGenerationReader, random, p_230372_3_, p_230372_5_.func_236763_a_(), size,
                    p_230372_8_, i, p_230372_5_.func_236765_c_(), p_230372_10_);
        }
    }

    @Override
    public int func_230374_a_(Random random, int i, BaseTreeFeatureConfig featureConfig) {
        return 0;
    }

    @Override
    protected boolean func_230373_a_(Random random, int x, int y, int z, int size, boolean p_230373_6_) {
        double sqrt = Math.sqrt(x * x + y * y + z * z);
        return sqrt > size || (sqrt > size * 0.9 && random.nextBoolean());
    }
}
