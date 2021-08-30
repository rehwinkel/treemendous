package de.deerangle.treemendous.item;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class LumberAxeItem extends AxeItem {

    private final Tag.Named<Block> logs;

    public LumberAxeItem(Tier toolTier, float attackDamage, float attackSpeed, Properties props) {
        super(toolTier, attackDamage, attackSpeed, props);
        this.logs = BlockTags.LOGS;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        float base = super.getDestroySpeed(stack, state);
        if (this.logs.contains(state.getBlock())) {
            return base * 0.1F;
        } else {
            return base;
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity miner) {
        return super.mineBlock(stack, world, state, pos, miner);
    }

}