package com.leus.model.menuItems;

import java.awt.*;

public abstract class MenuItem {

    private int coordinateX;
    private int coordinateY;
    private final int width;
    private final int height;

    public MenuItem(int coordinateX, int coordinateY, int width, int height) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.width = width;
        this.height = height;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public abstract void paint(Graphics g);

    public abstract void clear();

}
