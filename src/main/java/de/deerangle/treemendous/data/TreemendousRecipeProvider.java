package de.deerangle.treemendous.data;

import de.deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class TreemendousRecipeProvider extends RecipeProvider {

    public TreemendousRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipeConsumer) {
        ShapedRecipeBuilder.shaped(Blocks.CRAFTING_TABLE).define('#', Blocks.OAK_PLANKS).pattern("##").pattern("##").unlockedBy("has_planks", has(ItemTags.PLANKS)).save(recipeConsumer);
        ShapedRecipeBuilder.shaped(Blocks.CHEST).define('#', Blocks.OAK_PLANKS).pattern("###").pattern("# #").pattern("###").unlockedBy("has_lots_of_items", new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[0])).save(recipeConsumer);

        planksFromLog(recipeConsumer, TreeRegistry.JUNIPER_TREE.getPlanks(), TreeRegistry.JUNIPER_TREE.getLogsItemTag());
        woodFromLogs(recipeConsumer, TreeRegistry.JUNIPER_TREE.getWood(), TreeRegistry.JUNIPER_TREE.getLog());
        woodFromLogs(recipeConsumer, TreeRegistry.JUNIPER_TREE.getStrippedWood(), TreeRegistry.JUNIPER_TREE.getStrippedLog());
        woodenBoat(recipeConsumer, TreeRegistry.JUNIPER_TREE.getBoatItem(), TreeRegistry.JUNIPER_TREE.getPlanks());
        ShapelessRecipeBuilder.shapeless(TreeRegistry.JUNIPER_TREE.getButton()).requires(TreeRegistry.JUNIPER_TREE.getPlanks()).unlockedBy("has_planks", has(TreeRegistry.JUNIPER_TREE.getPlanks())).group("wooden_button").save(recipeConsumer);
        ShapedRecipeBuilder.shaped(TreeRegistry.JUNIPER_TREE.getFence(), 3).define('W', TreeRegistry.JUNIPER_TREE.getPlanks()).define('#', Items.STICK).pattern("W#W").pattern("W#W").unlockedBy("has_planks", has(TreeRegistry.JUNIPER_TREE.getPlanks())).group("wooden_fence").save(recipeConsumer);
        ShapedRecipeBuilder.shaped(TreeRegistry.JUNIPER_TREE.getFenceGate()).define('#', Items.STICK).define('W', TreeRegistry.JUNIPER_TREE.getPlanks()).pattern("#W#").pattern("#W#").unlockedBy("has_planks", has(TreeRegistry.JUNIPER_TREE.getPlanks())).group("wooden_fence_gate").save(recipeConsumer);
        ShapedRecipeBuilder.shaped(TreeRegistry.JUNIPER_TREE.getPressurePlate()).define('#', TreeRegistry.JUNIPER_TREE.getPlanks()).pattern("##").unlockedBy("has_planks", has(TreeRegistry.JUNIPER_TREE.getPlanks())).group("wooden_pressure_plate").save(recipeConsumer);
        ShapedRecipeBuilder.shaped(TreeRegistry.JUNIPER_TREE.getSignItem(), 3).define('#', TreeRegistry.JUNIPER_TREE.getPlanks()).define('X', Items.STICK).pattern("###").pattern("###").pattern(" X ").unlockedBy("has_planks", has(TreeRegistry.JUNIPER_TREE.getPlanks())).group("sign").save(recipeConsumer);
        ShapedRecipeBuilder.shaped(TreeRegistry.JUNIPER_TREE.getSlab(), 6).define('#', TreeRegistry.JUNIPER_TREE.getPlanks()).pattern("###").unlockedBy("has_planks", has(TreeRegistry.JUNIPER_TREE.getPlanks())).group("wooden_slab").save(recipeConsumer);
        ShapedRecipeBuilder.shaped(TreeRegistry.JUNIPER_TREE.getStairs(), 4).define('#', TreeRegistry.JUNIPER_TREE.getPlanks()).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_planks", has(TreeRegistry.JUNIPER_TREE.getPlanks())).group("wooden_stairs").save(recipeConsumer);
        ShapedRecipeBuilder.shaped(TreeRegistry.JUNIPER_TREE.getDoor(), 3).define('#', TreeRegistry.JUNIPER_TREE.getPlanks()).pattern("##").pattern("##").pattern("##").unlockedBy("has_planks", has(TreeRegistry.JUNIPER_TREE.getPlanks())).group("wooden_door").save(recipeConsumer);
        ShapedRecipeBuilder.shaped(TreeRegistry.JUNIPER_TREE.getTrapdoor(), 2).define('#', TreeRegistry.JUNIPER_TREE.getPlanks()).pattern("###").pattern("###").unlockedBy("has_planks", has(TreeRegistry.JUNIPER_TREE.getPlanks())).group("wooden_trapdoor").save(recipeConsumer);
        ShapedRecipeBuilder.shaped(TreeRegistry.JUNIPER_TREE.getCraftingTable(), 1).define('#', TreeRegistry.JUNIPER_TREE.getPlanks()).pattern("##").pattern("##").unlockedBy("has_planks", has(TreeRegistry.JUNIPER_TREE.getPlanks())).group("wooden_crafting_table").save(recipeConsumer);
    }

    private static void planksFromLog(Consumer<FinishedRecipe> p_125999_, ItemLike result, Tag<Item> ingredient) {
        ShapelessRecipeBuilder.shapeless(result, 4).requires(ingredient).group("planks").unlockedBy("has_log", has(ingredient)).save(p_125999_);
    }

    private static void woodFromLogs(Consumer<FinishedRecipe> p_126003_, ItemLike p_126004_, ItemLike p_126005_) {
        ShapedRecipeBuilder.shaped(p_126004_, 3).define('#', p_126005_).pattern("##").pattern("##").group("bark").unlockedBy("has_log", has(p_126005_)).save(p_126003_);
    }

    private static void woodenBoat(Consumer<FinishedRecipe> p_126022_, ItemLike p_126023_, ItemLike p_126024_) {
        ShapedRecipeBuilder.shaped(p_126023_).define('#', p_126024_).pattern("# #").pattern("###").group("boat").unlockedBy("in_water", insideOf(Blocks.WATER)).save(p_126022_);
    }

}
