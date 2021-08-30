package de.deerangle.treemendous.main;

import de.deerangle.treemendous.data.*;
import de.deerangle.treemendous.network.NetworkHandler;
import de.deerangle.treemendous.tree.RegisteredTree;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Treemendous.MODID)
public class Treemendous {

    public static final String MODID = "treemendous";
    public static final Logger LOGGER = LogManager.getLogger();

    public Treemendous() {
        TreeRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TreeRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ExtraRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ExtraRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ExtraRegistry.BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ExtraRegistry.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @SubscribeEvent
    public void onCommonInitSetup(FMLCommonSetupEvent event) {
        NetworkHandler.register();
    }

    @SubscribeEvent
    public void createRegistries(RegistryEvent.NewRegistry event) {
        new RegistryBuilder<RegisteredTree>().setName(new ResourceLocation(Treemendous.MODID, "trees")).setType(RegisteredTree.class).create();
    }

    @SubscribeEvent
    public void onGatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(new TreemendousModelProvider(event.getGenerator(), MODID, event.getExistingFileHelper()));
        BlockTagsProvider blockTagsProvider = new TreemendousBlockTagsProvider(event.getGenerator(), MODID, event.getExistingFileHelper());
        event.getGenerator().addProvider(blockTagsProvider);
        event.getGenerator().addProvider(new TreemendousItemTagsProvider(event.getGenerator(), blockTagsProvider, MODID, event.getExistingFileHelper()));
        event.getGenerator().addProvider(new TreemendousRecipeProvider(event.getGenerator()));
        event.getGenerator().addProvider(new TreemendousLootTableProvider(event.getGenerator()));
        event.getGenerator().addProvider(new TreemendousLanguageProvider(event.getGenerator(), MODID, "en_us"));
        event.getGenerator().addProvider(new TreemendousLanguageProvider(event.getGenerator(), MODID, "de_de"));
    }

}
