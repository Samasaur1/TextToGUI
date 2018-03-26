package com.samgauck;

import java.io.*;

/**
 * A Javadoc template. You're welcome. TODO: Update me.
 *
 * @author Samasaur
 */
public class GUIPrintStream extends PrintStream {
    private HasTextField textField;

    public GUIPrintStream(HasTextField outputLocation) {
        super(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                outputLocation.addText(Character.toString(((char) b)));
            }
        });
        this.textField = outputLocation;
    }

    public HasTextField getHasTextField() {
        return textField;
    }
}
