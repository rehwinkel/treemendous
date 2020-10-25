package deerangle.treemendous.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.*;
import net.minecraft.world.storage.loot.functions.ExplosionDecay;
import net.minecraft.world.storage.loot.functions.SetCount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public abstract class AbstractLootTableProvider implements IDataProvider {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private final DataGenerator generator;
    private final String modid;
    private final Map<ResourceLocation, LootTable> tables;

    public AbstractLootTableProvider(DataGenerator gen, String modid) {
        this.tables = new HashMap<>();
        this.modid = modid;
        this.generator = gen;
    }

    protected abstract void addLootTables();

    @Override
    public void act(DirectoryCache cache) throws IOException {
        addLootTables();

        tables.forEach((location, table) -> {
            Path lootTablePath = this.generator.getOutputFolder()
                    .resolve("data/" + location.getNamespace() + "/loot_tables/" + location.getPath() + ".json");
            try {
                IDataProvider.save(GSON, cache, LootTableManager.toJson(table), lootTablePath);
            } catch (IOException e) {
                LOGGER.error("Couldn't write loot table {}", lootTablePath, e);
            }
        });
    }

    @Override
    public String getName() {
        return "LootTables: " + this.modid;
    }

    private String name(Block block) {
        return block.getRegistryName().getPath();
    }

    public void regularBlock(Block block) {
        regularBlock(name(block), block);
    }

    public void regularBlock(String name, Block block) {
        this.tables.put(new ResourceLocation(this.modid, "blocks/" + name),
                LootTable.builder().setParameterSet(LootParameterSets.BLOCK).addLootPool(
                        LootPool.builder().rolls(ConstantRange.of(1)).acceptCondition(SurvivesExplosion.builder())
                                .addEntry(ItemLootEntry.builder(block))).build());
    }

    public void slabBlock(SlabBlock slabBlock) {
        slabBlock(name(slabBlock), slabBlock);
    }

    public void slabBlock(String name, SlabBlock slabBlock) {
        this.tables.put(new ResourceLocation(this.modid, "blocks/" + name),
                LootTable.builder().setParameterSet(LootParameterSets.BLOCK).addLootPool(
                        LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(slabBlock)
                                .acceptFunction(SetCount.builder(ConstantRange.of(2)).acceptCondition(
                                        BlockStateProperty.builder(slabBlock).fromProperties(
                                                StatePropertiesPredicate.Builder.newBuilder()
                                                        .withProp(SlabBlock.TYPE, SlabType.DOUBLE))))
                                .acceptFunction(ExplosionDecay.builder()))).build());
    }

    public void doorBlock(DoorBlock doorBlock) {
        doorBlock(name(doorBlock), doorBlock);
    }

    public void doorBlock(String name, DoorBlock doorBlock) {
        this.tables.put(new ResourceLocation(this.modid, "blocks/" + name),
                LootTable.builder().setParameterSet(LootParameterSets.BLOCK).addLootPool(
                        LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(doorBlock)
                                .acceptCondition(BlockStateProperty.builder(doorBlock).fromProperties(
                                        StatePropertiesPredicate.Builder.newBuilder()
                                                .withProp(DoorBlock.HALF, DoubleBlockHalf.LOWER))))
                                .acceptCondition(SurvivesExplosion.builder())).build());
    }

    public void leavesBlock(LeavesBlock block, SaplingBlock saplingBlock, Supplier<IItemProvider> apple) {
        leavesBlock(name(block), block, saplingBlock, apple);
    }

    public void leavesBlock(String name, LeavesBlock block, SaplingBlock sapling, Supplier<IItemProvider> apple) {
        LootTable.Builder builder = LootTable.builder().setParameterSet(LootParameterSets.BLOCK).addLootPool(
                LootPool.builder().rolls(ConstantRange.of(1)).addEntry(AlternativesLootEntry.builder(
                        ItemLootEntry.builder(block).acceptCondition(Alternative
                                .builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS)), MatchTool
                                        .builder(ItemPredicate.Builder.create().enchantment(
                                                new EnchantmentPredicate(Enchantments.SILK_TOUCH,
                                                        MinMaxBounds.IntBound.atLeast(1)))))),
                        ItemLootEntry.builder(sapling).acceptCondition(SurvivesExplosion.builder()).acceptCondition(
                                TableBonus.builder(Enchantments.FORTUNE, 1f / 20f, 1f / 16f, 1f / 12f, 1f / 10f)))))
                .addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(Items.STICK)
                        .acceptCondition(TableBonus
                                .builder(Enchantments.FORTUNE, 1f / 50f, 1f / 45f, 1f / 40f, 1f / 30f, 1f / 10f))
                        .acceptFunction(SetCount.builder(RandomValueRange.of(1.0f, 2.0f)))
                        .acceptFunction(ExplosionDecay.builder())).acceptCondition(Inverted.builder(Alternative
                        .builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS)), MatchTool
                                .builder(ItemPredicate.Builder.create().enchantment(
                                        new EnchantmentPredicate(Enchantments.SILK_TOUCH,
                                                MinMaxBounds.IntBound.atLeast(1))))))));
        if (apple != null) {
            builder.addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).acceptCondition(Inverted.builder(
                    Alternative.builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS)), MatchTool
                            .builder(ItemPredicate.Builder.create().enchantment(
                                    new EnchantmentPredicate(Enchantments.SILK_TOUCH,
                                            MinMaxBounds.IntBound.atLeast(1))))))).addEntry(
                    ItemLootEntry.builder(apple.get()).acceptCondition(SurvivesExplosion.builder()).acceptCondition(
                            TableBonus.builder(Enchantments.FORTUNE, 1f / 200f, 1f / 180f, 1f / 160f, 1f / 120f,
                                    1f / 40f))));
        }
        this.tables.put(new ResourceLocation(this.modid, "blocks/" + name), builder.build());
    }

}
