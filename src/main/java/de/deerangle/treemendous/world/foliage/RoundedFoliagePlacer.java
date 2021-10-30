package de.deerangle.treemendous.world.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.deerangle.treemendous.tree.TreeFeatureRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.Random;
import java.util.function.BiConsumer;

public class RoundedFoliagePlacer extends FoliagePlacer
{
    public static final Codec<RoundedFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> foliagePlacerParts(instance).and(Codec.INT.fieldOf("index").forGetter(inst -> inst.index)).apply(instance, RoundedFoliagePlacer::new));
    private static final float[][] rings = new float[][]{{1.2f, 2.0f}, {1.2f, 2.4f, 2.0f}, {1.3f, 2.4f, 3.4f, 3f}, {1.6f, 2.4f, 4f, 4f, 3.5f}, {2.0f, 3.2f, 4.5f, 4.5f, 3.7f, 2.6f}};
    private static final int[] sizes = new int[]{2, 3, 3, 3, 4};
    private static final int[] layers = new int[]{2, 3, 4, 5, 6};

    private final int index;

    public RoundedFoliagePlacer(IntProvider radius, IntProvider offset, int roundedIndex)
    {
        super(radius, offset);
        this.index = roundedIndex;
    }

    @Override
    protected FoliagePlacerType<?> type()
    {
        return TreeFeatureRegistry.ROUNDED_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> blockConsumer, Random random, TreeConfiguration config, int p_161378_, FoliagePlacer.FoliageAttachment foliage, int foliageHeight, int foliageRadius, int offset)
    {
        for (int i = offset; i > offset - layers[index]; i--)
        {
            int ringSize = sizes[index];
            this.placeLeavesRow(reader, blockConsumer, random, config, foliage.pos(), ringSize, i, foliage.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(Random p_68568_, int p_68569_, TreeConfiguration p_68570_)
    {
        return layers[index];
    }

    @Override
    protected boolean shouldSkipLocation(Random rand, int x, int y, int z, int p_68566_, boolean p_68567_)
    {
        float realSize = rings[index][-y];
        double distance = Math.sqrt(x * x + z * z) - realSize;
        return distance > 0 || (!(distance / realSize < -0.1) && rand.nextFloat() < 0.1f);
    }
}
