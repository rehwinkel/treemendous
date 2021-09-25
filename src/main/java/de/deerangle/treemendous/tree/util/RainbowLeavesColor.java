package de.deerangle.treemendous.tree.util;

import net.minecraft.core.BlockPos;

import java.awt.*;

public class RainbowLeavesColor implements ILeavesColor {

    @Override
    public int getColor(BlockPos blockPos) {
        if (blockPos == BlockPos.ZERO) {
            return Color.HSBtoRGB(0, 1, 1);
        }
        float x = (blockPos.getX() % 32) / 32.0f + (float) Math.sin(blockPos.getX() / 3.2f) * 0.03f;
        float y = (blockPos.getY() % 32) / 32.0f + (float) Math.sin(blockPos.getY() / 3.2f) * 0.03f;
        float z = (blockPos.getZ() % 32) / 32.0f + (float) Math.sin(blockPos.getZ() / 3.2f) * 0.03f;
        return Color.HSBtoRGB(x + y + z, 0.7f, 0.8f);
    }

    @Override
    public boolean isBiomeDependent() {
        return false;
    }

}
