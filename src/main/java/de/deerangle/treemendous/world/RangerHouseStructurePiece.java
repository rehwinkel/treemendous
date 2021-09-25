package de.deerangle.treemendous.world;

import de.deerangle.treemendous.data.TreemendousChestLoot;
import de.deerangle.treemendous.main.ExtraRegistry;
import de.deerangle.treemendous.tree.RegisteredTree;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.properties.StructureMode;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import java.util.Random;

public class RangerHouseStructurePiece extends StructurePiece {

    protected final ResourceLocation templateLocation;
    protected StructureTemplate template;
    protected StructurePlaceSettings placeSettings;
    protected BlockPos templatePosition;

    public RangerHouseStructurePiece(StructureManager structureManager, ResourceLocation template, BlockPos position, Rotation rotation, RegisteredTree tree, Block flowerOne, Block flowerTwo) {
        super(ExtraRegistry.RANGER_HOUSE_PIECE_TYPE, 0, structureManager.getOrCreate(template).getBoundingBox(makeSettings(rotation), position));
        this.setOrientation(Direction.NORTH);
        this.templateLocation = template;
        this.templatePosition = position;
        this.template = structureManager.getOrCreate(template);
        StructureProcessor replacer = ReplacementProcessor.rangerHouseReplacer(tree.getTree(), flowerOne, flowerTwo);
        this.placeSettings = makeSettings(rotation, replacer);
    }

    public RangerHouseStructurePiece(ServerLevel world, CompoundTag tag) {
        super(ExtraRegistry.RANGER_HOUSE_PIECE_TYPE, tag);
        this.setOrientation(Direction.NORTH);
        this.templateLocation = new ResourceLocation(tag.getString("Template"));
        this.templatePosition = new BlockPos(tag.getInt("TPX"), tag.getInt("TPY"), tag.getInt("TPZ"));
        this.template = world.getStructureManager().getOrCreate(this.templateLocation);
        StructureProcessor replacer = ReplacementProcessor.fromNBT(tag.getCompound("WoodReplacer"));
        this.placeSettings = makeSettings(Rotation.valueOf(tag.getString("Rot")), replacer);
        this.boundingBox = this.template.getBoundingBox(this.placeSettings, this.templatePosition);
    }

    private static StructurePlaceSettings makeSettings(Rotation rotation) {
        return (new StructurePlaceSettings()).setRotation(rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK).addProcessor(new BlockRotProcessor(0.95f));
    }

    private static StructurePlaceSettings makeSettings(Rotation rotation, StructureProcessor replacer) {
        return (new StructurePlaceSettings()).setRotation(rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK).addProcessor(new BlockRotProcessor(0.95f)).addProcessor(replacer);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void addAdditionalSaveData(ServerLevel world, CompoundTag tag) {
        tag.putInt("TPX", this.templatePosition.getX());
        tag.putInt("TPY", this.templatePosition.getY());
        tag.putInt("TPZ", this.templatePosition.getZ());
        tag.putString("Template", this.templateLocation.toString());
        tag.putString("Rot", this.placeSettings.getRotation().name());
        for (StructureProcessor processor : this.placeSettings.getProcessors()) {
            if (processor instanceof ReplacementProcessor woodReplacer) {
                tag.put("WoodReplacer", woodReplacer.toNBT());
            }
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean postProcess(WorldGenLevel world, StructureFeatureManager featureManager, ChunkGenerator generator, Random rand, BoundingBox bounds, ChunkPos chunkPos, BlockPos blockPos) {
        this.placeSettings.setBoundingBox(bounds);
        this.boundingBox = this.template.getBoundingBox(this.placeSettings, this.templatePosition);
        if (this.template.placeInWorld(world, this.templatePosition.below(1), blockPos, this.placeSettings, rand, 2)) {
            for (StructureTemplate.StructureBlockInfo structureBlockInfo : this.template.filterBlocks(this.templatePosition, this.placeSettings, Blocks.STRUCTURE_BLOCK)) {
                if (structureBlockInfo.nbt != null) {
                    StructureMode structuremode = StructureMode.valueOf(structureBlockInfo.nbt.getString("mode"));
                    if (structuremode == StructureMode.DATA) {
                        this.handleDataMarker(structureBlockInfo.nbt.getString("metadata"), structureBlockInfo.pos, world, rand);
                    }
                }
            }
        }

        return true;
    }

    @Override
    public void move(int x, int y, int z) {
        super.move(x, y, z);
        this.templatePosition = this.templatePosition.offset(x, y, z);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Rotation getRotation() {
        return this.placeSettings.getRotation();
    }

    protected void handleDataMarker(String name, BlockPos blockPos, ServerLevelAccessor world, Random random) {
        if ("chest".equals(name)) {
            BlockEntity blockentity = world.getBlockEntity(blockPos.below(2));
            if (blockentity instanceof ChestBlockEntity) {
                ((ChestBlockEntity) blockentity).setLootTable(TreemendousChestLoot.RANGER_HOUSE, random.nextLong());
            }
        } else if ("monster".equals(name)) {
            ZombieVillager monster = EntityType.ZOMBIE_VILLAGER.create(world.getLevel());
            assert monster != null;
            monster.setPersistenceRequired();
            monster.setVillagerData(new VillagerData(VillagerType.PLAINS, ExtraRegistry.FOREST_RANGER_PROFESSION.get(), 1));
            monster.moveTo(blockPos, 0.0f, 0.0f);
            monster.finalizeSpawn(world, world.getCurrentDifficultyAt(monster.blockPosition()), MobSpawnType.STRUCTURE, null, null);
            world.addFreshEntityWithPassengers(monster);
        }
    }

}
