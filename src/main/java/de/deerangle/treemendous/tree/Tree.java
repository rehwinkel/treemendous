package de.deerangle.treemendous.tree;

import de.deerangle.treemendous.block.StrippableBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class Tree {

    private final TreeConfig config;
    private RegistryObject<Block> planks;
    private RegistryObject<RotatedPillarBlock> strippedLog;
    private RegistryObject<StrippableBlock> log;
    private RegistryObject<RotatedPillarBlock> strippedWood;
    private RegistryObject<StrippableBlock> wood;

    private Tree(TreeConfig config) {
        this.config = config;
    }

    public static Tree fromConfig(DeferredRegister<Block> blocks, DeferredRegister<Item> items, TreeConfig config) {
        Tree tree = new Tree(config);
        BlockBehaviour.Properties logProperties = BlockBehaviour.Properties.of(Material.WOOD, (state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? config.appearance().woodMaterialColor() : config.appearance().barkMaterialColor()).strength(2.0F).sound(SoundType.WOOD);
        BlockBehaviour.Properties woodProperties = BlockBehaviour.Properties.of(Material.WOOD, (state) -> config.appearance().barkMaterialColor()).strength(2.0F).sound(SoundType.WOOD);
        BlockBehaviour.Properties strippedProperties = BlockBehaviour.Properties.of(Material.WOOD, (state) -> config.appearance().woodMaterialColor()).strength(2.0F).sound(SoundType.WOOD);
        BlockBehaviour.Properties planksProperties = BlockBehaviour.Properties.of(Material.WOOD, (state) -> config.appearance().woodMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD);
        tree.planks = blocks.register(getNameForTree(config, "planks"), () -> new Block(planksProperties));
        tree.strippedLog = blocks.register(getNameForTree(config, "stripped", "log"), () -> new RotatedPillarBlock(strippedProperties));
        tree.log = blocks.register(getNameForTree(config, "log"), () -> new StrippableBlock(logProperties, tree.strippedLog::get));
        tree.strippedWood = blocks.register(getNameForTree(config, "stripped", "wood"), () -> new RotatedPillarBlock(strippedProperties));
        tree.wood = blocks.register(getNameForTree(config, "wood"), () -> new StrippableBlock(woodProperties, tree.strippedWood::get));
        registerBlockItem(items, getNameForTree(config, "planks"), tree.planks, CreativeModeTab.TAB_BUILDING_BLOCKS);
        registerBlockItem(items, getNameForTree(config, "log"), tree.log, CreativeModeTab.TAB_BUILDING_BLOCKS);
        registerBlockItem(items, getNameForTree(config, "stripped", "log"), tree.strippedLog, CreativeModeTab.TAB_BUILDING_BLOCKS);
        registerBlockItem(items, getNameForTree(config, "wood"), tree.wood, CreativeModeTab.TAB_BUILDING_BLOCKS);
        registerBlockItem(items, getNameForTree(config, "stripped", "wood"), tree.strippedWood, CreativeModeTab.TAB_BUILDING_BLOCKS);
        return tree;
    }

    private static void registerBlockItem(DeferredRegister<Item> items, String name, RegistryObject<? extends Block> block, CreativeModeTab tab) {
        items.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    private static String getNameForTree(TreeConfig config, String suffix) {
        return String.format("%s_%s", config.registryName(), suffix);
    }

    private static String getNameForTree(TreeConfig config, String prefix, String suffix) {
        return String.format("%s_%s_%s", prefix, config.registryName(), suffix);
    }

    public Block getPlanks() {
        return this.planks.get();
    }

    public RotatedPillarBlock getLog() {
        return this.log.get();
    }

    public RotatedPillarBlock getStrippedLog() {
        return this.strippedLog.get();
    }

    public RotatedPillarBlock getWood() {
        return this.wood.get();
    }

    public RotatedPillarBlock getStrippedWood() {
        return this.strippedWood.get();
    }

    public TreeConfig getConfig() {
        return this.config;
    }
}
