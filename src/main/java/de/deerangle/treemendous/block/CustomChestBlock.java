package de.deerangle.treemendous.block;

import de.deerangle.treemendous.blockentity.CustomChestBlockEntity;
import de.deerangle.treemendous.main.ExtraRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CustomChestBlock extends ChestBlock {

    private final String woodType;

    public CustomChestBlock(Properties properties, String woodType) {
        super(properties, ExtraRegistry.CHEST::get);
        this.woodType = woodType;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CustomChestBlockEntity(pos, state);
    }

    public String getWoodType() {
        return this.woodType;
    }

}
