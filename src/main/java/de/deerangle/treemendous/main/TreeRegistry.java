package de.deerangle.treemendous.main;

import de.deerangle.treemendous.tree.FakeTree;
import de.deerangle.treemendous.tree.Tree;
import de.deerangle.treemendous.tree.config.TreeConfig;
import de.deerangle.treemendous.tree.config.TreeConfigBuilder;
import de.deerangle.treemendous.tree.util.RainbowLeavesColor;
import de.deerangle.treemendous.util.WoodColors;
import de.deerangle.treemendous.world.TreeMaker;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TreeRegistry
{

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Treemendous.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Treemendous.MODID);

    private static final TreeConfig DOUGLAS_CONFIG = new TreeConfigBuilder("douglas", WoodColors.DOUGLAS_LEAVES, true, WoodColors.DOUGLAS_WOOD, WoodColors.DOUGLAS_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeDouglasTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig PINE_CONFIG = new TreeConfigBuilder("pine", WoodColors.PINE_LEAVES, true, WoodColors.PINE_WOOD, WoodColors.PINE_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makePineTree(leaves, wood, sapling))).addTree(1, true, ((leaves, wood, sapling, tree) -> TreeMaker.makeMegaPineTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig LARCH_CONFIG = new TreeConfigBuilder("larch", WoodColors.LARCH_LEAVES, true, WoodColors.LARCH_WOOD, WoodColors.LARCH_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeLarchTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig FIR_CONFIG = new TreeConfigBuilder("fir", WoodColors.FIR_LEAVES, true, WoodColors.FIR_WOOD, WoodColors.FIR_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeFirTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig MAPLE_CONFIG = new TreeConfigBuilder("maple", WoodColors.MAPLE_LEAVES, false, WoodColors.MAPLE_WOOD, WoodColors.MAPLE_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeMapleTree(leaves, wood, sapling))).add()
            .extraSapling("red").setLeaves(tree -> ExtraRegistry.MAPLE_RED_LEAVES.get()).addTree((leaves, wood, sapling, tree) -> TreeMaker.makeMapleTree(leaves, wood, sapling)).add()
            .extraSapling("brown").setLeaves(tree -> ExtraRegistry.MAPLE_BROWN_LEAVES.get()).addTree((leaves, wood, sapling, tree) -> TreeMaker.makeMapleTree(leaves, wood, sapling)).add()
            .createTreeConfig();
    private static final TreeConfig JAPANESE_CONFIG = new TreeConfigBuilder("japanese", WoodColors.JAPANESE_LEAVES, true, WoodColors.JAPANESE_WOOD, WoodColors.JAPANESE_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeMapleTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig BEECH_CONFIG = new TreeConfigBuilder("beech", WoodColors.BEECH_LEAVES, false, WoodColors.BEECH_WOOD, WoodColors.BEECH_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeBeechTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig CHERRY_CONFIG = new TreeConfigBuilder("cherry", WoodColors.CHERRY_LEAVES, false, WoodColors.CHERRY_WOOD, WoodColors.CHERRY_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeCherryTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig ALDER_CONFIG = new TreeConfigBuilder("alder", WoodColors.ALDER_LEAVES, false, WoodColors.ALDER_WOOD, WoodColors.ALDER_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeAlderTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig CHESTNUT_CONFIG = new TreeConfigBuilder("chestnut", WoodColors.CHESTNUT_LEAVES, false, WoodColors.CHESTNUT_WOOD, WoodColors.CHESTNUT_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeChestnutTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig PLANE_CONFIG = new TreeConfigBuilder("plane", WoodColors.PLANE_LEAVES, false, WoodColors.PLANE_WOOD, WoodColors.PLANE_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makePlaneTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig ASH_CONFIG = new TreeConfigBuilder("ash", WoodColors.ASH_LEAVES, false, WoodColors.ASH_WOOD, WoodColors.ASH_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeAshTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig LINDEN_CONFIG = new TreeConfigBuilder("linden", WoodColors.LINDEN_LEAVES, false, WoodColors.LINDEN_WOOD, WoodColors.LINDEN_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeLindenTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig ROBINIA_CONFIG = new TreeConfigBuilder("robinia", WoodColors.ROBINIA_LEAVES, false, WoodColors.ROBINIA_WOOD, WoodColors.ROBINIA_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeRobiniaTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig WILLOW_CONFIG = new TreeConfigBuilder("willow", WoodColors.WILLOW_LEAVES, false, WoodColors.WILLOW_WOOD, WoodColors.WILLOW_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeWillowTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig POMEGRANATE_CONFIG = new TreeConfigBuilder("pomegranate", WoodColors.POMEGRANATE_LEAVES, false, WoodColors.POMEGRANATE_WOOD, WoodColors.POMEGRANATE_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makePomegranateTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig WALNUT_CONFIG = new TreeConfigBuilder("walnut", WoodColors.WALNUT_LEAVES, false, WoodColors.WALNUT_WOOD, WoodColors.WALNUT_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeWalnutTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig CEDAR_CONFIG = new TreeConfigBuilder("cedar", WoodColors.CEDAR_LEAVES, true, WoodColors.CEDAR_WOOD, WoodColors.CEDAR_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeCedarTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig POPLAR_CONFIG = new TreeConfigBuilder("poplar", WoodColors.POPLAR_LEAVES, false, WoodColors.POPLAR_WOOD, WoodColors.POPLAR_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makePoplarTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig ELM_CONFIG = new TreeConfigBuilder("elm", WoodColors.ELM_LEAVES, false, WoodColors.ELM_WOOD, WoodColors.ELM_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeElmTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig JUNIPER_CONFIG = new TreeConfigBuilder("juniper", WoodColors.JUNIPER_LEAVES, true, WoodColors.JUNIPER_WOOD, WoodColors.JUNIPER_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeJuniperTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig MAGNOLIA_CONFIG = new TreeConfigBuilder("magnolia", WoodColors.MAGNOLIA_LEAVES, true, WoodColors.MAGNOLIA_WOOD, WoodColors.MAGNOLIA_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeMagnoliaTree(leaves, wood, sapling))).add()
            .createTreeConfig();
    private static final TreeConfig RAINBOW_EUCALYPTUS_CONFIG = new TreeConfigBuilder("rainbow_eucalyptus", new RainbowLeavesColor(), MaterialColor.WOOD, MaterialColor.TERRACOTTA_BLACK)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeRainbowEucalyptusTree(leaves, wood, sapling))).add()
            .createTreeConfig();

    public static final Tree DOUGLAS_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, DOUGLAS_CONFIG));
    public static final Tree PINE_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, PINE_CONFIG));
    public static final Tree LARCH_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, LARCH_CONFIG));
    public static final Tree FIR_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, FIR_CONFIG));
    public static final Tree MAPLE_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, MAPLE_CONFIG));
    public static final Tree JAPANESE_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, JAPANESE_CONFIG));
    public static final Tree BEECH_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, BEECH_CONFIG));
    public static final Tree CHERRY_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, CHERRY_CONFIG));
    public static final Tree ALDER_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, ALDER_CONFIG));
    public static final Tree CHESTNUT_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, CHESTNUT_CONFIG));
    public static final Tree PLANE_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, PLANE_CONFIG));
    public static final Tree ASH_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, ASH_CONFIG));
    public static final Tree LINDEN_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, LINDEN_CONFIG));
    public static final Tree ROBINIA_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, ROBINIA_CONFIG));
    public static final Tree WILLOW_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, WILLOW_CONFIG));
    public static final Tree POMEGRANATE_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, POMEGRANATE_CONFIG));
    public static final Tree WALNUT_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, WALNUT_CONFIG));
    public static final Tree CEDAR_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, CEDAR_CONFIG));
    public static final Tree POPLAR_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, POPLAR_CONFIG));
    public static final Tree ELM_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, ELM_CONFIG));
    public static final Tree JUNIPER_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, JUNIPER_CONFIG));
    public static final Tree MAGNOLIA_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, MAGNOLIA_CONFIG));
    public static final Tree RAINBOW_EUCALYPTUS_TREE = ExtraRegistry.registerTree(Tree.fromConfig(BLOCKS, ITEMS, RAINBOW_EUCALYPTUS_CONFIG));
    //TODO: Mahogany, Fruit Trees, Redwood, Teakbaum, Aspen, Ebony, Palmtree (Öl, Kokos, Date, Royal), Eucalyptus, Olive

    public static final FakeTree OAK_TREE = new FakeTree(
            Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG, Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD, Blocks.OAK_SAPLING, () -> (ChestBlock) Blocks.CHEST, () -> (CraftingTableBlock) Blocks.CRAFTING_TABLE, Blocks.OAK_BUTTON, Blocks.OAK_DOOR, Blocks.OAK_FENCE, Blocks.OAK_FENCE_GATE, Blocks.OAK_PRESSURE_PLATE, Blocks.OAK_SLAB, Blocks.OAK_STAIRS, Blocks.OAK_TRAPDOOR, Blocks.OAK_WALL_SIGN, Blocks.OAK_PLANKS
    );
    public static final FakeTree BIRCH_TREE = new FakeTree(
            Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG, Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD, Blocks.BIRCH_SAPLING, ExtraRegistry.BIRCH_CHEST::get, ExtraRegistry.BIRCH_CRAFTING_TABLE::get, Blocks.BIRCH_BUTTON, Blocks.BIRCH_DOOR, Blocks.BIRCH_FENCE, Blocks.BIRCH_FENCE_GATE, Blocks.BIRCH_PRESSURE_PLATE, Blocks.BIRCH_SLAB, Blocks.BIRCH_STAIRS, Blocks.BIRCH_TRAPDOOR, Blocks.BIRCH_WALL_SIGN, Blocks.BIRCH_PLANKS
    );
    public static final FakeTree SPRUCE_TREE = new FakeTree(
            Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG, Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD, Blocks.SPRUCE_SAPLING, ExtraRegistry.SPRUCE_CHEST::get, ExtraRegistry.SPRUCE_CRAFTING_TABLE::get, Blocks.SPRUCE_BUTTON, Blocks.SPRUCE_DOOR, Blocks.SPRUCE_FENCE, Blocks.SPRUCE_FENCE_GATE, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.SPRUCE_SLAB, Blocks.SPRUCE_STAIRS, Blocks.SPRUCE_TRAPDOOR, Blocks.SPRUCE_WALL_SIGN, Blocks.SPRUCE_PLANKS
    );
    public static final FakeTree JUNGLE_TREE = new FakeTree(
            Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG, Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD, Blocks.JUNGLE_SAPLING, ExtraRegistry.JUNGLE_CHEST::get, ExtraRegistry.JUNGLE_CRAFTING_TABLE::get, Blocks.JUNGLE_BUTTON, Blocks.JUNGLE_DOOR, Blocks.JUNGLE_FENCE, Blocks.JUNGLE_FENCE_GATE, Blocks.JUNGLE_PRESSURE_PLATE, Blocks.JUNGLE_SLAB, Blocks.JUNGLE_STAIRS, Blocks.JUNGLE_TRAPDOOR, Blocks.JUNGLE_WALL_SIGN, Blocks.JUNGLE_PLANKS
    );
    public static final FakeTree ACACIA_TREE = new FakeTree(
            Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG, Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD, Blocks.ACACIA_SAPLING, ExtraRegistry.ACACIA_CHEST::get, ExtraRegistry.ACACIA_CRAFTING_TABLE::get, Blocks.ACACIA_BUTTON, Blocks.ACACIA_DOOR, Blocks.ACACIA_FENCE, Blocks.ACACIA_FENCE_GATE, Blocks.ACACIA_PRESSURE_PLATE, Blocks.ACACIA_SLAB, Blocks.ACACIA_STAIRS, Blocks.ACACIA_TRAPDOOR, Blocks.ACACIA_WALL_SIGN, Blocks.ACACIA_PLANKS
    );
    public static final FakeTree DARK_OAK_TREE = new FakeTree(
            Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.DARK_OAK_SAPLING, ExtraRegistry.DARK_OAK_CHEST::get, ExtraRegistry.DARK_OAK_CRAFTING_TABLE::get, Blocks.DARK_OAK_BUTTON, Blocks.DARK_OAK_DOOR, Blocks.DARK_OAK_FENCE, Blocks.DARK_OAK_FENCE_GATE, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_STAIRS, Blocks.DARK_OAK_TRAPDOOR, Blocks.DARK_OAK_WALL_SIGN, Blocks.DARK_OAK_PLANKS
    );

}
