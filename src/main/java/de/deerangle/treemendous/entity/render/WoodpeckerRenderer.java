package de.deerangle.treemendous.entity.render;

import de.deerangle.treemendous.entity.Woodpecker;
import de.deerangle.treemendous.main.ClientHandler;
import de.deerangle.treemendous.main.Treemendous;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WoodpeckerRenderer extends MobRenderer<Woodpecker, WoodpeckerModel>
{
    public static final ResourceLocation[] WOODPECKER_LOCATIONS = new ResourceLocation[]{
            new ResourceLocation(Treemendous.MOD_ID, "textures/entity/woodpecker/woodpecker.png")
    };

    public WoodpeckerRenderer(EntityRendererProvider.Context context)
    {
        super(context, new WoodpeckerModel(context.bakeLayer(ClientHandler.WOODPECKER_LAYER)), 0.2f); // 0.2 is shadow radius
    }

    @Override
    public ResourceLocation getTextureLocation(Woodpecker woodpecker)
    {
        return WOODPECKER_LOCATIONS[0];
    }

}
