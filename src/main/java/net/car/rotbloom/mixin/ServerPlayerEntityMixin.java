package net.car.rotbloom.mixin;

import com.mojang.authlib.GameProfile;
import net.car.rotbloom.Rotbloom;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
/**
 * Mixin class that extends the `PlayerEntity` class to provide additional functionality for server-side player entities.
 */
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
    public ServerPlayerEntityMixin(World world, BlockPos blockPos, float f, GameProfile gameProfile, @Nullable PlayerPublicKey playerPublicKey) {
        super(world, blockPos, f, gameProfile);
    }

    @ModifyVariable(method = "onDeath", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/entity/damage/DamageTracker;getDeathMessage()Lnet/minecraft/text/Text;"), index = 3)
    private Text eplayers$modifyDeathMessage(Text value) {
        if (Rotbloom.bannedUuids.contains(this.getUuid())) {
            return (Text) Text.EMPTY;
        }
        return value;
    }


    /*
    @Inject(method = "copyFrom", at = @At("HEAD"))
    private void eplayers$copyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci) {
        if (Rotbloom.bannedUuids.contains(oldPlayer.getUuid())) {
            this.getInventory().clone(oldPlayer.getInventory());
            this.experienceLevel = oldPlayer.experienceLevel;
            this.totalExperience = oldPlayer.totalExperience;
            this.experienceProgress = oldPlayer.experienceProgress;
            this.setScore(oldPlayer.getScore());
        }
    } */
    // only activate with novva's permission

}


