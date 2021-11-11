package net.guillian.tutorialmod.common.entity;

import net.guillian.tutorialmod.Tutorialmod;
import net.guillian.tutorialmod.core.init.EntityInit;
import net.guillian.tutorialmod.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ArrowTnt  extends AbstractArrow {

    private final Item referenceItem;
    private Explosion.BlockInteraction blockInteraction;
    private int explosionRadius;
    private int maxTick;
    private boolean hitEntity;
    public int tickBeforeExplosion;

    public ArrowTnt(EntityType<? extends ArrowTnt> entityType, Level level) {
        super(entityType, level);
        this.referenceItem = ItemInit.ARROW_TNT_SIMPLE.get();
        this.hitEntity = false;

    }


    public ArrowTnt(LivingEntity shooter, Level level, Item referenceItem) {
        super(EntityInit.ARROW_TNT.get(),shooter, level);
        this.referenceItem = referenceItem;
        this.hitEntity = false;


    }


    public void setMaxTick(int maxTick){
        this.maxTick = maxTick;
    }

    public int getMaxTick(){
        return this.maxTick;
    }

    public void setExplosionRadius(int explosionRadius){
        this.explosionRadius = explosionRadius;
    }

    public int getExplosionRadius(){
        return this.explosionRadius;
    }

    public void setBlockInteraction(Explosion.BlockInteraction blockInteraction){
        this.blockInteraction = blockInteraction;
    }

    public Explosion.BlockInteraction getBlockInteraction(){
        return this.blockInteraction;
    }






    @Mod.EventBusSubscriber(modid = Tutorialmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class Events {
        @SubscribeEvent
        public static void livingTick(ProjectileImpactEvent event) {

            if (event.getEntity() instanceof ArrowTnt){



               // ArrowTnt arrowTnt= (ArrowTnt) event.getEntity();
               // Level level = arrowTnt.level;





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
            if (!this.level.isClientSide() && getMaxTick() < tickBeforeExplosion){
                this.level.explode(this,this.getX(),this.getY(),this.getZ(),this.getExplosionRadius(),this.getBlockInteraction());
                this.remove(RemovalReason.KILLED);

            }else {
                tickBeforeExplosion++;
            }
        }



    }


    @Override
    public ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }


}
