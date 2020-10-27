package ca.wanwari;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

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

    BufferedImage getBufferedImage() {
        return this.bufferedImage;
    }

    String convertToBase64() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write( bufferedImage, "png", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();

        return Base64.getEncoder().encodeToString(imageInByte);
    }
}
