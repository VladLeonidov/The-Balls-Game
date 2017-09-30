package com.leus.utils;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

/**
 * This class-util is designed for load of files from resources.
 */
public final class ResourceLoader {

    private ResourceLoader() {
    }

    /**
     * This method is a loader of ImageIcons.
     * @param path The path to imageIcon from resources.
     * @return Instance of ImageIcon
     */
    public static ImageIcon loadImageIcon(String path) {
        if (path == null) {
            throw new NullPointerException("Path can't be null " + path);
        }

        if (path.equals("")) {
            throw new IllegalArgumentException("Path can't be is empty " + path);
        }

        return new ImageIcon(ResourceLoader.class.getClassLoader().getResource(path));
    }

    /**
     * This method is a loader of {@literal Image.} Method delegates part of its work to method {@link #loadImageIcon(String)}.
     * @param path The path to Image from resources
     * @return Instance of Image
     */
    public static Image loadImage(String path) {
        if (path == null) {
            throw new NullPointerException("Path can't be null " + path);
        }

        if (path.equals("")) {
            throw new IllegalArgumentException("Path can't be is empty " + path);
        }

        return loadImageIcon(path).getImage();
    }

    /**
     * This method provides files from resources as InputStream.
     * @param path The path to file from resources
     * @return File as InputStream
     */
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
