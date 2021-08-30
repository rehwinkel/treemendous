package de.deerangle.treemendous.data;

import de.deerangle.treemendous.main.ExtraRegistry;
import de.deerangle.treemendous.main.TreeRegistry;
import de.deerangle.treemendous.tree.Tree;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.LanguageProvider;


public class TreemendousLanguageProvider extends LanguageProvider {

    private final String locale;

    public TreemendousLanguageProvider(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
        this.locale = locale;
    }

    @Override
    protected void addTranslations() {
        if (this.locale.equals("en_us")) {
            add("block.treemendous.chopping_block", "Chopping Block");
            add("entity.treemendous.boat", "Boat");
            add(ExtraRegistry.MAPLE_RED_LEAVES.get(), "Red Maple Leaves");
            add(ExtraRegistry.MAPLE_BROWN_LEAVES.get(), "Brown Maple Leaves");
            add("block.treemendous.maple_red_sapling", "Red Maple Sapling");
            add("block.treemendous.maple_brown_sapling", "Brown Maple Sapling");
            add(Blocks.CHEST, "Oak Chest");
            add(ExtraRegistry.BIRCH_CHEST.get(), "Birch Chest");
            add(ExtraRegistry.SPRUCE_CHEST.get(), "Spruce Chest");
            add(ExtraRegistry.JUNGLE_CHEST.get(), "Jungle Chest");
            add(ExtraRegistry.ACACIA_CHEST.get(), "Acacia Chest");
            add(ExtraRegistry.DARK_OAK_CHEST.get(), "Dark Oak Chest");
            add(ExtraRegistry.CRIMSON_CHEST.get(), "Crimson Chest");
            add(ExtraRegistry.WARPED_CHEST.get(), "Warped Chest");
            add(Blocks.CRAFTING_TABLE, "Oak Crafting Table");
            add(ExtraRegistry.BIRCH_CRAFTING_TABLE.get(), "Birch Crafting Table");
            add(ExtraRegistry.SPRUCE_CRAFTING_TABLE.get(), "Spruce Crafting Table");
            add(ExtraRegistry.JUNGLE_CRAFTING_TABLE.get(), "Jungle Crafting Table");
            add(ExtraRegistry.ACACIA_CRAFTING_TABLE.get(), "Acacia Crafting Table");
            add(ExtraRegistry.DARK_OAK_CRAFTING_TABLE.get(), "Dark Oak Crafting Table");
            add(ExtraRegistry.CRIMSON_CRAFTING_TABLE.get(), "Crimson Crafting Table");
            add(ExtraRegistry.WARPED_CRAFTING_TABLE.get(), "Warped Crafting Table");
        } else if (this.locale.equals("de_de")) {
            add("block.treemendous.chopping_block", "Hackklotz");
            add("entity.treemendous.boat", "Boot");
            add(ExtraRegistry.MAPLE_RED_LEAVES.get(), "Rotes Ahornlaub");
            add(ExtraRegistry.MAPLE_BROWN_LEAVES.get(), "Braunes Ahornlaub");
            add("block.treemendous.maple_red_sapling", "Roter Ahornsetzling");
            add("block.treemendous.maple_brown_sapling", "Brauner Ahornsetzling");
            add(Blocks.CHEST, "Eichenholztruhe");
            add(ExtraRegistry.BIRCH_CHEST.get(), "Birkenholztruhe");
            add(ExtraRegistry.SPRUCE_CHEST.get(), "Fichtenholztruhe");
            add(ExtraRegistry.JUNGLE_CHEST.get(), "Tropenholztruhe");
            add(ExtraRegistry.ACACIA_CHEST.get(), "Akazienholztruhe");
            add(ExtraRegistry.DARK_OAK_CHEST.get(), "Schwarzeichenholztruhe");
            add(ExtraRegistry.CRIMSON_CHEST.get(), "Karmesintruhe");
            add(ExtraRegistry.WARPED_CHEST.get(), "Wirrtruhe");
            add(Blocks.CRAFTING_TABLE, "Eichenholzwerkbank");
            add(ExtraRegistry.BIRCH_CRAFTING_TABLE.get(), "Birkenholzwerkbank");
            add(ExtraRegistry.SPRUCE_CRAFTING_TABLE.get(), "Fichtenholzwerkbank");
            add(ExtraRegistry.JUNGLE_CRAFTING_TABLE.get(), "Tropenholzwerkbank");
            add(ExtraRegistry.ACACIA_CRAFTING_TABLE.get(), "Akazienholzwerkbank");
            add(ExtraRegistry.DARK_OAK_CRAFTING_TABLE.get(), "Schwarzeichenholzwerkbank");
            add(ExtraRegistry.CRIMSON_CRAFTING_TABLE.get(), "Karmesinwerkbank");
            add(ExtraRegistry.WARPED_CRAFTING_TABLE.get(), "Wirrwerkbank");
        }
        addTree(TreeRegistry.DOUGLAS_TREE, "Douglas Fir", "Douglasien");
        addTree(TreeRegistry.PINE_TREE, "Pine", "Kiefern");
        addTree(TreeRegistry.LARCH_TREE, "Larch", "Lärchen");
        addTree(TreeRegistry.FIR_TREE, "Fir", "Tannen");
        addTree(TreeRegistry.MAPLE_TREE, "Maple", "Ahorn");
        addTree(TreeRegistry.JAPANESE_TREE, "Japanese Maple", "Fächer-Ahorn");
        addTree(TreeRegistry.BEECH_TREE, "Beech", "Buchen");
        addTree(TreeRegistry.CHERRY_TREE, "Chery", "Kirsch");
        addTree(TreeRegistry.ALDER_TREE, "Alder", "Erlen");
        addTree(TreeRegistry.CHESTNUT_TREE, "Chestnut", "Kastanien");
        addTree(TreeRegistry.PLANE_TREE, "Plane", "Platanen");
        addTree(TreeRegistry.ASH_TREE, "Ash", "Eschen");
        addTree(TreeRegistry.LINDEN_TREE, "Linden", "Linden");
        addTree(TreeRegistry.ROBINIA_TREE, "Robinia", "Robinien");
        addTree(TreeRegistry.WILLOW_TREE, "Willow", "Weiden");
        addTree(TreeRegistry.POMEGRANATE_TREE, "Pomegranate", "Granatapfel");
        addTree(TreeRegistry.WALNUT_TREE, "Walnut", "Walnuss");
        addTree(TreeRegistry.CEDAR_TREE, "Cedar", "Zedern");
        addTree(TreeRegistry.POPLAR_TREE, "Poplar", "Pappel");
        addTree(TreeRegistry.ELM_TREE, "Elm", "Ulmen");
        addTree(TreeRegistry.JUNIPER_TREE, "Juniper", "Wacholder");
        addTree(TreeRegistry.MAGNOLIA_TREE, "Magnolia", "Magnolien");
        addTree(TreeRegistry.RAINBOW_EUCALYPTUS_TREE, "Rainbow Eucalyptus", "Regenbogen-Eukalyptus");
    }

    private void addTree(Tree tree, String english, String german) {
        if (this.locale.equals("en_us")) {
            addTreeEnglish(tree, english);
        } else if (this.locale.equals("de_de")) {
            addTreeGerman(tree, german);
        }
    }

    private void addTreeGerman(Tree tree, String woodName) {
        add(tree.getPlanks(), "%sholzbretter", woodName);
        add(tree.getDefaultSapling(), "%ssetzling", woodName);
        add(tree.getLog(), "%sstamm", woodName);
        add(tree.getStrippedLog(), "Entrindeter %sstamm", woodName);
        add(tree.getWood(), "%sholz", woodName);
        add(tree.getStrippedWood(), "Entrindetes %sholz", woodName);
        add(tree.getLeaves(), "%slaub", woodName);
        add(tree.getSlab(), "%sholzstufe", woodName);
        add(tree.getFence(), "%sholzzaun", woodName);
        add(tree.getStairs(), "%sholztreppe", woodName);
        add(tree.getButton(), "%sholzknopf", woodName);
        add(tree.getPressurePlate(), "%sholzdruckplatte", woodName);
        add(tree.getDoor(), "%sholztür", woodName);
        add(tree.getTrapdoor(), "%sholzfalltür", woodName);
        add(tree.getFenceGate(), "%sholzzauntor", woodName);
        add(tree.getBoatItem(), "%sholzboot", woodName);
        add(tree.getSign(), "%sholzschild", woodName);
        add(tree.getCraftingTable(), "%sholzwerkbank", woodName);
        add(tree.getChest(), "%sholztruhe", woodName);
    }

    private void addTreeEnglish(Tree tree, String woodName) {
        add(tree.getPlanks(), "%s Planks", woodName);
        add(tree.getDefaultSapling(), "%s Sapling", woodName);
        add(tree.getLog(), "%s Log", woodName);
        add(tree.getStrippedLog(), "Stripped %s Log", woodName);
        add(tree.getWood(), "%s Wood", woodName);
        add(tree.getStrippedWood(), "Stripped %s Wood", woodName);
        add(tree.getLeaves(), "%s Leaves", woodName);
        add(tree.getSlab(), "%s Slab", woodName);
        add(tree.getFence(), "%s Fence", woodName);
        add(tree.getStairs(), "%s Stairs", woodName);
        add(tree.getButton(), "%s Button", woodName);
        add(tree.getPressurePlate(), "%s Pressure Plate", woodName);
        add(tree.getDoor(), "%s Door", woodName);
        add(tree.getTrapdoor(), "%s Trapdoor", woodName);
        add(tree.getFenceGate(), "%s Fence Gate", woodName);
        add(tree.getBoatItem(), "%s Boat", woodName);
        add(tree.getSign(), "%s Sign", woodName);
        add(tree.getCraftingTable(), "%s Crafting Table", woodName);
        add(tree.getChest(), "%s Chest", woodName);
    }

    private void add(Item item, String name, String woodName) {
        add(item, String.format(name, woodName));
    }

    private void add(Block block, String name, String woodName) {
        add(block, String.format(name, woodName));
    }

}
