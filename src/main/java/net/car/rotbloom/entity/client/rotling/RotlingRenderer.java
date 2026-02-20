package net.car.rotbloom.entity.client.rotling;

import net.car.rotbloom.Rotbloom;
import net.car.rotbloom.entity.client.ModModelLayers;
import net.car.rotbloom.entity.custom.RotlingEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class RotlingRenderer extends MobEntityRenderer<RotlingEntity, RotlingModel<RotlingEntity>> {
    private static final Identifier TEXTURE = Identifier.of(Rotbloom.MOD_ID, "textures/entity/rotling.png");

    public RotlingRenderer(EntityRendererFactory.Context context) {
        super(context, new RotlingModel<>(context.getPart(ModModelLayers.ROTLING)), 0.25f);
    }

    @Override
    public Identifier getTexture(RotlingEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(RotlingEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f,1f,1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}