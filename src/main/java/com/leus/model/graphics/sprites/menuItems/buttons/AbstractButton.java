package com.leus.model.graphics.sprites.menuItems.buttons;

import java.awt.*;

public abstract class AbstractButton {
    private final Image buttonImg;
    private final int coordinateX;
    private final int coordinateY;
    private final int width;
    private final int height;

    public AbstractButton(Image buttonImg, int coordinateX, int coordinateY, int width, int height) {
        this.buttonImg = buttonImg;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.width = width;
        this.height = height;
    }

    public abstract void push();

    public void paint(Graphics g) {
        g.drawImage(buttonImg, coordinateX, coordinateY, width, height, null);
    }

    public void clear() {
        buttonImg.flush();
    }
}
