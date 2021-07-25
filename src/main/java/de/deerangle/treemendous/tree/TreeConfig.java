package de.deerangle.treemendous.tree;

import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public record TreeConfig(String registryName, TreeTexture appearance, ILeavesColor leavesColor,
                         Supplier<Item> appleItem) {
}
