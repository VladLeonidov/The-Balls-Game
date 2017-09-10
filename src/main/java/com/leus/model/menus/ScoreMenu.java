package com.leus.model.menus;

import com.leus.model.menuItems.Button;
import com.leus.model.menuItems.Cursor;
import com.leus.view.displays.Display;

import java.awt.*;

public class ScoreMenu extends AbstractMenu {
    private static final Font DEFAULT_FONT = new Font("Impact", Font.BOLD, 16);
    private String[] records;
    private Font fontForRecords = DEFAULT_FONT;
    private int coordinateXForRecords;
    private int coordinateYForRecords;
    private int interval;

    public ScoreMenu(Image backGround, String[] records, Cursor cursor, int coordinateXForRecords, int coordinateYForRecords, int interval, Button... buttons) {
        super(backGround, cursor, buttons);
        this.records = records;
        this.coordinateXForRecords = coordinateXForRecords;
        this.coordinateYForRecords = coordinateYForRecords;
        this.interval = interval;
    }

    public void paint(Graphics g) {
        g.drawImage(getBackGround(), 0, 0, Display.getWidthWindow(), Display.getHeightWindow(), null);
        g.setFont(fontForRecords);
        int tmp = coordinateYForRecords;

        g.setColor(Color.BLACK);
        for (String record : records) {
            g.drawString(record, coordinateXForRecords, coordinateYForRecords += interval);
        }
        coordinateYForRecords = tmp;
        for (Button currentButton : getButtons()) {
            currentButton.paint(g);
        }
        getCursor().paint(g);
    }

    public void clear() {
        getBackGround().flush();
        getCursor().clear();
        for (Button elem : getButtons()) {
            elem.clear();
        }
    }
}
