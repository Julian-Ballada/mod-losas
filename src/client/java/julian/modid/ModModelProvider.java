package julian.modid;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;


public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output){
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generators) {
        for (DyeColor color : DyeColor.values()) {
            crearTexturas(generators, ModBlocks.getConcrete(color),
                    ModBlocks.CONCRETE_SLABS.get(color),
                    ModBlocks.CONCRETE_STAIRS.get(color));
            crearTexturas(generators, ModBlocks.getTerracotta(color),
                    ModBlocks.TERRACOTTA_SLABS.get(color),
                    ModBlocks.TERRACOTTA_STAIRS.get(color));
            crearTexturas(generators,ModBlocks.getGlazed(color),
                    ModBlocks.GLAZED_SLABS.get(color),
                    ModBlocks.GLAZED_STAIRS.get(color));



        }
    }
    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) { }

    // --- EL MÉTODO AYUDANTE (La fórmula automática) ---
    private void crearTexturas(BlockModelGenerators generators, Block base, Block slab, Block stairs) {
        var pool = generators.family(base);
        pool.slab(slab);
        pool.stairs(stairs);
    }
}
