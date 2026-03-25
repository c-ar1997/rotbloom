package net.car.rotbloom.entity.client.chain;

import net.car.rotbloom.Rotbloom;
import net.car.rotbloom.entity.client.ModModelLayers;
import net.car.rotbloom.entity.custom.ChainsEntity;
import net.car.rotbloom.entity.custom.RotlingEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class ChainsRenderer extends EntityRenderer<ChainsEntity> {

    private static final Identifier TEXTURE =
            Identifier.of(Rotbloom.MOD_ID, "textures/entity/chains.png");

    private final ChainsModel<ChainsEntity> model;

    public ChainsRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new ChainsModel<>(ctx.getPart(ModModelLayers.CHAINS));
    }

    @Override
    public Identifier getTexture(ChainsEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(ChainsEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                       int light) {

        matrices.push();

        matrices.multiply(
                RotationAxis.POSITIVE_Y.rotationDegrees((entity.age + tickDelta) * 10f)
        );

        VertexConsumer vertexConsumer =
                vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(TEXTURE));

        model.setAngles(entity, 0, 0, entity.age + tickDelta, 0, 0);
        model.render(matrices, vertexConsumer, light,
                OverlayTexture.DEFAULT_UV);

        matrices.pop();

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}