package com.leus.model.graphics.sprites.menuItem;

import java.awt.*;

public class Logo {
    private Image logoImg;
    private int coordinateX;
    private int coordinateY;
    private int width;
    private int height;

    public Logo(Image logoImg, int coordinateX, int coordinateY, int width, int height) {
        this.logoImg = logoImg;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.width = width;
        this.height = height;
    }

    public void paint(Graphics g) {
        g.drawImage(logoImg, coordinateX, coordinateY, width, height, null);
    }

    public void clear() {
        logoImg.flush();
    }
}
