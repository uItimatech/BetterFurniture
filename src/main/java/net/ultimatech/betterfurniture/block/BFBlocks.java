package net.ultimatech.betterfurniture.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ultimatech.betterfurniture.BetterFurniture;
import net.ultimatech.betterfurniture.block.custom.ChairBlock;
import net.ultimatech.betterfurniture.block.custom.StoolBlock;
import net.ultimatech.betterfurniture.block.custom.TableBlock;

public class BFBlocks {

    // --- Tables ---

    public static final Block OAK_TABLE = registerBlock("table_oak",
            new TableBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).nonOpaque().burnable()));

    public static final Block SPRUCE_TABLE = registerBlock("table_spruce",
            new TableBlock(FabricBlockSettings.copy(Blocks.SPRUCE_PLANKS).nonOpaque().burnable()));

    public static final Block BIRCH_TABLE = registerBlock("table_birch",
            new TableBlock(FabricBlockSettings.copy(Blocks.BIRCH_PLANKS).nonOpaque().burnable()));

    public static final Block JUNGLE_TABLE = registerBlock("table_jungle",
            new TableBlock(FabricBlockSettings.copy(Blocks.JUNGLE_PLANKS).nonOpaque().burnable()));

    public static final Block ACACIA_TABLE = registerBlock("table_acacia",
            new TableBlock(FabricBlockSettings.copy(Blocks.ACACIA_PLANKS).nonOpaque().burnable()));

    public static final Block DARK_OAK_TABLE = registerBlock("table_dark_oak",
            new TableBlock(FabricBlockSettings.copy(Blocks.DARK_OAK_PLANKS).nonOpaque().burnable()));

    public static final Block CRIMSON_TABLE = registerBlock("table_crimson",
            new TableBlock(FabricBlockSettings.copy(Blocks.CRIMSON_PLANKS).nonOpaque().burnable()));

    public static final Block WARPED_TABLE = registerBlock("table_warped",
            new TableBlock(FabricBlockSettings.copy(Blocks.WARPED_PLANKS).nonOpaque().burnable()));

    public static final Block MANGROVE_TABLE = registerBlock("table_mangrove",
            new TableBlock(FabricBlockSettings.copy(Blocks.MANGROVE_PLANKS).nonOpaque().burnable()));

    public static final Block CHERRY_TABLE = registerBlock("table_cherry",
            new TableBlock(FabricBlockSettings.copy(Blocks.CHERRY_PLANKS).nonOpaque().burnable()));

    public static final Block FURLED_TABLE = registerBlock("table_furled",
            new TableBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).nonOpaque().burnable()));



    // --- Chairs ---

    public static final Block OAK_CHAIR = registerBlock("chair_oak",
            new ChairBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).nonOpaque().burnable()));

    public static final Block SPRUCE_CHAIR = registerBlock("chair_spruce",
            new ChairBlock(FabricBlockSettings.copy(Blocks.SPRUCE_PLANKS).nonOpaque().burnable()));

    public static final Block BIRCH_CHAIR = registerBlock("chair_birch",
            new ChairBlock(FabricBlockSettings.copy(Blocks.BIRCH_PLANKS).nonOpaque().burnable()));

    public static final Block JUNGLE_CHAIR = registerBlock("chair_jungle",
            new ChairBlock(FabricBlockSettings.copy(Blocks.JUNGLE_PLANKS).nonOpaque().burnable()));

    public static final Block ACACIA_CHAIR = registerBlock("chair_acacia",
            new ChairBlock(FabricBlockSettings.copy(Blocks.ACACIA_PLANKS).nonOpaque().burnable()));

    public static final Block DARK_OAK_CHAIR = registerBlock("chair_dark_oak",
            new ChairBlock(FabricBlockSettings.copy(Blocks.DARK_OAK_PLANKS).nonOpaque().burnable()));

    public static final Block CRIMSON_CHAIR = registerBlock("chair_crimson",
            new ChairBlock(FabricBlockSettings.copy(Blocks.CRIMSON_PLANKS).nonOpaque().burnable()));

    public static final Block WARPED_CHAIR = registerBlock("chair_warped",
            new ChairBlock(FabricBlockSettings.copy(Blocks.WARPED_PLANKS).nonOpaque().burnable()));

    public static final Block MANGROVE_CHAIR = registerBlock("chair_mangrove",
            new ChairBlock(FabricBlockSettings.copy(Blocks.MANGROVE_PLANKS).nonOpaque().burnable()));

    public static final Block CHERRY_CHAIR = registerBlock("chair_cherry",
            new ChairBlock(FabricBlockSettings.copy(Blocks.CHERRY_PLANKS).nonOpaque().burnable()));

    public static final Block FURLED_CHAIR = registerBlock("chair_furled",
            new ChairBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).nonOpaque().burnable()));



    // --- Stools ---

    public static final Block OAK_STOOL = registerBlock("stool_oak",
            new StoolBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).nonOpaque().burnable()));

    public static final Block SPRUCE_STOOL = registerBlock("stool_spruce",
            new StoolBlock(FabricBlockSettings.copy(Blocks.SPRUCE_PLANKS).nonOpaque().burnable()));

    public static final Block BIRCH_STOOL = registerBlock("stool_birch",
            new StoolBlock(FabricBlockSettings.copy(Blocks.BIRCH_PLANKS).nonOpaque().burnable()));

    public static final Block JUNGLE_STOOL = registerBlock("stool_jungle",
            new StoolBlock(FabricBlockSettings.copy(Blocks.JUNGLE_PLANKS).nonOpaque().burnable()));

    public static final Block ACACIA_STOOL = registerBlock("stool_acacia",
            new StoolBlock(FabricBlockSettings.copy(Blocks.ACACIA_PLANKS).nonOpaque().burnable()));

    public static final Block DARK_OAK_STOOL = registerBlock("stool_dark_oak",
            new StoolBlock(FabricBlockSettings.copy(Blocks.DARK_OAK_PLANKS).nonOpaque().burnable()));

    public static final Block CRIMSON_STOOL = registerBlock("stool_crimson",
            new StoolBlock(FabricBlockSettings.copy(Blocks.CRIMSON_PLANKS).nonOpaque().burnable()));

    public static final Block WARPED_STOOL = registerBlock("stool_warped",
            new StoolBlock(FabricBlockSettings.copy(Blocks.WARPED_PLANKS).nonOpaque().burnable()));

    public static final Block MANGROVE_STOOL = registerBlock("stool_mangrove",
            new StoolBlock(FabricBlockSettings.copy(Blocks.MANGROVE_PLANKS).nonOpaque().burnable()));

    public static final Block CHERRY_STOOL = registerBlock("stool_cherry",
            new StoolBlock(FabricBlockSettings.copy(Blocks.CHERRY_PLANKS).nonOpaque().burnable()));

    public static final Block FURLED_STOOL = registerBlock("stool_furled",
            new StoolBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).nonOpaque().burnable()));





    // ----- REGISTRATION ----- //

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(BetterFurniture.MOD_ID, name), block);
    }

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(BetterFurniture.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(BetterFurniture.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        BetterFurniture.LOGGER.info("Registering blocks for " + BetterFurniture.MOD_ID);
    }
}
