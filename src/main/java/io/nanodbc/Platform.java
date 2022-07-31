package io.nanodbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.bytedeco.javacpp.Loader;

public class Platform {

    private static final String PLATFORM_RESOURCE_NAME = "NANODBC_PLATFORM.txt";

    public static void checkPlatform() {
        String systemPlatform = Loader.getPlatform();
        String libraryPlatform = getLibraryPlatform();
        if (libraryPlatform == null) {
            System.err.println("Warning: nanodbc library platform could not be read");
        } else if (!systemPlatform.equals(libraryPlatform)) {
            System.err.println("!!!!!!!!!! WARNING: nanodbc system platform does not match library platform!");
            System.err.println("!!!!!!!!!! System platform:  " + systemPlatform);
            System.err.println("!!!!!!!!!! Library platform: " + libraryPlatform);
        }
    }

    public static String getLibraryPlatform() {
        try (InputStream stream = Nanodbc.class.getClassLoader().getResourceAsStream(PLATFORM_RESOURCE_NAME)) {
            if (stream == null) {
                System.err.println("nanodbc platform file not found");
                return null;
            }
            try (Scanner scanner = new Scanner(stream, "UTF-8").useDelimiter("\\A")) {
                return scanner.hasNext() ? scanner.next() : null;
            }
        } catch (IOException e) {
            System.err.println("Error reading nanodbc platform");
            System.err.println(e);
            return null;
        }
    }

    private Platform() {
        // utility class
    }

}
