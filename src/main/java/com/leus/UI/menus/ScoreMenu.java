package com.leus.UI.menus;

import com.leus.UI.menuItems.Cursor;
import com.leus.game.service.scores.RecordTable;

import java.awt.*;

public class ScoreMenu extends AbstractMenu {
    private static final Font DEFAULT_FONT = new Font("Impact", Font.BOLD, 16);
    private static final int DEFAULT_COORDINATE_X_FOR_RECORDS = 15;
    private static final int DEFAULT_COORDINATE_Y_FOR_RECORDS = 15;
    private static final int DEFAULT_INTERVAL = 45;
    private RecordTable recordTable;
    private Font fontForRecords;
    private int coordinateXForRecords;
    private int coordinateYForRecords;
    private int interval;

    public ScoreMenu(Image backGround, RecordTable recordTable) {
        this(backGround, recordTable, DEFAULT_COORDINATE_X_FOR_RECORDS, DEFAULT_COORDINATE_Y_FOR_RECORDS, DEFAULT_INTERVAL, DEFAULT_FONT);
    }

    public ScoreMenu(Image backGround, Cursor cursor, RecordTable recordTable) {
        this(backGround, cursor, recordTable, DEFAULT_COORDINATE_X_FOR_RECORDS, DEFAULT_COORDINATE_Y_FOR_RECORDS, DEFAULT_INTERVAL, DEFAULT_FONT);
    }

    public ScoreMenu(Image backGround, RecordTable recordTable, int coordinateXForRecords, int coordinateYForRecords, int interval, Font fontForRecords) {
        super(backGround);
        this.recordTable = recordTable;
        this.coordinateXForRecords = coordinateXForRecords;
        this.coordinateYForRecords = coordinateYForRecords;
        this.interval = interval;
        this.fontForRecords = fontForRecords;
    }

    public ScoreMenu(Image backGround, Cursor cursor, RecordTable recordTable, int coordinateXForRecords, int coordinateYForRecords, int interval, Font fontForRecords) {
        super(backGround, cursor);
        this.recordTable = recordTable;
        this.coordinateXForRecords = coordinateXForRecords;
        this.coordinateYForRecords = coordinateYForRecords;
        this.interval = interval;
        this.fontForRecords = fontForRecords;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(fontForRecords);
        g.setColor(Color.BLACK);

        recordTable.paintRecords(g, coordinateXForRecords, coordinateYForRecords, interval);
    }
}
