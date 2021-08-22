package de.deerangle.treemendous.data;

import de.deerangle.treemendous.main.Treemendous;
import de.deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
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
        buttonItemBlock(TreeRegistry.JUNIPER_TREE.getButton(), TreeRegistry.JUNIPER_TREE.getPlanks());
        stairsItemBlock(TreeRegistry.JUNIPER_TREE.getStairs(), TreeRegistry.JUNIPER_TREE.getPlanks());
        slabItemBlock(TreeRegistry.JUNIPER_TREE.getSlab(), TreeRegistry.JUNIPER_TREE.getPlanks());
        pressurePlateItemBlock(TreeRegistry.JUNIPER_TREE.getPressurePlate(), TreeRegistry.JUNIPER_TREE.getPlanks());
        fenceItemBlock(TreeRegistry.JUNIPER_TREE.getFence(), TreeRegistry.JUNIPER_TREE.getPlanks());
        fenceGateItemBlock(TreeRegistry.JUNIPER_TREE.getFenceGate(), TreeRegistry.JUNIPER_TREE.getPlanks());
        doorItemBlock(TreeRegistry.JUNIPER_TREE.getDoor());
        trapdoorItemBlock(TreeRegistry.JUNIPER_TREE.getTrapdoor());
        leavesItemBlock(TreeRegistry.JUNIPER_TREE.getLeaves());
        saplingItemBlock(TreeRegistry.JUNIPER_TREE.getSapling());
        pottedSaplingItemBlock(TreeRegistry.JUNIPER_TREE.getPottedSapling(), TreeRegistry.JUNIPER_TREE.getSapling());
        generatedItem(TreeRegistry.JUNIPER_TREE.getBoatItem());
        generatedItem(TreeRegistry.JUNIPER_TREE.getSignItem());
        signItemBlock(TreeRegistry.JUNIPER_TREE.getSign(), TreeRegistry.JUNIPER_TREE.getPlanks());
        signItemBlock(TreeRegistry.JUNIPER_TREE.getWallSign(), TreeRegistry.JUNIPER_TREE.getPlanks());
    }

    private void pottedSaplingItemBlock(FlowerPotBlock pottedSapling, SaplingBlock saplingBlock) {
        String name = pottedSapling.getRegistryName().getPath();
        ModelFile model = models().singleTexture(name, new ResourceLocation("block/flower_pot_cross"), "plant", blockTexture(saplingBlock));
        getVariantBuilder(pottedSapling).partialState().modelForState().modelFile(model).addModel();
    }

    private void generatedItem(Item item) {
        String name = item.getRegistryName().getPath();
        itemModels().singleTexture(name, new ResourceLocation("item/generated"), "layer0", new ResourceLocation(Treemendous.MODID, "item/" + name));
    }

    private void signItemBlock(SignBlock sign, Block planks) {
        String name = sign.getRegistryName().getPath();
        ModelFile signModel = models().getBuilder(name).texture("particle", blockTexture(planks));
        getVariantBuilder(sign).partialState().modelForState().modelFile(signModel).addModel();
    }

    private void saplingItemBlock(SaplingBlock sapling) {
        String name = sapling.getRegistryName().getPath();
        ModelFile leavesModel = models().singleTexture(name, new ResourceLocation("block/cross"), "cross", new ResourceLocation(Treemendous.MODID, "block/" + name));
        getVariantBuilder(sapling).partialState().modelForState().modelFile(leavesModel).addModel();
        itemModels().singleTexture(name, new ResourceLocation("item/generated"), "layer0", new ResourceLocation(Treemendous.MODID, "block/" + name));
    }

    private void leavesItemBlock(LeavesBlock leaves) {
        String name = leaves.getRegistryName().getPath();
        ModelFile leavesModel = models().singleTexture(name, new ResourceLocation("block/leaves"), "all", new ResourceLocation(Treemendous.MODID, "block/" + name));
        getVariantBuilder(leaves).partialState().modelForState().modelFile(leavesModel).addModel();
        itemModels().getBuilder(name).parent(leavesModel);
    }

    private void trapdoorItemBlock(TrapDoorBlock trapdoor) {
        String name = trapdoor.getRegistryName().getPath();
        trapdoorBlock(trapdoor, new ResourceLocation(Treemendous.MODID, "block/" + name), true);
        ModelFile inventoryModel = new ModelFile.ExistingModelFile(new ResourceLocation(Treemendous.MODID, "block/" + name + "_bottom"), models().existingFileHelper);
        itemModels().getBuilder(trapdoor.getRegistryName().getPath()).parent(inventoryModel);
    }

    private void doorItemBlock(DoorBlock door) {
        String name = door.getRegistryName().getPath();
        doorBlock(door, new ResourceLocation(Treemendous.MODID, "block/" + name + "_bottom"), new ResourceLocation(Treemendous.MODID, "block/" + name + "_top"));
        itemModels().singleTexture(name, new ResourceLocation("item/generated"), "layer0", new ResourceLocation(door.getRegistryName().getNamespace(), "item/" + name));
    }

    private void fenceGateItemBlock(FenceGateBlock fence, Block planks) {
        fenceGateBlock(fence, blockTexture(planks));
        ModelFile inventoryModel = new ModelFile.ExistingModelFile(new ResourceLocation(Treemendous.MODID, "block/" + fence.getRegistryName().getPath()), this.models().existingFileHelper);
        itemModels().getBuilder(fence.getRegistryName().getPath()).parent(inventoryModel);
    }

    private void fenceItemBlock(FenceBlock fence, Block planks) {
        fenceBlock(fence, blockTexture(planks));
        ModelFile inventoryModel = models().singleTexture(fence.getRegistryName().getPath() + "_inventory", new ResourceLocation("block/fence_inventory"), "texture", blockTexture(planks));
        itemModels().getBuilder(fence.getRegistryName().getPath()).parent(inventoryModel);
    }

    private void pressurePlateItemBlock(PressurePlateBlock block, Block planks) {
        ModelFile model = models().singleTexture(block.getRegistryName().getPath(), new ResourceLocation("block/pressure_plate_up"), "texture", blockTexture(planks));
        ModelFile modelPressed = models().singleTexture(block.getRegistryName().getPath() + "_down", new ResourceLocation("block/pressure_plate_down"), "texture", blockTexture(planks));
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        builder.partialState().with(PressurePlateBlock.POWERED, false).modelForState().modelFile(model).addModel().partialState().with(PressurePlateBlock.POWERED, true).modelForState().modelFile(modelPressed).addModel();

        itemModels().getBuilder(block.getRegistryName().getPath()).parent(model);
    }

    private void buttonItemBlock(ButtonBlock button, Block planks) {
        ModelFile model = models().singleTexture(button.getRegistryName().getPath(), new ResourceLocation("block/button"), "texture", blockTexture(planks));
        ModelFile pressed = models().singleTexture(button.getRegistryName().getPath() + "_pressed", new ResourceLocation("block/button_pressed"), "texture", blockTexture(planks));
        ModelFile inventory = models().singleTexture(button.getRegistryName().getPath() + "_inventory", new ResourceLocation("block/button_inventory"), "texture", blockTexture(planks));
        VariantBlockStateBuilder builder = getVariantBuilder(button);
        for (Direction d : Direction.Plane.HORIZONTAL) {
            for (AttachFace f : AttachFace.values()) {
                int rotX = switch (f) {
                    case CEILING -> 180;
                    case WALL -> 90;
                    default -> 0;
                };
                int rotY = switch (d) {
                    case EAST -> 90;
                    case WEST -> 270;
                    case NORTH -> 0;
                    default -> 180;
                };
                builder.partialState().with(ButtonBlock.FACE, f).with(ButtonBlock.FACING, d)
                        .with(ButtonBlock.POWERED, true).modelForState().rotationX(rotX).rotationY(rotY).modelFile(pressed).addModel();
                builder.partialState().with(ButtonBlock.FACE, f).with(ButtonBlock.FACING, d)
                        .with(ButtonBlock.POWERED, false).modelForState().rotationX(rotX).rotationY(rotY).modelFile(model).addModel();
            }
        }
        itemModels().getBuilder(button.getRegistryName().getPath()).parent(inventory);
    }

    public void stairsItemBlock(StairBlock block, Block planks) {
        ResourceLocation texture = blockTexture(planks);
        ModelFile stairs = models().stairs(block.getRegistryName().getPath(), texture, texture, texture);
        ModelFile stairsInner = models().stairsInner(block.getRegistryName().getPath() + "_inner", texture, texture, texture);
        ModelFile stairsOuter = models().stairsOuter(block.getRegistryName().getPath() + "_outer", texture, texture, texture);
        getVariantBuilder(block)
                .forAllStatesExcept(state -> {
                    Direction facing = state.getValue(StairBlock.FACING);
                    Half half = state.getValue(StairBlock.HALF);
                    StairsShape shape = state.getValue(StairBlock.SHAPE);
                    int yRot = (int) facing.getClockWise().toYRot(); // Stairs model is rotated 90 degrees clockwise for some reason
                    if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT) {
                        yRot += 270; // Left facing stairs are rotated 90 degrees clockwise
                    }
                    if (shape != StairsShape.STRAIGHT && half == Half.TOP) {
                        yRot += 90; // Top stairs are rotated 90 degrees clockwise
                    }
                    yRot %= 360;
                    boolean uvlock = yRot != 0 || half == Half.TOP; // Don't set uvlock for states that have no rotation
                    return ConfiguredModel.builder()
                            .modelFile(shape == StairsShape.STRAIGHT ? stairs : shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT ? stairsInner : stairsOuter)
                            .rotationX(half == Half.BOTTOM ? 0 : 180)
                            .rotationY(yRot)
                            .uvLock(uvlock)
                            .build();
                }, StairBlock.WATERLOGGED);
        itemModels().getBuilder(block.getRegistryName().getPath()).parent(stairs);
    }

    public void slabItemBlock(SlabBlock block, Block planks) {
        ResourceLocation texture = blockTexture(planks);
        ModelFile bottom = models().slab(block.getRegistryName().getPath(), texture, texture, texture);
        ModelFile top = models().slabTop(block.getRegistryName().getPath() + "_top", texture, texture, texture);
        ModelFile full = models().getExistingFile(planks.getRegistryName());
        getVariantBuilder(block)
                .partialState().with(SlabBlock.TYPE, SlabType.BOTTOM).addModels(new ConfiguredModel(bottom))
                .partialState().with(SlabBlock.TYPE, SlabType.TOP).addModels(new ConfiguredModel(top))
                .partialState().with(SlabBlock.TYPE, SlabType.DOUBLE).addModels(new ConfiguredModel(full));
        itemModels().getBuilder(block.getRegistryName().getPath()).parent(bottom);
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
