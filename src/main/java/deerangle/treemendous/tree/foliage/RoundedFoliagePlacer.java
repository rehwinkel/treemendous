package deerangle.treemendous.tree.foliage;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import deerangle.treemendous.world.TreeWorldGenRegistry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

import java.util.Random;
import java.util.Set;

public class RoundedFoliagePlacer extends FoliagePlacer {
    public static final Codec<RoundedFoliagePlacer> CODEC = RecordCodecBuilder
            .create((placerInstance) -> addHeightToCodec(placerInstance)
                    .apply(placerInstance, RoundedFoliagePlacer::new));
    protected final int index;
    float[][] rings = new float[][]{{1.2f, 2.0f}, {1.2f, 2.4f, 2.0f}, {1.3f, 2.4f, 3.4f, 3f}, {1.6f, 2.4f, 4f, 4f, 3.5f}, {2.0f, 3.2f, 4.5f, 4.5f, 3.7f, 2.6f}};
    int[] sizes = new int[]{2, 3, 3, 3, 4};
    int[] layers = new int[]{2, 3, 4, 5, 6};

    public RoundedFoliagePlacer(FeatureSpread radius, FeatureSpread offset, int index) {
        super(radius, offset);
        this.index = index;
    }

    protected static <P extends RoundedFoliagePlacer> Products.P3<RecordCodecBuilder.Mu<P>, FeatureSpread, FeatureSpread, Integer> addHeightToCodec(RecordCodecBuilder.Instance<P> placerInstance) {
        return func_242830_b(placerInstance)
                .and(Codec.intRange(0, 5).fieldOf("height").forGetter((instance) -> instance.index));
    }

    @Override
    protected FoliagePlacerType<?> func_230371_a_() {
        return TreeWorldGenRegistry.ROUNDED_FOLIAGE_PLACER;
    }

    // generateLayers
    @Override
    protected void func_230372_a_(IWorldGenerationReader worldGenerationReader, Random random, BaseTreeFeatureConfig baseConfig, int p_230372_4_, Foliage foliage, int height, int width, Set<BlockPos> usedPositions, int startY, MutableBoundingBox bounds) {
        for (int i = 0; i < this.layers[index]; i++) {
            int ringSize = this.sizes[index];
            this.func_236753_a_(worldGenerationReader, random, baseConfig, foliage.func_236763_a_(), ringSize,
                    usedPositions, startY - i, foliage.func_236765_c_(), bounds);
        }
    }

    @Override
    public int func_230374_a_(Random p_230374_1_, int p_230374_2_, BaseTreeFeatureConfig p_230374_3_) {
        return this.layers[index];
    }

    // shouldPlaceAtPos
    @Override
    protected boolean func_230373_a_(Random rand, int x, int y, int z, int size, boolean p_230373_6_) {
        float realSize = this.rings[index][-y];
        double distance = Math.sqrt(x * x + z * z) - realSize;
        return distance > 0 || (!(distance / realSize < -0.1) && rand.nextFloat() < 0.1f);
    }

}
