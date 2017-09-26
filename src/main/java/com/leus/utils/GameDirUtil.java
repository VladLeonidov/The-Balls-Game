package com.leus.utils;

import java.io.File;

public final class GameDirUtil {
    private static File gameDir = new File(System.getProperty("user.home") + "/BallsGame");

    private GameDirUtil() {
    }

    public static File getGameDir() {
        return gameDir;
    }

    public static void setGameDir(File gameDir) {
        GameDirUtil.gameDir = gameDir;
    }

    public static boolean isGameDirInUser() {
        return gameDir.exists();
    }

    public static boolean createGameDir() {
        if (!isGameDirInUser()) {
            return gameDir.mkdir();
        }

        return false;
    }
}
