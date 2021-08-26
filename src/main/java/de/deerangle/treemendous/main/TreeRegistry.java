package de.deerangle.treemendous.main;

import de.deerangle.treemendous.tree.Tree;
import de.deerangle.treemendous.tree.TreeConfig;
import de.deerangle.treemendous.tree.TreeConfigBuilder;
import de.deerangle.treemendous.tree.WoodColors;
import de.deerangle.treemendous.world.TreeMaker;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TreeRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Treemendous.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Treemendous.MODID);

    private static final TreeConfig DOUGLAS_CONFIG = new TreeConfigBuilder("douglas", WoodColors.DOUGLAS_LEAVES, WoodColors.DOUGLAS_WOOD, WoodColors.DOUGLAS_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    private static final TreeConfig PINE_CONFIG = new TreeConfigBuilder("pine", WoodColors.PINE_LEAVES, WoodColors.PINE_WOOD, WoodColors.PINE_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    private static final TreeConfig LARCH_CONFIG = new TreeConfigBuilder("larch", WoodColors.LARCH_LEAVES, WoodColors.LARCH_WOOD, WoodColors.LARCH_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    private static final TreeConfig FIR_CONFIG = new TreeConfigBuilder("fir", WoodColors.FIR_LEAVES, WoodColors.FIR_WOOD, WoodColors.FIR_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    private static final TreeConfig MAPLE_CONFIG = new TreeConfigBuilder("maple", WoodColors.MAPLE_LEAVES, WoodColors.MAPLE_WOOD, WoodColors.MAPLE_LOG)
            .defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add()
            .extraSapling("red").setLeaves(tree -> Blocks.RED_WOOL).addTree((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling)).add()
            .extraSapling("brown").setLeaves(tree -> Blocks.BROWN_WOOL).addTree((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling)).add().createTreeConfig();
    private static final TreeConfig JAPANESE_CONFIG = new TreeConfigBuilder("japanese", WoodColors.JAPANESE_LEAVES, WoodColors.JAPANESE_WOOD, WoodColors.JAPANESE_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    private static final TreeConfig BEECH_CONFIG = new TreeConfigBuilder("beech", WoodColors.BEECH_LEAVES, WoodColors.BEECH_WOOD, WoodColors.BEECH_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    private static final TreeConfig CHERRY_CONFIG = new TreeConfigBuilder("cherry", WoodColors.CHERRY_LEAVES, WoodColors.CHERRY_WOOD, WoodColors.CHERRY_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    private static final TreeConfig ALDER_CONFIG = new TreeConfigBuilder("alder", WoodColors.ALDER_LEAVES, WoodColors.ALDER_WOOD, WoodColors.ALDER_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    private static final TreeConfig CHESTNUT_CONFIG = new TreeConfigBuilder("chestnut", WoodColors.CHESTNUT_LEAVES, WoodColors.CHESTNUT_WOOD, WoodColors.CHESTNUT_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    private static final TreeConfig PLANE_CONFIG = new TreeConfigBuilder("plane", WoodColors.PLANE_LEAVES, WoodColors.PLANE_WOOD, WoodColors.PLANE_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    private static final TreeConfig ASH_CONFIG = new TreeConfigBuilder("ash", WoodColors.ASH_LEAVES, WoodColors.ASH_WOOD, WoodColors.ASH_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    private static final TreeConfig LINDEN_CONFIG = new TreeConfigBuilder("linden", WoodColors.LINDEN_LEAVES, WoodColors.LINDEN_WOOD, WoodColors.LINDEN_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    private static final TreeConfig ROBINIA_CONFIG = new TreeConfigBuilder("robinia", WoodColors.ROBINIA_LEAVES, WoodColors.ROBINIA_WOOD, WoodColors.ROBINIA_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    private static final TreeConfig WILLOW_CONFIG = new TreeConfigBuilder("willow", WoodColors.WILLOW_LEAVES, WoodColors.WILLOW_WOOD, WoodColors.WILLOW_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    private static final TreeConfig POMEGRANATE_CONFIG = new TreeConfigBuilder("pomegranate", WoodColors.POMEGRANATE_LEAVES, WoodColors.POMEGRANATE_WOOD, WoodColors.POMEGRANATE_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    private static final TreeConfig WALNUT_CONFIG = new TreeConfigBuilder("walnut", WoodColors.WALNUT_LEAVES, WoodColors.WALNUT_WOOD, WoodColors.WALNUT_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    private static final TreeConfig CEDAR_CONFIG = new TreeConfigBuilder("cedar", WoodColors.CEDAR_LEAVES, WoodColors.CEDAR_WOOD, WoodColors.CEDAR_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    private static final TreeConfig POPLAR_CONFIG = new TreeConfigBuilder("poplar", WoodColors.POPLAR_LEAVES, WoodColors.POPLAR_WOOD, WoodColors.POPLAR_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    private static final TreeConfig ELM_CONFIG = new TreeConfigBuilder("elm", WoodColors.ELM_LEAVES, WoodColors.ELM_WOOD, WoodColors.ELM_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    private static final TreeConfig JUNIPER_CONFIG = new TreeConfigBuilder("juniper", WoodColors.JUNIPER_LEAVES, WoodColors.JUNIPER_WOOD, WoodColors.JUNIPER_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    //private static final TreeConfig RAINBOW_EUCALYPTUS_CONFIG = new TreeConfigBuilder("rainbow_eucalyptus", WoodColors.JUNIPER_LEAVES, WoodColors.JUNIPER_WOOD, WoodColors.JUNIPER_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    //private static final TreeConfig MAGNOLIA_CONFIG = new TreeConfigBuilder("magnolia", WoodColors.MAGNOLIA_LEAVES, WoodColors.MAGNOLIA_WOOD, WoodColors.MAGNOLIA_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    //private static final TreeConfig MAPLE_CONFIG = new TreeConfigBuilder("red_maple", WoodColors.RED_MAPLE_LEAVES, WoodColors.MAPLE_WOOD, WoodColors.MAPLE_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();
    //private static final TreeConfig MAPLE_CONFIG = new TreeConfigBuilder("brown_maple", WoodColors.BROWN_MAPLE_LEAVES, WoodColors.MAPLE_WOOD, WoodColors.MAPLE_LOG).defaultSapling().addTree(((leaves, wood, sapling, tree) -> TreeMaker.makeSomeTree(leaves, wood, sapling))).add().createTreeConfig();

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

}