package net.car.rotbloom.datagen;

import net.car.rotbloom.block.ModBlocks;
import net.car.rotbloom.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleState(ModBlocks.SMALL_HUSK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.HOSTILE_SOUL, Models.GENERATED);
        itemModelGenerator.register(ModItems.PASSIVE_SOUL, Models.GENERATED);
        itemModelGenerator.register(ModItems.BOSS_SOUL, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROTTEN_BONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WITHER_BONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CONTRACT, Models.GENERATED);

        itemModelGenerator.register(ModItems.LESSER_HARVEST, Models.HANDHELD);
    }
}
