package com.leus.view;

import com.leus.controllers.BallsKeyController;
import com.leus.model.GameField;
import com.leus.model.service.FigureManager;

import javax.swing.*;
import java.awt.*;

import static com.leus.model.GameField.TILE_HEIGHT;
import static com.leus.model.GameField.TILE_WIDTH;

public class GameFrame {

    public static final int FIELD_WIDTH_IN_TILE = 6;
    public static final int FIELD_HEIGHT_IN_TILE = 12;
    public static final int WIDTH_GAME_FRAME = TILE_WIDTH * FIELD_WIDTH_IN_TILE;
    public static final int HEIGHT_GAME_FRAME = TILE_HEIGHT * FIELD_HEIGHT_IN_TILE;
    public static final int FIELD_ADD_TO_WIDTH = 15;
    public static final int FIELD_ADD_TO_HEIGHT = 64;

    private static GameFrame gameFrame;
    private static GameField gameField;
    private static final FigureManager figureManager = new FigureManager();

    private final JFrame frameWorker;
    private final String title;
    private JPanel gamePanel;

    private GameFrame(GameField gameField, JFrame frameWorker, String title) {
        this.gameField = gameField;
        this.frameWorker = frameWorker;
        this.title = title;
        this.gamePanel = gameField.getGamePanel();
    }

    public static GameFrame getGameFrame(GameField gameField, JFrame frameWorker, String title) {
        if (gameFrame == null) {
            gameFrame = new GameFrame(gameField, frameWorker, title);
        }
        return gameFrame;
    }

    public static FigureManager getFigureManager() {
        return figureManager;
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public GameField getGameField() {
        return gameField;
    }

    public JFrame getFrameWorker() {
        return frameWorker;
    }

    public String getTitle() {
        return title;
    }

    public void createFrame() {
        frameWorker.setTitle(title);
        frameWorker.setSize(WIDTH_GAME_FRAME + FIELD_ADD_TO_WIDTH, HEIGHT_GAME_FRAME + FIELD_ADD_TO_HEIGHT);
        frameWorker.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameWorker.addKeyListener(new BallsKeyController());
        gamePanel.setBackground(Color.white);
        frameWorker.getContentPane().add(gamePanel);
        frameWorker.setLocationRelativeTo(null);
        frameWorker.setResizable(false);
        frameWorker.setVisible(true);
        gameField.startGameProcess();
    }
}
