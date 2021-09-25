package de.deerangle.treemendous.tree.util;

import net.minecraft.core.BlockPos;

public interface ILeavesColor {

    int getColor(BlockPos pos);

    boolean isBiomeDependent();

}
