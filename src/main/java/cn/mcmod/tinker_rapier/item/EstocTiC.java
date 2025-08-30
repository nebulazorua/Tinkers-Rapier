package cn.mcmod.tinker_rapier.item;

import cn.mcmod.tinker_rapier.RapierMain;
import cn.mcmod.tinker_rapier.RapierUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.tools.item.ModifiableSwordItem;

public class EstocTiC extends ModifiableSwordItem {

    public static final ToolDefinition ESTOC = ToolDefinition.create(TiCItemRegistry.ESTOC);

    public EstocTiC() {
        super(RapierMain.defaultItemProperties().stacksTo(1), ESTOC);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        super.use(level, player, hand);
        return RapierUtil.useRapier(level, player, hand);
    }

}
