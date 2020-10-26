package deerangle.treemendous.main;

import deerangle.treemendous.block.entity.CustomSignTileEntity;
import deerangle.treemendous.entity.CustomBoatEntity;
import deerangle.treemendous.item.LumberAxeItem;
import deerangle.treemendous.tree.TreeRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ExtraRegistry {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Treemendous.MODID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(
            ForgeRegistries.TILE_ENTITIES, Treemendous.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES,
            Treemendous.MODID);

    public static final RegistryObject<Item> IRON_LUMBER_AXE = ITEMS.register("iron_lumber_axe",
            () -> new LumberAxeItem(ItemTier.IRON, 6.0F, -3.1F, (new Item.Properties()).group(ItemGroup.TOOLS)));
    public static final RegistryObject<Item> GOLDEN_LUMBER_AXE = ITEMS.register("golden_lumber_axe",
            () -> new LumberAxeItem(ItemTier.GOLD, 6.0F, -3.0F, (new Item.Properties()).group(ItemGroup.TOOLS)));
    public static final RegistryObject<Item> DIAMOND_LUMBER_AXE = ITEMS.register("diamond_lumber_axe",
            () -> new LumberAxeItem(ItemTier.DIAMOND, 5.0F, -3.0F, (new Item.Properties()).group(ItemGroup.TOOLS)));
    public static final RegistryObject<Item> POMEGRANATE = ITEMS
            .register("pomegranate", () -> new Item((new Item.Properties()).group(ItemGroup.FOOD).food(Foods.APPLE)));

    public static final RegistryObject<TileEntityType<CustomSignTileEntity>> SIGN = TILE_ENTITIES
            .register("sign", () -> {
                Block[] signs = new Block[TreeRegistry.TREES.size() * 2];
                for (int i = 0; i < TreeRegistry.TREES.size(); i++) {
                    signs[2 * i] = TreeRegistry.TREES.get(i).sign.get();
                    signs[2 * i + 1] = TreeRegistry.TREES.get(i).wall_sign.get();
                }
                return TileEntityType.Builder.create(CustomSignTileEntity::new, signs).build(null);
            });

    public static final RegistryObject<EntityType<CustomBoatEntity>> BOAT = ENTITIES.register("boat",
            () -> EntityType.Builder.<CustomBoatEntity>create(CustomBoatEntity::new, EntityClassification.MISC)
                    .size(1.375F, 0.5625F).setCustomClientFactory((spawnEntity, world) -> new CustomBoatEntity(world))
                    .build("boat"));

}
