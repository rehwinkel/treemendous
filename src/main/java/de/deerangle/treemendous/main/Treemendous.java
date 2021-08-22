package de.deerangle.treemendous.main;

import de.deerangle.treemendous.data.TreemendousBlockStateProvider;
import de.deerangle.treemendous.tree.TreeRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Treemendous.MODID)
public class Treemendous {

    public static final String MODID = "treemendous";
    public static final Logger LOGGER = LogManager.getLogger();

    public Treemendous() {
        TreeRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TreeRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TreeRegistry.BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @SubscribeEvent
    public void onGatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(new TreemendousBlockStateProvider(event.getGenerator(), MODID, event.getExistingFileHelper()));
    }

}
