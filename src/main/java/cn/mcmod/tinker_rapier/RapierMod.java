package cn.mcmod.tinker_rapier;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.mcmod.tinker_rapier.item.TiCItemRegistry;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RapierMod.MODID)
public class RapierMod {
    public static final String MODID = "tinker_rapier";
    private static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("removal")
	public RapierMod(FMLJavaModLoadingContext context) {
        IEventBus bus = context.getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RapierConfig.COMMON_CONFIG);
//        ModLoadingContext.get().
        TiCItemRegistry.ITEMS.register(bus);
        RapierCreativeGroup.CREATIVE_MODE_TABS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);

        bus.register(new RapierModifiers(bus));
        //RapierModifiers.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static ResourceLocation getResource(String name) {
        return ResourceLocation.fromNamespaceAndPath(RapierMod.MODID, name);
    }


    public static Item.Properties defaultItemProperties() {
        return new Item.Properties();
    }

    public static Logger getLogger() {
        return LOGGER;
    }

}
