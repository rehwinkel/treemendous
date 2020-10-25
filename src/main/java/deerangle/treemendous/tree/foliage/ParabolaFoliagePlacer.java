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


public class ParabolaFoliagePlacer extends FoliagePlacer {

    private final int width;
    private final int height;

    public ParabolaFoliagePlacer(FeatureSpread radius, int width, int height) {
        super(radius.getBase(), radius.getVariance(), TreeWorldGenRegistry.PARABOLA_FOLIAGE_PLACER);
        this.width = width;
        this.height = height;
    }

    public <T> ParabolaFoliagePlacer(Dynamic<T> dyn) {
        this(FeatureSpread.fromDynamic(dyn, "radius"), dyn.get("width").asInt(0), dyn.get("height").asInt(0));
    }

    public void func_225571_a_(IWorldGenerationReader p_225571_1_, Random p_225571_2_, TreeFeatureConfig p_225571_3_, int p_225571_4_, int p_225571_5_, int p_225571_6_, BlockPos p_225571_7_, Set<BlockPos> p_225571_8_) {
        for (int i = p_225571_4_; i >= p_225571_5_; --i) {
            int j = Math.max(p_225571_6_ - 1 - (i - p_225571_4_) / 2, 0);
            this.func_227384_a_(p_225571_1_, p_225571_2_, p_225571_3_, p_225571_4_, p_225571_7_, i, j, p_225571_8_);
        }

    }

    private double getSize(double x) {
        return Math.sqrt(x) * Math.sqrt(this.width * this.width / (double) this.height);
    }

    public int func_225573_a_(Random p_225573_1_, int p_225573_2_, int p_225573_3_, TreeFeatureConfig p_225573_4_) {
        return this.field_227381_a_ + p_225573_1_.nextInt(this.field_227382_b_ + 1);
    }

    protected boolean func_225572_a_(Random p_225572_1_, int x, int y, int z, int p_225572_5_, int p_225572_6_) {
        double dist = Math.sqrt(x * x + z * z);
        double realSize = getSize(1.5 - y);
        return dist > realSize;//x == size && z == size && (random.nextInt(2) == 0 || y == 0);
    }

    public int func_225570_a_(int p_225570_1_, int p_225570_2_, int p_225570_3_, int p_225570_4_) {
        return p_225570_4_ == 0 ? 0 : 1;
    }

    @Override
    public <T> T serialize(DynamicOps<T> ops) {
        ImmutableMap.Builder<T, T> builder = ImmutableMap.builder();
        builder.put(ops.createString("type"),
                ops.createString(Registry.FOLIAGE_PLACER_TYPE.getKey(this.field_227383_c_).toString()))
                .put(ops.createString("radius"), ops.createInt(this.field_227381_a_))
                .put(ops.createString("radius_random"), ops.createInt(this.field_227382_b_))
                .put(ops.createString("width"), ops.createInt(this.width))
                .put(ops.createString("height"), ops.createInt(this.height));
        return (new Dynamic<>(ops, ops.createMap(builder.build()))).getValue();
    }

}
