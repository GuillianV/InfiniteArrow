package net.guillian.tutorialmod.common.items;

import net.guillian.tutorialmod.common.entity.ArrowDigger;
import net.guillian.tutorialmod.core.init.ItemInit;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class ArrowItemDigger extends ArrowItem {

    public final float damage;
    public final int radius;
    public final int rows;
    public final boolean isLooting;




    public ArrowItemDigger(Properties properties,float damage,int radius, int rows,boolean isLooting) {

        super(properties);
        this.damage = damage;
        this.rows=rows;
        this.radius=radius;
        this.isLooting = isLooting;

    }

    @Override
    public Component getName(ItemStack itemStack) {
        return new TranslatableComponent(this.getDescriptionId(itemStack));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {


        if(Screen.hasShiftDown()){
            components.add(new TextComponent("Damages : " + this.damage).setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_RED)));
            components.add(new TextComponent("Radius : " + this.radius).setStyle(Style.EMPTY.withColor(ChatFormatting.RED)) );
            components.add(new TextComponent("Rows : " + this.rows).setStyle(Style.EMPTY.withColor(ChatFormatting.LIGHT_PURPLE)) );
            components.add(new TextComponent("Dig blocks when arrow hit").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)) );
        }
        else {
            components.add(new TextComponent(""));
            TextComponent parent = new TextComponent("");
            parent.append(new TextComponent("press "));
            parent.append(new TextComponent("Shift").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GREEN)));
            parent.append(new TextComponent(" for more info"));

            components.add(parent);
        }

        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity shooter) {
        ArrowDigger arrow = new ArrowDigger(shooter,level, ItemInit.ARROW_DIGGER_SIMPLE.get());
        arrow.setBaseDamage(this.damage);
        arrow.setRadius(this.radius);
        arrow.setRows(this.rows);
        arrow.setLooting(this.isLooting);
        return arrow;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.player.Player player) {
        int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == ArrowItemDigger.class;
    }





}
