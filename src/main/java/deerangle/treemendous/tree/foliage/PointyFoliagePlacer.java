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

public class PointyFoliagePlacer extends FoliagePlacer {
    public static final Codec<PointyFoliagePlacer> CODEC = RecordCodecBuilder
            .create((placerInstance) -> func_242830_b(placerInstance)
                    .and(FeatureSpread.func_242254_a(0, 100, 100).fieldOf("outward_speed")
                            .forGetter((inst) -> inst.outwardSpeedX100))
                    .and(FeatureSpread.func_242254_a(0, 100, 100).fieldOf("inward_speed")
                            .forGetter((inst) -> inst.inwardSpeedX100))
                    .and(FeatureSpread.func_242254_a(0, 16, 16).fieldOf("target_height")
                            .forGetter((inst) -> inst.bottomOffset)).apply(placerInstance, PointyFoliagePlacer::new));

    private final FeatureSpread outwardSpeedX100;
    private final FeatureSpread inwardSpeedX100;
    private final FeatureSpread bottomOffset;
    private float currentWidth;

    public PointyFoliagePlacer(FeatureSpread radius, FeatureSpread offset, FeatureSpread outwardSpeedX100, FeatureSpread inwardSpeedX100, FeatureSpread bottomOffset) {
        super(radius, offset);
        this.outwardSpeedX100 = outwardSpeedX100;
        this.inwardSpeedX100 = inwardSpeedX100;
        this.bottomOffset = bottomOffset;
    }

    @Override
    protected FoliagePlacerType<?> func_230371_a_() {
        return TreeRegistry.POINTY_FOLIAGE_PLACER;
    }

    @Override
    protected void func_230372_a_(IWorldGenerationReader worldGenerationReader, Random random, BaseTreeFeatureConfig featureConfig, int trunkHeight, Foliage foliage, int crownHeight, int radius, Set<BlockPos> p_230372_8_, int startY, MutableBoundingBox p_230372_10_) {
        float m = this.inwardSpeedX100.func_242259_a(random) * 0.01f;
        float n = this.outwardSpeedX100.func_242259_a(random) * 0.01f;
        float h = trunkHeight - this.bottomOffset.func_242259_a(random) + startY;
        float targetHeightN = m / (n + m) * h;
        float targetWidthAct = n * targetHeightN;

        int y = -startY;
        currentWidth = 0.0f;
        for (; currentWidth <= targetWidthAct; y++) {
            currentWidth += n;
            this.func_236753_a_(worldGenerationReader, random, featureConfig, foliage.func_236763_a_(),
                    (int) currentWidth + 1, p_230372_8_, -y, foliage.func_236765_c_(), p_230372_10_);
        }
        for (; currentWidth > 0; y++) {
            currentWidth -= m;
            this.func_236753_a_(worldGenerationReader, random, featureConfig, foliage.func_236763_a_(),
                    (int) currentWidth + 1, p_230372_8_, -y, foliage.func_236765_c_(), p_230372_10_);
        }
    }

    @Override
    public int func_230374_a_(Random p_230374_1_, int p_230374_2_, BaseTreeFeatureConfig p_230374_3_) {
        return 0;
    }

    @Override
    protected boolean func_230373_a_(Random rand, int x, int y, int z, int radius, boolean p_230373_6_) {
        double distance = Math.sqrt(x * x + z * z) - currentWidth;
        return distance > 0 || (!(distance / currentWidth < -0.15) && rand.nextFloat() < 0.3f);
    }

}
