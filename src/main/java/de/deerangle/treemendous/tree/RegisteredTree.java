package de.deerangle.treemendous.tree;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;

public class RegisteredTree implements IForgeRegistryEntry<RegisteredTree> {

    private final Tree tree;
    private ResourceLocation registryName;

    public RegisteredTree(Tree tree) {
        this.tree = tree;
        this.registryName = null;
    }

    @Override
    public RegisteredTree setRegistryName(ResourceLocation name) {
        this.registryName = name;
        return this;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return this.registryName;
    }

    @Override
    public Class<RegisteredTree> getRegistryType() {
        return RegisteredTree.class;
    }

    public Tree getTree() {
        return tree;
    }

}
