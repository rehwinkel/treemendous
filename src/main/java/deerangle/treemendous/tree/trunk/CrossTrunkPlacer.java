package deerangle.treemendous.tree.trunk;


public class CrossTrunkPlacer {
    /*
    public static final Codec<CrossTrunkPlacer> CODEC = RecordCodecBuilder
            .create((placerInstance) -> func_236915_a_(placerInstance)
                    .and(FeatureSpread.func_242254_a(0, 8, 8).fieldOf("branch")
                            .forGetter((placerInstance2) -> placerInstance2.branchLength))
                    .and(FeatureSpread.func_242254_a(0, 8, 8).fieldOf("branch_offset")
                            .forGetter((placerInstance2) -> placerInstance2.branchTopOffset))
                    .and(FeatureSpread.func_242254_a(-6, 5, 12).fieldOf("crown_offset")
                            .forGetter((placerInstance2) -> placerInstance2.crownOffset))
                    .and(Codec.intRange(0, 1).fieldOf("leaves_at_end")
                            .forGetter((placerInstance2) -> placerInstance2.leavesAtEnd ? 1 : 0))
                    .apply(placerInstance, CrossTrunkPlacer::new));

    private final FeatureSpread branchLength;
    private final FeatureSpread branchTopOffset;
    private final FeatureSpread crownOffset;
    private final boolean leavesAtEnd;

    public CrossTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, FeatureSpread branchLength, FeatureSpread branchTopOffset, FeatureSpread crownOffset, boolean leavesAtEnd) {
        super(baseHeight, heightRandA, heightRandB);
        this.branchLength = branchLength;
        this.branchTopOffset = branchTopOffset;
        this.crownOffset = crownOffset;
        this.leavesAtEnd = leavesAtEnd;
    }

    public CrossTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, FeatureSpread branchLength, FeatureSpread branchTopOffset, FeatureSpread crownOffset, int leavesAtEnd) {
        this(baseHeight, heightRandA, heightRandB, branchLength, branchTopOffset, crownOffset, leavesAtEnd > 0);
    }

    @Override
    protected TrunkPlacerType<?> func_230381_a_() {
        return TreeWorldGenRegistry.CROSS_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.Foliage> func_230382_a_(IWorldGenerationReader generationReader, Random random, int height, BlockPos pos, Set<BlockPos> resultingBlocks, MutableBoundingBox boundingBox, BaseTreeFeatureConfig featureConfig) {
        // set bottom to dirt
        func_236909_a_(generationReader, pos.down());
        List<FoliagePlacer.Foliage> foliages = new ArrayList<>();

        int crownHeightOffset = crownOffset.func_242259_a(random);

        int[] branchOffsets = new int[]{this.branchTopOffset.func_242259_a(random), this.branchTopOffset.func_242259_a(
                random), this.branchTopOffset.func_242259_a(random), this.branchTopOffset.func_242259_a(random)};
        for (int i = 0; i < height; ++i) {
            func_236911_a_(generationReader, random, pos.up(i), resultingBlocks, boundingBox, featureConfig);
            for (Direction dir : Direction.Plane.HORIZONTAL) {
                if (i + 1 + branchOffsets[dir.getHorizontalIndex()] == height) {
                    int thisBranchLen = this.branchLength.func_242259_a(random);
                    for (int branch = 1; branch <= thisBranchLen; branch++) {
                        BlockPos p = pos.up(i).offset(dir, branch);
                        func_236913_a_(generationReader, p, featureConfig.trunkProvider.getBlockState(random, p)
                                .with(RotatedPillarBlock.AXIS, dir.getAxis()), boundingBox);
                        if (branch == thisBranchLen && this.leavesAtEnd) {
                            foliages.add(new FoliagePlacer.Foliage(p.up(crownHeightOffset), 0, false));
                        }
                    }
                }
            }
        }

        foliages.add(new FoliagePlacer.Foliage(pos.up(height + crownHeightOffset - 1), 0, false));
        return foliages;
    }
    */
}
