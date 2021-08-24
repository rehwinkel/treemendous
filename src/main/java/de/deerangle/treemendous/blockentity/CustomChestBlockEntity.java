package de.deerangle.treemendous.blockentity;

import de.deerangle.treemendous.main.ExtraRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CustomChestBlockEntity extends ChestBlockEntity {

    public CustomChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state);
    }

    public CustomChestBlockEntity(BlockPos pos, BlockState state) {
        this(ExtraRegistry.CHEST.get(), pos, state);
    }

}
