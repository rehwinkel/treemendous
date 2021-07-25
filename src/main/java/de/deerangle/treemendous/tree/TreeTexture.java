package de.deerangle.treemendous.tree;

import net.minecraft.world.level.material.MaterialColor;

public record TreeTexture(int woodColor, int barkColor, int plankTextureType) {

    public MaterialColor woodMaterialColor() {
        return MaterialColor.CLAY;
    }

    public MaterialColor barkMaterialColor() {
        return MaterialColor.CLAY;
    }

}
