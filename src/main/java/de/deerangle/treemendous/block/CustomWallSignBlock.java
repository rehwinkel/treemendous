package de.deerangle.treemendous.block;

import de.deerangle.treemendous.blockentity.CustomSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;


public class CustomWallSignBlock extends WallSignBlock
{

    public CustomWallSignBlock(Properties sound, WoodType woodType)
    {
        super(sound, woodType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new CustomSignBlockEntity(pos, state);
    }

}
