package deerangle.treemendous.tree.foliage;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import deerangle.treemendous.util.FeatureSpread;
import deerangle.treemendous.world.TreeWorldGenRegistry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;

import java.util.Random;
import java.util.Set;

public class PointyFoliagePlacer extends FoliagePlacer {

    private final FeatureSpread outwardSpeedX100;
    private final FeatureSpread inwardSpeedX100;
    private final FeatureSpread bottomOffset;
    private float currentWidth;

    public PointyFoliagePlacer(FeatureSpread radius, FeatureSpread outwardSpeedX100, FeatureSpread inwardSpeedX100, FeatureSpread bottomOffset) {
        super(radius.getBase(), radius.getVariance(), TreeWorldGenRegistry.POINTY_FOLIAGE_PLACER);
        this.outwardSpeedX100 = outwardSpeedX100;
        this.inwardSpeedX100 = inwardSpeedX100;
        this.bottomOffset = bottomOffset;
    }

    public <T> PointyFoliagePlacer(Dynamic<T> p_i225847_1_) {
        this(FeatureSpread.fromDynamic(p_i225847_1_, "radius"),
                FeatureSpread.fromDynamic(p_i225847_1_, "outward_speed"),
                FeatureSpread.fromDynamic(p_i225847_1_, "inward_speed"),
                FeatureSpread.fromDynamic(p_i225847_1_, "target_height"));
    }

    @Override
    public void func_225571_a_(IWorldGenerationReader worldGenerationReader, Random random, TreeFeatureConfig featureConfig, int startY, int trunk, int foliage, BlockPos pos, Set<BlockPos> resultingBlocks) {
        float m = this.inwardSpeedX100.func_242259_a(random) * 0.01f;
        float n = this.outwardSpeedX100.func_242259_a(random) * 0.01f;
        float h = trunk - this.bottomOffset.func_242259_a(random) + startY + 2;
        float targetHeightN = m / (n + m) * h;
        float targetWidthAct = n * targetHeightN;

        int y = -startY - 2;
        currentWidth = 0.0f;
        for (; currentWidth <= targetWidthAct; y++) {
            currentWidth += n;
            this.func_227384_a_(worldGenerationReader, random, featureConfig, startY, pos, -y, (int) currentWidth + 1,
                    resultingBlocks);
        }
        for (; currentWidth > 0; y++) {
            currentWidth -= m;
            this.func_227384_a_(worldGenerationReader, random, featureConfig, startY, pos, -y, (int) currentWidth + 1,
                    resultingBlocks);
        }
    }

    @Override
    public int func_225573_a_(Random p_225573_1_, int p_225573_2_, int p_225573_3_, TreeFeatureConfig p_225573_4_) {
        return 0;
    }

    @Override
    protected boolean func_225572_a_(Random rand, int height, int x, int y, int z, int size) {
        double distance = Math.sqrt(x * x + z * z) - currentWidth;
        return distance > 0 || (!(distance / currentWidth < -0.15) && rand.nextFloat() < 0.3f);
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
                .put(p_218175_1_.createString("outward_speed"), p_218175_1_.createInt(this.outwardSpeedX100.getBase()))
                .put(p_218175_1_.createString("outward_speed_random"),
                        p_218175_1_.createInt(this.outwardSpeedX100.getVariance()))
                .put(p_218175_1_.createString("inward_speed"), p_218175_1_.createInt(this.inwardSpeedX100.getBase()))
                .put(p_218175_1_.createString("inward_speed_random"),
                        p_218175_1_.createInt(this.inwardSpeedX100.getVariance()))
                .put(p_218175_1_.createString("target_height"), p_218175_1_.createInt(this.bottomOffset.getBase()))
                .put(p_218175_1_.createString("target_height_random"),
                        p_218175_1_.createInt(this.bottomOffset.getVariance()));
        return (new Dynamic<>(p_218175_1_, p_218175_1_.createMap(builder.build()))).getValue();
    }
}
