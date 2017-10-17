package com.leus.UI.menu;

import com.leus.UI.menuItem.Cursor;
import com.leus.business.GameOverListener;
import com.leus.business.service.score.Record;
import com.leus.business.service.score.RecordTable;
import com.leus.business.service.score.ScoreManager;
import com.leus.view.Display;

import javax.swing.*;
import java.awt.*;

public class GameOverMenu extends AbstractMenu {

    private int scorePositionY = Display.HEIGHT_WINDOW - 25;
    private Font scoreFont = new Font("Impact", Font.BOLD, 26);

    public GameOverMenu(Image backGround) {
        super(backGround);
    }

    public GameOverMenu(Image backGround, Cursor cursor) {
        super(backGround, cursor);
    }

    public void setScoreFont(Font scoreFont) {
        this.scoreFont = scoreFont;
    }

    public void paint(Graphics g) {
        super.paint(g);
        paintScore(g);
    }

    private void paintScore(Graphics g) {
        g.setColor(Color.BLACK);
        ScoreManager.setPositionScoreX(ScoreManager.DEFAULT_POSITION_SCORE_X);
        ScoreManager.setPositionScoreY(scorePositionY);
        ScoreManager.setScoreFont(scoreFont);
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
