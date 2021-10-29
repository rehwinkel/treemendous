package de.deerangle.treemendous.world;

import com.google.common.collect.ImmutableList;
import de.deerangle.treemendous.tree.Tree;
import de.deerangle.treemendous.tree.config.SaplingConfig;
import de.deerangle.treemendous.tree.util.WeightedTreeMaker;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class TreemendousTreeGrower extends AbstractMegaTreeGrower
{

    private final SaplingConfig saplingConfig;
    private final Tree tree;
    private final boolean hasMegaVariant;
    private List<ConfiguredFeature<TreeConfiguration, ?>> madeTrees;
    private List<ConfiguredFeature<TreeConfiguration, ?>> madeMegaTrees;

    public TreemendousTreeGrower(SaplingConfig saplingConfig, Tree tree)
    {
        this.saplingConfig = saplingConfig;
        this.tree = tree;
        this.madeTrees = null;
        this.madeMegaTrees = null;
        this.hasMegaVariant = saplingConfig.getTreeMakers().stream().anyMatch(WeightedTreeMaker::mega);
    }

    @SuppressWarnings("NullableProblems")
    public boolean growTree(ServerLevel world, ChunkGenerator generator, BlockPos pos, BlockState state, Random random)
    {
        if (this.hasMegaVariant)
        {
            for (int i = 0; i >= -1; --i)
            {
                for (int j = 0; j >= -1; --j)
                {
                    if (isTwoByTwoSapling(state, world, pos, i, j))
                    {
                        return this.placeMega(world, generator, pos, state, random, i, j);
                    }
                }
            }
        }

        return this.growSmallTree(world, generator, pos, state, random);
    }

    public boolean growSmallTree(ServerLevel world, ChunkGenerator generator, BlockPos pos, BlockState state, Random rand)
    {
        ConfiguredFeature<TreeConfiguration, ?> configuredFeature = this.getConfiguredFeature(rand, this.hasFlowers(world, pos));
        if (configuredFeature == null)
        {
            return false;
        } else
        {
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 4);
            if (configuredFeature.place(world, generator, rand, pos))
            {
                return true;
            } else
            {
                world.setBlock(pos, state, 4);
                return false;
            }
        }
    }

    private boolean hasFlowers(LevelAccessor world, BlockPos pos)
    {
        for (BlockPos blockpos : BlockPos.MutableBlockPos.betweenClosed(pos.below().north(2).west(2), pos.above().south(2).east(2)))
        {
            if (world.getBlockState(blockpos).is(BlockTags.FLOWERS))
            {
                return true;
            }
        }
        return false;
    }

    private void makeAllTrees()
    {
        List<ConfiguredFeature<TreeConfiguration, ?>> madeTrees = new ArrayList<>();
        List<ConfiguredFeature<TreeConfiguration, ?>> madeMegaTrees = new ArrayList<>();
        for (WeightedTreeMaker maker : saplingConfig.getTreeMakers())
        {
            ConfiguredFeature<TreeConfiguration, ?> madeTree = saplingConfig.makeTree(maker.treeMaker(), tree);
            List<ConfiguredFeature<TreeConfiguration, ?>> outputList;
            if (maker.mega())
            {
                outputList = madeMegaTrees;
            } else
            {
                outputList = madeTrees;
            }
            IntStream.range(0, maker.weight()).forEach(x -> outputList.add(madeTree));
        }
        this.madeTrees = ImmutableList.copyOf(madeTrees);
        this.madeMegaTrees = ImmutableList.copyOf(madeMegaTrees);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random random, boolean b)
    {
        if (madeTrees == null)
        {
            this.makeAllTrees();
        }
        return madeTrees.get(random.nextInt(madeTrees.size()));
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredMegaFeature(Random random)
    {
        if (madeMegaTrees == null)
        {
            this.makeAllTrees();
        }
        return madeMegaTrees.get(random.nextInt(madeMegaTrees.size()));
    }

}
