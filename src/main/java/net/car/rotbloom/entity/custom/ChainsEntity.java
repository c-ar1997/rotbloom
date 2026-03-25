package net.car.rotbloom.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class ChainsEntity extends MobEntity {

    public final AnimationState chainSpinState = new AnimationState();

    public ChainsEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer createChainsAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 666)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0f)
                .build();
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, PositionUpdater updater) {
        updater.accept(passenger, this.getX(), this.getY(), this.getZ());
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        if (!chainSpinState.isRunning()) {
            chainSpinState.start(this.age);
        }

        if (this.getPassengerList().isEmpty()) {
            this.remove(RemovalReason.DISCARDED);
            return;
        }

        Entity passenger = this.getPassengerList().get(0);

        if (!passenger.isAlive()) {
            this.remove(RemovalReason.DISCARDED);
            return;
        }

        if (passenger instanceof LivingEntity rider) {
            rider.setVelocity(0, 0, 0);
            rider.fallDistance = 0;
            rider.setSneaking(false);

            rider.setInvulnerable(true);
        }
    }

    @Override
    public void remove(RemovalReason reason) {
        if (!this.getPassengerList().isEmpty()) {
            Entity passenger = this.getPassengerList().get(0);
            if (passenger instanceof LivingEntity rider) {
                rider.setInvulnerable(false);
            }
        }

        super.remove(reason);
    }
}