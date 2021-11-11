package net.guillian.tutorialmod.common.entity;

import net.guillian.tutorialmod.Tutorialmod;
import net.guillian.tutorialmod.core.init.EntityInit;
import net.guillian.tutorialmod.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Tutorialmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ArrowDigger extends AbstractArrow {

    private final Item referenceItem;
    private int rows;
    private int radius;
    private boolean isLooting = false;

    public ArrowDigger(EntityType<? extends ArrowDigger> entityType, Level level) {
        super(entityType, level);
        this.referenceItem = ItemInit.ARROW_DIGGER_SIMPLE.get();

    }


    public ArrowDigger(LivingEntity shooter, Level level, Item referenceItem) {
        super(EntityInit.ARROW_DIGGER.get(),shooter, level);
        this.referenceItem = referenceItem;


    }

    @Override
    public void setDeltaMovement(Vec3 deltaMovement) {
        super.setDeltaMovement(deltaMovement);

    }

    public void setRows(int rows){
        this.rows = rows;
    }

    public void setRadius(int radius){
        this.radius = radius;
    }

    public void setLooting(boolean isLooting){
        this.isLooting = isLooting;
    }




/*
    @Override
    public void move(MoverType moverType, Vec3 vec3) {
        super.move(moverType, vec3);
        if (moverType != MoverType.SELF && this.shouldFall()) {
            this.startFalling();
        }

    }

    private boolean shouldFall() {
        return this.inGround && this.level.noCollision((new AABB(this.position(), this.position())).inflate(0.06D));
    }

    private void startFalling() {
        this.inGround = false;
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.multiply((double)(this.random.nextFloat() * 0.2F), (double)(this.random.nextFloat() * 0.2F), (double)(this.random.nextFloat() * 0.2F)));
    }
*/

    public boolean DestroyBlock(BlockPos pos){

        if (this.isLooting){
            return level.destroyBlock(pos, true,this);
        }else {
            return level.removeBlock(pos, false);
        }


    }




    @Override
    public void tick() {


        super.tick();

        boolean tickDestroyedBlock = false;


        Vec3 deltaMovement = this.getDeltaMovement();
        Vec3 position = this.position();
        Vec3 newPosition = position.add(deltaMovement);

        BlockHitResult hitresult = this.level.clip(new ClipContext(position, newPosition, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        if (hitresult.getType() != HitResult.Type.MISS ) {
            newPosition = hitresult.getLocation();
            if (rows > 0) {


                //  this.getOwner().sendMessage(new TextComponent("My arrow Hitted"),this.getUUID());

                for (int x = -this.radius ; x <= this.radius ; x++) {
                    for (int y = -this.radius ; y <= this.radius ; y++) {
                        for (int z = -this.radius ; z <= this.radius ; z++) {

                            BlockPos pos = new BlockPos(hitresult.getLocation().x + x, hitresult.getLocation().y+ y, hitresult.getLocation().z+ z);

                            boolean destroyed = false;
                            if ( level.getBlockState(pos).getBlock() == Blocks.BEDROCK){
                                rows = 0;
                            }else {
                                destroyed =  DestroyBlock(pos);
                            }


                            if (destroyed)
                                tickDestroyedBlock= true;

                        }
                    }
                }
                if (tickDestroyedBlock){
                    rows--;
                }


            }else {
                this.remove(RemovalReason.KILLED);
            }





        }

    }









    @Override
    public ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }


}
