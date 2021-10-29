package de.deerangle.treemendous.item.render;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import de.deerangle.treemendous.block.CustomChestBlock;
import de.deerangle.treemendous.blockentity.CustomChestBlockEntity;
import de.deerangle.treemendous.main.ExtraRegistry;
import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.Tree;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.registries.RegistryManager;

import java.util.HashMap;
import java.util.Map;

public class CustomChestItemRenderer extends BlockEntityWithoutLevelRenderer
{

    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;
    private final Map<String, CustomChestBlockEntity> chests;

    public CustomChestItemRenderer(BlockEntityRenderDispatcher renderDispatcher, EntityModelSet entityModelSet)
    {
        super(renderDispatcher, entityModelSet);
        this.blockEntityRenderDispatcher = renderDispatcher;

        Map<String, CustomChestBlockEntity> chestMap = new HashMap<>();
        chestMap.put("crimson", new CustomChestBlockEntity(BlockPos.ZERO, ExtraRegistry.CRIMSON_CHEST.get().defaultBlockState()));
        chestMap.put("warped", new CustomChestBlockEntity(BlockPos.ZERO, ExtraRegistry.WARPED_CHEST.get().defaultBlockState()));
        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues())
        {
            String woodName = regTree.getRegistryName().getPath();
            if (!"oak".equals(woodName))
            {
                Tree tree = regTree.getTree();
                chestMap.put(woodName, new CustomChestBlockEntity(BlockPos.ZERO, tree.getChest().defaultBlockState()));
            }
        }
        chests = ImmutableMap.copyOf(chestMap);
    }

    public CustomChestItemRenderer()
    {
        this(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @SuppressWarnings("NullableProblems")
    public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack poseStack, MultiBufferSource bufferSource, int i, int j)
    {
        Item item = itemStack.getItem();
        if (item instanceof BlockItem)
        {
            Block block = ((BlockItem) item).getBlock();
            if (block instanceof CustomChestBlock)
            {
                String woodType = ((CustomChestBlock) block).getWoodType();
                BlockEntity blockentity = this.chests.get(woodType);
                this.blockEntityRenderDispatcher.renderItem(blockentity, poseStack, bufferSource, i, j);
            }
        }
    }

}
