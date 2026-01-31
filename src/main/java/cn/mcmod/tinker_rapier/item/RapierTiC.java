package cn.mcmod.tinker_rapier.item;

import cn.mcmod.tinker_rapier.RapierMod;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.tools.item.ModifiableSwordItem;

public class RapierTiC extends ModifiableSwordItem {

    public static final ToolDefinition RAPIER = ToolDefinition.create(TiCItemRegistry.RAPIER);

    public RapierTiC() {
        super(RapierMod.defaultItemProperties().stacksTo(1), RAPIER);
    }

/*    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        super.use(level, player, hand);
        return RapierUtil.useRapier(level, player, hand);
    }
    
    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (!(entity instanceof LivingEntity))
            return super.onLeftClickEntity(stack, player, entity);
        LivingEntity target = (LivingEntity) entity;
        float baseDamage = ToolAttackUtil.getAttributeAttackDamage(ToolStack.from(stack), player, EquipmentSlot.MAINHAND);
        RapierUtil.DoStingAttack(stack, (baseDamage * 0.75F), baseDamage, player, target);
        return super.onLeftClickEntity(stack, player, entity);
    }*/

}
