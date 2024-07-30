package net.ultimatech.betterfurniture.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ultimatech.betterfurniture.BetterFurniture;
import net.ultimatech.betterfurniture.api.entity.SeatEntity;
import net.ultimatech.betterfurniture.entity.custom.*;

public class BFEntityType {

    public static final EntityType<SeatEntity> SEAT = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(BetterFurniture.MOD_ID, "seat"),
            EntityType.Builder.<SeatEntity>create(SeatEntity::new, SpawnGroup.MISC).setDimensions(0.0F, 0.0F).build()
    );

    public static final EntityType<ChairEntity> CHAIR = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(BetterFurniture.MOD_ID, "chair"),
            EntityType.Builder.<ChairEntity>create(ChairEntity::new, SpawnGroup.MISC).setDimensions(0.0F, 0.0F).build()
    );

    public static final EntityType<StoolEntity> STOOL = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(BetterFurniture.MOD_ID, "stool"),
            EntityType.Builder.<StoolEntity>create(StoolEntity::new, SpawnGroup.MISC).setDimensions(0.0F, 0.0F).build()
    );

    public static void register() {
    }
}

