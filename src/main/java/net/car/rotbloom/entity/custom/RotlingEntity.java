package net.car.rotbloom.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.EntityView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class RotlingEntity extends TameableEntity implements Angerable {

    public final AnimationState idleAnimState = new AnimationState();
    public final AnimationState summonAnimState = new AnimationState();
    public Boolean hasPlayedSummon = false;

    private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(RotlingEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    private UUID angryAt;

    public RotlingEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    private void setupAnimStates() {
        idleAnimState.start(0);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()) {
            if (!hasPlayedSummon) {
                if (!summonAnimState.isRunning()) {
                    summonAnimState.start(this.age);
                }

                if (this.age >= 23) {
                    hasPlayedSummon = true;
                    summonAnimState.stop();
                }
            }

            if (summonAnimState.isRunning()) {
                this.setVelocity(0,0,0);
            }

            if (hasPlayedSummon && !summonAnimState.isRunning()) {
                setupAnimStates();
            }
        }
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f;
        if (this.getPose() == EntityPose.STANDING) {
            f = Math.min(posDelta * 6.0F, 1.0F);
        } else {
            f = 0.0F;
        }

        this.limbAnimator.updateLimbs(f, 0.2F);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));

        this.goalSelector.add(1, new MeleeAttackGoal(this, 1D,true));
        this.goalSelector.add(2, new AttackWithOwnerGoal(this));
        this.goalSelector.add(1, new ActiveTargetGoal<>(this,HostileEntity.class,true));
        this.goalSelector.add(2, new FollowOwnerGoal(this,1.2D, 5,25));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
        this.goalSelector.add(4,new WanderAroundGoal(this,1D));
    }

    public static DefaultAttributeContainer createRotlingAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f)
                .add(EntityAttributes.GENERIC_ARMOR, 0.25f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,3)
                .build();
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    public int getAngerTime() {
        return this.dataTracker.get(ANGER_TIME);
    }

    @Override
    public void setAngerTime(int angerTime) {
        this.dataTracker.set(ANGER_TIME, angerTime);
    }

    @Override
    public @Nullable UUID getAngryAt() {
        return this.angryAt;
    }

    @Override
    public void setAngryAt(@Nullable UUID angryAt) {
        this.angryAt = angryAt;
    }

    @Override
    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }
}
