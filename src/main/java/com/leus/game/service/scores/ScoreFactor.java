package com.leus.game.service.scores;

public class ScoreFactor {
    private static int factor = 1;

    private ScoreFactor() {
    }

    public static int getFactor() {
        return factor;
    }

    public static String getFactorAsString() {
        return "X" + factor;
    }

    public static void incrementFactor() {
        if (factor < 12) {
            factor++;
        }
    }

    public static void resetFactor() {
        factor = 1;
    }
}
