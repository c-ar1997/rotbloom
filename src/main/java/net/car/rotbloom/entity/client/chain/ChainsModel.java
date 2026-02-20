package net.car.rotbloom.entity.client.chain;

import net.car.rotbloom.entity.animation.ModAnimations;
import net.car.rotbloom.entity.custom.ChainsEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class ChainsModel<T extends ChainsEntity> extends SinglePartEntityModel<T> {
	private final ModelPart root;
	private final ModelPart bone4;
	private final ModelPart bone3;
	private final ModelPart bone2;
	private final ModelPart bone;
	public ChainsModel(ModelPart root) {
		this.root = root.getChild("root");
		this.bone4 = this.root.getChild("bone4");
		this.bone3 = this.root.getChild("bone3");
		this.bone2 = this.root.getChild("bone2");
		this.bone = this.root.getChild("bone");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData bone4 = root.addChild("bone4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -13.5F, 0.0F));

		ModelPartData cube_r1 = bone4.addChild("cube_r1", ModelPartBuilder.create().uv(0, 57).cuboid(-8.0F, -3.0F, -8.0F, 16.0F, 3.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.5F, 0.0F, 0.0F, -0.7854F, 0.0F));

		ModelPartData bone3 = root.addChild("bone3", ModelPartBuilder.create().uv(0, 38).cuboid(-8.0F, -1.5F, -8.0F, 16.0F, 3.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -9.5F, 0.0F));

		ModelPartData bone2 = root.addChild("bone2", ModelPartBuilder.create().uv(0, 19).cuboid(-8.0F, -1.5F, -8.0F, 16.0F, 3.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.5F, 0.0F, 0.0F, 0.7854F, 0.0F));

		ModelPartData bone = root.addChild("bone", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -1.5F, -8.0F, 16.0F, 3.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.5F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
		root.render(matrices, vertexConsumer, light, overlay, color);
	}

	@Override
	public ModelPart getPart() {
		return root;
	}

	@Override
	public void setAngles(ChainsEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.updateAnimation(entity.chainSpinState, ModAnimations.IDLE, ageInTicks, 1f);
	}
}