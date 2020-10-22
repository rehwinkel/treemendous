package deerangle.treemendous.main;

import deerangle.treemendous.data.*;
import deerangle.treemendous.tree.BiomeMaker;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Treemendous.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Treemendous {
    public static final String MODID = "treemendous";

    public static final Logger logger = LogManager.getLogger();

    public Treemendous() {
        ExtraRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TreeRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TreeRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TreeRegistry.TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        TreeRegistry.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        TreeRegistry.BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
        BiomeMaker.BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
    }

    private final Proxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    @SubscribeEvent
    public static void registerDataGens(GatherDataEvent event) {
        event.getGenerator().addProvider(new LanguageProvider(event.getGenerator(), MODID, "en_us"));
        event.getGenerator()
                .addProvider(new ItemModelProvider(event.getGenerator(), MODID, event.getExistingFileHelper()));
        event.getGenerator()
                .addProvider(new BlockStateProvider(event.getGenerator(), MODID, event.getExistingFileHelper()));
        event.getGenerator().addProvider(new LootTableProvider(event.getGenerator(), MODID));
        event.getGenerator().addProvider(new RecipeProvider(event.getGenerator()));
        event.getGenerator()
                .addProvider(new ItemTagsProvider(event.getGenerator(), MODID, event.getExistingFileHelper()));
        event.getGenerator()
                .addProvider(new BlockTagsProvider(event.getGenerator(), MODID, event.getExistingFileHelper()));
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        proxy.clientSetup();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        BiomeMaker.addBiomesToOverworld();
    }

}
