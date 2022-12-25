package org.anm.phone.config;

import net.ventures.servermanagement.log.LogLevel;
import net.ventures.servermanagement.log.Logger;
import net.ventures.servermanagement.utils.Util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class Config<T> {
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
            config = new Config<>(Util.MAPPER.readValue(file, clazz));
        } catch (IOException e) {
            Logger.log(LogLevel.ERROR, e, "Parsing error", "File: " + fileName);
            e.printStackTrace();
        }
        return config;
    }
}
