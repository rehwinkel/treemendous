package deerangle.treemendous.tree;

import deerangle.treemendous.api.WoodColors;
import deerangle.treemendous.main.ExtraRegistry;
import deerangle.treemendous.main.Treemendous;
import deerangle.treemendous.util.FeatureSpread;
import deerangle.treemendous.world.BiomeSettings;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TreeRegistry {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS,
            Treemendous.MODID);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Treemendous.MODID);
    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES,
            Treemendous.MODID);

    public static final List<RegisteredTree> TREES = new ArrayList<>();

    public static final RegisteredTree douglas = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "douglas", "Douglas Fir").wood(WoodColors.DOUGLAS_WOOD)
                    .log(WoodColors.DOUGLAS_LOG).leaves(WoodColors.DOUGLAS_LEAVES)
                    .feature((log, leaves, sapling) -> TreeMaker.makeNeedleTree(log, leaves, sapling, 1, 6, 2),
                            Feature.NORMAL_TREE).biome(new BiomeSettings.Builder().temperature(0.4f).snow()).build());
    public static final RegisteredTree pine = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "pine", "Pine").wood(WoodColors.PINE_WOOD)
                    .log(WoodColors.PINE_LOG).leaves(WoodColors.PINE_LEAVES)
                    .feature(TreeMaker::makePineTree, Feature.NORMAL_TREE)
                    .biome(new BiomeSettings.Builder().temperature(0.4f).snow()).build());
    public static final RegisteredTree larch = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "larch", "Larch").wood(WoodColors.LARCH_WOOD)
                    .log(WoodColors.LARCH_LOG).leaves(WoodColors.LARCH_LEAVES)
                    .feature((log, leaves, sapling) -> TreeMaker.makeNeedleTree(log, leaves, sapling, 2, 7, 3),
                            Feature.NORMAL_TREE).biome(new BiomeSettings.Builder().temperature(0.4f).snow()).build());
    public static final RegisteredTree fir = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "fir", "Fir").wood(WoodColors.FIR_WOOD).log(WoodColors.FIR_LOG)
                    .leaves(WoodColors.FIR_LEAVES)
                    .feature((log, leaves, sapling) -> TreeMaker.makeNeedleTree(log, leaves, sapling, 1, 6, 2),
                            Feature.NORMAL_TREE).biome(new BiomeSettings.Builder().temperature(0.4f).snow()).build());
    public static final RegisteredTree maple = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "maple", "Maple").wood(WoodColors.MAPLE_WOOD)
                    .log(WoodColors.MAPLE_LOG).leaves(WoodColors.MAPLE_LEAVES)
                    .feature(TreeMaker::makeMapleTree, Feature.NORMAL_TREE).build());
    public static final RegisteredTree red_maple = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "red_maple", "Red Maple").inheritWood(maple)
                    .leaves(WoodColors.MAPLE_LEAVES_RED).feature(TreeMaker::makeMapleTree, Feature.NORMAL_TREE)
                    .build());
    public static final RegisteredTree brown_maple = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "brown_maple", "Brown Maple").inheritWood(maple)
                    .leaves(WoodColors.MAPLE_LEAVES_BROWN).feature(TreeMaker::makeMapleTree, Feature.NORMAL_TREE)
                    .build());
    public static final RegisteredTree japanese = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "japanese", "Japanese Maple").wood(WoodColors.JAPANESE_WOOD)
                    .log(WoodColors.JAPANESE_LOG).leaves(WoodColors.JAPANESE_LEAVES)
                    .feature(TreeMaker::makeLeafTree, Feature.NORMAL_TREE).build());
    public static final RegisteredTree beech = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "beech", "Beech").wood(WoodColors.BEECH_WOOD)
                    .log(WoodColors.BEECH_LOG).leaves(WoodColors.BEECH_LEAVES)
                    .feature((log, leaves, sapling) -> TreeMaker.makeRoundedLeafTree(log, leaves, sapling, 5, 2, 3),
                            Feature.NORMAL_TREE).build());
    public static final RegisteredTree cherry = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "cherry", "Cherry").wood(WoodColors.CHERRY_WOOD)
                    .log(WoodColors.CHERRY_LOG).plankType(1).leaves(WoodColors.CHERRY_LEAVES)
                    .feature((log, leaves, sapling) -> TreeMaker.makeRoundedLeafTree(log, leaves, sapling, 6, 2, 4),
                            Feature.NORMAL_TREE).build());
    public static final RegisteredTree alder = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "alder", "Alder").wood(WoodColors.ALDER_WOOD)
                    .log(WoodColors.ALDER_LOG).leaves(WoodColors.ALDER_LEAVES)
                    .feature((log, leaves, sapling) -> TreeMaker.makeRoundedLeafTree(log, leaves, sapling, 5, 2, 2),
                            Feature.NORMAL_TREE).build());
    public static final RegisteredTree chestnut = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "chestnut", "Chestnut").wood(WoodColors.CHESTNUT_WOOD)
                    .log(WoodColors.CHESTNUT_LOG).plankType(1).leaves(WoodColors.CHESTNUT_LEAVES)
                    .biome(new BiomeSettings.Builder().temperature(0.5f).snow())
                    .feature((log, leaves, sapling) -> TreeMaker.makeSmallLeafTree(log, leaves, sapling, 5, 3, 2, 4),
                            Feature.NORMAL_TREE).build());
    public static final RegisteredTree plane = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "plane", "Plane").wood(WoodColors.PLANE_WOOD)
                    .log(WoodColors.PLANE_LOG).leaves(WoodColors.PLANE_LEAVES).plankType(1)
                    .biome(new BiomeSettings.Builder().temperature(0.8f))
                    .feature(TreeMaker::makePlaneTree, Feature.NORMAL_TREE).build());
    public static final RegisteredTree ash = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "ash", "Ash").wood(WoodColors.ASH_WOOD).log(WoodColors.ASH_LOG)
                    .leaves(WoodColors.ASH_LEAVES).plankType(1).feature((log, leaves, sapling) -> TreeMaker
                    .makeAshTree(log, leaves, sapling, 7, 5, FeatureSpread.func_242253_a(1, 1),
                            FeatureSpread.func_242253_a(4, 2), FeatureSpread.func_242253_a(2, 1)), Feature.NORMAL_TREE)
                    .build());
    public static final RegisteredTree linden = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "linden", "Linden").wood(WoodColors.LINDEN_WOOD)
                    .log(WoodColors.LINDEN_LOG).leaves(WoodColors.LINDEN_LEAVES).plankType(2)
                    .feature(TreeMaker::makeFancyLeafTree, Feature.NORMAL_TREE).build());
    public static final RegisteredTree robinia = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "robinia", "Robinia").wood(WoodColors.ROBINIA_WOOD)
                    .log(WoodColors.ROBINIA_LOG).leaves(WoodColors.ROBINIA_LEAVES)
                    .feature(TreeMaker::makeAcaciaLeafTree, Feature.NORMAL_TREE)
                    .biome(new BiomeSettings.Builder().temperature(1.0f).dry()).build());
    public static final RegisteredTree willow = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "willow", "Willow").wood(WoodColors.WILLOW_WOOD)
                    .log(WoodColors.WILLOW_LOG).leaves(WoodColors.WILLOW_LEAVES)
                    .feature(TreeMaker::makeWillowLeafTree, Feature.NORMAL_TREE)
                    .biome(new BiomeSettings.Builder().temperature(0.6f)).build());
    public static final RegisteredTree pomegranate = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "pomegranate", "Pomegranate").wood(WoodColors.POMEGRANATE_WOOD)
                    .log(WoodColors.POMEGRANATE_LOG).leaves(WoodColors.POMEGRANATE_LEAVES).feature(
                    (log, leaves, sapling) -> TreeMaker
                            .makeAshTree(log, leaves, sapling, 4, 2, FeatureSpread.func_242253_a(0, 2),
                                    FeatureSpread.func_242253_a(3, 1), FeatureSpread.func_242253_a(2, 1)),
                    Feature.NORMAL_TREE).apple(ExtraRegistry.POMEGRANATE::get)
                    .biome(new BiomeSettings.Builder().temperature(1.0f).dry()).build());
    public static final RegisteredTree magnolia = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "magnolia", "Magnolia").leaves(WoodColors.MAGNOLIA_LEAVES)
                    .feature((log, leaves, sapling) -> TreeMaker.makeCrossBlobTree(log, leaves, sapling, 6, 3, 2, 3),
                            Feature.NORMAL_TREE).build());
    public static final RegisteredTree walnut = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "walnut", "Walnut").wood(WoodColors.WALNUT_WOOD)
                    .leaves(WoodColors.WALNUT_LEAVES).plankType(3).feature((log, leaves, sapling) -> TreeMaker
                            .makeCrossRoundTree(log, leaves, sapling, 6, 3, 3, FeatureSpread.func_242253_a(1, 1), 3),
                    Feature.NORMAL_TREE).build());
    public static final RegisteredTree cedar = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "cedar", "Cedar").leaves(WoodColors.CEDAR_LEAVES)
                    .wood(WoodColors.CEDAR_WOOD).log(WoodColors.CEDAR_LOG).plankType(1).feature(
                    (log, leaves, sapling) -> TreeMaker
                            .makePointyTree(log, leaves, sapling, FeatureSpread.func_242252_a(37),
                                    FeatureSpread.func_242252_a(100), 1, 8, 5), Feature.NORMAL_TREE)
                    .biome(new BiomeSettings.Builder().density(8).temperature(0.5f).snow()).build());
    public static final RegisteredTree poplar = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "poplar", "Poplar").leaves(WoodColors.POPLAR_LEAVES)
                    .wood(WoodColors.POPLAR_WOOD).log(WoodColors.POPLAR_LOG).plankType(1).feature(
                    (log, leaves, sapling) -> TreeMaker
                            .makeAshTree(log, leaves, sapling, 8, 6, FeatureSpread.func_242253_a(0, 1),
                                    FeatureSpread.func_242253_a(4, 2), FeatureSpread.func_242253_a(3, 1)),
                    Feature.NORMAL_TREE).biome(new BiomeSettings.Builder().density(8)).build());
    public static final RegisteredTree elm = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "elm", "Elm").wood(WoodColors.ELM_WOOD).log(WoodColors.ELM_LOG)
                    .leaves(WoodColors.ELM_LEAVES).plankType(2).feature(TreeMaker::makeBallTree, Feature.NORMAL_TREE)
                    .biome(new BiomeSettings.Builder().density(5)).build());
    public static final RegisteredTree rainbow_eucalyptus = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "rainbow_eucalyptus", "Rainbow Eucalyptus")
                    .leaves(blockPos -> blockPos != null ? Color.HSBtoRGB(
                            (float) (Math.pow((blockPos.getX() % 32) / 32.0f, 2) + Math
                                    .pow((blockPos.getY() % 32) / 32.0f, 2) + Math
                                    .pow((blockPos.getZ() % 32) / 32.0f, 2)), 0.7f, 0.8f) : Color.HSBtoRGB(0, 1, 1))
                    .feature(TreeMaker::makeBallTree, Feature.NORMAL_TREE).biome(new BiomeSettings.Builder().density(5))
                    .build());
    public static final RegisteredTree juniper = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "juniper", "Juniper").leaves(WoodColors.JUNIPER_LEAVES)
                    .wood(WoodColors.JUNIPER_WOOD).plankType(1).log(WoodColors.JUNIPER_LOG).feature(
                    (log, leaves, sapling) -> TreeMaker
                            .makeJuniperTree(log, leaves, sapling, FeatureSpread.func_242252_a(42),
                                    FeatureSpread.func_242252_a(100), 2, 12, 6), Feature.NORMAL_TREE)
                    .biome(new BiomeSettings.Builder().density(8).temperature(0.5f).snow()).build());

    private static RegisteredTree registerTree(RegisteredTree tree) {
        TREES.add(tree);
        return tree;
    }

}
