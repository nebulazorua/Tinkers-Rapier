package cn.mcmod.tinker_rapier.data;

import java.util.concurrent.CompletableFuture;

import cn.mcmod.tinker_rapier.RapierMain;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.tools.data.sprite.TinkerMaterialSpriteProvider;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
    	DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<Provider> lookupProvider = event.getLookupProvider();

        PackOutput packOutput = generator.getPackOutput();
        generator.addProvider(event.includeServer(), new RapierRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), new RapierDefinitionDataProvider(packOutput, RapierMain.MODID));
        generator.addProvider(event.includeServer(), new RapierStationSlotLayoutProvider(packOutput));
        
        var blockTagProvider = new BlockTagsProvider(packOutput, lookupProvider,
                RapierMain.MODID, existingFileHelper) {
				@Override
				protected void addTags(Provider p_256380_) {
				}
        };
        generator.addProvider(event.includeServer(), blockTagProvider);
        generator.addProvider(event.includeServer(), new RapierItemTagProvider(packOutput, lookupProvider,
                blockTagProvider.contentsGetter(), existingFileHelper));

        TinkerMaterialSpriteProvider materialSprites = new TinkerMaterialSpriteProvider();
        RapierTextureProvider rapierSprites = new RapierTextureProvider();
        generator.addProvider(event.includeClient(), new MaterialPartTextureGenerator(packOutput, existingFileHelper, rapierSprites, materialSprites));
        
    }
}
