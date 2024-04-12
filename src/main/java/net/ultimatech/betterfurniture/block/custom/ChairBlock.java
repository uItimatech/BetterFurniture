package net.ultimatech.betterfurniture.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import net.ultimatech.betterfurniture.BetterFurniture;
import net.ultimatech.betterfurniture.api.block.SeatBlock;
import net.ultimatech.betterfurniture.entity.custom.ChairEntity;

import java.util.List;

public class ChairBlock extends SeatBlock {

    VoxelShape LEG_NE = Block.createCuboidShape(2.0, 0.0, 2.0, 4.0, 8, 4);
    VoxelShape LEG_NW = Block.createCuboidShape(12.0, 0.0, 2.0, 14.0, 8, 4);
    VoxelShape LEG_SE = Block.createCuboidShape(2.0, 0.0, 12.0, 4.0, 8, 14);
    VoxelShape LEG_SW = Block.createCuboidShape(12.0, 0.0, 12.0, 14.0, 8, 14);

    VoxelShape LEGS = VoxelShapes.union(LEG_NE, LEG_NW, LEG_SE, LEG_SW);

    VoxelShape BASE = Block.createCuboidShape(2.0, 8.0, 2.0, 14.0, 10.0, 14.0);

    VoxelShape BACK_NORTH = Block.createCuboidShape(2.0, 10.0, 12.0, 14.0, 20.0, 14.0);
    VoxelShape BACK_SOUTH = Block.createCuboidShape(2.0, 10.0, 2.0, 14.0, 20.0, 4.0);
    VoxelShape BACK_EAST = Block.createCuboidShape(2.0, 10.0, 2.0, 4.0, 20.0, 14.0);
    VoxelShape BACK_WEST = Block.createCuboidShape(12.0, 10.0, 2.0, 14.0, 20.0, 14.0);

    VoxelShape SHAPE_NORTH = VoxelShapes.union(BASE, LEGS, BACK_NORTH);
    VoxelShape SHAPE_SOUTH = VoxelShapes.union(BASE, LEGS, BACK_SOUTH);
    VoxelShape SHAPE_EAST = VoxelShapes.union(BASE, LEGS, BACK_EAST);
    VoxelShape SHAPE_WEST = VoxelShapes.union(BASE, LEGS, BACK_WEST);

    public static final DirectionProperty FACING = DirectionProperty.of("facing", Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);
    public static final BooleanProperty OCCUPIED = BooleanProperty.of("occupied");
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public ChairBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(WATERLOGGED, false)
                .with(FACING, Direction.NORTH)
                .with(OCCUPIED, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState()
                .with(FACING, context.getHorizontalPlayerFacing().getOpposite())
                .with(WATERLOGGED, context.getWorld().getFluidState(context.getBlockPos()).getFluid() == Fluids.WATER)
                .with(OCCUPIED, false);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, OCCUPIED);
    }



    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (!player.shouldCancelInteraction() && world instanceof ServerWorld serverWorld) {

            // Get the seat entity at the block position to mount the new player on the already seated player (to make it look like they're sitting on each other)
            List<ChairEntity> chairEntities = serverWorld.getEntitiesByClass(ChairEntity.class, new Box(blockPos).expand(0.5), entity -> entity.getBlockPos().equals(blockPos));

            if (!blockState.get(OCCUPIED) || chairEntities.isEmpty()) {

                // Create a new seat entity
                serverWorld.setBlockState(blockPos, blockState.with(OCCUPIED, true));

                BetterFurniture.LOGGER.info("Creating stool entity at block position: " + blockPos);
                return ChairEntity.create(serverWorld, blockPos, player, blockState.get(FACING).getOpposite());

            } else {

                ChairEntity chairEntity = chairEntities.get(0);

                // Mount the player on the seat entity
                chairEntity.addPassengerOnTopOfPassengerPile(player);
            }
        }
        return ActionResult.PASS;
    }



    // ----- RENDERING ----- //

    @Override
    protected VoxelShape getShape(BlockState state) {
        return switch (state.get(FACING)) {
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
            default -> VoxelShapes.fullCube();
        };
    }
}
