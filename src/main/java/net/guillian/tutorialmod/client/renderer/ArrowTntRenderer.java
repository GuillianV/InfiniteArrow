package net.guillian.tutorialmod.client.renderer;

import net.guillian.tutorialmod.Tutorialmod;
import net.guillian.tutorialmod.common.entity.ArrowDigger;
import net.guillian.tutorialmod.common.entity.ArrowTnt;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ArrowTntRenderer extends ArrowRenderer<ArrowTnt> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Tutorialmod.MOD_ID,"textures/entities/projectiles/arrow_tnt.png");



    public ArrowTntRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(ArrowTnt arrowTnt) {
        return TEXTURE;
    }


}