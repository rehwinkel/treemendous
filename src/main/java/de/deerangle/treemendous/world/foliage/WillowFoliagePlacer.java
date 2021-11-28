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

public class WillowFoliagePlacer extends FoliagePlacer
{

    public static final Codec<WillowFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> foliagePlacerParts(instance).and(IntProvider.codec(0, 8).fieldOf("branch_length").forGetter((inst) -> inst.branchLength)).apply(instance, WillowFoliagePlacer::new));
    private static final double[] foliageRadii = new double[]{1.5, 2.5, 3, 3.5};

    private final IntProvider branchLength;

    public WillowFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider branchLength)
    {
        super(radius, offset);
        this.branchLength = branchLength;
    }

    @Override
    protected FoliagePlacerType<?> type()
    {
        return TreeFeatureRegistry.WILLOW_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> blockConsumer, Random random, TreeConfiguration config, int p_161426_, FoliageAttachment foliage, int height, int radius, int offset)
    {
        for (int i = offset; i > offset - 4; i--)
        {
            int size = Math.min((offset - i) + 1, 3);
            this.placeLeavesRow(reader, blockConsumer, random, config, foliage.pos(), size, i, foliage.doubleTrunk());
        }

        growBranchDown(2, offset - 4, 2, branchLength.sample(random), reader, blockConsumer, random, config, foliage);
        growBranchDown(-2, offset - 4, -2, branchLength.sample(random), reader, blockConsumer, random, config, foliage);
        growBranchDown(-2, offset - 4, 2, branchLength.sample(random), reader, blockConsumer, random, config, foliage);
        growBranchDown(2, offset - 4, -2, branchLength.sample(random), reader, blockConsumer, random, config, foliage);
        growBranchDown(3, offset - 4, 0, branchLength.sample(random), reader, blockConsumer, random, config, foliage);
        growBranchDown(-3, offset - 4, 0, branchLength.sample(random), reader, blockConsumer, random, config, foliage);
        growBranchDown(0, offset - 4, 3, branchLength.sample(random), reader, blockConsumer, random, config, foliage);
        growBranchDown(0, offset - 4, -3, branchLength.sample(random), reader, blockConsumer, random, config, foliage);
    }

    private void growBranchDown(int x, int y, int z, int branchLength, LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> blockConsumer, Random random, TreeConfiguration config, FoliageAttachment foliage)
    {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        for (int i = 0; i < branchLength; i++)
        {
            mutablePos.setWithOffset(foliage.pos(), x, y - i, z);
            tryPlaceLeaf(reader, blockConsumer, random, config, mutablePos);
        }
    }

    @Override
    public int foliageHeight(Random p_68568_, int p_68569_, TreeConfiguration p_68570_)
    {
        return 6;
    }

    @Override
    protected boolean shouldSkipLocation(Random p_68562_, int x, int y, int z, int p_68566_, boolean p_68567_)
    {
        double realSize = foliageRadii[-y];
        double distance = Math.sqrt(x * x + z * z) - realSize;
        return distance > 0;
    }

}
