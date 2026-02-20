package net.car.rotbloom.item.custom;

import net.car.rotbloom.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LesserHarvestItem extends SwordItem {
    public LesserHarvestItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Random random = target.getWorld().getRandom();
        if (target.isDead() && random.nextInt(3) == 0) {
            if (target instanceof WitherEntity) {
                target.dropItem(ModItems.BOSS_SOUL);
                target.playSound(SoundEvents.BLOCK_SOUL_SAND_BREAK, 10f, 1f);
            } else if (target instanceof EnderDragonEntity) {
                target.dropItem(ModItems.BOSS_SOUL);
            }else if (target instanceof WitherSkeletonEntity) {
                target.dropItem(ModItems.WITHER_BONE);
            } else if (target.getType().isIn(EntityTypeTags.UNDEAD)) {
                if (random.nextInt(2) == 0) {
                    target.dropItem(ModItems.ROTTEN_BONE);
                }
                target.dropItem(ModItems.HOSTILE_SOUL);
            } else if (target.getType().getSpawnGroup().isPeaceful()) {
                target.dropItem(ModItems.PASSIVE_SOUL);
            }
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.rotblossom.lesser_harvest.tooltip"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
