package com.leus.utils;

import javax.swing.*;
import java.awt.*;

public class ImgLoader {

    private ImgLoader() {
    }

    public static ImageIcon getImageIcon(String path) {
        return new ImageIcon(ImgLoader.class.getClassLoader().getResource(path));
    }

    public static Image getImage(String path) {
        return getImageIcon(path).getImage();
    }
}
