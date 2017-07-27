package com.leus.run;


import com.leus.view.displays.GameFrame;

import javax.swing.*;

public class Runner {
    public static void main(String[] args) {
        new GameFrame(new JFrame(), "Balls").createFrame();
    }
}
