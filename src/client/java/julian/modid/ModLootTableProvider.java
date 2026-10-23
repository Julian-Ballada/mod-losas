package julian.modid;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        for (DyeColor color : DyeColor.values()) {
            // Hormigón
            add(ModBlocks.CONCRETE_SLABS.get(color), createSlabItemTable(ModBlocks.CONCRETE_SLABS.get(color)));
            dropSelf(ModBlocks.CONCRETE_STAIRS.get(color));

            // Terracota
            add(ModBlocks.TERRACOTTA_SLABS.get(color), createSlabItemTable(ModBlocks.TERRACOTTA_SLABS.get(color)));
            dropSelf(ModBlocks.TERRACOTTA_STAIRS.get(color));

            // Terracota vidriada
            add(ModBlocks.GLAZED_SLABS.get(color), createSlabItemTable(ModBlocks.GLAZED_SLABS.get(color)));
            dropSelf(ModBlocks.GLAZED_STAIRS.get(color));
        }
    }
}