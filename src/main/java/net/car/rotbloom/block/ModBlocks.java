package net.car.rotbloom.block;

import com.ibm.icu.util.CodePointTrie;
import net.car.rotbloom.Rotbloom;
import net.car.rotbloom.block.custom.SmallHusk;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block SMALL_HUSK = registerBlock("small_husk",
            new SmallHusk(FabricBlockSettings.copyOf(Blocks.BONE_BLOCK)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Rotbloom.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, Identifier.of(Rotbloom.MOD_ID,name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        Rotbloom.LOGGER.info("Registering blocks for: " + Rotbloom.MOD_ID);
    }
}
