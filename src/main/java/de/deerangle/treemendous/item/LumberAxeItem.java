package de.deerangle.treemendous.item;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;
import java.util.function.Predicate;

public class LumberAxeItem extends AxeItem {

    private final Tag.Named<Block> logs;

    public LumberAxeItem(Tier toolTier, float attackDamage, float attackSpeed, Properties props) {
        super(toolTier, attackDamage, attackSpeed, props);
        this.logs = BlockTags.LOGS;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity miner) {
        super.mineBlock(stack, world, state, pos, miner);
        if (!world.isClientSide()) {
            if (logs.contains(state.getBlock())) {
                TreeIterator blocks = new TreeIterator(world, pos, s -> logs.contains(s.getBlock()));
                blocks.next(); // skip the first, otherwise we'll destroy the source block twice
                while (blocks.hasNext()) {
                    BlockPos treePos = blocks.next();
                    world.destroyBlock(treePos, true, miner);
                    stack.hurtAndBreak(1, miner, (entity) -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                    if (stack.isEmpty()) {
                        break;
                    }
                }
            }
        }
        return true;
    }

    public float calculateSpeed(float baseSpeed, Level level, BlockState state, BlockPos pos) {
        float speed = baseSpeed;
        if (logs.contains(state.getBlock())) {
            TreeIterator blocks = new TreeIterator(level, pos, s -> logs.contains(s.getBlock()));
            int i = 0;
            while (blocks.hasNext() && i < 100) { // limit to 100 to reduce lag
                blocks.next();
                i++;
            }
            speed /= i;
        }
        return speed;
    }

    public static class TreeIterator implements Iterator<BlockPos> {

        private final LevelAccessor level;
        private final Predicate<BlockState> isLog;
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> bfsQueue = new ArrayDeque<>();

        public TreeIterator(LevelAccessor world, BlockPos start, Predicate<BlockState> isLog) {
            this.level = world;
            this.isLog = isLog;
            bfsQueue.add(start);
            visited.add(start);
        }

        @Override
        public boolean hasNext() {
            return !this.bfsQueue.isEmpty();
        }

        @Override
        public BlockPos next() {
            BlockPos currentPos = this.bfsQueue.poll();
            if (currentPos != null) {
                tryBranch(currentPos.north());
                tryBranch(currentPos.south());
                tryBranch(currentPos.west());
                tryBranch(currentPos.east());
                tryBranch(currentPos.above());
            }
            return currentPos;
        }

        private void tryBranch(BlockPos pos) {
            if (!this.visited.contains(pos)) {
                if (this.isLog.test(this.level.getBlockState(pos))) {
                    this.bfsQueue.add(pos);
                    this.visited.add(pos);
                }
            }
        }

    }

}