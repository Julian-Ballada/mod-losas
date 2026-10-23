package julian.modid;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    // --- AQUÍ ESTÁ LA SOLUCIÓN AL ERROR ---
    @Override
    public String getName() {
        return "Crafteos de ModLosas";
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput exporter) {

        return new RecipeProvider(registries, exporter) {
            @Override
            public void buildRecipes() {
                HolderGetter<Item> items = registries.lookupOrThrow(Registries.ITEM);

                for (DyeColor color : DyeColor.values()) {
                    crearCrafteos(exporter, items,
                            ModBlocks.getConcrete(color),
                            ModBlocks.CONCRETE_SLABS.get(color),
                            ModBlocks.CONCRETE_STAIRS.get(color));
                    crearCrafteos(exporter, items,
                            ModBlocks.getTerracotta(color),
                            ModBlocks.TERRACOTTA_SLABS.get(color),
                            ModBlocks.TERRACOTTA_STAIRS.get(color));
                    crearCrafteos(exporter, items,
                            ModBlocks.getGlazed(color),
                            ModBlocks.GLAZED_SLABS.get(color),
                            ModBlocks.GLAZED_STAIRS.get(color));
                }
            }
            // --- LA FÓRMULA AUTOMÁTICA PARA CRAFTEOS ---
            private void crearCrafteos(RecipeOutput exporter, HolderGetter<Item> items, Block base, Block slab, Block stairs) {
                ShapedRecipeBuilder.shaped(items, RecipeCategory.BUILDING_BLOCKS, slab, 6)
                        .pattern("###")
                        .define('#', base)
                        .unlockedBy("tiene_bloque", has(base))
                        .save(exporter);

                ShapedRecipeBuilder.shaped(items, RecipeCategory.BUILDING_BLOCKS, stairs, 4)
                        .pattern("#  ")
                        .pattern("## ")
                        .pattern("###")
                        .define('#', base)
                        .unlockedBy("tiene_bloque", has(base))
                        .save(exporter);
            }
        };
    }
}