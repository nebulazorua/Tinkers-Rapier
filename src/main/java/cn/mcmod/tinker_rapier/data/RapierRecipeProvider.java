package cn.mcmod.tinker_rapier.data;

import java.util.function.Consumer;

import cn.mcmod.tinker_rapier.RapierMod;
import cn.mcmod.tinker_rapier.RapierModifiers;
import cn.mcmod.tinker_rapier.item.TiCItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.CompoundIngredient;
import net.minecraftforge.fluids.FluidType;
import slimeknights.mantle.datagen.MantleTags;
import slimeknights.mantle.recipe.ingredient.FluidContainerIngredient;
import slimeknights.mantle.recipe.ingredient.FluidIngredient;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;
import slimeknights.tconstruct.library.modifiers.util.LazyModifier;
import slimeknights.tconstruct.library.recipe.modifiers.adding.ModifierRecipeBuilder;
import slimeknights.tconstruct.library.tools.SlotType;

public class RapierRecipeProvider extends RecipeProvider implements IToolRecipeHelper {


    public RapierRecipeProvider(PackOutput datagen) {
		super(datagen);
	}

    @Override
    public String getModId() {
        return RapierMod.MODID;
    }

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "tools/building/";
        String partFolder = "tools/parts/";
        String castFolder = "smeltery/casts/";
        String abilityFolder = "tools/modifiers/ability/";

        ModifierRecipeBuilder.modifier(RapierModifiers.backpedal)
                .setTools(ingredientFromTags(TinkerTags.Items.MELEE_WEAPON, TinkerTags.Items.HARVEST))
                .addInput(Items.PISTON, 1)
                .addInput(
                        FluidContainerIngredient.fromIngredient(
                                FluidIngredient.of(
                                        MantleTags.Fluids.WATER,
                                        FluidType.BUCKET_VOLUME
                                ), Ingredient.of(Items.WATER_BUCKET)))
                .addInput(Items.PISTON, 1)
                .addInput(Items.PISTON, 1)
                .addInput(Items.PISTON, 1)
                .setMaxLevel(1)
                .setSlots(SlotType.ABILITY, 1)
                .save(consumer, prefix(RapierModifiers.backpedal, abilityFolder));

        partRecipes(consumer, TiCItemRegistry.SLENDER_BLADE, TiCItemRegistry.SLENDER_BLADE_CAST, 6, partFolder, castFolder);

        toolBuilding(consumer, TiCItemRegistry.ESTOC.get(), folder);
	}

    /** Prefixes the modifier ID with the given prefix */
    public ResourceLocation prefix(LazyModifier modifier, String prefix) {
        return prefix(modifier.getId(), prefix);
    }


    /**
     * Creates a compound ingredient from multiple tags
     * @param tags  Tags to use
     * @return  Compound ingredient
     */
    @SafeVarargs
    private static Ingredient ingredientFromTags(TagKey<Item>... tags) {
        Ingredient[] tagIngredients = new Ingredient[tags.length];
        for (int i = 0; i < tags.length; i++) {
            tagIngredients[i] = Ingredient.of(tags[i]);
        }
        return CompoundIngredient.of(tagIngredients);
    }

}
