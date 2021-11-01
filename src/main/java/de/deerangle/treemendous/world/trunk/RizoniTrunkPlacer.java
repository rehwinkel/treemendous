package de.deerangle.treemendous.world.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.deerangle.treemendous.tree.TreeFeatureRegistry;
import net.minecraft.core.BlockPos;
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

public class RizoniTrunkPlacer extends TrunkPlacer
{

    public static final Codec<RizoniTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> trunkPlacerParts(instance)
            .and(IntProvider.codec(0, 8).fieldOf("leaves_offset").forGetter(inst -> inst.leavesOffset))
            .and(IntProvider.codec(0, 8).fieldOf("min_leaf_height").forGetter(inst -> inst.bottomOffset))
            .and(IntProvider.codec(0, 8).fieldOf("distance_between_foliage").forGetter(inst -> inst.distanceBetweenFoliage))
            .and(Codec.FLOAT.fieldOf("middle_foliage_factor").forGetter(inst -> inst.middleFoliageFactor))
            .apply(instance, RizoniTrunkPlacer::new));

    private final IntProvider leavesOffset;
    private final IntProvider bottomOffset;
    private final IntProvider distanceBetweenFoliage;
    private final float middleFoliageFactor;

    public RizoniTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, IntProvider leavesOffset, IntProvider bottomOffset, IntProvider distanceBetweenFoliage, float middleFoliageFactor)
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

        for (int i = 0; i < height; ++i)
        {
            placeLog(reader, blockConsumer, random, startPos.above(i), config);
        }

        List<FoliagePlacer.FoliageAttachment> foliages = new ArrayList<>();
        int bottomOffset = this.bottomOffset.sample(random);
        int crownHeight = height - 1 - bottomOffset;
        float distance = this.distanceBetweenFoliage.sample(random);
        float blobCountFloat = (float) crownHeight / distance + 1.0f;
        int blobCount = Math.round(blobCountFloat);
        for (int i = 0; i < blobCount; i++)
        {
            float y = height - distance * i - 1.0f;
            float pos = 1.0f - Math.abs(i / (blobCount - 1.0f) * 2.0f - 1.0f);
            int foliageExtra = Math.round(pos * this.middleFoliageFactor);
            foliages.add(new FoliagePlacer.FoliageAttachment(startPos.above(Math.round(y)), foliageExtra, false));
        }
        /*
        return IntStream.range(0, foliages.size()).mapToObj(i -> {
            FoliagePlacer.FoliageAttachment foliage = foliages.get(i);
            double pos = 1.0 - Math.abs(i / (foliages.size() - 1.0) * 2.0 - 1.0);
            return new FoliagePlacer.FoliageAttachment(foliage.pos(), (int) (pos * middleFoliageFactor), foliage.doubleTrunk());
        }).collect(Collectors.toList());
        */
        return foliages;
    }

}
