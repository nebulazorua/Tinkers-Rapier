package cn.mcmod.tinker_rapier.data;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import cn.mcmod.tinker_rapier.RapierMod;
import cn.mcmod.tinker_rapier.item.TiCItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.common.registration.CastItemObject;

import static slimeknights.tconstruct.common.TinkerTags.Items.*;

public class RapierItemTagProvider extends ItemTagsProvider {

    public RapierItemTagProvider(PackOutput pGenerator, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> provider,
            ExistingFileHelper existingFileHelper) {
		super(pGenerator, lookupProvider, provider, RapierMod.MODID, existingFileHelper);
	}

    @Override
    protected void addTags(HolderLookup.Provider lookup) {
		tag(TinkerTags.Items.TOOL_PARTS).add(TiCItemRegistry.SLENDER_BLADE.get());

	    // tag each type of cast
	    IntrinsicTagAppender<Item> goldCasts = this.tag(TinkerTags.Items.GOLD_CASTS);
	    IntrinsicTagAppender<Item> sandCasts = this.tag(TinkerTags.Items.SAND_CASTS);
	    IntrinsicTagAppender<Item> redSandCasts = this.tag(TinkerTags.Items.RED_SAND_CASTS);
	    IntrinsicTagAppender<Item> singleUseCasts = this.tag(TinkerTags.Items.SINGLE_USE_CASTS);
	    IntrinsicTagAppender<Item> multiUseCasts = this.tag(TinkerTags.Items.MULTI_USE_CASTS);
	    Consumer<CastItemObject> addCast = cast -> {
	      // tag based on material
	      goldCasts.add(cast.get());
	      sandCasts.add(cast.getSand());
	      redSandCasts.add(cast.getRedSand());
	      // tag based on usage
	      singleUseCasts.addTag(cast.getSingleUseTag());
	      this.tag(cast.getSingleUseTag()).add(cast.getSand(), cast.getRedSand());
	      multiUseCasts.addTag(cast.getMultiUseTag());
	      this.tag(cast.getMultiUseTag()).add(cast.get());
	    };
	    addCast.accept(TiCItemRegistry.SLENDER_BLADE_CAST);

		addToolTags(TiCItemRegistry.ESTOC.get(), MULTIPART_TOOL, DURABILITY, HARVEST, MELEE_PRIMARY, INTERACTABLE_RIGHT,
				SMALL_TOOLS, BONUS_SLOTS, ItemTags.SWORDS, UNSALVAGABLE);
		
		addToolTags(TiCItemRegistry.RAPIER.get(), MULTIPART_TOOL, DURABILITY, HARVEST, MELEE_PRIMARY, INTERACTABLE_RIGHT,
				ANCIENT_TOOLS, BONUS_SLOTS, ItemTags.SWORDS, UNSALVAGABLE);
		
	}

	@SafeVarargs
	private void addToolTags(ItemLike tool, TagKey<Item>... tags) {
		Item item = tool.asItem();
		
		for (TagKey<Item> tag : tags) {
			this.tag(tag).add(item);
		}
		
	}
}
