package de.deerangle.treemendous.menu;

import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.Tree;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraftforge.registries.RegistryManager;

public class CustomCraftingMenu extends CraftingMenu {

    private final ContainerLevelAccess access;

    public CustomCraftingMenu(int i, Inventory inventory, ContainerLevelAccess containerLevelAccess) {
        super(i, inventory, containerLevelAccess);
        this.access = containerLevelAccess;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean stillValid(Player player) {
        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues()) {
            Tree tree = regTree.getTree();
            if (stillValid(this.access, player, tree.getCraftingTable())) {
                return true;
            }
        }
        return false;
    }

}
