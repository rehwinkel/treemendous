package de.deerangle.treemendous.util;

import net.minecraft.world.level.material.MaterialColor;

public class Util {

    public static MaterialColor getMaterialColor(int color) {
        MaterialColor closest = null;
        double closestDiff = 3;
        for (MaterialColor matColor : MaterialColor.MATERIAL_COLORS) {
            if (matColor != null) {
                int blueMat = matColor.col & 0xFF;
                int greenMat = (matColor.col & 0xFF00) >> 8;
                int redMat = (matColor.col & 0xFF0000) >> 16;
                int blue = color & 0xFF;
                int green = (color & 0xFF00) >> 8;
                int red = (color & 0xFF0000) >> 16;
                double redDiff = Math.abs(red / 255.0 - redMat / 255.0);
                double greenDiff = Math.abs(green / 255.0 - greenMat / 255.0);
                double blueDiff = Math.abs(blue / 255.0 - blueMat / 255.0);
                double diff = redDiff + greenDiff + blueDiff;
                if (diff < closestDiff) {
                    closest = matColor;
                    closestDiff = diff;
                }
            }
        }
        return closest;
    }

}
