package net.car.rotbloom.team;

import net.car.rotbloom.mixin.ServerPlayerEntityMixin;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class TeamHelper {

    public static void ensureChained(MinecraftServer server) {
        Scoreboard scoreboard = server.getScoreboard();
        if (scoreboard.getTeam("chained") == null) {
            Team team = scoreboard.addTeam("chained");
            team.setDisplayName(Text.literal("Chained"));
        }
    }

    public static void addPlayerAsChained(ServerPlayerEntity plr) {
        ensureChained(plr.getServer());
        Scoreboard scoreboard = plr.getScoreboard();
        Team team = scoreboard.addTeam("chained");
        if (team != null) {
            scoreboard.addScoreHolderToTeam(String.valueOf(plr.getName()),team);
        }
    }

    public static void removePlayerAsChained(ServerPlayerEntity plr) {
        ensureChained(plr.getServer());
        Scoreboard scoreboard = plr.getScoreboard();
        Team team = scoreboard.addTeam("chained");
        if (team != null) {
            scoreboard.removeScoreHolderFromTeam(String.valueOf(plr.getName()),team);
        }
    }

    public static boolean isPlayerChained(ClientPlayerEntity plr) {
        Team team = plr.getScoreboard().getScoreHolderTeam(String.valueOf(plr.getName()));
        return team != null && team.getName().equals("chained");
    }

}
