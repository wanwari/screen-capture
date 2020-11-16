package ca.wanwari;

/*
 * Main.java
 * Author: Wiesa Anwari
 * A screen capturing program that allows the user to screenshot a user defined area
 * and then save it to file, copy it to the clipboard, and/or upload it online
 */

import javax.swing.*;
import java.awt.*;
import static java.awt.GraphicsDevice.WindowTranslucency.*;

public class Main {

    public static String operatingSystem = System.getProperty("os.name");

    public static void main(String[] args) {

        //Check if the current environment supports translucency, if it is not quit the program
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        boolean isUniformTranslucencySupported = gd.isWindowTranslucencySupported(TRANSLUCENT);

        System.out.println("TRANSLUCENT " + isUniformTranslucencySupported);

        if (!isUniformTranslucencySupported) {
            System.out.println("ERROR: Translucency is not supported");
            System.exit(-1);
        }

        if (!SystemTray.isSupported()) {
            System.out.println("INFO: SystemTray is not supported");
            System.out.println("Skipping SystemTray and launching CaptureInterface directly");
            java.awt.EventQueue.invokeLater(() -> {
                CaptureInterface captureInterface = new CaptureInterface();
                captureInterface.showDisplay();
            });
        } else {
            //TODO: if SystemTray is supported create & launch an instance
        }

    }
}
