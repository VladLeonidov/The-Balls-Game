package com.leus.model.service;

import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.model.graphics.sprites.Ball;
import com.leus.view.GameFrame;

import java.util.HashSet;
import java.util.Set;

public class CleanerBalls implements CleanerableSprites {

    private static final int countForClearBalls = 4;
    private Set<Ball> ballsReadyToClear = new HashSet<Ball>((GameFrame.FIELD_HEIGHT_IN_TILE + 2) * GameFrame.FIELD_WIDTH_IN_TILE);
    private Set<Ball> tmpBallsSet = new HashSet<Ball>((GameFrame.FIELD_HEIGHT_IN_TILE + 2) * GameFrame.FIELD_WIDTH_IN_TILE);
    private AbstractSprite[][] gameFieldMatrix;

    public void clearSprites(AbstractSprite[][] gameFieldMatrix) {
        this.gameFieldMatrix = gameFieldMatrix;
        findBallsReadyToClear();
        clearBalls();
    }

    private void findBallsReadyToClear() {
        for (int i = 0; i < gameFieldMatrix.length; i++) {
            for (int j = 0; j < gameFieldMatrix[i].length; j++) {
                if (gameFieldMatrix[i][j] != null && gameFieldMatrix[i][j] instanceof Ball) {
                    Ball currentBall = (Ball) gameFieldMatrix[i][j];
                    tmpBallsSet.add(currentBall);
                    findChainsBallsOfRight(currentBall, i, j + 1);
                    findChainsBallsOfLeft(currentBall, i, j - 1);
                    findChainsBallsOfTop(currentBall, i - 1, j);
                    findChainsBallsOfDown(currentBall, i + 1, j);
                    if (tmpBallsSet.size() >= countForClearBalls) {
                        ballsReadyToClear.addAll(tmpBallsSet);
                        tmpBallsSet.clear();
                    } else {
                        tmpBallsSet.clear();
                    }
                }
            }
        }
    }

    private void clearBalls() {
        for (Ball elem : ballsReadyToClear) {
            elem.clear();
        }

        ballsReadyToClear.clear();
    }

    private void findChainsBallsOfRight(Ball currentBall, int indexY, int indexX) {
        if (indexX >= gameFieldMatrix[indexY].length) {
            return;
        } else if (gameFieldMatrix[indexY][indexX] == null || gameFieldMatrix[indexY][indexX].getClass() != currentBall.getClass() || ((Ball) gameFieldMatrix[indexY][indexX]).getColor() != currentBall.getColor()) {
            tmpBallsSet.add(currentBall);
            return;
        } else {
            findChainsBallsOfRight(currentBall, indexY, indexX + 1);
            tmpBallsSet.add(currentBall);
        }
    }

    private void findChainsBallsOfLeft(Ball currentBall, int indexY, int indexX) {
        if (indexX < 0) {
            return;
        } else if (gameFieldMatrix[indexY][indexX] == null || gameFieldMatrix[indexY][indexX].getClass() != currentBall.getClass() || ((Ball) gameFieldMatrix[indexY][indexX]).getColor() != currentBall.getColor()) {
            tmpBallsSet.add(currentBall);
            return;
        } else {
            findChainsBallsOfLeft(currentBall, indexY, indexX - 1);
            tmpBallsSet.add(currentBall);
        }
    }

    private void findChainsBallsOfTop(Ball currentBall, int indexY, int indexX) {
        if (indexY < 0) {
            return;
        } else if (gameFieldMatrix[indexY][indexX] == null || gameFieldMatrix[indexY][indexX].getClass() != currentBall.getClass() || ((Ball) gameFieldMatrix[indexY][indexX]).getColor() != currentBall.getColor()) {
            tmpBallsSet.add(currentBall);
            return;
        } else {
            findChainsBallsOfTop(currentBall, indexY - 1, indexX);
            tmpBallsSet.add(currentBall);
        }
    }

    private void findChainsBallsOfDown(Ball currentBall, int indexY, int indexX) {
        if (indexY >= gameFieldMatrix.length - 1) {
            return;
        } else if (gameFieldMatrix[indexY][indexX] == null || gameFieldMatrix[indexY][indexX].getClass() != currentBall.getClass() || ((Ball) gameFieldMatrix[indexY][indexX]).getColor() != currentBall.getColor()) {
            tmpBallsSet.add(currentBall);
            return;
        } else {
            findChainsBallsOfDown(currentBall, indexY + 1, indexX);
            tmpBallsSet.add(currentBall);
        }
    }
}
