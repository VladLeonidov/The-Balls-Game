package com.leus.run;

import com.leus.controllers.BallsKeyController;
import com.leus.model.GameField;
import com.leus.model.GameMenu;
import com.leus.model.ScoreMenu;
import com.leus.model.factories.figureFactories.HorizontalThreeBallFigureFactory;
import com.leus.model.factories.figureFactories.TwoBallFigureFactory;
import com.leus.model.factories.figureFactories.VerticalThreeBallFigureFactory;
import com.leus.model.factories.spriteFactories.RandBallFactory;
import com.leus.model.graphics.sprites.menuItems.buttons.AbstractButton;
import com.leus.model.graphics.sprites.menuItems.Logo;
import com.leus.model.graphics.sprites.menuItems.MenuCursor;
import com.leus.model.graphics.sprites.menuItems.buttons.ExitButton;
import com.leus.model.graphics.sprites.menuItems.buttons.ScoreButton;
import com.leus.model.graphics.sprites.menuItems.buttons.StartButton;
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
        AbstractButton startButton = new StartButton(ResourceLoader.loadImage(PathsToResources.START_BUTTON.getPath()), 60, 300, 150, 75, gameField, display);
        AbstractButton exitButton = new ExitButton(ResourceLoader.loadImage(PathsToResources.EXIT_BUTTON.getPath()), 60, 450, 150, 75, display);
        AbstractButton scoreButton = new ScoreButton(ResourceLoader.loadImage(PathsToResources.SCORE_BUTTON.getPath()), 60, 375, 150, 75, display.getCanvas());
        MenuCursor cursor = new MenuCursor(ResourceLoader.loadImage(PathsToResources.MENU_CURSOR.getPath()), 30, 325, 32, 32, 75);

        GameMenu mainMenu = new GameMenu(ResourceLoader.loadImage(PathsToResources.BACK_GROUND_MENU.getPath()), logo, cursor, startButton, scoreButton, exitButton);

        String[] records = new String[10];
        int i = 0;
        char k = 'A';
        for (; i < records.length; i++, k++) {
            records[i] = i + ". = " + k;
        }

        ScoreMenu scoreMenu = new ScoreMenu(ResourceLoader.loadImage(PathsToResources.BACK_GROUND_MENU.getPath()), records, 15, 15, 50);

        BallsKeyController keyController = new BallsKeyController(gameField, mainMenu, display);
        initializePcDisplay(display, gameField, mainMenu, scoreMenu, keyController);
        gameField.startGameProcess(display.getCanvas());
    }

    private static void initializePcDisplay(PcDisplay display, GameField gameField, GameMenu mainMenu, ScoreMenu scoreMenu, BallsKeyController keyController) {
        display.create(new GamePanel(gameField, mainMenu, scoreMenu),8, 16, "Balls");
        display.addKeyController(keyController);
    }
}
