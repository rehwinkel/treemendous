package de.deerangle.treemendous.world.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.deerangle.treemendous.tree.TreeFeatureRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.Random;
import java.util.function.BiConsumer;

public class PointyFoliagePlacer extends FoliagePlacer
{
    public static final Codec<PointyFoliagePlacer> CODEC = RecordCodecBuilder
            .create((instance) -> foliagePlacerParts(instance)
                    .and(IntProvider.codec(0, 100).fieldOf("outward_speed")
                            .forGetter((inst) -> inst.outwardSpeed))
                    .and(IntProvider.codec(0, 100).fieldOf("inward_speed")
                            .forGetter((inst) -> inst.inwardSpeed))
                    .and(IntProvider.codec(0, 16).fieldOf("target_height")
                            .forGetter((inst) -> inst.bottomOffset)).apply(instance, PointyFoliagePlacer::new));

    private final IntProvider outwardSpeed;
    private final IntProvider inwardSpeed;
    private final IntProvider bottomOffset;

    private float currentWidth;

    public PointyFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider outwardSpeed, IntProvider inwardSpeed, IntProvider bottomOffset)
    {
        super(radius, offset);
        this.outwardSpeed = outwardSpeed;
        this.inwardSpeed = inwardSpeed;
        this.bottomOffset = bottomOffset;
    }

    @Override
    protected FoliagePlacerType<?> type()
    {
        return TreeFeatureRegistry.POINTY_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> blockConsumer, Random random, TreeConfiguration config, int trunkHeight, FoliageAttachment foliage, int height, int radius, int offset)
    {
        float m = this.inwardSpeed.sample(random) * 0.01f;
        float n = this.outwardSpeed.sample(random) * 0.01f;
        float h = trunkHeight - this.bottomOffset.sample(random) + offset;
        float targetHeightN = m / (n + m) * h;
        float targetWidthAct = n * targetHeightN;

        int y = -offset;
        currentWidth = 0.0f;
        for (; currentWidth <= targetWidthAct; y++)
        {
            currentWidth += n;
            this.placeLeavesRow(reader, blockConsumer, random, config, foliage.pos(), (int) currentWidth + 1, -y, foliage.doubleTrunk());
        }
        for (; currentWidth > 0; y++)
        {
            currentWidth -= m;
            this.placeLeavesRow(reader, blockConsumer, random, config, foliage.pos(), (int) currentWidth + 1, -y, foliage.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(Random p_68568_, int p_68569_, TreeConfiguration p_68570_)
    {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(Random rand, int x, int y, int z, int radius, boolean p_230373_6_)
    {
        double distance = Math.sqrt(x * x + z * z) - currentWidth;
        return distance > 0 || (!(distance / currentWidth < -0.15) && rand.nextFloat() < 0.3f);
    }

}
