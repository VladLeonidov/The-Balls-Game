package com.leus.business.graphics.sprite;

import com.leus.business.Game;
import com.leus.util.PathsToResources;
import com.leus.util.ResourceLoader;

import java.awt.*;

public class Ball extends AbstractSprite {

    private ColorBalls color;

    public enum ColorBalls {

        BLUE(ResourceLoader.loadImage(PathsToResources.BLUE_BALL.getPath())),
        RAD(ResourceLoader.loadImage(PathsToResources.RAD_BALL.getPath())),
        GREEN(ResourceLoader.loadImage(PathsToResources.GREEN_BALL.getPath())),
        YELLOW(ResourceLoader.loadImage(PathsToResources.YELLOW_BALL.getPath()));

        private Image img;

        public final Image getImg() {
            return img;
        }

        ColorBalls(Image img) {
            this.img = img;
        }

        public static ColorBalls getColorBallFromNumber(int num) {
            switch (num) {
                case 1:
                    return BLUE;
                case 2:
                    return RAD;
                case 3:
                    return GREEN;
                case 4:
                    return YELLOW;
                default:
                    throw new IllegalArgumentException("No color for this number " + num);
            }
        }
    }

    public Ball(int coordinateX, int coordinateY, ColorBalls color) {
        super(coordinateX, coordinateY);
        this.color = color;
    }

    public ColorBalls getColor() {
        return color;
    }

    public boolean isOutField() {
        if (gameFieldMatrix[getCoordinateY() / Game.TILE_HEIGHT][getCoordinateX() / Game.TILE_WIDTH] != null &&
                gameFieldMatrix[getCoordinateY() / Game.TILE_HEIGHT + 1][getCoordinateX() / Game.TILE_WIDTH] != null) {
            return true;
        }

        return false;
    }

    public void paint(Graphics g) {
        g.drawImage(color.getImg(), getCoordinateX(), getCoordinateY(), null);
    }

    public void clear() {
        gameFieldMatrix[getCoordinateY() / Game.TILE_HEIGHT][getCoordinateX() / Game.TILE_WIDTH] = null;

    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + getCoordinateX();
        result = 31 * result + getCoordinateY();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Ball)) {
            return false;
        }

        Ball that = (Ball) obj;

        return this.getCoordinateX() == that.getCoordinateX() && this.getCoordinateY() == that.getCoordinateY();
    }

    @Override
    public String toString() {
        return "Ball{x=" + getCoordinateX() + ", y=" + getCoordinateY() + ", color=" + color + '}';
    }
}
