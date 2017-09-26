package com.leus.utils;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public final class ResourceLoader {

    private ResourceLoader() {
    }

    public static ImageIcon loadImageIcon(String path) {
        if (path == null) {
            throw new NullPointerException("Path can't be null " + path);
        }

        if (path.equals("")) {
            throw new IllegalArgumentException("Path can't be is empty " + path);
        }

        return new ImageIcon(ResourceLoader.class.getClassLoader().getResource(path));
    }

    public static Image loadImage(String path) {
        if (path == null) {
            throw new NullPointerException("Path can't be null " + path);
        }

        if (path.equals("")) {
            throw new IllegalArgumentException("Path can't be is empty " + path);
        }

        return loadImageIcon(path).getImage();
    }

    public static InputStream loadFile(String path) {
        if (path == null) {
            throw new NullPointerException("Path can't be null " + path);
        }

        if (path.equals("")) {
            throw new IllegalArgumentException("Path can't be is empty " + path);
        }

        return ResourceLoader.class.getClassLoader().getResourceAsStream(path);
    }
}
