package de.deerangle.treemendous.blockentity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import de.deerangle.treemendous.blockentity.ChoppingBlockBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;

public class ChoppingBlockRenderer implements BlockEntityRenderer<ChoppingBlockBlockEntity> {

    public ChoppingBlockRenderer(BlockEntityRendererProvider.Context context) {
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void render(ChoppingBlockBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int i, int j) {
        ItemStack currentItem = blockEntity.getInventory().getStackInSlot(0);
        Direction direction = Direction.NORTH;
        if (!currentItem.isEmpty()) {
            poseStack.pushPose();
            poseStack.translate(0.5D, 12.0D / 16.0D, 0.5D);
            Direction direction1 = Direction.from2DDataValue((j + direction.get2DDataValue()) % 4);
            float f = -direction1.toYRot();
            //poseStack.mulPose(Vector3f.YP.rotationDegrees(f));
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(-180.0F));
            //poseStack.translate(-0.3125D, -0.3125D, 0.0D);
            poseStack.scale(0.75f, 0.75f, 0.75f);
            Minecraft.getInstance().getItemRenderer().renderStatic(currentItem, ItemTransforms.TransformType.FIXED, i, j, poseStack, bufferSource, i + j);
            poseStack.popPose();
        }
    }

}
