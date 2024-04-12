package net.ultimatech.betterfurniture.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.ultimatech.betterfurniture.api.entity.SeatEntity;
import net.ultimatech.betterfurniture.entity.BFEntityType;

public class StoolEntity extends SeatEntity {

    public StoolEntity(EntityType<?> seatEntityEntityType, World world) {
        super(seatEntityEntityType, world);
    }

    public StoolEntity(World world, BlockPos blockPos, Direction direction) {
        super(BFEntityType.STOOL, world, blockPos, direction);
    }

    public static ActionResult create(World world, BlockPos blockPos, LivingEntity placer, Direction direction) {

        if (world instanceof ServerWorld serverWorld) {
            StoolEntity stool = new StoolEntity(world, blockPos, direction);
            serverWorld.spawnEntity(stool);

            placer.startRiding(stool, true);

            return ActionResult.SUCCESS;
        } else {
            return ActionResult.FAIL;
        }
    }
}
