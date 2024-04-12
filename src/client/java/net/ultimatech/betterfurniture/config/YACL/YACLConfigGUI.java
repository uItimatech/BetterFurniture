package net.ultimatech.betterfurniture.config.YACL;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.ultimatech.betterfurniture.tab.BFCreativeTabs;

import static net.ultimatech.betterfurniture.config.YACL.YACLConfig.*;

@Environment(EnvType.CLIENT)
public class YACLConfigGUI {

    public static Screen create(Screen parentScreen) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.of("Better Furniture"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.of("Better Furniture Blocks"))
                        .tooltip(Text.of("Options regarding the blocks that may be redundant with other mods."))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Furniture"))
                                .description(OptionDescription.of(Text.literal("Change here whether or not furniture can be crafted and appear in the creative menu.")))

                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enable Tables"))
                                        .description(OptionDescription.of(Text.literal("Whether or not tables can be crafted and appear in the creative menu.")))
                                        .binding(true, () -> tablesEnabled, newVal -> tablesEnabled = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())


                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enable Chairs"))
                                        .description(OptionDescription.of(Text.literal("Whether or not chairs can be crafted and appear in the creative menu.")))
                                        .binding(true, () -> chairsEnabled, newVal -> chairsEnabled = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())

                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enable Stools"))
                                        .description(OptionDescription.of(Text.literal("Whether or not stools can be crafted and appear in the creative menu.")))
                                        .binding(true, () -> stoolsEnabled, newVal -> stoolsEnabled = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())

                                .build())
                        .build())
                .save(new Runnable () {
                    @Override
                    public void run() {
                        furnitureEnabled = tablesEnabled || chairsEnabled || stoolsEnabled;

                        YACLConfig.save();

                        BFCreativeTabs.registerItemGroups();
                    }

                })
                .build()
                .generateScreen(parentScreen);
    }
}