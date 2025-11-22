package com.mobius.im.loader;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mobius.im.IM_Reference;
import com.mobius.im.entity.Form;
import com.mobius.im.entity.Material;

import net.neoforged.fml.loading.FMLPaths;

public class ConfigHandler {

    public static final Path CONFIG_DIR = FMLPaths.CONFIGDIR.get().resolve(IM_Reference.MODID);
    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static Map<String, Form> getForms() {
        var formPath = CONFIG_DIR.resolve("forms");
        return loadConfigs(formPath, Form.class);
    }

    public static Map<String, Material> getMaterials() {
        var materialPath = CONFIG_DIR.resolve("materials");
        return loadConfigs(materialPath, Material.class);
    }

    private static <T> Map<String, T> loadConfigs(Path path, Class<T> clazz) {

        Type type = TypeToken.getParameterized(Map.class, String.class, clazz).getType();
        try {
            return Files.walk(path).filter(Files::isRegularFile).map(t -> {
                try {
                    IM_Reference.LOGGER.info("Loading config file: " + t);
                    return Files.readString(t);
                } catch (IOException e) {
                    IM_Reference.LOGGER.error("Failed to read config file: " + t, e);
                    return null;
                }
            }).filter(Objects::nonNull).map(str -> {
                return GSON.<Map<String, T>>fromJson(str, type);
            }).flatMap(map -> map.entrySet().stream())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        } catch (IOException e) {
            IM_Reference.LOGGER.error("Failed to read config dir: ");
            return Map.of();
        }
    }
}
