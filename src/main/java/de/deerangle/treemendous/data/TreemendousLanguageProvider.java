package de.deerangle.treemendous.data;

import de.deerangle.treemendous.main.ExtraRegistry;
import de.deerangle.treemendous.main.TreeRegistry;
import de.deerangle.treemendous.main.Treemendous;
import de.deerangle.treemendous.tree.Tree;
import de.deerangle.treemendous.world.TreemendousBiomes;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.LanguageProvider;


public class TreemendousLanguageProvider extends LanguageProvider
{

    private final String locale;

    public TreemendousLanguageProvider(DataGenerator gen, String modId, String locale)
    {
        super(gen, modId, locale);
        this.locale = locale;
    }

    @Override
    protected void addTranslations()
    {
        if (this.locale.equals("en_us"))
        {
            add("advancements." + Treemendous.MOD_ID + ".explore_forests.title", "Green Tourist Destinations");
            add("advancements." + Treemendous.MOD_ID + ".explore_forests.description", "Explore all forest types");
            add("entity.minecraft.villager." + Treemendous.MOD_ID + ".forest_ranger", "Forest Ranger");
            add("block." + Treemendous.MOD_ID + ".chopping_block", "Chopping Block");
            add("entity." + Treemendous.MOD_ID + ".boat", "Boat");
            add(ExtraRegistry.MAPLE_RED_LEAVES.get(), "Red Maple Leaves");
            add(ExtraRegistry.MAPLE_BROWN_LEAVES.get(), "Brown Maple Leaves");
            add(ExtraRegistry.IRON_LUMBER_AXE.get(), "Iron Lumber Axe");
            add(ExtraRegistry.GOLDEN_LUMBER_AXE.get(), "Golden Lumber Axe");
            add(ExtraRegistry.DIAMOND_LUMBER_AXE.get(), "Diamond Lumber Axe");
            add(ExtraRegistry.NETHERITE_LUMBER_AXE.get(), "Netherite Lumber Axe");
            add(ExtraRegistry.POMEGRANATE.get(), "Pomegranate");
            add(ExtraRegistry.WOODPECKER_SPAWN_EGG.get(), "Woodpecker Spawn Egg");
            add("block." + Treemendous.MOD_ID + ".maple_red_sapling", "Red Maple Sapling");
            add("block." + Treemendous.MOD_ID + ".maple_brown_sapling", "Brown Maple Sapling");
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
        } else if (this.locale.equals("de_de"))
        {
            add("advancements." + Treemendous.MOD_ID + ".explore_forests.title", "Grüne Reiseziele");
            add("advancements." + Treemendous.MOD_ID + ".explore_forests.description", "Entdecke alle Waldarten");
            add("entity.minecraft.villager." + Treemendous.MOD_ID + ".forest_ranger", "Förster");
            add("block." + Treemendous.MOD_ID + ".chopping_block", "Hackklotz");
            add("entity." + Treemendous.MOD_ID + ".boat", "Boot");
            add(ExtraRegistry.MAPLE_RED_LEAVES.get(), "Rotes Ahornlaub");
            add(ExtraRegistry.MAPLE_BROWN_LEAVES.get(), "Braunes Ahornlaub");
            add(ExtraRegistry.IRON_LUMBER_AXE.get(), "Eisen-Holzfälleraxt");
            add(ExtraRegistry.GOLDEN_LUMBER_AXE.get(), "Gold-Holzfälleraxt");
            add(ExtraRegistry.DIAMOND_LUMBER_AXE.get(), "Diamant-Holzfälleraxt");
            add(ExtraRegistry.NETHERITE_LUMBER_AXE.get(), "Netherit-Holzfälleraxt");
            add(ExtraRegistry.POMEGRANATE.get(), "Granatapfel");
            add(ExtraRegistry.WOODPECKER_SPAWN_EGG.get(), "Specht-Spawn-Ei");
            add("block." + Treemendous.MOD_ID + ".maple_red_sapling", "Roter Ahornsetzling");
            add("block." + Treemendous.MOD_ID + ".maple_brown_sapling", "Brauner Ahornsetzling");
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
        addTree(TreeRegistry.CHERRY_TREE, "Cherry", "Kirsch");
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
        addBiome(TreeRegistry.DOUGLAS_TREE, TreemendousBiomes.BiomeKind.TAIGA, "Douglas Fir", "Douglasien");
        addBiome(TreeRegistry.PINE_TREE, TreemendousBiomes.BiomeKind.TAIGA, "Pine", "Kiefern");
        addBiome(TreeRegistry.LARCH_TREE, TreemendousBiomes.BiomeKind.TAIGA, "Larch", "Lärchen");
        addBiome(TreeRegistry.FIR_TREE, TreemendousBiomes.BiomeKind.TAIGA, "Fir", "Tannen");
        addBiome(TreeRegistry.MAPLE_TREE, TreemendousBiomes.BiomeKind.FOREST, "Maple", "Ahorn");
        if (this.locale.equals("en_us"))
        {
            add("biome." + Treemendous.MOD_ID + ".maple_red_forest", "Red Maple Forest");
            add("biome." + Treemendous.MOD_ID + ".maple_red_forest_hills", "Red Maple Forest Hills");
            add("biome." + Treemendous.MOD_ID + ".maple_brown_forest", "Brown Maple Forest");
            add("biome." + Treemendous.MOD_ID + ".maple_brown_forest_hills", "Brown Maple Forest Hills");
        } else if (this.locale.equals("de_de"))
        {
            add("biome." + Treemendous.MOD_ID + ".maple_red_forest", "Roter Ahornwald");
            add("biome." + Treemendous.MOD_ID + ".maple_red_forest_hills", "Rote Ahornwaldhügel");
            add("biome." + Treemendous.MOD_ID + ".maple_brown_forest", "Brauner Ahornwald");
            add("biome." + Treemendous.MOD_ID + ".maple_brown_forest_hills", "Braune Ahornwaldhügel");
        }
        addBiome(TreeRegistry.JAPANESE_TREE, TreemendousBiomes.BiomeKind.FOREST, "Japanese Maple", "Fächer-Ahorn");
        addBiome(TreeRegistry.BEECH_TREE, TreemendousBiomes.BiomeKind.FOREST, "Beech", "Buchen");
        addBiome(TreeRegistry.CHERRY_TREE, TreemendousBiomes.BiomeKind.FOREST, "Cherry", "Kirsch");
        addBiome(TreeRegistry.ALDER_TREE, TreemendousBiomes.BiomeKind.FOREST, "Alder", "Erlen");
        addBiome(TreeRegistry.CHESTNUT_TREE, TreemendousBiomes.BiomeKind.FOREST, "Chestnut", "Kastanien");
        addBiome(TreeRegistry.PLANE_TREE, TreemendousBiomes.BiomeKind.FOREST, "Plane", "Platanen");
        addBiome(TreeRegistry.ASH_TREE, TreemendousBiomes.BiomeKind.FOREST, "Ash", "Eschen");
        addBiome(TreeRegistry.LINDEN_TREE, TreemendousBiomes.BiomeKind.FOREST, "Linden", "Linden");
        addBiome(TreeRegistry.ROBINIA_TREE, TreemendousBiomes.BiomeKind.SAVANNA, "Robinia", "Robinien");
        addBiome(TreeRegistry.WILLOW_TREE, TreemendousBiomes.BiomeKind.FOREST, "Willow", "Weiden");
        addBiome(TreeRegistry.POMEGRANATE_TREE, TreemendousBiomes.BiomeKind.SAVANNA, "Pomegranate", "Granatapfel");
        addBiome(TreeRegistry.WALNUT_TREE, TreemendousBiomes.BiomeKind.FOREST, "Walnut", "Walnuss");
        addBiome(TreeRegistry.CEDAR_TREE, TreemendousBiomes.BiomeKind.TAIGA, "Cedar", "Zedern");
        addBiome(TreeRegistry.POPLAR_TREE, TreemendousBiomes.BiomeKind.FOREST, "Poplar", "Pappel");
        addBiome(TreeRegistry.ELM_TREE, TreemendousBiomes.BiomeKind.FOREST, "Elm", "Ulmen");
        addBiome(TreeRegistry.JUNIPER_TREE, TreemendousBiomes.BiomeKind.TAIGA, "Juniper", "Wacholder");
        addBiome(TreeRegistry.MAGNOLIA_TREE, TreemendousBiomes.BiomeKind.FOREST, "Magnolia", "Magnolien");
        addBiome(TreeRegistry.RAINBOW_EUCALYPTUS_TREE, TreemendousBiomes.BiomeKind.FOREST, "Rainbow Eucalyptus", "Regenbogen-Eukalyptus");
    }

    private void addBiome(Tree tree, TreemendousBiomes.BiomeKind biomeKind, String english, String german)
    {
        String treeName = tree.getRegistryName();
        switch (biomeKind)
        {
            case TAIGA -> {
                if (this.locale.equals("en_us"))
                {
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_taiga", String.format("%s Taiga", english));
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_taiga_hills", String.format("%s Taiga Hills", english));
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_taiga_mountains", String.format("%s Taiga Mountains", english));
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_snowy_taiga", String.format("Snowy %s Taiga", english));
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_snowy_taiga_hills", String.format("Snowy %s Taiga Hills", english));
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_snowy_taiga_mountains", String.format("Snowy %s Taiga Mountains", english));
                } else if (this.locale.equals("de_de"))
                {
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_taiga", String.format("%staiga", german));
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_taiga_hills", String.format("%staigahügel", german));
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_taiga_mountains", String.format("%staigaberge", german));
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_snowy_taiga", String.format("Verschneite %staiga", german));
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_snowy_taiga_hills", String.format("Verschneite %staigahügel", german));
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_snowy_taiga_mountains", String.format("Verschneite %staigaberge", german));
                }
            }
            case SAVANNA -> {
                if (this.locale.equals("en_us"))
                {
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_savanna", String.format("%s Savanna", english));
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_savanna_plateau", String.format("%s Savanna Plateau", english));
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_shattered_savanna", String.format("Shattered %s Savanna", english));
                } else if (this.locale.equals("de_de"))
                {
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_savanna", String.format("%ssavanne", german));
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_savanna_plateau", String.format("%ssavannenhochebene", german));
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_shattered_savanna", String.format("Zerklüftete %ssavanne", german));
                }

            }
            case FOREST -> {
                if (this.locale.equals("en_us"))
                {
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_forest", String.format("%s Forest", english));
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_forest_hills", String.format("%s Forest Hills", english));
                } else if (this.locale.equals("de_de"))
                {
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_forest", String.format("%swald", german));
                    add("biome." + Treemendous.MOD_ID + "." + treeName + "_forest_hills", String.format("%swaldhügel", german));
                }
            }
        }
    }

    private void addTree(Tree tree, String english, String german)
    {
        if (this.locale.equals("en_us"))
        {
            addTreeEnglish(tree, english);
        } else if (this.locale.equals("de_de"))
        {
            addTreeGerman(tree, german);
        }
    }

    private void addTreeGerman(Tree tree, String woodName)
    {
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

    private void addTreeEnglish(Tree tree, String woodName)
    {
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

    private void add(Item item, String name, String woodName)
    {
        add(item, String.format(name, woodName));
    }

    private void add(Block block, String name, String woodName)
    {
        add(block, String.format(name, woodName));
    }

}
