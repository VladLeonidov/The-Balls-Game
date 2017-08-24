package com.leus.paths;

public enum PathsToResources {
    BLUE_BALL("img/balls/Blue.png"),
    GREEN_BALL("img/balls/Green.png"),
    RAD_BALL("img/balls/Rad.png"),
    YELLOW_BALL("img/balls/Yellow.png"),
    BACK_GROUND_MENU("img/BackGroundMenu.png"),
    LOGO("img/Logo.png"),
    START_BUTTON("img/buttons/StartButton.png"),
    EXIT_BUTTON("img/buttons/ExitButton.png"),
    SCORE_BUTTON("img/buttons/ScoreButton.png"),
    MENU_CURSOR("img/cursors/MenuCursor.png");

    private String path;

    public String getPath() {
        return path;
    }

    PathsToResources(String path) {
        this.path = path;
    }
}
