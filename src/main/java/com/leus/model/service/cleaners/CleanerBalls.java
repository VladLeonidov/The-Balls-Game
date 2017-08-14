package com.leus.model.service.cleaners;

import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.model.graphics.sprites.Ball;
import com.leus.model.service.scores.CounterScore;
import com.leus.model.service.scores.ScoreManager;
import com.leus.view.GameFrame;

import java.util.HashSet;
import java.util.Set;

public class CleanerBalls implements CleanerableSprites {

    private static final int countForClearBalls = 4;
    private Set<Ball> ballsReadyToClear = new HashSet<>((GameFrame.FIELD_HEIGHT_IN_TILE + 2) * GameFrame.FIELD_WIDTH_IN_TILE);
    private Set<Ball> setOfTmpBalls = new HashSet<>((GameFrame.FIELD_HEIGHT_IN_TILE + 2) * GameFrame.FIELD_WIDTH_IN_TILE);
    private AbstractSprite[][] gameFieldMatrix;

    private CounterScore counter = () -> {
        int scoreForFourBalls = 100;
        if (setOfTmpBalls.size() == 4) {
            ScoreManager.addScore(scoreForFourBalls);
        } else if (setOfTmpBalls.size() > 4 && setOfTmpBalls.size() < 8) {
            ScoreManager.addScore(scoreForFourBalls + 50 * (setOfTmpBalls.size() - 4));
        } else {
            ScoreManager.addScore(scoreForFourBalls + 100 * (setOfTmpBalls.size() - 4));
        }
    };

    public void clearSprites(AbstractSprite[][] gameFieldMatrix) {
        this.gameFieldMatrix = gameFieldMatrix;
        findBallsReadyToClear();
        clearBalls();
    }

    private void findBallsReadyToClear() {
        for (int i = 0; i < gameFieldMatrix.length; i++) {
            for (int j = 0; j < gameFieldMatrix[i].length; j++) {
                if (gameFieldMatrix[i][j] != null && gameFieldMatrix[i][j].getClass() == Ball.class) {
                    Ball currentBall = (Ball) gameFieldMatrix[i][j];
                    if (!ballsReadyToClear.contains(currentBall)) {
                        findChainsBalls(currentBall, i, j + 1);
                        findChainsBalls(currentBall, i, j - 1);
                        findChainsBalls(currentBall, i - 1, j);
                        findChainsBalls(currentBall, i + 1, j);
                    }

                    if (setOfTmpBalls.size() >= countForClearBalls) {
                        ballsReadyToClear.addAll(setOfTmpBalls);
                        counter.countingScore();
                        setOfTmpBalls.clear();
                    } else {
                        setOfTmpBalls.clear();
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

    private void findChainsBalls(Ball prefBall, int indexY, int indexX) {
        if (isBoundOfGameFieldMatrix(indexX, indexY) || isNotBallOrDifferentColor(prefBall, indexX, indexY)) {
            if (!setOfTmpBalls.contains(prefBall)) setOfTmpBalls.add(prefBall);
        } else {
            Ball currentBall = (Ball) gameFieldMatrix[indexY][indexX];
            if (!setOfTmpBalls.contains(prefBall)) setOfTmpBalls.add(prefBall);

            if (!setOfTmpBalls.contains(currentBall)) {
                findChainsBalls(currentBall, indexY, indexX + 1);
                findChainsBalls(currentBall, indexY, indexX - 1);
                findChainsBalls(currentBall, indexY + 1, indexX);
                findChainsBalls(currentBall, indexY - 1, indexX);
            }
        }
    }

    private boolean isBoundOfGameFieldMatrix(int indexX, int indexY) {
        return indexX < 0 || indexY > gameFieldMatrix.length - 1 || indexY < 0 || indexX > gameFieldMatrix[indexY].length - 1;
    }

    private boolean isNotBallOrDifferentColor(Ball prefBall, int indexX, int indexY) {
        return gameFieldMatrix[indexY][indexX] == null || gameFieldMatrix[indexY][indexX].getClass() != prefBall.getClass() || ((Ball) gameFieldMatrix[indexY][indexX]).getColor() != prefBall.getColor();
    }
}
