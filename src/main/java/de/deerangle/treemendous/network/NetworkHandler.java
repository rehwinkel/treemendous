package de.deerangle.treemendous.network;

import de.deerangle.treemendous.main.Treemendous;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fmllegacy.network.NetworkDirection;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

import java.util.Optional;

public class NetworkHandler
{

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(new ResourceLocation(Treemendous.MODID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void register()
    {
        NETWORK.registerMessage(0, UpdateChoppingBlock.class, UpdateChoppingBlock::encode, UpdateChoppingBlock::decode, UpdateChoppingBlock::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        NETWORK.registerMessage(1, RqUpdateChoppingBlock.class, RqUpdateChoppingBlock::encode, RqUpdateChoppingBlock::decode, RqUpdateChoppingBlock::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
    }

}
