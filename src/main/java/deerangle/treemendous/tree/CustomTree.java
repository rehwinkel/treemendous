package deerangle.treemendous.tree;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Random;
import java.util.function.Supplier;

public class CustomTree extends Tree {

    private final Supplier<ConfiguredFeature<BaseTreeFeatureConfig, ?>> feature;

    public CustomTree(Supplier<ConfiguredFeature<BaseTreeFeatureConfig, ?>> feature) {
        this.feature = feature;
    }

    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return feature.get();
    }

}
