package de.deerangle.treemendous.world;

import com.google.common.collect.ImmutableList;
import de.deerangle.treemendous.tree.SaplingConfig;
import de.deerangle.treemendous.tree.Tree;
import de.deerangle.treemendous.tree.WeightedTreeMaker;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class TreemendousTreeGrower extends AbstractTreeGrower {

    private final SaplingConfig saplingConfig;
    private final Tree tree;
    private List<ConfiguredFeature<TreeConfiguration, ?>> madeTrees;

    public TreemendousTreeGrower(SaplingConfig saplingConfig, Tree tree) {
        this.saplingConfig = saplingConfig;
        this.tree = tree;
        this.madeTrees = null;
    }

    private List<ConfiguredFeature<TreeConfiguration, ?>> makeAllTrees() {
        List<ConfiguredFeature<TreeConfiguration, ?>> madeTrees = new ArrayList<>();
        for (WeightedTreeMaker maker : saplingConfig.getTreeMakers()) {
            ConfiguredFeature<TreeConfiguration, ?> madeTree = saplingConfig.makeTree(maker.treeMaker(), tree);
            IntStream.range(0, maker.weight()).forEach(x -> madeTrees.add(madeTree));
        }
        return ImmutableList.copyOf(madeTrees);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random random, boolean b) {
        if (madeTrees == null) {
            madeTrees = this.makeAllTrees(); //TODO: can I do this earlier?
        }
        return madeTrees.get(random.nextInt(madeTrees.size()));
    }

}
