package com.leus.enums;

public enum PathsToResources {
    BLUE_BALL("img/balls/Blue.png"),
    GREEN_BALL("img/balls/Green.png"),
    RAD_BALL("img/balls/Rad.png"),
    YELLOW_BALL("img/balls/Yellow.png"),
    START_BUTTON("img/button/ButtonStart.png"),
    LOGO("img/logo/Logo.png");

    private String path;

    public String getPath() {
        return path;
    }

    PathsToResources(String path) {
        this.path = path;
    }
}
