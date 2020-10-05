package deerangle.treemendous.data;

import deerangle.treemendous.main.ExtraRegistry;
import deerangle.treemendous.main.Treemendous;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

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

    @Override
    protected void registerModels() {
        addLumberAxeItem(ExtraRegistry.IRON_LUMBER_AXE.get(), "item/iron_lumber_axe");
        addLumberAxeItem(ExtraRegistry.GOLDEN_LUMBER_AXE.get(), "item/golden_lumber_axe");
        addLumberAxeItem(ExtraRegistry.DIAMOND_LUMBER_AXE.get(), "item/diamond_lumber_axe");
        addLumberAxeItem(ExtraRegistry.NETHERITE_LUMBER_AXE.get(), "item/netherite_lumber_axe");
        addGenericItem(ExtraRegistry.POMEGRANATE.get());
    }

}
