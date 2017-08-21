package com.leus.model.graphics.sprites.menuItem;

import java.awt.*;

public class MenuCursor {

    private static final int SPEED = 32;

    private Image cursorImg;
    private Button focus;
    private int coordinateX;
    private int coordinateY;
    private int width;
    private int height;

    public MenuCursor(Image cursorImg, int coordinateX, int coordinateY, int width, int height) {
        this.cursorImg = cursorImg;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.width = width;
        this.height = height;
    }

    public Button getFocus() {
        return focus;
    }

    public void setFocus(Button focus) {
        this.focus = focus;
    }

    public void moveUp() {
        coordinateY -= SPEED;
    }

    public void moveDown() {
        coordinateY += SPEED;
    }

    public void enter() {

    }

    public void paint(Graphics g) {
        g.drawImage(cursorImg, coordinateX, coordinateY, width, height, null);
    }

    public void clear() {
        cursorImg.flush();
    }
}
