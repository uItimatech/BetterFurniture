package net.ultimatech.betterfurniture.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.ultimatech.betterfurniture.api.entity.SeatEntity;
import net.ultimatech.betterfurniture.entity.BFEntityType;

public class ChairEntity extends SeatEntity {

    public ChairEntity(EntityType<?> seatEntityEntityType, World world) {
        super(seatEntityEntityType, world);
    }

    public ChairEntity(World world, BlockPos blockPos, Direction direction) {
        super(BFEntityType.CHAIR, world, blockPos, direction);
    }

    public static ActionResult create(World world, BlockPos blockPos, LivingEntity placer, Direction direction) {

        if (world instanceof ServerWorld serverWorld) {
            ChairEntity chair = new ChairEntity(world, blockPos, direction);
            serverWorld.spawnEntity(chair);

            placer.startRiding(chair, true);

            return ActionResult.SUCCESS;
        } else {
            return ActionResult.FAIL;
        }
    }



    @Override
    public void updatePassengerPosition(Entity entity, Entity.PositionUpdater positionUpdater) {
        Vec3d vec3d;

        float offsetAmount = 0.25F;

        float Xoffset = 0.0F;
        float Zoffset = 0.0F;

        switch (this.getMovementDirection()) {
            case NORTH -> Zoffset = -offsetAmount;
            case SOUTH -> Zoffset = offsetAmount;
            case EAST -> Xoffset = offsetAmount;
            case WEST -> Xoffset = -offsetAmount;
        }

        for (Entity passenger : this.getPassengerPile()) {
            vec3d = this.getPassengerRidingPos(passenger);
            positionUpdater.accept(passenger, vec3d.x + Xoffset * (this.getPassengerPile().indexOf(passenger)-0.5), vec3d.y + heightOffset + 0.25 * this.getPassengerPile().indexOf(passenger), vec3d.z + Zoffset * (this.getPassengerPile().indexOf(passenger)-0.5));
            this.clampYaw(passenger);
        }
    }

    @Override
    public void onPassengerLookAround(Entity entity) {
        this.clampYaw(entity);
    }
}
