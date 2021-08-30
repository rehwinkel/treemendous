package de.deerangle.treemendous.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdateChoppingBlock {

    public BlockPos pos;
    public ItemStack stack;

    public UpdateChoppingBlock() {
    }

    public UpdateChoppingBlock(BlockPos pos, ItemStack stack) {
        this.pos = pos;
        this.stack = stack;
    }

    public static void encode(UpdateChoppingBlock msg, FriendlyByteBuf buf) {
        buf.writeBlockPos(msg.pos);
        buf.writeItem(msg.stack);
    }

    public static UpdateChoppingBlock decode(FriendlyByteBuf buf) {
        return new UpdateChoppingBlock(buf.readBlockPos(), buf.readItem());
    }

    public static void handle(UpdateChoppingBlock msg, Supplier<NetworkEvent.Context> contextSupplier) {
        ClientPacketHandlers.handleUpdateChoppingBlock(msg, contextSupplier);
    }

}
