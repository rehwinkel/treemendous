package de.deerangle.treemendous.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import de.deerangle.treemendous.entity.BoatType;
import de.deerangle.treemendous.entity.CustomBoat;
import de.deerangle.treemendous.main.Treemendous;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.Map;
import java.util.stream.Collectors;

public class CustomBoatRenderer extends EntityRenderer<CustomBoat>
{

    private final Map<Integer, Pair<ResourceLocation, BoatModel>> boatResources;

    public CustomBoatRenderer(EntityRendererProvider.Context context)
    {
        super(context);
        this.boatResources = BoatType.getBoatTypeStream().map(boatType ->
                Pair.of(boatType.getId(),
                        Pair.of(
                                new ResourceLocation(Treemendous.MODID, "textures/entity/boat/" + boatType.getName() + ".png"),
                                new BoatModel(context.bakeLayer(ModelLayers.createBoatModelName(Boat.Type.OAK)))
                        )
                )
        ).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }

    @SuppressWarnings("NullableProblems")
    public void render(CustomBoat boat, float p_113930_, float p_113931_, PoseStack poseStack, MultiBufferSource bufferSource, int i)
    {
        poseStack.pushPose();
        poseStack.translate(0.0D, 0.375D, 0.0D);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - p_113930_));
        float f = (float) boat.getHurtTime() - p_113931_;
        float f1 = boat.getDamage() - p_113931_;
        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        if (f > 0.0F)
        {
            poseStack.mulPose(Vector3f.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float) boat.getHurtDir()));
        }

        float f2 = boat.getBubbleAngle(p_113931_);
        if (!Mth.equal(f2, 0.0F))
        {
            poseStack.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), boat.getBubbleAngle(p_113931_), true));
        }

        Pair<ResourceLocation, BoatModel> pair = getModelWithLocation(boat);
        ResourceLocation resourcelocation = pair.getFirst();
        BoatModel boatmodel = pair.getSecond();
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        boatmodel.setupAnim(boat, p_113931_, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = bufferSource.getBuffer(boatmodel.renderType(resourcelocation));
        boatmodel.renderToBuffer(poseStack, vertexconsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!boat.isUnderWater())
        {
            VertexConsumer vertexconsumer1 = bufferSource.getBuffer(RenderType.waterMask());
            boatmodel.waterPatch().render(poseStack, vertexconsumer1, i, OverlayTexture.NO_OVERLAY);
        }

        poseStack.popPose();
        super.render(boat, p_113930_, p_113931_, poseStack, bufferSource, i);
    }

    @SuppressWarnings("NullableProblems")
    @Deprecated
    public ResourceLocation getTextureLocation(CustomBoat boat)
    {
        return getModelWithLocation(boat).getFirst();
    }

    public Pair<ResourceLocation, BoatModel> getModelWithLocation(CustomBoat boat)
    {
        return this.boatResources.get(boat.getCustomBoatType().getId());
    }

}
