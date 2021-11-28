package de.deerangle.treemendous.main;

import de.deerangle.treemendous.data.TreemendousAdvancementProvider;
import de.deerangle.treemendous.data.TreemendousBlockTagsProvider;
import de.deerangle.treemendous.data.TreemendousItemTagsProvider;
import de.deerangle.treemendous.data.TreemendousLanguageProvider;
import de.deerangle.treemendous.data.TreemendousLootTableProvider;
import de.deerangle.treemendous.data.TreemendousModelProvider;
import de.deerangle.treemendous.data.TreemendousRecipeProvider;
import de.deerangle.treemendous.network.NetworkHandler;
import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.TreeFeatureRegistry;
import de.deerangle.treemendous.world.Biomes;
import de.deerangle.treemendous.world.TreemendousConfiguredFeatures;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.RegistryBuilder;

@Mod(Treemendous.MODID)
public class Treemendous
{

    public static final String MODID = "treemendous";

    public Treemendous()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TreemendousConfig.commonSpec);
        TreeRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TreeRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        Biomes.registerTrees();
        Biomes.BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ExtraRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ExtraRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ExtraRegistry.BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ExtraRegistry.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ExtraRegistry.PROFESSIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ExtraRegistry.POI_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ExtraRegistry.STRUCTURE_FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        TreeFeatureRegistry.FOLIAGE_PLACERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @SubscribeEvent
    public void onCommonSetup(FMLCommonSetupEvent event)
    {
        NetworkHandler.register();
        event.enqueueWork(() -> {
            ExtraRegistry.setupStructures();
            TreemendousConfiguredFeatures.registerConfiguredStructures();
        });
        Biomes.addBiomesToOverworld();
        Biomes.addBiomesToDictionary();
    }

    @SubscribeEvent
    public void createRegistries(RegistryEvent.NewRegistry event)
    {
        new RegistryBuilder<RegisteredTree>().setName(new ResourceLocation(Treemendous.MODID, "trees")).setType(RegisteredTree.class).create();
    }

    @SubscribeEvent
    public void onGatherData(GatherDataEvent event)
    {
        event.getGenerator().addProvider(new TreemendousModelProvider(event.getGenerator(), MODID, event.getExistingFileHelper()));
        BlockTagsProvider blockTagsProvider = new TreemendousBlockTagsProvider(event.getGenerator(), MODID, event.getExistingFileHelper());
        event.getGenerator().addProvider(blockTagsProvider);
        event.getGenerator().addProvider(new TreemendousItemTagsProvider(event.getGenerator(), blockTagsProvider, MODID, event.getExistingFileHelper()));
        event.getGenerator().addProvider(new TreemendousRecipeProvider(event.getGenerator()));
        event.getGenerator().addProvider(new TreemendousLootTableProvider(event.getGenerator()));
        event.getGenerator().addProvider(new TreemendousAdvancementProvider(event.getGenerator(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(new TreemendousLanguageProvider(event.getGenerator(), MODID, "en_us"));
        event.getGenerator().addProvider(new TreemendousLanguageProvider(event.getGenerator(), MODID, "de_de"));
    }

}
