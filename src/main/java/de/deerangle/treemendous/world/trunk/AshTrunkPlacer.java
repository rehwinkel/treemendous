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

public class AshTrunkPlacer extends TrunkPlacer
{

    public static final Codec<AshTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> trunkPlacerParts(instance)
            .and(IntProvider.codec(0, 8).fieldOf("leaves_offset").forGetter(inst -> inst.leavesOffset))
            .and(IntProvider.codec(0, 8).fieldOf("min_leaf_height").forGetter(inst -> inst.minLeafHeight))
            .apply(instance, AshTrunkPlacer::new));

    private final IntProvider leavesOffset;
    private final IntProvider minLeafHeight;

    public AshTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, IntProvider leavesOffset, IntProvider minLeafHeight)
    {
        super(baseHeight, heightRandA, heightRandB);
        this.leavesOffset = leavesOffset;
        this.minLeafHeight = minLeafHeight;
    }

    @Override
    protected TrunkPlacerType<?> type()
    {
        return TreeFeatureRegistry.ASH_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> blockConsumer, Random random, int height, BlockPos startPos, TreeConfiguration config)
    {
        setDirtAt(reader, blockConsumer, random, startPos.below(), config);

        List<FoliagePlacer.FoliageAttachment> foliages = new ArrayList<>();
        int leafHeight = minLeafHeight.sample(random);
        int last = 0;
        for (int i = 0; i < height; ++i)
        {
            if (i >= leafHeight && i - last > 3)
            {
                BlockPos pos = startPos.above(i - 1).relative(Direction.Plane.HORIZONTAL.getRandomDirection(random), leavesOffset.sample(random));
                foliages.add(new FoliagePlacer.FoliageAttachment(pos, 0, false));
                last = i;
            }
            placeLog(reader, blockConsumer, random, startPos.above(i), config);
        }

        foliages.add(new FoliagePlacer.FoliageAttachment(startPos.above(height - 1), 0, false));
        return foliages;
    }

}
