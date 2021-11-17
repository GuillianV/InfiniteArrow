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
    public final int areaRadius;
    public final int areaDuration;
    public final int effectDuration;
    public final int effectAmplifier;




    public ArrowItemPoisonArea(Properties properties, float damage,int areaDuration, int areaRadius, int effectAmplifier, int effectDuration) {

        super(properties);
        this.damage = damage;
        this.areaDuration = areaDuration;
        this.areaRadius = areaRadius;
        this.effectAmplifier = effectAmplifier;
        this.effectDuration = effectDuration;

    }

    @Override
    public Component getName(ItemStack itemStack) {
        return new TranslatableComponent(this.getDescriptionId(itemStack));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {


        if(Screen.hasShiftDown()){
            components.add(new TextComponent("Damages : " + this.damage).setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_RED)));
            components.add(new TextComponent("Area Duration : " +(float) this.areaDuration/20f+"s").setStyle(Style.EMPTY.withColor(ChatFormatting.RED)));
            components.add(new TextComponent("Area Radius : " + this.areaRadius).setStyle(Style.EMPTY.withColor(ChatFormatting.LIGHT_PURPLE)));
            components.add(new TextComponent("Effect Duration : " +(float) this.effectDuration/20f +"s").setStyle(Style.EMPTY.withColor(ChatFormatting.BLUE)));
            components.add(new TextComponent("Poison Level on Area : " + (this.effectAmplifier+1)).setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN)));
            components.add(new TextComponent("Poison Level on Hit : " + (this.effectAmplifier+3)).setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GREEN)));
            components.add(new TextComponent("Create a poisoneous area when hit , applay poison apgraded when hit entity").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)) );
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
        arrow.setAreaRadius(this.areaRadius);
        arrow.setAreaDuration(this.areaDuration);
        arrow.setEffectAmplifier(this.effectAmplifier);
        arrow.setEffectDuration(this.effectDuration);
        return arrow;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.player.Player player) {
        int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == ArrowItemPoisonArea.class;
    }





}
