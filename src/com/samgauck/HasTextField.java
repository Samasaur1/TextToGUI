package com.samgauck;

/**
 * A Javadoc template. You're welcome. TODO: Update me.
 *
 * @author Samasaur
 */
public interface HasTextField {
    public String getText();
    public void setText(String newText);
    default public void addText(String newText) {
        setText(getText() + newText);
    }
}
