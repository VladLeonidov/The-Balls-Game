package com.leus.model.graphics.sprites.menuItem;

import java.awt.*;

public class Button {
    private Image buttonImg;
    private int coordinateX;
    private int coordinateY;
    private int width;
    private int height;

    public Button(Image buttonImg, int coordinateX, int coordinateY, int width, int height) {
        this.buttonImg = buttonImg;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.width = width;
        this.height = height;
    }

    public void paint(Graphics g) {
        g.drawImage(buttonImg, coordinateX, coordinateY, width, height, null);
    }

    public void clear() {
        buttonImg.flush();
    }
}
