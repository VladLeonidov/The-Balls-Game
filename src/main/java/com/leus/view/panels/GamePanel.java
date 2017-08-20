package com.leus.view.panels;

import com.leus.model.GameField;
import com.leus.model.graphics.figures.AbstractFigure;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.model.service.scores.ScoreManager;
import com.leus.paths.PathsToResources;
import com.leus.utils.ResourceLoader;
import com.leus.view.displays.PcDisplay;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private static AbstractSprite[][] gameFieldMatrix = GameField.getGameFieldMatrix();
    private GameField gameField;
    private AbstractFigure figure;
    private boolean inMenu = false;

    public GamePanel(GameField gameField) {
        this.gameField = gameField;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (inMenu) {
            paintMenu(g);
        } else {
            figure = gameField.getFigure();
            paintFigure(g);
            paintBallsOnField(g);
            ScoreManager.drawScore(g);
        }
    }

    private void paintMenu(Graphics g) {
        Image logo = ResourceLoader.loadImage(PathsToResources.LOGO.getPath());
        g.drawImage(logo, (PcDisplay.getWidthWindow() - logo.getWidth(null)) / 2, PcDisplay.getHeightWindow() / 2, null);
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

    public class StartButton extends JButton {
        public void startGame() {
            gameField.startGameProcess(GamePanel.this);
        }
    }
}
