package de.deerangle.treemendous.blockentity;

import de.deerangle.treemendous.main.ExtraRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CustomSignBlockEntity extends SignBlockEntity {

    public CustomSignBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @SuppressWarnings("NullableProblems")
    public BlockEntityType<?> getType() {
        return ExtraRegistry.SIGN.get();
    }

}
