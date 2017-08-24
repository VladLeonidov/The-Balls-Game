package com.leus.view.panels;

import com.leus.model.GameField;
import com.leus.model.GameMenu;
import com.leus.model.ScoreMenu;
import com.leus.model.graphics.figures.AbstractFigure;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.model.service.scores.ScoreManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private static AbstractSprite[][] gameFieldMatrix = GameField.getGameFieldMatrix();

    private GameField gameField;
    private GameMenu mainMenu;
    private ScoreMenu scoreMenu;
    private AbstractFigure figure;

    public GamePanel(GameField gameField, GameMenu mainMenu, ScoreMenu scoreMenu) {
        this.gameField = gameField;
        this.mainMenu = mainMenu;
        this.scoreMenu = scoreMenu;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (GameMenu.isFocusMenu()) {
            mainMenu.paintMenu(g);
        } else if (ScoreMenu.isFocusMenu()) {
            scoreMenu.paint(g);
        } else {
            figure = gameField.getFigure();
            paintFigure(g);
            paintBallsOnField(g);
            ScoreManager.drawScore(g);
        }
    }

    private void paintFigure(Graphics g) {
        figure.paint(g);
    }

    private void paintBallsOnField(Graphics g) {
        for (AbstractSprite[] line : gameFieldMatrix) {
            for (AbstractSprite elem : line) {
                if (elem != null) {
                    elem.paint(g);
                }
            }
        }
    }
}
