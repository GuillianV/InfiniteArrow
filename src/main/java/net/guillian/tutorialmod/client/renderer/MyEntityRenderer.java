package net.guillian.tutorialmod.client.renderer;

import net.guillian.tutorialmod.Tutorialmod;
import net.guillian.tutorialmod.client.renderer.model.MyEntityModel;
import net.guillian.tutorialmod.common.entity.MyEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MyEntityRenderer <Type extends MyEntity> extends MobRenderer<Type, MyEntityModel<Type>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Tutorialmod.MOD_ID,"textures/entities/myentity.png");

    public MyEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new MyEntityModel<>(context.bakeLayer(MyEntityModel.LAYER_LOCATION)),0.5f);
    }



    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return TEXTURE;
    }
}
