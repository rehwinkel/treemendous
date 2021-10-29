package de.deerangle.treemendous.tree;

import de.deerangle.treemendous.block.CustomChestBlock;
import de.deerangle.treemendous.block.CustomCraftingTableBlock;
import de.deerangle.treemendous.block.CustomStandingSignBlock;
import de.deerangle.treemendous.block.CustomWallSignBlock;
import de.deerangle.treemendous.block.FlammableBlock;
import de.deerangle.treemendous.block.FlammableFenceBlock;
import de.deerangle.treemendous.block.FlammableFenceGateBlock;
import de.deerangle.treemendous.block.FlammableLeavesBlock;
import de.deerangle.treemendous.block.FlammableRotatedPillarBlock;
import de.deerangle.treemendous.block.FlammableSlabBlock;
import de.deerangle.treemendous.block.FlammableStairBlock;
import de.deerangle.treemendous.block.StrippableBlock;
import de.deerangle.treemendous.entity.BoatType;
import de.deerangle.treemendous.item.CustomBoatItem;
import de.deerangle.treemendous.item.CustomChestBlockItem;
import de.deerangle.treemendous.main.Treemendous;
import de.deerangle.treemendous.tree.config.SaplingConfig;
import de.deerangle.treemendous.tree.config.TreeConfig;
import de.deerangle.treemendous.tree.util.ILeavesColor;
import de.deerangle.treemendous.tree.util.WeightedTreeMaker;
import de.deerangle.treemendous.world.TreemendousTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.WoodButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Tree
{

    private final String registryName;
    private RegistryObject<Block> planks;
    private RegistryObject<RotatedPillarBlock> strippedLog;
    private RegistryObject<StrippableBlock> log;
    private RegistryObject<RotatedPillarBlock> strippedWood;
    private RegistryObject<StrippableBlock> wood;
    private RegistryObject<StairBlock> stairs;
    private RegistryObject<SlabBlock> slab;
    private RegistryObject<PressurePlateBlock> pressurePlate;
    private RegistryObject<WoodButtonBlock> button;
    private RegistryObject<FenceBlock> fence;
    private RegistryObject<FenceGateBlock> fenceGate;
    private RegistryObject<DoorBlock> door;
    private RegistryObject<TrapDoorBlock> trapdoor;
    private RegistryObject<LeavesBlock> leaves;
    private RegistryObject<StandingSignBlock> sign;
    private RegistryObject<WallSignBlock> wallSign;
    private RegistryObject<CustomChestBlock> chest;
    private RegistryObject<CraftingTableBlock> craftingTable;
    private Map<String, RegistryObject<SaplingBlock>> saplings;
    private Map<String, RegistryObject<FlowerPotBlock>> pottedSaplings;
    private Map<String, SaplingConfig> saplingConfigs;
    private RegistryObject<CustomBoatItem> boatItem;
    private RegistryObject<SignItem> signItem;
    private WoodType woodType;
    private Tag.Named<Block> logsBlockTag;
    private Tag.Named<Item> logsItemTag;
    private BoatType boatType;
    private ILeavesColor leavesColor;
    private boolean hasMegaTree;
    private Map<String, ConfiguredFeature<?, ?>> forestOrTaigaTrees;
    private Map<String, ConfiguredFeature<?, ?>> savannaTrees;
    private Map<String, ConfiguredFeature<?, ?>> shatteredSavannaTrees;

    public Tree(String registryName)
    {
        this.registryName = registryName;
    }

    public static Tree fromConfig(DeferredRegister<Block> blocks, DeferredRegister<Item> items, TreeConfig config)
    {
        Tree tree = new Tree(config.registryName());
        tree.saplings = new HashMap<>();
        tree.pottedSaplings = new HashMap<>();
        tree.saplingConfigs = new HashMap<>();
        tree.forestOrTaigaTrees = new HashMap<>();
        tree.savannaTrees = new HashMap<>();
        tree.shatteredSavannaTrees = new HashMap<>();
        tree.logsBlockTag = BlockTags.bind(Treemendous.MODID + ":" + config.registryName() + "_logs");
        tree.logsItemTag = ItemTags.bind(Treemendous.MODID + ":" + config.registryName() + "_logs");
        tree.woodType = WoodType.register(WoodType.create(config.registryName()));
        MaterialColor woodColor = config.woodMaterialColor();
        MaterialColor barkColor = config.barkMaterialColor();
        BlockBehaviour.Properties logProperties = BlockBehaviour.Properties.of(Material.WOOD, (state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? woodColor : barkColor).strength(2.0F).sound(SoundType.WOOD);
        BlockBehaviour.Properties woodProperties = BlockBehaviour.Properties.of(Material.WOOD, barkColor).strength(2.0F).sound(SoundType.WOOD);
        BlockBehaviour.Properties strippedProperties = BlockBehaviour.Properties.of(Material.WOOD, woodColor).strength(2.0F).sound(SoundType.WOOD);
        BlockBehaviour.Properties planksProperties = BlockBehaviour.Properties.of(Material.WOOD, woodColor).strength(2.0F, 3.0F).sound(SoundType.WOOD);
        BlockBehaviour.Properties craftingTableProperties = BlockBehaviour.Properties.of(Material.WOOD, woodColor).strength(2.5F).sound(SoundType.WOOD);
        BlockBehaviour.Properties doorProperties = BlockBehaviour.Properties.of(Material.WOOD, woodColor).strength(3.0F).noOcclusion().sound(SoundType.WOOD);
        BlockBehaviour.Properties trapdoorProperties = BlockBehaviour.Properties.of(Material.WOOD, woodColor).strength(3.0F).noOcclusion().sound(SoundType.WOOD).isValidSpawn((state, world, pos, entityType) -> false);
        BlockBehaviour.Properties signProperties = BlockBehaviour.Properties.of(Material.WOOD, woodColor).noCollission().strength(1.0F).sound(SoundType.WOOD);

        tree.leavesColor = config.leavesColor();
        tree.planks = blocks.register(getNameForTree(config, "planks"), () -> new FlammableBlock(planksProperties));
        tree.strippedLog = blocks.register(getNameForTree(config, "stripped", "log"), () -> new FlammableRotatedPillarBlock(strippedProperties));
        tree.log = blocks.register(getNameForTree(config, "log"), () -> new StrippableBlock(logProperties, tree.strippedLog::get));
        tree.strippedWood = blocks.register(getNameForTree(config, "stripped", "wood"), () -> new FlammableRotatedPillarBlock(strippedProperties));
        tree.wood = blocks.register(getNameForTree(config, "wood"), () -> new StrippableBlock(woodProperties, tree.strippedWood::get));
        tree.stairs = blocks.register(getNameForTree(config, "stairs"), () -> new FlammableStairBlock(() -> tree.planks.get().defaultBlockState(), planksProperties));
        tree.slab = blocks.register(getNameForTree(config, "slab"), () -> new FlammableSlabBlock(planksProperties));
        tree.pressurePlate = blocks.register(getNameForTree(config, "pressure_plate"), () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, planksProperties));
        tree.button = blocks.register(getNameForTree(config, "button"), () -> new WoodButtonBlock(planksProperties));
        tree.fence = blocks.register(getNameForTree(config, "fence"), () -> new FlammableFenceBlock(planksProperties));
        tree.fenceGate = blocks.register(getNameForTree(config, "fence_gate"), () -> new FlammableFenceGateBlock(planksProperties));
        tree.door = blocks.register(getNameForTree(config, "door"), () -> new DoorBlock(doorProperties));
        tree.trapdoor = blocks.register(getNameForTree(config, "trapdoor"), () -> new TrapDoorBlock(trapdoorProperties));
        tree.hasMegaTree = config.saplingConfigs().stream().flatMap(saplingConfig -> saplingConfig.getTreeMakers().stream()).anyMatch(WeightedTreeMaker::mega);
        for (SaplingConfig saplingConfig : config.saplingConfigs())
        {
            String saplingName = saplingConfig.variantName();
            String name;
            if (saplingName == null)
            {
                name = "sapling";
            } else
            {
                name = saplingName + "_sapling";
            }
            RegistryObject<SaplingBlock> registeredSapling = blocks.register(getNameForTree(config, name), () -> new SaplingBlock(new TreemendousTreeGrower(saplingConfig, tree), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
            tree.saplings.put(saplingName, registeredSapling);
            tree.saplingConfigs.put(saplingName, saplingConfig);
            //noinspection deprecation
            tree.pottedSaplings.put(saplingName, blocks.register(getNameForTree(config, "potted", name), () -> new FlowerPotBlock(registeredSapling.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion())));
        }
        tree.leaves = blocks.register(getNameForTree(config, "leaves"), () -> new FlammableLeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(Tree::ocelotOrParrot).isSuffocating((state, world, pos) -> false).isViewBlocking((state, world, pos) -> false)));
        tree.sign = blocks.register(getNameForTree(config, "sign"), () -> new CustomStandingSignBlock(signProperties, tree.woodType));
        tree.wallSign = blocks.register(getNameForTree(config, "wall_sign"), () -> new CustomWallSignBlock(signProperties, tree.woodType));
        tree.craftingTable = blocks.register(getNameForTree(config, "crafting_table"), () -> new CustomCraftingTableBlock(craftingTableProperties));
        tree.chest = blocks.register(getNameForTree(config, "chest"), () -> new CustomChestBlock(craftingTableProperties, config.registryName()));
        tree.signItem = items.register(getNameForTree(config, "sign"), () -> new SignItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTab.TAB_DECORATIONS), tree.sign.get(), tree.wallSign.get()));
        tree.boatType = BoatType.createAndRegister(config.registryName(), () -> tree.boatItem::get);
        tree.boatItem = items.register(getNameForTree(config, "boat"), () -> new CustomBoatItem(tree.boatType, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION)));
        registerBlockItem(items, getNameForTree(config, "planks"), tree.planks, CreativeModeTab.TAB_BUILDING_BLOCKS);
        registerBlockItem(items, getNameForTree(config, "log"), tree.log, CreativeModeTab.TAB_BUILDING_BLOCKS);
        registerBlockItem(items, getNameForTree(config, "stripped", "log"), tree.strippedLog, CreativeModeTab.TAB_BUILDING_BLOCKS);
        registerBlockItem(items, getNameForTree(config, "wood"), tree.wood, CreativeModeTab.TAB_BUILDING_BLOCKS);
        registerBlockItem(items, getNameForTree(config, "stripped", "wood"), tree.strippedWood, CreativeModeTab.TAB_BUILDING_BLOCKS);
        registerBlockItem(items, getNameForTree(config, "stairs"), tree.stairs, CreativeModeTab.TAB_BUILDING_BLOCKS);
        registerBlockItem(items, getNameForTree(config, "slab"), tree.slab, CreativeModeTab.TAB_BUILDING_BLOCKS);
        registerBlockItem(items, getNameForTree(config, "pressure_plate"), tree.pressurePlate, CreativeModeTab.TAB_REDSTONE);
        registerBlockItem(items, getNameForTree(config, "button"), tree.button, CreativeModeTab.TAB_REDSTONE);
        registerBlockItem(items, getNameForTree(config, "fence"), tree.fence, CreativeModeTab.TAB_DECORATIONS);
        registerBlockItem(items, getNameForTree(config, "fence_gate"), tree.fenceGate, CreativeModeTab.TAB_REDSTONE);
        registerBlockItem(items, getNameForTree(config, "door"), tree.door, CreativeModeTab.TAB_REDSTONE);
        registerBlockItem(items, getNameForTree(config, "trapdoor"), tree.trapdoor, CreativeModeTab.TAB_REDSTONE);
        for (int i = 0; i < config.saplingConfigs().size(); i++)
        {
            SaplingConfig saplingConfig = config.saplingConfigs().get(i);
            String saplingName = saplingConfig.variantName();
            String name;
            if (saplingName == null)
            {
                name = "sapling";
            } else
            {
                name = saplingName + "_sapling";
            }
            registerBlockItem(items, getNameForTree(config, name), tree.saplings.get(saplingName), CreativeModeTab.TAB_DECORATIONS);
        }
        registerBlockItem(items, getNameForTree(config, "leaves"), tree.leaves, CreativeModeTab.TAB_DECORATIONS);
        registerBlockItem(items, getNameForTree(config, "crafting_table"), tree.craftingTable, CreativeModeTab.TAB_DECORATIONS);
        registerChestBlockItem(items, getNameForTree(config, "chest"), tree.chest);
        return tree;
    }

    private static ConfiguredFeature<?, ?> getTreesFeature(Tree tree, String saplingName, Predicate<WeightedTreeMaker> use)
    {
        SaplingConfig saplingConfig = tree.getSaplingConfig(saplingName);
        List<WeightedTreeMaker> treeMakers = saplingConfig.getTreeMakers();
        List<Supplier<ConfiguredFeature<?, ?>>> configuredTrees = new ArrayList<>();
        for (WeightedTreeMaker treeMaker : treeMakers)
        {
            if (use.test(treeMaker))
            {
                ConfiguredFeature<?, ?> configuredTree = saplingConfig.makeTree(treeMaker.treeMaker(), tree);
                Supplier<ConfiguredFeature<?, ?>> treeSupplier = () -> configuredTree;
                for (int i = 0; i < treeMaker.weight(); i++)
                {
                    configuredTrees.add(treeSupplier);
                }
            }
        }
        return Feature.SIMPLE_RANDOM_SELECTOR
                .configured(new SimpleRandomFeatureConfiguration(configuredTrees))
                .decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED);
    }

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> registerConfiguredFeature(String name, ConfiguredFeature<FC, ?> feature)
    {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Treemendous.MODID, name), feature);
    }

    private static ConfiguredFeature<?, ?> registerForestOrTaigaTrees(Tree tree, String saplingName, Predicate<WeightedTreeMaker> predicate)
    {
        String name = (saplingName == null ? tree.getRegistryName() : tree.getRegistryName() + "_" + saplingName) + "_trees";
        return registerConfiguredFeature(name, getTreesFeature(tree, saplingName, predicate).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(10, 0.1F, 1))));
    }

    private static ConfiguredFeature<?, ?> registerSavannaTrees(Tree tree, String saplingName, Predicate<WeightedTreeMaker> predicate)
    {
        String name = (saplingName == null ? tree.getRegistryName() : tree.getRegistryName() + "_" + saplingName) + "_savanna_trees";
        return registerConfiguredFeature(name, getTreesFeature(tree, saplingName, predicate).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(1, 0.1F, 1))));
    }

    private static ConfiguredFeature<?, ?> registerShatteredTrees(Tree tree, String saplingName, Predicate<WeightedTreeMaker> predicate)
    {
        String name = (saplingName == null ? tree.getRegistryName() : tree.getRegistryName() + "_" + saplingName) + "_shattered_savanna_trees";
        return registerConfiguredFeature(name, getTreesFeature(tree, saplingName, predicate).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(2, 0.1F, 1))));
    }

    private static void registerChestBlockItem(DeferredRegister<Item> items, String name, RegistryObject<? extends Block> block)
    {
        //noinspection unchecked
        items.register(name, () -> new CustomChestBlockItem(block.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    }

    public static boolean ocelotOrParrot(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType)
    {
        return entityType == EntityType.OCELOT || entityType == EntityType.PARROT;
    }

    private static void registerBlockItem(DeferredRegister<Item> items, String name, RegistryObject<? extends Block> block, CreativeModeTab tab)
    {
        //noinspection unchecked
        items.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    private static String getNameForTree(TreeConfig config, String suffix)
    {
        return String.format("%s_%s", config.registryName(), suffix);
    }

    private static String getNameForTree(TreeConfig config, String prefix, String suffix)
    {
        return String.format("%s_%s_%s", prefix, config.registryName(), suffix);
    }

    public ConfiguredFeature<?, ?> getForestOrTaigaTrees(String sapling)
    {
        ConfiguredFeature<?, ?> feature = forestOrTaigaTrees.get(sapling);
        if (feature == null)
        {
            ConfiguredFeature<?, ?> newFeature = registerForestOrTaigaTrees(this, sapling, maker -> !maker.mega());
            this.forestOrTaigaTrees.put(sapling, newFeature);
            feature = newFeature;
        }
        return feature;
    }

    public ConfiguredFeature<?, ?> getSavannaTrees(String sapling)
    {
        ConfiguredFeature<?, ?> feature = savannaTrees.get(sapling);
        if (feature == null)
        {
            ConfiguredFeature<?, ?> newFeature = registerSavannaTrees(this, sapling, maker -> !maker.mega());
            this.savannaTrees.put(sapling, newFeature);
            feature = newFeature;
        }
        return feature;
    }

    public ConfiguredFeature<?, ?> getShatteredSavannaTrees(String sapling)
    {
        ConfiguredFeature<?, ?> feature = shatteredSavannaTrees.get(sapling);
        if (feature == null)
        {
            ConfiguredFeature<?, ?> newFeature = registerShatteredTrees(this, sapling, maker -> !maker.mega());
            this.shatteredSavannaTrees.put(sapling, newFeature);
            feature = newFeature;
        }
        return feature;
    }

    public String getRegistryName()
    {
        return registryName;
    }

    public Block getPlanks()
    {
        return this.planks.get();
    }

    public RotatedPillarBlock getLog()
    {
        return this.log.get();
    }

    public RotatedPillarBlock getStrippedLog()
    {
        return this.strippedLog.get();
    }

    public RotatedPillarBlock getWood()
    {
        return this.wood.get();
    }

    public RotatedPillarBlock getStrippedWood()
    {
        return this.strippedWood.get();
    }

    public StairBlock getStairs()
    {
        return stairs.get();
    }

    public SlabBlock getSlab()
    {
        return slab.get();
    }

    public FenceBlock getFence()
    {
        return fence.get();
    }

    public FenceGateBlock getFenceGate()
    {
        return fenceGate.get();
    }

    public PressurePlateBlock getPressurePlate()
    {
        return pressurePlate.get();
    }

    public WoodButtonBlock getButton()
    {
        return button.get();
    }

    public DoorBlock getDoor()
    {
        return door.get();
    }

    public TrapDoorBlock getTrapdoor()
    {
        return trapdoor.get();
    }

    public LeavesBlock getLeaves()
    {
        return leaves.get();
    }

    public StandingSignBlock getSign()
    {
        return sign.get();
    }

    public WallSignBlock getWallSign()
    {
        return wallSign.get();
    }

    public SaplingBlock getDefaultSapling()
    {
        return saplings.get(null).get();
    }

    public FlowerPotBlock getDefaultPottedSapling()
    {
        return pottedSaplings.get(null).get();
    }

    public CraftingTableBlock getCraftingTable()
    {
        return craftingTable.get();
    }

    public ChestBlock getChest()
    {
        return chest.get();
    }

    public CustomBoatItem getBoatItem()
    {
        return boatItem.get();
    }

    public SignItem getSignItem()
    {
        return signItem.get();
    }

    public WoodType getWoodType()
    {
        return woodType;
    }

    public Tag.Named<Block> getLogsBlockTag()
    {
        return logsBlockTag;
    }

    public Tag.Named<Item> getLogsItemTag()
    {
        return logsItemTag;
    }

    public ILeavesColor getLeavesColor()
    {
        return this.leavesColor;
    }

    public SaplingBlock getSapling(String key)
    {
        return this.saplings.get(key).get();
    }

    public SaplingBlock getRandomSapling(Random rand)
    {
        Collection<RegistryObject<SaplingBlock>> saplings = this.saplings.values();
        RegistryObject<SaplingBlock> sapling = saplings.stream().skip(rand.nextInt(saplings.size())).findFirst().orElseThrow();
        return sapling.get();
    }

    public FlowerPotBlock getPottedSapling(String key)
    {
        return pottedSaplings.get(key).get();
    }

    public SaplingConfig getSaplingConfig(String key)
    {
        return this.saplingConfigs.get(key);
    }

    public boolean hasMegaTree()
    {
        return this.hasMegaTree;
    }

    public Set<String> getSaplingNames()
    {
        return this.saplings.keySet();
    }

}
