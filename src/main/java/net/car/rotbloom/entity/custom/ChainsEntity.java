package net.car.rotbloom.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class ChainsEntity extends AmbientEntity {

    public final AnimationState chainSpinState = new AnimationState();

    public ChainsEntity(EntityType<? extends AmbientEntity> entityType, World world) {
        super(entityType, world);
    }

    private void setupAnimStates() {
        chainSpinState.start(0);
    }

    public static DefaultAttributeContainer createChainsAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 666)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0f)
                .add(EntityAttributes.GENERIC_ARMOR, 0f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,0)
                .build();
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, PositionUpdater positionUpdater) {
        passenger.setPos(this.getPos().x,this.getPos().y,this.getPos().z);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        setupAnimStates();
        if (!this.getPassengerList().isEmpty()) {
            if (this.getPassengerList().get(0) instanceof LivingEntity rider) {
                rider.setPose(EntityPose.STANDING);
                rider.setHealth(rider.getHealth());
                rider.setSneaking(false);
            }
        }
    }
}
