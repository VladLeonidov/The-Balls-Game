package com.leus.UI.menu;

import com.leus.UI.menuItem.Cursor;
import com.leus.business.service.score.RecordTable;

import java.awt.*;

public class ScoreMenu extends AbstractMenu {
    private static final Font DEFAULT_FONT = new Font("Impact", Font.BOLD, 16);
    private static final int DEFAULT_RECORDS_COORDINATE_X = 15;
    private static final int DEFAULT_RECORDS_COORDINATE_Y = 15;
    private static final int DEFAULT_INTERVAL = 45;
    private RecordTable recordTable;
    private Font recordsFont;
    private int recordsCoordinateX;
    private int recordsCoordinateY;
    private int interval;

    public ScoreMenu(Image backGround, RecordTable recordTable) {
        this(backGround, recordTable, DEFAULT_RECORDS_COORDINATE_X, DEFAULT_RECORDS_COORDINATE_Y, DEFAULT_INTERVAL, DEFAULT_FONT);
    }

    public ScoreMenu(Image backGround, Cursor cursor, RecordTable recordTable) {
        this(backGround, cursor, recordTable, DEFAULT_RECORDS_COORDINATE_X, DEFAULT_RECORDS_COORDINATE_Y, DEFAULT_INTERVAL, DEFAULT_FONT);
    }

    public ScoreMenu(Image backGround, RecordTable recordTable, int recordsCoordinateX, int recordsCoordinateY, int interval, Font recordsFont) {
        super(backGround);
        this.recordTable = recordTable;
        this.recordsCoordinateX = recordsCoordinateX;
        this.recordsCoordinateY = recordsCoordinateY;
        this.interval = interval;
        this.recordsFont = recordsFont;
    }

    public ScoreMenu(Image backGround, Cursor cursor, RecordTable recordTable, int recordsCoordinateX, int recordsCoordinateY, int interval, Font recordsFont) {
        super(backGround, cursor);
        this.recordTable = recordTable;
        this.recordsCoordinateX = recordsCoordinateX;
        this.recordsCoordinateY = recordsCoordinateY;
        this.interval = interval;
        this.recordsFont = recordsFont;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(recordsFont);
        g.setColor(Color.BLACK);

        recordTable.paintRecords(g, recordsCoordinateX, recordsCoordinateY, interval);
    }
}
