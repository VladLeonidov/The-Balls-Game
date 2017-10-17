package com.leus.business.service.score;

public class ScoreFactor {
    private static int factor = 1;
    private static boolean collapsed  = false;

    private ScoreFactor() {
    }

    public static void setCollapsed() {
        ScoreFactor.collapsed = true;
    }

    public static void checkCollapsed() {
        if(!collapsed) {
            resetFactor();
        }
        collapsed = false;
    }

    public static int getFactor() {
        return factor;
    }

    public static String getFactorAsString() {
        return "X: " + factor;
    }

    public static void incrementFactor() {
        collapsed = true;
        if (factor < 12) {
            factor++;
        }
    }

    public static void resetFactor() {
        factor = 1;
    }
}
