package julian.modid;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeColor;

import java.util.concurrent.CompletableFuture;

public class ModLanguageProvider extends FabricLanguageProvider {
    // Si quieres español, cambia "en_us" por "es_es" o "es_mx"
    public ModLanguageProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, "en_us", registriesFuture);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder builder) {
        // Simplemente le dices el bloque y el nombre que quieres que tenga en el juego
        for (DyeColor color : DyeColor.values()) {
            String nombre = nombreColor(color);

            builder.add(ModBlocks.CONCRETE_SLABS.get(color), "Losa de Hormigón " + nombre);
            builder.add(ModBlocks.CONCRETE_STAIRS.get(color), "Escalera de Hormigón " + nombre);
            builder.add(ModBlocks.TERRACOTTA_SLABS.get(color), "Losa de Terracotta " + nombre);
            builder.add(ModBlocks.TERRACOTTA_STAIRS.get(color), "Escalera de Terracotta " + nombre);
            builder.add(ModBlocks.GLAZED_SLABS.get(color), "Losa de AZULEJO " + nombre);
            builder.add(ModBlocks.GLAZED_STAIRS.get(color), "Escalera de AZULEJO " + nombre);
        }
    }

    private static String nombreColor(DyeColor color) {
        return switch (color) {
            case WHITE -> "Blanco";
            case ORANGE -> "Naranja";
            case MAGENTA -> "Magenta";
            case LIGHT_BLUE -> "Azul Claro";
            case YELLOW -> "Amarillo";
            case LIME -> "Lima";
            case PINK -> "Rosa";
            case GRAY -> "Gris";
            case LIGHT_GRAY -> "Gris Claro";
            case CYAN -> "Cian";
            case PURPLE -> "Morado";
            case BLUE -> "Azul";
            case BROWN -> "Marrón";
            case GREEN -> "Verde";
            case RED -> "Rojo";
            case BLACK -> "Negro";
        };
    }
    // builder.add(ModBlocks.RED_TERRACOTTA_SLAB, "Losa de Terracota Roja");
}
