package de.deerangle.treemendous.data;

import de.deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;


public class TreemendousBlockStateProvider extends BlockStateProvider {

    public TreemendousBlockStateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        planksItemBlock(TreeRegistry.JUNIPER_TREE.getPlanks());
        logItemBlock(TreeRegistry.JUNIPER_TREE.getLog());
        logItemBlock(TreeRegistry.JUNIPER_TREE.getStrippedLog());
        woodItemBlock(TreeRegistry.JUNIPER_TREE.getWood());
        woodItemBlock(TreeRegistry.JUNIPER_TREE.getStrippedWood());
    }

    private void woodItemBlock(RotatedPillarBlock block) {
        ResourceLocation name = blockTexture(block);
        name = new ResourceLocation(name.getNamespace(), name.getPath().replace("wood", "log"));
        ModelFile model = models().cubeColumn(block.getRegistryName().getPath(), name, name);
        ModelFile horizontalModel = models().cubeColumnHorizontal(block.getRegistryName().getPath() + "_horizontal", name, name);
        axisBlock(block, model, horizontalModel);
        itemModels().getBuilder(block.getRegistryName().getPath()).parent(model);
    }

    private void logItemBlock(RotatedPillarBlock block) {
        ResourceLocation name = blockTexture(block);
        ResourceLocation nameTop = new ResourceLocation(name.getNamespace(), name.getPath() + "_top");
        ModelFile model = models().cubeColumn(block.getRegistryName().getPath(), name, nameTop);
        ModelFile horizontalModel = models().cubeColumnHorizontal(block.getRegistryName().getPath() + "_horizontal", name, nameTop);
        axisBlock(block, model, horizontalModel);
        itemModels().getBuilder(block.getRegistryName().getPath()).parent(model);
    }

    private void planksItemBlock(Block planks) {
        ModelFile model = models().cubeAll(planks.getRegistryName().getPath(), blockTexture(planks));
        getVariantBuilder(planks).partialState().setModels(new ConfiguredModel(model));
        itemModels().getBuilder(planks.getRegistryName().getPath()).parent(model);
    }

}
