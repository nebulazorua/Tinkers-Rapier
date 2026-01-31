package cn.mcmod.tinker_rapier.item;

import cn.mcmod.tinker_rapier.RapierMod;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.tools.item.ModifiableSwordItem;

public class EstocTiC extends ModifiableSwordItem {

    public static final ToolDefinition ESTOC = ToolDefinition.create(TiCItemRegistry.ESTOC);

    public EstocTiC() {
        super(RapierMod.defaultItemProperties().stacksTo(1), ESTOC);
    }

/*    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        super.use(level, player, hand);
        return RapierUtil.useRapier(level, player, hand);
    }*/

}
