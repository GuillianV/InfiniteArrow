package net.guillian.tutorialmod.common.entity;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.guillian.tutorialmod.Tutorialmod;
import net.guillian.tutorialmod.core.init.EntityInit;
import net.guillian.tutorialmod.core.init.ItemInit;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class ArrowTnt  extends AbstractArrow {

    private final Item referenceItem;
    private Explosion.BlockInteraction blockInteraction;
    private int explosionRadius;
    private int maxTick;
    private boolean hitEntity;
    public int tickBeforeExplosion;
    @Nullable
    private IntOpenHashSet piercingIgnoreEntityIds;
    @Nullable
    private List<Entity> piercedAndKilledEntities;

    public ArrowTnt(EntityType<? extends ArrowTnt> entityType, Level level) {
        super(entityType, level);
        this.referenceItem = ItemInit.ARROW_TNT_SIMPLE.get();
        this.hitEntity = false;
        this.pickup = Pickup.DISALLOWED;

    }


    public ArrowTnt(LivingEntity shooter, Level level, Item referenceItem) {
        super(EntityInit.ARROW_TNT.get(),shooter, level);
        this.referenceItem = referenceItem;
        this.hitEntity = false;
        this.pickup = Pickup.DISALLOWED;


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

        EntityHitResult entityhitresult = this.findHitEntity(position, newPosition);
        if (entityhitresult != null) {
           Entity entity = entityhitresult.getEntity();
            this.level.explode(this,entity.getX(),entity.getY(),entity.getZ(),this.getExplosionRadius(),this.getBlockInteraction());
            this.remove(RemovalReason.DISCARDED);
          /*  if (!this.level.isClientSide() && getMaxTick() < tickBeforeExplosion){
                this.level.explode(this,entity.getX(),entity.getY(),entity.getZ(),this.getExplosionRadius(),this.getBlockInteraction());
                this.remove(RemovalReason.KILLED);

            }else {
                if((tickBeforeExplosion % 20) == 0) {
                    this.level.playLocalSound(entity.getX(),entity.getY(),entity.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 4.0F, (1.0F + (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2F) * 0.7F, false);
                }
                tickBeforeExplosion++;
            }*/

        }





        if (this.inGround){
            if (!this.level.isClientSide() && getMaxTick() < tickBeforeExplosion){
                this.level.explode(this,this.getX(),this.getY(),this.getZ(),this.getExplosionRadius(),this.getBlockInteraction());
                this.remove(RemovalReason.DISCARDED);

            }else {
                if((tickBeforeExplosion % 20) == 0) {
                    this.level.playLocalSound(this.getX(),this.getY(),this.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 4.0F, (1.0F + (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2F) * 0.7F, false);
                }
                tickBeforeExplosion++;
            }
        }



    }


    @Override
    public ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }


}
