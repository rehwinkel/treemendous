package de.deerangle.treemendous.network;

import de.deerangle.treemendous.blockentity.ChoppingBlockBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fmllegacy.network.NetworkEvent;
import net.minecraftforge.fmllegacy.network.PacketDistributor;

import java.util.Objects;
import java.util.function.Supplier;

public class RqUpdateChoppingBlock
{

    private BlockPos pos;

    public RqUpdateChoppingBlock()
    {
    }

    public RqUpdateChoppingBlock(BlockPos pos)
    {
        this.pos = pos;
    }

    public static void encode(RqUpdateChoppingBlock msg, FriendlyByteBuf buf)
    {
        buf.writeBlockPos(msg.pos);
    }

    public static RqUpdateChoppingBlock decode(FriendlyByteBuf buf)
    {
        return new RqUpdateChoppingBlock(buf.readBlockPos());
    }

    public static void handle(RqUpdateChoppingBlock msg, Supplier<NetworkEvent.Context> contextSupplier)
    {
        contextSupplier.get().enqueueWork(() -> {
            ServerLevel world = Objects.requireNonNull(contextSupplier.get().getSender()).getLevel();
            if (world.isLoaded(msg.pos))
            {
                BlockEntity be = world.getBlockEntity(msg.pos);
                if (be instanceof ChoppingBlockBlockEntity blockEntity)
                {
                    ItemStack item = blockEntity.getInventory().getStackInSlot(0);
                    NetworkHandler.NETWORK.send(PacketDistributor.PLAYER.with(contextSupplier.get()::getSender), new UpdateChoppingBlock(msg.pos, item));
                }
            }
        });
        contextSupplier.get().setPacketHandled(true);
    }
}
