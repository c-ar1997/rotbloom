package net.car.rotbloom.entity;

import com.sun.net.httpserver.Filter;
import net.car.rotbloom.Rotbloom;
import net.car.rotbloom.entity.custom.ChainsEntity;
import net.car.rotbloom.entity.custom.RotlingEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<RotlingEntity> ROTLING = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Rotbloom.MOD_ID,"rotling"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, RotlingEntity::new).dimensions(EntityDimensions.fixed(0.75f,1.375f)).build());

    public static final EntityType<ChainsEntity> CHAINS = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Rotbloom.MOD_ID,"chains"),
            FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, ChainsEntity::new).dimensions(EntityDimensions.fixed(2f,2f)).build());

    public static void registerModEntities() {
        Rotbloom.LOGGER.info("registering entities for " + Rotbloom.MOD_ID);
    }

}
