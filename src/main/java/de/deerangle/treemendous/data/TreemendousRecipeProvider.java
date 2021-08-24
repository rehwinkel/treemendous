package de.deerangle.treemendous.data;

import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.Tree;
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
import net.minecraftforge.registries.RegistryManager;

import java.util.function.Consumer;

public class TreemendousRecipeProvider extends RecipeProvider {

    public TreemendousRecipeProvider(DataGenerator generator) {
        super(generator);
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

    @SuppressWarnings("NullableProblems")
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipeConsumer) {
        ShapedRecipeBuilder.shaped(Blocks.CRAFTING_TABLE).define('#', Blocks.OAK_PLANKS).pattern("##").pattern("##").unlockedBy("has_planks", has(ItemTags.PLANKS)).save(recipeConsumer);
        ShapedRecipeBuilder.shaped(Blocks.CHEST).define('#', Blocks.OAK_PLANKS).pattern("###").pattern("# #").pattern("###").group("wooden_chest").unlockedBy("has_lots_of_items", new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[0])).save(recipeConsumer);

        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues()) {
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
            ShapedRecipeBuilder.shaped(tree.getCraftingTable()).define('#', tree.getPlanks()).pattern("##").pattern("##").unlockedBy("has_planks", has(tree.getPlanks())).group("wooden_crafting_table").save(recipeConsumer);
            ShapedRecipeBuilder.shaped(tree.getChest()).define('#', tree.getPlanks()).pattern("###").pattern("# #").pattern("###").group("wooden_chest").unlockedBy("has_lots_of_items", new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[0])).save(recipeConsumer);
        }
    }

}
