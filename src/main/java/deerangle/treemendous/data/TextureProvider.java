package deerangle.treemendous.data;

import deerangle.treemendous.tree.RegisteredTree;
import deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.io.IOException;

public class TextureProvider extends AbstractTextureProvider {

    public TextureProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    private void processTexturesILW() throws IOException {
        Texture barrelBottom = loadTexture("block_barrel_bottom_base1", "templates_ilw");
        Texture barrelSide = loadTexture("block_barrel_side_base1", "templates_ilw");
        Texture barrelTop = loadTexture("block_barrel_top_base1", "templates_ilw");
        Texture barrelTopOpen = loadTexture("block_barrel_top_open_base1", "templates_ilw");
        Texture bedFrame = loadTexture("block_bed_frame_base1", "templates_ilw");
        Texture bookshelf = loadTexture("block_bookshelf_base1", "templates_ilw");
        Texture composterBottom = loadTexture("block_composter_bottom_base1", "templates_ilw");
        Texture composterSide = loadTexture("block_composter_side_base1", "templates_ilw");
        Texture composterTop = loadTexture("block_composter_top_base1", "templates_ilw");
        Texture ladder = loadTexture("block_ladder_base1", "templates_ilw");
        Texture lecternBase = loadTexture("block_lectern_base_base1", "templates_ilw");
        Texture lecternFront = loadTexture("block_lectern_front_base1", "templates_ilw");
        Texture lecternSides = loadTexture("block_lectern_sides_base1", "templates_ilw");
        Texture lecternTop = loadTexture("block_lectern_top_base1", "templates_ilw");
        Texture post = loadTexture("block_post_base1", "templates_ilw");
        Texture strippedPost = loadTexture("block_post_stripped_base1", "templates_ilw");
        Texture torch = loadTexture("block_torch_base1", "templates_ilw");
        Texture scaffoldingSide = loadTexture("block_scaffolding_side_base1", "templates_ilw");
        Texture scaffoldingTop = loadTexture("block_scaffolding_top_base1", "templates_ilw");
        Texture scaffoldingBottom = loadTexture("block_scaffolding_bottom_base1", "templates_ilw");
        Texture chestLeft = loadTexture("entity_chest_left_base1", "templates_ilw");
        Texture chestRight = loadTexture("entity_chest_right_base1", "templates_ilw");
        Texture chestSingle = loadTexture("entity_chest_single_base1", "templates_ilw");
        Texture pickaxe = loadTexture("item_pickaxe_wooden_base1", "templates_ilw");
        Texture sword = loadTexture("item_sword_wooden_base1", "templates_ilw");
        Texture shovel = loadTexture("item_shovel_wooden_base1", "templates_ilw");
        Texture hoe = loadTexture("item_hoe_wooden_base1", "templates_ilw");
        Texture axe = loadTexture("item_axe_wooden_base1", "templates_ilw");
        Texture bow = loadTexture("item_bow_base1", "templates_ilw");
        Texture bowPull0 = loadTexture("item_bow_pulling_0_base1", "templates_ilw");
        Texture bowPull1 = loadTexture("item_bow_pulling_1_base1", "templates_ilw");
        Texture bowPull2 = loadTexture("item_bow_pulling_2_base1", "templates_ilw");
        Texture crossbowArrow = loadTexture("item_crossbow_arrow_base1", "templates_ilw");
        Texture crossbowFirework = loadTexture("item_crossbow_firework_base1", "templates_ilw");
        Texture crossbowStandby = loadTexture("item_crossbow_standby_base1", "templates_ilw");
        Texture crossbowPull0 = loadTexture("item_crossbow_pulling_0_base1", "templates_ilw");
        Texture crossbowPull1 = loadTexture("item_crossbow_pulling_1_base1", "templates_ilw");
        Texture crossbowPull2 = loadTexture("item_crossbow_pulling_2_base1", "templates_ilw");
        Texture itemFrame = loadTexture("item_item_frame_base1", "templates_ilw");
        Texture stick = loadTexture("item_stick_base1", "templates_ilw");
        Texture swordStick = loadTexture("item_stick_sword_base1", "templates_ilw");
        Texture shovelStick = loadTexture("item_stick_shovel_base1", "templates_ilw");
        Texture axeStick = loadTexture("item_stick_axe_base1", "templates_ilw");
        Texture hoeStick = loadTexture("item_stick_hoe_base1", "templates_ilw");
        Texture pickaxeStick = loadTexture("item_stick_pickaxe_base1", "templates_ilw");
        Texture swordStickNetherite = loadTexture("item_stick_sword_netherite_base1", "templates_ilw");
        Texture shovelStickNetherite = loadTexture("item_stick_shovel_netherite_base1", "templates_ilw");
        Texture axeStickNetherite = loadTexture("item_stick_axe_netherite_base1", "templates_ilw");
        Texture hoeStickNetherite = loadTexture("item_stick_hoe_netherite_base1", "templates_ilw");
        Texture pickaxeStickNetherite = loadTexture("item_stick_pickaxe_netherite_base1", "templates_ilw");
        for (RegisteredTree tree : TreeRegistry.TREES) {
            String name = "trm_" + tree.getName();
            Texture colored;
            if (tree.getWoodColor() != 0) {
                colored = barrelBottom.multiply(tree.getWoodColor());
                this.textures.put(new ResourceLocation("ilikewood", "block/barrel/bottom/" + name), colored);

                colored = barrelTopOpen.multiply(tree.getWoodColor());
                this.textures.put(new ResourceLocation("ilikewood", "block/barrel/top/open/" + name), colored);

                colored = barrelSide.copy();
                colored.multiplyArea(tree.getWoodColor(), 0, 0, 16, 3);
                colored.multiplyArea(tree.getWoodColor(), 0, 5, 16, 6);
                colored.multiplyArea(tree.getWoodColor(), 0, 13, 16, 3);
                this.textures.put(new ResourceLocation("ilikewood", "block/barrel/side/" + name), colored);

                colored = barrelTop.copy();
                colored.multiplyArea(tree.getWoodColor(), 0, 0, 3, 16);
                colored.multiplyArea(tree.getWoodColor(), 3, 0, 2, 8);
                colored.multiplyArea(tree.getWoodColor(), 3, 10, 2, 6);
                colored.multiplyArea(tree.getWoodColor(), 5, 0, 11, 16);
                this.textures.put(new ResourceLocation("ilikewood", "block/barrel/top/" + name), colored);

                colored = bedFrame.multiply(tree.getWoodColor());
                this.textures.put(new ResourceLocation("ilikewood", "block/bed/frame/" + name), colored);

                colored = bookshelf.copy();
                colored.multiplyArea(tree.getWoodColor(), 0, 0, 16, 2);
                colored.multiplyArea(tree.getWoodColor(), 0, 7, 16, 2);
                colored.multiplyArea(tree.getWoodColor(), 0, 14, 16, 2);
                colored.multiplyArea(tree.getWoodColor(), 0, 2, 2, 5);
                colored.multiplyArea(tree.getWoodColor(), 14, 2, 2, 5);
                colored.multiplyArea(tree.getWoodColor(), 0, 9, 2, 5);
                colored.multiplyArea(tree.getWoodColor(), 14, 9, 2, 5);
                this.textures.put(new ResourceLocation("ilikewood", "block/bookshelf/" + name), colored);

                colored = composterTop.multiply(tree.getWoodColor());
                this.textures.put(new ResourceLocation("ilikewood", "block/composter/top/" + name), colored);

                colored = composterBottom.multiply(tree.getWoodColor());
                this.textures.put(new ResourceLocation("ilikewood", "block/composter/bottom/" + name), colored);

                colored = composterSide.copy();
                colored.multiplyArea(tree.getWoodColor(), 0, 0, 16, 4);
                colored.multiplyArea(tree.getWoodColor(), 0, 4, 2, 12);
                colored.multiplyArea(tree.getWoodColor(), 14, 4, 2, 12);
                colored.multiplyArea(tree.getWoodColor(), 2, 5, 12, 3);
                colored.multiplyArea(tree.getWoodColor(), 2, 9, 12, 3);
                colored.multiplyArea(tree.getWoodColor(), 2, 13, 12, 3);
                this.textures.put(new ResourceLocation("ilikewood", "block/composter/side/" + name), colored);

                colored = ladder.multiply(tree.getWoodColor());
                this.textures.put(new ResourceLocation("ilikewood", "block/ladder/" + name), colored);

                colored = lecternSides.multiply(tree.getWoodColor());
                this.textures.put(new ResourceLocation("ilikewood", "block/lectern/sides/" + name), colored);

                colored = lecternTop.multiply(tree.getWoodColor());
                this.textures.put(new ResourceLocation("ilikewood", "block/lectern/top/" + name), colored);

                colored = lecternBase.copy();
                colored.multiplyArea(tree.getWoodColor(), 0, 0, 16, 12);
                colored.multiplyArea(tree.getWoodColor(), 0, 12, 5, 4);
                colored.multiplyArea(tree.getWoodColor(), 11, 12, 5, 4);
                this.textures.put(new ResourceLocation("ilikewood", "block/lectern/base/" + name), colored);

                colored = lecternFront.copy();
                colored.multiplyArea(tree.getWoodColor(), 0, 0, 16, 7);
                colored.multiplyArea(tree.getWoodColor(), 0, 7, 1, 9);
                colored.multiplyArea(tree.getWoodColor(), 1, 12, 6, 4);
                colored.multiplyArea(tree.getWoodColor(), 7, 7, 9, 9);
                this.textures.put(new ResourceLocation("ilikewood", "block/lectern/front/" + name), colored);

                colored = strippedPost.multiply(tree.getWoodColor());
                this.textures.put(new ResourceLocation("ilikewood", "block/post/stripped/" + name), colored);

                colored = torch.copy();
                colored.multiplyArea(tree.getWoodColor(), 0, 8, 4, 8);
                this.textures.put(new ResourceLocation("ilikewood", "block/torch/" + name), colored);

                colored = scaffoldingSide.copy();
                colored.multiplyArea(tree.getWoodColor(), 2, 0, 12, 2);
                colored.multiplyArea(tree.getWoodColor(), 0, 2, 16, 12);
                colored.multiplyArea(tree.getWoodColor(), 2, 14, 12, 2);
                this.textures.put(new ResourceLocation("ilikewood", "block/scaffolding/side/" + name), colored);

                colored = scaffoldingBottom.copy();
                colored.multiplyArea(tree.getWoodColor(), 2, 0, 12, 2);
                colored.multiplyArea(tree.getWoodColor(), 0, 2, 16, 12);
                colored.multiplyArea(tree.getWoodColor(), 2, 14, 12, 2);
                this.textures.put(new ResourceLocation("ilikewood", "block/scaffolding/bottom/" + name), colored);

                colored = scaffoldingTop.copy();
                colored.multiplyArea(tree.getWoodColor(), 2, 0, 12, 2);
                colored.multiplyArea(tree.getWoodColor(), 0, 2, 16, 12);
                colored.multiplyArea(tree.getWoodColor(), 2, 14, 12, 2);
                this.textures.put(new ResourceLocation("ilikewood", "block/scaffolding/top/" + name), colored);

                colored = chestLeft.copy();
                colored.multiplyArea(tree.getWoodColor(), 14, 1, 14, 1);
                colored.multiplyArea(tree.getWoodColor(), 14, 12, 14, 1);
                colored.multiplyArea(tree.getWoodColor(), 27, 2, 1, 10);
                colored.multiplyArea(tree.getWoodColor(), 29, 1, 14, 12);
                colored.multiplyArea(tree.getWoodColor(), 14, 15, 14, 3);
                colored.multiplyArea(tree.getWoodColor(), 30, 15, 12, 3);
                colored.multiplyArea(tree.getWoodColor(), 44, 15, 14, 3);
                colored.multiplyArea(tree.getWoodColor(), 14, 20, 14, 12);
                colored.multiplyArea(tree.getWoodColor(), 29, 20, 14, 1);
                colored.multiplyArea(tree.getWoodColor(), 29, 31, 14, 1);
                colored.multiplyArea(tree.getWoodColor(), 42, 21, 1, 10);
                colored.multiplyArea(tree.getWoodColor(), 14, 34, 14, 8);
                colored.multiplyArea(tree.getWoodColor(), 30, 34, 12, 8);
                colored.multiplyArea(tree.getWoodColor(), 44, 34, 14, 8);
                this.textures.put(new ResourceLocation("ilikewood", "entity/chest/left/" + name), colored);

                colored = chestRight.copy();
                colored.multiplyArea(tree.getWoodColor(), 15, 1, 14, 1);
                colored.multiplyArea(tree.getWoodColor(), 15, 2, 1, 10);
                colored.multiplyArea(tree.getWoodColor(), 15, 12, 14, 1);
                colored.multiplyArea(tree.getWoodColor(), 30, 1, 14, 12);
                colored.multiplyArea(tree.getWoodColor(), 1, 15, 12, 3);
                colored.multiplyArea(tree.getWoodColor(), 15, 15, 14, 3);
                colored.multiplyArea(tree.getWoodColor(), 45, 15, 14, 3);
                colored.multiplyArea(tree.getWoodColor(), 15, 20, 14, 12);
                colored.multiplyArea(tree.getWoodColor(), 30, 20, 14, 1);
                colored.multiplyArea(tree.getWoodColor(), 30, 21, 1, 10);
                colored.multiplyArea(tree.getWoodColor(), 30, 31, 14, 1);
                colored.multiplyArea(tree.getWoodColor(), 15, 34, 14, 8);
                colored.multiplyArea(tree.getWoodColor(), 1, 34, 12, 8);
                colored.multiplyArea(tree.getWoodColor(), 43, 34, 14, 8);
                this.textures.put(new ResourceLocation("ilikewood", "entity/chest/right/" + name), colored);

                colored = chestSingle.copy();
                colored.multiplyArea(tree.getWoodColor(), 15, 1, 12, 1);
                colored.multiplyArea(tree.getWoodColor(), 15, 2, 1, 10);
                colored.multiplyArea(tree.getWoodColor(), 26, 2, 1, 10);
                colored.multiplyArea(tree.getWoodColor(), 15, 12, 12, 1);
                colored.multiplyArea(tree.getWoodColor(), 29, 1, 12, 12);
                colored.multiplyArea(tree.getWoodColor(), 1, 15, 12, 3);
                colored.multiplyArea(tree.getWoodColor(), 15, 15, 12, 3);
                colored.multiplyArea(tree.getWoodColor(), 29, 15, 12, 3);
                colored.multiplyArea(tree.getWoodColor(), 43, 15, 12, 3);
                colored.multiplyArea(tree.getWoodColor(), 15, 20, 12, 12);
                colored.multiplyArea(tree.getWoodColor(), 29, 20, 12, 1);
                colored.multiplyArea(tree.getWoodColor(), 29, 21, 1, 10);
                colored.multiplyArea(tree.getWoodColor(), 40, 21, 1, 10);
                colored.multiplyArea(tree.getWoodColor(), 29, 31, 12, 1);
                colored.multiplyArea(tree.getWoodColor(), 1, 34, 12, 8);
                colored.multiplyArea(tree.getWoodColor(), 15, 34, 12, 8);
                colored.multiplyArea(tree.getWoodColor(), 29, 34, 12, 8);
                colored.multiplyArea(tree.getWoodColor(), 43, 34, 12, 8);
                this.textures.put(new ResourceLocation("ilikewood", "entity/chest/single/" + name), colored);

                colored = pickaxe.multiply(tree.getWoodColor());
                this.textures.put(new ResourceLocation("ilikewood", "item/pickaxe/wooden/" + name), colored);

                colored = sword.multiply(tree.getWoodColor());
                this.textures.put(new ResourceLocation("ilikewood", "item/sword/wooden/" + name), colored);

                colored = shovel.multiply(tree.getWoodColor());
                this.textures.put(new ResourceLocation("ilikewood", "item/shovel/wooden/" + name), colored);

                colored = axe.multiply(tree.getWoodColor());
                this.textures.put(new ResourceLocation("ilikewood", "item/axe/wooden/" + name), colored);

                colored = hoe.multiply(tree.getWoodColor());
                this.textures.put(new ResourceLocation("ilikewood", "item/hoe/wooden/" + name), colored);

                colored = bow.copy();
                colored.multiplyArea(tree.getWoodColor(), 1, 6, 3, 10);
                colored.multiplyArea(tree.getWoodColor(), 4, 7, 1, 4);
                colored.multiplyArea(tree.getWoodColor(), 6, 1, 10, 3);
                colored.multiplyArea(tree.getWoodColor(), 7, 4, 4, 1);
                colored.multiplyArea(tree.getWoodColor(), 5, 4, 1, 1);
                colored.multiplyArea(tree.getWoodColor(), 4, 5, 1, 1);
                this.textures.put(new ResourceLocation("ilikewood", "item/bow/" + name), colored);

                colored = bowPull0.copy();
                colored.multiplyArea(tree.getWoodColor(), 1, 6, 3, 10);
                colored.multiplyArea(tree.getWoodColor(), 4, 7, 1, 4);
                colored.multiplyArea(tree.getWoodColor(), 6, 1, 10, 3);
                colored.multiplyArea(tree.getWoodColor(), 7, 4, 4, 1);
                colored.multiplyArea(tree.getWoodColor(), 4, 5, 1, 1);
                this.textures.put(new ResourceLocation("ilikewood", "item/bow/pulling/0/" + name), colored);

                colored = bowPull1.copy();
                colored.multiplyArea(tree.getWoodColor(), 1, 6, 3, 10);
                colored.multiplyArea(tree.getWoodColor(), 6, 1, 10, 3);
                colored.multiplyArea(tree.getWoodColor(), 7, 4, 5, 1);
                colored.multiplyArea(tree.getWoodColor(), 4, 7, 1, 5);
                colored.multiplyArea(tree.getWoodColor(), 4, 5, 1, 1);
                this.textures.put(new ResourceLocation("ilikewood", "item/bow/pulling/1/" + name), colored);

                colored = bowPull2.copy();
                colored.multiplyArea(tree.getWoodColor(), 2, 7, 3, 9);
                colored.multiplyArea(tree.getWoodColor(), 3, 6, 1, 1);
                colored.multiplyArea(tree.getWoodColor(), 4, 5, 1, 1);
                colored.multiplyArea(tree.getWoodColor(), 6, 3, 1, 1);
                colored.multiplyArea(tree.getWoodColor(), 7, 2, 9, 3);
                this.textures.put(new ResourceLocation("ilikewood", "item/bow/pulling/2/" + name), colored);

                colored = crossbowArrow.copy();
                colored.multiplyArea(tree.getWoodColor(), 4, 4, 8, 8);
                colored.multiplyArea(tree.getWoodColor(), 6, 1, 6, 3);
                colored.multiplyArea(tree.getWoodColor(), 1, 6, 3, 6);
                colored.multiplyArea(tree.getWoodColor(), 0, 12, 3, 2);
                colored.multiplyArea(tree.getWoodColor(), 12, 0, 2, 3);
                colored.multiplyArea(tree.getWoodColor(), 13, 11, 2, 2);
                colored.multiplyArea(tree.getWoodColor(), 11, 13, 5, 3);
                this.textures.put(new ResourceLocation("ilikewood", "item/crossbow/arrow/" + name), colored);

                colored = crossbowFirework.copy();
                colored.multiplyArea(tree.getWoodColor(), 6, 1, 6, 3);
                colored.multiplyArea(tree.getWoodColor(), 1, 6, 3, 6);
                colored.multiplyArea(tree.getWoodColor(), 0, 12, 3, 2);
                colored.multiplyArea(tree.getWoodColor(), 12, 0, 2, 3);
                colored.multiplyArea(tree.getWoodColor(), 13, 11, 2, 2);
                colored.multiplyArea(tree.getWoodColor(), 11, 13, 5, 3);
                colored.multiplyArea(tree.getWoodColor(), 4, 7, 2, 2);
                colored.multiplyArea(tree.getWoodColor(), 7, 4, 2, 2);
                colored.multiplyArea(tree.getWoodColor(), 8, 6, 2, 1);
                colored.multiplyArea(tree.getWoodColor(), 9, 7, 2, 1);
                colored.multiplyArea(tree.getWoodColor(), 10, 8, 2, 1);
                colored.multiplyArea(tree.getWoodColor(), 11, 9, 1, 1);
                colored.multiplyArea(tree.getWoodColor(), 6, 8, 1, 2);
                colored.multiplyArea(tree.getWoodColor(), 7, 9, 1, 2);
                colored.multiplyArea(tree.getWoodColor(), 8, 10, 1, 2);
                colored.multiplyArea(tree.getWoodColor(), 9, 11, 1, 1);
                this.textures.put(new ResourceLocation("ilikewood", "item/crossbow/firework/" + name), colored);

                colored = crossbowStandby.copy();
                colored.multiplyArea(tree.getWoodColor(), 4, 4, 3, 5);
                colored.multiplyArea(tree.getWoodColor(), 7, 4, 2, 3);
                colored.multiplyArea(tree.getWoodColor(), 7, 7, 1, 1);
                colored.multiplyArea(tree.getWoodColor(), 9, 7, 7, 9);
                colored.multiplyArea(tree.getWoodColor(), 8, 8, 1, 4);
                colored.multiplyArea(tree.getWoodColor(), 7, 9, 1, 2);
                colored.multiplyArea(tree.getWoodColor(), 6, 1, 6, 3);
                colored.multiplyArea(tree.getWoodColor(), 1, 6, 3, 6);
                colored.multiplyArea(tree.getWoodColor(), 0, 12, 3, 2);
                colored.multiplyArea(tree.getWoodColor(), 12, 0, 2, 3);
                this.textures.put(new ResourceLocation("ilikewood", "item/crossbow/standby/" + name), colored);

                colored = crossbowPull0.copy();
                colored.multiplyArea(tree.getWoodColor(), 10, 8, 6, 8);
                colored.multiplyArea(tree.getWoodColor(), 8, 10, 2, 3);
                colored.multiplyArea(tree.getWoodColor(), 6, 1, 6, 3);
                colored.multiplyArea(tree.getWoodColor(), 1, 6, 3, 6);
                colored.multiplyArea(tree.getWoodColor(), 0, 12, 3, 2);
                colored.multiplyArea(tree.getWoodColor(), 12, 0, 2, 3);
                colored.multiplyArea(tree.getWoodColor(), 4, 4, 5, 5);
                colored.multiplyArea(tree.getWoodColor(), 9, 6, 1, 2);
                colored.multiplyArea(tree.getWoodColor(), 6, 9, 2, 1);
                this.textures.put(new ResourceLocation("ilikewood", "item/crossbow/pulling/0/" + name), colored);

                colored = crossbowPull1.copy();
                colored.multiplyArea(tree.getWoodColor(), 6, 1, 6, 3);
                colored.multiplyArea(tree.getWoodColor(), 1, 6, 3, 6);
                colored.multiplyArea(tree.getWoodColor(), 0, 12, 3, 2);
                colored.multiplyArea(tree.getWoodColor(), 12, 0, 2, 3);
                colored.multiplyArea(tree.getWoodColor(), 4, 4, 6, 6);
                colored.multiplyArea(tree.getWoodColor(), 10, 7, 1, 2);
                colored.multiplyArea(tree.getWoodColor(), 7, 10, 2, 1);
                colored.multiplyArea(tree.getWoodColor(), 11, 9, 5, 7);
                colored.multiplyArea(tree.getWoodColor(), 9, 11, 2, 2);
                this.textures.put(new ResourceLocation("ilikewood", "item/crossbow/pulling/1/" + name), colored);

                colored = crossbowPull2.copy();
                colored.multiplyArea(tree.getWoodColor(), 4, 4, 8, 8);
                colored.multiplyArea(tree.getWoodColor(), 6, 1, 6, 3);
                colored.multiplyArea(tree.getWoodColor(), 1, 6, 3, 6);
                colored.multiplyArea(tree.getWoodColor(), 0, 12, 3, 2);
                colored.multiplyArea(tree.getWoodColor(), 12, 0, 2, 3);
                colored.multiplyArea(tree.getWoodColor(), 13, 11, 2, 2);
                colored.multiplyArea(tree.getWoodColor(), 11, 13, 5, 3);
                this.textures.put(new ResourceLocation("ilikewood", "item/crossbow/pulling/2/" + name), colored);

                colored = itemFrame.copy();
                colored.multiplyArea(tree.getWoodColor(), 1, 3, 14, 2);
                colored.multiplyArea(tree.getWoodColor(), 1, 13, 14, 2);
                colored.multiplyArea(tree.getWoodColor(), 1, 5, 2, 8);
                colored.multiplyArea(tree.getWoodColor(), 13, 5, 2, 8);
                this.textures.put(new ResourceLocation("ilikewood", "item/item_frame/" + name), colored);

                this.textures.put(new ResourceLocation("ilikewood", "item/stick/" + name),
                        stick.multiply(tree.getWoodColor()));
                this.textures.put(new ResourceLocation("ilikewood", "item/stick/shovel/" + name),
                        shovelStick.multiply(tree.getWoodColor()));
                this.textures.put(new ResourceLocation("ilikewood", "item/stick/axe/" + name),
                        axeStick.multiply(tree.getWoodColor()));
                this.textures.put(new ResourceLocation("ilikewood", "item/stick/hoe/" + name),
                        hoeStick.multiply(tree.getWoodColor()));
                this.textures.put(new ResourceLocation("ilikewood", "item/stick/sword/" + name),
                        swordStick.multiply(tree.getWoodColor()));
                this.textures.put(new ResourceLocation("ilikewood", "item/stick/pickaxe/" + name),
                        pickaxeStick.multiply(tree.getWoodColor()));

                colored = shovelStickNetherite.copy();
                colored.multiplyArea(tree.getWoodColor(), 6, 3, 8, 7);
                colored.multiplyArea(tree.getWoodColor(), 7, 10, 1, 1);
                colored.multiplyArea(tree.getWoodColor(), 2, 12, 2, 3);
                colored.multiplyArea(tree.getWoodColor(), 4, 13, 1, 2);
                this.textures.put(new ResourceLocation("ilikewood", "item/stick/shovel/netherite/" + name), colored);

                colored = swordStickNetherite.copy();
                colored.multiplyArea(tree.getWoodColor(), 2, 2, 12, 11);
                colored.multiplyArea(tree.getWoodColor(), 3, 13, 1, 1);
                colored.multiplyArea(tree.getWoodColor(), 1, 13, 1, 2);
                colored.multiplyArea(tree.getWoodColor(), 2, 14, 1, 1);
                this.textures.put(new ResourceLocation("ilikewood", "item/stick/sword/netherite/" + name), colored);

                colored = pickaxeStickNetherite.copy();
                colored.multiplyArea(tree.getWoodColor(), 2, 13, 2, 2);
                colored.multiplyArea(tree.getWoodColor(), 6, 3, 8, 7);
                colored.multiplyArea(tree.getWoodColor(), 7, 10, 1, 1);
                this.textures.put(new ResourceLocation("ilikewood", "item/stick/pickaxe/netherite/" + name), colored);

                colored = axeStickNetherite.copy();
                colored.multiplyArea(tree.getWoodColor(), 2, 13, 2, 2);
                colored.multiplyArea(tree.getWoodColor(), 6, 4, 7, 6);
                colored.multiplyArea(tree.getWoodColor(), 7, 10, 1, 1);
                this.textures.put(new ResourceLocation("ilikewood", "item/stick/axe/netherite/" + name), colored);

                colored = hoeStickNetherite.copy();
                colored.multiplyArea(tree.getWoodColor(), 6, 3, 8, 7);
                colored.multiplyArea(tree.getWoodColor(), 7, 10, 1, 1);
                colored.multiplyArea(tree.getWoodColor(), 2, 12, 2, 3);
                colored.multiplyArea(tree.getWoodColor(), 4, 13, 1, 1);
                colored.multiplyArea(tree.getWoodColor(), 4, 11, 1, 1);
                colored.multiplyArea(tree.getWoodColor(), 6, 11, 1, 1);
                colored.multiplyArea(tree.getWoodColor(), 5, 10, 1, 1);
                colored.multiplyArea(tree.getWoodColor(), 5, 12, 1, 1);
                this.textures.put(new ResourceLocation("ilikewood", "item/stick/hoe/netherite/" + name), colored);
            }

            if (tree.getLogColor() != 0) {
                colored = post.multiply(tree.getLogColor());
                this.textures.put(new ResourceLocation("ilikewood", "block/post/" + name), colored);
            }
        }
    }

    @Override
    protected void processTextures() throws IOException {
        processTexturesILW();
        Texture[] planks = new Texture[]{loadTexture("planks_base2"), loadTexture("planks_base1"), loadTexture(
                "planks_base3"), loadTexture("planks_base4")};
        Texture boatEntity = loadTexture("boat_entity_base1");
        Texture signEntity = loadTexture("sign_entity_base1");
        Texture sign = loadTexture("sign_base1");
        Texture boat = loadTexture("boat_base1");
        Texture logTop = loadTexture("log_top_base1");
        Texture log = loadTexture("log_base1");
        Texture strippedLog = loadTexture("stripped_log_base1");
        Texture strippedLogTop = loadTexture("stripped_log_top_base1");
        for (RegisteredTree tree : TreeRegistry.TREES) {
            if (tree.isNotInherited()) {
                Texture colored;
                if (tree.getLogColor() != 0) {
                    colored = log.multiply(tree.getLogColor());
                    this.textures.put(blockTexture(tree.log.getId()), colored);
                }
                if (tree.getWoodColor() != 0) {
                    colored = planks[tree.getPlankType()].multiply(tree.getWoodColor());
                    this.textures.put(blockTexture(tree.planks.getId()), colored);

                    colored = strippedLogTop.multiply(tree.getWoodColor());
                    this.textures.put(new ResourceLocation(this.modid, "block/stripped_" + tree.getName() + "_log_top"),
                            colored);

                    colored = strippedLog.multiply(tree.getWoodColor());
                    this.textures.put(new ResourceLocation(this.modid, "block/stripped_" + tree.getName() + "_log"),
                            colored);

                    colored = sign.multiply(tree.getWoodColor());
                    this.textures.put(itemTexture(tree.sign_item.getId()), colored);

                    colored = boat.copy();
                    colored.multiplyArea(tree.getWoodColor(), 2, 12, 8, 3);
                    colored.multiplyArea(tree.getWoodColor(), 9, 10, 1, 1);
                    colored.multiplyArea(tree.getWoodColor(), 0, 9, 9, 3);
                    colored.multiplyArea(tree.getWoodColor(), 0, 8, 8, 1);
                    colored.multiplyArea(tree.getWoodColor(), 0, 5, 6, 3);
                    colored.multiplyArea(tree.getWoodColor(), 3, 4, 2, 1);
                    colored.multiplyArea(tree.getWoodColor(), 5, 3, 2, 2);
                    colored.multiplyArea(tree.getWoodColor(), 6, 5, 1, 1);
                    colored.multiplyArea(tree.getWoodColor(), 7, 2, 7, 3);
                    colored.multiplyArea(tree.getWoodColor(), 9, 5, 5, 2);
                    colored.multiplyArea(tree.getWoodColor(), 10, 7, 4, 1);
                    colored.multiplyArea(tree.getWoodColor(), 11, 8, 1, 1);
                    colored.multiplyArea(tree.getWoodColor(), 13, 8, 1, 1);
                    this.textures.put(itemTexture(tree.boat_item.getId()), colored);

                    colored = boatEntity.multiply(tree.getWoodColor());
                    this.textures.put(new ResourceLocation(this.modid, "entity/boat/" + tree.getName()), colored);
                }
                if (tree.getLogColor() != 0 && tree.getWoodColor() != 0) {
                    colored = logTop.copy();
                    colored.multiplyArea(tree.getWoodColor(), 1, 1, 14, 14);
                    colored.multiplyArea(tree.getLogColor(), 0, 0, 16, 1);
                    colored.multiplyArea(tree.getLogColor(), 0, 15, 16, 1);
                    colored.multiplyArea(tree.getLogColor(), 0, 1, 1, 14);
                    colored.multiplyArea(tree.getLogColor(), 15, 1, 1, 14);
                    this.textures
                            .put(new ResourceLocation(this.modid, "block/" + tree.getName() + "_log_top"), colored);

                    colored = signEntity.copy();
                    colored.multiplyArea(tree.getWoodColor(), 0, 0, 52, 16);
                    colored.multiplyArea(tree.getLogColor(), 0, 16, 8, 14);
                    this.textures.put(new ResourceLocation("minecraft", "entity/signs/" + tree.getName()), colored);
                }
            }
        }
    }

}
