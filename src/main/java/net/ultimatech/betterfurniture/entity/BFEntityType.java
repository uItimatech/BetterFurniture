package net.ultimatech.betterfurniture.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
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
            new Identifier(BetterFurniture.MOD_ID, "seat"),
            FabricEntityTypeBuilder.<SeatEntity>create(SpawnGroup.MISC, SeatEntity::new).dimensions(EntityDimensions.fixed(0.0F, 0.0F)).build()
    );

    public static final EntityType<ChairEntity> CHAIR = Registry.register(Registries.ENTITY_TYPE,
        new Identifier(BetterFurniture.MOD_ID, "chair"),
        FabricEntityTypeBuilder.<ChairEntity>create(SpawnGroup.MISC, ChairEntity::new).dimensions(EntityDimensions.fixed(0.0F, 0.0F)).build()
    );

    public static final EntityType<StoolEntity> STOOL = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(BetterFurniture.MOD_ID, "stool"),
            FabricEntityTypeBuilder.<StoolEntity>create(SpawnGroup.MISC, StoolEntity::new).dimensions(EntityDimensions.fixed(0.0F, 0.0F)).build()
    );
}

