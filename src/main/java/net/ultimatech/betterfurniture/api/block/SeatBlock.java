package net.ultimatech.betterfurniture.api.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.ultimatech.betterfurniture.BetterFurniture;
import net.ultimatech.betterfurniture.api.entity.SeatEntity;

import java.util.List;

public class SeatBlock extends Block implements Waterloggable {

    public static final BooleanProperty OCCUPIED = BooleanProperty.of("occupied");
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public SeatBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(WATERLOGGED, false)
                .with(OCCUPIED, false));
    }

    public static final MapCodec<SeatBlock> CODEC = createCodec(SeatBlock::new);

    @Override
    public MapCodec<SeatBlock> getCodec() {
        return CODEC;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState()
                .with(WATERLOGGED, context.getWorld().getFluidState(context.getBlockPos()).getFluid() == Fluids.WATER)
                .with(OCCUPIED, false);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, OCCUPIED);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        return hasTopRim(world, blockPos) || sideCoversSmallSquare(world, blockPos, Direction.UP);
    }

    @Override
    protected ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, BlockHitResult hit) {

        if (!player.shouldCancelInteraction() && world instanceof ServerWorld serverWorld) {

            // Get the seat entity at the block position to mount the new player on the already seated player (to make it look like they're sitting on each other)
            List<SeatEntity> seatEntities = serverWorld.getEntitiesByClass(SeatEntity.class, new Box(blockPos).expand(0.5), entity -> entity.getBlockPos().equals(blockPos));

            if (!blockState.get(OCCUPIED) || seatEntities.isEmpty()) {

                // Create a new seat entity
                serverWorld.setBlockState(blockPos, blockState.with(OCCUPIED, true));

                //BetterFurniture.LOGGER.info("Creating seat entity at block position: " + blockPos);
                return SeatEntity.create(serverWorld, blockPos, player, Direction.NORTH);

            } else {

                SeatEntity chairEntity = seatEntities.get(0);

                // Mount the player on the seat entity
                chairEntity.addPassengerOnTopOfPassengerPile(player);
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return direction == Direction.DOWN && !state.canPlaceAt(world, pos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    // ----- RENDERING ----- //
    public boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return !state.get(WATERLOGGED);
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState) {return BlockRenderType.MODEL;}

    @Override
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0F;
    }

    @Override
    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    protected VoxelShape getShape(BlockState state) {
        return VoxelShapes.empty();
    }
    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return this.getShape(state);
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.getShape(state);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.getShape(state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.getShape(state);
    }
}
