package net.ultimatech.betterfurniture.config.YACL;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.ultimatech.betterfurniture.BetterFurniture;

public class YACLConfig {

    public static ConfigClassHandler<YACLConfig> HANDLER = ConfigClassHandler.createBuilder(YACLConfig.class)
            .id(Identifier.of(BetterFurniture.MOD_ID, "bf_config"))
            .serializer(config -> {
                return GsonConfigSerializerBuilder.create(config)
                        .setPath(FabricLoader.getInstance().getConfigDir().resolve("betterfurniture.json5"))
                        .appendGsonBuilder(GsonBuilder::setPrettyPrinting) // not needed, pretty print by default
                        .setJson5(true)
                        .build();
            })
            .build();

    @SerialEntry(comment = "Defines if any furniture is enabled at all. If set to false, all furniture will be disabled.")
    public static boolean furnitureEnabled = true;

    @SerialEntry
    public static boolean tablesEnabled = true;

    @SerialEntry
    public static boolean chairsEnabled = true;

    @SerialEntry
    public static boolean stoolsEnabled = true;

    public static void save() {
        HANDLER.save();
    }

    public static void load() {
        HANDLER.load();
    }
}
