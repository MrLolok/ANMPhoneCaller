package org.anm.phone.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.anm.phone.log.LogLevel;
import org.anm.phone.log.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class Config<T> {
    public final static ObjectMapper MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));

    private final T config;

    public Config(T config) {
        this.config = config;
    }

    /**
     * This method returns the config object
     * @return The config object
     */
    public T getConfig() {
        return config;
    }

    public static <T> Config<T> load(String fileName, Class<T> clazz) {
        File file = new File("configs", String.format("%s.json", fileName));
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try (InputStream in = Config.class.getClassLoader().getResourceAsStream(String.format("%s.json", fileName))) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                Logger.log(LogLevel.WARN, e, "Unable to copy the default configuration", "File: " + fileName);
            }
        }

        Config<T> config = null;
        try {
            config = new Config<>(MAPPER.readValue(file, clazz));
        } catch (IOException e) {
            Logger.log(LogLevel.ERROR, e, "Parsing error", "File: " + fileName);
            e.printStackTrace();
        }
        return config;
    }
}
