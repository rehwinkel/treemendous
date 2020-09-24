package deerangle.treemendous.data;

import deerangle.treemendous.main.TreeRegistry;
import deerangle.treemendous.tree.RegisteredTree;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class TextureProvider implements IDataProvider {

    private final String modid;
    private final DataGenerator generator;
    private final ExistingFileHelper existingHelper;

    public TextureProvider(DataGenerator gen, String modid, ExistingFileHelper existingHelper) {
        this.generator = gen;
        this.modid = modid;
        this.existingHelper = existingHelper;
    }

    public Path newTexturePath(ResourceLocation texture) {
        return this.generator.getOutputFolder()
                .resolve("assets/" + texture.getNamespace() + "/textures/" + texture.getPath() + ".png");
    }


    public InputStream existingTextureStream(ResourceLocation texture) throws IOException {
        return this.existingHelper.getResource(texture, ResourcePackType.CLIENT_RESOURCES, ".png", "textures")
                .getInputStream();
    }

    public void newTexture(DirectoryCache cache, ResourceLocation texture, Texture data) throws IOException {
        save(cache, data, newTexturePath(texture));
    }

    private void save(DirectoryCache cache, Texture texture, Path target) throws IOException {
        ByteBuffer buff = ByteBuffer.allocate(texture.data.length * 4);
        IntBuffer intBuffer = buff.asIntBuffer();
        intBuffer.put(texture.data);
        buff.flip();
        byte[] bytes = buff.array();
        String hash = IDataProvider.HASH_FUNCTION.hashBytes(bytes).toString();
        if (!Objects.equals(cache.getPreviousHash(target), hash) || !Files.exists(target)) {
            Files.createDirectories(target.getParent());
            NativeImage img = new NativeImage(NativeImage.PixelFormat.RGBA, texture.width, texture.height, false);
            for (int x = 0; x < texture.width; x++) {
                for (int y = 0; y < texture.height; y++) {
                    img.setPixelRGBA(x, y, texture.data[y * texture.width + x]);
                }
            }
            img.write(target);
        }

        cache.recordHash(target, hash);
    }

    public Texture existingTexture(ResourceLocation texture) throws IOException {
        NativeImage image = NativeImage.read(existingTextureStream(texture));
        return new Texture(image.getWidth(), image.getHeight(), image.makePixelArray());
    }

    @Override
    public void act(DirectoryCache cache) throws IOException {
        /*
        Texture generic_planks = existingTexture(new ResourceLocation(this.modid, "block/generic_planks"));
        generic_planks.multiply(0xFFFF0000);
        newTexture(cache, new ResourceLocation(this.modid, "block/beech_planks"), generic_planks);
        */
        for (RegisteredTree tree : TreeRegistry.trees) {
            Texture planks = existingTexture(new ResourceLocation(this.modid, "block/" + tree.getEnglishName() + "_planks"));
            Texture log = existingTexture(new ResourceLocation(this.modid, "block/" + tree.getEnglishName() + "_log"));
            Texture sign = new Texture(64, 32);
            sign.paste(planks, 0, 2, 16, 14, 2, 0);
            sign.paste(planks, 0, 2, 16, 14, 18, 0);
            sign.paste(planks, 0, 2, 16, 14, 34, 0);
            sign.paste(planks, 0, 4, 2, 12, 0, 2);
            sign.paste(planks, 0, 4, 2, 12, 50, 2);
            sign.paste(planks, 12, 3, 4, 2, 2, 14);
            sign.paste(log, 0, 0, 8, 14, 0, 16);
            newTexture(cache, new ResourceLocation("minecraft", "entity/signs/" + tree.getEnglishName()), sign);
        }
    }

    @Override
    public String getName() {
        return "Textures: " + this.modid;
    }

    private class Texture {
        private final int width;
        private final int height;
        private final int[] data;

        private Texture(int width, int height, int[] data) {
            this.width = width;
            this.height = height;
            this.data = data;
        }

        private Texture(int width, int height) {
            this.width = width;
            this.height = height;
            this.data = new int[width * height];
        }

        private void paste(Texture texture, int offsetX, int offsetY, int width, int height, int targetX, int targetY) {
            assert texture.width >= width && texture.height >= height;
            assert this.width >= width && this.height >= height;
            for (int row = 0; row < height; row++) {
                int srcY = offsetY + row;
                int dstY = targetY + row;
                int srcOffset = offsetX + texture.width * srcY;
                int dstOffset = targetX + this.width * dstY;
                System.arraycopy(texture.data, srcOffset, this.data, dstOffset, width);
            }
        }

        private void multiply(int color) {
            for (int i = 0; i < this.data.length; i++) {
                float colorA = (float) (color >> 24 & 0xFF) / 255F;
                float colorR = (float) (color >> 16 & 0xFF) / 255F;
                float colorG = (float) (color >> 8 & 0xFF) / 255F;
                float colorB = (float) (color & 0xFF) / 255F;
                float srcR = (float) (this.data[i] >> 24 & 0xFF) / 255F;
                float srcG = (float) (this.data[i] >> 16 & 0xFF) / 255F;
                float srcB = (float) (this.data[i] >> 8 & 0xFF) / 255F;
                float srcA = (float) (this.data[i] & 0xFF) / 255F;
                int dst = (byte) ((colorR * srcR) * 255F) << 24;
                dst |= (byte) ((colorG * srcG) * 255F) << 16;
                dst |= (byte) ((colorB * srcB) * 255F) << 8;
                dst |= (byte) ((colorA * srcA) * 255F);
                this.data[i] = dst;
            }
        }
    }
}
