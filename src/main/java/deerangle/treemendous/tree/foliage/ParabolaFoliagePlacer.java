package deerangle.treemendous.tree.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import deerangle.treemendous.world.TreeWorldGenRegistry;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

import java.util.Random;

public class ParabolaFoliagePlacer extends BlobFoliagePlacer {
    public static final Codec<ParabolaFoliagePlacer> CODEC = RecordCodecBuilder
            .create((instance) -> func_236740_a_(instance)
                    .and(Codec.intRange(0, 10).fieldOf("width").forGetter(inst -> inst.width))
                    .apply(instance, ParabolaFoliagePlacer::new));

    private final int width;

    public ParabolaFoliagePlacer(FeatureSpread radius, FeatureSpread offset, int atHeight, int width) {
        super(radius, offset, atHeight);
        this.width = width;
    }

    protected FoliagePlacerType<?> func_230371_a_() {
        return TreeWorldGenRegistry.PARABOLA_FOLIAGE_PLACER;
    }

    private double getSize(double x, double width, double atHeight) {
        return Math.sqrt(x) * Math.sqrt(width * width / atHeight);
    }

    private double getSize(double x) {
        return Math.sqrt(x) * Math.sqrt(width * width / (double) this.field_236739_b_);
    }

    protected boolean func_230373_a_(Random random, int x, int y, int z, int size, boolean b) {
        double dist = Math.sqrt(x * x + z * z);
        double realSize = getSize(1.5 - y);
        return dist > realSize;//x == size && z == size && (random.nextInt(2) == 0 || y == 0);
    }
}
