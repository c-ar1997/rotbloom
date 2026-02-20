package net.car.rotbloom.command;

import net.car.rotbloom.Rotbloom;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;

import static net.car.rotbloom.Rotbloom.isGhost;

public class GhostToggle {
    public static void GhostToggle() {
        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            commandDispatcher.register(CommandManager.literal("ghost").executes(commandContext -> {
                if (Rotbloom.bannedUuids.contains(commandContext.getSource().getPlayer().getUuid())) {
                    isGhost = !isGhost;
                    if (isGhost) {
                        commandContext.getSource().sendFeedback(() -> Text.literal("Corner vision is enabled."), false);
                    } else {
                        commandContext.getSource().sendFeedback(() -> Text.literal("You are now visible."), false);
                    }
                } else {
                    commandContext.getSource().sendFeedback(() -> Text.literal("...?"), false);
                }
                return 0;
            }));
        });
    }
}
