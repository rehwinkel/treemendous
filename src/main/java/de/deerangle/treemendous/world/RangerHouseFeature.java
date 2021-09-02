package de.deerangle.treemendous.world;

import com.mojang.serialization.Codec;
import de.deerangle.treemendous.main.Treemendous;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

public class RangerHouseFeature extends StructureFeature<RangerHouseConfiguration> {

    public RangerHouseFeature(Codec<RangerHouseConfiguration> codec) {
        super(codec);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public StructureStartFactory<RangerHouseConfiguration> getStartFactory() {
        return RangerHouseFeature.FeatureStart::new;
    }

    public static class FeatureStart extends StructureStart<RangerHouseConfiguration> {

        static final ResourceLocation STRUCTURE_LOCATION = new ResourceLocation(Treemendous.MODID, "ranger_house");

        public FeatureStart(StructureFeature<RangerHouseConfiguration> feature, ChunkPos chunkPos, int references, long seed) {
            super(feature, chunkPos, references, seed);
        }

        @SuppressWarnings("NullableProblems")
        @Override
        public void generatePieces(RegistryAccess registryAccess, ChunkGenerator generator, StructureManager structureManager, ChunkPos chunkPos, Biome biome, RangerHouseConfiguration config, LevelHeightAccessor heightAccessor) {
            int height = generator.getBaseHeight(chunkPos.getMinBlockX(), chunkPos.getMinBlockZ(), Heightmap.Types.WORLD_SURFACE_WG, heightAccessor);
            BlockPos blockpos = new BlockPos(chunkPos.getMinBlockX(), height, chunkPos.getMinBlockZ());
            Rotation rotation = Rotation.getRandom(this.random);
            RangerHouseStructurePiece piece = new RangerHouseStructurePiece(structureManager, STRUCTURE_LOCATION, blockpos, rotation, config.getTree(), BlockTags.SMALL_FLOWERS.getRandomElement(random), BlockTags.SMALL_FLOWERS.getRandomElement(random));
            this.addPiece(piece);
        }

    }

}
