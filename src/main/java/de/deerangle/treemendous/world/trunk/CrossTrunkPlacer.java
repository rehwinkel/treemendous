package de.deerangle.treemendous.world.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.deerangle.treemendous.tree.TreeFeatureRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class CrossTrunkPlacer extends TrunkPlacer
{
    public static final Codec<CrossTrunkPlacer> CODEC = RecordCodecBuilder
            .create((instance) -> trunkPlacerParts(instance)
                    .and(IntProvider.codec(0, 8).fieldOf("branch")
                            .forGetter((placerInstance2) -> placerInstance2.branchLength))
                    .and(IntProvider.codec(0, 8).fieldOf("branch_offset")
                            .forGetter((placerInstance2) -> placerInstance2.branchTopOffset))
                    .and(IntProvider.codec(-6, 5).fieldOf("crown_offset")
                            .forGetter((placerInstance2) -> placerInstance2.crownOffset))
                    .and(Codec.BOOL.fieldOf("leaves_at_end")
                            .forGetter((placerInstance2) -> placerInstance2.leavesAtEnd))
                    .apply(instance, CrossTrunkPlacer::new));

    private final IntProvider branchLength;
    private final IntProvider branchTopOffset;
    private final IntProvider crownOffset;
    private final boolean leavesAtEnd;

    public CrossTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, IntProvider branchLength, IntProvider branchTopOffset, IntProvider crownOffset, boolean leavesAtEnd)
    {
        super(baseHeight, heightRandA, heightRandB);
        this.branchLength = branchLength;
        this.branchTopOffset = branchTopOffset;
        this.crownOffset = crownOffset;
        this.leavesAtEnd = leavesAtEnd;
    }

    @Override
    protected TrunkPlacerType<?> type()
    {
        return TreeFeatureRegistry.CROSS_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> blockConsumer, Random random, int height, BlockPos startPos, TreeConfiguration config)
    {
        setDirtAt(reader, blockConsumer, random, startPos.below(), config);
        List<FoliagePlacer.FoliageAttachment> foliages = new ArrayList<>();

        int crownHeightOffset = crownOffset.sample(random);

        int[] branchOffsets = new int[]{
                this.branchTopOffset.sample(random),
                this.branchTopOffset.sample(random),
                this.branchTopOffset.sample(random),
                this.branchTopOffset.sample(random)
        };
        for (int i = 0; i < height; ++i)
        {
            placeLog(reader, blockConsumer, random, startPos.above(i), config);
            for (Direction dir : Direction.Plane.HORIZONTAL)
            {
                if (i + 1 + branchOffsets[dir.get2DDataValue()] == height)
                {
                    int thisBranchLen = this.branchLength.sample(random);
                    for (int branch = 1; branch <= thisBranchLen; branch++)
                    {
                        BlockPos p = startPos.above(i).relative(dir, branch);
                        placeLog(reader, blockConsumer, random, p, config, state -> state.setValue(RotatedPillarBlock.AXIS, dir.getAxis()));
                        if (branch == thisBranchLen && this.leavesAtEnd)
                        {
                            foliages.add(new FoliagePlacer.FoliageAttachment(p.above(crownHeightOffset), 0, false));
                        }
                    }
                }
            }
        }

        foliages.add(new FoliagePlacer.FoliageAttachment(startPos.above(height + crownHeightOffset - 1), 0, false));
        return foliages;
    }

}
