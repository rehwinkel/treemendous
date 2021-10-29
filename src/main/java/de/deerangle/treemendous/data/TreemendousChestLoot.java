package de.deerangle.treemendous.data;

import de.deerangle.treemendous.main.ExtraRegistry;
import de.deerangle.treemendous.main.Treemendous;
import de.deerangle.treemendous.tree.RegisteredTree;
import net.minecraft.data.loot.ChestLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryManager;

import java.util.function.BiConsumer;

public class TreemendousChestLoot extends ChestLoot
{

    public static final ResourceLocation RANGER_HOUSE = new ResourceLocation(Treemendous.MODID, "chests/ranger_house");

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> tables)
    {
        LootPool.Builder saplingPool = LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 2.0F));
        LootPool.Builder logPool = LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 3.0F));
        for (RegisteredTree regTree : RegistryManager.ACTIVE.getRegistry(RegisteredTree.class).getValues())
        {
            for (String saplingName : regTree.getTree().getSaplingNames())
            {
                SaplingBlock sapling = regTree.getTree().getSapling(saplingName);
                saplingPool.add(LootItem.lootTableItem(sapling).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f))));
            }
            RotatedPillarBlock log = regTree.getTree().getLog();
            RotatedPillarBlock strippedLog = regTree.getTree().getStrippedLog();
            logPool.add(LootItem.lootTableItem(log).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 6.0f))));
            logPool.add(LootItem.lootTableItem(strippedLog).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 4.0f))));
        }
        tables.accept(RANGER_HOUSE, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(0.0F, 1.75F))
                        .add(LootItem.lootTableItem(Items.STONE_AXE).setWeight(30).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15f, 0.75f), false)))
                        .add(LootItem.lootTableItem(Items.IRON_AXE).setWeight(20).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15f, 0.75f), false)))
                        .add(LootItem.lootTableItem(Items.GOLDEN_AXE).setWeight(15).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15f, 0.75f), false)))
                        .add(LootItem.lootTableItem(Items.DIAMOND_AXE).setWeight(2).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15f, 0.75f), false)))
                        .add(LootItem.lootTableItem(ExtraRegistry.IRON_LUMBER_AXE.get()).setWeight(15).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15f, 0.75f), false)))
                        .add(LootItem.lootTableItem(ExtraRegistry.GOLDEN_LUMBER_AXE.get()).setWeight(10).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15f, 0.75f), false)))
                        .add(LootItem.lootTableItem(ExtraRegistry.DIAMOND_LUMBER_AXE.get()).setWeight(1).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15f, 0.75f), false)))
                )
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(0.0F, 0.75F))
                        .add(LootItem.lootTableItem(Items.STONE_AXE).setWeight(30).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15f, 0.75f), false)).apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
                        .add(LootItem.lootTableItem(Items.IRON_AXE).setWeight(20).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15f, 0.75f), false)).apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
                        .add(LootItem.lootTableItem(Items.GOLDEN_AXE).setWeight(15).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15f, 0.75f), false)).apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
                        .add(LootItem.lootTableItem(Items.DIAMOND_AXE).setWeight(2).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15f, 0.75f), false)).apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
                        .add(LootItem.lootTableItem(ExtraRegistry.IRON_LUMBER_AXE.get()).setWeight(15).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15f, 0.75f), false)).apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
                        .add(LootItem.lootTableItem(ExtraRegistry.GOLDEN_LUMBER_AXE.get()).setWeight(10).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15f, 0.75f), false)).apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
                        .add(LootItem.lootTableItem(ExtraRegistry.DIAMOND_LUMBER_AXE.get()).setWeight(1).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15f, 0.75f), false)).apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
                )
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(2.0F))
                        .add(LootItem.lootTableItem(Items.BONE).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
                        .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
                        .add(LootItem.lootTableItem(Items.STRING).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
                        .add(LootItem.lootTableItem(Items.GOLDEN_APPLE).setWeight(1))
                )
                .withPool(saplingPool)
                .withPool(logPool)
        );
    }
}
