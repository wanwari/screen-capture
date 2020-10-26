package ca.wanwari;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveInterface {

    JFrame frame;
    JPanel panel;
    JButton saveToFileBtn;
    JButton saveToClipboardBtn;
    JButton uploadBtn;
    int frameWidth;
    int frameHeight;
    Rectangle captureRectangle;
    CaptureManager captureManager;

    SaveInterface(Rectangle captureRectangle) throws AWTException {
        frame = new JFrame();
        panel = new JPanel();
        captureManager = new CaptureManager();
        captureManager.captureScreenShot(captureRectangle);
        frameWidth = 550;
        frameHeight = 80;
        frame.setSize(frameWidth, frameHeight);
        frame.setLocation(getCenterLocation());
        frame.setResizable(false);
        frame.setTitle("Save");

        saveToFileBtn = new JButton("Save to File");
        saveToFileBtn.setPreferredSize(new Dimension(150, 30));

        saveToClipboardBtn = new JButton("Copy to Clipboard");
        saveToClipboardBtn.setPreferredSize(new Dimension(200, 30));

        uploadBtn = new JButton("Upload to Imgur");
        uploadBtn.setPreferredSize(new Dimension(150, 30));

        panel.add(saveToFileBtn);
        panel.add(saveToClipboardBtn);
        panel.add(uploadBtn);
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        frame.add(panel);
    }

    void show() {
        frame.setVisible(true);
    }

    Point getCenterLocation() {
        int screen_width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int screen_height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int x = (screen_width/2) - frameWidth/2;
        int y = (screen_height/2) - frameHeight/2;
        return new Point(x, y);
    }

    void setCaptureRectangle(Rectangle captureRectangle) {
        this.captureRectangle = captureRectangle;
    }
}
