package net.car.rotbloom.entity.client.chain;

import net.car.rotbloom.Rotbloom;
import net.car.rotbloom.entity.client.ModModelLayers;
import net.car.rotbloom.entity.custom.ChainsEntity;
import net.car.rotbloom.entity.custom.RotlingEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
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
    private static final Identifier TEXTURE = Identifier.of(Rotbloom.MOD_ID,"textures/entity/chains.png");
    private final EntityModel model;

    public ChainsRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new ChainsModel(ctx.getPart(ModModelLayers.CHAINS));
    }

    @Override
    public Identifier getTexture(ChainsEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(ChainsEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.scale(1f,1f,1f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) entity.age /10));
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucentEmissive(TEXTURE)), light,1,1);
    }
}
