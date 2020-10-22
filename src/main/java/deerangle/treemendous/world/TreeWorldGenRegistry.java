package deerangle.treemendous.world;

import com.mojang.serialization.Codec;
import deerangle.treemendous.main.Treemendous;
import deerangle.treemendous.tree.foliage.*;
import deerangle.treemendous.tree.trunk.AshTrunkPlacer;
import deerangle.treemendous.tree.trunk.CrossTrunkPlacer;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;

public class TreeWorldGenRegistry {

    public static final FoliagePlacerType<RoundedFoliagePlacer> ROUNDED_FOLIAGE_PLACER = registerFoliagePlacerType(
            "rounded_foliage_placer", RoundedFoliagePlacer.CODEC);
    public static final FoliagePlacerType<WillowFoliagePlacer> WILLOW_FOLIAGE_PLACER = registerFoliagePlacerType(
            "willow_foliage_placer", WillowFoliagePlacer.CODEC);
    public static final FoliagePlacerType<ParabolaFoliagePlacer> PARABOLA_FOLIAGE_PLACER = registerFoliagePlacerType(
            "parabola_foliage_placer", ParabolaFoliagePlacer.CODEC);
    public static final FoliagePlacerType<PointyFoliagePlacer> POINTY_FOLIAGE_PLACER = registerFoliagePlacerType(
            "pointy_foliage_placer", PointyFoliagePlacer.CODEC);
    public static final FoliagePlacerType<BallFoliagePlacer> BALL_FOLIAGE_PLACER = registerFoliagePlacerType(
            "ball_foliage_placer", BallFoliagePlacer.CODEC);
    public static final TrunkPlacerType<CrossTrunkPlacer> CROSS_TRUNK_PLACER = registerTrunkPlacerType(
            "cross_trunk_placer", CrossTrunkPlacer.CODEC);
    public static final TrunkPlacerType<AshTrunkPlacer> ASH_TRUNK_PLACER = registerTrunkPlacerType("ash_trunk_placer",
            AshTrunkPlacer.CODEC);

    public static void register() {
    }

    private static <P extends FoliagePlacer> FoliagePlacerType<P> registerFoliagePlacerType(String name, Codec<P> codec) {
        return Registry
                .register(Registry.FOLIAGE_PLACER_TYPE, Treemendous.MODID + ":" + name, new FoliagePlacerType<>(codec));
    }

    private static <P extends AbstractTrunkPlacer> TrunkPlacerType<P> registerTrunkPlacerType(String name, Codec<P> codec) {
        return Registry.register(Registry.TRUNK_REPLACER, Treemendous.MODID + ":" + name, new TrunkPlacerType<>(codec));
    }

}
