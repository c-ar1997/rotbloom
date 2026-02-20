package net.car.rotbloom.mixin;


import com.google.common.collect.Maps;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.car.rotbloom.item.custom.ContractItem;
import net.car.rotbloom.team.TeamHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.Map;

@Environment(EnvType.CLIENT)
@Mixin(KeyBinding.class)
public class KeybindMixin{
    @ModifyReturnValue(method = "isPressed", at = @At("RETURN"))
    private boolean disableMovement(boolean original) {
        if (!original) return false;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && TeamHelper.isPlayerChained(client.player)) {
            KeyBinding self = (KeyBinding) (Object) this;
            if (self == client.options.forwardKey || self == client.options.backKey || self == client.options.leftKey || self == client.options.rightKey) {
                return false;
            }
        }
        return original;
    }
}
