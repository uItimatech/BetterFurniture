package net.ultimatech.betterfurniture.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.ultimatech.betterfurniture.api.block.ConnectedHorizontalBlock;

public class TableBlock extends ConnectedHorizontalBlock implements Waterloggable {
    private static final VoxelShape TABLE_TOP = Block.createCuboidShape(0, 14, 0, 16, 16, 16);
    private static final VoxelShape TABLE_FEET_NW = Block.createCuboidShape(1, 0, 1, 3, 14, 3);
    private static final VoxelShape TABLE_FEET_NE = Block.createCuboidShape(13, 0, 1, 15, 14, 3);
    private static final VoxelShape TABLE_FEET_SE = Block.createCuboidShape(13, 0, 13, 15, 14, 15);
    private static final VoxelShape TABLE_FEET_SW = Block.createCuboidShape(1, 0, 13, 3, 14, 15);

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public TableBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(WATERLOGGED, false));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(WATERLOGGED));
    }

    @Override
    public VoxelShape getShape(BlockState state) {

        VoxelShape shape = TABLE_TOP;

        if (!state.get(NORTH) && !state.get(EAST)) {
            shape = VoxelShapes.union(shape, TABLE_FEET_NE);
        }
        if (!state.get(NORTH) && !state.get(WEST)) {
            shape = VoxelShapes.union(shape, TABLE_FEET_NW);
        }
        if (!state.get(SOUTH) && !state.get(EAST)) {
            shape = VoxelShapes.union(shape, TABLE_FEET_SE);
        }
        if (!state.get(SOUTH) && !state.get(WEST)) {
            shape = VoxelShapes.union(shape, TABLE_FEET_SW);
        }

        return shape;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(WATERLOGGED, context.getWorld().getFluidState(context.getBlockPos()).getFluid() == Fluids.WATER);
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

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return switch (type) {
            case LAND -> false;
            case WATER -> world.getFluidState(pos).isIn(FluidTags.WATER);
            case AIR -> false;
            default -> false;
        };
    }

    @Override
    public boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return !state.get(WATERLOGGED);
    }

    @Override
    public boolean isSolidBlock(BlockView world, BlockPos pos) {
        return false;
    }
}
