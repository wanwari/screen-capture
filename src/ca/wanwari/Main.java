package ca.wanwari;

/*
 * Main.java
 * Author: Wiesa Anwari
 * A screen capturing program that allows the user to screenshot a user defined area
 * and then save it to file, copy it to the clipboard, and/or upload it online
 */

import java.awt.*;
import static java.awt.GraphicsDevice.WindowTranslucency.*;

public class Main {

    public static String operatingSystem = System.getProperty("os.name");

    public static void main(String[] args) {

        //Check if the current environment supports translucency, if it is not quit the program
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        boolean isUniformTranslucencySupported = gd.isWindowTranslucencySupported(TRANSLUCENT);

        if (!isUniformTranslucencySupported) {
            System.out.println("ERROR: Translucency is not supported. Quitting.");
            System.exit(-1);
        } else {
            java.awt.EventQueue.invokeLater(() -> {
                CaptureInterface captureInterface = new CaptureInterface();
                captureInterface.showDisplay();
            });
        }
    }
}
