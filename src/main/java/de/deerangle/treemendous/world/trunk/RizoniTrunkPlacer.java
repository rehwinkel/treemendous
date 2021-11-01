package de.deerangle.treemendous.world.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.deerangle.treemendous.tree.TreeFeatureRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RizoniTrunkPlacer extends TrunkPlacer
{

    public static final Codec<RizoniTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> trunkPlacerParts(instance)
            .and(IntProvider.codec(0, 8).fieldOf("leaves_offset").forGetter(inst -> inst.leavesOffset))
            .and(IntProvider.codec(0, 8).fieldOf("min_leaf_height").forGetter(inst -> inst.bottomOffset))
            .and(IntProvider.codec(0, 8).fieldOf("distance_between_foliage").forGetter(inst -> inst.distanceBetweenFoliage))
            .and(Codec.DOUBLE.fieldOf("middle_foliage_factor").forGetter(inst -> inst.middleFoliageFactor))
            .apply(instance, RizoniTrunkPlacer::new));

    private final IntProvider leavesOffset;
    private final IntProvider bottomOffset;
    private final IntProvider distanceBetweenFoliage;
    private final double middleFoliageFactor;

    public RizoniTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, IntProvider leavesOffset, IntProvider bottomOffset, IntProvider distanceBetweenFoliage, double middleFoliageFactor)
    {
        super(baseHeight, heightRandA, heightRandB);
        this.leavesOffset = leavesOffset;
        this.bottomOffset = bottomOffset;
        this.distanceBetweenFoliage = distanceBetweenFoliage;
        this.middleFoliageFactor = middleFoliageFactor;
    }

    @Override
    protected TrunkPlacerType<?> type()
    {
        return TreeFeatureRegistry.RIZONI_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> blockConsumer, Random random, int height, BlockPos startPos, TreeConfiguration config)
    {
        setDirtAt(reader, blockConsumer, random, startPos.below(), config);

        List<FoliagePlacer.FoliageAttachment> foliages = new ArrayList<>();
        int bottomOffset = this.bottomOffset.sample(random);
        int last = 0;
        for (int i = 0; i < height; ++i)
        {
            if (i >= bottomOffset && i - last > distanceBetweenFoliage.sample(random))
            {
                BlockPos pos = startPos.above(i - 1).relative(Direction.Plane.HORIZONTAL.getRandomDirection(random), leavesOffset.sample(random));
                foliages.add(new FoliagePlacer.FoliageAttachment(pos, 0, false));
                last = i;
            }
            placeLog(reader, blockConsumer, random, startPos.above(i), config);
        }

        foliages.add(new FoliagePlacer.FoliageAttachment(startPos.above(height - 1), 0, false));
        return IntStream.range(0, foliages.size()).mapToObj(i -> {
            FoliagePlacer.FoliageAttachment foliage = foliages.get(i);
            double pos = 1.0 - Math.abs(i / (foliages.size() - 1.0) * 2.0 - 1.0);
            return new FoliagePlacer.FoliageAttachment(foliage.pos(), (int) (pos * middleFoliageFactor), foliage.doubleTrunk());
        }).collect(Collectors.toList());
    }

}
