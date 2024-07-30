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
import net.ultimatech.betterfurniture.api.block.SeatBlock;
import net.ultimatech.betterfurniture.BetterFurniture;
import net.ultimatech.betterfurniture.entity.custom.StoolEntity;

import java.util.List;

public class StoolBlock extends SeatBlock {

    VoxelShape LEG_NE = Block.createCuboidShape(4.0, 0.0, 4.0, 6.0, 8, 6);
    VoxelShape LEG_NW = Block.createCuboidShape(10.0, 0.0, 4.0, 12.0, 8, 6);
    VoxelShape LEG_SE = Block.createCuboidShape(4.0, 0.0, 10.0, 6.0, 8, 12);
    VoxelShape LEG_SW = Block.createCuboidShape(10.0, 0.0, 10.0, 12.0, 8, 12);

    VoxelShape LEGS = VoxelShapes.union(LEG_NE, LEG_NW, LEG_SE, LEG_SW);

    VoxelShape BASE = Block.createCuboidShape(3.0, 8.0, 3.0, 13.0, 10.0, 13.0);

    VoxelShape SHAPE = VoxelShapes.union(BASE, LEGS);

    public static final DirectionProperty FACING = DirectionProperty.of("facing", Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);

    public StoolBlock(Settings settings) {
        super(settings);
        this.setDefaultState(super.getDefaultState()
                .with(FACING, Direction.NORTH));
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
        super.appendProperties(builder);
        builder.add(FACING);
    }


    @Override
    protected ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, BlockHitResult hit) {

        if (!player.shouldCancelInteraction() && world instanceof ServerWorld serverWorld) {

            // Get the seat entity at the block position to mount the new player on the already seated player (to make it look like they're sitting on each other)
            List<StoolEntity> stoolEntities = serverWorld.getEntitiesByClass(StoolEntity.class, new Box(blockPos).expand(0.5), entity -> entity.getBlockPos().equals(blockPos));

            if (!blockState.get(OCCUPIED) || stoolEntities.isEmpty()) {

                // Create a new seat entity
                serverWorld.setBlockState(blockPos, blockState.with(OCCUPIED, true));

                BetterFurniture.LOGGER.info("Creating stool entity at block position: " + blockPos);
                return StoolEntity.create(serverWorld, blockPos, player, blockState.get(FACING).getOpposite());

            } else {

                StoolEntity stoolEntity = stoolEntities.get(0);

                // Mount the player on the seat entity
                stoolEntity.addPassengerOnTopOfPassengerPile(player);
            }
        }
        return ActionResult.PASS;
    }





    // ----- RENDERING ----- //

    @Override
    protected VoxelShape getShape(BlockState state) {
        return SHAPE;
    }
}
