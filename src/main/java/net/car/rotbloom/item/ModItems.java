package net.car.rotbloom.item;

import net.car.rotbloom.Rotbloom;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.IdentityHashMap;

public class ModItems {
    public static final Item HOSTILE_SOUL = registerItem("hostile_soul", new Item(new FabricItemSettings()));
    public static final Item PASSIVE_SOUL = registerItem("passive_soul", new Item(new FabricItemSettings()));
    public static final Item BOSS_SOUL = registerItem("boss_soul", new Item(new FabricItemSettings()));

    public static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries){
        entries.add(HOSTILE_SOUL);
        entries.add(PASSIVE_SOUL);
        entries.add(BOSS_SOUL);
    }


    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Rotbloom.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Rotbloom.LOGGER.info("Registering Mod items for "+ Rotbloom.MOD_ID);
    }
}
