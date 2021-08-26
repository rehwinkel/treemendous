package de.deerangle.treemendous.tree;

import com.google.common.collect.ImmutableList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RegisteredTree implements IForgeRegistryEntry<RegisteredTree> {

    private final Tree tree;
    private ResourceLocation registryName;

    public RegisteredTree(Tree tree) {
        this.tree = tree;
        this.registryName = null;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return this.registryName;
    }

    @Override
    public RegisteredTree setRegistryName(ResourceLocation name) {
        this.registryName = name;
        return this;
    }

    @Override
    public Class<RegisteredTree> getRegistryType() {
        return RegisteredTree.class;
    }

    public Tree getTree() {
        return tree;
    }

    public Collection<Block> getAllBlocks() {
        List<Block> mostTreeBlocks = ImmutableList.of(this.getTree().getPlanks(),
                this.getTree().getStrippedLog(),
                this.getTree().getLog(),
                this.getTree().getStrippedWood(),
                this.getTree().getWood(),
                this.getTree().getStairs(),
                this.getTree().getSlab(),
                this.getTree().getPressurePlate(),
                this.getTree().getButton(),
                this.getTree().getFence(),
                this.getTree().getFenceGate(),
                this.getTree().getDoor(),
                this.getTree().getTrapdoor(),
                this.getTree().getLeaves(),
                this.getTree().getSign(),
                this.getTree().getWallSign(),
                this.getTree().getChest(),
                this.getTree().getCraftingTable());
        List<Block> blocks = new ArrayList<>(mostTreeBlocks);
        for (int i = 0; i < this.getTree().getSaplings(); i++) {
            blocks.add(this.getTree().getSapling(i));
            blocks.add(this.getTree().getPottedSapling(i));
        }
        return blocks;
    }

}
