package com.leus.model.service;

import com.leus.model.menus.AbstractMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuManager {
    private final List<AbstractMenu> menus = new ArrayList<>();
    private AbstractMenu focus;
    private int index = 0;

    public AbstractMenu getFocus() {
        return focus;
    }

    public void addMenu(AbstractMenu menu) {
        menus.add(menu);
    }

    public void removeMenu(AbstractMenu menu) {
        menus.remove(menu);
    }


}
