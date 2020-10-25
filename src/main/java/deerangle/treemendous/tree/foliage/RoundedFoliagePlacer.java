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

public class RoundedFoliagePlacer extends FoliagePlacer {

    protected final int index;
    private static final float[][] rings = new float[][]{{1.2f, 2.0f}, {1.2f, 2.4f, 2.0f}, {1.3f, 2.4f, 3.4f, 3f}, {1.6f, 2.4f, 4f, 4f, 3.5f}, {2.0f, 3.2f, 4.5f, 4.5f, 3.7f, 2.6f}};
    private static final int[] sizes = new int[]{2, 3, 3, 3, 4};
    private static final int[] layers = new int[]{2, 3, 4, 5, 6};

    public RoundedFoliagePlacer(FeatureSpread radius, int index) {
        super(radius.getBase(), radius.getVariance(), TreeWorldGenRegistry.ROUNDED_FOLIAGE_PLACER);
        this.index = index;
    }

    public <T> RoundedFoliagePlacer(Dynamic<T> dyn) {
        this(FeatureSpread.fromDynamic(dyn, "radius"), dyn.get("index").asInt(0));
    }

    // generateLayers
    @Override
    public void func_225571_a_(IWorldGenerationReader worldGenerationReader, Random random, TreeFeatureConfig featureConfig, int startY, int trunk, int foliage, BlockPos pos, Set<BlockPos> resultingBlocks) {
        for (int i = 0; i < layers[index]; i++) {
            int ringSize = sizes[index];
            this.func_227384_a_(worldGenerationReader, random, featureConfig, startY, pos, startY - i, ringSize,
                    resultingBlocks);
        }
    }

    @Override
    public int func_225573_a_(Random p_225573_1_, int p_225573_2_, int p_225573_3_, TreeFeatureConfig p_225573_4_) {
        return layers[index];
    }

    // shouldPlaceAtPos
    @Override
    protected boolean func_225572_a_(Random rand, int height, int x, int y, int z, int size) {
        float realSize = rings[index][-y + height];
        double distance = Math.sqrt(x * x + z * z) - realSize;
        return distance > 0 || (!(distance / realSize < -0.1) && rand.nextFloat() < 0.1f);
    }

    @Override
    public int func_225570_a_(int p_225570_1_, int p_225570_2_, int p_225570_3_, int r) {
        return r == 0 ? 0 : 1;
    }

    @Override
    public <T> T serialize(DynamicOps<T> ops) {
        ImmutableMap.Builder<T, T> builder = ImmutableMap.builder();
        builder.put(ops.createString("type"),
                ops.createString(Registry.FOLIAGE_PLACER_TYPE.getKey(this.field_227383_c_).toString()))
                .put(ops.createString("radius"), ops.createInt(this.field_227381_a_))
                .put(ops.createString("radius_random"), ops.createInt(this.field_227382_b_))
                .put(ops.createString("index"), ops.createInt(this.index));
        return (new Dynamic<>(ops, ops.createMap(builder.build()))).getValue();
    }

}
