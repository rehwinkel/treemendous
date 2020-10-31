package deerangle.treemendous.tree;

import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public interface IConfigProvider<C extends TreeFeatureConfig> {
    C getConfig(Block log, Block leaves, Block sapling);
}
