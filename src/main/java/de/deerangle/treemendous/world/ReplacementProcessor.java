package de.deerangle.treemendous.world;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Keyable;
import de.deerangle.treemendous.main.ExtraRegistry;
import de.deerangle.treemendous.tree.Tree;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class ReplacementProcessor extends StructureProcessor {

    public static final Codec<ReplacementProcessor> CODEC = Codec.simpleMap(blockStringCodec(), blockStringCodec(), new ForgeBlocksKeyable()).fieldOf("replacement_map").xmap(ReplacementProcessor::new, (instance) -> instance.blockReplacementMap).codec();
    private final Map<Block, Block> blockReplacementMap;

    public ReplacementProcessor(Map<Block, Block> blockReplacementMap) {
        this.blockReplacementMap = blockReplacementMap;
    }

    public static ReplacementProcessor rangerHouseReplacer(Tree tree, Block flowerOne, Block flowerTwo) {
        HashMap<Block, Block> map = new HashMap<>();
        map.put(Blocks.OAK_BUTTON, tree.getButton());
        map.put(Blocks.OAK_DOOR, tree.getDoor());
        map.put(Blocks.OAK_FENCE, tree.getFence());
        map.put(Blocks.OAK_FENCE_GATE, tree.getFenceGate());
        map.put(Blocks.OAK_SAPLING, tree.getDefaultSapling());
        map.put(Blocks.OAK_LOG, tree.getLog());
        map.put(Blocks.STRIPPED_OAK_LOG, tree.getStrippedLog());
        map.put(Blocks.OAK_WOOD, tree.getWood());
        map.put(Blocks.STRIPPED_OAK_WOOD, tree.getStrippedWood());
        map.put(Blocks.OAK_PRESSURE_PLATE, tree.getPressurePlate());
        map.put(Blocks.OAK_SLAB, tree.getSlab());
        map.put(Blocks.OAK_STAIRS, tree.getStairs());
        map.put(Blocks.OAK_TRAPDOOR, tree.getTrapdoor());
        map.put(Blocks.OAK_WALL_SIGN, tree.getWallSign());
        map.put(Blocks.OAK_PLANKS, tree.getPlanks());
        map.put(Blocks.CHEST, tree.getChest());
        map.put(Blocks.CRAFTING_TABLE, tree.getCraftingTable());
        map.put(Blocks.CORNFLOWER, flowerOne);
        map.put(Blocks.OXEYE_DAISY, flowerTwo);
        return new ReplacementProcessor(ImmutableMap.copyOf(map));
    }

    private static Codec<Block> blockStringCodec() {
        return Codec.STRING.xmap(name -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation(name)), block -> Objects.requireNonNull(block.getRegistryName()).toString());
    }

    public static StructureProcessor fromNBT(CompoundTag tag) {
        CompoundTag blockMapNbt = tag.getCompound("BlockMap");
        HashMap<Block, Block> map = new HashMap<>();
        for (String key : blockMapNbt.getAllKeys()) {
            String value = blockMapNbt.getString(key);
            Block from = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(key));
            Block to = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(value));
            map.put(from, to);
        }
        return new ReplacementProcessor(ImmutableMap.copyOf(map));
    }

    public CompoundTag toNBT() {
        CompoundTag blockMap = new CompoundTag();
        for (Block key : this.blockReplacementMap.keySet()) {
            Block value = this.blockReplacementMap.get(key);
            blockMap.putString(Objects.requireNonNull(key.getRegistryName()).toString(), Objects.requireNonNull(value.getRegistryName()).toString());
        }
        CompoundTag result = new CompoundTag();
        result.put("BlockMap", blockMap);
        return result;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected StructureProcessorType<?> getType() {
        return ExtraRegistry.REPLACEMENT_PROCESSOR;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public StructureTemplate.StructureBlockInfo process(LevelReader world, BlockPos pos1, BlockPos pos2, StructureTemplate.StructureBlockInfo blockInfo1, StructureTemplate.StructureBlockInfo blockInfo2, StructurePlaceSettings settings, @Nullable StructureTemplate template) {
        Block replacementBlock = this.blockReplacementMap.get(blockInfo2.state.getBlock());
        if (replacementBlock == null) {
            return blockInfo2;
        } else {
            BlockState state = blockInfo2.state;
            BlockState replacementState = replacementBlock.defaultBlockState();

            for (Property<?> prop : state.getProperties()) {
                replacementState = copyProp(state, replacementState, prop);
            }

            return new StructureTemplate.StructureBlockInfo(blockInfo2.pos, replacementState, blockInfo2.nbt);
        }
    }

    private <T extends Comparable<T>> BlockState copyProp(BlockState source, BlockState dest, Property<T> prop) {
        T value = source.getValue(prop);
        return dest.setValue(prop, value);
    }

    private static class ForgeBlocksKeyable implements Keyable {
        @Override
        public <T> Stream<T> keys(DynamicOps<T> ops) {
            return ForgeRegistries.BLOCKS.getKeys().stream().map((resourceLocation) -> ops.createString(resourceLocation.toString()));
        }
    }
}
