package de.deerangle.treemendous.world;

import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.PineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.OptionalInt;

public class BaseTreeMaker
{

    protected static ConfiguredFeature<TreeConfiguration, ?> makeBaseOakTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling)
    {
        return Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(wood, new StraightTrunkPlacer(4, 2, 0), leaves, sapling, new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build());
    }

    protected static ConfiguredFeature<TreeConfiguration, ?> makeBaseAcaciaTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling)
    {
        return Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(wood, new ForkingTrunkPlacer(5, 2, 2), leaves, sapling, new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build());
    }

    protected static ConfiguredFeature<TreeConfiguration, ?> makeBaseSpruceTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling)
    {
        return Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(wood, new StraightTrunkPlacer(5, 2, 1), leaves, sapling, new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build());
    }

    protected static ConfiguredFeature<TreeConfiguration, ?> makeBasePineTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling)
    {
        return Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(wood, new StraightTrunkPlacer(6, 4, 0), leaves, sapling, new PineFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), UniformInt.of(3, 4)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build());
    }

    protected static ConfiguredFeature<TreeConfiguration, ?> makeBaseFancyTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling)
    {
        return Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(wood, new FancyTrunkPlacer(3, 11, 0), leaves, sapling, new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().build());
    }

    protected static ConfiguredFeature<TreeConfiguration, ?> makeMegaTree(Block leaves, Block wood, Block sapling)
    {
        return makeMegaTree(new SimpleStateProvider(leaves.defaultBlockState()), new SimpleStateProvider(wood.defaultBlockState()), new SimpleStateProvider(sapling.defaultBlockState()));
    }

    protected static ConfiguredFeature<TreeConfiguration, ?> makeMegaTree(BlockStateProvider leaves, BlockStateProvider wood, BlockStateProvider sapling)
    {
        return Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(wood, new GiantTrunkPlacer(13, 2, 14), leaves, sapling, new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(13, 17)), new TwoLayersFeatureSize(1, 1, 2))).build());
    }

}
