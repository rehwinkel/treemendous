package de.deerangle.treemendous.data;

import de.deerangle.treemendous.main.ExtraRegistry;
import de.deerangle.treemendous.main.Treemendous;
import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.Tree;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.RegistryManager;

import java.util.function.Consumer;

public class TreemendousRecipeProvider extends RecipeProvider
{

    public TreemendousRecipeProvider(DataGenerator generator)
    {
        super(generator);
    }

    private static void planksFromLog(Consumer<FinishedRecipe> p_125999_, ItemLike result, Tag<Item> ingredient)
    {
        ShapelessRecipeBuilder.shapeless(result, 4).requires(ingredient).group("planks").unlockedBy("has_log", has(ingredient)).save(p_125999_);
    }

    private static void woodFromLogs(Consumer<FinishedRecipe> p_126003_, ItemLike p_126004_, ItemLike p_126005_)
    {
        ShapedRecipeBuilder.shaped(p_126004_, 3).define('#', p_126005_).pattern("##").pattern("##").group("bark").unlockedBy("has_log", has(p_126005_)).save(p_126003_);
    }

    private static void woodenBoat(Consumer<FinishedRecipe> p_126022_, ItemLike p_126023_, ItemLike p_126024_)
    {
        ShapedRecipeBuilder.shaped(p_126023_).define('#', p_126024_).pattern("# #").pattern("###").group("boat").unlockedBy("in_water", insideOf(Blocks.WATER)).save(p_126022_);
    }

    private static void choppingBlock(Consumer<FinishedRecipe> recipeConsumer, ItemLike result)
    {
        ShapedRecipeBuilder.shaped(result).define('#', ItemTags.PLANKS).define('L', ItemTags.LOGS).pattern("LL").pattern("##").pattern("##").unlockedBy("has_log", has(ItemTags.LOGS)).save(recipeConsumer);
    }

    private static void lumberAxe(Consumer<FinishedRecipe> recipeConsumer, ItemLike result, ItemLike ingredient, String hasConditionName)
    {
        ShapedRecipeBuilder.shaped(result).define('#', Items.STICK).define('X', ingredient).pattern("XXX").pattern("X# ").pattern("X# ").unlockedBy(hasConditionName, has(ingredient)).save(recipeConsumer);
    }

    private static String getItemName(ItemLike p_176633_)
    {
        //noinspection deprecation
        return Registry.ITEM.getKey(p_176633_.asItem()).getPath();
    }

    private static void netheriteSmithing(Consumer<FinishedRecipe> recipeConsumer, Item ingredient, Item result)
    {
        UpgradeRecipeBuilder.smithing(Ingredient.of(ingredient), Ingredient.of(Items.NETHERITE_INGOT), result).unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT)).save(recipeConsumer, new ResourceLocation(Treemendous.MOD_ID, getItemName(result) + "_smithing"));
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipeConsumer)
    {
        choppingBlock(recipeConsumer, ExtraRegistry.CHOPPING_BLOCK.get());
        lumberAxe(recipeConsumer, ExtraRegistry.IRON_LUMBER_AXE.get(), Items.IRON_INGOT, "has_iron_ingot");
        lumberAxe(recipeConsumer, ExtraRegistry.GOLDEN_LUMBER_AXE.get(), Items.GOLD_INGOT, "has_gold_ingot");
        lumberAxe(recipeConsumer, ExtraRegistry.DIAMOND_LUMBER_AXE.get(), Items.DIAMOND, "has_diamond");
        netheriteSmithing(recipeConsumer, ExtraRegistry.DIAMOND_LUMBER_AXE.get(), ExtraRegistry.NETHERITE_LUMBER_AXE.get());

        craftingTable(recipeConsumer, Blocks.CRAFTING_TABLE, Blocks.OAK_PLANKS);
        chest(recipeConsumer, Blocks.CHEST, Blocks.OAK_PLANKS);

        craftingTable(recipeConsumer, ExtraRegistry.BIRCH_CRAFTING_TABLE.get(), Blocks.BIRCH_PLANKS);
        craftingTable(recipeConsumer, ExtraRegistry.SPRUCE_CRAFTING_TABLE.get(), Blocks.SPRUCE_PLANKS);
        craftingTable(recipeConsumer, ExtraRegistry.JUNGLE_CRAFTING_TABLE.get(), Blocks.JUNGLE_PLANKS);
        craftingTable(recipeConsumer, ExtraRegistry.ACACIA_CRAFTING_TABLE.get(), Blocks.ACACIA_PLANKS);
        craftingTable(recipeConsumer, ExtraRegistry.DARK_OAK_CRAFTING_TABLE.get(), Blocks.DARK_OAK_PLANKS);
        craftingTable(recipeConsumer, ExtraRegistry.CRIMSON_CRAFTING_TABLE.get(), Blocks.CRIMSON_PLANKS);
        craftingTable(recipeConsumer, ExtraRegistry.WARPED_CRAFTING_TABLE.get(), Blocks.WARPED_PLANKS);
        chest(recipeConsumer, ExtraRegistry.BIRCH_CHEST.get(), Blocks.BIRCH_PLANKS);
        chest(recipeConsumer, ExtraRegistry.SPRUCE_CHEST.get(), Blocks.SPRUCE_PLANKS);
        chest(recipeConsumer, ExtraRegistry.JUNGLE_CHEST.get(), Blocks.JUNGLE_PLANKS);
        chest(recipeConsumer, ExtraRegistry.ACACIA_CHEST.get(), Blocks.ACACIA_PLANKS);
        chest(recipeConsumer, ExtraRegistry.DARK_OAK_CHEST.get(), Blocks.DARK_OAK_PLANKS);
        chest(recipeConsumer, ExtraRegistry.CRIMSON_CHEST.get(), Blocks.CRIMSON_PLANKS);
        chest(recipeConsumer, ExtraRegistry.WARPED_CHEST.get(), Blocks.WARPED_PLANKS);

        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues())
        {
            if (regTree.isNotFake())
            {
                Tree tree = regTree.getTree();
                planksFromLog(recipeConsumer, tree.getPlanks(), tree.getLogsItemTag());
                woodFromLogs(recipeConsumer, tree.getWood(), tree.getLog());
                woodFromLogs(recipeConsumer, tree.getStrippedWood(), tree.getStrippedLog());
                woodenBoat(recipeConsumer, tree.getBoatItem(), tree.getPlanks());
                ShapelessRecipeBuilder.shapeless(tree.getButton()).requires(tree.getPlanks()).unlockedBy("has_planks", has(tree.getPlanks())).group("wooden_button").save(recipeConsumer);
                ShapedRecipeBuilder.shaped(tree.getFence(), 3).define('W', tree.getPlanks()).define('#', Items.STICK).pattern("W#W").pattern("W#W").unlockedBy("has_planks", has(tree.getPlanks())).group("wooden_fence").save(recipeConsumer);
                ShapedRecipeBuilder.shaped(tree.getFenceGate()).define('#', Items.STICK).define('W', tree.getPlanks()).pattern("#W#").pattern("#W#").unlockedBy("has_planks", has(tree.getPlanks())).group("wooden_fence_gate").save(recipeConsumer);
                ShapedRecipeBuilder.shaped(tree.getPressurePlate()).define('#', tree.getPlanks()).pattern("##").unlockedBy("has_planks", has(tree.getPlanks())).group("wooden_pressure_plate").save(recipeConsumer);
                ShapedRecipeBuilder.shaped(tree.getSignItem(), 3).define('#', tree.getPlanks()).define('X', Items.STICK).pattern("###").pattern("###").pattern(" X ").unlockedBy("has_planks", has(tree.getPlanks())).group("sign").save(recipeConsumer);
                ShapedRecipeBuilder.shaped(tree.getSlab(), 6).define('#', tree.getPlanks()).pattern("###").unlockedBy("has_planks", has(tree.getPlanks())).group("wooden_slab").save(recipeConsumer);
                ShapedRecipeBuilder.shaped(tree.getStairs(), 4).define('#', tree.getPlanks()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_planks", has(tree.getPlanks())).group("wooden_stairs").save(recipeConsumer);
                ShapedRecipeBuilder.shaped(tree.getDoor(), 3).define('#', tree.getPlanks()).pattern("##").pattern("##").pattern("##").unlockedBy("has_planks", has(tree.getPlanks())).group("wooden_door").save(recipeConsumer);
                ShapedRecipeBuilder.shaped(tree.getTrapdoor(), 2).define('#', tree.getPlanks()).pattern("###").pattern("###").unlockedBy("has_planks", has(tree.getPlanks())).group("wooden_trapdoor").save(recipeConsumer);
                craftingTable(recipeConsumer, tree.getCraftingTable(), tree.getPlanks());
                chest(recipeConsumer, tree.getChest(), tree.getPlanks());
            }
        }
    }

    private void chest(Consumer<FinishedRecipe> recipeConsumer, Block chest, Block planks)
    {
        ShapedRecipeBuilder.shaped(chest).define('#', planks).pattern("###").pattern("# #").pattern("###").group("wooden_chest").unlockedBy("has_lots_of_items", new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[0])).save(recipeConsumer);
    }

    private void craftingTable(Consumer<FinishedRecipe> recipeConsumer, Block craftingTable, Block planks)
    {
        ShapedRecipeBuilder.shaped(craftingTable).define('#', planks).pattern("##").pattern("##").unlockedBy("has_planks", has(ItemTags.PLANKS)).save(recipeConsumer);
    }

}
