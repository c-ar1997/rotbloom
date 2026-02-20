package net.car.rotbloom.entity.client.rotling;

import net.car.rotbloom.entity.animation.ModAnimations;
import net.car.rotbloom.entity.custom.RotlingEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class RotlingModel<T extends RotlingEntity> extends SinglePartEntityModel<T> {
	private final ModelPart root;
	private final ModelPart head;
	public RotlingModel(ModelPart root) {
		this.root = root.getChild("root");
		this.head = root.getChild("root").getChild("torso").getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(14.0F, 24.0F, -13.0F));

		ModelPartData torso = root.addChild("torso", ModelPartBuilder.create(), ModelTransform.pivot(-14.0F, -9.0F, 13.0F));

		ModelPartData head = torso.addChild("head", ModelPartBuilder.create().uv(32, 2).cuboid(-4.0F, -7.0F, -4.0F, 8.0F, 7.0F, 8.0F, new Dilation(0.0F))
		.uv(41, 50).cuboid(0.75F, -5.5F, -4.0F, 3.0F, 4.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -6.0F, 0.0F));

		ModelPartData cube_r1 = head.addChild("cube_r1", ModelPartBuilder.create().uv(12, 27).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -7.0F, -2.5F, 0.0F, 0.0F, 0.6109F));

		ModelPartData cube_r2 = head.addChild("cube_r2", ModelPartBuilder.create().uv(8, 27).cuboid(0.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -7.0F, -2.5F, 0.0F, 0.0F, -0.6109F));

		ModelPartData cube_r3 = head.addChild("cube_r3", ModelPartBuilder.create().uv(4, 27).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, -7.0F, -2.0F, 0.6109F, 0.0F, 0.0F));

		ModelPartData cube_r4 = head.addChild("cube_r4", ModelPartBuilder.create().uv(0, 27).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, -7.0F, -3.0F, -0.6109F, 0.0F, 0.0F));

		ModelPartData cube_r5 = head.addChild("cube_r5", ModelPartBuilder.create().uv(21, 27).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, -7.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

		ModelPartData cube_r6 = head.addChild("cube_r6", ModelPartBuilder.create().uv(19, 32).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, -7.0F, 1.0F, 0.6109F, 0.0F, 0.0F));

		ModelPartData cube_r7 = head.addChild("cube_r7", ModelPartBuilder.create().uv(39, 24).cuboid(0.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -7.0F, 0.5F, 0.0F, 0.0F, -0.6109F));

		ModelPartData cube_r8 = head.addChild("cube_r8", ModelPartBuilder.create().uv(13, 20).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -7.0F, 0.5F, 0.0F, 0.0F, 0.6109F));

		ModelPartData cube_r9 = head.addChild("cube_r9", ModelPartBuilder.create().uv(46, 19).cuboid(-4.0F, 0.0F, 0.0F, 8.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, 4.0F, -1.309F, 0.0F, 0.0F));

		ModelPartData cube_r10 = head.addChild("cube_r10", ModelPartBuilder.create().uv(46, 21).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, -4.0F, 1.309F, 0.0F, 0.0F));

		ModelPartData cube_r11 = head.addChild("cube_r11", ModelPartBuilder.create().uv(28, 25).mirrored().cuboid(0.0F, 0.0F, -4.0F, 2.0F, 0.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(4.0F, -7.0F, 0.0F, 0.0F, 0.0F, 1.309F));

		ModelPartData cube_r12 = head.addChild("cube_r12", ModelPartBuilder.create().uv(14, 13).cuboid(-2.0F, 0.0F, -4.0F, 2.0F, 0.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -7.0F, 0.0F, 0.0F, 0.0F, -1.309F));

		ModelPartData bone9 = head.addChild("bone9", ModelPartBuilder.create().uv(31, 61).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(2.5F, -3.5F, -3.5F));

		ModelPartData body = torso.addChild("body", ModelPartBuilder.create().uv(44, 41).cuboid(-3.5F, -5.5F, -1.5F, 7.0F, 5.0F, 3.0F, new Dilation(0.0F))
		.uv(40, 31).cuboid(-4.0F, -6.0F, -2.0F, 8.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_arm = torso.addChild("right_arm", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, 0.0F, -2.0F, 3.0F, 11.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.5F, -6.0F, 0.0F));

		ModelPartData cube_r13 = right_arm.addChild("cube_r13", ModelPartBuilder.create().uv(0, 23).cuboid(-2.0F, 0.0F, -2.0F, 2.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, 7.0F, 0.0F, 0.0F, 0.0F, -1.309F));

		ModelPartData left_arm = torso.addChild("left_arm", ModelPartBuilder.create().uv(23, 34).cuboid(-1.5F, 0.0F, -2.0F, 3.0F, 11.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.5F, -6.0F, 0.0F));

		ModelPartData cube_r14 = left_arm.addChild("cube_r14", ModelPartBuilder.create().uv(20, 24).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 0.0F, 0.5F, 0.0F, 0.0F, 0.6109F));

		ModelPartData cube_r15 = left_arm.addChild("cube_r15", ModelPartBuilder.create().uv(33, 27).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

		ModelPartData cube_r16 = left_arm.addChild("cube_r16", ModelPartBuilder.create().uv(39, 26).cuboid(0.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 0.0F, 0.5F, 0.0F, 0.0F, -0.6109F));

		ModelPartData cube_r17 = left_arm.addChild("cube_r17", ModelPartBuilder.create().uv(31, 27).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 1.0F, 0.6109F, 0.0F, 0.0F));

		ModelPartData cube_r18 = left_arm.addChild("cube_r18", ModelPartBuilder.create().uv(36, 27).cuboid(0.0F, 0.0F, -2.0F, 2.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, 7.0F, 0.0F, 0.0F, 0.0F, 1.309F));

		ModelPartData cube_r19 = left_arm.addChild("cube_r19", ModelPartBuilder.create().uv(44, 23).cuboid(0.0F, 0.0F, -2.0F, 2.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, 5.0F, 0.0F, 0.0F, 0.0F, 1.309F));

		ModelPartData cube_r20 = left_arm.addChild("cube_r20", ModelPartBuilder.create().uv(56, 25).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, -1.309F, 0.0F, 0.0F));

		ModelPartData cube_r21 = left_arm.addChild("cube_r21", ModelPartBuilder.create().uv(50, 25).cuboid(-1.5F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -2.0F, 1.309F, 0.0F, 0.0F));

		ModelPartData cube_r22 = left_arm.addChild("cube_r22", ModelPartBuilder.create().uv(52, 27).cuboid(0.0F, 0.0F, -2.0F, 2.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.309F));

		ModelPartData right_leg = root.addChild("right_leg", ModelPartBuilder.create().uv(14, 0).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-16.0F, -9.0F, 13.0F));

		ModelPartData cube_r23 = right_leg.addChild("cube_r23", ModelPartBuilder.create().uv(0, 19).cuboid(-2.0F, 0.0F, -2.0F, 2.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 6.0F, 0.0F, 0.0F, 0.0F, -1.309F));

		ModelPartData left_leg = root.addChild("left_leg", ModelPartBuilder.create().uv(5, 34).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-12.0F, -9.0F, 13.0F));

		ModelPartData cube_r24 = left_leg.addChild("cube_r24", ModelPartBuilder.create().uv(12, 21).cuboid(-2.0F, 0.0F, -2.0F, 2.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 2.0F, 0.0F, 0.0F, 0.0F, -1.309F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(RotlingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		if (entity.summonAnimState.isRunning()) {
			this.updateAnimation(entity.summonAnimState, ModAnimations.RUN, ageInTicks, 1f);
		}

        if (entity.isAttacking()) {
			this.animateMovement(ModAnimations.RUN, limbSwing, limbSwingAmount, 2f, 2.5f);
		} else {
			this.animateMovement(ModAnimations.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		}

		this.updateAnimation(entity.idleAnimState, ModAnimations.IDLE, ageInTicks, 1f);

	}

	private void setHeadAngles(float headYaw, float headPitch){
		headYaw = MathHelper.clamp(headYaw, -30.0f,30.0f);
		headPitch = MathHelper.clamp(headPitch, -25.0f, 45.0f);

		this.head.yaw = headYaw * 0.017453292f;
		this.head.pitch = headPitch * 0.017453292f;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
		root.render(matrices, vertexConsumer, light, overlay, color);
	}

	@Override
	public ModelPart getPart() {
		return root;
	}
}