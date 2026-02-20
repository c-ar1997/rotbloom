package net.car.rotbloom.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.car.rotbloom.Rotbloom;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.stream.Collectors;

@Mixin(PlayerListHud.class)
public abstract class PlayerListHudMixin {
    @Shadow protected abstract List<PlayerListEntry> collectPlayerEntries();

    // @ModifyVariable(method = "collectPlayerEntries", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/network/ClientPlayNetworkHandler;getListedPlayerListEntries()Ljava/util/Collection;"))
//    private PlayerListHud eplayers$modifyDeathMessage(PlayerListHud value) {
    //     List<PlayerListEntry> list = this.collectPlayerEntries();
    //      list.removeIf((entry) -> ExpulsionMod.bannedUuids.contains(entry.getProfile().getId()));
    //     return (PlayerListHud) list;
    //   }
    // tbh idk why i even bothered to try this thing up here im just keeping it to remind me of my failures
    @ModifyReturnValue(method = "collectPlayerEntries", at = @At("RETURN"))
    private List<PlayerListEntry> innominepatrisetfiliietspiritussancti (List<PlayerListEntry> original) {
        return original.stream()
                .filter(entry -> !Rotbloom.bannedUuids.contains(entry.getProfile().getId()))
                .collect(Collectors.toList());
    }

}