package com.leus.business.service.score;

import com.leus.business.Game;
import com.leus.view.Display;

import java.awt.*;

public final class ScoreManager {
    public static final int DEFAULT_SIZE_SCORE_FRAME = 32;
    public static final int DEFAULT_POSITION_SCORE_X = 10;
    public static final int DEFAULT_POSITION_SCORE_Y = Display.HEIGHT_GAME_SPACE_WINDOW + Game.TILE_HEIGHT + (DEFAULT_SIZE_SCORE_FRAME / 2) + 5;
    public static final Font DEFAULT_SCORE_FONT = new Font("Impact", Font.BOLD, 16);

    private static int sizeScoreFrame = DEFAULT_SIZE_SCORE_FRAME;
    private static int positionScoreX = DEFAULT_POSITION_SCORE_X;
    private static int positionScoreY = DEFAULT_POSITION_SCORE_Y;
    private static Font scoreFont = DEFAULT_SCORE_FONT;

    private static long score = 0;


    private ScoreManager() {
    }

    public static int getSizeScoreFrame() {
        return sizeScoreFrame;
    }

    public static void setSizeScoreFrame(int sizeScoreFrame) {
        ScoreManager.sizeScoreFrame = sizeScoreFrame;
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

    public static Font getScoreFont() {
        return scoreFont;
    }

    public static void setScoreFont(Font scoreFont) {
        ScoreManager.scoreFont = scoreFont;
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
        g.setFont(scoreFont);

        if (factor == null || factor.equals("")) {
            g.drawString("Your score = " + score, positionScoreX, positionScoreY);
        } else {
            g.drawString("Your score = " + score + "   " + factor, positionScoreX, positionScoreY);
        }
    }
}
