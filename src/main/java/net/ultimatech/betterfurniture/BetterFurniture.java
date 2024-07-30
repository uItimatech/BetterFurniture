package net.ultimatech.betterfurniture;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.ultimatech.betterfurniture.block.BFBlocks;
import net.ultimatech.betterfurniture.config.BFConfig;
import net.ultimatech.betterfurniture.entity.BFEntityType;
import net.ultimatech.betterfurniture.tab.BFCreativeTabs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterFurniture implements ModInitializer {

	public static final String MOD_ID = "betterfurniture";
	public static final Logger LOGGER = LoggerFactory.getLogger("betterfurniture");

	@Override
	public void onInitialize() {

		// --- Dependencies --- //

		if (!BFDependencyManager.isYacl3Installed()) LOGGER.warn("Yacl3 is not installed. " + MOD_ID + " config will not be available.");

		// --- Config --- //

		BFConfig.save();
		BFConfig.load();

		// --- Global registries --- //

		BFBlocks.registerModBlocks();

		BFCreativeTabs.registerItemGroups();

		BFEntityType.register();



		// --- Block specific registers --- //
		
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.OAK_TABLE, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.SPRUCE_TABLE, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.BIRCH_TABLE, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.JUNGLE_TABLE, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.ACACIA_TABLE, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.DARK_OAK_TABLE, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.CRIMSON_TABLE, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.WARPED_TABLE, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.MANGROVE_TABLE, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.CHERRY_TABLE, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.FURLED_TABLE, 5, 5);

		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.OAK_CHAIR, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.SPRUCE_CHAIR, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.BIRCH_CHAIR, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.JUNGLE_CHAIR, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.ACACIA_CHAIR, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.DARK_OAK_CHAIR, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.CRIMSON_CHAIR, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.WARPED_CHAIR, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.MANGROVE_CHAIR, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.CHERRY_CHAIR, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.FURLED_CHAIR, 5, 5);

		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.OAK_STOOL, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.SPRUCE_STOOL, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.BIRCH_STOOL, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.JUNGLE_STOOL, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.ACACIA_STOOL, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.DARK_OAK_STOOL, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.CRIMSON_STOOL, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.WARPED_STOOL, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.MANGROVE_STOOL, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.CHERRY_STOOL, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(BFBlocks.FURLED_STOOL, 5, 5);


		LOGGER.info("Better Furniture mod initialized.");
	}
}