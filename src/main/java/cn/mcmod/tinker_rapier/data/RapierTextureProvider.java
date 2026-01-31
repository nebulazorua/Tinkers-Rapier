package cn.mcmod.tinker_rapier.data;

import cn.mcmod.tinker_rapier.RapierMod;
import slimeknights.tconstruct.library.client.data.material.AbstractPartSpriteProvider;

public class RapierTextureProvider extends AbstractPartSpriteProvider{

    public RapierTextureProvider() {
        super(RapierMod.MODID);
    }

    @Override
    protected void addAllSpites() {
        addHead("slender_blade");
        
        buildTool("rapier").addBreakableHead("blade").addBinding("guard_cup").addHandle("guard").addHandle("handle");
        buildTool("estoc").addBreakableHead("blade").addHandle("guard_estoc").addHandle("handle");
    }

    @Override
    public String getName() {
        return "Tinker's Rapier Tool Texture";
    }

}
