package net.car.rotbloom;

import net.car.rotbloom.block.ModBlocks;
import net.car.rotbloom.command.GhostToggle;
import net.car.rotbloom.components.ModComponents;
import net.car.rotbloom.entity.ModEntities;
import net.car.rotbloom.entity.custom.ChainsEntity;
import net.car.rotbloom.entity.custom.RotlingEntity;
import net.car.rotbloom.item.ModItemGroups;
import net.car.rotbloom.item.ModItems;
import net.car.rotbloom.item.custom.LesserHarvestItem;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.item.ItemGroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.UUID;

public class Rotbloom implements ModInitializer {
	public static final String MOD_ID = "rotbloom";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ArrayList<UUID> bannedUuids = new ArrayList<>();
	public static Boolean isGhost = true;


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Do you smell it? The stench of a thousand corpses?");
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		ModEntities.registerModEntities();
		ModComponents.initialize();
		GhostToggle.GhostToggle();
		FabricDefaultAttributeRegistry.register(ModEntities.ROTLING, RotlingEntity.createRotlingAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CHAINS, ChainsEntity.createChainsAttributes());

		bannedUuids.add(UUID.fromString("5db035e3-1304-4b6b-ae2f-f5adbf518406"));

	}
}