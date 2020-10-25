package deerangle.treemendous.tree.trunk;

public class AshTrunkPlacer {
    /*
    public static final Codec<AshTrunkPlacer> CODEC = RecordCodecBuilder
            .create((placerInstance) -> func_236915_a_(placerInstance)
                    .and(FeatureSpread.func_242254_a(0, 8, 8).fieldOf("offset").forGetter((inst) -> inst.leavesOffset))
                    .and(FeatureSpread.func_242254_a(0, 8, 8).fieldOf("min_height")
                            .forGetter((inst) -> inst.minLeafHeight)).apply(placerInstance, AshTrunkPlacer::new));
    private final FeatureSpread leavesOffset;
    private final FeatureSpread minLeafHeight;

    public AshTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, FeatureSpread leavesOffset, FeatureSpread minLeafHeight) {
        super(baseHeight, heightRandA, heightRandB);
        this.leavesOffset = leavesOffset;
        this.minLeafHeight = minLeafHeight;
    }

    @Override
    protected TrunkPlacerType<?> func_230381_a_() {
        return TreeWorldGenRegistry.ASH_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.Foliage> func_230382_a_(IWorldGenerationReader generationReader, Random random, int height, BlockPos startPos, Set<BlockPos> resultingBlocks, MutableBoundingBox boundingBox, BaseTreeFeatureConfig featureConfig) {
        func_236909_a_(generationReader, startPos.down());

        List<FoliagePlacer.Foliage> foliages = new ArrayList<>();
        int leafHeight = minLeafHeight.func_242259_a(random);
        int last = 0;
        for (int i = 0; i < height; ++i) {
            if (i >= leafHeight && i - last > 3) {
                BlockPos pos = startPos.up(i - 1)
                        .offset(Direction.Plane.HORIZONTAL.random(random), leavesOffset.func_242259_a(random));
                foliages.add(new FoliagePlacer.Foliage(pos, 0, false));
                last = i;
            }
            func_236911_a_(generationReader, random, startPos.up(i), resultingBlocks, boundingBox, featureConfig);
        }

        foliages.add(new FoliagePlacer.Foliage(startPos.up(height - 1), 0, false));
        return foliages;
    }
    */
}
