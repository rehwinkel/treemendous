package de.deerangle.treemendous.main;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import de.deerangle.treemendous.block.ChoppingBlockBlock;
import de.deerangle.treemendous.block.CustomChestBlock;
import de.deerangle.treemendous.block.CustomCraftingTableBlock;
import de.deerangle.treemendous.blockentity.ChoppingBlockBlockEntity;
import de.deerangle.treemendous.blockentity.CustomChestBlockEntity;
import de.deerangle.treemendous.entity.CustomBoat;
import de.deerangle.treemendous.item.CustomChestBlockItem;
import de.deerangle.treemendous.item.LumberAxeItem;
import de.deerangle.treemendous.item.LumberTiers;
import de.deerangle.treemendous.tree.Tree;
import de.deerangle.treemendous.world.RangerHouseConfiguration;
import de.deerangle.treemendous.world.RangerHouseFeature;
import de.deerangle.treemendous.world.RangerHouseStructurePiece;
import de.deerangle.treemendous.world.ReplacementProcessor;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import static net.minecraft.world.entity.ai.village.poi.PoiType.getBlockStates;

public class ExtraRegistry
{

    private static final List<Supplier<SignBlock>> SIGN_BLOCK_LIST = new ArrayList<>();
    private static final List<Supplier<ChestBlock>> CHEST_BLOCK_LIST = new ArrayList<>();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Treemendous.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Treemendous.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Treemendous.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Treemendous.MODID);
    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, Treemendous.MODID);
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, Treemendous.MODID);
    public static final DeferredRegister<StructureFeature<?>> STRUCTURE_FEATURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Treemendous.MODID);

    // Treemendous Extras
    @SuppressWarnings("ConstantConditions")
    public static final RegistryObject<BlockEntityType<SignBlockEntity>> SIGN = BLOCK_ENTITIES.register("sign", () -> BlockEntityType.Builder.of(SignBlockEntity::new, SIGN_BLOCK_LIST.stream().map(Supplier::get).toArray(SignBlock[]::new)).build(null));
    @SuppressWarnings("ConstantConditions")
    public static final RegistryObject<BlockEntityType<CustomChestBlockEntity>> CHEST = BLOCK_ENTITIES.register("chest", () -> BlockEntityType.Builder.of(CustomChestBlockEntity::new, CHEST_BLOCK_LIST.stream().map(Supplier::get).toArray(ChestBlock[]::new)).build(null));
    public static final RegistryObject<EntityType<CustomBoat>> BOAT = ENTITIES.register("boat", () -> EntityType.Builder.<CustomBoat>of(CustomBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("boat"));
    public static final RegistryObject<LeavesBlock> MAPLE_RED_LEAVES = BLOCKS.register("maple_red_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(Tree::ocelotOrParrot).isSuffocating((state, world, pos) -> false).isViewBlocking((state, world, pos) -> false)));
    public static final RegistryObject<LeavesBlock> MAPLE_BROWN_LEAVES = BLOCKS.register("maple_brown_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(Tree::ocelotOrParrot).isSuffocating((state, world, pos) -> false).isViewBlocking((state, world, pos) -> false)));
    public static final RegistryObject<ChoppingBlockBlock> CHOPPING_BLOCK = BLOCKS.register("chopping_block", () -> new ChoppingBlockBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));
    @SuppressWarnings("ConstantConditions")
    public static final RegistryObject<BlockEntityType<ChoppingBlockBlockEntity>> CHOPPING_BLOCK_BE = BLOCK_ENTITIES.register("chopping_block", () -> BlockEntityType.Builder.of(ChoppingBlockBlockEntity::new, CHOPPING_BLOCK.get()).build(null));

    // Village
    public static final RegistryObject<PoiType> FOREST_RANGER_POI = POI_TYPES.register("forest_ranger", () -> new PoiType("forest_ranger", getBlockStates(CHOPPING_BLOCK.get()), 1, 1));
    public static final RegistryObject<VillagerProfession> FOREST_RANGER_PROFESSION = PROFESSIONS.register("forest_ranger", () -> new VillagerProfession("forest_ranger", FOREST_RANGER_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.WOOD_PLACE));

    // Houses
    public static final RegistryObject<RangerHouseFeature> RANGER_HOUSE_FEATURE = STRUCTURE_FEATURES.register("ranger_house", () -> new RangerHouseFeature(RangerHouseConfiguration.CODEC));
    public static final StructurePieceType RANGER_HOUSE_PIECE_TYPE = StructurePieceType.setPieceId(RangerHouseStructurePiece::new, "RangerHouse");
    public static final StructureProcessorType<ReplacementProcessor> REPLACEMENT_PROCESSOR = registerStructureProcessor("replacement", ReplacementProcessor.CODEC);

    static <P extends StructureProcessor> StructureProcessorType<P> registerStructureProcessor(String name, Codec<P> codec)
    {
        return Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(Treemendous.MODID, name), () -> codec);
    }

    // Vanilla Extras
    public static final RegistryObject<CustomChestBlock> BIRCH_CHEST = BLOCKS.register("birch_chest", () -> new CustomChestBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).strength(2.5F).sound(SoundType.WOOD), "birch"));
    public static final RegistryObject<CustomChestBlock> SPRUCE_CHEST = BLOCKS.register("spruce_chest", () -> new CustomChestBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2.5F).sound(SoundType.WOOD), "spruce"));
    public static final RegistryObject<CustomChestBlock> JUNGLE_CHEST = BLOCKS.register("jungle_chest", () -> new CustomChestBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.DIRT).strength(2.5F).sound(SoundType.WOOD), "jungle"));
    public static final RegistryObject<CustomChestBlock> ACACIA_CHEST = BLOCKS.register("acacia_chest", () -> new CustomChestBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.5F).sound(SoundType.WOOD), "acacia"));
    public static final RegistryObject<CustomChestBlock> DARK_OAK_CHEST = BLOCKS.register("dark_oak_chest", () -> new CustomChestBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.5F).sound(SoundType.WOOD), "dark_oak"));
    public static final RegistryObject<CustomChestBlock> CRIMSON_CHEST = BLOCKS.register("crimson_chest", () -> new CustomChestBlock(BlockBehaviour.Properties.of(Material.NETHER_WOOD, MaterialColor.CRIMSON_STEM).strength(2.5F).sound(SoundType.WOOD), "crimson"));
    public static final RegistryObject<CustomChestBlock> WARPED_CHEST = BLOCKS.register("warped_chest", () -> new CustomChestBlock(BlockBehaviour.Properties.of(Material.NETHER_WOOD, MaterialColor.WARPED_STEM).strength(2.5F).sound(SoundType.WOOD), "warped"));
    public static final RegistryObject<CustomCraftingTableBlock> BIRCH_CRAFTING_TABLE = BLOCKS.register("birch_crafting_table", () -> new CustomCraftingTableBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<CustomCraftingTableBlock> SPRUCE_CRAFTING_TABLE = BLOCKS.register("spruce_crafting_table", () -> new CustomCraftingTableBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<CustomCraftingTableBlock> JUNGLE_CRAFTING_TABLE = BLOCKS.register("jungle_crafting_table", () -> new CustomCraftingTableBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.DIRT).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<CustomCraftingTableBlock> ACACIA_CRAFTING_TABLE = BLOCKS.register("acacia_crafting_table", () -> new CustomCraftingTableBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<CustomCraftingTableBlock> DARK_OAK_CRAFTING_TABLE = BLOCKS.register("dark_oak_crafting_table", () -> new CustomCraftingTableBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<CustomCraftingTableBlock> CRIMSON_CRAFTING_TABLE = BLOCKS.register("crimson_crafting_table", () -> new CustomCraftingTableBlock(BlockBehaviour.Properties.of(Material.NETHER_WOOD, MaterialColor.CRIMSON_STEM).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<CustomCraftingTableBlock> WARPED_CRAFTING_TABLE = BLOCKS.register("warped_crafting_table", () -> new CustomCraftingTableBlock(BlockBehaviour.Properties.of(Material.NETHER_WOOD, MaterialColor.WARPED_STEM).strength(2.5F).sound(SoundType.WOOD)));

    public static final RegistryObject<LumberAxeItem> IRON_LUMBER_AXE = ITEMS.register("iron_lumber_axe", () -> new LumberAxeItem(LumberTiers.IRON, 6.0F, -3.1F, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<LumberAxeItem> GOLDEN_LUMBER_AXE = ITEMS.register("golden_lumber_axe", () -> new LumberAxeItem(LumberTiers.GOLD, 6.0F, -3.0F, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<LumberAxeItem> DIAMOND_LUMBER_AXE = ITEMS.register("diamond_lumber_axe", () -> new LumberAxeItem(LumberTiers.DIAMOND, 5.0F, -3.0F, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<LumberAxeItem> NETHERITE_LUMBER_AXE = ITEMS.register("netherite_lumber_axe", () -> new LumberAxeItem(LumberTiers.NETHERITE, 5.0F, -3.0F, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));

    static
    {
        CHEST_BLOCK_LIST.add(BIRCH_CHEST::get);
        CHEST_BLOCK_LIST.add(SPRUCE_CHEST::get);
        CHEST_BLOCK_LIST.add(JUNGLE_CHEST::get);
        CHEST_BLOCK_LIST.add(ACACIA_CHEST::get);
        CHEST_BLOCK_LIST.add(DARK_OAK_CHEST::get);
        CHEST_BLOCK_LIST.add(CRIMSON_CHEST::get);
        CHEST_BLOCK_LIST.add(WARPED_CHEST::get);

        ITEMS.register("maple_red_leaves", () -> new BlockItem(MAPLE_RED_LEAVES.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("maple_brown_leaves", () -> new BlockItem(MAPLE_BROWN_LEAVES.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

        ITEMS.register("birch_chest", () -> new CustomChestBlockItem(BIRCH_CHEST.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("spruce_chest", () -> new CustomChestBlockItem(SPRUCE_CHEST.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("jungle_chest", () -> new CustomChestBlockItem(JUNGLE_CHEST.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("acacia_chest", () -> new CustomChestBlockItem(ACACIA_CHEST.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("dark_oak_chest", () -> new CustomChestBlockItem(DARK_OAK_CHEST.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("crimson_chest", () -> new CustomChestBlockItem(CRIMSON_CHEST.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("warped_chest", () -> new CustomChestBlockItem(WARPED_CHEST.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("birch_crafting_table", () -> new BlockItem(BIRCH_CRAFTING_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("spruce_crafting_table", () -> new BlockItem(SPRUCE_CRAFTING_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("jungle_crafting_table", () -> new BlockItem(JUNGLE_CRAFTING_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("acacia_crafting_table", () -> new BlockItem(ACACIA_CRAFTING_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("dark_oak_crafting_table", () -> new BlockItem(DARK_OAK_CRAFTING_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("crimson_crafting_table", () -> new BlockItem(CRIMSON_CRAFTING_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("warped_crafting_table", () -> new BlockItem(WARPED_CRAFTING_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("chopping_block", () -> new BlockItem(CHOPPING_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    }

    public static void setupStructures()
    {
        setupStructure(RANGER_HOUSE_FEATURE.get(), new StructureFeatureConfiguration(40, 8, 835769016));
    }

    private static void setupStructure(StructureFeature<? extends FeatureConfiguration> feature, StructureFeatureConfiguration spawnConfig)
    {
        if (!(StructureFeature.NOISE_AFFECTING_FEATURES instanceof ArrayList))
        {
            StructureFeature.NOISE_AFFECTING_FEATURES = new ArrayList<>(StructureFeature.NOISE_AFFECTING_FEATURES);
        }
        StructureFeature.NOISE_AFFECTING_FEATURES.add(feature);

        StructureFeature.STRUCTURES_REGISTRY.put(Objects.requireNonNull(feature.getRegistryName()).toString(), feature);

        StructureSettings.DEFAULTS = ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder()
                .putAll(StructureSettings.DEFAULTS)
                .put(feature, spawnConfig)
                .build();

        BuiltinRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
            Map<StructureFeature<?>, StructureFeatureConfiguration> structureMap = settings.getValue().structureSettings().structureConfig();

            if (structureMap instanceof ImmutableMap)
            {
                Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(structureMap);
                tempMap.put(feature, spawnConfig);
                settings.getValue().structureSettings().structureConfig = tempMap;
            } else
            {
                structureMap.put(feature, spawnConfig);
            }
        });
    }

    public static Tree registerTree(Tree tree)
    {
        SIGN_BLOCK_LIST.add(tree::getSign);
        SIGN_BLOCK_LIST.add(tree::getWallSign);
        CHEST_BLOCK_LIST.add(tree::getChest);
        return tree;
    }

}
