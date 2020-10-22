package deerangle.treemendous.block.entity;

import deerangle.treemendous.main.ExtraRegistry;
import net.minecraft.tileentity.TileEntityType;

public class CustomSignTileEntity extends net.minecraft.tileentity.SignTileEntity {

    @Override
    public TileEntityType<?> getType() {
        return ExtraRegistry.SIGN.get();
    }

}