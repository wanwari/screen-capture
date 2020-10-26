package ca.wanwari;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CaptureManager {

    private final Robot robot;
    private BufferedImage bufferedImage;
    private boolean capturedImage;

    CaptureManager() throws AWTException {
        robot = new Robot();
        bufferedImage = null;
    }

    void captureScreenShot(Rectangle areaToCapture) {
        bufferedImage = robot.createScreenCapture(areaToCapture);
        capturedImage = true;
    }

    void saveCaptureToFile(File file) throws IOException {
        if (bufferedImage != null && capturedImage) {
            ImageIO.write(bufferedImage, "png", file);
        }
    }
}
