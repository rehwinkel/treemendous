package deerangle.treemendous.tree;

import deerangle.treemendous.main.ExtraRegistry;
import deerangle.treemendous.main.Treemendous;
import deerangle.treemendous.world.BiomeSettings;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TreeRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister
            .create(ForgeRegistries.BLOCKS, Treemendous.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister
            .create(ForgeRegistries.ITEMS, Treemendous.MODID);
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister
            .create(ForgeRegistries.BIOMES, Treemendous.MODID);

    public static final List<RegisteredTree> TREES = new ArrayList<>();

    // ACACIA: 0xcd6b38

    public static final RegisteredTree douglas = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "douglas", "Douglas Fir").wood(0xcd6b38).log(0x000000)
                    .leaves(0x78a65d).feature((log, leaves) -> TreeMaker.makeNeedleTree(log, leaves, 1, 6, 2))
                    .biome(new BiomeSettings.Builder().temperature(0.4f).snow()).build());
    public static final RegisteredTree pine = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "pine", "Pine").wood(0x000000).leaves(0x486942)
                    .feature(TreeMaker::makePineTree).biome(new BiomeSettings.Builder().temperature(0.4f).snow())
                    .build());
    public static final RegisteredTree larch = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "larch", "Larch").leaves(0x486942)
                    .feature((log, leaves) -> TreeMaker.makeNeedleTree(log, leaves, 2, 7, 3))
                    .biome(new BiomeSettings.Builder().temperature(0.4f).snow()).build());
    public static final RegisteredTree fir = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "fir", "Fir").log(0x000000).leaves(0x5a914e)
                    .feature((log, leaves) -> TreeMaker.makeNeedleTree(log, leaves, 1, 6, 2))
                    .biome(new BiomeSettings.Builder().temperature(0.4f).snow()).build());
    public static final RegisteredTree maple = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "maple", "Maple").wood(0x000000).leaves(0x9bd95d)
                    .feature(TreeMaker::makeMapleTree).build());
    public static final RegisteredTree red_maple = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "red_maple", "Red Maple").inheritWood(maple).wood(0x000000)
                    .leaves(0xcc764e).feature(TreeMaker::makeMapleTree).build());
    public static final RegisteredTree brown_maple = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "brown_maple", "Brown Maple").inheritWood(maple).wood(0x000000)
                    .leaves(0xd9c25d).feature(TreeMaker::makeMapleTree).build());
    public static final RegisteredTree japanese = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "japanese", "Japanese Maple").wood(0x000000).leaves(0xb54c36)
                    .feature(TreeMaker::makeLeafTree).build());
    public static final RegisteredTree beech = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "beech", "Beech").wood(0x000000).log(0x000000).leaves(0xaadb69)
                    .feature((log, leaves) -> TreeMaker.makeRoundedLeafTree(log, leaves, 5, 2, 3)).build());
    public static final RegisteredTree cherry = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "cherry", "Cherry").wood(0x000000).log(0x000000).leaves(0x81ba56)
                    .feature((log, leaves) -> TreeMaker.makeRoundedLeafTree(log, leaves, 6, 2, 4)).build());
    public static final RegisteredTree alder = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "alder", "Alder").wood(0x000000).log(0x000000).leaves(0x589926)
                    .feature((log, leaves) -> TreeMaker.makeRoundedLeafTree(log, leaves, 5, 2, 2)).build());
    public static final RegisteredTree chestnut = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "chestnut", "Chestnut").wood(0x000000).log(0x000000)
                    .leaves(0x6a9956).biome(new BiomeSettings.Builder().temperature(0.5f).snow())
                    .feature((log, leaves) -> TreeMaker.makeSmallLeafTree(log, leaves, 5, 3, 2, 4)).build());
    public static final RegisteredTree plane = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "plane", "Plane").wood(0x000000).log(0x000000).leaves(0x8cb856)
                    .biome(new BiomeSettings.Builder().temperature(0.8f)).feature(TreeMaker::makePlaneTree).build());
    public static final RegisteredTree ash = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "ash", "Ash").wood(0x000000).log(0x000000).leaves(0x79a348)
                    .feature((log, leaves) -> TreeMaker
                            .makeAshTree(log, leaves, 7, 5, FeatureSpread.func_242253_a(1, 1),
                                    FeatureSpread.func_242253_a(4, 2), FeatureSpread.func_242253_a(2, 1))).build());
    public static final RegisteredTree linden = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "linden", "Linden").wood(0x000000).log(0x000000).leaves(0x79a348)
                    .feature(TreeMaker::makeFancyLeafTree).build());
    public static final RegisteredTree robinia = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "robinia", "Robinia").wood(0x000000).leaves(0x97bf50)
                    .feature(TreeMaker::makeAcaciaLeafTree).biome(new BiomeSettings.Builder().temperature(1.0f).dry())
                    .build());
    public static final RegisteredTree willow = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "willow", "Willow").log(0x000000).wood(0x000000).leaves(0x75b354)
                    .feature(TreeMaker::makeWillowLeafTree).biome(new BiomeSettings.Builder().temperature(0.6f))
                    .build());
    public static final RegisteredTree pomegranate = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "pomegranate", "Pomegranate").log(0x000000).wood(0x000000)
                    .leaves(0x7dab48).feature((log, leaves) -> TreeMaker
                    .makeAshTree(log, leaves, 4, 2, FeatureSpread.func_242253_a(0, 2),
                            FeatureSpread.func_242253_a(3, 1), FeatureSpread.func_242253_a(2, 1)))
                    .apple(ExtraRegistry.POMEGRANATE::get).biome(new BiomeSettings.Builder().temperature(1.0f).dry())
                    .build());
    public static final RegisteredTree magnolia = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "magnolia", "Magnolia").log(0x000000).wood(0x000000)
                    .leaves(0xFFFFFF).feature((log, leaves) -> TreeMaker.makeCrossBlobTree(log, leaves, 6, 3, 2, 3))
                    .build());
    public static final RegisteredTree walnut = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "walnut", "Walnut").leaves(0x6ba147).feature(
                    (log, leaves) -> TreeMaker
                            .makeCrossRoundTree(log, leaves, 6, 3, 3, FeatureSpread.func_242253_a(1, 1), 3)).build());
    public static final RegisteredTree cedar = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "cedar", "Cedar").leaves(0x5ea148).wood(0x000000).feature(
                    (log, leaves) -> TreeMaker.makePointyTree(log, leaves, FeatureSpread.func_242252_a(37),
                            FeatureSpread.func_242252_a(100), 1, 8, 5))
                    .biome(new BiomeSettings.Builder().density(8).temperature(0.5f).snow()).build());
    public static final RegisteredTree poplar = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "poplar", "Poplar").leaves(0x81ba56).wood(0x000000).feature(
                    (log, leaves) -> TreeMaker.makeAshTree(log, leaves, 8, 6, FeatureSpread.func_242253_a(0, 1),
                            FeatureSpread.func_242253_a(4, 2), FeatureSpread.func_242253_a(3, 1)))
                    .biome(new BiomeSettings.Builder().density(8)).build());
    public static final RegisteredTree elm = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "elm", "Elm").leaves(0xaed162).feature(TreeMaker::makeBallTree)
                    .biome(new BiomeSettings.Builder().density(5)).build());
    public static final RegisteredTree rainbow_eucalyptus = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "rainbow_eucalyptus", "Rainbow Eucalyptus")
                    .leaves(blockPos -> blockPos != null ? Color.HSBtoRGB(
                            (float) (Math.pow((blockPos.getX() % 32) / 32.0f, 2) + Math
                                    .pow((blockPos.getY() % 32) / 32.0f, 2) + Math
                                    .pow((blockPos.getZ() % 32) / 32.0f, 2)), 0.7f, 0.8f) : Color.HSBtoRGB(0, 1, 1))
                    .feature(TreeMaker::makeBallTree).biome(new BiomeSettings.Builder().density(5)).build());
    public static final RegisteredTree juniper = registerTree(
            TreeBuilder.create(BLOCKS, ITEMS, BIOMES, "juniper", "Juniper").leaves(0x5ead55).wood(0x000000).feature(
                    (log, leaves) -> TreeMaker.makeJuniperTree(log, leaves, FeatureSpread.func_242252_a(42),
                            FeatureSpread.func_242252_a(100), 2, 12, 6))
                    .biome(new BiomeSettings.Builder().density(8).temperature(0.5f).snow()).build());

    private static RegisteredTree registerTree(RegisteredTree tree) {
        TREES.add(tree);
        return tree;
    }

}
