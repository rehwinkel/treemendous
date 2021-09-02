package de.deerangle.treemendous.world;

import de.deerangle.treemendous.main.CommonHandler;
import de.deerangle.treemendous.main.ExtraRegistry;
import de.deerangle.treemendous.main.Treemendous;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;

public class TreemendousConfiguredFeatures {

    public static ConfiguredStructureFeature<RangerHouseConfiguration, ? extends StructureFeature<RangerHouseConfiguration>> SPRUCE_RANGER_HOUSE = ExtraRegistry.RANGER_HOUSE_FEATURE.get().configured(new RangerHouseConfiguration(CommonHandler.SPRUCE_TREE));

    public static void registerConfiguredStructures() {
        BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new ResourceLocation(Treemendous.MODID, "spruce_ranger_house"), SPRUCE_RANGER_HOUSE);

        /* Ok so, this part may be hard to grasp but basically, just add your structure to this to
         * prevent any sort of crash or issue with other mod's custom ChunkGenerators. If they use
         * FlatGenerationSettings.STRUCTURE_FEATURES in it and you don't add your structure to it, the game
         * could crash later when you attempt to add the StructureSeparationSettings to the dimension.
         *
         * (It would also crash with superflat worldtype if you omit the below line
         * and attempt to add the structure's StructureSeparationSettings to the world)
         *
         * Note: If you want your structure to spawn in superflat, remove the FlatChunkGenerator check
         * in StructureTutorialMain.addDimensionalSpacing and then create a superflat world, exit it,
         * and re-enter it and your structures will be spawning. I could not figure out why it needs
         * the restart but honestly, superflat is really buggy and shouldn't be your main focus in my opinion.
         *
         * Requires AccessTransformer ( see resources/META-INF/accesstransformer.cfg )
         */
        //FlatGenerationSettings.STRUCTURE_FEATURES.put(STStructures.RUN_DOWN_HOUSE.get(), CONFIGURED_RUN_DOWN_HOUSE);
    }

}
