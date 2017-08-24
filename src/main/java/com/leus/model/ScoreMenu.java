package com.leus.model;

import com.leus.view.displays.PcDisplay;

import java.awt.*;

public class ScoreMenu {
    private static final Font DEFAULT_FONT = new Font("Impact", Font.BOLD, 16);
    private static boolean focusMenu;
    private final Image backGround;
    private String[] records;
    private Font currentFont = DEFAULT_FONT;
    private int coordinateXForRecords;
    private int coordinateYForRecords;
    private int interval;

    public ScoreMenu(Image backGround, String[] records, int coordinateXForRecords, int coordinateYForRecords, int interval) {
        this.backGround = backGround;
        this.records = records;
        this.coordinateXForRecords = coordinateXForRecords;
        this.coordinateYForRecords = coordinateYForRecords;
        this.interval = interval;
    }

    public static boolean isFocusMenu() {
        return focusMenu;
    }

    public static void setFocusMenu(boolean focusMenu) {
        ScoreMenu.focusMenu = focusMenu;
    }

    public void paint(Graphics g) {
        g.drawImage(backGround, 0, 0, PcDisplay.getWidthWindow(), PcDisplay.getHeightWindow(), null);
        g.setFont(currentFont);
        for (String record : records) {
            g.drawString(record, coordinateXForRecords, coordinateYForRecords += interval);
        }
    }

    public void clear() {
        backGround.flush();
    }
}
