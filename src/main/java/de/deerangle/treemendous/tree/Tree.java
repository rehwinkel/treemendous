package de.deerangle.treemendous.tree;

import de.deerangle.treemendous.block.StrippableBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class Tree {

    private RegistryObject<Block> planks;
    private RegistryObject<RotatedPillarBlock> strippedLog;
    private RegistryObject<StrippableBlock> log;
    private RegistryObject<RotatedPillarBlock> strippedWood;
    private RegistryObject<StrippableBlock> wood;

    private Tree() {

    }

    public static Tree fromConfig(DeferredRegister<Block> blocks, DeferredRegister<Block> items, TreeConfig config) {
        Tree tree = new Tree();
        BlockBehaviour.Properties logProperties = BlockBehaviour.Properties.of(Material.WOOD, (state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? config.appearance().woodMaterialColor() : config.appearance().barkMaterialColor()).strength(2.0F).sound(SoundType.WOOD);
        BlockBehaviour.Properties woodProperties = BlockBehaviour.Properties.of(Material.WOOD, (state) -> config.appearance().barkMaterialColor()).strength(2.0F).sound(SoundType.WOOD);
        BlockBehaviour.Properties strippedProperties = BlockBehaviour.Properties.of(Material.WOOD, (state) -> config.appearance().woodMaterialColor()).strength(2.0F).sound(SoundType.WOOD);
        BlockBehaviour.Properties planksProperties = BlockBehaviour.Properties.of(Material.WOOD, (state) -> config.appearance().woodMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD);
        tree.planks = blocks.register(getNameForTree(config, "planks"), () -> new Block(planksProperties));
        tree.strippedLog = blocks.register(getNameForTree(config, "stripped", "log"), () -> new RotatedPillarBlock(strippedProperties));
        tree.log = blocks.register(getNameForTree(config, "log"), () -> new StrippableBlock(logProperties, tree.strippedLog::get));
        tree.strippedWood = blocks.register(getNameForTree(config, "stripped", "wood"), () -> new RotatedPillarBlock(strippedProperties));
        tree.wood = blocks.register(getNameForTree(config, "wood"), () -> new StrippableBlock(woodProperties, tree.strippedWood::get));
        return tree;
    }

    private static String getNameForTree(TreeConfig config, String suffix) {
        return String.format("%s_%s", config.registryName(), suffix);
    }

    private static String getNameForTree(TreeConfig config, String prefix, String suffix) {
        return String.format("%s_%s_%s", prefix, config.registryName(), suffix);
    }

}
