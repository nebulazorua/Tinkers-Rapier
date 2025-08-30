package cn.mcmod.tinker_rapier.data;

import java.util.function.Consumer;

import cn.mcmod.tinker_rapier.RapierMain;
import cn.mcmod.tinker_rapier.item.TiCItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;

public class RapierRecipeProvider extends RecipeProvider implements IToolRecipeHelper {


    public RapierRecipeProvider(PackOutput datagen) {
		super(datagen);
	}

    @Override
    public String getModId() {
        return RapierMain.MODID;
    }

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "tools/building/";
        String partFolder = "tools/parts/";
        String castFolder = "smeltery/casts/";
        partRecipes(consumer, TiCItemRegistry.SLENDER_BLADE, TiCItemRegistry.SLENDER_BLADE_CAST, 6, partFolder, castFolder);
        
        toolBuilding(consumer, TiCItemRegistry.RAPIER.get(), folder);
        toolBuilding(consumer, TiCItemRegistry.ESTOC.get(), folder);
	}

}
