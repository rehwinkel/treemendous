package de.deerangle.treemendous.tree;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public interface ITreeMaker {

    ConfiguredFeature<TreeConfiguration, ?> getTree(Block leaves, Block wood, Block sapling, Tree tree);

}
