package cn.mcmod.tinker_rapier.modules;

import cn.mcmod.tinker_rapier.RapierModifiers;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.mantle.data.registry.GenericLoaderRegistry;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolActionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public enum BackpedalModule implements ModifierModule, GeneralInteractionModifierHook {
    INSTANCE;
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.<BackpedalModule>defaultHooks(ModifierHooks.GENERAL_INTERACT);
    public static final RecordLoadable<BackpedalModule> LOADER = new SingletonLoader<>(INSTANCE);

    public @NotNull RecordLoadable<BackpedalModule> getLoader() {
        return LOADER;
    }

    @Override
    public Integer getPriority() {
        return 120; // HOPEFULLY it goes above tasty now. HOPEFULLY.
    }

    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }


    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source) {
        if(source == InteractionSource.RIGHT_CLICK && !tool.isBroken()){
            if(player.getFoodData().getFoodLevel() > 2 && player.onGround()) {
                float f = 1f;
                float motionY = 0.33f;

                player.causeFoodExhaustion(1f);


                float motionX = Mth.sin(player.getYRot() / 180.0F * (float) Math.PI)
                        * Mth.cos(player.getXRot() / 180.0F * (float) Math.PI) * f;
                float motionZ = -Mth.cos(player.getYRot() / 180.0F * (float) Math.PI)
                        * Mth.cos(player.getXRot() / 180.0F * (float) Math.PI) * f;
                player.setDeltaMovement(player.getDeltaMovement().x + motionX, motionY, player.getDeltaMovement().z + motionZ);

                //player.getCooldowns().addCooldown(tool.getItem(), 5);

                return InteractionResult.CONSUME;
            }else
                return InteractionResult.FAIL;
        }
        return InteractionResult.PASS;
    }

}
