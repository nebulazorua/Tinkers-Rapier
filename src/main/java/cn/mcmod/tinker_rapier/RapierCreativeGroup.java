package cn.mcmod.tinker_rapier;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import cn.mcmod.tinker_rapier.item.TiCItemRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.library.tools.helper.ToolBuildHandler;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.part.IMaterialItem;
import slimeknights.tconstruct.tools.item.ModifierCrystalItem;

public class RapierCreativeGroup {

	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
			.create(Registries.CREATIVE_MODE_TAB, RapierMod.MODID);

	public static final RegistryObject<CreativeModeTab> ITEMS = CREATIVE_MODE_TABS.register("umapyoi",
			() -> CreativeModeTab.builder().icon(() -> TiCItemRegistry.RAPIER.get().getRenderTool())
					.title(Component.translatable("itemGroup.tinker_rapier"))
					.displayItems(RapierCreativeGroup::addTabItems).build());

	/** Adds all relevant items to the creative tab */
	private static void addTabItems(ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output tab) {
		// start with tools that lack materials
		Consumer<ItemStack> output = tab::accept;
		acceptPart(output, TiCItemRegistry.SLENDER_BLADE);
		acceptCast(tab, CastItemObject::get, TiCItemRegistry.SLENDER_BLADE_CAST);
		acceptCast(tab, CastItemObject::getSand, TiCItemRegistry.SLENDER_BLADE_CAST);
		acceptCast(tab, CastItemObject::getRedSand, TiCItemRegistry.SLENDER_BLADE_CAST);
		acceptTool(output, TiCItemRegistry.ESTOC);
		acceptTool(output, TiCItemRegistry.RAPIER);

	}

	/** Adds a tool part to the tab */
	private static void acceptPart(Consumer<ItemStack> output, Supplier<? extends IMaterialItem> item) {
		item.get().addVariants(output, "");
	}

	/** Adds a cast to the tab */
	private static void acceptCast(CreativeModeTab.Output output, Function<CastItemObject, ItemLike> getter,
			CastItemObject cast) {
		output.accept(getter.apply(cast));
	}

	/** Adds a tool to the tab */
	private static void acceptTool(Consumer<ItemStack> output, Supplier<? extends IModifiable> tool) {
		ToolBuildHandler.addVariants(output, tool.get(), "");
	}
}
