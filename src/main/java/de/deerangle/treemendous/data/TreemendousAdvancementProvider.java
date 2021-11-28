package de.deerangle.treemendous.data;

import de.deerangle.treemendous.main.ExtraRegistry;
import de.deerangle.treemendous.main.Treemendous;
import de.deerangle.treemendous.world.Biomes;
import de.deerangle.treemendous.world.TreemendousBiomes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.LocationTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class TreemendousAdvancementProvider extends AdvancementProvider
{

    public TreemendousAdvancementProvider(DataGenerator generatorIn, ExistingFileHelper fileHelper)
    {
        super(generatorIn, fileHelper);
    }

    private static Advancement.Builder addBiomes(Advancement.Builder builder, Map<String, ArrayList<String>> requirements)
    {
        for (TreemendousBiomes.RegisteredBiome biome : Biomes.getAllBiomes())
        {
            if (!requirements.containsKey(biome.getTree().getRegistryName()))
            {
                requirements.put(biome.getTree().getRegistryName(), new ArrayList<>());
            }
            requirements.get(biome.getTree().getRegistryName()).add(biome.getBiomeKey().location().toString());
            builder.addCriterion(biome.getBiomeKey().location().toString(), LocationTrigger.TriggerInstance.located(LocationPredicate.inBiome(biome.getBiomeKey())));
        }
        return builder;
    }

    @Override
    protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper)
    {
        Map<String, ArrayList<String>> requirements = new HashMap<>();
        Advancement.Builder builder = addBiomes(Advancement.Builder.advancement(), requirements);
        String[][] requirementsArrays = requirements.values().stream().map(x -> x.toArray(new String[0])).toArray(String[][]::new);
        builder.parent(new ResourceLocation("adventure/root"))
                .display(
                        ExtraRegistry.IRON_LUMBER_AXE.get(),
                        new TranslatableComponent("advancements." + Treemendous.MOD_ID + ".explore_forests.title"),
                        new TranslatableComponent("advancements." + Treemendous.MOD_ID + ".explore_forests.description"),
                        null, FrameType.CHALLENGE, true, true, false)
                .requirements(requirementsArrays)
                .rewards(AdvancementRewards.Builder.experience(500)).save(consumer, new ResourceLocation(Treemendous.MOD_ID, "explore_forests"), fileHelper);
    }

}
