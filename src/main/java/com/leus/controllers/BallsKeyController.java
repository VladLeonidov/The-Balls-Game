package com.leus.controllers;

import com.leus.model.GameField;
import com.leus.model.GameMenu;
import com.leus.model.graphics.figures.AbstractFigure;
import com.leus.view.displays.PcDisplay;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BallsKeyController extends KeyAdapter {

    private GameField gameField;
    private GameMenu menu;
    private PcDisplay window;
    private AbstractFigure figure;
    private int indexFocusButton;

    public BallsKeyController(GameField gameField, GameMenu menu, PcDisplay window) {
        this.gameField = gameField;
        this.menu = menu;
        this.window = window;
    }

    public GameMenu getMenu() {
        return menu;
    }

    public GameField getGameField() {
        return gameField;
    }

    public PcDisplay getWindow() {
        return window;
    }

    public void keyPressed(KeyEvent e) {
        figure = gameField.getFigure();

        if (menu.isFocusMenu()) {
            keyPressedInMenu(e);
        } else {
            if (!gameField.isGameOver()) {
                keyPressedInGameProcess(e);
            }
        }

        window.getCanvas().repaint();
    }

    private void keyPressedInMenu(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (indexFocusButton < menu.getMenuButtons().length - 1) {
                menu.getCursor().moveDown();
                menu.getCursor().setFocus(menu.getMenuButtons()[++indexFocusButton]);
            }

        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (indexFocusButton > 0) {
                menu.getCursor().moveUp();
                menu.getCursor().setFocus(menu.getMenuButtons()[--indexFocusButton]);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            menu.getCursor().enter();
        }
    }

    private void keyPressedInGameProcess(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keyDown();
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keyUp();
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keyLeft();
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keyRight();
        }
    }

    private void keyUp() {
        if (!figure.isFrozen()) {
            figure.rotate();
        }
    }

    private void keyLeft() {
        if (!figure.isFrozen()) {
            figure.moveLeft();
        }
    }

    private void keyRight() {
        if (!figure.isFrozen()) {
            figure.moveRight();
        }
    }

    private void keyDown() {
        if (!figure.isFrozen()) {
            figure.fastMoveDown();
        }
    }
}
