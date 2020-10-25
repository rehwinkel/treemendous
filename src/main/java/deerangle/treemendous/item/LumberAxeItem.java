package deerangle.treemendous.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class LumberAxeItem extends AxeItem {

    public LumberAxeItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return BlockTags.LOGS.contains(state.getBlock()) ? 0.05F * this.efficiency : super
                .getDestroySpeed(stack, state);
    }

    private boolean propagateSingle(Queue<BlockPos> nextStarts, World world, BlockPos.Mutable pos, BlockPos start, int offX, int offY, int offZ, Block requiredBlock, LivingEntity player, int leftUses, int recursionDepth, Set<BlockPos> destroyed) {
        if (destroyed.size() >= leftUses) {
            return true;
        }
        pos.setPos(start);
        pos.move(offX, offY, offZ);
        if (destroyed.contains(new BlockPos(pos))) {
            return false;
        }
        Block a = world.getBlockState(pos).getBlock();
        if (a == requiredBlock) {
            BlockPos breakPos = new BlockPos(pos);
            world.getServer().enqueue(
                    new TickDelayedTask(recursionDepth + 40, () -> world.destroyBlock(breakPos, true, player)));
            destroyed.add(breakPos);
            nextStarts.add(breakPos);
        }
        return false;
    }

    private boolean propagateDestruction(Queue<BlockPos> nextStarts, World world, BlockPos startUnused, Block requiredBlock, LivingEntity player, int leftUses, int recursionDepth, Set<BlockPos> destroyed) {
        BlockPos.Mutable pos = new BlockPos.Mutable();

        while (nextStarts.size() > 0) {
            BlockPos start = nextStarts.remove();
            if (propagateSingle(nextStarts, world, pos, start, 0, 1, 0, requiredBlock, player, leftUses, recursionDepth,
                    destroyed)) {
                return true;
            }
            if (propagateSingle(nextStarts, world, pos, start, 0, 0, 1, requiredBlock, player, leftUses, recursionDepth,
                    destroyed)) {
                return true;
            }
            if (propagateSingle(nextStarts, world, pos, start, 0, 0, -1, requiredBlock, player, leftUses,
                    recursionDepth, destroyed)) {
                return true;
            }
            if (propagateSingle(nextStarts, world, pos, start, 1, 0, 0, requiredBlock, player, leftUses, recursionDepth,
                    destroyed)) {
                return true;
            }
            if (propagateSingle(nextStarts, world, pos, start, -1, 0, 0, requiredBlock, player, leftUses,
                    recursionDepth, destroyed)) {
                return true;
            }
            if (propagateSingle(nextStarts, world, pos, start, -1, 0, 1, requiredBlock, player, leftUses,
                    recursionDepth, destroyed)) {
                return true;
            }
            if (propagateSingle(nextStarts, world, pos, start, -1, 0, -1, requiredBlock, player, leftUses,
                    recursionDepth, destroyed)) {
                return true;
            }
            if (propagateSingle(nextStarts, world, pos, start, 1, 0, 1, requiredBlock, player, leftUses, recursionDepth,
                    destroyed)) {
                return true;
            }
            if (propagateSingle(nextStarts, world, pos, start, 1, 0, -1, requiredBlock, player, leftUses,
                    recursionDepth, destroyed)) {
                return true;
            }
            if (propagateSingle(nextStarts, world, pos, start, 0, 1, 1, requiredBlock, player, leftUses, recursionDepth,
                    destroyed)) {
                return true;
            }
            if (propagateSingle(nextStarts, world, pos, start, 0, 1, -1, requiredBlock, player, leftUses,
                    recursionDepth, destroyed)) {
                return true;
            }
            if (propagateSingle(nextStarts, world, pos, start, 1, 1, 0, requiredBlock, player, leftUses, recursionDepth,
                    destroyed)) {
                return true;
            }
            if (propagateSingle(nextStarts, world, pos, start, -1, 1, 0, requiredBlock, player, leftUses,
                    recursionDepth, destroyed)) {
                return true;
            }
            if (propagateSingle(nextStarts, world, pos, start, -1, 1, 1, requiredBlock, player, leftUses,
                    recursionDepth, destroyed)) {
                return true;
            }
            if (propagateSingle(nextStarts, world, pos, start, -1, 1, -1, requiredBlock, player, leftUses,
                    recursionDepth, destroyed)) {
                return true;
            }
            if (propagateSingle(nextStarts, world, pos, start, 1, 1, 1, requiredBlock, player, leftUses, recursionDepth,
                    destroyed)) {
                return true;
            }
            if (propagateSingle(nextStarts, world, pos, start, 1, 1, -1, requiredBlock, player, leftUses,
                    recursionDepth, destroyed)) {
                return true;
            }
        }
        return false;
    }

    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (!worldIn.isRemote && state.getBlockHardness(worldIn, pos) != 0.0F) {
            if (BlockTags.LOGS.contains(state.getBlock())) {
                Set<BlockPos> destroyed = new HashSet<>();
                int usesLeft = stack.getMaxDamage() - stack.getDamage();
                Queue<BlockPos> nextStarts = new ArrayDeque<>();
                destroyed.add(pos);
                nextStarts.add(pos);
                propagateDestruction(nextStarts, worldIn, null, state.getBlock(), entityLiving, usesLeft, 1, destroyed);
                stack.damageItem(destroyed.size(), entityLiving,
                        (entity) -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));
            } else {
                stack.damageItem(1, entityLiving, (entity) -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));
            }
        }

        return true;
    }

}
