package com.samgauck;

import java.io.IOException;
import java.io.InputStream;

/**
 * A Javadoc template. You're welcome. TODO: Update me.
 *
 * @author Samasaur
 */
public class GUIInputStream extends InputStream {
    private HasTextField textField;
    private int offset;

    public GUIInputStream(HasTextField inputLocation) {
        this.textField = inputLocation;
        this.offset = 0;
    }

    @Override
    public int read() throws IOException {
        return textField.getText().charAt(offset);
    }

    public HasTextField getHasTextField() {
        return textField;
    }
}
