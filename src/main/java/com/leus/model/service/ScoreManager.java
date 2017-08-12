package com.leus.model.service;

import com.leus.view.GameFrame;

import java.awt.*;

public class ScoreManager {
    private static int POSITION_SCORE_ON_FRAME_X = 15;
    private static int POSITION_SCORE_ON_FRAME_Y = GameFrame.HEIGHT_GAME_FRAME + GameFrame.ADD_TO_FRAME_SIZE_IN_HEIGHT - GameFrame.FIELD_SIZE_FOR_SCORE - (GameFrame.FIELD_SIZE_FOR_SCORE - 25);
    private static long score = 0;
    private static Font fontForScore = new Font("Gabriola", 8, 24);

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

    public static void addScore(long value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Value cannot by 0 or negative: " + value);
        }
        score += value;
    }

    public static void resetScore() {
        score = 0;
    }

    public static void drowScore(Graphics g) {
        g.setFont(fontForScore);
        g.drawString("Your score = " + score, POSITION_SCORE_ON_FRAME_X, POSITION_SCORE_ON_FRAME_Y);
    }
}
