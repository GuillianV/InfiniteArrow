package net.guillian.tutorialmod.core.init;

import net.guillian.tutorialmod.Tutorialmod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class BlockInit {

    private  BlockInit(){}

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Tutorialmod.MOD_ID);

    public static  final RegistryObject<Block> MYBLOCK = BLOCKS.register("myblock",() -> new Block(BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_CYAN).requiresCorrectToolForDrops().noCollission().strength(2.5f,15)));


}
