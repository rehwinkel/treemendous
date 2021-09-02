package de.deerangle.treemendous.world;

import com.mojang.serialization.Codec;
import de.deerangle.treemendous.tree.RegisteredTree;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.registries.RegistryManager;

public class RangerHouseConfiguration implements FeatureConfiguration {

    public static final Codec<RangerHouseConfiguration> CODEC = Codec.STRING.xmap(
            name -> new RangerHouseConfiguration(RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValue(new ResourceLocation(name))),
            config -> config.tree.getRegistryName().toString()
    );

    public final RegisteredTree tree;

    public RangerHouseConfiguration(RegisteredTree tree) {
        this.tree = tree;
    }

    public RegisteredTree getTree() {
        return this.tree;
    }

}
