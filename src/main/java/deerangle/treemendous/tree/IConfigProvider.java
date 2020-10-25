package deerangle.treemendous.tree;

import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public interface IConfigProvider {
    TreeFeatureConfig getConfig(Block log, Block leaves, Block sapling);
}
