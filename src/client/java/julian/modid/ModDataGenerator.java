package julian.modid;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        // Aquí encendemos los motores que hemos creado
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModLootTableProvider::new);
        pack.addProvider(ModBlockTagProvider::new);

        pack.addProvider((output, registries) -> new ModLanguageProvider(output, registries, "en_us"));
        pack.addProvider((output, registries) -> new ModLanguageProvider(output, registries, "es_es"));
        pack.addProvider((output, registries) -> new ModLanguageProvider(output, registries, "es_mx"));
        pack.addProvider((output, registries) -> new ModLanguageProvider(output, registries, "es_ar"));
        // Solo si creaste el paso de los idiomas
    }
}