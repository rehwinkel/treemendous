package de.deerangle.treemendous.tree.util;

import net.minecraft.core.BlockPos;

public class FixedLeavesColor implements ILeavesColor {

    private final int color;

    public FixedLeavesColor(int leavesColor) {
        this.color = leavesColor;
    }

    @Override
    public int getColor(BlockPos pos) {
        return color;
    }

}
