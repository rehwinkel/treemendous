package de.deerangle.treemendous.tree;

import com.google.common.collect.ImmutableSet;
import de.deerangle.treemendous.item.CustomBoatItem;
import de.deerangle.treemendous.tree.util.ILeavesColor;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

public class FakeTree extends Tree {

    private final RotatedPillarBlock log;
    private final RotatedPillarBlock strippedLog;
    private final RotatedPillarBlock wood;
    private final RotatedPillarBlock strippedWood;
    private final SaplingBlock sapling;
    private final Supplier<ChestBlock> chest;
    private final Supplier<CraftingTableBlock> craftingTable;
    private final WoodButtonBlock button;
    private final DoorBlock door;
    private final FenceBlock fence;
    private final FenceGateBlock fenceGate;
    private final PressurePlateBlock pressurePlate;
    private final SlabBlock slab;
    private final StairBlock stairs;
    private final TrapDoorBlock trapdoor;
    private final WallSignBlock wallSign;
    private final Block planks;

    public FakeTree(Block log, Block strippedLog, Block wood, Block strippedWood, Block sapling, Supplier<ChestBlock> chest, Supplier<CraftingTableBlock> craftingTable, Block button, Block door, Block fence, Block fenceGate, Block pressurePlate, Block slab, Block stairs, Block trapdoor, Block wallSign, Block planks) {
        super(null);
        this.log = (RotatedPillarBlock) log;
        this.strippedLog = (RotatedPillarBlock) strippedLog;
        this.wood = (RotatedPillarBlock) wood;
        this.strippedWood = (RotatedPillarBlock) strippedWood;
        this.sapling = (SaplingBlock) sapling;
        this.chest = chest;
        this.craftingTable = craftingTable;
        this.button = (WoodButtonBlock) button;
        this.door = (DoorBlock) door;
        this.fence = (FenceBlock) fence;
        this.fenceGate = (FenceGateBlock) fenceGate;
        this.pressurePlate = (PressurePlateBlock) pressurePlate;
        this.slab = (SlabBlock) slab;
        this.stairs = (StairBlock) stairs;
        this.trapdoor = (TrapDoorBlock) trapdoor;
        this.wallSign = (WallSignBlock) wallSign;
        this.planks = planks;
    }

    @Override
    public SaplingBlock getRandomSapling(Random rand) {
        return this.sapling;
    }

    @Override
    public SaplingBlock getSapling(String key) {
        return this.sapling;
    }

    @Override
    public Set<String> getSaplingNames() {
        return ImmutableSet.of("dummy");
    }

    @Override
    public FlowerPotBlock getPottedSapling(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CustomBoatItem getBoatItem() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ILeavesColor getLeavesColor() {
        throw new UnsupportedOperationException();
    }

    @Override
    public LeavesBlock getLeaves() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tag.Named<Block> getLogsBlockTag() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tag.Named<Item> getLogsItemTag() {
        throw new UnsupportedOperationException();
    }

    @Override
    public SaplingBlock getDefaultSapling() {
        return this.sapling;
    }

    @Override
    public SignItem getSignItem() {
        throw new UnsupportedOperationException();
    }

    @Override
    public StandingSignBlock getSign() {
        throw new UnsupportedOperationException();
    }

    @Override
    public WoodType getWoodType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public RotatedPillarBlock getLog() {
        return log;
    }

    @Override
    public RotatedPillarBlock getStrippedLog() {
        return strippedLog;
    }

    @Override
    public RotatedPillarBlock getWood() {
        return wood;
    }

    @Override
    public RotatedPillarBlock getStrippedWood() {
        return strippedWood;
    }

    @Override
    public ChestBlock getChest() {
        return chest.get();
    }

    @Override
    public CraftingTableBlock getCraftingTable() {
        return craftingTable.get();
    }

    @Override
    public WoodButtonBlock getButton() {
        return button;
    }

    @Override
    public DoorBlock getDoor() {
        return door;
    }

    @Override
    public FenceBlock getFence() {
        return fence;
    }

    @Override
    public FenceGateBlock getFenceGate() {
        return fenceGate;
    }

    @Override
    public PressurePlateBlock getPressurePlate() {
        return pressurePlate;
    }

    @Override
    public SlabBlock getSlab() {
        return slab;
    }

    @Override
    public StairBlock getStairs() {
        return stairs;
    }

    @Override
    public TrapDoorBlock getTrapdoor() {
        return trapdoor;
    }

    @Override
    public WallSignBlock getWallSign() {
        return wallSign;
    }

    @Override
    public Block getPlanks() {
        return planks;
    }

}
