package de.deerangle.treemendous.world.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.deerangle.treemendous.tree.TreeFeatureRegistry;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.Random;

public class ParabolicFoliagePlacer extends BlobFoliagePlacer
{
    public static final Codec<ParabolicFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> blobParts(instance).and(Codec.intRange(0, 10).fieldOf("width").forGetter(inst -> inst.width)).apply(instance, ParabolicFoliagePlacer::new));

    private final int width;

    public ParabolicFoliagePlacer(IntProvider radius, IntProvider offset, int height, int width)
    {
        super(radius, offset, height);
        this.width = width;
    }

    @Override
    protected FoliagePlacerType<?> type()
    {
        return TreeFeatureRegistry.PARABOLIC_FOLIAGE_PLACER.get();
    }

    private double getSize(double x)
    {
        return Math.sqrt(x) * Math.sqrt(width * width / (double) this.height);
    }

    protected boolean shouldSkipLocation(Random random, int x, int y, int z, int size, boolean b)
    {
        double dist = Math.sqrt(x * x + z * z);
        double realSize = getSize(1.5 - y);
        return dist > realSize;
    }

}
