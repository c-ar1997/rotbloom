package net.car.rotbloom.item.custom;

import com.mojang.datafixers.kinds.IdF;
import net.car.rotbloom.components.ModComponents;
import net.car.rotbloom.entity.ModEntities;
import net.car.rotbloom.entity.custom.ChainsEntity;
import net.car.rotbloom.team.TeamHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import javax.tools.Tool;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ContractItem extends Item {
    public ContractItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (stack.contains(ModComponents.VICTIM_NAME) && stack.contains(ModComponents.VICTIM_UUID)) {
            user.setStackInHand(hand,setContractNbt(stack,user));
            return TypedActionResult.success(user.getStackInHand(hand));
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!user.getWorld().isClient) {
            if (getVictimUUID(stack).equals(entity.getUuid())) {
                setSit((ServerPlayerEntity) user,entity);
            } else if (entity instanceof ChainsEntity) {
                entity.remove(Entity.RemovalReason.DISCARDED);
            }
        }
        return ActionResult.SUCCESS;
    }

    public static ItemStack setContractNbt(ItemStack stack, PlayerEntity entity) {
        //stack.getOrCreateNbt().putUuid("VictimUUID", entity.getUuid());
        //stack.getOrCreateNbt().putString("VictimName", entity.getDisplayName().getString());
        stack.set(ModComponents.VICTIM_UUID, entity.getUuid());
        stack.set(ModComponents.VICTIM_NAME, entity.getDisplayName().getString());
        return stack;
    }

    private void setSit(ServerPlayerEntity plr, LivingEntity entity) {
        ServerWorld world = plr.getServerWorld();
        if (entity.canMoveVoluntarily()) {
            ChainsEntity chains = new ChainsEntity(ModEntities.CHAINS,world);
            world.spawnEntity(chains);
            BlockPos pos = entity.getBlockPos();
            chains.refreshPositionAndAngles(pos,entity.getYaw(),entity.getPitch());
            entity.startRiding(chains,true);
        }
    }

    public static UUID getVictimUUID(ItemStack stack) {
        //if(hasNbt(stack)) {
        //    return stack.getOrCreateNbt().getUuid("VictimUUID");
        //}
        //return null;
        if (stack.contains(ModComponents.VICTIM_UUID)) {
            return stack.getOrDefault(ModComponents.VICTIM_UUID, UUID.fromString(""));
        }
        return null;
    }

    public static String getVictimName(ItemStack stack) {
        if (stack.contains(ModComponents.VICTIM_NAME)) {
            return stack.getOrDefault(ModComponents.VICTIM_NAME,"");
        }
        return "";
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.contract.tooltip"));
        tooltip.add(Text.literal("Binded to: " + getVictimName(stack)).formatted(Formatting.DARK_GRAY));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
