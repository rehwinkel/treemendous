package deerangle.treemendous.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.function.Supplier;

public class StrippableBlock extends RotatedPillarBlock {

    private final Supplier<Block> stripped;

    public StrippableBlock(Properties properties, Supplier<Block> stripped) {
        super(properties);
        this.stripped = stripped;
    }

    @Override
    public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
        if (toolType == ToolType.AXE) {
            return this.stripped.get().getDefaultState().with(AXIS, state.get(AXIS));
        }
        return super.getToolModifiedState(state, world, pos, player, stack, toolType);
    }

}
