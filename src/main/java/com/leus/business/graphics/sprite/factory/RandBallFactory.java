package com.leus.business.graphics.sprite.factory;

import com.leus.business.graphics.sprite.Ball;

import java.util.Random;

public class RandBallFactory implements SpriteFactory {

    public Ball newSprite(int x, int y) {
        return new Ball(x, y, Ball.ColorBalls.getColorBallFromNumber(new Random().nextInt(4) + 1));
    }
}
