package de.deerangle.treemendous.world;

import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;

public class TreeMaker {

    public static ConfiguredFeature<TreeConfiguration, ?> makeSomeTree(Block leaves, Block wood, Block sapling) {
        return TreeMaker.makeSomeTree(new SimpleStateProvider(leaves.defaultBlockState()), new SimpleStateProvider(wood.defaultBlockState()), new SimpleStateProvider(sapling.defaultBlockState()));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeSomeTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling) {
        return Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(wood, new ForkingTrunkPlacer(5, 2, 2), leaves, sapling, new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build());
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeMegaTree(Block leaves, Block wood, Block sapling) {
        return TreeMaker.makeMegaTree(new SimpleStateProvider(leaves.defaultBlockState()), new SimpleStateProvider(wood.defaultBlockState()), new SimpleStateProvider(sapling.defaultBlockState()));
    }

    public static ConfiguredFeature<TreeConfiguration, ?> makeMegaTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling) {
        return Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(wood, new GiantTrunkPlacer(13, 2, 14), leaves, sapling, new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(13, 17)), new TwoLayersFeatureSize(1, 1, 2))).build());
    }

}
