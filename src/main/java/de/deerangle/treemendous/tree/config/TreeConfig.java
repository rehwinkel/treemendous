package de.deerangle.treemendous.tree.config;

import de.deerangle.treemendous.tree.util.ILeavesColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.MaterialColor;

import java.util.List;
import java.util.function.Supplier;

public record TreeConfig(String registryName, ILeavesColor leavesColor, Supplier<Item> appleItem,
                         MaterialColor woodMaterialColor, MaterialColor barkMaterialColor,
                         List<SaplingConfig> saplingConfigs)
{
}
