package de.deerangle.treemendous.tree;

import de.deerangle.treemendous.data.WoodColors;
import de.deerangle.treemendous.main.Treemendous;
import net.minecraft.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class TreeRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Treemendous.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Treemendous.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Treemendous.MODID);
    // Extra
    public static final RegistryObject<BlockEntityType<SignBlockEntity>> SIGN = BLOCK_ENTITIES.register("sign", () -> BlockEntityType.Builder.of(SignBlockEntity::new, Objects.requireNonNull(getSignBlocks())).build(Objects.requireNonNull(Util.fetchChoiceType(References.BLOCK_ENTITY, "sign"))));
    public static final Tree JUNIPER_TREE = Tree.fromConfig(BLOCKS, ITEMS, JUNIPER_CONFIG);
    // Trees
    private static final TreeConfig JUNIPER_CONFIG = new TreeConfigBuilder("juniper").setAppearance(new TreeTexture(WoodColors.JUNIPER_WOOD, WoodColors.JUNIPER_WOOD, 0)).setLeavesColor(WoodColors.JUNIPER_LEAVES).createTreeConfig();

    private static SignBlock[] getSignBlocks() {
        return new SignBlock[]{JUNIPER_TREE.getSign(), JUNIPER_TREE.getWallSign()};
    }

}