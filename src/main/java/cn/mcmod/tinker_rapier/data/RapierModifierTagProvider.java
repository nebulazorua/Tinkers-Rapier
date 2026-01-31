package cn.mcmod.tinker_rapier.data;

import cn.mcmod.tinker_rapier.RapierMod;
import cn.mcmod.tinker_rapier.RapierModifiers;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierTagProvider;

import static slimeknights.tconstruct.common.TinkerTags.Modifiers.INTERACTION_ABILITIES;
import static slimeknights.tconstruct.common.TinkerTags.Modifiers.DAMAGE_UPGRADES;

public class RapierModifierTagProvider extends AbstractModifierTagProvider {

    protected RapierModifierTagProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, RapierMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(INTERACTION_ABILITIES).add(RapierModifiers.backpedal);
        //tag(DAMAGE_UPGRADES).add(RapierModifiers.sting);
    }

    @Override
    public String getName() {
        return "Tinkers Rapiers Modifier Tags";
    }
}
