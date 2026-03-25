package net.car.rotbloom.item;

import net.car.rotbloom.Rotbloom;
import net.car.rotbloom.components.ModComponents;
import net.car.rotbloom.item.custom.ContractItem;
import net.car.rotbloom.item.custom.LesserHarvestItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.IdentityHashMap;
import java.util.UUID;

public class ModItems {
    public static final Item HOSTILE_SOUL = registerItem("hostile_soul", new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item PASSIVE_SOUL = registerItem("passive_soul", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item BOSS_SOUL = registerItem("boss_soul", new Item(new Item.Settings().rarity(Rarity.EPIC)));
    public static final Item ROTTEN_BONE = registerItem("rotten_bone", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item WITHER_BONE = registerItem("wither_bone", new Item(new Item.Settings().rarity(Rarity.RARE)));

    public static final Item LESSER_HARVEST = registerItem("lesser_harvest",
            new LesserHarvestItem(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));

    public static final Item CONTRACT = registerItem("contract",
            new ContractItem(new Item.Settings().maxCount(1)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Rotbloom.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Rotbloom.LOGGER.info("Registering Mod items for "+ Rotbloom.MOD_ID);
    }
}
