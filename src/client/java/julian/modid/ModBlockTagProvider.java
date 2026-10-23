package julian.modid;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DyeColor;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider{

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        var pickaxe = valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE);

        for (DyeColor color : DyeColor.values()) {
            pickaxe.add(ModBlocks.CONCRETE_SLABS.get(color));
            pickaxe.add(ModBlocks.CONCRETE_STAIRS.get(color));
            pickaxe.add(ModBlocks.TERRACOTTA_SLABS.get(color));
            pickaxe.add(ModBlocks.TERRACOTTA_STAIRS.get(color));
            pickaxe.add(ModBlocks.GLAZED_SLABS.get(color));
            pickaxe.add(ModBlocks.GLAZED_STAIRS.get(color));
        }
    }
}
