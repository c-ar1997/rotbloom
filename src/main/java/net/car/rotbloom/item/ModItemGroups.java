package net.car.rotbloom.item;

import net.car.rotbloom.Rotbloom;
import net.car.rotbloom.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup ROTBLOOM = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Rotbloom.MOD_ID,"rotbloom"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.rotbloom"))
                    .icon(() -> new ItemStack(ModItems.BOSS_SOUL)).entries(((displayContext, entries) -> {

                        entries.add(ModItems.PASSIVE_SOUL);
                        entries.add(ModItems.HOSTILE_SOUL);
                        entries.add(ModItems.BOSS_SOUL);
                        entries.add(ModItems.ROTTEN_BONE);
                        entries.add(ModItems.WITHER_BONE);

                        entries.add(ModItems.LESSER_HARVEST);
                        entries.add(ModItems.CONTRACT);

                        entries.add(ModBlocks.SMALL_HUSK);


                    })).build());

    public static void registerItemGroups(){
        Rotbloom.LOGGER.info("Registering Item Groups for" + Rotbloom.MOD_ID);
    }
}
