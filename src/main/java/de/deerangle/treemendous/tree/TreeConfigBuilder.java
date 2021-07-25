package de.deerangle.treemendous.tree;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class TreeConfigBuilder {
    private final String registryName;
    private TreeTexture appearance;
    private ILeavesColor leavesColor;
    private Supplier<Item> appleItem;

    public TreeConfigBuilder(String registryName) {
        this.registryName = registryName;
        this.appleItem = () -> null;
        this.appearance = new TreeTexture(0x00ff00, 0xff0000, 0);
    }

    public TreeConfigBuilder setAppearance(TreeTexture appearance) {
        this.appearance = appearance;
        return this;
    }

    public TreeConfigBuilder setLeavesColor(ILeavesColor leavesColor) {
        this.leavesColor = leavesColor;
        return this;
    }

    public TreeConfigBuilder setLeavesColor(int leavesColor) {
        this.leavesColor = (BlockPos pos) -> leavesColor;
        return this;
    }

    public TreeConfigBuilder setAppleItem(Supplier<Item> appleItem) {
        this.appleItem = appleItem;
        return this;
    }

    public TreeConfig createTreeConfig() {
        return new TreeConfig(registryName, appearance, leavesColor, appleItem);
    }

}