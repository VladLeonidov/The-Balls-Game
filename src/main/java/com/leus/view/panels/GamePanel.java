package com.leus.view.panels;

import com.leus.model.GameField;
import com.leus.model.graphics.figures.AbstractFigure;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.model.service.scores.ScoreManager;
import com.leus.view.GameFrame;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private static AbstractSprite[][] gameFieldMatrix = GameField.getGameFieldMatrix();
    private AbstractFigure figure;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        figure = GameFrame.getGameFrame(null, null, null).getGameField().getFigure();
        paintFigure(g);
        paintBallsOnField(g);
        ScoreManager.drawScore(g);
    }

    private void paintFigure(Graphics g) {
        figure.paint(g);
    }

    private void paintBallsOnField(Graphics g) {
        for (int i = 0; i < gameFieldMatrix.length; i++) {
            for (int j = 0; j < gameFieldMatrix[i].length; j++) {
                if (gameFieldMatrix[i][j] != null) {
                    gameFieldMatrix[i][j].paint(g);
                }
            }
        }
    }
}
