package com.leus.game.fields;

import com.leus.displays.GameFrame;
import com.leus.game.factories.RandBallsFactory;
import com.leus.game.graphics.Ball;
import com.leus.game.graphics.Figure;
import com.leus.panels.GamePanel;

import java.util.LinkedList;
import java.util.List;

public class GameField implements Runnable {

    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;
    public static int START_POSITION_BALL_X = (GameFrame.WIDTH_GAME_FRAME + GameFrame.FIELD_DX) / 2 - GameField.TILE_WIDTH - 2;
    public static int START_POSITION_BALL_Y = 0;
    public static final long DELAY = 500;

    private static List<Ball> ballList = new LinkedList<Ball>();
    private static int[][] matrix = new int[GameFrame.HEIGHT_GAME_FRAME][GameFrame.WIDTH_GAME_FRAME - GameField.TILE_WIDTH];

    private boolean isGameOver = false;
    private RandBallsFactory factory = new RandBallsFactory();
    private Figure figure;
    private GamePanel gamePanel = new GamePanel();

    public static int[][] getMatrix() {
        return matrix;
    }

    public static List<Ball> getBallList() {
        return ballList;
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

        ballList.add(figure.getFirstBall());
        ballList.add(figure.getSecondBall());

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

                ballList.add(figure.getFirstBall());
                ballList.add(figure.getSecondBall());

                isGameOver = figure.getFirstBall().isOutField() || figure.getSecondBall().isOutField();
            } else {
                figure.getFirstBall().moveDown();
                figure.getSecondBall().moveDown();
            }
            gamePanel.repaint();
        }
    }

    private void checkLines() {
        //TODO: Need implement check lines
    }

}
