package de.deerangle.treemendous.tree.config;

import de.deerangle.treemendous.tree.Tree;
import de.deerangle.treemendous.tree.util.ITreeMaker;
import de.deerangle.treemendous.tree.util.WeightedTreeMaker;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SaplingConfigBuilder
{

    private final TreeConfigBuilder treeConfigBuilder;
    private final String variantName;
    private final List<WeightedTreeMaker> trees;
    private Function<Tree, Block> leaves;
    private Function<Tree, Block> wood;
    private Function<Tree, Block> sapling;

    public SaplingConfigBuilder(TreeConfigBuilder treeConfigBuilder, String variantName)
    {
        this.treeConfigBuilder = treeConfigBuilder;
        this.variantName = variantName;
        this.trees = new ArrayList<>();
    }

    public SaplingConfigBuilder setLeaves(Function<Tree, Block> leaves)
    {
        this.leaves = leaves;
        return this;
    }

    public SaplingConfigBuilder setWood(Function<Tree, Block> wood)
    {
        this.wood = wood;
        return this;
    }

    public SaplingConfigBuilder setSapling(Function<Tree, Block> sapling)
    {
        this.sapling = sapling;
        return this;
    }

    public SaplingConfigBuilder addTree(int weight, boolean isMega, ITreeMaker treeMaker)
    {
        this.trees.add(new WeightedTreeMaker(weight, isMega, treeMaker));
        return this;
    }

    public SaplingConfigBuilder addTree(ITreeMaker treeMaker)
    {
        return this.addTree(1, false, treeMaker);
    }

    public TreeConfigBuilder add()
    {
        this.treeConfigBuilder.addSapling(new SaplingConfig(this.variantName, this.leaves, this.wood, this.sapling, this.trees));
        return this.treeConfigBuilder;
    }

}
