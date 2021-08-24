package de.deerangle.treemendous.tree;

import de.deerangle.treemendous.blockentity.CustomChestBlockEntity;
import de.deerangle.treemendous.data.WoodColors;
import de.deerangle.treemendous.entity.CustomBoat;
import de.deerangle.treemendous.main.Treemendous;
import net.minecraft.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class TreeRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Treemendous.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Treemendous.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Treemendous.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Treemendous.MODID);

    // Extra
    private static final List<Supplier<SignBlock>> SIGN_BLOCK_LIST = new ArrayList<>();
    private static final List<Supplier<ChestBlock>> CHEST_BLOCK_LIST = new ArrayList<>();
    public static final RegistryObject<BlockEntityType<SignBlockEntity>> SIGN = BLOCK_ENTITIES.register("sign", () -> BlockEntityType.Builder.of(SignBlockEntity::new, SIGN_BLOCK_LIST.stream().map(Supplier::get).toArray(SignBlock[]::new)).build(Objects.requireNonNull(Util.fetchChoiceType(References.BLOCK_ENTITY, "sign"))));
    public static final RegistryObject<BlockEntityType<CustomChestBlockEntity>> CHEST = BLOCK_ENTITIES.register("chest", () -> BlockEntityType.Builder.of(CustomChestBlockEntity::new, CHEST_BLOCK_LIST.stream().map(Supplier::get).toArray(ChestBlock[]::new)).build(Objects.requireNonNull(Util.fetchChoiceType(References.BLOCK_ENTITY, "chest"))));
    public static final RegistryObject<EntityType<CustomBoat>> BOAT = ENTITIES.register("boat", () -> EntityType.Builder.<CustomBoat>of(CustomBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("boat"));

    // Tree Configs
    private static final TreeConfig JUNIPER_CONFIG = new TreeConfigBuilder("juniper").setAppearance(new TreeTexture(WoodColors.JUNIPER_WOOD, WoodColors.JUNIPER_WOOD, 0)).setLeavesColor(WoodColors.JUNIPER_LEAVES).createTreeConfig();

    // Trees
    public static final Tree JUNIPER_TREE = register(Tree.fromConfig(BLOCKS, ITEMS, JUNIPER_CONFIG));

    public static Tree register(Tree tree) {
        SIGN_BLOCK_LIST.add(tree::getSign);
        SIGN_BLOCK_LIST.add(tree::getWallSign);
        CHEST_BLOCK_LIST.add(tree::getChest);
        return tree;
    }

}
