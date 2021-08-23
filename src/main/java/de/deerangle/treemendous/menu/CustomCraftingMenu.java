package de.deerangle.treemendous.menu;

import de.deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;

public class CustomCraftingMenu extends CraftingMenu {

    private final ContainerLevelAccess access;

    public CustomCraftingMenu(int i, Inventory inventory, ContainerLevelAccess containerLevelAccess) {
        super(i, inventory, containerLevelAccess);
        this.access = containerLevelAccess;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, TreeRegistry.JUNIPER_TREE.getCraftingTable()); //TODO: for all TREES
    }

}
