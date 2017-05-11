package com.leus.game.fields;

import com.leus.displays.GameFrame;
import com.leus.game.factories.RandBallsFactory;
import com.leus.game.graphics.Ball;
import com.leus.game.graphics.Figure;
import com.leus.panels.GamePanel;

public class GameField implements Runnable {

    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;
    public static final int START_POSITION_BALL_X = (GameFrame.WIDTH_GAME_FRAME + GameFrame.FIELD_DX) / 2 - GameField.TILE_WIDTH - 2;
    public static final int START_POSITION_BALL_Y = 0;
    public static final long DELAY = 500;
    public static final int COUNT_BALLS_FOR_CLEAR = 4;

    private static Ball[][] ballsOnField = new Ball[GameFrame.FIELD_HEIGHT_IN_TILE + 2][GameFrame.FIELD_WIDTH_IN_TILE];

    private boolean isGameOver = false;
    private RandBallsFactory factory = new RandBallsFactory();
    private Figure figure;
    private GamePanel gamePanel = new GamePanel();

    public static Ball[][] getBallsOnField() {
        return ballsOnField;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public Figure getFigure() {
        return figure;
    }

    public void run() {

        figure = new Figure
                (Ball.createBall(START_POSITION_BALL_X, START_POSITION_BALL_Y, factory),
                 Ball.createBall(START_POSITION_BALL_X + TILE_WIDTH, START_POSITION_BALL_Y, factory));

        ballsOnField[figure.getFirstBall().getY() / TILE_HEIGHT][figure.getFirstBall().getX() / TILE_WIDTH] = figure.getFirstBall();
        ballsOnField[figure.getSecondBall().getY() / TILE_HEIGHT][figure.getSecondBall().getX() / TILE_WIDTH] = figure.getSecondBall();

        while (!isGameOver) {
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (figure.getFirstBall().isFrozen() && !figure.getSecondBall().isFrozen()) {
                figure.getFirstBall().leaveOnTheField();
                checkLines();
                if (!figure.isVertical()) {
                    figure.getSecondBall().moveDown();
                }
            } else if (!figure.getFirstBall().isFrozen() && figure.getSecondBall().isFrozen()) {
                figure.getSecondBall().leaveOnTheField();
                checkLines();
                if (!figure.isVertical()) {
                    figure.getFirstBall().moveDown();
                }
            } else if (figure.getFirstBall().isFrozen() && figure.getSecondBall().isFrozen()) {
                figure.getFirstBall().leaveOnTheField();
                figure.getSecondBall().leaveOnTheField();
                checkLines();

                figure = new Figure
                        (Ball.createBall(START_POSITION_BALL_X, START_POSITION_BALL_Y, factory),
                         Ball.createBall(START_POSITION_BALL_X + TILE_WIDTH, START_POSITION_BALL_Y, factory));

                ballsOnField[figure.getFirstBall().getY() / TILE_HEIGHT][figure.getFirstBall().getX() / TILE_WIDTH] = figure.getFirstBall();
                ballsOnField[figure.getSecondBall().getY() / TILE_HEIGHT][figure.getSecondBall().getX() / TILE_WIDTH] = figure.getSecondBall();

                isGameOver = figure.getFirstBall().isOutField() || figure.getSecondBall().isOutField();
            } else {
                figure.getFirstBall().moveDown();
                figure.getSecondBall().moveDown();
            }
            gamePanel.repaint();
        }
    }

    private void checkLines() {
        //TODO: Need Wave or A* algoritm
    }
}
