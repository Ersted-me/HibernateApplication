package com.ersted;

import com.ersted.view.RunnerView;

import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        RunnerView view = new RunnerView();
        view.showMenu();
    }
}
