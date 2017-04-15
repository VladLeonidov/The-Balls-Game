package com.leus.game.factories;

import com.leus.enums.ColorBalls;
import com.leus.game.graphics.Ball;

import java.util.Random;

public class RandBallsFactory implements BallsFactory {

    private Random rand;

    public Ball newBall(int x, int y) {
        rand = new Random();

        return new Ball(x, y, ColorBalls.getColorBallFromNumber(rand.nextInt(5)));
    }
}
