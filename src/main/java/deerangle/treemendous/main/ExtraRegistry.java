package deerangle.treemendous.main;

import deerangle.treemendous.item.LumberAxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ExtraRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister
            .create(ForgeRegistries.ITEMS, Treemendous.MODID);

    public static final RegistryObject<Item> IRON_LUMBER_AXE = ITEMS.register("iron_lumber_axe",
            () -> new LumberAxeItem(ItemTier.IRON, 6.0F, -3.1F, (new Item.Properties()).group(ItemGroup.TOOLS)));
    public static final RegistryObject<Item> GOLDEN_LUMBER_AXE = ITEMS.register("golden_lumber_axe",
            () -> new LumberAxeItem(ItemTier.GOLD, 6.0F, -3.0F, (new Item.Properties()).group(ItemGroup.TOOLS)));
    public static final RegistryObject<Item> DIAMOND_LUMBER_AXE = ITEMS.register("diamond_lumber_axe",
            () -> new LumberAxeItem(ItemTier.DIAMOND, 5.0F, -3.0F, (new Item.Properties()).group(ItemGroup.TOOLS)));
    public static final RegistryObject<Item> NETHERITE_LUMBER_AXE = ITEMS.register("netherite_lumber_axe",
            () -> new LumberAxeItem(ItemTier.NETHERITE, 5.0F, -3.0F, (new Item.Properties()).group(ItemGroup.TOOLS)));

}
