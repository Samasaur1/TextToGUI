package com.gauck.sam;

import javafx.application.Application;
import javafx.application.Platform;

import java.io.*;

/**
 * A Javadoc template. You're welcome. TODO: Update me.
 */
public class TextToGUI {
    private static TextToGUI instance;

    public static TextToGUI getInstance() {
        return instance == null ? instance = new TextToGUI() : instance;
    }

    private InputStream stdin = System.in;
    private PrintStream stdout = System.out;

    private GUI gui;
    private Thread t;

    private TextToGUI() {
        stdin = System.in;
        stdout = System.out;
    }

    public void start() {
        // new Thread() {
        //     @Override
        //     public void run() {
        //         javafx.application.Application.launch(StartUpTest.class);
        //     }
        // }.start();
        // StartUpTest startUpTest = StartUpTest.waitForStartUpTest();
        // startUpTest.printSomething();
        t = new Thread(() -> Application.launch(GUI.class));
        t.start();
        GUI g = GUI.waitFor();
        gui = g;
        System.setOut(g.stdout);
        System.setIn(g.stdin);
    }

    public void stop() {
        Platform.exit();
        System.setOut(stdout);
        System.setIn(stdin);
    }
}