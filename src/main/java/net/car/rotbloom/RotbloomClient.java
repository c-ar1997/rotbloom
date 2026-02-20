package net.car.rotbloom;

import net.car.rotbloom.entity.ModEntities;
import net.car.rotbloom.entity.client.ModModelLayers;
import net.car.rotbloom.entity.client.chain.ChainsModel;
import net.car.rotbloom.entity.client.chain.ChainsRenderer;
import net.car.rotbloom.entity.client.rotling.RotlingModel;
import net.car.rotbloom.entity.client.rotling.RotlingRenderer;
import net.car.rotbloom.entity.custom.ChainsEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class RotbloomClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ROTLING, RotlingModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.ROTLING, RotlingRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CHAINS, ChainsModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.CHAINS, ChainsRenderer::new);
    }
}
