package net.guillian.tutorialmod.client.renderer;

import net.guillian.tutorialmod.Tutorialmod;
import net.guillian.tutorialmod.common.entity.ArrowPoisonArea;
import net.guillian.tutorialmod.common.entity.ArrowTnt;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ArrowPoisonAreaRenderer extends ArrowRenderer<ArrowPoisonArea> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Tutorialmod.MOD_ID,"textures/entities/projectiles/arrow_poison_area.png");



    public ArrowPoisonAreaRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(ArrowPoisonArea arrowPoisonArea) {
        return TEXTURE;
    }


}