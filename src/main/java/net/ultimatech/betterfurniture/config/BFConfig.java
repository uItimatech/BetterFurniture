package net.ultimatech.betterfurniture.config;

import net.ultimatech.betterfurniture.BFDependencyManager;

public class BFConfig {

    public static boolean furnitureEnabled = true;

    public static boolean tablesEnabled = true;

    public static boolean chairsEnabled = true;

    public static boolean stoolsEnabled = true;

    public static void save() {
        if (BFDependencyManager.isYacl3Installed()) {
            net.ultimatech.betterfurniture.config.YACL.YACLConfig.save();
            furnitureEnabled = net.ultimatech.betterfurniture.config.YACL.YACLConfig.furnitureEnabled;
            tablesEnabled = net.ultimatech.betterfurniture.config.YACL.YACLConfig.tablesEnabled;
            chairsEnabled = net.ultimatech.betterfurniture.config.YACL.YACLConfig.chairsEnabled;
            stoolsEnabled = net.ultimatech.betterfurniture.config.YACL.YACLConfig.stoolsEnabled;
        }
    }

    public static void load() {
        if (BFDependencyManager.isYacl3Installed()) {
            net.ultimatech.betterfurniture.config.YACL.YACLConfig.load();
            furnitureEnabled = net.ultimatech.betterfurniture.config.YACL.YACLConfig.furnitureEnabled;
            tablesEnabled = net.ultimatech.betterfurniture.config.YACL.YACLConfig.tablesEnabled;
            chairsEnabled = net.ultimatech.betterfurniture.config.YACL.YACLConfig.chairsEnabled;
            stoolsEnabled = net.ultimatech.betterfurniture.config.YACL.YACLConfig.stoolsEnabled;
        }
    }
}
