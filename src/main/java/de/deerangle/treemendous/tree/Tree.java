package de.deerangle.treemendous.tree;

import de.deerangle.treemendous.block.CustomStandingSignBlock;
import de.deerangle.treemendous.block.CustomWallSignBlock;
import de.deerangle.treemendous.block.StrippableBlock;
import de.deerangle.treemendous.main.Treemendous;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.OakTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class Tree {

    private final TreeConfig config;
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
    private RegistryObject<SaplingBlock> sapling;
    private RegistryObject<FlowerPotBlock> pottedSapling;
    private RegistryObject<LeavesBlock> leaves;
    private RegistryObject<StandingSignBlock> sign;
    private RegistryObject<WallSignBlock> wallSign;
    private RegistryObject<BoatItem> boatItem;
    private RegistryObject<SignItem> signItem;
    private WoodType woodType;
    private Tag.Named<Block> logsBlockTag;
    private Tag.Named<Item> logsItemTag;
    //TODO: boat sign

    private Tree(TreeConfig config) {
        this.config = config;
    }

    public static Tree fromConfig(DeferredRegister<Block> blocks, DeferredRegister<Item> items, TreeConfig config) {
        Tree tree = new Tree(config);
        tree.logsBlockTag = BlockTags.bind(Treemendous.MODID + ":" + config.registryName() + "_logs");
        tree.logsItemTag = ItemTags.bind(Treemendous.MODID + ":" + config.registryName() + "_logs");
        tree.woodType = WoodType.register(WoodType.create(config.registryName()));
        BlockBehaviour.Properties logProperties = BlockBehaviour.Properties.of(Material.WOOD, (state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? config.appearance().woodMaterialColor() : config.appearance().barkMaterialColor()).strength(2.0F).sound(SoundType.WOOD);
        BlockBehaviour.Properties woodProperties = BlockBehaviour.Properties.of(Material.WOOD, (state) -> config.appearance().barkMaterialColor()).strength(2.0F).sound(SoundType.WOOD);
        BlockBehaviour.Properties strippedProperties = BlockBehaviour.Properties.of(Material.WOOD, (state) -> config.appearance().woodMaterialColor()).strength(2.0F).sound(SoundType.WOOD);
        BlockBehaviour.Properties planksProperties = BlockBehaviour.Properties.of(Material.WOOD, (state) -> config.appearance().woodMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD);
        tree.planks = blocks.register(getNameForTree(config, "planks"), () -> new Block(planksProperties));
        tree.strippedLog = blocks.register(getNameForTree(config, "stripped", "log"), () -> new RotatedPillarBlock(strippedProperties));
        tree.log = blocks.register(getNameForTree(config, "log"), () -> new StrippableBlock(logProperties, tree.strippedLog::get));
        tree.strippedWood = blocks.register(getNameForTree(config, "stripped", "wood"), () -> new RotatedPillarBlock(strippedProperties));
        tree.wood = blocks.register(getNameForTree(config, "wood"), () -> new StrippableBlock(woodProperties, tree.strippedWood::get));
        tree.stairs = blocks.register(getNameForTree(config, "stairs"), () -> new StairBlock(() -> tree.planks.get().defaultBlockState(), planksProperties));
        tree.slab = blocks.register(getNameForTree(config, "slab"), () -> new SlabBlock(planksProperties));
        tree.pressurePlate = blocks.register(getNameForTree(config, "pressure_plate"), () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, planksProperties));
        tree.button = blocks.register(getNameForTree(config, "button"), () -> new WoodButtonBlock(planksProperties));
        tree.fence = blocks.register(getNameForTree(config, "fence"), () -> new FenceBlock(planksProperties));
        tree.fenceGate = blocks.register(getNameForTree(config, "fence_gate"), () -> new FenceGateBlock(planksProperties));
        tree.door = blocks.register(getNameForTree(config, "door"), () -> new DoorBlock(planksProperties.strength(3.0F).noOcclusion()));
        tree.trapdoor = blocks.register(getNameForTree(config, "trapdoor"), () -> new TrapDoorBlock(planksProperties.strength(3.0F).noOcclusion().isValidSpawn((state, world, pos, entityType) -> false)));
        tree.sapling = blocks.register(getNameForTree(config, "sapling"), () -> new SaplingBlock(new OakTreeGrower(/*TODO*/), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
        tree.pottedSapling = blocks.register(getNameForTree(config, "potted", "sapling"), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, tree.sapling, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
        tree.leaves = blocks.register(getNameForTree(config, "leaves"), () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(Tree::ocelotOrParrot).isSuffocating((state, world, pos) -> false).isViewBlocking((state, world, pos) -> false)));
        tree.sign = blocks.register(getNameForTree(config, "sign"), () -> new CustomStandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), tree.woodType));
        tree.wallSign = blocks.register(getNameForTree(config, "wall_sign"), () -> new CustomWallSignBlock(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), tree.woodType));
        tree.signItem = items.register(getNameForTree(config, "sign"), () -> new SignItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTab.TAB_DECORATIONS), tree.sign.get(), tree.wallSign.get()));
        tree.boatItem = items.register(getNameForTree(config, "boat"), () -> new BoatItem(Boat.Type.OAK /*TODO*/, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION)));
        registerBlockItem(items, getNameForTree(config, "planks"), tree.planks, CreativeModeTab.TAB_BUILDING_BLOCKS);
        registerBlockItem(items, getNameForTree(config, "log"), tree.log, CreativeModeTab.TAB_BUILDING_BLOCKS);
        registerBlockItem(items, getNameForTree(config, "stripped", "log"), tree.strippedLog, CreativeModeTab.TAB_BUILDING_BLOCKS);
        registerBlockItem(items, getNameForTree(config, "wood"), tree.wood, CreativeModeTab.TAB_BUILDING_BLOCKS);
        registerBlockItem(items, getNameForTree(config, "stripped", "wood"), tree.strippedWood, CreativeModeTab.TAB_BUILDING_BLOCKS);
        registerBlockItem(items, getNameForTree(config, "stairs"), tree.stairs, CreativeModeTab.TAB_BUILDING_BLOCKS);
        registerBlockItem(items, getNameForTree(config, "slab"), tree.slab, CreativeModeTab.TAB_BUILDING_BLOCKS);
        registerBlockItem(items, getNameForTree(config, "pressure_plate"), tree.pressurePlate, CreativeModeTab.TAB_REDSTONE);
        registerBlockItem(items, getNameForTree(config, "button"), tree.button, CreativeModeTab.TAB_REDSTONE);
        registerBlockItem(items, getNameForTree(config, "fence"), tree.fence, CreativeModeTab.TAB_BUILDING_BLOCKS);
        registerBlockItem(items, getNameForTree(config, "fence_gate"), tree.fenceGate, CreativeModeTab.TAB_REDSTONE);
        registerBlockItem(items, getNameForTree(config, "door"), tree.door, CreativeModeTab.TAB_REDSTONE);
        registerBlockItem(items, getNameForTree(config, "trapdoor"), tree.trapdoor, CreativeModeTab.TAB_REDSTONE);
        registerBlockItem(items, getNameForTree(config, "sapling"), tree.sapling, CreativeModeTab.TAB_DECORATIONS);
        registerBlockItem(items, getNameForTree(config, "leaves"), tree.leaves, CreativeModeTab.TAB_DECORATIONS);
        return tree;
    }

    private static boolean ocelotOrParrot(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {
        return entityType == EntityType.OCELOT || entityType == EntityType.PARROT;
    }

    private static void registerBlockItem(DeferredRegister<Item> items, String name, RegistryObject<? extends Block> block, CreativeModeTab tab) {
        items.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    private static String getNameForTree(TreeConfig config, String suffix) {
        return String.format("%s_%s", config.registryName(), suffix);
    }

    private static String getNameForTree(TreeConfig config, String prefix, String suffix) {
        return String.format("%s_%s_%s", prefix, config.registryName(), suffix);
    }

    public Block getPlanks() {
        return this.planks.get();
    }

    public StrippableBlock getLog() {
        return this.log.get();
    }

    public RotatedPillarBlock getStrippedLog() {
        return this.strippedLog.get();
    }

    public StrippableBlock getWood() {
        return this.wood.get();
    }

    public RotatedPillarBlock getStrippedWood() {
        return this.strippedWood.get();
    }

    public StairBlock getStairs() {
        return stairs.get();
    }

    public SlabBlock getSlab() {
        return slab.get();
    }

    public FenceBlock getFence() {
        return fence.get();
    }

    public FenceGateBlock getFenceGate() {
        return fenceGate.get();
    }

    public PressurePlateBlock getPressurePlate() {
        return pressurePlate.get();
    }

    public WoodButtonBlock getButton() {
        return button.get();
    }

    public DoorBlock getDoor() {
        return door.get();
    }

    public TrapDoorBlock getTrapdoor() {
        return trapdoor.get();
    }

    public LeavesBlock getLeaves() {
        return leaves.get();
    }

    public StandingSignBlock getSign() {
        return sign.get();
    }

    public WallSignBlock getWallSign() {
        return wallSign.get();
    }

    public SaplingBlock getSapling() {
        return sapling.get();
    }

    public FlowerPotBlock getPottedSapling() {
        return pottedSapling.get();
    }

    public BoatItem getBoatItem() {
        return boatItem.get();
    }

    public SignItem getSignItem() {
        return signItem.get();
    }

    public TreeConfig getConfig() {
        return this.config;
    }

    public WoodType getWoodType() {
        return woodType;
    }

    public Tag.Named<Block> getLogsBlockTag() {
        return logsBlockTag;
    }

    public Tag.Named<Item> getLogsItemTag() {
        return logsItemTag;
    }

}
