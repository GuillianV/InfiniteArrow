package net.guillian.tutorialmod.common.entity;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.guillian.tutorialmod.Tutorialmod;
import net.guillian.tutorialmod.common.utils.SphereCalculator;
import net.guillian.tutorialmod.core.init.EntityInit;
import net.guillian.tutorialmod.core.init.ItemInit;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;

public class ArrowPoisonArea extends AbstractArrow {

    private final Item referenceItem;
    private int areaRadius;
    private int areaDuration;
    private int effectDuration;
    private int effectAmplifier;

    public ArrowPoisonArea(EntityType<? extends ArrowPoisonArea> entityType, Level level) {
        super(entityType, level);
        this.referenceItem = ItemInit.ARROW_POISON_AREA_SIMPLE.get();
    }


    public ArrowPoisonArea(LivingEntity shooter, Level level, Item referenceItem) {
        super(EntityInit.ARROW_POISON_AREA.get(),shooter, level);
        this.referenceItem = referenceItem;
    }


    public void setAreaRadius(int areaRadius){
        this.areaRadius = areaRadius;
    }
    public void setAreaDuration(int areaDuration){
        this.areaDuration = areaDuration;
    }
    public void setEffectDuration(int effectDuration){
        this.effectDuration = effectDuration;
    }
    public void setEffectAmplifier(int effectAmplifier){
        this.effectAmplifier = effectAmplifier;
    }

    @Mod.EventBusSubscriber(modid = Tutorialmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class Events {

        @SubscribeEvent
        public static void livingTick(ProjectileImpactEvent event) {


           /* if ( event.getRayTraceResult() instanceof EntityHitResult){
                EntityHitResult entityHitResult = (EntityHitResult) event.getRayTraceResult();

                Entity entity = entityHitResult.getEntity();
            }*/


            if (event.getEntity() instanceof ArrowPoisonArea){

                ArrowPoisonArea arrowPoisonArea = (ArrowPoisonArea) event.getEntity();
                arrowPoisonArea.createArea();
                arrowPoisonArea.discard();

            }

        }

    }

    protected void createArea(){

        AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level, this.getX(), this.getY(), this.getZ());
        areaeffectcloud.setParticle(ParticleTypes.WITCH);
        areaeffectcloud.setRadius(this.areaRadius);
        areaeffectcloud.setDuration(this.areaDuration);
        areaeffectcloud.setRadiusPerTick((7.0F - areaeffectcloud.getRadius()) / (float)areaeffectcloud.getDuration());


        MobEffectInstance mobEffect = new MobEffectInstance(MobEffects.POISON, this.effectDuration, this.effectAmplifier);
        areaeffectcloud.addEffect(mobEffect);
        this.level.addFreshEntity(areaeffectcloud);

    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {

        Entity entity = entityHitResult.getEntity();
        if (entity instanceof LivingEntity){
            LivingEntity livingEntity = (LivingEntity) entity;
            livingEntity.addEffect( new MobEffectInstance(MobEffects.POISON, this.effectDuration/3,this.effectAmplifier+1));
        }

        super.onHitEntity(entityHitResult);
    }

    @Override
    public void tick() {


        super.tick();


    }


    @Override
    public ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }


   /* public void createAreaBlocks(@Nullable Entity entity){

        List<BlockPos> blockPoss;
        if (entity != null){
            blockPoss = SphereCalculator.SphereOutlinePositions(new BlockPos(entity.getBlockX(),entity.getBlockY(),entity.getBlockZ()),4,4,4,4,4,2);
        }else {
            blockPoss = SphereCalculator.SphereOutlinePositions(new BlockPos(this.getBlockX(),this.getBlockY(),this.getBlockZ()),4,4,4,4,4,2);
        }


        blockPoss.forEach(pos->{
            level.setBlock(pos, Blocks.BEDROCK.defaultBlockState(),3);
        });
    }
*/

}
