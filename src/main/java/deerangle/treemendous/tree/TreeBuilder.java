package deerangle.treemendous.tree;

import net.minecraft.block.Block;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class TreeBuilder {
    private final DeferredRegister<Item> itemRegistry;
    private final DeferredRegister<Block> blockRegistry;
    private final DeferredRegister<Biome> biomeRegistry;
    private final String name;
    private final String englishName;
    private MaterialColor woodColor;
    private MaterialColor logColor;
    private ILeavesColor leavesColor;
    private Supplier<IItemProvider> apple;
    private RegisteredTree inherit;
    private BiFunction<Block, Block, ConfiguredFeature<BaseTreeFeatureConfig, ?>> feature;
    private BiomeSettings biomeSettings;

    private TreeBuilder(DeferredRegister<Block> BLOCKS, DeferredRegister<Item> ITEMS, DeferredRegister<Biome> BIOMES, String name, String englishName) {
        this.biomeRegistry = BIOMES;
        this.itemRegistry = ITEMS;
        this.blockRegistry = BLOCKS;
        this.name = name;
        this.englishName = englishName;
        this.woodColor = MaterialColor.WOOD;
        this.logColor = MaterialColor.BROWN;
        this.leavesColor = (blockPos) -> 8431445;
        this.apple = null;
        this.inherit = null;
        this.feature = (log, leaves) -> null;
        this.biomeSettings = new BiomeSettings.Builder().build();
    }

    public static TreeBuilder create(DeferredRegister<Block> BLOCKS, DeferredRegister<Item> ITEMS, DeferredRegister<Biome> BIOMES, String id, String name) {
        return new TreeBuilder(BLOCKS, ITEMS, BIOMES, id, name);
    }

    public TreeBuilder wood(MaterialColor color) {
        this.woodColor = color;
        return this;
    }

    public TreeBuilder log(MaterialColor color) {
        this.logColor = color;
        return this;
    }

    public TreeBuilder leaves(int color) {
        this.leavesColor = (blockPos) -> color;
        return this;
    }

    public TreeBuilder leaves(ILeavesColor color) {
        this.leavesColor = color;
        return this;
    }

    public TreeBuilder apple(Supplier<IItemProvider> apple) {
        this.apple = apple;
        return this;
    }

    public RegisteredTree build() {
        return new RegisteredTree(this.blockRegistry, this.itemRegistry, this.biomeRegistry, this.name,
                this.englishName, this.woodColor, this.logColor, this.leavesColor, this.apple, this.inherit,
                this.feature, this.biomeSettings);
    }

    public TreeBuilder biome(BiomeSettings.Builder settings) {
        this.biomeSettings = settings.build();
        return this;
    }

    public TreeBuilder inheritWood(RegisteredTree inherit) {
        this.inherit = inherit;
        return this;
    }

    public TreeBuilder feature(BiFunction<Block, Block, ConfiguredFeature<BaseTreeFeatureConfig, ?>> feature) {
        this.feature = feature;
        return this;
    }
}
