package cn.mcmod.tinker_rapier.modules;

import cn.mcmod.tinker_rapier.RapierConfig;
import cn.mcmod.tinker_rapier.RapierMod;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.tconstruct.common.TinkerDamageTypes;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;
import java.util.Objects;

// TODo: give this JSON vars

public enum StingModule implements ModifierModule, MeleeDamageModifierHook, MeleeHitModifierHook {
    INSTANCE;
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.<StingModule>defaultHooks(ModifierHooks.MELEE_DAMAGE, ModifierHooks.MELEE_HIT);
    public static final RecordLoadable<StingModule> LOADER = new SingletonLoader<>(INSTANCE);

    public @NotNull RecordLoadable<StingModule> getLoader() {
        return LOADER;
    }

    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        if(context.isFullyCharged() && context.getLivingTarget() != null)
            return damage * 0.5f; // TODO: make this % able to be set in the tool definition

        return damage;
    }

    @Override
    public float beforeMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damage, float baseKnockback, float knockback) {
        if(context.isFullyCharged() && context.getLivingTarget() != null){
            DamageSource source = TinkerDamageTypes.source(
                    context.getLevel().registryAccess(),
                    TinkerDamageTypes.PIERCING,
                    Objects.requireNonNullElse(context.getPlayerAttacker(), context.getAttacker()));

            float newDamage = context.getBaseDamage() * 0.5f; // TODO: make this % able to be set in the tool definition
            RapierMod.getLogger().info("dealing {} sting damage", newDamage);
            ToolAttackUtil.attackEntitySecondary(source, newDamage, context.getTarget(), context.getLivingTarget(), true);

            LivingEntity target = context.getLivingTarget();

            context.getLevel().playSound(null, target, SoundEvents.PLAYER_HURT_SWEET_BERRY_BUSH, target.getSoundSource(),
                    0.5F,
                    (target.getRandom().nextFloat() - target.getRandom().nextFloat()) * 0.5F + 1.0F);

        }

        return knockback;
    }


/*    LivingEntity target = (LivingEntity) entity;
    float baseDamage = ToolAttackUtil.getAttributeAttackDamage(ToolStack.from(stack), player, EquipmentSlot.MAINHAND);
        RapierUtil.DoStingAttack(stack, (baseDamage * 0.75F), baseDamage, player, target);*/

}
