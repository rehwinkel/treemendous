package de.deerangle.treemendous.world.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.deerangle.treemendous.tree.TreeFeatureRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FingerTrunkPlacer extends TrunkPlacer
{

    public static final Codec<FingerTrunkPlacer> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.fieldOf("base_height").forGetter(inst -> inst.baseHeight),
                    Codec.INT.fieldOf("height_rand").forGetter(inst -> inst.heightRandA),
                    IntProvider.codec(0, 8).fieldOf("top_offset").forGetter(inst -> inst.topOffset),
                    IntProvider.codec(0, 8).fieldOf("bottom_offset").forGetter(inst -> inst.bottomOffset),
                    IntProvider.codec(0, 8).fieldOf("branch_length").forGetter(inst -> inst.branchLength),
                    Codec.DOUBLE.fieldOf("branch_chance").forGetter(inst -> inst.branchChance),
                    Codec.DOUBLE.fieldOf("extra_branch_chance").forGetter(inst -> inst.extraBranchChance),
                    Codec.DOUBLE.fieldOf("foliage_at_stem_chance").forGetter(inst -> inst.foliageAtStemChance),
                    Codec.DOUBLE.fieldOf("branch_y_angle").forGetter(inst -> inst.branchAngleY),
                    Codec.DOUBLE.fieldOf("branch_extra_angle_deg").forGetter(inst -> inst.extraDegrees)
            ).apply(instance, FingerTrunkPlacer::new));

    private final IntProvider topOffset;
    private final IntProvider bottomOffset;
    private final IntProvider branchLength;
    private final double branchChance;
    private final double extraBranchChance;
    private final double foliageAtStemChance;
    private final double branchAngleY;
    private final double extraDegrees;

    public FingerTrunkPlacer(int baseHeight, int heightRand, IntProvider topOffset, IntProvider bottomOffset, IntProvider branchLength, double branchChance, double extraBranchChance, double foliageAtStemChance, double branchAngleY, double extraDegrees)
    {
        super(baseHeight, heightRand, 0);
        this.topOffset = topOffset;
        this.bottomOffset = bottomOffset;
        this.branchLength = branchLength;
        this.branchChance = branchChance;
        this.extraBranchChance = extraBranchChance;
        this.foliageAtStemChance = foliageAtStemChance;
        this.branchAngleY = branchAngleY;
        this.extraDegrees = extraDegrees;
    }

    @Override
    protected TrunkPlacerType<?> type()
    {
        return TreeFeatureRegistry.FINGER_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> blockConsumer, Random random, int height, BlockPos startPos, TreeConfiguration config)
    {
        setDirtAt(reader, blockConsumer, random, startPos.below(), config);

        for (int i = 0; i < height; ++i)
        {
            placeLog(reader, blockConsumer, random, startPos.above(i), config);
        }
        int topOffset = this.topOffset.sample(random);
        int bottomOffset = this.bottomOffset.sample(random);
        int fingers = height - bottomOffset - topOffset;
        double fingerAngle = Math.PI * 2 / fingers;
        List<Integer> ints = IntStream.range(0, fingers).boxed().collect(Collectors.toList());
        Collections.shuffle(ints);
        List<FoliagePlacer.FoliageAttachment> foliages = new ArrayList<>();
        foliages.add(new FoliagePlacer.FoliageAttachment(startPos.above(height), 0, false));
        for (int i = 0; i < fingers; i++)
        {
            int branches = random.nextDouble() < this.branchChance ? 1 : (random.nextDouble() < this.extraBranchChance ? 1 : 0);
            for (int b = 0; b < branches; b++)
            {
                double angle = fingerAngle * ints.get(i) + b * Math.PI + (random.nextDouble() * 2.0 - 1.0) * Math.toRadians(this.extraDegrees);
                Vec3 v = new Vec3(startPos.getX() + 0.5, startPos.getY() + bottomOffset + i, startPos.getZ() + 0.5);
                Vec3 dir = new Vec3(Math.sin(angle), this.branchAngleY, Math.cos(angle)).normalize();
                BlockPos stemPos = new BlockPos(v);
                BlockPos branchPos = new BlockPos(v.add(dir.scale(this.branchLength.sample(random))));
                makeLimb(reader, blockConsumer, random, stemPos, branchPos, config);
                if (random.nextDouble() < this.foliageAtStemChance)
                {
                    foliages.add(new FoliagePlacer.FoliageAttachment(stemPos, 0, false));
                }
                foliages.add(new FoliagePlacer.FoliageAttachment(branchPos, 0, false));
            }
        }

        return foliages;
    }

    private void makeLimb(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> blockConsumer, Random random, BlockPos startPos, BlockPos endPos, TreeConfiguration config)
    {
        BlockPos blockpos = endPos.offset(-startPos.getX(), -startPos.getY(), -startPos.getZ());
        int i = this.getSteps(blockpos);
        float f = (float) blockpos.getX() / (float) i;
        float f1 = (float) blockpos.getY() / (float) i;
        float f2 = (float) blockpos.getZ() / (float) i;

        for (int j = 0; j <= i; ++j)
        {
            BlockPos blockpos1 = startPos.offset((0.5F + (float) j * f), (0.5F + (float) j * f1), (0.5F + (float) j * f2));
            TrunkPlacer.placeLog(reader, blockConsumer, random, blockpos1, config, (state) -> state.setValue(RotatedPillarBlock.AXIS, this.getLogAxis(startPos, blockpos1)));
        }
    }

    private Direction.Axis getLogAxis(BlockPos startPos, BlockPos endPos)
    {
        Direction.Axis axis = Direction.Axis.Y;
        int i = Math.abs(endPos.getX() - startPos.getX());
        int j = Math.abs(endPos.getZ() - startPos.getZ());
        int k = Math.max(i, j);
        if (k > 0)
        {
            if (i == k)
            {
                axis = Direction.Axis.X;
            } else
            {
                axis = Direction.Axis.Z;
            }
        }

        return axis;
    }

    private int getSteps(BlockPos p_70128_)
    {
        int i = Mth.abs(p_70128_.getX());
        int j = Mth.abs(p_70128_.getY());
        int k = Mth.abs(p_70128_.getZ());
        return Math.max(i, Math.max(j, k));
    }

}
