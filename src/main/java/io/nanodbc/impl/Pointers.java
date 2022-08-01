package io.nanodbc.impl;

import org.bytedeco.javacpp.Pointer;

public class Pointers {

    public static void closeQuietly(Pointer pointer) {
        try {
            if (pointer != null) {
                pointer.close();
            }
        } catch (Exception e) {
            // swallow
        }
    }

    private Pointers() {
        // utility class
    }

}
