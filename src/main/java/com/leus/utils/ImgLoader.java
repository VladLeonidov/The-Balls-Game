package com.leus.utils;

import javax.swing.*;
import java.awt.*;

public class ImgLoader {

    private ImgLoader() {
    }

    public static ImageIcon getImageIcon(String path) {

        if (path == null || path.length() == 0) {
            throw new IllegalArgumentException("Path can't by null or length by 0");
        }

        return new ImageIcon(ImgLoader.class.getClassLoader().getResource(path));
    }

    public static Image getImage(String path) {

        if (path == null || path.length() == 0) {
            throw new IllegalArgumentException("Path can't by null or length by 0");
        }

        return getImageIcon(path).getImage();
    }
}
