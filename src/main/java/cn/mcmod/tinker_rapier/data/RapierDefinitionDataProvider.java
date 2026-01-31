package cn.mcmod.tinker_rapier.data;

import cn.mcmod.tinker_rapier.RapierModifiers;
import cn.mcmod.tinker_rapier.item.EstocTiC;
import cn.mcmod.tinker_rapier.item.RapierTiC;
import cn.mcmod.tinker_rapier.item.TiCItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ToolActions;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;
import slimeknights.tconstruct.library.materials.RandomMaterial;
import slimeknights.tconstruct.library.tools.definition.module.ToolHooks;
import slimeknights.tconstruct.library.tools.definition.module.ToolModule;
import slimeknights.tconstruct.library.tools.definition.module.build.MultiplyStatsModule;
import slimeknights.tconstruct.library.tools.definition.module.build.SetStatsModule;
import slimeknights.tconstruct.library.tools.definition.module.build.ToolActionsModule;
import slimeknights.tconstruct.library.tools.definition.module.build.ToolTraitsModule;
import slimeknights.tconstruct.library.tools.definition.module.material.DefaultMaterialsModule;
import slimeknights.tconstruct.library.tools.definition.module.material.MaterialTraitsModule;
import slimeknights.tconstruct.library.tools.definition.module.material.PartStatsModule;
import slimeknights.tconstruct.library.tools.definition.module.mining.IsEffectiveModule;
import slimeknights.tconstruct.library.tools.definition.module.mining.MiningSpeedModifierModule;
import slimeknights.tconstruct.library.tools.nbt.MultiplierNBT;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.data.ModifierIds;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

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
		RandomMaterial anyMaterial = RandomMaterial.random().allowHidden().build();

        DefaultMaterialsModule defaultThreeParts = DefaultMaterialsModule.builder()
                .material(tier1Material, tier1Material, tier1Material).build();
        DefaultMaterialsModule ancientFourParts = DefaultMaterialsModule.builder()
                .material(anyMaterial, anyMaterial, anyMaterial, anyMaterial).build();

        ToolModule[] swordHarvest = {IsEffectiveModule.tag(TinkerTags.Blocks.MINABLE_WITH_SWORD),
                MiningSpeedModifierModule.blocks(7.5f, Blocks.COBWEB)};
        define(RapierTiC.RAPIER)
                .module(PartStatsModule.stats()
							.stat(HeadMaterialStats.ID)
							.stat(HeadMaterialStats.ID)
							.stat(HandleMaterialStats.ID)
							.stat(HandleMaterialStats.ID)

                        .build()
                )
                .module(ancientFourParts)

                // stats
                .module(new SetStatsModule(StatsNBT.builder()
                        .set(ToolStats.ATTACK_DAMAGE, 1f)
                        .set(ToolStats.ATTACK_SPEED, 3f).build())
                )
				// ancient tools add a second copy of traits, and add both traits to rebalanced
				.module(new MaterialTraitsModule(HeadMaterialStats.ID, 0), ToolHooks.REBALANCED_TRAIT)

                .module(new MultiplyStatsModule(
                        MultiplierNBT.builder()
                                .set(ToolStats.ATTACK_DAMAGE, 0.75f)
                                .set(ToolStats.DURABILITY, 0.75F)
                                .build())
                )

                .largeToolStartingSlots()

                .module(ToolTraitsModule.builder().trait(RapierModifiers.backpedal).trait(RapierModifiers.sting).trait(TinkerModifiers.silkyShears).build())
                .module(ToolActionsModule.of(ToolActions.SWORD_DIG)).module(swordHarvest);

        define(EstocTiC.ESTOC)
                // parts
                .module(PartStatsModule.parts()
                        .part(TiCItemRegistry.SLENDER_BLADE)
                        .part(TinkerToolParts.toughHandle)
                        .part(TinkerToolParts.toolHandle).build())
                .module(defaultThreeParts)
                // stats
                .module(new SetStatsModule(StatsNBT.builder()
                        .set(ToolStats.ATTACK_SPEED, 1.8f)
                        .build()
                ))
                .module(new MultiplyStatsModule(MultiplierNBT.builder().set(ToolStats.MINING_SPEED, 0.75f).build()))
                .smallToolStartingSlots()
                // traits
                .module(ToolTraitsModule.builder().trait(RapierModifiers.backpedal).trait(ModifierIds.pierce, 2).trait(TinkerModifiers.silkyShears).build())
                .module(ToolActionsModule.of(ToolActions.SWORD_DIG))
                // behavior
                .module(swordHarvest);
    }

}
