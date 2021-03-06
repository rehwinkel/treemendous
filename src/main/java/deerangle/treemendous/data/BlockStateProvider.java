package deerangle.treemendous.data;

import deerangle.treemendous.tree.RegisteredTree;
import deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStateProvider extends net.minecraftforge.client.model.generators.BlockStateProvider {

    private final String modid;

    public BlockStateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
        this.modid = modid;
    }

    private String name(Block block) {
        return block.getRegistryName().getPath();
    }

    public void woodBlock(RotatedPillarBlock block, Block textureBlock) {
        ResourceLocation tex = blockTexture(textureBlock);
        axisBlock(block, models().cubeColumn(name(block), tex, tex),
                models().cubeColumnHorizontal(name(block), tex, tex));
    }

    public void pressurePlate(PressurePlateBlock block, Block textureBlock) {
        ModelFile up = models()
                .singleTexture(name(block), new ResourceLocation(ModelProvider.BLOCK_FOLDER + "/pressure_plate_up"),
                        "texture", blockTexture(textureBlock));
        ModelFile down = models().singleTexture(name(block) + "_down",
                new ResourceLocation(ModelProvider.BLOCK_FOLDER + "/pressure_plate_down"), "texture",
                blockTexture(textureBlock));
        getVariantBuilder(block).partialState().with(PressurePlateBlock.POWERED, false)
                .addModels(new ConfiguredModel(up)).partialState().with(PressurePlateBlock.POWERED, true)
                .addModels(new ConfiguredModel(down));
    }

    public void sapling(SaplingBlock block, FlowerPotBlock block2) {
        simpleBlock(block, models().cross(name(block), blockTexture(block)));
        simpleBlock(block2, models().singleTexture(name(block2),
                new ResourceLocation(ModelProvider.BLOCK_FOLDER + "/flower_pot_cross"), "plant", blockTexture(block)));
    }

    public void leaves(LeavesBlock block) {
        simpleBlock(block,
                models().singleTexture(name(block), new ResourceLocation(ModelProvider.BLOCK_FOLDER + "/leaves"), "all",
                        blockTexture(block)));
    }

    public void buttonBlock(AbstractButtonBlock block, Block textureBlock) {
        ModelFile normal = models()
                .singleTexture(name(block), new ResourceLocation(ModelProvider.BLOCK_FOLDER + "/button"), "texture",
                        blockTexture(textureBlock));
        ModelFile pressed = models().singleTexture(name(block) + "_pressed",
                new ResourceLocation(ModelProvider.BLOCK_FOLDER + "/button_pressed"), "texture",
                blockTexture(textureBlock));
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        for (Direction d : Direction.Plane.HORIZONTAL) {
            for (AttachFace f : AttachFace.values()) {
                int rotX = 0;
                switch (f) {
                    case CEILING:
                        rotX = 180;
                        break;
                    case WALL:
                        rotX = 90;
                        break;
                }
                int rotY = 180;
                switch (d) {
                    case EAST:
                        rotY = 90;
                        break;
                    case WEST:
                        rotY = 270;
                        break;
                    case NORTH:
                        rotY = 0;
                        break;
                }
                builder.partialState().with(AbstractButtonBlock.FACE, f).with(AbstractButtonBlock.HORIZONTAL_FACING, d)
                        .with(AbstractButtonBlock.POWERED, true).modelForState().rotationX(rotX).rotationY(rotY)
                        .modelFile(pressed).addModel().partialState().with(AbstractButtonBlock.FACE, f)
                        .with(AbstractButtonBlock.HORIZONTAL_FACING, d).with(AbstractButtonBlock.POWERED, false)
                        .modelForState().rotationX(rotX).rotationY(rotY).modelFile(normal).addModel();
            }
        }
    }

    public void signBlock(StandingSignBlock sign, WallSignBlock wallSign, ResourceLocation texture) {
        ModelFile model = models().getBuilder(name(sign)).texture("particle", texture);
        simpleBlock(sign, model);
        simpleBlock(wallSign, model);
    }

    public void basicBlockItem(Block block) {
        simpleBlockItem(block, models().getExistingFile(new ResourceLocation(this.modid, name(block))));
    }

    public void basicBlockItem(Block block, String suffix) {
        simpleBlockItem(block, models().getExistingFile(new ResourceLocation(this.modid, name(block) + suffix)));
    }

    public void generatedItem(IItemProvider block, ResourceLocation texture) {
        itemModels().getBuilder(block.asItem().getRegistryName().getPath())
                .parent(itemModels().getExistingFile(new ResourceLocation("item/generated")))
                .texture("layer0", texture);
    }

    public ResourceLocation itemTexture(IItemProvider block) {
        ResourceLocation name = block.asItem().getRegistryName();
        return new ResourceLocation(name.getNamespace(), ModelProvider.ITEM_FOLDER + "/" + name.getPath());
    }

    public void buttonItem(AbstractButtonBlock block, Block textureBlock) {
        ModelFile inventory = models().singleTexture(name(block) + "_inventory",
                new ResourceLocation(ModelProvider.BLOCK_FOLDER + "/button_inventory"), "texture",
                blockTexture(textureBlock));
        simpleBlockItem(block, inventory);
    }

    public void fenceBlockWithItem(FenceBlock block, ResourceLocation texture) {
        fenceBlock(block, texture);
        ModelFile inventory = models().singleTexture(name(block) + "_inventory",
                new ResourceLocation(ModelProvider.BLOCK_FOLDER + "/fence_inventory"), "texture", texture);
        simpleBlockItem(block, inventory);
    }

    @Override
    protected void registerStatesAndModels() {
        for (RegisteredTree tree : TreeRegistry.TREES) {
            sapling((SaplingBlock) tree.sapling.get(), (FlowerPotBlock) tree.potted_sapling.get());
            leaves((LeavesBlock) tree.leaves.get());
            generatedItem(tree.sapling.get(), blockTexture(tree.sapling.get()));
            basicBlockItem(tree.leaves.get());

            if (tree.isNotInherited()) {
                simpleBlock(tree.planks.get());
                logBlock((RotatedPillarBlock) tree.log.get());
                logBlock((RotatedPillarBlock) tree.stripped_log.get());
                woodBlock((RotatedPillarBlock) tree.wood.get(), tree.log.get());
                woodBlock((RotatedPillarBlock) tree.stripped_wood.get(), tree.stripped_log.get());
                pressurePlate((PressurePlateBlock) tree.pressure_plate.get(), tree.planks.get());
                trapdoorBlock((TrapDoorBlock) tree.trapdoor.get(), blockTexture(tree.trapdoor.get()), true);
                buttonBlock((AbstractButtonBlock) tree.button.get(), tree.planks.get());
                stairsBlock((StairsBlock) tree.stairs.get(), blockTexture(tree.planks.get()));
                slabBlock((SlabBlock) tree.slab.get(), new ResourceLocation(this.modid, name(tree.planks.get())),
                        blockTexture(tree.planks.get()));
                fenceGateBlock((FenceGateBlock) tree.fence_gate.get(), blockTexture(tree.planks.get()));
                fenceBlockWithItem((FenceBlock) tree.fence.get(), blockTexture(tree.planks.get()));
                String door_name = blockTexture(tree.door.get()).toString();
                doorBlock((DoorBlock) tree.door.get(), new ResourceLocation(door_name + "_bottom"),
                        new ResourceLocation(door_name + "_top"));
                signBlock((StandingSignBlock) tree.sign.get(), (WallSignBlock) tree.wall_sign.get(),
                        blockTexture(tree.planks.get()));

                basicBlockItem(tree.planks.get());
                basicBlockItem(tree.log.get());
                basicBlockItem(tree.stripped_log.get());
                basicBlockItem(tree.wood.get());
                basicBlockItem(tree.stripped_wood.get());
                basicBlockItem(tree.pressure_plate.get());
                basicBlockItem(tree.trapdoor.get(), "_bottom");
                buttonItem((AbstractButtonBlock) tree.button.get(), tree.planks.get());
                basicBlockItem(tree.stairs.get());
                basicBlockItem(tree.slab.get());
                basicBlockItem(tree.fence_gate.get());
                generatedItem(tree.door.get(), itemTexture(tree.door.get()));
                generatedItem(tree.sign_item.get(), itemTexture(tree.sign_item.get()));
                generatedItem(tree.boat_item.get(), itemTexture(tree.boat_item.get()));
            }
        }
    }
}
