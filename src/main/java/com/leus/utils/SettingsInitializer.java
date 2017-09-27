package com.leus.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class loads the Settings.properties and provides access to the property value of the property key.
 * @see #getPropertyValue(String)
 */
public class SettingsInitializer {
    private static Properties settings = new Properties();

    static {
        try(BufferedInputStream in = new BufferedInputStream(ResourceLoader.loadFile("Settings.properties"))) {
            settings.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SettingsInitializer() {
    }

    /**
     * This method returns property value on the specified key.
     * @param propertyKey property key in file.
     * @return property value from file.
     */
    public static String getPropertyValue(String propertyKey) {
        return settings.getProperty(propertyKey);
    }
}
