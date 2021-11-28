package de.deerangle.treemendous.world;

import com.mojang.serialization.Codec;
import de.deerangle.treemendous.tree.RegisteredTree;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.registries.RegistryManager;

public record RangerHouseConfiguration(RegisteredTree tree) implements FeatureConfiguration
{

    public static final Codec<RangerHouseConfiguration> CODEC = Codec.STRING.xmap(
            name -> new RangerHouseConfiguration(RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValue(new ResourceLocation(name))),
            config -> {
                assert config.tree.getRegistryName() != null;
                return config.tree.getRegistryName().toString();
            }
    );

}
