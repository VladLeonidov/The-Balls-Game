package com.leus.utils;

import java.io.File;

public final class GameDirUtil {

    private static File gameDir = new File(System.getProperty("user.home") + "/BallsGame");

    private GameDirUtil() {
    }

    public static String getGameDirPath() {
        return gameDir.getPath();
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
