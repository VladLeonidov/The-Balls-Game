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
    public static final int BALLS_FOR_CLEAR = 4;

    private static Map<Integer, Ball> ballsOnField = new HashMap<Integer, Ball>();
    private static int[][] matrix = new int[GameFrame.FIELD_HEIGHT_IN_TILE + 2][GameFrame.FIELD_WIDTH_IN_TILE + 1];

    private boolean isGameOver = false;
    private RandBallsFactory factory = new RandBallsFactory();
    private Figure figure;
    private GamePanel gamePanel = new GamePanel();

    public static int[][] getMatrix() {
        return matrix;
    }

    public static Map<Integer, Ball> getBallsOnField() {
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

        ballsOnField.put(figure.getFirstBall().getCountId(), figure.getFirstBall());
        ballsOnField.put(figure.getSecondBall().getCountId(), figure.getSecondBall());

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

                ballsOnField.put(figure.getFirstBall().getCountId(), figure.getFirstBall());
                ballsOnField.put(figure.getSecondBall().getCountId(), figure.getSecondBall());

                isGameOver = figure.getFirstBall().isOutField() || figure.getSecondBall().isOutField();
            } else {
                figure.getFirstBall().moveDown();
                figure.getSecondBall().moveDown();
            }
            gamePanel.repaint();
        }
    }

    private void checkLines() {
        if (isLineBlueBalls() || isLineRadBalls() || isLineGreenBalls() || isLineYellowBalls()) {
            clearLine();
        }
    }

    private boolean isLineBlueBalls() {
        //TODO: need implement check vertical lines
        for (int i = 0; i < matrix.length; i++) {
            int count = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    count++;
                }
            }

            if (count == BALLS_FOR_CLEAR) {
                return true;
            }
        }

        return false;
    }

    private boolean isLineRadBalls() {
        //TODO: need implement check vertical lines
        for (int i = 0; i < matrix.length - 1; i++) {
            int count = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 2) {
                    count++;
                }
            }

            if (count == BALLS_FOR_CLEAR) {
                return true;
            }
        }

        return false;
    }

    private boolean isLineGreenBalls() {
        //TODO: need implement check vertical lines
        for (int i = 0; i < matrix.length; i++) {
            int count = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 3) {
                    count++;
                }
            }

            if (count == BALLS_FOR_CLEAR) {
                return true;
            }
        }

        return false;
    }

    private boolean isLineYellowBalls() {
        //TODO: need implement check vertical lines
        for (int i = 0; i < matrix.length; i++) {
            int count = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 4) {
                    count++;
                }
            }

            if (count == BALLS_FOR_CLEAR) {
                return true;
            }
        }

        return false;
    }

    private void clearLine() {
        //TODO: need implement clear Balls
    }
}
