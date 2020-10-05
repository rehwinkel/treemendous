package deerangle.treemendous.block;

import deerangle.treemendous.block.entity.CustomSignTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class CustomWallSignBlock extends net.minecraft.block.WallSignBlock {

    public CustomWallSignBlock(Properties props, WoodType type) {
        super(props, type);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new CustomSignTileEntity();
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return this.createNewTileEntity(world);
    }
}
