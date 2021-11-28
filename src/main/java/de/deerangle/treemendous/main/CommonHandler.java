package de.deerangle.treemendous.main;

import de.deerangle.treemendous.tree.FakeTree;
import de.deerangle.treemendous.tree.RegisteredTree;
import de.deerangle.treemendous.tree.Tree;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Treemendous.MOD_ID)
public class CommonHandler
{

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

    public static RegisteredTree OAK_TREE;
    public static RegisteredTree BIRCH_TREE;
    public static RegisteredTree JUNGLE_TREE;
    public static RegisteredTree SPRUCE_TREE;
    public static RegisteredTree ACACIA_TREE;
    public static RegisteredTree DARK_OAK_TREE;

    @SubscribeEvent
    public static void registerTrees(RegistryEvent.Register<RegisteredTree> event)
    {
        IForgeRegistry<RegisteredTree> registry = event.getRegistry();
        DOUGLAS_TREE = register("douglas", TreeRegistry.DOUGLAS_TREE);
        PINE_TREE = register("pine", TreeRegistry.PINE_TREE);
        LARCH_TREE = register("larch", TreeRegistry.LARCH_TREE);
        FIR_TREE = register("fir", TreeRegistry.FIR_TREE);
        MAPLE_TREE = register("maple", TreeRegistry.MAPLE_TREE);
        JAPANESE_TREE = register("japanese", TreeRegistry.JAPANESE_TREE);
        BEECH_TREE = register("beech", TreeRegistry.BEECH_TREE);
        CHERRY_TREE = register("cherry", TreeRegistry.CHERRY_TREE);
        ALDER_TREE = register("alder", TreeRegistry.ALDER_TREE);
        CHESTNUT_TREE = register("chestnut", TreeRegistry.CHESTNUT_TREE);
        PLANE_TREE = register("plane", TreeRegistry.PLANE_TREE);
        ASH_TREE = register("ash", TreeRegistry.ASH_TREE);
        LINDEN_TREE = register("linden", TreeRegistry.LINDEN_TREE);
        ROBINIA_TREE = register("robinia", TreeRegistry.ROBINIA_TREE);
        WILLOW_TREE = register("willow", TreeRegistry.WILLOW_TREE);
        POMEGRANATE_TREE = register("pomegranate", TreeRegistry.POMEGRANATE_TREE);
        WALNUT_TREE = register("walnut", TreeRegistry.WALNUT_TREE);
        CEDAR_TREE = register("cedar", TreeRegistry.CEDAR_TREE);
        POPLAR_TREE = register("poplar", TreeRegistry.POPLAR_TREE);
        ELM_TREE = register("elm", TreeRegistry.ELM_TREE);
        JUNIPER_TREE = register("juniper", TreeRegistry.JUNIPER_TREE);
        MAGNOLIA_TREE = register("magnolia", TreeRegistry.MAGNOLIA_TREE);
        RAINBOW_EUCALYPTUS_TREE = register("rainbow_eucalyptus", TreeRegistry.RAINBOW_EUCALYPTUS_TREE);
        registry.registerAll(DOUGLAS_TREE, PINE_TREE, LARCH_TREE, FIR_TREE, MAPLE_TREE, JAPANESE_TREE, BEECH_TREE, CHERRY_TREE, ALDER_TREE, CHESTNUT_TREE, PLANE_TREE, ASH_TREE, LINDEN_TREE, ROBINIA_TREE, WILLOW_TREE, POMEGRANATE_TREE, WALNUT_TREE, CEDAR_TREE, POPLAR_TREE, ELM_TREE, JUNIPER_TREE, MAGNOLIA_TREE, RAINBOW_EUCALYPTUS_TREE);

        OAK_TREE = registerFake("oak", TreeRegistry.OAK_TREE);
        BIRCH_TREE = registerFake("birch", TreeRegistry.BIRCH_TREE);
        JUNGLE_TREE = registerFake("jungle", TreeRegistry.JUNGLE_TREE);
        SPRUCE_TREE = registerFake("spruce", TreeRegistry.SPRUCE_TREE);
        ACACIA_TREE = registerFake("acacia", TreeRegistry.ACACIA_TREE);
        DARK_OAK_TREE = registerFake("dark_oak", TreeRegistry.DARK_OAK_TREE);
        registry.registerAll(OAK_TREE, BIRCH_TREE, SPRUCE_TREE, JUNGLE_TREE, ACACIA_TREE, DARK_OAK_TREE);
    }

    private static RegisteredTree register(String name, Tree tree)
    {
        RegisteredTree newTree = new RegisteredTree(tree, false);
        newTree.setRegistryName(new ResourceLocation(Treemendous.MOD_ID, name));
        return newTree;
    }

    private static RegisteredTree registerFake(String name, FakeTree tree)
    {
        RegisteredTree newTree = new RegisteredTree(tree, true);
        newTree.setRegistryName(new ResourceLocation(Treemendous.MOD_ID, name));
        return newTree;
    }

}
