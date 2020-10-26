package deerangle.treemendous.tree;

import deerangle.treemendous.world.BiomeSettings;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TreeBuilder {
    private final DeferredRegister<Item> itemRegistry;
    private final DeferredRegister<Block> blockRegistry;
    private final DeferredRegister<Biome> biomeRegistry;
    private final String name;
    private final String englishName;
    private int woodColor;
    private int logColor;
    private int plankType;
    private ILeavesColor leavesColor;
    private Supplier<IItemProvider> apple;
    private RegisteredTree inherit;
    private IConfigProvider configProvider;
    private Supplier<? extends Feature<? extends TreeFeatureConfig>> feature;
    private BiomeSettings biomeSettings;

    private TreeBuilder(DeferredRegister<Block> BLOCKS, DeferredRegister<Item> ITEMS, DeferredRegister<Biome> BIOMES, String name, String englishName) {
        this.biomeRegistry = BIOMES;
        this.itemRegistry = ITEMS;
        this.blockRegistry = BLOCKS;
        this.name = name;
        this.englishName = englishName;
        this.woodColor = 0;
        this.logColor = 0;
        this.plankType = 0;
        this.leavesColor = (blockPos) -> 0x80a755;
        this.apple = null;
        this.inherit = null;
        this.configProvider = (log, leaves, sapling) -> null;
        this.feature = null;
        this.biomeSettings = new BiomeSettings.Builder().build();
    }

    public static TreeBuilder create(DeferredRegister<Block> BLOCKS, DeferredRegister<Item> ITEMS, DeferredRegister<Biome> BIOMES, String id, String name) {
        return new TreeBuilder(BLOCKS, ITEMS, BIOMES, id, name);
    }

    public TreeBuilder wood(int color) {
        this.woodColor = color;
        return this;
    }

    public TreeBuilder plankType(int type) {
        this.plankType = type;
        return this;
    }

    public TreeBuilder log(int color) {
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
                this.englishName, this.woodColor, this.logColor, this.plankType, this.leavesColor, this.apple,
                this.inherit, this.configProvider, this.feature, this.biomeSettings);
    }

    public TreeBuilder biome(BiomeSettings.Builder settings) {
        this.biomeSettings = settings.build();
        return this;
    }

    public TreeBuilder inheritWood(RegisteredTree inherit) {
        this.inherit = inherit;
        return this;
    }

    public TreeBuilder feature(IConfigProvider config, Feature<? extends TreeFeatureConfig> feature) {
        this.configProvider = config;
        this.feature = () -> feature;
        return this;
    }

    public TreeBuilder feature(IConfigProvider config, Supplier<? extends Feature<? extends TreeFeatureConfig>> feature) {
        this.configProvider = config;
        this.feature = feature;
        return this;
    }

}
