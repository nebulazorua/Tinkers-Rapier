package cn.mcmod.tinker_rapier.data;

import cn.mcmod.tinker_rapier.item.EstocTiC;
import cn.mcmod.tinker_rapier.item.RapierTiC;
import cn.mcmod.tinker_rapier.item.TiCItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ToolActions;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;
import slimeknights.tconstruct.library.materials.RandomMaterial;
import slimeknights.tconstruct.library.tools.definition.module.ToolModule;
import slimeknights.tconstruct.library.tools.definition.module.build.MultiplyStatsModule;
import slimeknights.tconstruct.library.tools.definition.module.build.SetStatsModule;
import slimeknights.tconstruct.library.tools.definition.module.build.ToolActionsModule;
import slimeknights.tconstruct.library.tools.definition.module.build.ToolTraitsModule;
import slimeknights.tconstruct.library.tools.definition.module.material.DefaultMaterialsModule;
import slimeknights.tconstruct.library.tools.definition.module.material.PartStatsModule;
import slimeknights.tconstruct.library.tools.definition.module.mining.IsEffectiveModule;
import slimeknights.tconstruct.library.tools.definition.module.mining.MiningSpeedModifierModule;
import slimeknights.tconstruct.library.tools.nbt.MultiplierNBT;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.TinkerToolParts;

public class RapierDefinitionDataProvider extends AbstractToolDefinitionDataProvider {
	public RapierDefinitionDataProvider(PackOutput packOutput, String modId) {
		super(packOutput, modId);
	}

	@Override
	public String getName() {
		return "Tinker's Rapier Tool Definition";
	}

	@Override
	protected void addToolDefinitions() {
		RandomMaterial tier1Material = RandomMaterial.random().tier(1).build();
		DefaultMaterialsModule defaultThreeParts = DefaultMaterialsModule.builder()
				.material(tier1Material, tier1Material, tier1Material).build();
		DefaultMaterialsModule defaultFourParts = DefaultMaterialsModule.builder()
				.material(tier1Material, tier1Material, tier1Material, tier1Material).build();
		ToolModule[] swordHarvest = { IsEffectiveModule.tag(TinkerTags.Blocks.MINABLE_WITH_SWORD),
				MiningSpeedModifierModule.blocks(7.5f, Blocks.COBWEB) };
		define(RapierTiC.RAPIER)
				.module(PartStatsModule.parts()
						.part(TiCItemRegistry.SLENDER_BLADE)
						.part(TinkerToolParts.largePlate)
						.part(TinkerToolParts.toughHandle)
						.part(TinkerToolParts.toolHandle)
						.build()
						)
				.module(defaultFourParts)
				
				// stats
				.module(new SetStatsModule(StatsNBT.builder()
						.set(ToolStats.ATTACK_DAMAGE, 1.5f)
						.set(ToolStats.ATTACK_SPEED, 2.75f).build())
						)
				.module(new MultiplyStatsModule(
						MultiplierNBT.builder()
						.set(ToolStats.MINING_SPEED, 0.75f)
						.set(ToolStats.DURABILITY, 0.75F)
						.build())
						)
				.largeToolStartingSlots()
				
				.module(ToolTraitsModule.builder().trait(TinkerModifiers.silkyShears).build())
				.module(ToolActionsModule.of(ToolActions.SWORD_DIG)).module(swordHarvest);

		define(EstocTiC.ESTOC)
				// parts
				.module(PartStatsModule.parts()
						.part(TiCItemRegistry.SLENDER_BLADE)
						.part(TinkerToolParts.toughHandle)
						.part(TinkerToolParts.toolHandle).build())
				.module(defaultThreeParts)
				// stats
				.module(new SetStatsModule(StatsNBT.builder().set(ToolStats.ATTACK_DAMAGE, 2.5f)
						.set(ToolStats.ATTACK_SPEED, 1.8f).build()))
				.module(new MultiplyStatsModule(MultiplierNBT.builder().set(ToolStats.MINING_SPEED, 0.75f)
						.set(ToolStats.DURABILITY, 1.0f).build()))
				.smallToolStartingSlots()
				// traits
				.module(ToolTraitsModule.builder().trait(TinkerModifiers.silkyShears).trait(TinkerModifiers.piercing).build())
				.module(ToolActionsModule.of(ToolActions.SWORD_DIG))
				// behavior
				.module(swordHarvest);
	}

}
