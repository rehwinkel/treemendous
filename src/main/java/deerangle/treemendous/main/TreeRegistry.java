package deerangle.treemendous.main;

import com.mojang.serialization.Codec;
import deerangle.treemendous.block.entity.CustomSignTileEntity;
import deerangle.treemendous.entity.CustomBoatEntity;
import deerangle.treemendous.tree.RegisteredTree;
import deerangle.treemendous.tree.RoundedFoliagePlacer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.*;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class TreeRegistry {

    public static final FoliagePlacerType<RoundedFoliagePlacer> ROUNDED_FOLIAGE_PLACER = registerFoliagePlacerType(
            "rounded_foliage_placer", RoundedFoliagePlacer.CODEC);

    private static <P extends FoliagePlacer> FoliagePlacerType<P> registerFoliagePlacerType(String name, Codec<P> codec) {
        return Registry
                .register(Registry.FOLIAGE_PLACER_TYPE, Treemendous.MODID + ":" + name, new FoliagePlacerType<>(codec));
    }

    private static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeNeedleTree(Block log, Block leaves) {
        return Feature.TREE.withConfiguration(
                new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new SpruceFoliagePlacer(FeatureSpread.func_242253_a(2, 1), FeatureSpread.func_242253_a(0, 2),
                                FeatureSpread.func_242253_a(1, 1)), new StraightTrunkPlacer(5, 2, 1),
                        new TwoLayerFeature(2, 0, 2)).setIgnoreVines().build());
    }

    private static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeSmallLeafTree(Block log, Block leaves, int baseHeight, int extraHeight, int crownWidth, int crownHeight) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new BlobFoliagePlacer(FeatureSpread.func_242252_a(crownWidth), FeatureSpread.func_242252_a(0),
                                crownHeight), new StraightTrunkPlacer(baseHeight, extraHeight, 0),
                        new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build());
    }

    private static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeRoundedLeafTree(Block log, Block leaves, int baseHeight, int extraHeight, int roundedIndex) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new RoundedFoliagePlacer(FeatureSpread.func_242252_a(4), FeatureSpread.func_242252_a(0),
                                roundedIndex), new StraightTrunkPlacer(baseHeight, extraHeight, 0),
                        new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build());
    }

    private static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeAcaciaLeafTree(Block log, Block leaves) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new AcaciaFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0)),
                        new ForkyTrunkPlacer(4, 1, 2), new TwoLayerFeature(1, 0, 2))).setIgnoreVines().build());
    }

    private static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeSmallLeafTree(Block log, Block leaves) {
        return makeSmallLeafTree(log, leaves, 5, 2, 2, 3);
    }

    private static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeFancyLeafTree(Block log, Block leaves) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new FancyFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(4), 4),
                        new FancyTrunkPlacer(6, 3, 1), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4))))
                        .setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build());
    }

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister
            .create(ForgeRegistries.BLOCKS, Treemendous.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister
            .create(ForgeRegistries.ITEMS, Treemendous.MODID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister
            .create(ForgeRegistries.TILE_ENTITIES, Treemendous.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister
            .create(ForgeRegistries.ENTITIES, Treemendous.MODID);
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister
            .create(ForgeRegistries.BIOMES, Treemendous.MODID);

    public static final List<RegisteredTree> trees = new ArrayList<>();

    public static final RegisteredTree douglas = registerTree(
            RegisteredTree.Builder.create(BLOCKS, ITEMS, BIOMES, "douglas", "Douglas Fir").wood(MaterialColor.DIRT)
                    .log(MaterialColor.STONE).leaves(0x78a65d).feature(TreeRegistry::makeNeedleTree)
                    .biome(new RegisteredTree.BiomeSettings.Builder().temperature(0.4f).snow()).build());
    public static final RegisteredTree pine = registerTree(
            RegisteredTree.Builder.create(BLOCKS, ITEMS, BIOMES, "pine", "Pine").wood(MaterialColor.SAND)
                    .leaves(0x486942).feature(TreeRegistry::makeNeedleTree)
                    .biome(new RegisteredTree.BiomeSettings.Builder().temperature(0.4f).snow()).build());
    public static final RegisteredTree larch = registerTree(
            RegisteredTree.Builder.create(BLOCKS, ITEMS, BIOMES, "larch", "Larch").leaves(0x486942)
                    .feature(TreeRegistry::makeNeedleTree)
                    .biome(new RegisteredTree.BiomeSettings.Builder().temperature(0.4f).snow()).build());
    public static final RegisteredTree fir = registerTree(
            RegisteredTree.Builder.create(BLOCKS, ITEMS, BIOMES, "fir", "Fir").log(MaterialColor.OBSIDIAN)
                    .leaves(0x5a914e).feature(TreeRegistry::makeNeedleTree)
                    .biome(new RegisteredTree.BiomeSettings.Builder().temperature(0.4f).snow()).build());
    public static final RegisteredTree maple = registerTree(
            RegisteredTree.Builder.create(BLOCKS, ITEMS, BIOMES, "maple", "Maple").wood(MaterialColor.DIRT)
                    .leaves(0x9bd95d).feature((log, leaves) -> makeSmallLeafTree(log, leaves, 6, 2, 3, 3)).build());
    public static final RegisteredTree red_maple = registerTree(
            RegisteredTree.Builder.create(BLOCKS, ITEMS, BIOMES, "red_maple", "Red Maple").inheritWood(maple)
                    .wood(MaterialColor.DIRT).leaves(0xcc764e)
                    .feature((log, leaves) -> makeSmallLeafTree(log, leaves, 6, 2, 3, 3)).build());
    public static final RegisteredTree brown_maple = registerTree(
            RegisteredTree.Builder.create(BLOCKS, ITEMS, BIOMES, "brown_maple", "Brown Maple").inheritWood(maple)
                    .wood(MaterialColor.DIRT).leaves(0xd9c25d)
                    .feature((log, leaves) -> makeSmallLeafTree(log, leaves, 6, 2, 3, 3)).build());
    public static final RegisteredTree japanese = registerTree(
            RegisteredTree.Builder.create(BLOCKS, ITEMS, BIOMES, "japanese", "Japanese Maple").wood(MaterialColor.PINK)
                    .leaves(0xb54c36).feature(TreeRegistry::makeSmallLeafTree).build());
    public static final RegisteredTree beech = registerTree(
            RegisteredTree.Builder.create(BLOCKS, ITEMS, BIOMES, "beech", "Beech").wood(MaterialColor.GREEN)
                    .log(MaterialColor.STONE).leaves(0xaadb69)
                    .feature((log, leaves) -> makeRoundedLeafTree(log, leaves, 5, 2, 3)).build());
    public static final RegisteredTree cherry = registerTree(
            RegisteredTree.Builder.create(BLOCKS, ITEMS, BIOMES, "cherry", "Cherry").wood(MaterialColor.RED)
                    .log(MaterialColor.STONE).leaves(0x81ba56)
                    .feature((log, leaves) -> makeRoundedLeafTree(log, leaves, 6, 2, 4)).build());
    public static final RegisteredTree alder = registerTree(
            RegisteredTree.Builder.create(BLOCKS, ITEMS, BIOMES, "alder", "Alder").wood(MaterialColor.STONE)
                    .log(MaterialColor.STONE).leaves(0x589926)
                    .feature((log, leaves) -> makeRoundedLeafTree(log, leaves, 5, 2, 2)).build());
    public static final RegisteredTree chestnut = registerTree(
            RegisteredTree.Builder.create(BLOCKS, ITEMS, BIOMES, "chestnut", "Chestnut").wood(MaterialColor.BROWN)
                    .log(MaterialColor.OBSIDIAN).leaves(0x6a9956)
                    .biome(new RegisteredTree.BiomeSettings.Builder().temperature(0.5f).snow())
                    .feature((log, leaves) -> makeSmallLeafTree(log, leaves, 5, 3, 2, 4)).build());
    public static final RegisteredTree plane = registerTree(
            RegisteredTree.Builder.create(BLOCKS, ITEMS, BIOMES, "plane", "Plane").wood(MaterialColor.BLUE)
                    .log(MaterialColor.PURPLE).leaves(0x8cb856)
                    .biome(new RegisteredTree.BiomeSettings.Builder().temperature(0.8f))
                    .feature((log, leaves) -> makeFancyLeafTree(log, leaves)).build());
    public static final RegisteredTree ash = registerTree(
            RegisteredTree.Builder.create(BLOCKS, ITEMS, BIOMES, "ash", "Ash").wood(MaterialColor.ORANGE_TERRACOTTA)
                    .log(MaterialColor.BLACK).leaves(0x79a348)
                    .feature((log, leaves) -> makeSmallLeafTree(log, leaves, 5, 3, 2, 2)).build());
    public static final RegisteredTree linden = registerTree(
            RegisteredTree.Builder.create(BLOCKS, ITEMS, BIOMES, "linden", "Linden")
                    .wood(MaterialColor.ORANGE_TERRACOTTA).log(MaterialColor.BLACK).leaves(0x79a348)
                    .feature((log, leaves) -> makeSmallLeafTree(log, leaves)).build());
    public static final RegisteredTree robinia = registerTree(
            RegisteredTree.Builder.create(BLOCKS, ITEMS, BIOMES, "robinia", "Robinia").log(MaterialColor.GOLD)
                    .leaves(0x97bf50).feature((log, leaves) -> makeAcaciaLeafTree(log, leaves))
                    .biome(new RegisteredTree.BiomeSettings.Builder().temperature(1.0f).dry()).build());

    /*
    public static final RegisteredTree willow = registerTree(new RegisteredTree(BLOCKS, ITEMS, "willow", "Willow", MaterialColor.WOOD,
            MaterialColor.PINK));
     */

    public static RegistryObject<Block> rp = BLOCKS
            .register("rainbow_eukalyptus_planks", () -> new Block(AbstractBlock.Properties.create(Material.WOOD)));

    private static RegisteredTree registerTree(RegisteredTree tree) {
        trees.add(tree);
        return tree;
    }

    public static final RegistryObject<TileEntityType<CustomSignTileEntity>> SIGN = TILE_ENTITIES
            .register("sign", () -> {
                Block[] signs = new Block[trees.size() * 2];
                for (int i = 0; i < trees.size(); i++) {
                    signs[2 * i] = trees.get(i).sign.get();
                    signs[2 * i + 1] = trees.get(i).wall_sign.get();
                }
                return TileEntityType.Builder.create(CustomSignTileEntity::new, signs).build(null);
            });

    public static final RegistryObject<EntityType<CustomBoatEntity>> BOAT = ENTITIES.register("boat",
            () -> EntityType.Builder.<CustomBoatEntity>create(CustomBoatEntity::new, EntityClassification.MISC)
                    .size(1.375F, 0.5625F).trackingRange(10)
                    .setCustomClientFactory((spawnEntity, world) -> new CustomBoatEntity(world)).build("boat"));

}
