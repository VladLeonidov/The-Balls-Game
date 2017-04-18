package com.leus.game.fields;

import com.leus.displays.GameFrame;
import com.leus.game.factories.RandBallsFactory;
import com.leus.game.graphics.Ball;
import com.leus.game.graphics.Figure;
import com.leus.panels.GamePanel;

import java.util.HashMap;
import java.util.Map;

public class GameField implements Runnable {

    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;
    public static final int START_POSITION_BALL_X = (GameFrame.WIDTH_GAME_FRAME + GameFrame.FIELD_DX) / 2 - GameField.TILE_WIDTH - 2;
    public static final int START_POSITION_BALL_Y = 0;
    public static final long DELAY = 500;
    public static final int BALLS_FOR_CLEAR = 6;

    private static Map<Integer, Ball> ballMapOnField = new HashMap<Integer, Ball>();
    private static int[][] matrix = new int[GameFrame.HEIGHT_GAME_FRAME][GameFrame.WIDTH_GAME_FRAME - GameField.TILE_WIDTH];

    private boolean isGameOver = false;
    private RandBallsFactory factory = new RandBallsFactory();
    private Figure figure;
    private GamePanel gamePanel = new GamePanel();

    public static int[][] getMatrix() {
        return matrix;
    }

    public static Map<Integer, Ball> getBallMapOnField() {
        return ballMapOnField;
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

        ballMapOnField.put(figure.getFirstBall().getCountId(), figure.getFirstBall());
        ballMapOnField.put(figure.getSecondBall().getCountId(), figure.getSecondBall());

        while (!isGameOver) {
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (figure.getFirstBall().isFrozen() && !figure.getSecondBall().isFrozen()) {
                checkLines();
                figure.getFirstBall().leaveOnTheField();
                figure.getSecondBall().moveDown();
            } else if (!figure.getFirstBall().isFrozen() && figure.getSecondBall().isFrozen()) {
                checkLines();
                figure.getSecondBall().leaveOnTheField();
                figure.getFirstBall().moveDown();
            } else if (figure.getFirstBall().isFrozen() && figure.getSecondBall().isFrozen()) {
                checkLines();
                figure.getFirstBall().leaveOnTheField();
                figure.getSecondBall().leaveOnTheField();

                figure = new Figure
                        (Ball.createBall(START_POSITION_BALL_X, START_POSITION_BALL_Y, factory),
                                Ball.createBall(START_POSITION_BALL_X + TILE_WIDTH, START_POSITION_BALL_Y, factory));

                ballMapOnField.put(figure.getFirstBall().getCountId(), figure.getFirstBall());
                ballMapOnField.put(figure.getSecondBall().getCountId(), figure.getSecondBall());

                isGameOver = figure.getFirstBall().isOutField() || figure.getSecondBall().isOutField();
            } else {
                figure.getFirstBall().moveDown();
                figure.getSecondBall().moveDown();
            }
            gamePanel.repaint();
        }
    }

    private void checkLines() {
        //TODO: need implement check Lines
    }
}
