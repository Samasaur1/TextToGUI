package com.gauck.sam;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class GUI extends Application {
    private static GUI gui = null;
    public static GUI getInstance() {
        return gui;
    }

    public InputStream stdin;
    public PrintStream stdout;

    @Override
    public void start(Stage primaryStage) {
        GUI.gui = this;
        primaryStage.setTitle("GUI");
        TextField input = new TextField();
        TextArea output = new TextArea();
        output.setEditable(false);
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10));
        pane.setCenter(new VBox(5, output, input));
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();

        stdin = new InputStream() {
            private int offset = 0;
            @Override
            public int read() throws IOException {
                final byte[] arr = input.getText().getBytes();
                if (offset >= arr.length) {
                    return -1;
                }
                byte b = arr[offset++];
                if (b == ((byte) '\n')) {
                    offset = 0;
                    input.clear();
                }
                return b;
            }
        };

        stdout = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                int length = output.getText().getBytes().length + 1;
                byte[] arr = Arrays.copyOf(output.getText().getBytes(), length);
                arr[arr.length - 1] = (byte) b;
                output.setText(new String(arr));
                output.setScrollTop(Double.MAX_VALUE);
            }
        });

        // input.setOnKeyReleased(event -> {
        //     if (event.getCode() != KeyCode.ENTER) {
        //         return;
        //     }
        //     // output.setText(output.getText() + input.getText() + "\n");
        //     stdout.println(input.getText());
        //     input.clear();
        // });


    }
}
