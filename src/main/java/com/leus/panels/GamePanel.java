package com.leus.panels;

import com.leus.game.fields.GameField;
import com.leus.game.graphics.Ball;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private static Ball[][] ballsOnField = GameField.getBallsOnField();

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintGame(g);
    }

    private void paintGame(Graphics g) {
        for (int i = 0; i < ballsOnField.length; i++) {
            for (int j = 0; j < ballsOnField[i].length; j++) {
                if (ballsOnField[i][j] != null) {
                    ballsOnField[i][j].paint(g);
                }
            }
        }
    }
}
