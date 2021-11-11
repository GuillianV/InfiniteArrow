package net.guillian.tutorialmod.common.entity;

import net.guillian.tutorialmod.Tutorialmod;
import net.guillian.tutorialmod.core.init.EntityInit;
import net.guillian.tutorialmod.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
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

    public ArrowDigger(EntityType<? extends ArrowDigger> entityType, Level level) {
        super(entityType, level);
        this.referenceItem = ItemInit.ARROW_DIGGER_SIMPLE.get();
        this.rows = 3;
        this.radius = 4;
    }

    public ArrowDigger(LivingEntity shooter, Level level, Item referenceItem, int rows, int radius) {
        super(EntityInit.ARROW_DIGGER.get(),shooter, level);
        this.referenceItem = referenceItem;
        this.rows = rows;
        this.radius = radius;
    }

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



    @SubscribeEvent
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
            if (this.level.getBlockState( hitresult.getBlockPos()).getBlock() != Blocks.AIR && rows > 0)
            {
                //  this.getOwner().sendMessage(new TextComponent("My arrow Hitted"),this.getUUID());

                for (int x = -this.radius/2 ;x < this.radius/2 ; x++){
                    for (int y = -this.radius/2;y < this.radius/2 ; y++){
                        for (int z = -this.radius/2;z < this.radius/2 ; z++){

                         /*   this.getServer().getAllLevels().forEach(level ->{
                                if (this.level == level){

                                }
                            });*/

                            level.removeBlock(new BlockPos(this.getX() + x, this.getY()+y,this.getZ()+z),true);

                          /*  boolean destroyed = this.level.destroyBlock(new BlockPos(this.getX() + x, this.getY()+y,this.getZ()+z),true,this,512);
                            if (destroyed){
                                tickDestroyedBlock = true;
                            }*/

                        }
                    }
                }
                if (tickDestroyedBlock){
                    this.rows--;
                }

            }


        }






     /*   HitResult hitresult = this.level.clip(new ClipContext( new Vec3(this.getX() , this.getY() ,this.getZ()) , new Vec3(this.getX() + this.getLookAngle().x  , this.getY()+ this.getLookAngle().y,this.getZ() +this.getLookAngle().z ), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        if (hitresult.getType() != HitResult.Type.BLOCK && rows > 0) {

            BlockPos blockPos = new BlockPos(hitresult.getLocation());
            if (level.getBlockState(  blockPos).getBlock() != Blocks.AIR && rows > 0)
            {

                int radius = 4;
                for (int x = -radius/2 ;x < radius/2 ; x++){
                    for (int y = -radius/2;y < radius/2 ; y++){
                        for (int z = -radius/2;z < radius/2 ; z++){
                            this.level.removeBlock(new BlockPos(this.getX() + x, this.getY()+y,this.getZ()+z),true);
                            //rows--;
                        }
                    }
                }


            }
        }
*/




        if (this.inGround){
            //float f = 100.0F;

            //this.level.explode(this, this.getX(), this.getY(), this.getZ(), 3 * f, Explosion.BlockInteraction.DESTROY );
             this.remove(RemovalReason.KILLED);
        }


    }

    @Override
    public ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }


}
