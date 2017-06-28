package com.leus.model.graphics.sprites;

import com.leus.paths.PathsToResources;
import com.leus.utils.ImgLoader;

import java.awt.*;
import java.util.Random;

public enum ColorBalls {

    BLUE(1, ImgLoader.getImage(PathsToResources.BLUE_BALL.getPath())),
    RAD(2, ImgLoader.getImage(PathsToResources.RAD_BALL.getPath())),
    GREEN(3, ImgLoader.getImage(PathsToResources.GREEN_BALL.getPath())),
    YELLOW(4, ImgLoader.getImage(PathsToResources.YELLOW_BALL.getPath()));

    private int number;
    private Image img;

    public int getNumber() {
        return number;
    }

    public Image getImg() {
        return img;
    }

    ColorBalls(int number, Image img) {
        this.number = number;
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
                return getColorBallFromNumber(new Random().nextInt(5));
        }
    }
}
