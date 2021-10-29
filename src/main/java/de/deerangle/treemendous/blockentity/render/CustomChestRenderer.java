package de.deerangle.treemendous.blockentity.render;

import com.google.common.collect.ImmutableMap;
import de.deerangle.treemendous.block.CustomChestBlock;
import de.deerangle.treemendous.blockentity.CustomChestBlockEntity;
import de.deerangle.treemendous.main.Treemendous;
import de.deerangle.treemendous.tree.RegisteredTree;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.registries.RegistryManager;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CustomChestRenderer extends ChestRenderer<CustomChestBlockEntity>
{

    private final boolean xmasTextures;
    private final Map<String, Material> defaultMaterials;
    private final Map<String, Material> leftMaterials;
    private final Map<String, Material> rightMaterials;

    public CustomChestRenderer(BlockEntityRendererProvider.Context context)
    {
        super(context);
        Calendar calendar = Calendar.getInstance();
        this.xmasTextures = calendar.get(Calendar.MONTH) + 1 == 12 && calendar.get(Calendar.DATE) >= 24 && calendar.get(Calendar.DATE) <= 26;

        Map<String, Material> defaultMaterials = new HashMap<>();
        Map<String, Material> leftMaterials = new HashMap<>();
        Map<String, Material> rightMaterials = new HashMap<>();
        addChestMaterials("crimson", defaultMaterials, leftMaterials, rightMaterials);
        addChestMaterials("warped", defaultMaterials, leftMaterials, rightMaterials);
        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues())
        {
            String woodName = regTree.getRegistryName().getPath();
            if (!"oak".equals(woodName))
            {
                addChestMaterials(woodName, defaultMaterials, leftMaterials, rightMaterials);
            }
        }
        this.defaultMaterials = ImmutableMap.copyOf(defaultMaterials);
        this.leftMaterials = ImmutableMap.copyOf(leftMaterials);
        this.rightMaterials = ImmutableMap.copyOf(rightMaterials);
    }

    private void addChestMaterials(String woodName, Map<String, Material> defaultMaterials, Map<String, Material> leftMaterials, Map<String, Material> rightMaterials)
    {
        defaultMaterials.put(woodName, new Material(new ResourceLocation("textures/atlas/chest.png"), new ResourceLocation(Treemendous.MODID, "entity/chest/" + woodName)));
        leftMaterials.put(woodName, new Material(new ResourceLocation("textures/atlas/chest.png"), new ResourceLocation(Treemendous.MODID, "entity/chest/" + woodName + "_left")));
        rightMaterials.put(woodName, new Material(new ResourceLocation("textures/atlas/chest.png"), new ResourceLocation(Treemendous.MODID, "entity/chest/" + woodName + "_right")));
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected Material getMaterial(CustomChestBlockEntity blockEntity, ChestType chestType)
    {
        CustomChestBlock chestBlock = (CustomChestBlock) blockEntity.getBlockState().getBlock();
        return chooseMaterial(chestBlock.getWoodType(), chestType, xmasTextures);
    }

    public Material chooseMaterial(String woodType, ChestType chestType, boolean isChristmas)
    {
        Material chestLocation = this.defaultMaterials.get(woodType);
        Material chestLocationLeft = this.leftMaterials.get(woodType);
        Material chestLocationRight = this.rightMaterials.get(woodType);
        if (isChristmas)
        {
            return chooseMaterial(chestType, Sheets.CHEST_XMAS_LOCATION, Sheets.CHEST_XMAS_LOCATION_LEFT, Sheets.CHEST_XMAS_LOCATION_RIGHT);
        } else
        {
            return chooseMaterial(chestType, chestLocation, chestLocationLeft, chestLocationRight);
        }
    }

    private Material chooseMaterial(ChestType chestType, Material defaultMaterial, Material leftMaterial, Material rightMaterial)
    {
        return switch (chestType)
                {
                    case LEFT -> leftMaterial;
                    case RIGHT -> rightMaterial;
                    default -> defaultMaterial;
                };
    }

}
