package deerangle.treemendous.tree;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;
import java.util.function.Supplier;

public class CustomTree extends Tree {

    private final Supplier<ConfiguredFeature<TreeFeatureConfig, ?>> feature;

    public CustomTree(Supplier<ConfiguredFeature<TreeFeatureConfig, ?>> feature) {
        this.feature = feature;
    }

    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return feature.get();
    }
}
