package net.guillian.tutorialmod.client.renderer;

import net.guillian.tutorialmod.Tutorialmod;
import net.guillian.tutorialmod.common.entity.ArrowTnt;
import net.guillian.tutorialmod.common.entity.ArrowTracking;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ArrowTrackingRenderer extends ArrowRenderer<ArrowTracking> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Tutorialmod.MOD_ID,"textures/entities/projectiles/arrow_tracking.png");



    public ArrowTrackingRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(ArrowTracking arrowTracking) {
        return TEXTURE;
    }


}