package net.ultimatech.betterfurniture.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.ultimatech.betterfurniture.BFDependencyManager;
import net.ultimatech.betterfurniture.BetterFurniture;
import net.ultimatech.betterfurniture.config.YACL.YACLConfigGUI;

@Environment(EnvType.CLIENT)
public class ModMenuApiImpl implements ModMenuApi
{
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (BFDependencyManager.isYacl3Installed()) {
            return YACLConfigGUI::create;
        }
        BetterFurniture.LOGGER.warn("YetAnotherConfigLib is not installed, the mod config will not be available.");
        return null;
    }
}