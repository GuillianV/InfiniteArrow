package net.guillian.tutorialmod.core.init;

import net.guillian.tutorialmod.Tutorialmod;
import net.guillian.tutorialmod.common.items.ArrowItemDigger;
import net.guillian.tutorialmod.common.items.ArrowItemTnt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ItemInit {

    private  ItemInit(){}

     public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Tutorialmod.MOD_ID);

    //Items basique
    public static  final RegistryObject<Item> MYITEM = ITEMS.register("myitem",() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static  final RegistryObject<Item> MYITEM2 = ITEMS.register("myitem2",() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).fireResistant().stacksTo(4)));


    //Block Items (Block crée , Item )
    public static  final RegistryObject<BlockItem> MYITEMBLOCK = ITEMS.register("myitemblock",() -> new BlockItem(BlockInit.MYBLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC).fireResistant().stacksTo(4)));

    //ArrowItems
    public static  final RegistryObject<Item> ARROW_DIGGER_SIMPLE = ITEMS.register("arrow_digger_simple",() -> new ArrowItemDigger(new Item.Properties().tab(CreativeModeTab.TAB_MISC),1.5f,1,1,false));
    public static  final RegistryObject<Item> ARROW_DIGGER_MEDIUM = ITEMS.register("arrow_digger_medium",() -> new ArrowItemDigger(new Item.Properties().tab(CreativeModeTab.TAB_MISC),2.5f,2,1,false));
    public static  final RegistryObject<Item> ARROW_DIGGER_ADVANCED = ITEMS.register("arrow_digger_advanced",() -> new ArrowItemDigger(new Item.Properties().tab(CreativeModeTab.TAB_MISC),3.5f,3,2,false));

    public static  final RegistryObject<Item> ARROW_TNT_SIMPLE = ITEMS.register("arrow_tnt_simple",() -> new ArrowItemTnt(new Item.Properties().tab(CreativeModeTab.TAB_MISC),0.5f,1, Explosion.BlockInteraction.DESTROY,120));
    public static  final RegistryObject<Item> ARROW_TNT_MEDIUM = ITEMS.register("arrow_tnt_medium",() -> new ArrowItemTnt(new Item.Properties().tab(CreativeModeTab.TAB_MISC),1.5f,2, Explosion.BlockInteraction.DESTROY,100));
    public static  final RegistryObject<Item> ARROW_TNT_ADVANCED = ITEMS.register("arrow_tnt_advanced",() -> new ArrowItemTnt(new Item.Properties().tab(CreativeModeTab.TAB_MISC),2.5f,3, Explosion.BlockInteraction.DESTROY,80));

}
