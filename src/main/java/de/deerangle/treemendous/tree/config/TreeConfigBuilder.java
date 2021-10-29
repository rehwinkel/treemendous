package de.deerangle.treemendous.tree.config;

import de.deerangle.treemendous.tree.Tree;
import de.deerangle.treemendous.tree.util.FixedLeavesColor;
import de.deerangle.treemendous.tree.util.ILeavesColor;
import de.deerangle.treemendous.util.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.MaterialColor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class TreeConfigBuilder
{

    private final String registryName;
    private final ILeavesColor leavesColor;
    private final MaterialColor woodColor;
    private final MaterialColor barkColor;
    private final List<SaplingConfig> saplingConfigs;
    private Supplier<Item> appleItem;

    public TreeConfigBuilder(String registryName, ILeavesColor leavesColor, MaterialColor woodColor, MaterialColor barkColor)
    {
        this.registryName = registryName;
        this.saplingConfigs = new ArrayList<>();
        this.leavesColor = leavesColor;
        this.woodColor = woodColor;
        this.barkColor = barkColor;
        this.appleItem = () -> null;
    }

    public TreeConfigBuilder(String registryName, int leavesColor, boolean evergreen, int woodColor, int barkColor)
    {
        this(registryName, new FixedLeavesColor(leavesColor, !evergreen), Util.getMaterialColor(woodColor), Util.getMaterialColor(barkColor));
    }

    public TreeConfigBuilder setAppleItem(Supplier<Item> appleItem)
    {
        this.appleItem = appleItem;
        return this;
    }

    void addSapling(SaplingConfig saplingConfig)
    {
        this.saplingConfigs.add(saplingConfig);
    }

    public TreeConfig createTreeConfig()
    {
        return new TreeConfig(registryName, leavesColor, appleItem, woodColor, barkColor, saplingConfigs);
    }

    public SaplingConfigBuilder defaultSapling()
    {
        SaplingConfigBuilder builder = new SaplingConfigBuilder(this, null);
        builder.setLeaves(Tree::getLeaves);
        builder.setWood(Tree::getLog);
        builder.setSapling(tree -> tree.getSapling(null));
        return builder;
    }

    public SaplingConfigBuilder extraSapling(String variantName)
    {
        SaplingConfigBuilder builder = new SaplingConfigBuilder(this, variantName);
        builder.setLeaves(Tree::getLeaves);
        builder.setWood(Tree::getLog);
        builder.setSapling(tree -> tree.getSapling(variantName));
        return builder;
    }

}