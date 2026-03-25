package net.car.rotbloom.item.custom;

import net.car.rotbloom.components.ModComponents;
import net.car.rotbloom.entity.ModEntities;
import net.car.rotbloom.entity.custom.ChainsEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class ContractItem extends Item {

    public ContractItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            UUID stored = stack.get(ModComponents.VICTIM_UUID);

            if (stored == null) {
                setContractNbt(stack, user);
                user.sendMessage(Text.literal("The Contract has been Signed"), true);
            }
        }

        return TypedActionResult.success(stack, world.isClient());
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!(user instanceof ServerPlayerEntity serverPlayer)) {
            return ActionResult.SUCCESS;
        }

        UUID stored = getVictimUUID(stack);

        if (entity.getVehicle() instanceof ChainsEntity chains) {
            entity.stopRiding();
            chains.remove(Entity.RemovalReason.DISCARDED);
            return ActionResult.SUCCESS;
        }

        if (stored != null && stored.equals(entity.getUuid())) {
            setSit(serverPlayer, entity);
        }

        return ActionResult.SUCCESS;
    }

    public static void setContractNbt(ItemStack stack, LivingEntity entity) {
        stack.set(ModComponents.VICTIM_UUID, entity.getUuid());
        stack.set(ModComponents.VICTIM_NAME, entity.getName().getString());
    }

    private void setSit(ServerPlayerEntity player, LivingEntity target) {
        ServerWorld world = player.getServerWorld();

        if (target.hasVehicle()) return;

        ChainsEntity chains = ModEntities.CHAINS.create(world);
        if (chains == null) return;

        BlockPos pos = target.getBlockPos();

        chains.refreshPositionAndAngles(
                pos.getX() + 0.5,
                pos.getY(),
                pos.getZ() + 0.5,
                target.getYaw(),
                target.getPitch()
        );

        world.spawnEntity(chains);
        target.startRiding(chains, true);
    }

    public static UUID getVictimUUID(ItemStack stack) {
        return stack.contains(ModComponents.VICTIM_UUID)
                ? stack.get(ModComponents.VICTIM_UUID)
                : null;
    }

    public static String getVictimName(ItemStack stack) {
        return stack.contains(ModComponents.VICTIM_NAME)
                ? stack.get(ModComponents.VICTIM_NAME)
                : "";
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.contract.tooltip"));

        String name = getVictimName(stack);
        tooltip.add(Text.literal(name.isEmpty() ? "Unbound" : "Bound to: " + name)
                .formatted(Formatting.DARK_GRAY));
    }
}
