package net.guillian.tutorialmod.client.event;

import net.guillian.tutorialmod.Tutorialmod;
import net.guillian.tutorialmod.client.renderer.ArrowDiggerRenderer;
import net.guillian.tutorialmod.client.renderer.ArrowPoisonAreaRenderer;
import net.guillian.tutorialmod.client.renderer.ArrowTntRenderer;
import net.guillian.tutorialmod.client.renderer.MyEntityRenderer;
import net.guillian.tutorialmod.client.renderer.model.MyEntityModel;
import net.guillian.tutorialmod.common.entity.ArrowPoisonArea;
import net.guillian.tutorialmod.common.entity.ArrowTnt;
import net.guillian.tutorialmod.common.entity.MyEntity;
import net.guillian.tutorialmod.core.init.EntityInit;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Tutorialmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientModEvents {

    private ClientModEvents(){
    }



    @SubscribeEvent
    public static void registerLayerd(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(MyEntityModel.LAYER_LOCATION, MyEntityModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(EntityInit.MYENTITY.get(), MyEntityRenderer::new);
        event.registerEntityRenderer(EntityInit.ARROW_DIGGER.get(), ArrowDiggerRenderer::new);
        event.registerEntityRenderer(EntityInit.ARROW_TNT.get(), ArrowTntRenderer::new);
        event.registerEntityRenderer(EntityInit.ARROW_POISON_AREA.get(), ArrowPoisonAreaRenderer::new);
    }

}
