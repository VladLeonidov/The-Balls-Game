package com.leus.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Properties;

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

    public static String getPropertyValue(String propertyKey) {
        return settings.getProperty(propertyKey);
    }
}
