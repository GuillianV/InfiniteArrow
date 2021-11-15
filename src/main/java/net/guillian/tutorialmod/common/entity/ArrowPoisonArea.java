package net.guillian.tutorialmod.common.entity;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.guillian.tutorialmod.Tutorialmod;
import net.guillian.tutorialmod.core.init.EntityInit;
import net.guillian.tutorialmod.core.init.ItemInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;

public class ArrowPoisonArea extends AbstractArrow {

    private final Item referenceItem;

    public ArrowPoisonArea(EntityType<? extends ArrowPoisonArea> entityType, Level level) {
        super(entityType, level);
        this.referenceItem = ItemInit.ARROW_POISON_AREA_SIMPLE.get();

    }


    public ArrowPoisonArea(LivingEntity shooter, Level level, Item referenceItem) {
        super(EntityInit.ARROW_POISON_AREA.get(),shooter, level);
        this.referenceItem = referenceItem;


    }


    @Mod.EventBusSubscriber(modid = Tutorialmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class Events {
        @SubscribeEvent
        public static void livingTick(ProjectileImpactEvent event) {

            if (event.getEntity() instanceof ArrowPoisonArea){



            }



        }
    }




    @Override
    public void tick() {


        super.tick();

        boolean tickDestroyedBlock = false;


        Vec3 deltaMovement = this.getDeltaMovement();
        Vec3 position = this.position();
        Vec3 newPosition = position.add(deltaMovement);


        if (this.inGround){
            AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level, this.getX(), this.getY(), this.getZ());
          /*  Entity entity = this.getOwner();
            if (entity instanceof LivingEntity) {
                areaeffectcloud.setOwner((LivingEntity)entity);
            }*/

            areaeffectcloud.setParticle(ParticleTypes.DRAGON_BREATH);
            areaeffectcloud.setRadius(3.0F);
            areaeffectcloud.setDuration(600);
            areaeffectcloud.setRadiusPerTick((7.0F - areaeffectcloud.getRadius()) / (float)areaeffectcloud.getDuration());
            MobEffectInstance mobEffect = new MobEffectInstance(MobEffects.HARM, 1, 0,true,true,true);
            areaeffectcloud.addEffect(mobEffect);
            this.level.addFreshEntity(areaeffectcloud);
            this.discard();
        }


    }


    @Override
    public ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }


}
