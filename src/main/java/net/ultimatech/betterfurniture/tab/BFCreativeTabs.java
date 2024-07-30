package net.ultimatech.betterfurniture.tab;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.ultimatech.betterfurniture.BFDependencyManager;
import net.ultimatech.betterfurniture.BetterFurniture;
import net.ultimatech.betterfurniture.block.BFBlocks;
import net.ultimatech.betterfurniture.config.BFConfig;

public class BFCreativeTabs {

    @SuppressWarnings("unused")
    public static final ItemGroup BETTER_FURNITURE = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(BetterFurniture.MOD_ID, "better_furniture"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.better_furniture"))
                    .icon(() -> new ItemStack(BFBlocks.OAK_TABLE)).entries((displayContext, entries) -> {

                        BFConfig.load();

                        if (BFConfig.tablesEnabled) {
                            entries.add(BFBlocks.OAK_TABLE);
                            entries.add(BFBlocks.SPRUCE_TABLE);
                            entries.add(BFBlocks.BIRCH_TABLE);
                            entries.add(BFBlocks.JUNGLE_TABLE);
                            entries.add(BFBlocks.ACACIA_TABLE);
                            entries.add(BFBlocks.DARK_OAK_TABLE);
                            entries.add(BFBlocks.CRIMSON_TABLE);
                            entries.add(BFBlocks.WARPED_TABLE);
                            entries.add(BFBlocks.MANGROVE_TABLE);
                            entries.add(BFBlocks.CHERRY_TABLE);

                            if (BFDependencyManager.isEchoingWildsInstalled()) {
                                entries.add(BFBlocks.FURLED_TABLE);
                            }
                        }

                        if (BFConfig.chairsEnabled) {

                            entries.add(BFBlocks.OAK_CHAIR);
                            entries.add(BFBlocks.SPRUCE_CHAIR);
                            entries.add(BFBlocks.BIRCH_CHAIR);
                            entries.add(BFBlocks.JUNGLE_CHAIR);
                            entries.add(BFBlocks.ACACIA_CHAIR);
                            entries.add(BFBlocks.DARK_OAK_CHAIR);
                            entries.add(BFBlocks.CRIMSON_CHAIR);
                            entries.add(BFBlocks.WARPED_CHAIR);
                            entries.add(BFBlocks.MANGROVE_CHAIR);
                            entries.add(BFBlocks.CHERRY_CHAIR);

                            if (BFDependencyManager.isEchoingWildsInstalled()) {
                                entries.add(BFBlocks.FURLED_CHAIR);
                            }
                        }

                        if (BFConfig.stoolsEnabled) {
                            entries.add(BFBlocks.OAK_STOOL);
                            entries.add(BFBlocks.SPRUCE_STOOL);
                            entries.add(BFBlocks.BIRCH_STOOL);
                            entries.add(BFBlocks.JUNGLE_STOOL);
                            entries.add(BFBlocks.ACACIA_STOOL);
                            entries.add(BFBlocks.DARK_OAK_STOOL);
                            entries.add(BFBlocks.CRIMSON_STOOL);
                            entries.add(BFBlocks.WARPED_STOOL);
                            entries.add(BFBlocks.MANGROVE_STOOL);
                            entries.add(BFBlocks.CHERRY_STOOL);

                            if (BFDependencyManager.isEchoingWildsInstalled()) {
                                entries.add(BFBlocks.FURLED_STOOL);
                            }
                        }

                    }).build());

    // ----- REGISTRATION ----- //
    public static void registerItemGroups() {
        BetterFurniture.LOGGER.info("Registering item groups for " + BetterFurniture.MOD_ID);
    }
}
