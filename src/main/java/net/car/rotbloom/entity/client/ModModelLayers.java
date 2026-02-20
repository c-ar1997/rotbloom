package net.car.rotbloom.entity.client;

import net.car.rotbloom.Rotbloom;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer ROTLING =
            new EntityModelLayer(Identifier.of(Rotbloom.MOD_ID,"rotling"), "main");
    public static final EntityModelLayer CHAINS =
            new EntityModelLayer(Identifier.of(Rotbloom.MOD_ID, "chains"), "main");
}
