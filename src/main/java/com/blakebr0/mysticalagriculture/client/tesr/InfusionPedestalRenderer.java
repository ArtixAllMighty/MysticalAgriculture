package com.blakebr0.mysticalagriculture.client.tesr;

import com.blakebr0.mysticalagriculture.tileentity.InfusionPedestalTileEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class InfusionPedestalRenderer extends TileEntityRenderer<InfusionPedestalTileEntity> {
    @Override
    public void render(InfusionPedestalTileEntity tile, double x, double y, double z, float partialTicks, int destroyStage) {
        ItemStack stack = tile.getInventory().getStackInSlot(0);
        if (!stack.isEmpty()) {
            GlStateManager.pushMatrix();
            GlStateManager.translated(x + 0.5D, y + 1.2D, z + 0.5D);
            float scale = stack.getItem() instanceof BlockItem ? 0.95F : 0.75F;
            GlStateManager.scalef(scale, scale, scale);
            double tick = System.currentTimeMillis() / 800.0D;
            GlStateManager.translated(0.0D, Math.sin(tick % (2 * Math.PI)) * 0.065D, 0.0D);
            GlStateManager.rotatef((float) ((tick * 40.0D) % 360), 0, 1, 0);
            GlStateManager.disableLighting();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.GROUND);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }
}

