package de.deerangle.treemendous.main;

import de.deerangle.treemendous.item.LumberAxeItem;
import de.deerangle.treemendous.tree.Tree;
import de.deerangle.treemendous.village.TreemendousTrades;
import de.deerangle.treemendous.world.TreemendousConfiguredFeatures;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = Treemendous.MODID)
public class ForgeEventHandler
{

    @SubscribeEvent
    public static void onVillagerTrade(VillagerTradesEvent event)
    {
        if (event.getType() == ExtraRegistry.FOREST_RANGER_PROFESSION.get())
        {
            List<VillagerTrades.ItemListing> tradesLevel1 = event.getTrades().get(1);
            List<VillagerTrades.ItemListing> tradesLevel2 = event.getTrades().get(2);
            List<VillagerTrades.ItemListing> tradesLevel3 = event.getTrades().get(3);
            List<VillagerTrades.ItemListing> tradesLevel4 = event.getTrades().get(4);
            List<VillagerTrades.ItemListing> tradesLevel5 = event.getTrades().get(5);
            tradesLevel1.add(new TreemendousTrades.EmeraldsForTreeItems((tree, rand) -> tree.getLog(), 4, 1, 16, 1));
            tradesLevel1.add(new TreemendousTrades.TreeItemForEmeralds(Tree::getRandomSapling, 1, 1, 12, 1, 0.05f));

            tradesLevel2.add(new TreemendousTrades.EmeraldForRandomItems(ItemTags.FLOWERS.getValues(), 2, 16, 2));
            tradesLevel2.add(new TreemendousTrades.ItemsForEmeralds(ExtraRegistry.IRON_LUMBER_AXE.get(), UniformInt.of(6, 8), 1, 8, 6));

            tradesLevel3.add(new TreemendousTrades.ItemsForEmeralds(Items.BONE_MEAL, ConstantInt.of(1), 5, 16, 2));
            tradesLevel3.add(new TreemendousTrades.EmeraldsForTreeItems((tree, rand) -> tree.getStrippedWood(), 5, 2, 16, 3));

            tradesLevel4.add(new TreemendousTrades.EmeraldForItems(Items.APPLE, 5, 16, 3));
            tradesLevel4.add(new TreemendousTrades.ItemsForEmeralds(ExtraRegistry.DIAMOND_LUMBER_AXE.get(), UniformInt.of(16, 24), 1, 8, 18));

            tradesLevel5.add(new TreemendousTrades.TreasureMapForEmeralds(13, ExtraRegistry.RANGER_HOUSE_FEATURE.get(), MapDecoration.Type.RED_X, 12, 5));
        }
    }

    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event)
    {
        ConfiguredStructureFeature<?, ?> rangerHouse = TreemendousConfiguredFeatures.getRangerHouseForBiome(event.getName());
        if (rangerHouse != null)
        {
            event.getGeneration().addStructureStart(rangerHouse);
        }
    }

    @SubscribeEvent
    public static void onGetBreakSpeed(PlayerEvent.BreakSpeed event)
    {
        if (event.getEntityLiving() instanceof Player player)
        {
            ItemStack toolItem = player.getInventory().getSelected();
            if (toolItem.getItem() instanceof LumberAxeItem lumberAxeItem)
            {
                float speed = lumberAxeItem.calculateSpeed(event.getOriginalSpeed(), player.level, event.getState(), event.getPos());
                event.setNewSpeed(speed);
            }
        }
    }

}
