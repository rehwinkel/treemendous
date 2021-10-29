package de.deerangle.treemendous.blockentity;

import de.deerangle.treemendous.main.ExtraRegistry;
import de.deerangle.treemendous.network.NetworkHandler;
import de.deerangle.treemendous.network.RqUpdateChoppingBlock;
import de.deerangle.treemendous.network.UpdateChoppingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fmllegacy.network.PacketDistributor;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ChoppingBlockBlockEntity extends BlockEntity
{

    private final LazyOptional<IItemHandler> inventory = LazyOptional.of(() -> new ItemStackHandler(1)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            super.onContentsChanged(slot);
            Level world = ChoppingBlockBlockEntity.this.getLevel();
            if (world != null && !world.isClientSide())
            {
                BlockPos pos = ChoppingBlockBlockEntity.this.getBlockPos();
                NetworkHandler.NETWORK.send(PacketDistributor.TRACKING_CHUNK.with(() -> world.getChunkAt(pos)), new UpdateChoppingBlock(pos, this.getStackInSlot(slot)));
            }
        }
    });

    public ChoppingBlockBlockEntity(BlockPos pos, BlockState state)
    {
        super(ExtraRegistry.CHOPPING_BLOCK_BE.get(), pos, state);
    }

    @Override
    public void clearRemoved()
    {
        super.clearRemoved();
        Level world = this.getLevel();
        if (world != null && world.isClientSide())
        {
            NetworkHandler.NETWORK.send(PacketDistributor.SERVER.noArg(), new RqUpdateChoppingBlock(this.getBlockPos()));
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return inventory.cast();
        }

        return super.getCapability(cap, side);
    }

    public IItemHandler getInventory()
    {
        if (!inventory.isPresent()) return null;
        return inventory.orElseThrow(RuntimeException::new);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public CompoundTag save(CompoundTag tag)
    {
        ItemStackHandler itemStackHandler = (ItemStackHandler) getInventory();
        tag.put("Inventory", itemStackHandler.serializeNBT());
        return super.save(tag);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void load(CompoundTag tag)
    {
        super.load(tag);
        ItemStackHandler itemStackHandler = (ItemStackHandler) getInventory();
        itemStackHandler.deserializeNBT(tag.getCompound("Inventory"));
    }

}
