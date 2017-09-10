package com.leus.model.menuItems;

import java.awt.*;

public final class Logo extends MenuItem {
    private final Image logoImg;

    public Logo(Image logoImg, int coordinateX, int coordinateY, int width, int height) {
        super(coordinateX, coordinateY, width, height);
        this.logoImg = logoImg;
    }

    public void paint(Graphics g) {
        g.drawImage(logoImg, getCoordinateX(), getCoordinateY(), getWidth(), getHeight(), null);
    }

    public void clear() {
        logoImg.flush();
    }
}
