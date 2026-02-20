package net.car.rotbloom.components;

import com.mojang.serialization.Codec;
import net.car.rotbloom.Rotbloom;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.UUID;

public class ModComponents {
    public static void initialize() {
        Rotbloom.LOGGER.info("Registering {} components", Rotbloom.MOD_ID);
        // Technically this method can stay empty, but some developers like to notify
        // the console, that certain parts of the mod have been successfully initialized
    }

    public static final ComponentType<UUID> VICTIM_UUID = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Rotbloom.MOD_ID, "victimuuid"),
            ComponentType.<UUID>builder().codec(Uuids.CODEC).build()
    );

    public static final ComponentType<String> VICTIM_NAME = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Rotbloom.MOD_ID,"victimname"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );
}
