package com.leus.game.factories;

import com.leus.game.graphics.Ball;

public interface BallsFactory {
    Ball newBall(int x, int y);
}
