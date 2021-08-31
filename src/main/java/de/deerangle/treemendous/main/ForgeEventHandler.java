package de.deerangle.treemendous.main;

import de.deerangle.treemendous.tree.Tree;
import de.deerangle.treemendous.village.TreemendousTrades;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = Treemendous.MODID)
public class ForgeEventHandler {

    @SubscribeEvent
    public static void onVillagerTrade(VillagerTradesEvent event) {
        if (event.getType() == ExtraRegistry.FOREST_RANGER_PROFESSION.get()) {
            List<VillagerTrades.ItemListing> tradesLevel1 = event.getTrades().get(1);
            List<VillagerTrades.ItemListing> tradesLevel2 = event.getTrades().get(2);
            List<VillagerTrades.ItemListing> tradesLevel3 = event.getTrades().get(3);
            List<VillagerTrades.ItemListing> tradesLevel4 = event.getTrades().get(4);
            List<VillagerTrades.ItemListing> tradesLevel5 = event.getTrades().get(5);
            tradesLevel1.add(new TreemendousTrades.EmeraldForTreeItems((tree, rand) -> tree.getLog(), 4, 16, 1));
            tradesLevel1.add(new TreemendousTrades.TreeItemForEmeralds(Tree::getRandomSapling, 1, 1, 12, 1, 0.05f));
        }
    }

}
