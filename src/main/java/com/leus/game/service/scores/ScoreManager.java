package com.leus.game.service.scores;

import com.leus.game.Game;
import com.leus.display.Display;

import java.awt.*;

public final class ScoreManager {
    public static final int SIZE_FRAME_FOR_SCORE = 32;
    public static final int DEFAULT_POSITION_SCORE_X = 10;
    public static final int DEFAULT_POSITION_SCORE_Y = Display.HEIGHT_GAME_SPACE_WINDOW + Game.TILE_HEIGHT + (SIZE_FRAME_FOR_SCORE / 2) + 5;
    public static final Font DEFAULT_FONT_FOR_SCORE = new Font("Impact", Font.BOLD, 16);

    private static long score = 0;

    private ScoreManager() {
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

    public static void drawScore(Graphics g, int positionX, int positionY, Font font, String factor) {
        if (font == null) {
            g.setFont(DEFAULT_FONT_FOR_SCORE);
        } else {
            g.setFont(font);
        }

        if (factor == null || factor.equals("")) {
            g.drawString("Your score = " + score, positionX, positionY);
        } else {
            g.drawString("Your score = " + score + "   " + factor, positionX, positionY);
        }
    }
}
