package net.car.rotbloom.block.custom;

import net.car.rotbloom.entity.ModEntities;
import net.car.rotbloom.entity.custom.RotlingEntity;
import net.car.rotbloom.item.ModItemGroups;
import net.car.rotbloom.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SmallHusk extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    private static final VoxelShape SHAPE = Block.createCuboidShape(3, 0, 3, 13, 8, 13);
    public SmallHusk(Settings settings) {
        super(settings);
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        Hand hand = player.getActiveHand();
        if (player.isHolding(ModItems.PASSIVE_SOUL)) {
            world.breakBlock(pos,false,player);
            world.playSound(player, pos, SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, SoundCategory.BLOCKS, 1f , 1f);
            player.getStackInHand(hand).decrement(1);
            if (world instanceof ServerWorld serverWorld){
                world.spawnEntity(EntityType.SILVERFISH.spawn(serverWorld, pos, SpawnReason.MOB_SUMMONED));
            }
        }
        if (player.isHolding(ModItems.HOSTILE_SOUL)) {
            world.breakBlock(pos,false,player);
            world.playSound(player, pos, SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, SoundCategory.BLOCKS, 1f , 1f);
            player.getStackInHand(hand).decrement(1);
            if (world instanceof ServerWorld serverWorld){
                RotlingEntity rotling = ModEntities.ROTLING.spawn(serverWorld, pos, SpawnReason.MOB_SUMMONED);
                assert rotling != null;
                world.spawnEntity(rotling);
                rotling.setOwner(player);
            }
        }
        if (player.isHolding(ModItems.BOSS_SOUL)) {
            world.breakBlock(pos,false,player);
            world.playSound(player, pos, SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, SoundCategory.BLOCKS, 1f , 1f);
            player.getStackInHand(hand).decrement(1);
            if (world instanceof ServerWorld serverWorld){
                world.spawnEntity(EntityType.WITHER_SKELETON.spawn(serverWorld, pos, SpawnReason.MOB_SUMMONED));
            }
        }
        return super.onUse(state, world, pos, player, hit);
    }
}
