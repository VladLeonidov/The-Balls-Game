package com.leus.model.service.scores;

import com.leus.model.GameField;
import com.leus.view.displays.PcDisplay;

import java.awt.*;

public class ScoreManager {
    public static final int SIZE_FRAME_FOR_SCORE = 32;
    private static int POSITION_SCORE_ON_FRAME_X = 10;
    private static int POSITION_SCORE_ON_FRAME_Y = PcDisplay.getHeightWindow() + GameField.TILE_HEIGHT + (SIZE_FRAME_FOR_SCORE / 2) + 5;
    private static int factor = 1;
    private static long score = 0;
    private static boolean canResetFactor;
    private static Font fontForScore = new Font("Impact", Font.BOLD, 16);

    private ScoreManager() {
    }

    public static void setPositionScoreOnFrameX(int positionScoreOnFrameX) {
        POSITION_SCORE_ON_FRAME_X = positionScoreOnFrameX;
    }

    public static void setPositionScoreOnFrameY(int positionScoreOnFrameY) {
        POSITION_SCORE_ON_FRAME_Y = positionScoreOnFrameY;
    }

    public static void setFontForScore(Font fontForScore) {
        ScoreManager.fontForScore = fontForScore;
    }

    public static long getScore() {
        return score;
    }

    public static int getFactor() {
        return factor;
    }

    public static boolean isCanResetFactor() {
        return canResetFactor;
    }

    public static void setIsCanResetFactor(boolean canResetFactor) {
        ScoreManager.canResetFactor = canResetFactor;
    }

    public static void addScore(long value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Value cannot by 0 or negative: " + value);
        }
        score += value;
    }

    public static void resetScore() {
        score = 0;
    }

    public static void incrementFactor() {
        if (factor < 12) {
            factor++;
        }
    }

    public static void resetFactor() {
        factor = 1;
    }

    public static void drawScore(Graphics g) {
        g.setFont(fontForScore);
        g.drawString("Your score = " + score + "    X" + factor, POSITION_SCORE_ON_FRAME_X, POSITION_SCORE_ON_FRAME_Y);
    }
}
