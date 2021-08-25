package de.deerangle.treemendous.tree;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.MaterialColor;

import java.util.function.Supplier;

public class TreeConfigBuilder {

    private final String registryName;
    private final ILeavesColor leavesColor;
    private final MaterialColor woodColor;
    private final MaterialColor barkColor;
    private Supplier<Item> appleItem;

    public TreeConfigBuilder(String registryName, ILeavesColor leavesColor, MaterialColor woodColor, MaterialColor barkColor) {
        this.registryName = registryName;
        this.leavesColor = leavesColor;
        this.woodColor = woodColor;
        this.barkColor = barkColor;
        this.appleItem = () -> null;
    }

    public TreeConfigBuilder(String registryName, int leavesColor, int woodColor, int barkColor) {
        this.registryName = registryName;
        this.leavesColor = new FixedLeavesColor(leavesColor);
        this.woodColor = Util.getMaterialColor(woodColor);
        this.barkColor = Util.getMaterialColor(barkColor);
        this.appleItem = () -> null;
    }

    public TreeConfigBuilder setAppleItem(Supplier<Item> appleItem) {
        this.appleItem = appleItem;
        return this;
    }

    public TreeConfig createTreeConfig() {
        return new TreeConfig(registryName, leavesColor, appleItem, woodColor, barkColor);
    }

}