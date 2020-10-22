package deerangle.treemendous.data;

import deerangle.treemendous.tree.RegisteredTree;
import deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.DataGenerator;

public class LootTableProvider extends AbstractLootTableProvider {

    public LootTableProvider(DataGenerator gen, String modid) {
        super(gen, modid);
    }

    @Override
    protected void addLootTables() {
        for (RegisteredTree tree : TreeRegistry.TREES) {
            regularBlock(tree.sapling.get());
            leavesBlock((LeavesBlock) tree.leaves.get(), (SaplingBlock) tree.sapling.get(), tree.getApple());
            if (tree.isNotInherited()) {
                regularBlock(tree.planks.get());
                regularBlock(tree.log.get());
                regularBlock(tree.wood.get());
                regularBlock(tree.stripped_log.get());
                regularBlock(tree.stripped_wood.get());
                regularBlock(tree.fence.get());
                regularBlock(tree.fence_gate.get());
                regularBlock(tree.button.get());
                regularBlock(tree.pressure_plate.get());
                regularBlock(tree.trapdoor.get());
                regularBlock(tree.stairs.get());
                regularBlock(tree.sign.get());
                slabBlock((SlabBlock) tree.slab.get());
                doorBlock((DoorBlock) tree.door.get());
            }
        }
    }

}
