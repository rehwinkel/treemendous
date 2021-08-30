package de.deerangle.treemendous.data;

import de.deerangle.treemendous.main.ExtraRegistry;
import de.deerangle.treemendous.main.Treemendous;
import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.Tree;
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
import net.minecraftforge.registries.RegistryManager;

import java.util.Objects;


public class TreemendousModelProvider extends BlockStateProvider {

    private final String modid;

    public TreemendousModelProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
        this.modid = modid;
    }

    @Override
    protected void registerStatesAndModels() {
        leavesItemBlock(ExtraRegistry.MAPLE_RED_LEAVES.get());
        leavesItemBlock(ExtraRegistry.MAPLE_BROWN_LEAVES.get());
        existingModelBlock(ExtraRegistry.CHOPPING_BLOCK.get());
        handheldItem(ExtraRegistry.IRON_LUMBER_AXE.get());
        handheldItem(ExtraRegistry.GOLDEN_LUMBER_AXE.get());
        handheldItem(ExtraRegistry.DIAMOND_LUMBER_AXE.get());
        handheldItem(ExtraRegistry.NETHERITE_LUMBER_AXE.get());

        craftingTableBlock(ExtraRegistry.BIRCH_CRAFTING_TABLE.get(), Blocks.BIRCH_PLANKS);
        craftingTableBlock(ExtraRegistry.SPRUCE_CRAFTING_TABLE.get(), Blocks.SPRUCE_PLANKS);
        craftingTableBlock(ExtraRegistry.JUNGLE_CRAFTING_TABLE.get(), Blocks.JUNGLE_PLANKS);
        craftingTableBlock(ExtraRegistry.ACACIA_CRAFTING_TABLE.get(), Blocks.ACACIA_PLANKS);
        craftingTableBlock(ExtraRegistry.DARK_OAK_CRAFTING_TABLE.get(), Blocks.DARK_OAK_PLANKS);
        craftingTableBlock(ExtraRegistry.CRIMSON_CRAFTING_TABLE.get(), Blocks.CRIMSON_PLANKS);
        craftingTableBlock(ExtraRegistry.WARPED_CRAFTING_TABLE.get(), Blocks.WARPED_PLANKS);
        chestBlock(ExtraRegistry.BIRCH_CHEST.get(), Blocks.BIRCH_PLANKS);
        chestBlock(ExtraRegistry.SPRUCE_CHEST.get(), Blocks.SPRUCE_PLANKS);
        chestBlock(ExtraRegistry.JUNGLE_CHEST.get(), Blocks.JUNGLE_PLANKS);
        chestBlock(ExtraRegistry.ACACIA_CHEST.get(), Blocks.ACACIA_PLANKS);
        chestBlock(ExtraRegistry.DARK_OAK_CHEST.get(), Blocks.DARK_OAK_PLANKS);
        chestBlock(ExtraRegistry.CRIMSON_CHEST.get(), Blocks.CRIMSON_PLANKS);
        chestBlock(ExtraRegistry.WARPED_CHEST.get(), Blocks.WARPED_PLANKS);

        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues()) {
            Tree tree = regTree.getTree();
            planksItemBlock(tree.getPlanks());
            logItemBlock(tree.getLog());
            logItemBlock(tree.getStrippedLog());
            woodItemBlock(tree.getWood(), tree.getLog());
            woodItemBlock(tree.getStrippedWood(), tree.getStrippedLog());
            buttonItemBlock(tree.getButton(), tree.getPlanks());
            stairsItemBlock(tree.getStairs(), tree.getPlanks());
            slabItemBlock(tree.getSlab(), tree.getPlanks());
            pressurePlateItemBlock(tree.getPressurePlate(), tree.getPlanks());
            fenceItemBlock(tree.getFence(), tree.getPlanks());
            fenceGateItemBlock(tree.getFenceGate(), tree.getPlanks());
            doorItemBlock(tree.getDoor());
            trapdoorItemBlock(tree.getTrapdoor());
            leavesItemBlock(tree.getLeaves());
            for (int i = 0; i < tree.getSaplings(); i++) {
                saplingItemBlock(tree.getSapling(i));
                pottedSaplingBlock(tree.getPottedSapling(i), tree.getSapling(i));
            }
            craftingTableBlock(tree.getCraftingTable(), tree.getPlanks());
            chestBlock(tree.getChest(), tree.getPlanks());
            generatedItem(tree.getBoatItem());
            generatedItem(tree.getSignItem());
            signItemBlock(tree.getSign(), tree.getPlanks());
            signItemBlock(tree.getWallSign(), tree.getPlanks());
        }
    }

    private void existingModelBlock(Block block) {
        ModelFile model = new ModelFile.ExistingModelFile(new ResourceLocation(Objects.requireNonNull(block.getRegistryName()).getNamespace(), "block/" + block.getRegistryName().getPath()), models().existingFileHelper);
        getVariantBuilder(block).partialState().modelForState().modelFile(model).addModel();
        itemModels().getBuilder(block.getRegistryName().getPath()).parent(model);
    }

    private void chestBlock(ChestBlock chest, Block planks) {
        String name = Objects.requireNonNull(chest.getRegistryName()).getPath();
        ModelFile model = models().getBuilder(name).texture("particle", blockTexture(planks));
        getVariantBuilder(chest).partialState().modelForState().modelFile(model).addModel();
        itemModels().getBuilder(name).parent(new ModelFile.ExistingModelFile(new ResourceLocation(Treemendous.MODID, "block/chest"), models().existingFileHelper)).texture("particle", blockTexture(planks));
    }

    private void craftingTableBlock(CraftingTableBlock craftingTable, Block planks) {
        String name = Objects.requireNonNull(craftingTable.getRegistryName()).getPath();
        ModelFile model = models().singleTexture(name, new ResourceLocation(this.modid, "block/crafting_table"), "planks", blockTexture(planks));
        getVariantBuilder(craftingTable).partialState().modelForState().modelFile(model).addModel();
        itemModels().getBuilder(name).parent(model);
    }

    private void pottedSaplingBlock(FlowerPotBlock pottedSapling, SaplingBlock saplingBlock) {
        String name = Objects.requireNonNull(pottedSapling.getRegistryName()).getPath();
        ModelFile model = models().singleTexture(name, new ResourceLocation("block/flower_pot_cross"), "plant", blockTexture(saplingBlock));
        getVariantBuilder(pottedSapling).partialState().modelForState().modelFile(model).addModel();
    }

    private void generatedItem(Item item) {
        String name = Objects.requireNonNull(item.getRegistryName()).getPath();
        itemModels().singleTexture(name, new ResourceLocation("item/generated"), "layer0", new ResourceLocation(this.modid, "item/" + name));
    }

    private void handheldItem(Item item) {
        String name = Objects.requireNonNull(item.getRegistryName()).getPath();
        itemModels().singleTexture(name, new ResourceLocation("item/handheld"), "layer0", new ResourceLocation(this.modid, "item/" + name));
    }

    private void signItemBlock(SignBlock sign, Block planks) {
        String name = Objects.requireNonNull(sign.getRegistryName()).getPath();
        ModelFile signModel = models().getBuilder(name).texture("particle", blockTexture(planks));
        getVariantBuilder(sign).partialState().modelForState().modelFile(signModel).addModel();
    }

    private void saplingItemBlock(SaplingBlock sapling) {
        String name = Objects.requireNonNull(sapling.getRegistryName()).getPath();
        ModelFile leavesModel = models().singleTexture(name, new ResourceLocation("block/cross"), "cross", blockTexture(sapling));
        getVariantBuilder(sapling).partialState().modelForState().modelFile(leavesModel).addModel();
        itemModels().singleTexture(name, new ResourceLocation("item/generated"), "layer0", new ResourceLocation(this.modid, "block/" + name));
    }

    private void leavesItemBlock(LeavesBlock leaves) {
        String name = Objects.requireNonNull(leaves.getRegistryName()).getPath();
        ModelFile leavesModel = models().singleTexture(name, new ResourceLocation("block/leaves"), "all", blockTexture(leaves));
        getVariantBuilder(leaves).partialState().modelForState().modelFile(leavesModel).addModel();
        itemModels().getBuilder(name).parent(leavesModel);
    }

    private void trapdoorItemBlock(TrapDoorBlock trapdoor) {
        String name = Objects.requireNonNull(trapdoor.getRegistryName()).getPath();
        trapdoorBlock(trapdoor, new ResourceLocation(this.modid, "block/" + name), true);
        ModelFile inventoryModel = new ModelFile.ExistingModelFile(new ResourceLocation(this.modid, "block/" + name + "_bottom"), models().existingFileHelper);
        itemModels().getBuilder(name).parent(inventoryModel);
    }

    private void doorItemBlock(DoorBlock door) {
        String name = Objects.requireNonNull(door.getRegistryName()).getPath();
        doorBlock(door, new ResourceLocation(this.modid, "block/" + name + "_bottom"), new ResourceLocation(this.modid, "block/" + name + "_top"));
        itemModels().singleTexture(name, new ResourceLocation("item/generated"), "layer0", new ResourceLocation(door.getRegistryName().getNamespace(), "item/" + name));
    }

    private void fenceGateItemBlock(FenceGateBlock fence, Block planks) {
        String name = Objects.requireNonNull(fence.getRegistryName()).getPath();
        fenceGateBlock(fence, blockTexture(planks));
        ModelFile inventoryModel = new ModelFile.ExistingModelFile(new ResourceLocation(this.modid, "block/" + name), this.models().existingFileHelper);
        itemModels().getBuilder(name).parent(inventoryModel);
    }

    private void fenceItemBlock(FenceBlock fence, Block planks) {
        String name = Objects.requireNonNull(fence.getRegistryName()).getPath();
        fenceBlock(fence, blockTexture(planks));
        ModelFile inventoryModel = models().singleTexture(name + "_inventory", new ResourceLocation("block/fence_inventory"), "texture", blockTexture(planks));
        itemModels().getBuilder(name).parent(inventoryModel);
    }

    private void pressurePlateItemBlock(PressurePlateBlock block, Block planks) {
        String name = Objects.requireNonNull(block.getRegistryName()).getPath();
        ModelFile model = models().singleTexture(name, new ResourceLocation("block/pressure_plate_up"), "texture", blockTexture(planks));
        ModelFile modelPressed = models().singleTexture(name + "_down", new ResourceLocation("block/pressure_plate_down"), "texture", blockTexture(planks));
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        builder.partialState().with(PressurePlateBlock.POWERED, false).modelForState().modelFile(model).addModel().partialState().with(PressurePlateBlock.POWERED, true).modelForState().modelFile(modelPressed).addModel();

        itemModels().getBuilder(name).parent(model);
    }

    private void buttonItemBlock(ButtonBlock button, Block planks) {
        String name = Objects.requireNonNull(button.getRegistryName()).getPath();
        ModelFile model = models().singleTexture(name, new ResourceLocation("block/button"), "texture", blockTexture(planks));
        ModelFile pressed = models().singleTexture(name + "_pressed", new ResourceLocation("block/button_pressed"), "texture", blockTexture(planks));
        ModelFile inventory = models().singleTexture(name + "_inventory", new ResourceLocation("block/button_inventory"), "texture", blockTexture(planks));
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
        itemModels().getBuilder(name).parent(inventory);
    }

    public void stairsItemBlock(StairBlock block, Block planks) {
        String name = Objects.requireNonNull(block.getRegistryName()).getPath();
        ResourceLocation texture = blockTexture(planks);
        ModelFile stairs = models().stairs(name, texture, texture, texture);
        ModelFile stairsInner = models().stairsInner(name + "_inner", texture, texture, texture);
        ModelFile stairsOuter = models().stairsOuter(name + "_outer", texture, texture, texture);
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
        itemModels().getBuilder(name).parent(stairs);
    }

    public void slabItemBlock(SlabBlock block, Block planks) {
        String name = Objects.requireNonNull(block.getRegistryName()).getPath();
        ResourceLocation texture = blockTexture(planks);
        ModelFile bottom = models().slab(name, texture, texture, texture);
        ModelFile top = models().slabTop(name + "_top", texture, texture, texture);
        ModelFile full = models().getExistingFile(planks.getRegistryName());
        getVariantBuilder(block)
                .partialState().with(SlabBlock.TYPE, SlabType.BOTTOM).addModels(new ConfiguredModel(bottom))
                .partialState().with(SlabBlock.TYPE, SlabType.TOP).addModels(new ConfiguredModel(top))
                .partialState().with(SlabBlock.TYPE, SlabType.DOUBLE).addModels(new ConfiguredModel(full));
        itemModels().getBuilder(name).parent(bottom);
    }

    private void woodItemBlock(RotatedPillarBlock block, RotatedPillarBlock log) {
        String name = Objects.requireNonNull(block.getRegistryName()).getPath();
        ResourceLocation texture = blockTexture(log);
        ModelFile model = models().cubeColumn(name, texture, texture);
        ModelFile horizontalModel = models().cubeColumnHorizontal(name + "_horizontal", texture, texture);
        axisBlock(block, model, horizontalModel);
        itemModels().getBuilder(name).parent(model);
    }

    private void logItemBlock(RotatedPillarBlock block) {
        String name = Objects.requireNonNull(block.getRegistryName()).getPath();
        ResourceLocation texture = blockTexture(block);
        ResourceLocation textureTop = new ResourceLocation(texture.getNamespace(), texture.getPath() + "_top");
        ModelFile model = models().cubeColumn(name, texture, textureTop);
        ModelFile horizontalModel = models().cubeColumnHorizontal(name + "_horizontal", texture, textureTop);
        axisBlock(block, model, horizontalModel);
        itemModels().getBuilder(name).parent(model);
    }

    private void planksItemBlock(Block planks) {
        String name = Objects.requireNonNull(planks.getRegistryName()).getPath();
        ModelFile model = models().cubeAll(name, blockTexture(planks));
        getVariantBuilder(planks).partialState().setModels(new ConfiguredModel(model));
        itemModels().getBuilder(name).parent(model);
    }

}
