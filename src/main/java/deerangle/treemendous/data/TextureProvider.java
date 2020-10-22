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

    @Override
    protected void processTextures() throws IOException {
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
                Texture colored = planks[tree.getPlankType()].multiply(tree.getWoodColor());
                if (tree.getWoodColor() != 0) {
                    this.textures.put(blockTexture(tree.planks.getId()), colored);
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
                }
                if (tree.getLogColor() != 0) {
                    colored = log.multiply(tree.getLogColor());
                    this.textures.put(blockTexture(tree.log.getId()), colored);
                }
                if (tree.getWoodColor() != 0) {
                    colored = strippedLogTop.multiply(tree.getWoodColor());
                    this.textures.put(new ResourceLocation(this.modid, "block/stripped_" + tree.getName() + "_log_top"),
                            colored);
                    colored = strippedLog.multiply(tree.getWoodColor());
                    this.textures.put(new ResourceLocation(this.modid, "block/stripped_" + tree.getName() + "_log"),
                            colored);
                    colored = sign.multiply(tree.getWoodColor());
                    this.textures.put(itemTexture(tree.sign_item.getId()), colored);
                }
                if (tree.getWoodColor() != 0) {
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
                }
                if (tree.getWoodColor() != 0) {
                    colored = boatEntity.multiply(tree.getWoodColor());
                    this.textures.put(new ResourceLocation(this.modid, "entity/boat/" + tree.getName()), colored);
                }
                if (tree.getWoodColor() != 0 && tree.getLogColor() != 0) {
                    colored = signEntity.copy();
                    colored.multiplyArea(tree.getWoodColor(), 0, 0, 52, 16);
                    colored.multiplyArea(tree.getLogColor(), 0, 16, 8, 14);
                    this.textures.put(new ResourceLocation("minecraft", "entity/signs/" + tree.getName()), colored);
                }
            }
        }
    }

}
