package deerangle.treemendous.main;

import deerangle.treemendous.block.entity.CustomSignTileEntity;
import deerangle.treemendous.entity.CustomBoatEntity;
import deerangle.treemendous.tree.RegisteredTree;
import net.minecraft.block.Block;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class TreeRegistry {

    private static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeNeedleTree(Block log, Block leaves) {
        return Feature.TREE.withConfiguration(
                new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new SpruceFoliagePlacer(FeatureSpread.func_242253_a(2, 1), FeatureSpread.func_242253_a(0, 2),
                                FeatureSpread.func_242253_a(1, 1)), new StraightTrunkPlacer(5, 2, 1),
                        new TwoLayerFeature(2, 0, 2)).setIgnoreVines().build());
    }

    private static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeSmallLeafTree(Block log, Block leaves, int baseHeight, int extraHeight, int crownWidth) {
        return Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(log.getDefaultState()),
                        new SimpleBlockStateProvider(leaves.getDefaultState()),
                        new BlobFoliagePlacer(FeatureSpread.func_242252_a(crownWidth), FeatureSpread.func_242252_a(0),
                                3), new StraightTrunkPlacer(baseHeight, extraHeight, 0), new TwoLayerFeature(1, 0, 1)))
                        .setIgnoreVines().build());
    }

    private static ConfiguredFeature<BaseTreeFeatureConfig, ?> makeSmallLeafTree(Block log, Block leaves) {
        return makeSmallLeafTree(log, leaves, 5, 2, 2);
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
                    .leaves(0x9bd95d).feature((log, leaves) -> makeSmallLeafTree(log, leaves, 6, 2, 3)).build());
    public static final RegisteredTree red_maple = registerTree(
            RegisteredTree.Builder.create(BLOCKS, ITEMS, BIOMES, "red_maple", "Red Maple").inheritWood(maple)
                    .wood(MaterialColor.DIRT).leaves(0xcc764e).feature((log, leaves) -> makeSmallLeafTree(log, leaves, 6, 2, 3)).build());
    public static final RegisteredTree brown_maple = registerTree(
            RegisteredTree.Builder.create(BLOCKS, ITEMS, BIOMES, "brown_maple", "Brown Maple").inheritWood(maple)
                    .wood(MaterialColor.DIRT).leaves(0xd9c25d)
                    .feature((log, leaves) -> makeSmallLeafTree(log, leaves, 6, 2, 3)).build());
    public static final RegisteredTree japanese = registerTree(
            RegisteredTree.Builder.create(BLOCKS, ITEMS, BIOMES, "japanese", "Japanese Maple").wood(MaterialColor.PINK)
                    .leaves(0xb54c36).feature(TreeRegistry::makeSmallLeafTree).build());
    /*
    public static final RegisteredTree beech = registerTree(new RegisteredTree(BLOCKS, ITEMS, "beech", "Beech", MaterialColor.WOOD,
            MaterialColor.PINK));
    public static final RegisteredTree alder = registerTree(new RegisteredTree(BLOCKS, ITEMS, "alder", "Alder", MaterialColor.WOOD,
            MaterialColor.PINK));
    public static final RegisteredTree chestnut = registerTree(new RegisteredTree(BLOCKS, ITEMS, "chestnut", "Chestnut", MaterialColor.WOOD,
            MaterialColor.PINK));
    public static final RegisteredTree linden = registerTree(new RegisteredTree(BLOCKS, ITEMS, "linden", "Linden", MaterialColor.WOOD,
            MaterialColor.PINK));
    public static final RegisteredTree plane = registerTree(new RegisteredTree(BLOCKS, ITEMS, "plane", "Plane", MaterialColor.WOOD,
            MaterialColor.PINK));
    public static final RegisteredTree ash = registerTree(new RegisteredTree(BLOCKS, ITEMS, "ash", "Ash", MaterialColor.WOOD,
            MaterialColor.PINK));
    public static final RegisteredTree robinia = registerTree(new RegisteredTree(BLOCKS, ITEMS, "robinia", "Robinia", MaterialColor.WOOD,
            MaterialColor.PINK));
    public static final RegisteredTree willow = registerTree(new RegisteredTree(BLOCKS, ITEMS, "willow", "Willow", MaterialColor.WOOD,
            MaterialColor.PINK));
    public static final RegisteredTree cherry = registerTree(new RegisteredTree(BLOCKS, ITEMS, "cherry", "Cherry", MaterialColor.WOOD,
            MaterialColor.PINK));
     */

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
