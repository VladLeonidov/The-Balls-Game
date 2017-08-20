package com.leus.utils;

import javax.swing.*;
import java.awt.*;

public final class ResourceLoader {

    private ResourceLoader() {
    }

    public static ImageIcon loadImageIcon(String path) {
        if (path == null || path.length() == 0) {
            throw new IllegalArgumentException("Not correct path: " + path);
        }

        return new ImageIcon(ResourceLoader.class.getClassLoader().getResource(path));
    }

    public static Image loadImage(String path) {
        if (path == null || path.length() == 0) {
            throw new IllegalArgumentException("Not correct path: " + path);
        }

        return loadImageIcon(path).getImage();
    }
}
