package deerangle.treemendous.data;

import deerangle.treemendous.main.TreeRegistry;
import deerangle.treemendous.tree.RegisteredTree;
import net.minecraft.data.DataGenerator;

public class LanguageProvider extends net.minecraftforge.common.data.LanguageProvider {

    private final String modid;

    public LanguageProvider(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
        this.modid = modid;
    }

    @Override
    protected void addTranslations() {
        add("entity." + this.modid + "." + "boat", "Boat");
        add("biome." + this.modid + "." + "mixed_maple_forest", "Mixed Maple Forest");
        add("biome." + this.modid + "." + "mixed_forest", "Treemendous Mixed Forest");
        add("biome." + this.modid + "." + "mixed_forest_vanilla", "Mixed Forest");
        for (RegisteredTree tree : TreeRegistry.trees) {
            String name = tree.getName();
            String englishName = tree.getEnglishName();
            add("block." + this.modid + "." + name + "_sapling", englishName + " Sapling");
            add("block." + this.modid + "." + name + "_leaves", englishName + " Leaves");
            add("biome." + this.modid + "." + name + "_forest", englishName + " Forest");
            add("biome." + this.modid + "." + name + "_forest_hills", englishName + " Hills");
            add("biome." + this.modid + "." + name + "_forest_snow", "Snowy " + englishName + " Forest");
            add("biome." + this.modid + "." + name + "_forest_hills_snow", "Snowy " + englishName + " Hills");
            if (tree.isNotInherited()) {
                add("block." + this.modid + "." + name + "_planks", englishName + " Planks");
                add("block." + this.modid + "." + name + "_log", englishName + " Log");
                add("block." + this.modid + "." + "stripped_" + name + "_log", "Stripped " + englishName + " Log");
                add("block." + this.modid + "." + name + "_wood", englishName + " Wood");
                add("block." + this.modid + "." + "stripped_" + name + "_wood", "Stripped " + englishName + " Wood");
                add("block." + this.modid + "." + name + "_sign", englishName + " Sign");
                add("block." + this.modid + "." + name + "_pressure_plate", englishName + " Pressure Plate");
                add("block." + this.modid + "." + name + "_trapdoor", englishName + " Trapdoor");
                add("block." + this.modid + "." + name + "_button", englishName + " Button");
                add("block." + this.modid + "." + name + "_stairs", englishName + " Stairs");
                add("block." + this.modid + "." + name + "_slab", englishName + " Slab");
                add("block." + this.modid + "." + name + "_fence_gate", englishName + " Fence Gate");
                add("block." + this.modid + "." + name + "_fence", englishName + " Fence");
                add("block." + this.modid + "." + name + "_door", englishName + " Door");
                add("item." + this.modid + "." + name + "_boat", englishName + " Boat");
            }
        }
    }
}
