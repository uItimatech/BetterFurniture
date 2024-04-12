package net.ultimatech.betterfurniture.api.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.ultimatech.betterfurniture.BetterFurniture;
import net.ultimatech.betterfurniture.api.block.SeatBlock;
import net.ultimatech.betterfurniture.entity.BFEntityType;

import java.util.List;
import java.util.Objects;

import static net.ultimatech.betterfurniture.api.block.SeatBlock.OCCUPIED;


public class SeatEntity extends Entity {

    protected static float heightOffset = -0.45F;

    private static final ImmutableMap<EntityPose, ImmutableList<Integer>> DISMOUNT_FREE_Y_SPACES_NEEDED = ImmutableMap.of(
            EntityPose.STANDING, ImmutableList.of(0, 1, -1), EntityPose.CROUCHING, ImmutableList.of(0, 1, -1), EntityPose.SWIMMING, ImmutableList.of(0, 1)
    );

    public SeatEntity(EntityType<?> seatEntityEntityType, World world) {
        super(seatEntityEntityType, world);
        this.noClip = true;
    }

    public SeatEntity(World world) {
        super(BFEntityType.SEAT, world);
        this.noClip = true;
    }

    public SeatEntity(World world, BlockPos blockPos, Direction direction) {
        this(world);
        this.setPosition(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5);
        this.setRotation(direction.getOpposite().asRotation(), 0F);
        this.noClip = true;
    }

    public SeatEntity(EntityType<?> seatEntityEntityType, World world, BlockPos blockPos, Direction direction) {
        super(seatEntityEntityType, world);
        this.setPosition(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5);
        this.setRotation(direction.getOpposite().asRotation(), 0F);
        this.noClip = true;
    }

    @Override
    protected void initDataTracker() {
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        SeatEntity.heightOffset = nbt.getFloat("HeightOffset");
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putFloat("HeightOffset", SeatEntity.heightOffset);
    }

    @Override
    public void tick() {
        super.tick();

        if(!this.getWorld().isClient()) {
            if (this.getPassengerList().isEmpty() || !(this.getWorld().getBlockState(this.getBlockPos()).getBlock() instanceof SeatBlock)) {

                if (this.getWorld().getBlockState(this.getBlockPos()).getBlock() instanceof SeatBlock) {
                    this.getWorld().setBlockState(this.getBlockPos(), this.getWorld().getBlockState(this.getBlockPos()).with(OCCUPIED, false));
                }

                this.remove(RemovalReason.DISCARDED);
                this.getWorld().updateNeighbors(this.getBlockPos(), this.getWorld().getBlockState(this.getBlockPos()).getBlock());
            }
        }
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }



    public static ActionResult create(World world, BlockPos blockPos, LivingEntity placer, Direction direction) {

        if (world instanceof ServerWorld serverWorld) {
            SeatEntity seat = new SeatEntity(world, blockPos, direction);
            serverWorld.spawnEntity(seat);

            placer.startRiding(seat, true);

            return ActionResult.SUCCESS;
        } else {
            return ActionResult.FAIL;
        }
    }


    @Override
    public Vec3d updatePassengerForDismount(LivingEntity entity) {
        Direction direction = this.getMovementDirection();

        if (direction.getAxis() == Direction.Axis.Y) {
            return super.updatePassengerForDismount(entity);

        } else {
            int[][] is = Dismounting.getDismountOffsets(direction);
            BlockPos blockPos = this.getBlockPos();
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            ImmutableList<EntityPose> immutableList = entity.getPoses();

            for(EntityPose entityPose : immutableList) {

                EntityDimensions entityDimensions = entity.getDimensions(entityPose);
                float f = Math.min(entityDimensions.width, 1.0F) / 2.0F;

                for(int i : Objects.requireNonNull(DISMOUNT_FREE_Y_SPACES_NEEDED.get(entityPose))) {

                    for(int[] js : is) {
                        mutable.set(blockPos.getX() + js[0], blockPos.getY() + 1 + i, blockPos.getZ() + js[1]);
                        double d = this.getWorld().getDismountHeight(Dismounting.getCollisionShape(this.getWorld(), mutable), () -> Dismounting.getCollisionShape(this.getWorld(), mutable.down()));

                        if (Dismounting.canDismountInBlock(d)) {
                            Box box = new Box((-f), 0.0, (-f), f, entityDimensions.height, f);
                            Vec3d vec3d = Vec3d.ofCenter(mutable, d);

                            if (Dismounting.canPlaceEntityAt(this.getWorld(), entity, box.offset(vec3d))) {
                                entity.setPose(entityPose);

                                return vec3d;
                            }
                        }
                    }
                }
            }


            double e = this.getBoundingBox().maxY;
            mutable.set(blockPos.getX(), e, blockPos.getZ());

            for(EntityPose entityPose2 : immutableList) {
                double g = entity.getDimensions(entityPose2).height;
                int j = MathHelper.ceil(e - (double)mutable.getY() + g);
                double h = Dismounting.getCeilingHeight(mutable, j, pos -> this.getWorld().getBlockState(pos.up()).getCollisionShape(this.getWorld(), pos.up()));

                if (e + g <= h) {
                    entity.setPose(entityPose2);
                    break;
                }
            }

            return new Vec3d(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5);
        }
    }



    // Returns a list of entities that are riding each other, the first being the one riding this entity
    protected List<Entity> getPassengerPile() {
        List<Entity> passengerPile = super.getPassengerList();

        return passengerPile;
    }



    public void addPassengerOnTopOfPassengerPile(Entity entity) {

        int pileSize = this.getPassengerPile().size();

        // Checks if the entity is already in the pile
        for (Entity passenger : this.getPassengerPile()) {
            if (passenger.equals(entity)) {
                return;
            }
        }

        entity.startRiding(this, true);
    }



    @Override
    public void updatePassengerPosition(Entity entity, PositionUpdater positionUpdater) {
        Vec3d vec3d;

        float offsetAmount = 0.25F;

        double Xoffset = offsetAmount * Math.sin(-this.getFirstPassenger().getBodyYaw()*2*Math.PI/360);
        double Zoffset = offsetAmount * Math.cos(-this.getFirstPassenger().getBodyYaw()*2*Math.PI/360);

        for (Entity passenger : this.getPassengerPile()) {
            vec3d = this.getPassengerRidingPos(passenger);
            positionUpdater.accept(passenger, vec3d.x + Xoffset * this.getPassengerPile().indexOf(passenger), vec3d.y + heightOffset + 0.25 * this.getPassengerPile().indexOf(passenger), vec3d.z + Zoffset * this.getPassengerPile().indexOf(passenger));

            if (this.getPassengerPile().indexOf(passenger)>0) {
                Entity previousPassenger = this.getPassengerPile().get(this.getPassengerPile().indexOf(passenger)-1);
                this.clampYaw(previousPassenger, passenger);
            }
        }
    }

    protected void clampYaw(Entity host, Entity passenger) {
        passenger.setBodyYaw(host.getBodyYaw());
        float wrappedYaw = MathHelper.wrapDegrees(passenger.getYaw() - host.getBodyYaw());
        float clampedYaw = MathHelper.clamp(wrappedYaw, -95.0F, 95.0F);
        passenger.prevYaw += clampedYaw - wrappedYaw;
        passenger.setYaw(passenger.getYaw() + clampedYaw - wrappedYaw);
        passenger.setHeadYaw(passenger.getYaw());
    }

    protected void clampYaw(Entity passenger) {
        passenger.setBodyYaw(this.getYaw());
        float wrappedYaw = MathHelper.wrapDegrees(passenger.getYaw() - this.getYaw());
        float clampedYaw = MathHelper.clamp(wrappedYaw, -95.0F, 95.0F);
        passenger.prevYaw += clampedYaw - wrappedYaw;
        passenger.setYaw(passenger.getYaw() + clampedYaw - wrappedYaw);
        passenger.setHeadYaw(passenger.getYaw());
    }
}
