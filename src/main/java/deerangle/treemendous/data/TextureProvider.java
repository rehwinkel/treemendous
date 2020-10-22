package deerangle.treemendous.data;

import deerangle.treemendous.tree.RegisteredTree;
import deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.io.IOException;

public class TextureProvider extends AbstractTextureProvider {

    public TextureProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void processTextures() throws IOException {
        Texture tex = loadTexture("planks_base2");
        for (RegisteredTree tree : TreeRegistry.TREES) {
            if (tree.isNotInherited()) {
                Texture colored = tex.multiply(tree.getWoodColor());
                this.textures.put(tree.planks.getId(), colored);
            }
        }
    }

}
