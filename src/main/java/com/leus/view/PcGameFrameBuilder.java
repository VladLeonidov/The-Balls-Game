package com.leus.view;

import com.leus.model.GameField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class PcGameFrameBuilder {

    public static final int ADD_TO_FRAME_SIZE_IN_WIDTH = 6;
    public static final int FIELD_SIZE_FOR_SCORE = 35;
    public static final int ADD_TO_FRAME_SIZE_IN_HEIGHT = 64 + FIELD_SIZE_FOR_SCORE;

    private static int fieldWidthInTile = 8;
    private static int fieldHeightInTile = 16;
    private static int widthGameFrame = GameField.TILE_WIDTH * fieldWidthInTile;
    private static int heightGameFrame = GameField.TILE_HEIGHT * fieldHeightInTile;

    private JFrame canvas = new JFrame();
    private String title = "Balls";
    private Component component = null;
    private int operation = WindowConstants.EXIT_ON_CLOSE;
    private boolean resizable = false;
    private boolean visible = true;

    public PcGameFrameBuilder() {
    }

    public PcGameFrameBuilder(JFrame canvas) {
        this.canvas = canvas;
    }

    public static int getFieldWidthInTile() {
        return fieldWidthInTile;
    }

    public static int getFieldHeightInTile() {
        return fieldHeightInTile;
    }

    public static int getWidthGameFrame() {
        return widthGameFrame;
    }

    public static int getHeightGameFrame() {
        return heightGameFrame;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSize(int widthGameFrame, int heightGameFrame) {
        this.widthGameFrame = widthGameFrame;
        this.heightGameFrame = heightGameFrame;
    }

    public void setDefaultCloseOperation(int operation) {
        this.operation = operation;
    }

    public void setLocationRelativeTo(Component component) {
        this.component = component;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void addPanel(JPanel panel) {
        canvas.getContentPane().add(panel);
    }

    public void addKeyController(KeyAdapter keyController) {
        canvas.addKeyListener(keyController);
    }

    public void addMouseController(MouseAdapter mouseController) {
        canvas.addMouseListener(mouseController);
    }

    public void buildPcGameFrame() {
        canvas.setTitle(title);
        canvas.setSize(widthGameFrame + ADD_TO_FRAME_SIZE_IN_WIDTH, heightGameFrame + ADD_TO_FRAME_SIZE_IN_HEIGHT);
        canvas.setDefaultCloseOperation(operation);
        canvas.setLocationRelativeTo(component);
        canvas.setResizable(resizable);
        canvas.setVisible(visible);
        GameField.getGameField(null, null, null).startGameProcess(canvas);
    }
}
