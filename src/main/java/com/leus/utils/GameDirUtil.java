package com.leus.utils;

import java.io.File;

/**
 * This class-util is responsible for the game directory in users.
 */
public final class GameDirUtil {

    private static File gameDir = new File(System.getProperty("user.home") + "/BallsGame");

    private GameDirUtil() {
    }

    /**
     * This method return the path to the game directory.
     * @return The path to the game directory as String
     */
    public static String getGameDirPath() {
        return gameDir.getPath();
    }

    /**
     * This method checks the game directory in users.
     * @return True if the game directory there is in users and false if it there isn't
     */
    public static boolean isGameDirInUser() {
        return gameDir.exists();
    }

    /**
     * This method creates the game directory in users, if it there isn't yet.
     * @return True if the game directory was created and false if it there is already
     */
    public static boolean createGameDir() {
        if (!isGameDirInUser()) {
            return gameDir.mkdir();
        }

        return false;
    }
}
