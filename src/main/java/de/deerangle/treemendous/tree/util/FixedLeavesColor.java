package de.deerangle.treemendous.tree.util;

import net.minecraft.core.BlockPos;

public class FixedLeavesColor implements ILeavesColor
{

    private final int color;
    private final boolean biomeDependent;

    public FixedLeavesColor(int leavesColor, boolean biomeDependent)
    {
        this.color = leavesColor;
        this.biomeDependent = biomeDependent;
    }

    @Override
    public int getColor(BlockPos pos)
    {
        return color;
    }

    @Override
    public boolean isBiomeDependent()
    {
        return this.biomeDependent;
    }

}
