package net.guillian.tutorialmod.core.init;

import net.guillian.tutorialmod.Tutorialmod;
import net.guillian.tutorialmod.common.entity.ArrowDigger;
import net.guillian.tutorialmod.common.entity.ArrowPoisonArea;
import net.guillian.tutorialmod.common.entity.ArrowTnt;
import net.guillian.tutorialmod.common.entity.MyEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.swing.text.html.parser.Entity;

public final class EntityInit {

    private  EntityInit(){}

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Tutorialmod.MOD_ID);

    public static  final RegistryObject<EntityType<MyEntity>> MYENTITY = ENTITIES.register("myentity",()-> EntityType.Builder.of(MyEntity::new, MobCategory.CREATURE).sized(0.5f,0.5f).build(new ResourceLocation(Tutorialmod.MOD_ID,"myentity").toString()));

    //Arrows
    public static  final RegistryObject<EntityType<ArrowDigger>> ARROW_DIGGER = ENTITIES.register("arrow_digger",()-> EntityType.Builder.<ArrowDigger>of(ArrowDigger::new,MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(Tutorialmod.MOD_ID,"arrow_digger").toString()));
    public static  final RegistryObject<EntityType<ArrowTnt>> ARROW_TNT = ENTITIES.register("arrow_tnt",()-> EntityType.Builder.<ArrowTnt>of(ArrowTnt::new,MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(Tutorialmod.MOD_ID,"arrow_tnt").toString()));
    public static  final RegistryObject<EntityType<ArrowPoisonArea>> ARROW_POISON_AREA = ENTITIES.register("arrow_poison_area",()-> EntityType.Builder.<ArrowPoisonArea>of(ArrowPoisonArea::new,MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(Tutorialmod.MOD_ID,"arrow_poison_area").toString()));

}
