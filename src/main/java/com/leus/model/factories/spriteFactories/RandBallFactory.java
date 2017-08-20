package com.leus.model.factories.spriteFactories;

import com.leus.model.graphics.sprites.Ball;
import com.leus.model.graphics.sprites.ColorBalls;

import java.util.Random;

public class RandBallFactory implements SpriteFactory {

    public Ball newSprite(int x, int y) {
        return new Ball(x, y, ColorBalls.getColorBallFromNumber(new Random().nextInt(4) + 1));
    }
}
