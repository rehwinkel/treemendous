package de.deerangle.treemendous.tree;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.List;
import java.util.function.Function;

public record SaplingConfig(String variantName,
                            Function<Tree, Block> leaves,
                            Function<Tree, Block> wood,
                            Function<Tree, Block> sapling,
                            List<WeightedTreeMaker> trees) {

    public Block getWood(Tree tree) {
        return wood.apply(tree);
    }

    public Block getLeaves(Tree tree) {
        return leaves.apply(tree);
    }

    public Block getSapling(Tree tree) {
        return sapling.apply(tree);
    }

    public List<WeightedTreeMaker> getTreeMakers() {
        return trees;
    }

    public ConfiguredFeature<TreeConfiguration, ?> makeTree(ITreeMaker treeMaker, Tree tree) {
        return treeMaker.getTree(this.getLeaves(tree), this.getWood(tree), this.getSapling(tree), tree);
    }

}
