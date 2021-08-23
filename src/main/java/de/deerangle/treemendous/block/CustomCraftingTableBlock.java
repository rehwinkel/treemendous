package de.deerangle.treemendous.block;

import de.deerangle.treemendous.menu.CustomCraftingMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;

public class CustomCraftingTableBlock extends CraftingTableBlock {

    private static final Component CONTAINER_TITLE = new TranslatableComponent("container.crafting");

    public CustomCraftingTableBlock(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {
        return new SimpleMenuProvider((i, inventory, player) -> new CustomCraftingMenu(i, inventory, ContainerLevelAccess.create(world, pos)), CONTAINER_TITLE);
    }

}
