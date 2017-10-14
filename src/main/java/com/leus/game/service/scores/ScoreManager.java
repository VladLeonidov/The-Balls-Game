package com.leus.game.service.scores;

import com.leus.game.Game;
import com.leus.display.Display;

import java.awt.*;

public final class ScoreManager {
    public static final int DEFAULT_SIZE_FRAME_FOR_SCORE = 32;
    public static final int DEFAULT_POSITION_SCORE_X = 10;
    public static final int DEFAULT_POSITION_SCORE_Y = Display.HEIGHT_GAME_SPACE_WINDOW + Game.TILE_HEIGHT + (DEFAULT_SIZE_FRAME_FOR_SCORE / 2) + 5;
    public static final Font DEFAULT_FONT_FOR_SCORE = new Font("Impact", Font.BOLD, 16);

    private static int sizeFrameForScore = DEFAULT_SIZE_FRAME_FOR_SCORE;
    private static int positionScoreX = DEFAULT_POSITION_SCORE_X;
    private static int positionScoreY = DEFAULT_POSITION_SCORE_Y;
    private static Font fontForScore = DEFAULT_FONT_FOR_SCORE;

    private static long score = 0;

    private ScoreManager() {
    }

    public static int getSizeFrameForScore() {
        return sizeFrameForScore;
    }

    public static void setSizeFrameForScore(int sizeFrameForScore) {
        ScoreManager.sizeFrameForScore = sizeFrameForScore;
    }

    public static int getPositionScoreX() {
        return positionScoreX;
    }

    public static void setPositionScoreX(int positionScoreX) {
        ScoreManager.positionScoreX = positionScoreX;
    }

    public static int getPositionScoreY() {
        return positionScoreY;
    }

    public static void setPositionScoreY(int positionScoreY) {
        ScoreManager.positionScoreY = positionScoreY;
    }

    public static Font getFontForScore() {
        return fontForScore;
    }

    public static void setFontForScore(Font fontForScore) {
        ScoreManager.fontForScore = fontForScore;
    }

    public static long getScore() {
        return score;
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

    public static void drawScore(Graphics g, String factor) {
        g.setFont(fontForScore);

        if (factor == null || factor.equals("")) {
            g.drawString("Your score = " + score, positionScoreX, positionScoreY);
        } else {
            g.drawString("Your score = " + score + "   " + factor, positionScoreX, positionScoreY);
        }
    }
}
