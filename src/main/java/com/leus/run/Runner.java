package com.leus.run;

import com.leus.controllers.BallsKeyController;
import com.leus.model.GameField;
import com.leus.model.GameMenu;
import com.leus.model.factories.figureFactories.HorizontalThreeBallFigureFactory;
import com.leus.model.factories.figureFactories.TwoBallFigureFactory;
import com.leus.model.factories.figureFactories.VerticalThreeBallFigureFactory;
import com.leus.model.factories.spriteFactories.RandBallFactory;
import com.leus.model.graphics.sprites.menuItem.Button;
import com.leus.model.graphics.sprites.menuItem.Logo;
import com.leus.model.graphics.sprites.menuItem.MenuCursor;
import com.leus.model.service.FieldManager;
import com.leus.paths.PathsToResources;
import com.leus.utils.ResourceLoader;
import com.leus.view.displays.PcDisplay;
import com.leus.view.panels.GamePanel;

public class Runner {
    public static void main(String[] args) {
        int figureStartPositionX = PcDisplay.getWidthWindow() / 2 - GameField.TILE_WIDTH;
        int figureStartPositionY = 0;

        PcDisplay display = PcDisplay.getDisplay();

        GameField gameField = new GameField(new FieldManager(),
                new TwoBallFigureFactory(figureStartPositionX, figureStartPositionY, new RandBallFactory()),
                new VerticalThreeBallFigureFactory(figureStartPositionX, figureStartPositionY, new RandBallFactory()),
                new HorizontalThreeBallFigureFactory(figureStartPositionX, figureStartPositionY, new RandBallFactory()));

        Logo logo = new Logo(ResourceLoader.loadImage(PathsToResources.LOGO.getPath()), -60, 100, 400, 300);
        MenuCursor cursor = new MenuCursor(ResourceLoader.loadImage(PathsToResources.MENU_CURSOR.getPath()), 30, 325, 32, 32);
        Button startButton = new Button(ResourceLoader.loadImage(PathsToResources.START_BUTTON.getPath()), 60, 300, 150, 75);
        Button exitButton = new Button(ResourceLoader.loadImage(PathsToResources.EXIT_BUTTON.getPath()), 60, 375, 150, 75);

        GameMenu menu = new GameMenu(ResourceLoader.loadImage(PathsToResources.BACK_GROUND_MENU.getPath()), logo, cursor, startButton, exitButton);

        BallsKeyController keyController = new BallsKeyController(gameField, display);
        initializePcDisplay(display, gameField, menu, keyController);
        gameField.startGameProcess(display.getCanvas());
    }

    private static void initializePcDisplay(PcDisplay display, GameField gameField, GameMenu menu, BallsKeyController keyController) {
        display.create(new GamePanel(gameField, menu),8, 16, "Balls");
        display.addKeyController(keyController);
    }
}
