package com.leus.model.graphics.sprites.menuItems;

import com.leus.model.graphics.sprites.menuItems.buttons.AbstractButton;

import java.awt.*;

public class MenuCursor {

    private int speed;
    private final Image cursorImg;
    private AbstractButton focus;
    private int coordinateX;
    private int coordinateY;
    private final int width;
    private final int height;

    public MenuCursor(Image cursorImg, int coordinateX, int coordinateY, int width, int height, int speed) {
        this.cursorImg = cursorImg;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    public AbstractButton getFocus() {
        return focus;
    }

    public void setFocus(AbstractButton focus) {
        this.focus = focus;
    }

    public void moveUp() {
        coordinateY -= speed;
    }

    public void moveDown() {
        coordinateY += speed;
    }

    public void enter() {
        focus.push();
    }

    public void paint(Graphics g) {
        g.drawImage(cursorImg, coordinateX, coordinateY, width, height, null);
    }

    public void clear() {
        cursorImg.flush();
    }

    @Override
    public String toString() {
        return "Cursor(x=" + coordinateX + ", y=" + coordinateY +
                ", width=" + width + ", height=" + height +
                ", speed=" + speed + ", focus->" +  focus + ")";
    }
}
