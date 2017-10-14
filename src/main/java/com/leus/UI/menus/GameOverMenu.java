package com.leus.UI.menus;

import com.leus.UI.menuItems.Cursor;
import com.leus.game.GameOverListener;
import com.leus.game.service.scores.Record;
import com.leus.game.service.scores.RecordTable;
import com.leus.game.service.scores.ScoreManager;
import com.leus.display.Display;

import javax.swing.*;
import java.awt.*;

public class GameOverMenu extends AbstractMenu {

    private int scorePositionY = Display.HEIGHT_WINDOW - 25;
    private Font fontForScore = new Font("Impact", Font.BOLD, 26);

    public GameOverMenu(Image backGround) {
        super(backGround);
    }

    public GameOverMenu(Image backGround, Cursor cursor) {
        super(backGround, cursor);
    }

    public void setFontForScore(Font fontForScore) {
        this.fontForScore = fontForScore;
    }

    public void paint(Graphics g) {
        super.paint(g);
        paintingScore(g);
    }

    private void paintingScore(Graphics g) {
        g.setColor(Color.BLACK);
        ScoreManager.setPositionScoreX(ScoreManager.DEFAULT_POSITION_SCORE_X);
        ScoreManager.setPositionScoreY(scorePositionY);
        ScoreManager.setFontForScore(fontForScore);
        ScoreManager.drawScore(g, null);
    }

    public class GameOverListenerImpl implements GameOverListener {
        @Override
        public void onEvent() {
            setActive(true);
            Cursor cursor = getCursor();
            if (cursor != null) {
                cursor.setActive(true);
            }

            checkIsNewRecord();
        }

        private void checkIsNewRecord() {
            long currentScore = ScoreManager.getScore();
            RecordTable recordTable = RecordTable.getInstance();

            if (recordTable.isNewRecord(currentScore)) {
                String nameRecord = JOptionPane.showInputDialog(null, "Enter name of record:", "Congratulation you have new record!", JOptionPane.PLAIN_MESSAGE);
                if (nameRecord == null) {
                    return;
                }
                recordTable.setRecord(new Record(nameRecord, currentScore));
                recordTable.saveRecordsToFile();
            }
        }
    }
}
