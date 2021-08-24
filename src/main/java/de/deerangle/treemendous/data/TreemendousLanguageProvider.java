package de.deerangle.treemendous.data;

import de.deerangle.treemendous.tree.Tree;
import de.deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;


public class TreemendousLanguageProvider extends LanguageProvider {

    private final String locale;

    public TreemendousLanguageProvider(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
        this.locale = locale;
    }

    @Override
    protected void addTranslations() {
        addTree(TreeRegistry.JUNIPER_TREE, "Juniper", "Wacholder");
    }

    private void addTree(Tree tree, String english, String german) {
        if (this.locale.equals("en_us")) {
            add("entity.treemendous.boat", "Boat");
            addTreeEnglish(tree, english);
        } else if (this.locale.equals("de_de")) {
            add("entity.treemendous.boat", "Boot");
            addTreeGerman(tree, german);
        }
    }

    private void addTreeGerman(Tree tree, String woodName) {
        add(tree.getPlanks(), "%sholzbretter", woodName);
        add(tree.getSapling(), "%ssetzling", woodName);
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
        add(tree.getSapling(), "%s Sapling", woodName);
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
