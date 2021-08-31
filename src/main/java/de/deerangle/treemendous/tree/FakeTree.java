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

public class FakeTree extends Tree {

    private final RotatedPillarBlock log;
    private final SaplingBlock sapling;

    public FakeTree(Block log, Block sapling) {
        super(null);
        this.log = (RotatedPillarBlock) log;
        this.sapling = (SaplingBlock) sapling;
    }

    @Override
    public RotatedPillarBlock getLog() {
        return this.log;
    }

    @Override
    public SaplingBlock getSapling(int index) {
        return this.sapling;
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
    public FlowerPotBlock getPottedSapling(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ILeavesColor getLeavesColor() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getSaplings() {
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
        throw new UnsupportedOperationException();
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
