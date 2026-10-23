package julian.modid;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;


import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class ModBlocks {

    public static final Map<DyeColor, Block> CONCRETE_SLABS = new EnumMap<>(DyeColor.class);
    public static final Map<DyeColor, Block> CONCRETE_STAIRS = new EnumMap<>(DyeColor.class);

    public static final Map<DyeColor, Block> TERRACOTTA_SLABS = new EnumMap<>(DyeColor.class);
    public static final Map<DyeColor, Block> TERRACOTTA_STAIRS = new EnumMap<>(DyeColor.class);

    public static final Map<DyeColor, Block> GLAZED_SLABS = new EnumMap<>(DyeColor.class);
    public static final Map<DyeColor, Block> GLAZED_STAIRS = new EnumMap<>(DyeColor.class);

    static {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getName();
            Block concrete = getConcrete(color);
            Block terracotta = getTerracotta(color);
            Block glazed = getGlazed(color);

            CONCRETE_SLABS.put(color, registerBlock(name + "_concrete_slab",
                    SlabBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(concrete)));

            CONCRETE_STAIRS.put(color, registerBlock(name + "_concrete_stairs",
                    props -> new StairBlock(concrete.defaultBlockState(), props),
                    BlockBehaviour.Properties.ofFullCopy(concrete)));


            TERRACOTTA_SLABS.put(color, registerBlock(name + "_terracotta_slab",
                    SlabBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(terracotta)));

            TERRACOTTA_STAIRS.put(color, registerBlock(name + "_terracotta_stairs",
                    props -> new StairBlock(terracotta.defaultBlockState(), props),
                    BlockBehaviour.Properties.ofFullCopy(terracotta)));

            GLAZED_SLABS.put(color, registerBlock(name + "_glazed_terracotta_slab",
                    SlabBlock::new,
                    BlockBehaviour.Properties.ofFullCopy(glazed)));

            GLAZED_STAIRS.put(color, registerBlock(name + "_glazed_terracotta_stairs",
                    props -> new StairBlock(terracotta.defaultBlockState(), props),
                    BlockBehaviour.Properties.ofFullCopy(glazed)));
        }
    }

    // Devuelve el bloque de hormigón vanilla de ese color
    public static Block getConcrete(DyeColor color) {
        return BuiltInRegistries.BLOCK.getValue(
                Identifier.withDefaultNamespace(color.getName() + "_concrete"));
    }

    public static Block getTerracotta(DyeColor color) {
        return BuiltInRegistries.BLOCK.getValue(
                Identifier.withDefaultNamespace(color.getName() + "_terracotta"));
    }

    public static Block getGlazed(DyeColor color) {
        return BuiltInRegistries.BLOCK.getValue(
                Identifier.withDefaultNamespace(color.getName() + "_glazed_terracotta"));
    }



    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, ModLosas.id(name));
        Block block = blockFactory.apply(properties.setId(blockKey));
        Block bloqueRegistrado = Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
        registerBlockItem(name, bloqueRegistrado);
        return bloqueRegistrado;
    }

    private static Item registerBlockItem(String name, Block block) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, ModLosas.id(name));
        Item.Properties itemProperties = new Item.Properties().setId(itemKey);
        return Registry.register(BuiltInRegistries.ITEM, itemKey, new BlockItem(block, itemProperties));
    }

    public static void registerModBlocks() {
        ModLosas.LOGGER.info("Registrando bloques para " + ModLosas.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
            for (DyeColor color : DyeColor.values()) {
                entries.accept(CONCRETE_SLABS.get(color));
                entries.accept(CONCRETE_STAIRS.get(color));
                entries.accept(TERRACOTTA_SLABS.get(color));
                entries.accept(TERRACOTTA_STAIRS.get(color));
                entries.accept(GLAZED_SLABS.get(color));
                entries.accept(GLAZED_STAIRS.get(color));
            }
        });
    }
}
