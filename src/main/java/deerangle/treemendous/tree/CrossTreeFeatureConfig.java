package deerangle.treemendous.tree;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import deerangle.treemendous.util.FeatureSpread;
import net.minecraft.block.Block;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;

public class CrossTreeFeatureConfig extends TreeFeatureConfig {

    public final FeatureSpread branchLength;
    public final FeatureSpread branchTopOffset;
    public final FeatureSpread crownOffset;
    public final boolean leavesAtEnd;

    protected CrossTreeFeatureConfig(Block trunkProviderIn, Block leavesProviderIn, FoliagePlacer foliagePlacerIn, FeatureSpread height, FeatureSpread crownHeight, FeatureSpread branchLength, FeatureSpread branchTopOffset, FeatureSpread crownOffset, boolean leavesAtEnd) {
        this(trunkProviderIn, leavesProviderIn, foliagePlacerIn, height, crownHeight, FeatureSpread.create(0),
                FeatureSpread.create(0), branchLength, branchTopOffset, crownOffset, leavesAtEnd);
    }

    protected CrossTreeFeatureConfig(TreeFeatureConfig parent, FeatureSpread branchLength, FeatureSpread branchTopOffset, FeatureSpread crownOffset, boolean leavesAtEnd) {
        this(parent.trunkProvider, parent.leavesProvider, parent.foliagePlacer, FeatureSpread.create(0),
                FeatureSpread.create(0), FeatureSpread.create(0), FeatureSpread.create(0), branchLength,
                branchTopOffset, crownOffset, leavesAtEnd);
    }

    protected CrossTreeFeatureConfig(Block trunkProviderIn, Block leavesProviderIn, FoliagePlacer foliagePlacerIn, FeatureSpread height, FeatureSpread trunk, FeatureSpread topOffset, FeatureSpread foliage, FeatureSpread branchLength, FeatureSpread branchTopOffset, FeatureSpread crownOffset, boolean leavesAtEnd) {
        this(new SimpleBlockStateProvider(trunkProviderIn.getDefaultState()),
                new SimpleBlockStateProvider(leavesProviderIn.getDefaultState()), foliagePlacerIn, height, trunk,
                topOffset, foliage, branchLength, branchTopOffset, crownOffset, leavesAtEnd);
    }

    protected CrossTreeFeatureConfig(BlockStateProvider trunkProviderIn, BlockStateProvider leavesProviderIn, FoliagePlacer foliagePlacerIn, FeatureSpread height, FeatureSpread trunk, FeatureSpread topOffset, FeatureSpread foliage, FeatureSpread branchLength, FeatureSpread branchTopOffset, FeatureSpread crownOffset, boolean leavesAtEnd) {
        super(trunkProviderIn, leavesProviderIn, foliagePlacerIn, ImmutableList.of(), height.getBase(),
                height.getVariance(), 0, trunk.getBase(), trunk.getVariance(), topOffset.getBase(),
                topOffset.getVariance(), foliage.getBase(), foliage.getVariance(), 0, false);
        this.branchLength = branchLength;
        this.branchTopOffset = branchTopOffset;
        this.crownOffset = crownOffset;
        this.leavesAtEnd = leavesAtEnd;
    }

    public static <T> CrossTreeFeatureConfig deserializeCrossTree(Dynamic<T> data) {
        TreeFeatureConfig parentConfig = deserializeFoliage(data);
        return new CrossTreeFeatureConfig(parentConfig, FeatureSpread.fromDynamic(data, "branch_length"),
                FeatureSpread.fromDynamic(data, "branch_top_offset"), FeatureSpread.fromDynamic(data, "crown_offset"),
                data.get("leaves_at_end").asBoolean(false));
    }

    @Override
    public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
        ImmutableMap.Builder<T, T> builder = ImmutableMap.builder();
        builder.put(ops.createString("foliage_placer"), this.foliagePlacer.serialize(ops))
                .put(ops.createString("height_rand_a"), ops.createInt(this.heightRandA))
                .put(ops.createString("height_rand_b"), ops.createInt(this.heightRandB))
                .put(ops.createString("trunk_height"), ops.createInt(this.trunkHeight))
                .put(ops.createString("trunk_height_random"), ops.createInt(this.trunkHeightRandom))
                .put(ops.createString("trunk_top_offset"), ops.createInt(this.trunkTopOffset))
                .put(ops.createString("trunk_top_offset_random"), ops.createInt(this.trunkTopOffsetRandom))
                .put(ops.createString("foliage_height"), ops.createInt(this.foliageHeight))
                .put(ops.createString("foliage_height_random"), ops.createInt(this.foliageHeightRandom))
                .put(ops.createString("max_water_depth"), ops.createInt(this.maxWaterDepth))
                .put(ops.createString("ignore_vines"), ops.createBoolean(this.ignoreVines))
                .put(ops.createString("branch_length"), ops.createInt(this.branchLength.getBase()))
                .put(ops.createString("branch_length_random"), ops.createInt(this.branchLength.getVariance()))
                .put(ops.createString("branch_top_offset"), ops.createInt(this.branchTopOffset.getBase()))
                .put(ops.createString("branch_top_offset_random"), ops.createInt(this.branchTopOffset.getVariance()))
                .put(ops.createString("crown_offset"), ops.createInt(this.crownOffset.getBase()))
                .put(ops.createString("crown_offset_random"), ops.createInt(this.crownOffset.getVariance()))
                .put(ops.createString("leaves_at_end"), ops.createBoolean(this.leavesAtEnd));
        Dynamic<T> dynamic = new Dynamic<>(ops, ops.createMap(builder.build()));
        return dynamic.merge(super.serialize(ops));
    }
}
