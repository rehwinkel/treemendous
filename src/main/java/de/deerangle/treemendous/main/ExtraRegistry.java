package de.deerangle.treemendous.main;

import de.deerangle.treemendous.block.CustomChestBlock;
import de.deerangle.treemendous.block.CustomCraftingTableBlock;
import de.deerangle.treemendous.blockentity.CustomChestBlockEntity;
import de.deerangle.treemendous.entity.CustomBoat;
import de.deerangle.treemendous.item.CustomChestBlockItem;
import de.deerangle.treemendous.tree.Tree;
import net.minecraft.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class ExtraRegistry {

    private static final List<Supplier<SignBlock>> SIGN_BLOCK_LIST = new ArrayList<>();
    private static final List<Supplier<CustomChestBlock>> CHEST_BLOCK_LIST = new ArrayList<>();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Treemendous.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Treemendous.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Treemendous.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Treemendous.MODID);

    // Treemendous Extras
    public static final RegistryObject<BlockEntityType<SignBlockEntity>> SIGN = BLOCK_ENTITIES.register("sign", () -> BlockEntityType.Builder.of(SignBlockEntity::new, SIGN_BLOCK_LIST.stream().map(Supplier::get).toArray(SignBlock[]::new)).build(Objects.requireNonNull(Util.fetchChoiceType(References.BLOCK_ENTITY, "sign"))));
    public static final RegistryObject<BlockEntityType<CustomChestBlockEntity>> CHEST = BLOCK_ENTITIES.register("chest", () -> BlockEntityType.Builder.of(CustomChestBlockEntity::new, CHEST_BLOCK_LIST.stream().map(Supplier::get).toArray(ChestBlock[]::new)).build(Objects.requireNonNull(Util.fetchChoiceType(References.BLOCK_ENTITY, "chest"))));
    public static final RegistryObject<EntityType<CustomBoat>> BOAT = ENTITIES.register("boat", () -> EntityType.Builder.<CustomBoat>of(CustomBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("boat"));

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

    static {
        CHEST_BLOCK_LIST.add(BIRCH_CHEST);
        CHEST_BLOCK_LIST.add(SPRUCE_CHEST);
        CHEST_BLOCK_LIST.add(JUNGLE_CHEST);
        CHEST_BLOCK_LIST.add(ACACIA_CHEST);
        CHEST_BLOCK_LIST.add(DARK_OAK_CHEST);
        CHEST_BLOCK_LIST.add(CRIMSON_CHEST);
        CHEST_BLOCK_LIST.add(WARPED_CHEST);

        ITEMS.register("birch_chest", () -> new CustomChestBlockItem(BIRCH_CHEST.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("spruce_chest", () -> new CustomChestBlockItem(SPRUCE_CHEST.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("jungle_chest", () -> new CustomChestBlockItem(JUNGLE_CHEST.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("acacia_chest", () -> new CustomChestBlockItem(ACACIA_CHEST.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("dark_oak_chest", () -> new CustomChestBlockItem(DARK_OAK_CHEST.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("crimson_chest", () -> new CustomChestBlockItem(CRIMSON_CHEST.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("warped_chest", () -> new CustomChestBlockItem(WARPED_CHEST.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("birch_crafting_table", () -> new CustomChestBlockItem(BIRCH_CRAFTING_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("spruce_crafting_table", () -> new CustomChestBlockItem(SPRUCE_CRAFTING_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("jungle_crafting_table", () -> new CustomChestBlockItem(JUNGLE_CRAFTING_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("acacia_crafting_table", () -> new CustomChestBlockItem(ACACIA_CRAFTING_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("dark_oak_crafting_table", () -> new CustomChestBlockItem(DARK_OAK_CRAFTING_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("crimson_crafting_table", () -> new CustomChestBlockItem(CRIMSON_CRAFTING_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        ITEMS.register("warped_crafting_table", () -> new CustomChestBlockItem(WARPED_CRAFTING_TABLE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    }

    public static Tree registerTree(Tree tree) {
        SIGN_BLOCK_LIST.add(tree::getSign);
        SIGN_BLOCK_LIST.add(tree::getWallSign);
        CHEST_BLOCK_LIST.add(tree::getChest);
        return tree;
    }

}
