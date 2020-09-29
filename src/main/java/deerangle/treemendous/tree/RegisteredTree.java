package deerangle.treemendous.tree;

import com.google.common.collect.ImmutableList;
import deerangle.treemendous.block.CustomStandingSignBlock;
import deerangle.treemendous.block.CustomWallSignBlock;
import deerangle.treemendous.block.StrippableBlock;
import deerangle.treemendous.entity.CustomBoatType;
import deerangle.treemendous.item.CustomBoatItem;
import deerangle.treemendous.main.Treemendous;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class RegisteredTree {

    private static Boolean neverAllowSpawn(BlockState p_235427_0_, IBlockReader p_235427_1_, BlockPos p_235427_2_, EntityType<?> p_235427_3_) {
        return false;
    }

    private static Boolean allowsSpawnOnLeaves(BlockState p_235441_0_, IBlockReader p_235441_1_, BlockPos p_235441_2_, EntityType<?> p_235441_3_) {
        return p_235441_3_ == EntityType.OCELOT || p_235441_3_ == EntityType.PARROT;
    }

    private static boolean isntSolid(BlockState p_235436_0_, IBlockReader p_235436_1_, BlockPos p_235436_2_) {
        return false;
    }

    private final int leavesColor;
    private final String name;
    private final String englishName;
    private final boolean inherited;
    public final RegistryObject<Block> planks, sapling, log, stripped_log, wood, stripped_wood, leaves, pressure_plate, trapdoor, potted_sapling, button, stairs, slab, fence_gate, fence, door, sign, wall_sign;
    public final RegistryObject<Item> planks_item, sapling_item, log_item, stripped_log_item, wood_item, stripped_wood_item, leaves_item, pressure_plate_item, trapdoor_item, button_item, stairs_item, slab_item, fence_gate_item, fence_item, door_item, sign_item, boat_item;
    private final Supplier<IItemProvider> apple;
    public final ITag.INamedTag<Item> logsItemTag;
    public final ITag.INamedTag<Block> logsBlockTag;
    private final WoodType woodType;
    private final CustomBoatType boatType;
    private final BiFunction<Block, Block, ConfiguredFeature<BaseTreeFeatureConfig, ?>> featureBiFunction;
    private ConfiguredFeature<BaseTreeFeatureConfig, ?> singleTreeFeature;
    private ConfiguredFeature<?, ?> treesFeature;
    private final Collection<RegistryKey<Biome>> biomes;
    private final Collection<RegistryKey<Biome>> frostyBiomes;

    private RegisteredTree(DeferredRegister<Block> BLOCKS, DeferredRegister<Item> ITEMS, DeferredRegister<Biome> BIOMES, String name, String englishName, MaterialColor woodColor, MaterialColor barkColor, int leavesColor, Supplier<IItemProvider> apple, RegisteredTree inherit, BiFunction<Block, Block, ConfiguredFeature<BaseTreeFeatureConfig, ?>> feature, BiomeSettings biomeSettings) {
        this.apple = apple;
        this.englishName = englishName;
        this.name = name;
        this.leavesColor = leavesColor;
        this.featureBiFunction = feature;
        this.singleTreeFeature = null;

        this.sapling = BLOCKS.register(name + "_sapling",
                () -> new SaplingBlock(new CustomTree(() -> this.singleTreeFeature),
                        AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly()
                                .zeroHardnessAndResistance().sound(SoundType.PLANT)));
        this.leaves = BLOCKS.register(name + "_leaves", () -> new LeavesBlock(
                AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly()
                        .sound(SoundType.PLANT).notSolid().setAllowsSpawn(RegisteredTree::allowsSpawnOnLeaves)
                        .setSuffocates(RegisteredTree::isntSolid).setBlocksVision(RegisteredTree::isntSolid)));
        this.potted_sapling = BLOCKS.register("potted_" + name + "_sapling", () -> new FlowerPotBlock(sapling.get(),
                AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
        this.sapling_item = ITEMS.register(name + "_sapling",
                () -> new BlockItem(this.sapling.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
        this.leaves_item = ITEMS.register(name + "_leaves",
                () -> new BlockItem(this.leaves.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));

        BIOMES.register(name + "_forest", () -> {
            this.registerFeature();
            return BiomeMaker.makeForestBiome(0.1f, 0.2f, biomeSettings.getTemperature(), false, biomeSettings.isDry(),
                    new MobSpawnInfo.Builder(), this.treesFeature);
        });

        BIOMES.register(name + "_forest_hills", () -> {
            this.registerFeature();
            return BiomeMaker.makeForestBiome(0.55f, 0.4f, biomeSettings.getTemperature(), false, biomeSettings.isDry(),
                    new MobSpawnInfo.Builder(), this.treesFeature);
        });


        this.biomes = ImmutableList
                .of(BiomeMaker.makeBiomeKey(name + "_forest"), BiomeMaker.makeBiomeKey(name + "_forest_hills"));

        if (biomeSettings.isSnowy()) {
            BIOMES.register(name + "_forest_snow", () -> {
                this.registerFeature();
                return BiomeMaker.makeForestBiome(0.1f, 0.2f, biomeSettings.getTemperature(), true, false,
                        new MobSpawnInfo.Builder(), this.treesFeature);
            });

            BIOMES.register(name + "_forest_hills_snow", () -> {
                this.registerFeature();
                return BiomeMaker.makeForestBiome(0.55f, 0.4f, biomeSettings.getTemperature(), true, false,
                        new MobSpawnInfo.Builder(), this.treesFeature);
            });
            this.frostyBiomes = ImmutableList.of(BiomeMaker.makeBiomeKey(name + "_forest_snow"),
                    BiomeMaker.makeBiomeKey(name + "_forest_hills_snow"));
        } else {
            this.frostyBiomes = ImmutableList.of();
        }

        if (inherit == null) {
            this.inherited = false;
            this.logsItemTag = ItemTags.makeWrapperTag(Treemendous.MODID + ":" + name + "_logs");
            this.logsBlockTag = BlockTags.makeWrapperTag(Treemendous.MODID + ":" + name + "_logs");
            this.woodType = new WoodType(name);
            WoodType.VALUES.add(this.woodType);
            this.planks = BLOCKS.register(name + "_planks", () -> new Block(
                    AbstractBlock.Properties.create(Material.WOOD, woodColor).hardnessAndResistance(2.0F, 3.0F)
                            .sound(SoundType.WOOD)));
            this.stripped_log = BLOCKS.register("stripped_" + name + "_log", () -> new RotatedPillarBlock(
                    AbstractBlock.Properties.create(Material.WOOD, (p_235431_2_) -> woodColor)
                            .hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
            this.log = BLOCKS.register(name + "_log", () -> new StrippableBlock(AbstractBlock.Properties
                    .create(Material.WOOD, (blockState) -> blockState
                            .get(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? woodColor : barkColor)
                    .hardnessAndResistance(2.0F).sound(SoundType.WOOD), this.stripped_log));
            this.stripped_wood = BLOCKS.register("stripped_" + name + "_wood", () -> new RotatedPillarBlock(
                    AbstractBlock.Properties.create(Material.WOOD, woodColor).hardnessAndResistance(2.0F)
                            .sound(SoundType.WOOD)));
            this.wood = BLOCKS.register(name + "_wood", () -> new StrippableBlock(
                    AbstractBlock.Properties.create(Material.WOOD, barkColor).hardnessAndResistance(2.0F)
                            .sound(SoundType.WOOD), this.stripped_wood));
            this.sign = BLOCKS.register(name + "_sign", () -> new CustomStandingSignBlock(
                    AbstractBlock.Properties.create(Material.WOOD, woodColor).doesNotBlockMovement()
                            .hardnessAndResistance(1.0F).sound(SoundType.WOOD), this.woodType));
            this.wall_sign = BLOCKS.register(name + "_wall_sign", () -> new CustomWallSignBlock(
                    AbstractBlock.Properties.create(Material.WOOD, woodColor).doesNotBlockMovement()
                            .hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(sign.get()), this.woodType));
            this.pressure_plate = BLOCKS.register(name + "_pressure_plate",
                    () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                            AbstractBlock.Properties.create(Material.WOOD, woodColor).doesNotBlockMovement()
                                    .hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
            this.trapdoor = BLOCKS.register(name + "_trapdoor", () -> new TrapDoorBlock(
                    AbstractBlock.Properties.create(Material.WOOD, woodColor).hardnessAndResistance(3.0F)
                            .sound(SoundType.WOOD).notSolid().setAllowsSpawn(RegisteredTree::neverAllowSpawn)));
            this.button = BLOCKS.register(name + "_button", () -> new WoodButtonBlock(
                    AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement()
                            .hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
            this.stairs = BLOCKS.register(name + "_stairs", () -> new StairsBlock(() -> planks.get().getDefaultState(),
                    AbstractBlock.Properties.from(planks.get())));
            this.slab = BLOCKS.register(name + "_slab", () -> new SlabBlock(
                    AbstractBlock.Properties.create(Material.WOOD, woodColor).hardnessAndResistance(2.0F, 3.0F)
                            .sound(SoundType.WOOD)));
            this.fence_gate = BLOCKS.register(name + "_fence_gate", () -> new FenceGateBlock(
                    AbstractBlock.Properties.create(Material.WOOD, woodColor).hardnessAndResistance(2.0F, 3.0F)
                            .sound(SoundType.WOOD)));
            this.fence = BLOCKS.register(name + "_fence", () -> new FenceBlock(
                    AbstractBlock.Properties.create(Material.WOOD, woodColor).hardnessAndResistance(2.0F, 3.0F)
                            .sound(SoundType.WOOD)));
            this.door = BLOCKS.register(name + "_door", () -> new DoorBlock(
                    AbstractBlock.Properties.create(Material.WOOD, woodColor).hardnessAndResistance(3.0F)
                            .sound(SoundType.WOOD).notSolid()));

            this.planks_item = ITEMS.register(name + "_planks",
                    () -> new BlockItem(this.planks.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
            this.log_item = ITEMS.register(name + "_log",
                    () -> new BlockItem(this.log.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
            this.stripped_log_item = ITEMS.register("stripped_" + name + "_log",
                    () -> new BlockItem(this.stripped_log.get(),
                            new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
            this.stripped_wood_item = ITEMS.register("stripped_" + name + "_wood",
                    () -> new BlockItem(this.stripped_wood.get(),
                            new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
            this.wood_item = ITEMS.register(name + "_wood",
                    () -> new BlockItem(this.wood.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
            this.slab_item = ITEMS.register(name + "_slab",
                    () -> new BlockItem(this.slab.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
            this.pressure_plate_item = ITEMS.register(name + "_pressure_plate",
                    () -> new BlockItem(this.pressure_plate.get(), new Item.Properties().group(ItemGroup.REDSTONE)));
            this.fence_item = ITEMS.register(name + "_fence",
                    () -> new BlockItem(this.fence.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
            this.trapdoor_item = ITEMS.register(name + "_trapdoor",
                    () -> new BlockItem(this.trapdoor.get(), new Item.Properties().group(ItemGroup.REDSTONE)));
            this.fence_gate_item = ITEMS.register(name + "_fence_gate",
                    () -> new BlockItem(this.fence_gate.get(), new Item.Properties().group(ItemGroup.REDSTONE)));
            this.button_item = ITEMS.register(name + "_button",
                    () -> new BlockItem(this.button.get(), new Item.Properties().group(ItemGroup.REDSTONE)));
            this.stairs_item = ITEMS.register(name + "_stairs",
                    () -> new BlockItem(this.stairs.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
            this.door_item = ITEMS.register(name + "_door",
                    () -> new TallBlockItem(this.door.get(), (new Item.Properties()).group(ItemGroup.REDSTONE)));
            this.sign_item = ITEMS.register(name + "_sign",
                    () -> new SignItem((new Item.Properties()).maxStackSize(16).group(ItemGroup.DECORATIONS),
                            this.sign.get(), this.wall_sign.get()));

            this.boatType = CustomBoatType
                    .register(name, new ResourceLocation(Treemendous.MODID, name), this.planks_item::get);
            this.boat_item = ITEMS.register(name + "_boat", () -> new CustomBoatItem(this.boatType,
                    (new Item.Properties()).maxStackSize(1).group(ItemGroup.TRANSPORTATION)));
        } else {
            this.inherited = true;
            this.boatType = inherit.boatType;
            this.logsItemTag = inherit.logsItemTag;
            this.logsBlockTag = inherit.logsBlockTag;
            this.woodType = inherit.woodType;

            this.planks = inherit.planks;
            this.log = inherit.log;
            this.stripped_log = inherit.stripped_log;
            this.wood = inherit.wood;
            this.stripped_wood = inherit.stripped_wood;
            this.pressure_plate = inherit.pressure_plate;
            this.trapdoor = inherit.trapdoor;
            this.button = inherit.button;
            this.stairs = inherit.stairs;
            this.slab = inherit.slab;
            this.fence_gate = inherit.fence_gate;
            this.fence = inherit.fence;
            this.door = inherit.door;
            this.sign = inherit.sign;
            this.wall_sign = inherit.wall_sign;
            this.planks_item = inherit.planks_item;
            this.log_item = inherit.log_item;
            this.stripped_log_item = inherit.stripped_log_item;
            this.wood_item = inherit.wood_item;
            this.stripped_wood_item = inherit.stripped_wood_item;
            this.pressure_plate_item = inherit.pressure_plate_item;
            this.trapdoor_item = inherit.trapdoor_item;
            this.button_item = inherit.button_item;
            this.stairs_item = inherit.stairs_item;
            this.slab_item = inherit.slab_item;
            this.fence_gate_item = inherit.fence_gate_item;
            this.fence_item = inherit.fence_item;
            this.door_item = inherit.door_item;
            this.sign_item = inherit.sign_item;
            this.boat_item = inherit.boat_item;
        }
    }

    public ConfiguredFeature<BaseTreeFeatureConfig, ?> getSingleTreeFeature() {
        return singleTreeFeature;
    }

    public String getName() {
        return this.name;
    }

    public Collection<? extends RegistryKey<Biome>> getBiomes() {
        return this.biomes;
    }

    public Collection<? extends RegistryKey<Biome>> getFrostyBiomes() {
        return this.frostyBiomes;
    }

    public static class Builder {
        private final DeferredRegister<Item> itemRegistry;
        private final DeferredRegister<Block> blockRegistry;
        private final DeferredRegister<Biome> biomeRegistry;
        private final String name;
        private final String englishName;
        private MaterialColor woodColor;
        private MaterialColor logColor;
        private int leavesColor;
        private Supplier<IItemProvider> apple;
        private RegisteredTree inherit;
        private BiFunction<Block, Block, ConfiguredFeature<BaseTreeFeatureConfig, ?>> feature;
        private BiomeSettings biomeSettings;

        private Builder(DeferredRegister<Block> BLOCKS, DeferredRegister<Item> ITEMS, DeferredRegister<Biome> BIOMES, String name, String englishName) {
            this.biomeRegistry = BIOMES;
            this.itemRegistry = ITEMS;
            this.blockRegistry = BLOCKS;
            this.name = name;
            this.englishName = englishName;
            this.woodColor = MaterialColor.WOOD;
            this.logColor = MaterialColor.BROWN;
            this.leavesColor = 8431445;
            this.apple = null;
            this.inherit = null;
            this.feature = (log, leaves) -> null;
            this.biomeSettings = new BiomeSettings.Builder().build();
        }

        public Builder wood(MaterialColor color) {
            this.woodColor = color;
            return this;
        }

        public Builder log(MaterialColor color) {
            this.logColor = color;
            return this;
        }

        public Builder leaves(int color) {
            this.leavesColor = color;
            return this;
        }

        public Builder apple(Supplier<IItemProvider> apple) {
            this.apple = apple;
            return this;
        }

        public static Builder create(DeferredRegister<Block> BLOCKS, DeferredRegister<Item> ITEMS, DeferredRegister<Biome> BIOMES, String id, String name) {
            return new Builder(BLOCKS, ITEMS, BIOMES, id, name);
        }

        public RegisteredTree build() {
            return new RegisteredTree(this.blockRegistry, this.itemRegistry, this.biomeRegistry, this.name,
                    this.englishName, this.woodColor, this.logColor, this.leavesColor, this.apple, this.inherit,
                    this.feature, this.biomeSettings);
        }

        public Builder biome(BiomeSettings.Builder settings) {
            this.biomeSettings = settings.build();
            return this;
        }

        public Builder inheritWood(RegisteredTree inherit) {
            this.inherit = inherit;
            return this;
        }

        public Builder feature(BiFunction<Block, Block, ConfiguredFeature<BaseTreeFeatureConfig, ?>> feature) {
            this.feature = feature;
            return this;
        }
    }

    public void registerFeature() {
        if (this.singleTreeFeature == null) {
            this.singleTreeFeature = BiomeMaker
                    .registerConfiguredFeature(this.name, featureBiFunction.apply(this.log.get(), this.leaves.get()));
            this.treesFeature = BiomeMaker.registerConfiguredFeature("trees_" + this.name,
                    this.singleTreeFeature.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(
                            Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(10, 0.1F, 2))));
        }
    }

    public boolean isNotInherited() {
        return !inherited;
    }

    public String getEnglishName() {
        return englishName;
    }

    public Supplier<IItemProvider> getApple() {
        return this.apple;
    }

    public int getLeavesColor() {
        return this.leavesColor;
    }

    public static class BiomeSettings {
        private final float temperature;
        private final boolean snowy;
        private final boolean dry;

        private BiomeSettings(float temp, boolean snowy, boolean dry) {
            this.temperature = temp;
            this.snowy = snowy;
            this.dry = dry;
        }

        private float getTemperature() {
            return temperature;
        }

        public boolean isSnowy() {
            return snowy;
        }

        public boolean isDry() {
            return dry;
        }

        public static class Builder {
            private float temperature;
            private boolean snowy;
            private boolean dry;

            public Builder() {
                this.temperature = 0.7f;
                this.snowy = false;
                this.dry = false;
            }

            public Builder temperature(float temp) {
                this.temperature = temp;
                return this;
            }

            public Builder snow() {
                this.snowy = true;
                return this;
            }

            public Builder dry() {
                this.snowy = false;
                this.dry = true;
                return this;
            }

            public BiomeSettings build() {
                return new BiomeSettings(this.temperature, this.snowy, this.dry);
            }
        }
    }
}

