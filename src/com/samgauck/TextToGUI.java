package com.samgauck;

import com.samgauck.Exceptions.*;

import java.io.*;

/**
 * A Javadoc template. You're welcome. TODO: Update me.
 *
 * @author Samasaur
 */
public class TextToGUI {
    private static TextToGUI instance;

    public static TextToGUI getInstance() {
        return instance == null ? instance = new TextToGUI() : instance;
    }

    private static PrintStream originalPrintStream = System.out;
    private static InputStream originalInputStream = System.in;

    private TextToGUI() {
        originalPrintStream = System.out;
        originalInputStream = System.in;
    }

    public static void overrideStreams() {
        overrideStreams(OverrideOptions.Default, OverrideOptions.Default);
    }

    public static void overrideStreams(HasTextField inputLocation, HasTextField outputLocation) {
        overrideStreams(new GUIInputStream(inputLocation), new GUIPrintStream(outputLocation));
    }

    public static void overrideStreams(OverrideOptions inputLocation, OverrideOptions outputLocation) {

    }

    protected static void overrideStreams(InputStream inputLocation, PrintStream outputLocation) {
        System.setIn(inputLocation);
        System.setOut(outputLocation);
    }

    public static void overrideInputStream(HasTextField newLocation) {
        overrideInputStream(newLocation, OverrideOptions.Current);
    }

    public static void overrideInputStream(HasTextField newLocation, OverrideOptions outputLocation) {

    }

    public static void overrideOutputStream(HasTextField newLocation) {
        overrideOutputStream(newLocation, OverrideOptions.Current);
    }

    public static void overrideOutputStream(HasTextField newLocation, OverrideOptions inputLocation) {
        InputStream in;
        switch (inputLocation) {
            case Current:
                in = getInputStream();
            case Default:
                in =//TODO: Fix this. Don't have a default yet. Might actually set here.
            case Original:
        }
    }

    public static void resetStreams() {
        overrideStreams(OverrideOptions.Original, OverrideOptions.Original);
    }

    public static InputStream getInputStream() {
        return System.in;
    }

    public static HasTextField getInputTextField() throws TextToGUIException {
        if (getInputStream() instanceof GUIInputStream) {
            return ((GUIInputStream) getInputStream()).getHasTextField();
        }
        throw new NoHasTextFieldException();
    }

    public static PrintStream getPrintStream() {
        return System.out;
    }

    public static HasTextField getOutputTextField() throws TextToGUIException {
        if (getPrintStream() instanceof GUIPrintStream) {
            return ((GUIPrintStream) getPrintStream()).getHasTextField();
        }
        throw new NoHasTextFieldException();
    }

    public enum OverrideOptions {
        Current, Original, Default
    }
}