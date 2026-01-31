package cn.mcmod.tinker_rapier.data;

import java.util.concurrent.CompletableFuture;

import cn.mcmod.tinker_rapier.RapierMod;
import cn.mcmod.tinker_rapier.modules.BackpedalModule;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
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
        generator.addProvider(event.includeServer(), new RapierDefinitionDataProvider(packOutput, RapierMod.MODID));
        generator.addProvider(event.includeServer(), new RapierStationSlotLayoutProvider(packOutput));

        var blockTagProvider = new BlockTagsProvider(packOutput, lookupProvider,
                RapierMod.MODID, existingFileHelper) {
				@Override
				protected void addTags(Provider p_256380_) {
				}
        };

        generator.addProvider(event.includeServer(), new RapierModifierTagProvider(packOutput, existingFileHelper));

        generator.addProvider(event.includeServer(), blockTagProvider);
        generator.addProvider(event.includeServer(), new RapierItemTagProvider(packOutput, lookupProvider,
                blockTagProvider.contentsGetter(), existingFileHelper));

        TinkerMaterialSpriteProvider materialSprites = new TinkerMaterialSpriteProvider();
        RapierTextureProvider rapierSprites = new RapierTextureProvider();
        generator.addProvider(event.includeClient(), new MaterialPartTextureGenerator(packOutput, existingFileHelper, rapierSprites, materialSprites));

        generator.addProvider(event.includeServer(), new ModifierProvider(packOutput));
    }

}
