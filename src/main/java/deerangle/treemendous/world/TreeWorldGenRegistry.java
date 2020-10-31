package deerangle.treemendous.world;

import com.mojang.datafixers.Dynamic;
import deerangle.treemendous.main.Treemendous;
import deerangle.treemendous.tree.CrossTreeFeature;
import deerangle.treemendous.tree.CrossTreeFeatureConfig;
import deerangle.treemendous.tree.foliage.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

import java.util.function.Function;

public class TreeWorldGenRegistry {

    public static final FoliagePlacerType<RoundedFoliagePlacer> ROUNDED_FOLIAGE_PLACER = registerFoliagePlacerType(
            "rounded_foliage_placer", RoundedFoliagePlacer::new);
    public static final FoliagePlacerType<WillowFoliagePlacer> WILLOW_FOLIAGE_PLACER = registerFoliagePlacerType(
            "willow_foliage_placer", WillowFoliagePlacer::new);
    public static final FoliagePlacerType<ParabolaFoliagePlacer> PARABOLA_FOLIAGE_PLACER = registerFoliagePlacerType(
            "parabola_foliage_placer", ParabolaFoliagePlacer::new);
    public static final FoliagePlacerType<PointyFoliagePlacer> POINTY_FOLIAGE_PLACER = registerFoliagePlacerType(
            "pointy_foliage_placer", PointyFoliagePlacer::new);
    public static final FoliagePlacerType<BallFoliagePlacer> BALL_FOLIAGE_PLACER = registerFoliagePlacerType(
            "ball_foliage_placer", BallFoliagePlacer::new);

    private static boolean registered = false;
    private static Feature<CrossTreeFeatureConfig> CROSS_TREE;

    public static <P extends IFeatureConfig> Feature<P> registerFeature(String name, Feature<P> feature) {
        return Registry.register(Registry.FEATURE, Treemendous.MODID + ":" + name, feature);
    }

    public static void registerFeatures() {
        if (!registered) {
            registered = true;
            CROSS_TREE = registerFeature("cross_tree",
                    new CrossTreeFeature(CrossTreeFeatureConfig::deserializeCrossTree));
        }
    }

    public static Feature<CrossTreeFeatureConfig> getCrossTree() {
        return CROSS_TREE;
    }

    private static <P extends FoliagePlacer> FoliagePlacerType<P> registerFoliagePlacerType(String name, Function<Dynamic<?>, P> codec) {
        return Registry
                .register(Registry.FOLIAGE_PLACER_TYPE, Treemendous.MODID + ":" + name, new FoliagePlacerType<>(codec));
    }

}
