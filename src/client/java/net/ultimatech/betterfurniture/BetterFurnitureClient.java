package net.ultimatech.betterfurniture;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.ultimatech.betterfurniture.block.BFBlocks;
import net.ultimatech.betterfurniture.entity.BFEntityType;
import net.ultimatech.betterfurniture.entity.SeatEntityRenderer;

public class BetterFurnitureClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.OAK_TABLE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.SPRUCE_TABLE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.BIRCH_TABLE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.JUNGLE_TABLE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.ACACIA_TABLE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.DARK_OAK_TABLE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.CRIMSON_TABLE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.WARPED_TABLE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.MANGROVE_TABLE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.CHERRY_TABLE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.FURLED_TABLE, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.OAK_CHAIR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.SPRUCE_CHAIR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.BIRCH_CHAIR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.JUNGLE_CHAIR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.ACACIA_CHAIR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.DARK_OAK_CHAIR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.CRIMSON_CHAIR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.WARPED_CHAIR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.MANGROVE_CHAIR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.CHERRY_CHAIR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BFBlocks.FURLED_CHAIR, RenderLayer.getCutout());

		EntityRendererRegistry.register(BFEntityType.SEAT, SeatEntityRenderer::new);
		EntityRendererRegistry.register(BFEntityType.CHAIR, SeatEntityRenderer::new);
		EntityRendererRegistry.register(BFEntityType.STOOL, SeatEntityRenderer::new);
	}
}