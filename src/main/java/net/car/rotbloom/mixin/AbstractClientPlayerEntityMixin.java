package net.car.rotbloom.mixin;

import com.mojang.authlib.GameProfile;
import net.car.rotbloom.Rotbloom;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity {
    public AbstractClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }
    @Override
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
            if (Rotbloom.bannedUuids.contains(this.getUuid()) && (MinecraftClient.getInstance().getCameraEntity().getRotationVecClient().dotProduct(this.getPos().subtract(cameraX, cameraY, cameraZ).normalize()) > 0.5 || this.isInSneakingPose() || MinecraftClient.getInstance().options.getPerspective().isFrontView())) {
                if (!Rotbloom.isGhost) {
                    return true;
                } else {
                    return false;
                }
            }
        return super.shouldRender(cameraX, cameraY, cameraZ);
    }
    @Override
    public boolean shouldRenderName() {
        return !Rotbloom.bannedUuids.contains(this.getUuid());
    }
}
