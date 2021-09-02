package de.deerangle.treemendous.world;

import com.mojang.serialization.Codec;
import de.deerangle.treemendous.main.TreeRegistry;
import de.deerangle.treemendous.tree.Tree;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class RangerHouseConfiguration implements FeatureConfiguration {

    public static final Codec<RangerHouseConfiguration> CODEC = Codec.INT.xmap(RangerHouseConfiguration::new, x -> 0);

    //private final Tree tree;

    public RangerHouseConfiguration(int a) {
        //this.tree = tree;
        //TODO
    }

    public RangerHouseConfiguration(Tree tree) {
        //this.tree = tree;
        //TODO
    }

    public Tree getTree() {
        return TreeRegistry.JUNIPER_TREE;
    }

}
