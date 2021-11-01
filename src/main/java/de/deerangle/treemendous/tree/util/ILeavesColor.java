package de.deerangle.treemendous.tree.util;

import net.minecraft.core.BlockPos;

public interface ILeavesColor
{

    int getColor(BlockPos pos);

    default int getColor()
    {
        return getColor(BlockPos.ZERO);
    }

    boolean isBiomeDependent();

}
