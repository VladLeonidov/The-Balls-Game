package com.leus.game.fields;

import com.leus.displays.GameFrame;
import com.leus.enums.ColorBalls;
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

    private static Ball[][] ballsOnField = new Ball[GameFrame.FIELD_HEIGHT_IN_TILE + 2][GameFrame.FIELD_WIDTH_IN_TILE + 1];
    private static int[][] glass = new int[GameFrame.FIELD_HEIGHT_IN_TILE + 2][GameFrame.FIELD_WIDTH_IN_TILE + 1];

    private boolean isGameOver = false;
    private RandBallsFactory factory = new RandBallsFactory();
    private Figure figure;
    private GamePanel gamePanel = new GamePanel();

    public static int[][] getGlass() {
        return glass;
    }

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
        if (isLineFull(ColorBalls.BLUE.getNumber()) || isLineFull(ColorBalls.RAD.getNumber()) ||
            isLineFull(ColorBalls.GREEN.getNumber()) || isLineFull(ColorBalls.YELLOW.getNumber())) {
            clearLine();
        }
    }

    private boolean isLineFull(int type) {
        if (isHorizontalFull(type) || isVerticalFull(type)) {
            return true;
        }

        return false;
    }

    private boolean isHorizontalFull(int type) {
        for (int i = 0; i < glass.length; i++) {
            int count = 0;
            for (int j = 0; j < glass[i].length; j++) {
                if (glass[i][j] == type) {
                    count++;
                }
            }

            if (count >= COUNT_BALLS_FOR_CLEAR) {
                /*count--;
                while (count >= 0) {
                    ballsOnField[i][count] = null;
                    glass[i][count] = 0;
                    count--;
                }*/
                return true;
            }
        }

        return false;
    }

    private boolean isVerticalFull(int type) {
        for (int j = 0; j < glass[0].length; j++) {
            int count = 0;
            for (int i = 0; i < glass.length; i++) {
                if (glass[i][j] == type) {
                    count++;
                }
            }

            if (count >= COUNT_BALLS_FOR_CLEAR) {
                return true;
            }
        }

        return false;
    }

    private void clearLine() {
        System.out.println("Line was cleared");
    }
}
