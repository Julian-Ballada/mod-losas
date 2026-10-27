package julian.modid;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeColor;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ModLanguageProvider extends FabricLanguageProvider {

    private final boolean spanish;

    public ModLanguageProvider(FabricDataOutput output,
                               CompletableFuture<HolderLookup.Provider> registriesFuture,
                               String languageCode) {
        super(output, languageCode, registriesFuture);
        this.spanish = languageCode.startsWith("es");
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder builder) {
        for (DyeColor color : DyeColor.values()) {
            if (spanish) {
                String masc = colorEs(color, false);
                String fem = colorEs(color, true);

                builder.add(ModBlocks.CONCRETE_SLABS.get(color), "Losa de hormigón " + masc);
                builder.add(ModBlocks.CONCRETE_STAIRS.get(color), "Escalera de hormigón " + masc);
                builder.add(ModBlocks.TERRACOTTA_SLABS.get(color), "Losa de terracota " + fem);
                builder.add(ModBlocks.TERRACOTTA_STAIRS.get(color), "Escalera de terracota " + fem);
                builder.add(ModBlocks.GLAZED_SLABS.get(color), "Losa de terracota esmaltada " + fem);
                builder.add(ModBlocks.GLAZED_STAIRS.get(color), "Escalera de terracota esmaltada " + fem);
            } else {
                String name = colorEn(color);

                builder.add(ModBlocks.CONCRETE_SLABS.get(color), name + " Concrete Slab");
                builder.add(ModBlocks.CONCRETE_STAIRS.get(color), name + " Concrete Stairs");
                builder.add(ModBlocks.TERRACOTTA_SLABS.get(color), name + " Terracotta Slab");
                builder.add(ModBlocks.TERRACOTTA_STAIRS.get(color), name + " Terracotta Stairs");
                builder.add(ModBlocks.GLAZED_SLABS.get(color), name + " Glazed Terracotta Slab");
                builder.add(ModBlocks.GLAZED_STAIRS.get(color), name + " Glazed Terracotta Stairs");
            }
        }
    }

    private static String colorEn(DyeColor color) {
        return Arrays.stream(color.getName().split("_"))
                .map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1))
                .collect(Collectors.joining(" "));
    }

    private static String colorEs(DyeColor color, boolean fem) {
        return switch (color) {
            case WHITE -> fem ? "blanca" : "blanco";
            case ORANGE -> "naranja";
            case MAGENTA -> "magenta";
            case LIGHT_BLUE -> "azul claro";
            case YELLOW -> fem ? "amarilla" : "amarillo";
            case LIME -> "lima";
            case PINK -> "rosa";
            case GRAY -> "gris";
            case LIGHT_GRAY -> "gris claro";
            case CYAN -> "cian";
            case PURPLE -> fem ? "morada" : "morado";
            case BLUE -> "azul";
            case BROWN -> "marrón";
            case GREEN -> "verde";
            case RED -> fem ? "roja" : "rojo";
            case BLACK -> fem ? "negra" : "negro";
        };
    }
}
