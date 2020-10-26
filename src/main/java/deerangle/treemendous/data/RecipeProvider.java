package deerangle.treemendous.data;

import deerangle.treemendous.main.ExtraRegistry;
import deerangle.treemendous.tree.RegisteredTree;
import deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;

public class RecipeProvider extends net.minecraft.data.RecipeProvider {

    public RecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private void shapelessPlanksNew(Consumer<IFinishedRecipe> reipceConsumer, IItemProvider result, Tag<Item> wood) {
        ShapelessRecipeBuilder.shapelessRecipe(result, 4).addIngredient(wood).setGroup("planks")
                .addCriterion("has_log", hasItem(wood)).build(reipceConsumer);
    }

    private void shapelessStrippedToPlanks(Consumer<IFinishedRecipe> reipceConsumer, IItemProvider result, IItemProvider p_240471_2_) {
        ShapedRecipeBuilder.shapedRecipe(result, 3).key('#', p_240471_2_).patternLine("##").patternLine("##")
                .setGroup("bark").addCriterion("has_log", hasItem(p_240471_2_)).build(reipceConsumer);
    }

    private void shapedBoat(Consumer<IFinishedRecipe> reipceConsumer, IItemProvider result, IItemProvider planks) {
        ShapedRecipeBuilder.shapedRecipe(result).key('#', planks).patternLine("# #").patternLine("###").setGroup("boat")
                .addCriterion("in_water", enteredBlock(Blocks.WATER)).build(reipceConsumer);
    }

    private void shapelessWoodenButton(Consumer<IFinishedRecipe> reipceConsumer, IItemProvider result, IItemProvider p_240474_2_) {
        ShapelessRecipeBuilder.shapelessRecipe(result).addIngredient(p_240474_2_).setGroup("wooden_button")
                .addCriterion("has_planks", hasItem(p_240474_2_)).build(reipceConsumer);
    }

    private void shapedWoodenDoor(Consumer<IFinishedRecipe> reipceConsumer, IItemProvider result, IItemProvider p_240475_2_) {
        ShapedRecipeBuilder.shapedRecipe(result, 3).key('#', p_240475_2_).patternLine("##").patternLine("##")
                .patternLine("##").setGroup("wooden_door").addCriterion("has_planks", hasItem(p_240475_2_))
                .build(reipceConsumer);
    }

    private void shapedWoodenFence(Consumer<IFinishedRecipe> reipceConsumer, IItemProvider result, IItemProvider p_240476_2_) {
        ShapedRecipeBuilder.shapedRecipe(result, 3).key('#', Items.STICK).key('W', p_240476_2_).patternLine("W#W")
                .patternLine("W#W").setGroup("wooden_fence").addCriterion("has_planks", hasItem(p_240476_2_))
                .build(reipceConsumer);
    }

    private void shapedWoodenFenceGate(Consumer<IFinishedRecipe> reipceConsumer, IItemProvider result, IItemProvider p_240477_2_) {
        ShapedRecipeBuilder.shapedRecipe(result).key('#', Items.STICK).key('W', p_240477_2_).patternLine("#W#")
                .patternLine("#W#").setGroup("wooden_fence_gate").addCriterion("has_planks", hasItem(p_240477_2_))
                .build(reipceConsumer);
    }

    private void shapedWoodenPressurePlate(Consumer<IFinishedRecipe> reipceConsumer, IItemProvider result, IItemProvider p_240478_2_) {
        ShapedRecipeBuilder.shapedRecipe(result).key('#', p_240478_2_).patternLine("##")
                .setGroup("wooden_pressure_plate").addCriterion("has_planks", hasItem(p_240478_2_))
                .build(reipceConsumer);
    }

    private void shapedWoodenSlab(Consumer<IFinishedRecipe> reipceConsumer, IItemProvider result, IItemProvider p_240479_2_) {
        ShapedRecipeBuilder.shapedRecipe(result, 6).key('#', p_240479_2_).patternLine("###").setGroup("wooden_slab")
                .addCriterion("has_planks", hasItem(p_240479_2_)).build(reipceConsumer);
    }

    private void shapedWoodenStairs(Consumer<IFinishedRecipe> reipceConsumer, IItemProvider result, IItemProvider p_240480_2_) {
        ShapedRecipeBuilder.shapedRecipe(result, 4).key('#', p_240480_2_).patternLine("#  ").patternLine("## ")
                .patternLine("###").setGroup("wooden_stairs").addCriterion("has_planks", hasItem(p_240480_2_))
                .build(reipceConsumer);
    }

    private void shapedWoodenTrapdoor(Consumer<IFinishedRecipe> reipceConsumer, IItemProvider result, IItemProvider p_240481_2_) {
        ShapedRecipeBuilder.shapedRecipe(result, 2).key('#', p_240481_2_).patternLine("###").patternLine("###")
                .setGroup("wooden_trapdoor").addCriterion("has_planks", hasItem(p_240481_2_)).build(reipceConsumer);
    }

    private void shapedSign(Consumer<IFinishedRecipe> reipceConsumer, IItemProvider result, IItemProvider planks) {
        String s = Registry.ITEM.getKey(planks.asItem()).getPath();
        ShapedRecipeBuilder.shapedRecipe(result, 3).setGroup("sign").key('#', planks).key('X', Items.STICK)
                .patternLine("###").patternLine("###").patternLine(" X ").addCriterion("has_" + s, hasItem(planks))
                .build(reipceConsumer);
    }

    private void lumberAxe(Consumer<IFinishedRecipe> reipceConsumer, IItemProvider result, IItemProvider axeMaterial) {
        ShapedRecipeBuilder.shapedRecipe(result).key('#', Items.STICK).key('X', axeMaterial).patternLine("XXX")
                .patternLine("X# ").patternLine("X# ").addCriterion("has_iron_ingot", hasItem(Items.IRON_INGOT))
                .build(reipceConsumer);

    }


    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> reipceConsumer) {
        lumberAxe(reipceConsumer, ExtraRegistry.IRON_LUMBER_AXE.get(), Items.IRON_INGOT);
        lumberAxe(reipceConsumer, ExtraRegistry.GOLDEN_LUMBER_AXE.get(), Items.GOLD_INGOT);
        lumberAxe(reipceConsumer, ExtraRegistry.DIAMOND_LUMBER_AXE.get(), Items.DIAMOND);
        for (RegisteredTree tree : TreeRegistry.TREES) {
            if (tree.isNotInherited()) {
                shapelessPlanksNew(reipceConsumer, tree.planks.get(), tree.logsItemTag);
                shapelessStrippedToPlanks(reipceConsumer, tree.wood.get(), tree.log.get());
                shapelessStrippedToPlanks(reipceConsumer, tree.stripped_wood.get(), tree.stripped_log.get());
                shapelessWoodenButton(reipceConsumer, tree.button.get(), tree.planks.get());
                shapedWoodenDoor(reipceConsumer, tree.door.get(), tree.planks.get());
                shapedWoodenFence(reipceConsumer, tree.fence.get(), tree.planks.get());
                shapedWoodenFenceGate(reipceConsumer, tree.fence_gate.get(), tree.planks.get());
                shapedWoodenPressurePlate(reipceConsumer, tree.pressure_plate.get(), tree.planks.get());
                shapedWoodenSlab(reipceConsumer, tree.slab.get(), tree.planks.get());
                shapedWoodenStairs(reipceConsumer, tree.stairs.get(), tree.planks.get());
                shapedWoodenTrapdoor(reipceConsumer, tree.trapdoor.get(), tree.planks.get());
                shapedSign(reipceConsumer, tree.sign_item.get(), tree.planks.get());
                shapedBoat(reipceConsumer, tree.boat_item.get(), tree.planks.get());
            }
        }
    }

}
