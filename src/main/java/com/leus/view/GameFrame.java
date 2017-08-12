package com.leus.view;

import com.leus.model.GameField;
import com.leus.model.factories.controllerFactories.ControllerAbstractFactory;
import com.leus.model.service.FigureManager;

import javax.swing.*;
import java.awt.*;

public class GameFrame {

    public static final int FIELD_WIDTH_IN_TILE = 6;
    public static final int FIELD_HEIGHT_IN_TILE = 12;
    public static final int WIDTH_GAME_FRAME = GameField.TILE_WIDTH * FIELD_WIDTH_IN_TILE;
    public static final int HEIGHT_GAME_FRAME = GameField.TILE_HEIGHT * FIELD_HEIGHT_IN_TILE;
    public static final int ADD_TO_FRAME_SIZE_IN_WIDTH = 15;
    public static final int FIELD_SIZE_FOR_SCORE = 35;
    public static final int ADD_TO_FRAME_SIZE_IN_HEIGHT = 64 + FIELD_SIZE_FOR_SCORE;

    private static GameFrame gameFrame;
    private static GameField gameField;
    private static final FigureManager figureManager = new FigureManager();

    private final JFrame canvas;
    private final String title;
    private JPanel gamePanel;

    private GameFrame(GameField gameField, JFrame canvas, String title) {
        this.gameField = gameField;
        this.canvas = canvas;
        this.title = title;
        this.gamePanel = gameField.getGamePanel();
    }

    public static GameFrame getGameFrame(GameField gameField, JFrame canvas, String title) {
        if (gameFrame == null) {
            gameFrame = new GameFrame(gameField, canvas, title);
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

    public JFrame getCanvas() {
        return canvas;
    }

    public String getTitle() {
        return title;
    }

    public void addKeyController(ControllerAbstractFactory factory) {
        canvas.addKeyListener(factory.createKeyController());
    }

    public void addMouseController(ControllerAbstractFactory factory) {
        canvas.addMouseListener(factory.createMouseController());
    }

    public void createFrame() {
        gamePanel.setBackground(Color.white);
        canvas.setTitle(title);
        canvas.setSize(WIDTH_GAME_FRAME + ADD_TO_FRAME_SIZE_IN_WIDTH, HEIGHT_GAME_FRAME + ADD_TO_FRAME_SIZE_IN_HEIGHT);
        canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        canvas.getContentPane().add(gamePanel);
        canvas.setLocationRelativeTo(null);
        canvas.setResizable(false);
        canvas.setVisible(true);
        gameField.startGameProcess();
    }
}
