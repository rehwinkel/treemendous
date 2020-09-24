package deerangle.treemendous.block.entity;

import deerangle.treemendous.main.TreeRegistry;
import net.minecraft.tileentity.TileEntityType;

public class CustomSignTileEntity extends net.minecraft.tileentity.SignTileEntity {

    @Override
    public TileEntityType<?> getType() {
        return TreeRegistry.SIGN.get();
    }

}