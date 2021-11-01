package de.deerangle.treemendous.world.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.deerangle.treemendous.tree.TreeFeatureRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.Random;
import java.util.function.BiConsumer;

public class BallFoliagePlacer extends FoliagePlacer
{
    public static final Codec<BallFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> instance
            .group(IntProvider.codec(0, 8).fieldOf("size").forGetter(inst -> inst.size))
            .apply(instance, BallFoliagePlacer::new));

    private final IntProvider size;

    public BallFoliagePlacer(IntProvider ballSize)
    {
        super(ballSize, ConstantInt.of(0));
        this.size = ballSize;
    }

    @Override
    protected FoliagePlacerType<?> type()
    {
        return TreeFeatureRegistry.BALL_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> consumer, Random random, TreeConfiguration configuration, int p_161364_, FoliagePlacer.FoliageAttachment foliageAttachment, int height, int radius, int offset)
    {
        int size = this.size.sample(random);
        for (int i = offset + size; i > offset - size * 2; --i)
        {
            int layerSize = size + foliageAttachment.radiusOffset();
            this.placeLeavesRow(level, consumer, random, configuration, foliageAttachment.pos(), layerSize, i, foliageAttachment.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(Random p_68568_, int p_68569_, TreeConfiguration p_68570_)
    {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(Random random, int x, int y, int z, int size, boolean p_68567_)
    {
        double sqrt = Math.sqrt(x * x + y * y + z * z);
        return sqrt > size || (sqrt > size * 0.9 && random.nextBoolean());
    }

}
