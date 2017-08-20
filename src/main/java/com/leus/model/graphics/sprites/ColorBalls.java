package com.leus.model.graphics.sprites;

import com.leus.paths.PathsToResources;
import com.leus.utils.ResourceLoader;

import java.awt.*;

public enum ColorBalls {

    BLUE(ResourceLoader.loadImage(PathsToResources.BLUE_BALL.getPath())),
    RAD(ResourceLoader.loadImage(PathsToResources.RAD_BALL.getPath())),
    GREEN(ResourceLoader.loadImage(PathsToResources.GREEN_BALL.getPath())),
    YELLOW(ResourceLoader.loadImage(PathsToResources.YELLOW_BALL.getPath()));

    private Image img;

    public Image getImg() {
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
