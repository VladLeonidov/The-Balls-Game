package com.leus.UI.menuItem;

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

    @Override
    public int hashCode() {
        int result = getCoordinateX();
        result = 31 * result + getCoordinateX();
        result = 31 * result + getCoordinateY();
        result = 31 * result + getHeight();
        result = 31 * result + getWidth();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Logo)) {
            return false;
        }

        Logo that = (Logo) obj;

        return  this.getCoordinateY() == that.getCoordinateY() && this.getCoordinateX() == that.getCoordinateX() &&
                this.getWidth() == that.getWidth() && this.getHeight() ==  that.getHeight();
    }

    @Override
    public String toString() {
        return "Logo{" +
                "x=" + getCoordinateX() +
                ", y=" + getCoordinateY() +
                ", width=" + getWidth() +
                ", height=" + getHeight() + "}";
    }
}
