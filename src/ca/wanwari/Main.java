package ca.wanwari;

import java.awt.*;
import static java.awt.GraphicsDevice.WindowTranslucency.*;

public class Main {

    public static void main(String[] args) {

        GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        boolean isUniformTranslucencySupported =
                gd.isWindowTranslucencySupported(TRANSLUCENT);
        boolean isPerPixelTranslucencySupported =
                gd.isWindowTranslucencySupported(PERPIXEL_TRANSLUCENT);
        boolean isShapedWindowSupported =
                gd.isWindowTranslucencySupported(PERPIXEL_TRANSPARENT);

        System.out.println("TRANSLUCENT " + isUniformTranslucencySupported);
        System.out.println("PERPIXEL_TRANSLUCENT " + isPerPixelTranslucencySupported);
        System.out.println("PERPIXEL_TRANSPARENT " + isShapedWindowSupported);

        if (!isUniformTranslucencySupported)
            System.exit(-1);

        //SaveInterface saveInterface = new SaveInterface();
        if (SystemTray.isSupported()) {
            //TODO: create system tray
        } else {
            CaptureInterface captureInterface = new CaptureInterface();
            captureInterface.showDisplay();
        }

    }
}
