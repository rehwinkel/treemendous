package de.deerangle.treemendous.main;

import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.Tree;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Treemendous.MODID)
public class CommonHandler {

    public static RegisteredTree DOUGLAS_TREE;
    public static RegisteredTree PINE_TREE;
    public static RegisteredTree LARCH_TREE;
    public static RegisteredTree FIR_TREE;
    public static RegisteredTree MAPLE_TREE;
    public static RegisteredTree JAPANESE_TREE;
    public static RegisteredTree BEECH_TREE;
    public static RegisteredTree CHERRY_TREE;
    public static RegisteredTree ALDER_TREE;
    public static RegisteredTree CHESTNUT_TREE;
    public static RegisteredTree PLANE_TREE;
    public static RegisteredTree ASH_TREE;
    public static RegisteredTree LINDEN_TREE;
    public static RegisteredTree ROBINIA_TREE;
    public static RegisteredTree WILLOW_TREE;
    public static RegisteredTree POMEGRANATE_TREE;
    public static RegisteredTree WALNUT_TREE;
    public static RegisteredTree CEDAR_TREE;
    public static RegisteredTree POPLAR_TREE;
    public static RegisteredTree ELM_TREE;
    public static RegisteredTree JUNIPER_TREE;
    public static RegisteredTree MAGNOLIA_TREE;
    public static RegisteredTree RAINBOW_EUCALYPTUS_TREE;

    @SubscribeEvent
    public static void registerTrees(RegistryEvent.Register<RegisteredTree> event) {
        IForgeRegistry<RegisteredTree> registry = event.getRegistry();
        DOUGLAS_TREE = register(TreeRegistry.DOUGLAS_TREE);
        PINE_TREE = register(TreeRegistry.PINE_TREE);
        LARCH_TREE = register(TreeRegistry.LARCH_TREE);
        FIR_TREE = register(TreeRegistry.FIR_TREE);
        MAPLE_TREE = register(TreeRegistry.MAPLE_TREE);
        JAPANESE_TREE = register(TreeRegistry.JAPANESE_TREE);
        BEECH_TREE = register(TreeRegistry.BEECH_TREE);
        CHERRY_TREE = register(TreeRegistry.CHERRY_TREE);
        ALDER_TREE = register(TreeRegistry.ALDER_TREE);
        CHESTNUT_TREE = register(TreeRegistry.CHESTNUT_TREE);
        PLANE_TREE = register(TreeRegistry.PLANE_TREE);
        ASH_TREE = register(TreeRegistry.ASH_TREE);
        LINDEN_TREE = register(TreeRegistry.LINDEN_TREE);
        ROBINIA_TREE = register(TreeRegistry.ROBINIA_TREE);
        WILLOW_TREE = register(TreeRegistry.WILLOW_TREE);
        POMEGRANATE_TREE = register(TreeRegistry.POMEGRANATE_TREE);
        WALNUT_TREE = register(TreeRegistry.WALNUT_TREE);
        CEDAR_TREE = register(TreeRegistry.CEDAR_TREE);
        POPLAR_TREE = register(TreeRegistry.POPLAR_TREE);
        ELM_TREE = register(TreeRegistry.ELM_TREE);
        JUNIPER_TREE = register(TreeRegistry.JUNIPER_TREE);
        MAGNOLIA_TREE = register(TreeRegistry.MAGNOLIA_TREE);
        RAINBOW_EUCALYPTUS_TREE = register(TreeRegistry.RAINBOW_EUCALYPTUS_TREE);
        registry.registerAll(DOUGLAS_TREE, PINE_TREE, LARCH_TREE, FIR_TREE, MAPLE_TREE, JAPANESE_TREE, BEECH_TREE, CHERRY_TREE, ALDER_TREE, CHESTNUT_TREE, PLANE_TREE, ASH_TREE, LINDEN_TREE, ROBINIA_TREE, WILLOW_TREE, POMEGRANATE_TREE, WALNUT_TREE, CEDAR_TREE, POPLAR_TREE, ELM_TREE, JUNIPER_TREE, MAGNOLIA_TREE, RAINBOW_EUCALYPTUS_TREE);
    }

    private static RegisteredTree register(Tree tree) {
        RegisteredTree newTree = new RegisteredTree(tree);
        newTree.setRegistryName(new ResourceLocation(Treemendous.MODID, tree.getConfig().registryName()));
        return newTree;
    }

}
