package com.leus.model.graphics.sprites;

import com.leus.paths.PathsToResources;
import com.leus.utils.ImgLoader;
import java.awt.Image;
import java.util.Random;

public enum ColorBalls {

    BLUE(ImgLoader.getImage(PathsToResources.BLUE_BALL.getPath())),
    RAD(ImgLoader.getImage(PathsToResources.RAD_BALL.getPath())),
    GREEN(ImgLoader.getImage(PathsToResources.GREEN_BALL.getPath())),
    YELLOW(ImgLoader.getImage(PathsToResources.YELLOW_BALL.getPath()));

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
                return getColorBallFromNumber(new Random().nextInt(5) + 1);
        }
    }
}
