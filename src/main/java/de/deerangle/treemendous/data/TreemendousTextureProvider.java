package de.deerangle.treemendous.data;

import com.google.common.collect.ImmutableList;
import de.deerangle.treemendous.tree.Tree;
import de.deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.io.IOException;

public class TreemendousTextureProvider extends AbstractTextureProvider {

    private final Texture[] planksTextures;
    private final Texture boatEntityTexture;
    private final Texture signEntityTexture;
    private final Texture boatTexture;
    private final Texture signTexture;
    private final Texture logTopTexture;
    private final Texture logSideTexture;
    private final Texture strippedLogTopTexture;
    private final Texture strippedLogSideTexture;

    public TreemendousTextureProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) throws IOException {
        super(generator, modid, existingFileHelper);
        this.planksTextures = new Texture[]{loadTexture("planks_base2"), loadTexture("planks_base1"), loadTexture(
                "planks_base3"), loadTexture("planks_base4")};
        this.boatEntityTexture = loadTexture("boat_entity_base1");
        this.signEntityTexture = loadTexture("sign_entity_base1");
        this.signTexture = loadTexture("sign_base1");
        this.boatTexture = loadTexture("boat_base1");
        this.logTopTexture = loadTexture("log_top_base1");
        this.logSideTexture = loadTexture("log_base1");
        this.strippedLogSideTexture = loadTexture("stripped_log_base1");
        this.strippedLogTopTexture = loadTexture("stripped_log_top_base1");
    }

    @Override
    protected void processTextures() {
        for (Tree tree : ImmutableList.of(TreeRegistry.JUNIPER_TREE)) {
            Texture texture;
            int barkColor = tree.getConfig().appearance().barkColor();
            int woodColor = tree.getConfig().appearance().woodColor();
            if (barkColor != 0) {
                texture = this.logSideTexture.multiply(barkColor);
                this.textures.put(blockTexture(tree.getLog()), texture);
            }
            if (woodColor != 0) {
                texture = this.planksTextures[tree.getConfig().appearance().plankTextureType()].multiply(woodColor);
                this.textures.put(blockTexture(tree.getPlanks()), texture);

                texture = this.strippedLogTopTexture.multiply(woodColor);
                this.textures.put(blockTexture(tree.getStrippedLog(), "top"), texture);

                texture = this.strippedLogSideTexture.multiply(woodColor);
                this.textures.put(blockTexture(tree.getStrippedLog()), texture);
            }
            if (barkColor != 0 && woodColor != 0) {
                texture = this.logTopTexture.copy();
                texture.multiplyArea(woodColor, 1, 1, 14, 14);
                texture.multiplyArea(barkColor, 0, 0, 16, 1);
                texture.multiplyArea(barkColor, 0, 15, 16, 1);
                texture.multiplyArea(barkColor, 0, 1, 1, 14);
                texture.multiplyArea(barkColor, 15, 1, 1, 14);
                this.textures.put(blockTexture(tree.getLog(), "top"), texture);
            }
            /*
            if (woodColor != 0) {
                texture = sign.multiply(woodColor);
                this.textures.put(itemTexture(tree.sign_item.getId()), texture);

                texture = boat.copy();
                texture.multiplyArea(woodColor, 2, 12, 8, 3);
                texture.multiplyArea(woodColor, 9, 10, 1, 1);
                texture.multiplyArea(woodColor, 0, 9, 9, 3);
                texture.multiplyArea(woodColor, 0, 8, 8, 1);
                texture.multiplyArea(woodColor, 0, 5, 6, 3);
                texture.multiplyArea(woodColor, 3, 4, 2, 1);
                texture.multiplyArea(woodColor, 5, 3, 2, 2);
                texture.multiplyArea(woodColor, 6, 5, 1, 1);
                texture.multiplyArea(woodColor, 7, 2, 7, 3);
                texture.multiplyArea(woodColor, 9, 5, 5, 2);
                texture.multiplyArea(woodColor, 10, 7, 4, 1);
                texture.multiplyArea(woodColor, 11, 8, 1, 1);
                texture.multiplyArea(woodColor, 13, 8, 1, 1);
                this.textures.put(itemTexture(tree.boat_item.getId()), texture);

                texture = boatEntity.multiply(woodColor);
                this.textures.put(new ResourceLocation(this.modid, "entity/boat/" + tree.getName()), texture);
            }
            if (barkColor != 0 && woodColor != 0) {
                texture = logTop.copy();
                texture.multiplyArea(woodColor, 1, 1, 14, 14);
                texture.multiplyArea(barkColor, 0, 0, 16, 1);
                texture.multiplyArea(barkColor, 0, 15, 16, 1);
                texture.multiplyArea(barkColor, 0, 1, 1, 14);
                texture.multiplyArea(barkColor, 15, 1, 1, 14);
                this.textures
                        .put(new ResourceLocation(this.modid, "block/" + tree.getName() + "_log_top"), texture);

                texture = signEntity.copy();
                texture.multiplyArea(woodColor, 0, 0, 52, 16);
                texture.multiplyArea(barkColor, 0, 16, 8, 14);
                this.textures.put(new ResourceLocation("minecraft", "entity/signs/" + tree.getName()), texture);
            }
            */
        }
    }

}