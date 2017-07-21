package com.leus.model.factories;

import com.leus.model.graphics.sprites.Ball;
import com.leus.model.graphics.sprites.ColorBalls;
import com.leus.model.graphics.sprites.AbstractSprite;

import java.util.Random;

public class RandBallFactory implements SpriteFactory {

    private Random rand;

    public AbstractSprite newSprite(int x, int y) {
        rand = new Random();

        return new Ball(x, y, ColorBalls.getColorBallFromNumber(rand.nextInt(5)).getImg());
    }
}
