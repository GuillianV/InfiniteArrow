package net.guillian.tutorialmod.common.items;

import net.guillian.tutorialmod.common.entity.ArrowDigger;
import net.guillian.tutorialmod.common.entity.ArrowPoisonArea;
import net.guillian.tutorialmod.core.init.ItemInit;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class ArrowItemPoisonArea extends ArrowItem {

    public final float damage;





    public ArrowItemPoisonArea(Properties properties, float damage) {

        super(properties);
        this.damage = damage;


    }

    @Override
    public Component getName(ItemStack itemStack) {
        return new TranslatableComponent(this.getDescriptionId(itemStack));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {


        if(Screen.hasShiftDown()){
            components.add(new TextComponent("Damages : " + this.damage).setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_RED)));
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
        ArrowPoisonArea arrow = new ArrowPoisonArea(shooter,level, ItemInit.ARROW_POISON_AREA_SIMPLE.get());
        arrow.setBaseDamage(this.damage);
        return arrow;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.player.Player player) {
        int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == ArrowItemPoisonArea.class;
    }





}
