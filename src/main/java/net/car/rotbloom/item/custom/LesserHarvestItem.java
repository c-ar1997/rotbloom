package net.car.rotbloom.item.custom;

import net.car.rotbloom.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LesserHarvestItem extends SwordItem {

    private static final ToolMaterial MATERIAL = new WeaponMaterial();

    public LesserHarvestItem(Settings settings) {
        super(MATERIAL,
                settings.attributeModifiers(
                        SwordItem.createAttributeModifiers(MATERIAL, 9, -3.0F)
                )
        );
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
        tooltip.add(Text.translatable("tooltip.rotblossom.lesser_harvest.tooltip2"));
        super.appendTooltip(stack, context, tooltip, type);
    }

    private static class WeaponMaterial implements ToolMaterial {

        @Override
        public int getDurability() {
            return 2000;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 0;
        }

        @Override
        public float getAttackDamage() {
            return 0;
        }

        @Override
        public int getEnchantability() {
            return 0;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(Items.COPPER_INGOT);
        }

        @Override
        public TagKey<Block> getInverseTag() {
            return BlockTags.INCORRECT_FOR_WOODEN_TOOL;
        }
    }
}
