package de.deerangle.treemendous.tree;

import de.deerangle.treemendous.block.CustomChestBlock;
import de.deerangle.treemendous.item.CustomBoatItem;
import de.deerangle.treemendous.tree.config.TreeConfig;
import de.deerangle.treemendous.tree.util.ILeavesColor;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.Random;
import java.util.Set;

public class FakeTree extends Tree {

    private final RotatedPillarBlock log;
    private final RotatedPillarBlock strippedWood;
    private final SaplingBlock sapling;

    public FakeTree(Block log, Block sapling, Block strippedWood) {
        super(null);
        this.log = (RotatedPillarBlock) log;
        this.sapling = (SaplingBlock) sapling;
        this.strippedWood = (RotatedPillarBlock) strippedWood;
    }

    @Override
    public RotatedPillarBlock getLog() {
        return this.log;
    }

    @Override
    public SaplingBlock getRandomSapling(Random rand) {
        return sapling;
    }

    @Override
    public SaplingBlock getSapling(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> getSaplingNames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public FlowerPotBlock getPottedSapling(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Block getPlanks() {
        throw new UnsupportedOperationException();
    }

    @Override
    public CraftingTableBlock getCraftingTable() {
        throw new UnsupportedOperationException();
    }

    @Override
    public CustomBoatItem getBoatItem() {
        throw new UnsupportedOperationException();
    }

    @Override
    public CustomChestBlock getChest() {
        throw new UnsupportedOperationException();
    }

    @Override
    public DoorBlock getDoor() {
        throw new UnsupportedOperationException();
    }

    @Override
    public FenceBlock getFence() {
        throw new UnsupportedOperationException();
    }

    @Override
    public RotatedPillarBlock getWood() {
        throw new UnsupportedOperationException();
    }

    @Override
    public FenceGateBlock getFenceGate() {
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
    public PressurePlateBlock getPressurePlate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public RotatedPillarBlock getStrippedLog() {
        throw new UnsupportedOperationException();
    }

    @Override
    public RotatedPillarBlock getStrippedWood() {
        return this.strippedWood;
    }

    @Override
    public SaplingBlock getDefaultSapling() {
        throw new UnsupportedOperationException();
    }

    @Override
    public SignItem getSignItem() {
        throw new UnsupportedOperationException();
    }

    @Override
    public SlabBlock getSlab() {
        throw new UnsupportedOperationException();
    }

    @Override
    public StairBlock getStairs() {
        throw new UnsupportedOperationException();
    }

    @Override
    public StandingSignBlock getSign() {
        throw new UnsupportedOperationException();
    }

    @Override
    public TrapDoorBlock getTrapdoor() {
        throw new UnsupportedOperationException();
    }

    @Override
    public TreeConfig getConfig() {
        throw new UnsupportedOperationException();
    }

    @Override
    public WallSignBlock getWallSign() {
        throw new UnsupportedOperationException();
    }

    @Override
    public WoodButtonBlock getButton() {
        throw new UnsupportedOperationException();
    }

    @Override
    public WoodType getWoodType() {
        throw new UnsupportedOperationException();
    }

}
