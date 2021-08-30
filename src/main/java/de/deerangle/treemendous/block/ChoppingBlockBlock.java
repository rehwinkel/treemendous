package de.deerangle.treemendous.block;

import de.deerangle.treemendous.blockentity.ChoppingBlockBlockEntity;
import de.deerangle.treemendous.data.TreemendousItemTagsProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChoppingBlockBlock extends Block implements EntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    private final VoxelShape shape = Block.box(2, 0, 2, 14, 8, 14);

    public ChoppingBlockBlock(BlockBehaviour.Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @SuppressWarnings({"NullableProblems", "deprecation"})
    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return this.shape;
    }

    @SuppressWarnings({"NullableProblems", "deprecation"})
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        //TODO sound
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof ChoppingBlockBlockEntity blockEntity) {
            ItemStack heldItem = player.getItemInHand(hand);
            if (!heldItem.isEmpty()) {
                if (heldItem.is(TreemendousItemTagsProvider.FITS_IN_CHOPPING_BLOCK)) {
                    ItemStack remainder = blockEntity.getInventory().insertItem(0, heldItem, false);
                    player.setItemInHand(hand, remainder);
                    return InteractionResult.sidedSuccess(world.isClientSide());
                }
            } else {
                ItemStack extractedItem = blockEntity.getInventory().extractItem(0, 1, false);
                player.setItemInHand(hand, extractedItem);
                return InteractionResult.sidedSuccess(world.isClientSide());
            }
        }
        return super.use(state, world, pos, player, hand, blockHitResult);
    }

    @SuppressWarnings({"NullableProblems", "deprecation"})
    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos pos, BlockState resultingState, boolean flag) {
        if (!blockState.is(resultingState.getBlock())) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof ChoppingBlockBlockEntity blockEntity) {
                ItemStack item = blockEntity.getInventory().getStackInSlot(0);
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), item);
            }

            super.onRemove(blockState, level, pos, resultingState, flag);
        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @SuppressWarnings({"NullableProblems", "deprecation"})
    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    @SuppressWarnings({"NullableProblems", "deprecation"})
    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ChoppingBlockBlockEntity(pos, state);
    }

}
