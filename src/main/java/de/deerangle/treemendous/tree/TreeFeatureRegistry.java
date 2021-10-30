package de.deerangle.treemendous.tree;

import com.mojang.serialization.Codec;
import de.deerangle.treemendous.main.Treemendous;
import de.deerangle.treemendous.world.foliage.BallFoliagePlacer;
import de.deerangle.treemendous.world.foliage.ParabolicFoliagePlacer;
import de.deerangle.treemendous.world.foliage.PointyFoliagePlacer;
import de.deerangle.treemendous.world.foliage.RoundedFoliagePlacer;
import de.deerangle.treemendous.world.foliage.WillowFoliagePlacer;
import de.deerangle.treemendous.world.trunk.AshTrunkPlacer;
import de.deerangle.treemendous.world.trunk.CrossTrunkPlacer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TreeFeatureRegistry
{

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, Treemendous.MODID);
    // TODO: public static final DeferredRegister<FoliagePlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, Treemendous.MODID);

    public static final RegistryObject<FoliagePlacerType<BallFoliagePlacer>> BALL_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("ball_foliage_placer", () -> new FoliagePlacerType<>(BallFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<ParabolicFoliagePlacer>> PARABOLIC_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("parabolic_foliage_placer", () -> new FoliagePlacerType<>(ParabolicFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<RoundedFoliagePlacer>> ROUNDED_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("rounded_foliage_placer", () -> new FoliagePlacerType<>(RoundedFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<WillowFoliagePlacer>> WILLOW_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("willow_foliage_placer", () -> new FoliagePlacerType<>(WillowFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<PointyFoliagePlacer>> POINTY_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("pointy_foliage_placer", () -> new FoliagePlacerType<>(PointyFoliagePlacer.CODEC));

    public static final TrunkPlacerType<AshTrunkPlacer> ASH_TRUNK_PLACER = registerTrunkPlacer("ash_trunk_placer", AshTrunkPlacer.CODEC);
    public static final TrunkPlacerType<CrossTrunkPlacer> CROSS_TRUNK_PLACER = registerTrunkPlacer("cross_trunk_placer", CrossTrunkPlacer.CODEC);

    private static <P extends TrunkPlacer> TrunkPlacerType<P> registerTrunkPlacer(String name, Codec<P> codec)
    {
        return Registry.register(Registry.TRUNK_PLACER_TYPES, new ResourceLocation(Treemendous.MODID, name), new TrunkPlacerType<>(codec));
    }

}
