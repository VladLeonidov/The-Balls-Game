package com.leus.view.panels;

import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.view.displays.GameFrame;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private AbstractSprite[][] ballsOnField = GameFrame.getGameFrame().getGameField().getSpritesOnField();

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
