package deerangle.treemendous.data;

import deerangle.treemendous.main.ExtraRegistry;
import deerangle.treemendous.main.Treemendous;
import deerangle.treemendous.tree.RegisteredTree;
import deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;

public class ItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider {

    public ItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    public void addLumberAxeItem(Item item, String textureName) {
        getBuilder(item.getRegistryName().getPath()).parent(this.getExistingFile(new ResourceLocation("item/handheld")))
                .texture("layer0", new ResourceLocation(Treemendous.MODID, textureName));
    }

    public ResourceLocation itemTexture(IItemProvider block) {
        ResourceLocation name = block.asItem().getRegistryName();
        return new ResourceLocation(name.getNamespace(), ModelProvider.ITEM_FOLDER + "/" + name.getPath());
    }

    public void addGenericItem(Item item) {
        getBuilder(item.getRegistryName().getPath())
                .parent(this.getExistingFile(new ResourceLocation("item/generated")))
                .texture("layer0", itemTexture(item));
    }

    public ResourceLocation blockTexture(Block block) {
        ResourceLocation name = block.getRegistryName();
        return new ResourceLocation(name.getNamespace(), ModelProvider.BLOCK_FOLDER + "/" + name.getPath());
    }

    public void generatedItem(IItemProvider item, ResourceLocation texture) {
        getBuilder(item.asItem().getRegistryName().getPath())
                .parent(this.getExistingFile(new ResourceLocation("item/generated"))).texture("layer0", texture);
    }

    public void simpleBlockItem(IItemProvider item, ModelFile model) {
        getBuilder(item.asItem().getRegistryName().getPath()).parent(model);
    }

    public void basicBlockItem(Block block) {
        getBuilder(block.asItem().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(
                new ResourceLocation(this.modid, "block/" + block.getRegistryName().getPath())));
    }

    public void basicBlockItem(Block block, String suffix) {
        getBuilder(block.asItem().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(
                new ResourceLocation(this.modid, "block/" + block.getRegistryName().getPath() + suffix)));
    }

    public void buttonItem(AbstractButtonBlock block, Block textureBlock) {
        ModelFile inventory = singleTexture(block.getRegistryName().getPath() + "_inventory",
                new ResourceLocation(ModelProvider.BLOCK_FOLDER + "/button_inventory"), "texture",
                blockTexture(textureBlock));
        simpleBlockItem(block, inventory);
    }

    public void fenceItem(FenceBlock block, ResourceLocation texture) {
        ModelFile inventory = singleTexture(block.getRegistryName().getPath() + "_inventory",
                new ResourceLocation(ModelProvider.BLOCK_FOLDER + "/fence_inventory"), "texture", texture);
        simpleBlockItem(block, inventory);
    }

    @Override
    protected void registerModels() {
        addLumberAxeItem(ExtraRegistry.IRON_LUMBER_AXE.get(), "item/iron_lumber_axe");
        addLumberAxeItem(ExtraRegistry.GOLDEN_LUMBER_AXE.get(), "item/golden_lumber_axe");
        addLumberAxeItem(ExtraRegistry.DIAMOND_LUMBER_AXE.get(), "item/diamond_lumber_axe");
        // addLumberAxeItem(ExtraRegistry.NETHERITE_LUMBER_AXE.get(), "item/netherite_lumber_axe");
        addGenericItem(ExtraRegistry.POMEGRANATE.get());

        for (RegisteredTree tree : TreeRegistry.TREES) {
            generatedItem(tree.sapling.get(), blockTexture(tree.sapling.get()));
            basicBlockItem(tree.leaves.get());

            if (tree.isNotInherited()) {
                basicBlockItem(tree.planks.get());
                basicBlockItem(tree.log.get());
                basicBlockItem(tree.stripped_log.get());
                basicBlockItem(tree.wood.get());
                basicBlockItem(tree.stripped_wood.get());
                basicBlockItem(tree.pressure_plate.get());
                basicBlockItem(tree.trapdoor.get(), "_bottom");
                buttonItem((AbstractButtonBlock) tree.button.get(), tree.planks.get());
                fenceItem((FenceBlock) tree.fence.get(), blockTexture(tree.planks.get()));
                basicBlockItem(tree.stairs.get());
                basicBlockItem(tree.slab.get());
                basicBlockItem(tree.fence_gate.get());
                generatedItem(tree.door.get(), itemTexture(tree.door.get()));
                generatedItem(tree.sign_item.get(), itemTexture(tree.sign_item.get()));
                generatedItem(tree.boat_item.get(), itemTexture(tree.boat_item.get()));
            }
        }
    }

    @Override
    public String getName() {
        return "Item Models for " + this.modid;
    }
}
