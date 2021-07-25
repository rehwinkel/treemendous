package de.deerangle.treemendous.main;

import de.deerangle.treemendous.tree.TreeRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod(Treemendous.MODID)
public class Treemendous {

    public static final String MODID = "treemendous";

    public Treemendous() {
        TreeRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TreeRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @SubscribeEvent
    public void onGatherData(GatherDataEvent event) {

    }

}
