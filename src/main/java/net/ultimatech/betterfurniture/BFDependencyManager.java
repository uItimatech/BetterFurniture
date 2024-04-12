package net.ultimatech.betterfurniture;

public class BFDependencyManager {
    public static boolean isEchoingWildsInstalled() {
        try {
            Class.forName("net.ultimatech.echoingwilds.EchoingWilds");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isYacl3Installed() {
        try {
            Class.forName("dev.isxander.yacl3.config.v2.api.ConfigClassHandler");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}