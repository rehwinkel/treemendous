package de.deerangle.treemendous.block;


import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class StrippableBlock extends FlammableRotatedPillarBlock
{

    private final Supplier<Block> stripped;

    public StrippableBlock(Properties properties, Supplier<Block> stripped)
    {
        super(properties);
        this.stripped = stripped;
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, Level world, BlockPos pos, Player player, ItemStack stack, ToolAction toolAction)
    {
        if (toolAction == ToolActions.AXE_STRIP)
        {
            return this.stripped.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
        }
        return super.getToolModifiedState(state, world, pos, player, stack, toolAction);
    }

}