package cn.mcmod.tinker_rapier.data;


import cn.mcmod.tinker_rapier.RapierMod;
import cn.mcmod.tinker_rapier.RapierModifiers;
import cn.mcmod.tinker_rapier.modules.BackpedalModule;
import cn.mcmod.tinker_rapier.modules.StingModule;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;

public class ModifierProvider extends AbstractModifierProvider implements IConditionBuilder {
    public ModifierProvider(PackOutput packOutput) {
        super(packOutput);
    }


    @Override
    protected void addModifiers() {
        buildModifier(RapierModifiers.backpedal)
                .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .priority(120)
                .addModule(BackpedalModule.INSTANCE)
                .build();

        buildModifier(RapierModifiers.sting)
                .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .addModule(StingModule.INSTANCE)
                .build();
    }



    @Override
    public String getName() {
        return "TIC Rapier Modifiers";
    }


}
