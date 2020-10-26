package ca.wanwari;

import java.awt.*;
import static java.awt.GraphicsDevice.WindowTranslucency.*;

public class Main {

    public static void main(String[] args) {

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
            CaptureInterface captureInterface = new CaptureInterface();
            captureInterface.showDisplay();
        } else {
            //TODO: if SystemTray is supported create & launch an instance
        }

    }
}
