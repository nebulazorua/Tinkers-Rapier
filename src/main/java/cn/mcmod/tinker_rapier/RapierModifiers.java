package cn.mcmod.tinker_rapier;

import cn.mcmod.tinker_rapier.modules.BackpedalModule;
import cn.mcmod.tinker_rapier.modules.StingModule;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import slimeknights.mantle.registration.deferred.SynchronizedDeferredRegister;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;

public class RapierModifiers {
    private static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(RapierMod.MODID);
    public static SynchronizedDeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = SynchronizedDeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, RapierMod.MODID);

    public static final ModifierId backpedal = id("backpedaling");
    public static final ModifierId sting = id("stinging");


    public RapierModifiers(IEventBus bus)
    {
        RECIPE_SERIALIZER.register(bus);
        MODIFIERS.register(bus);
    }

    @SubscribeEvent
    void registerSerializers(RegisterEvent event) {
        if (event.getRegistryKey() == Registries.RECIPE_SERIALIZER) {
            ModifierModule.LOADER.register(RapierMod.getResource("backpedal"), BackpedalModule.LOADER);
            ModifierModule.LOADER.register(RapierMod.getResource("sting"), StingModule.LOADER);
        }
    }

    private static ModifierId id(String name){
        return new ModifierId(RapierMod.MODID, name);
    }


}
