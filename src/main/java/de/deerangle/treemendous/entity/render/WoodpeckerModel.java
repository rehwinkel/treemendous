package de.deerangle.treemendous.entity.render;

import de.deerangle.treemendous.entity.Woodpecker;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class WoodpeckerModel extends HierarchicalModel<Woodpecker>
{

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart leftWing;
    private final ModelPart rightWing;
    private final ModelPart neck;
    private final ModelPart head;

    public WoodpeckerModel(ModelPart layer)
    {
        this.root = layer;
        this.body = root.getChild("body");
        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");
        this.leftWing = body.getChild("left_wing");
        this.rightWing = body.getChild("right_wing");
        this.neck = body.getChild("neck");
        this.head = neck.getChild("head");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition rootPart = meshdefinition.getRoot();

        rootPart.addOrReplaceChild("left_leg", CubeListBuilder.create()
                        .texOffs(8, 7).addBox(0.5F, 0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 22.0F, 0.0F)
        );

        rootPart.addOrReplaceChild("right_leg", CubeListBuilder.create()
                        .texOffs(8, 7).addBox(-1.5F, 0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 22.0F, 0.0F)
        );

        PartDefinition bodyPart = rootPart.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(21, 7).addBox(-1.0F, 2.0F, 2.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(13, 6).addBox(-1.0F, 0.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                        .texOffs(12, 0).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 22.0F, 0.0F, 0.7854F, 0.0F, 0.0F)
        );

        bodyPart.addOrReplaceChild("right_wing", CubeListBuilder.create()
                        .texOffs(6, 0).mirror().addBox(-1.0F, -0.0101F, -2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
                PartPose.offset(-1.5F, -2.5F, 2.5F)
        );

        bodyPart.addOrReplaceChild("left_wing", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(0.0F, -0.0101F, -2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(1.5F, -2.5F, 2.5F)
        );

        PartDefinition neckPart = bodyPart.addOrReplaceChild("neck", CubeListBuilder.create()
                        .texOffs(0, 4).addBox(-1.0F, -2.0F, -2.0F, 1.98F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.01f, -3.0F, 3.0F, -0.7854F, 0.0f, 0.0f)
        );

        neckPart.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(8, 12).addBox(-0.5F, 1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 10).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-0.01F, -2.0F, 0.0F, -0.2f, 0.0F, 0.0F)
        );

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public ModelPart root()
    {
        return this.root;
    }

    @Override
    public void setupAnim(Woodpecker bird, float p_102619_, float p_102620_, float p_102621_, float pitch, float yaw)
    {
        if (bird.isFlying())
        {
            this.leftWing.yRot = -(float) Math.PI / 2.0f;
            this.rightWing.yRot = (float) Math.PI / 2.0f;
            this.body.xRot = (float) Math.PI / 2.0f;
            this.leftLeg.xRot = (float) Math.PI / 2.0f;
            this.rightLeg.xRot = (float) Math.PI / 2.0f;
            this.neck.xRot = -0.1f;
        } else
        {
            this.leftWing.yRot = 0f;
            this.rightWing.yRot = 0f;
            this.body.xRot = (float) Math.PI / 4.0f;
            this.leftLeg.xRot = bird.jumpProgression;
            this.rightLeg.xRot = bird.jumpProgression;
            this.neck.xRot = -0.4f;
        }
    }

}
