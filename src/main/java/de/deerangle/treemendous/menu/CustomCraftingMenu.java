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
        //TODO: remove
        /*
        if (stillValid(this.access, player, ExtraRegistry.BIRCH_CRAFTING_TABLE.get())) return true;
        if (stillValid(this.access, player, ExtraRegistry.SPRUCE_CRAFTING_TABLE.get())) return true;
        if (stillValid(this.access, player, ExtraRegistry.JUNGLE_CRAFTING_TABLE.get())) return true;
        if (stillValid(this.access, player, ExtraRegistry.ACACIA_CRAFTING_TABLE.get())) return true;
        if (stillValid(this.access, player, ExtraRegistry.DARK_OAK_CRAFTING_TABLE.get())) return true;
        if (stillValid(this.access, player, ExtraRegistry.CRIMSON_CRAFTING_TABLE.get())) return true;
        if (stillValid(this.access, player, ExtraRegistry.WARPED_CRAFTING_TABLE.get())) return true;
        */
        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues()) {
            Tree tree = regTree.getTree();
            if (stillValid(this.access, player, tree.getCraftingTable())) {
                return true;
            }
        }
        return false;
    }

}
