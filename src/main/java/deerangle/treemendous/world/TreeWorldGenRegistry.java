package deerangle.treemendous.world;

import com.mojang.serialization.Codec;
import deerangle.treemendous.main.Treemendous;
import deerangle.treemendous.tree.foliage.*;
import deerangle.treemendous.tree.trunk.AshTrunkPlacer;
import deerangle.treemendous.tree.trunk.CrossTrunkPlacer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TreeWorldGenRegistry {

    public static FoliagePlacerType<RoundedFoliagePlacer> ROUNDED_FOLIAGE_PLACER; //= registerFoliagePlacerType("rounded_foliage_placer", RoundedFoliagePlacer.CODEC);
    public static FoliagePlacerType<WillowFoliagePlacer> WILLOW_FOLIAGE_PLACER; //= registerFoliagePlacerType("willow_foliage_placer", WillowFoliagePlacer.CODEC);
    public static FoliagePlacerType<ParabolaFoliagePlacer> PARABOLA_FOLIAGE_PLACER; //= registerFoliagePlacerType("parabola_foliage_placer", ParabolaFoliagePlacer.CODEC);
    public static FoliagePlacerType<PointyFoliagePlacer> POINTY_FOLIAGE_PLACER; //= registerFoliagePlacerType("pointy_foliage_placer", PointyFoliagePlacer.CODEC);
    public static FoliagePlacerType<BallFoliagePlacer> BALL_FOLIAGE_PLACER; //= registerFoliagePlacerType("ball_foliage_placer", BallFoliagePlacer.CODEC);
    public static final TrunkPlacerType<CrossTrunkPlacer> CROSS_TRUNK_PLACER = registerTrunkPlacerType(
            "cross_trunk_placer", CrossTrunkPlacer.CODEC);
    public static final TrunkPlacerType<AshTrunkPlacer> ASH_TRUNK_PLACER = registerTrunkPlacerType("ash_trunk_placer",
            AshTrunkPlacer.CODEC);

    public static void register() {
    }

    /*
    private static <P extends FoliagePlacer> FoliagePlacerType<P> registerFoliagePlacerType(String name, Codec<P> codec) {
        return Registry.register(Registry.FOLIAGE_PLACER_TYPE, Treemendous.MODID + ":" + name, new FoliagePlacerType<>(codec));
    }
    */

    private static <P extends AbstractTrunkPlacer> TrunkPlacerType<P> registerTrunkPlacerType(String name, Codec<P> codec) {
        return Registry.register(Registry.TRUNK_REPLACER, Treemendous.MODID + ":" + name, new TrunkPlacerType<>(codec));
    }

    @SubscribeEvent
    public static void registerFoliagePlacers(RegistryEvent.Register<FoliagePlacerType<?>> event) {
        ROUNDED_FOLIAGE_PLACER = new FoliagePlacerType<>(RoundedFoliagePlacer.CODEC);
        ROUNDED_FOLIAGE_PLACER.setRegistryName(new ResourceLocation(Treemendous.MODID, "rounded_foliage_placer"));
        WILLOW_FOLIAGE_PLACER = new FoliagePlacerType<>(WillowFoliagePlacer.CODEC);
        WILLOW_FOLIAGE_PLACER.setRegistryName(new ResourceLocation(Treemendous.MODID, "willow_foliage_placer"));
        PARABOLA_FOLIAGE_PLACER = new FoliagePlacerType<>(ParabolaFoliagePlacer.CODEC);
        PARABOLA_FOLIAGE_PLACER.setRegistryName(new ResourceLocation(Treemendous.MODID, "parabola_foliage_placer"));
        POINTY_FOLIAGE_PLACER = new FoliagePlacerType<>(PointyFoliagePlacer.CODEC);
        POINTY_FOLIAGE_PLACER.setRegistryName(new ResourceLocation(Treemendous.MODID, "pointy_foliage_placer"));
        BALL_FOLIAGE_PLACER = new FoliagePlacerType<>(BallFoliagePlacer.CODEC);
        BALL_FOLIAGE_PLACER.setRegistryName(new ResourceLocation(Treemendous.MODID, "ball_foliage_placer"));
        event.getRegistry().registerAll(ROUNDED_FOLIAGE_PLACER, WILLOW_FOLIAGE_PLACER, PARABOLA_FOLIAGE_PLACER,
                POINTY_FOLIAGE_PLACER, BALL_FOLIAGE_PLACER);
    }

}
