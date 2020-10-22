package deerangle.treemendous.data;

import deerangle.treemendous.main.Treemendous;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.resources.IResource;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractTextureProvider implements IDataProvider {

    protected final ExistingFileHelper existingFileHelper;
    protected final DataGenerator generator;
    protected final String modid;

    protected final Map<ResourceLocation, Texture> textures;

    public AbstractTextureProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        this.generator = generator;
        this.modid = modid;
        this.existingFileHelper = existingFileHelper;
        this.textures = new HashMap<>();
    }

    protected Texture loadTexture(String path) throws IOException {
        IResource res = existingFileHelper
                .getResource(new ResourceLocation("base", path), ResourcePackType.CLIENT_RESOURCES, ".png",
                        "templates");
        NativeImage img = NativeImage.read(res.getInputStream());
        byte[] data = new byte[img.getHeight() * img.getWidth() * 4];
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int color = img.getPixelRGBA(x, y);
                byte a = (byte) ((color >> 24) & 0xFF);
                byte b = (byte) ((color >> 16) & 0xFF);
                byte g = (byte) ((color >> 8) & 0xFF);
                byte r = (byte) (color & 0xFF);
                data[(y * img.getWidth() + x) * 4] = a;
                data[(y * img.getWidth() + x) * 4 + 1] = b;
                data[(y * img.getWidth() + x) * 4 + 2] = g;
                data[(y * img.getWidth() + x) * 4 + 3] = r;
            }
        }
        return new Texture(img.getWidth(), img.getHeight(), data);
    }

    protected abstract void processTextures() throws IOException;

    @Override
    public void act(DirectoryCache cache) throws IOException {
        processTextures();

        this.textures.forEach((location, texture) -> {
            Path texturePath = this.generator.getOutputFolder()
                    .resolve("assets/" + location.getNamespace() + "/textures/" + location.getPath() + ".png");
            byte[] data = texture.getData();
            try {
                String hash = HASH_FUNCTION.hashBytes(data).toString();
                if (!Objects.equals(cache.getPreviousHash(texturePath), hash) || !Files.exists(texturePath)) {
                    Files.createDirectories(texturePath.getParent());
                    try {
                        texture.getNativeImage().write(texturePath);
                    } catch (IOException e) {
                        Treemendous.LOGGER.error(e.getStackTrace());
                    }
                }

                cache.recordHash(texturePath, hash);
            } catch (IOException e) {
                Treemendous.LOGGER.error("Couldn't write loot table {}", texturePath, e);
            }
        });
    }

    @Override
    public String getName() {
        return "Wood Textures";
    }

    protected ResourceLocation blockTexture(ResourceLocation id) {
        return new ResourceLocation(id.getNamespace(), "block/" + id.getPath());
    }

    protected ResourceLocation itemTexture(ResourceLocation id) {
        return new ResourceLocation(id.getNamespace(), "item/" + id.getPath());
    }

    class Texture {
        private final int width;
        private final int height;
        private final byte[] data;

        private Texture(int width, int height, byte[] data) {
            assert data.length == width * height * 4;
            this.data = data;
            this.width = width;
            this.height = height;
        }

        private byte[] getData() {
            return this.data;
        }

        public Texture multiply(int color) {
            Texture newTex = this.copy();
            newTex.multiplyArea(color, 0, 0, this.width, this.height);
            return newTex;
        }

        public Texture copy() {
            return new Texture(this.width, this.height, this.data.clone());
        }

        public void multiplyArea(int color, int x, int y, int areaWidth, int areaHeight) {
            float colorR = ((color >> 16) & 0xFF) / 255f;
            float colorG = ((color >> 8) & 0xFF) / 255f;
            float colorB = (color & 0xFF) / 255f;
            for (int ity = y; ity < y + areaHeight; ity++) {
                for (int itx = x; itx < x + areaWidth; itx++) {
                    int i = (this.width * ity + itx) * 4;
                    float pixelR = (this.data[i + 3] & 0xFF) / 255f;
                    float pixelG = (this.data[i + 2] & 0xFF) / 255f;
                    float pixelB = (this.data[i + 1] & 0xFF) / 255f;
                    this.data[i + 3] = (byte) (255f * colorR * pixelR);
                    this.data[i + 2] = (byte) (255f * colorG * pixelG);
                    this.data[i + 1] = (byte) (255f * colorB * pixelB);
                }
            }
        }

        private NativeImage getNativeImage() {
            NativeImage img = new NativeImage(this.width, this.height, false);
            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    int value = (data[(y * img.getWidth() + x) * 4] & 0xFF) << 24;
                    int j = (data[(y * img.getWidth() + x) * 4 + 1] & 0xFF) << 16;
                    value |= j;
                    int k = (data[(y * img.getWidth() + x) * 4 + 2] & 0xFF) << 8;
                    value |= k;
                    int l = data[(y * img.getWidth() + x) * 4 + 3];
                    value |= l & 0xFF;
                    img.setPixelRGBA(x, y, value);
                }
            }
            return img;
        }
    }
}
