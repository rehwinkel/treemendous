package de.deerangle.treemendous.network;

import de.deerangle.treemendous.blockentity.ChoppingBlockBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fmllegacy.network.NetworkEvent;
import net.minecraftforge.items.ItemStackHandler;

import java.util.function.Supplier;

public class ClientPacketHandlers
{

    public static void handleUpdateChoppingBlock(UpdateChoppingBlock msg, Supplier<NetworkEvent.Context> contextSupplier)
    {
        contextSupplier.get().enqueueWork(() -> {
            Level world = Minecraft.getInstance().level;
            assert world != null;
            BlockEntity be = world.getBlockEntity(msg.pos);
            if (be instanceof ChoppingBlockBlockEntity blockEntity)
            {
                ItemStackHandler inventory = (ItemStackHandler) blockEntity.getInventory();
                inventory.setStackInSlot(0, msg.stack);
            }
        });
        contextSupplier.get().setPacketHandled(true);
    }

}
