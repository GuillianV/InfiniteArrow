package net.guillian.tutorialmod.common.items;

import net.guillian.tutorialmod.common.entity.ArrowDigger;
import net.guillian.tutorialmod.core.init.ItemInit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ArrowItemDigger extends ArrowItem {

    public final float damage;
    public final int radius;
    public final int rows;


    public ArrowItemDigger(Properties properties) {

        super(properties);
        this.damage = 1.5f;
        this.rows=1;
        this.radius=2;
    }

    public ArrowItemDigger(Properties properties,float damage,int radius, int rows) {

        super(properties);
        this.damage = damage;
        this.rows=rows;
        this.radius=radius;
    }




    @Override
    public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity shooter) {
        ArrowDigger arrow = new ArrowDigger(shooter,level, ItemInit.ARROW_DIGGER_SIMPLE.get(),this.rows,this.radius);
        arrow.setBaseDamage(this.damage);
        return arrow;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.player.Player player) {
        int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == ArrowItemDigger.class;
    }





}