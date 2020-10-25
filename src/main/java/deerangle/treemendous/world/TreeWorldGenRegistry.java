package deerangle.treemendous.world;

import com.mojang.datafixers.Dynamic;
import deerangle.treemendous.tree.foliage.*;
import net.minecraft.util.registry.Registry;
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
    /*
    public static final TrunkPlacerType<CrossTrunkPlacer> CROSS_TRUNK_PLACER = registerTrunkPlacerType(
            "cross_trunk_placer", CrossTrunkPlacer.CODEC);
    public static final TrunkPlacerType<AshTrunkPlacer> ASH_TRUNK_PLACER = registerTrunkPlacerType("ash_trunk_placer",
            AshTrunkPlacer.CODEC);
     */

    public static void register() {
    }

    private static <P extends FoliagePlacer> FoliagePlacerType<P> registerFoliagePlacerType(String name, Function<Dynamic<?>, P> codec) {
        return Registry.register(Registry.FOLIAGE_PLACER_TYPE, name, new FoliagePlacerType<>(codec));
    }

    /*
    private static <P extends AbstractTrunkPlacer> TrunkPlacerType<P> registerTrunkPlacerType(String name, Codec<P> codec) {
        return Registry.register(Registry.TRUNK_REPLACER, Treemendous.MODID + ":" + name, new TrunkPlacerType<>(codec));
    }
    */

}
