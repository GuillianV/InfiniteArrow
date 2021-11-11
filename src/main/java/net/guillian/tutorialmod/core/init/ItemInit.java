package net.guillian.tutorialmod.core.init;

import net.guillian.tutorialmod.Tutorialmod;
import net.guillian.tutorialmod.common.items.ArrowItemDigger;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
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
    public static  final RegistryObject<Item> ARROW_DIGGER_SIMPLE = ITEMS.register("arrow_digger_simple",() -> new ArrowItemDigger(new Item.Properties().tab(CreativeModeTab.TAB_MISC),1.5f,2,1,true));


}
