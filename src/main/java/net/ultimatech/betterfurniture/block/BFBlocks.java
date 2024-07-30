package net.ultimatech.betterfurniture.block;

import net.minecraft.block.AbstractBlock;
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
            new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().burnable()));

    public static final Block SPRUCE_TABLE = registerBlock("table_spruce",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).nonOpaque().burnable()));

    public static final Block BIRCH_TABLE = registerBlock("table_birch",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_PLANKS).nonOpaque().burnable()));

    public static final Block JUNGLE_TABLE = registerBlock("table_jungle",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_PLANKS).nonOpaque().burnable()));

    public static final Block ACACIA_TABLE = registerBlock("table_acacia",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_PLANKS).nonOpaque().burnable()));

    public static final Block DARK_OAK_TABLE = registerBlock("table_dark_oak",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.DARK_OAK_PLANKS).nonOpaque().burnable()));

    public static final Block CRIMSON_TABLE = registerBlock("table_crimson",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.CRIMSON_PLANKS).nonOpaque().burnable()));

    public static final Block WARPED_TABLE = registerBlock("table_warped",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.WARPED_PLANKS).nonOpaque().burnable()));

    public static final Block MANGROVE_TABLE = registerBlock("table_mangrove",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_PLANKS).nonOpaque().burnable()));

    public static final Block CHERRY_TABLE = registerBlock("table_cherry",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_PLANKS).nonOpaque().burnable()));

    public static final Block FURLED_TABLE = registerBlock("table_furled",
            new TableBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().burnable()));



    // --- Chairs ---

    public static final Block OAK_CHAIR = registerBlock("chair_oak",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().burnable()));

    public static final Block SPRUCE_CHAIR = registerBlock("chair_spruce",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).nonOpaque().burnable()));

    public static final Block BIRCH_CHAIR = registerBlock("chair_birch",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_PLANKS).nonOpaque().burnable()));

    public static final Block JUNGLE_CHAIR = registerBlock("chair_jungle",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_PLANKS).nonOpaque().burnable()));

    public static final Block ACACIA_CHAIR = registerBlock("chair_acacia",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_PLANKS).nonOpaque().burnable()));

    public static final Block DARK_OAK_CHAIR = registerBlock("chair_dark_oak",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.DARK_OAK_PLANKS).nonOpaque().burnable()));

    public static final Block CRIMSON_CHAIR = registerBlock("chair_crimson",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.CRIMSON_PLANKS).nonOpaque().burnable()));

    public static final Block WARPED_CHAIR = registerBlock("chair_warped",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.WARPED_PLANKS).nonOpaque().burnable()));

    public static final Block MANGROVE_CHAIR = registerBlock("chair_mangrove",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_PLANKS).nonOpaque().burnable()));

    public static final Block CHERRY_CHAIR = registerBlock("chair_cherry",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_PLANKS).nonOpaque().burnable()));

    public static final Block FURLED_CHAIR = registerBlock("chair_furled",
            new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().burnable()));



    // --- Stools ---

    public static final Block OAK_STOOL = registerBlock("stool_oak",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().burnable()));

    public static final Block SPRUCE_STOOL = registerBlock("stool_spruce",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS).nonOpaque().burnable()));

    public static final Block BIRCH_STOOL = registerBlock("stool_birch",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_PLANKS).nonOpaque().burnable()));

    public static final Block JUNGLE_STOOL = registerBlock("stool_jungle",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_PLANKS).nonOpaque().burnable()));

    public static final Block ACACIA_STOOL = registerBlock("stool_acacia",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_PLANKS).nonOpaque().burnable()));

    public static final Block DARK_OAK_STOOL = registerBlock("stool_dark_oak",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.DARK_OAK_PLANKS).nonOpaque().burnable()));

    public static final Block CRIMSON_STOOL = registerBlock("stool_crimson",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.CRIMSON_PLANKS).nonOpaque().burnable()));

    public static final Block WARPED_STOOL = registerBlock("stool_warped",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.WARPED_PLANKS).nonOpaque().burnable()));

    public static final Block MANGROVE_STOOL = registerBlock("stool_mangrove",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_PLANKS).nonOpaque().burnable()));

    public static final Block CHERRY_STOOL = registerBlock("stool_cherry",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_PLANKS).nonOpaque().burnable()));

    public static final Block FURLED_STOOL = registerBlock("stool_furled",
            new StoolBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque().burnable()));





    // ----- REGISTRATION ----- //

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(BetterFurniture.MOD_ID, name), block);
    }

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(BetterFurniture.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(BetterFurniture.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        BetterFurniture.LOGGER.info("Registering blocks for " + BetterFurniture.MOD_ID);
    }
}
