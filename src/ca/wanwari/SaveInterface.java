package ca.wanwari;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SaveInterface {

    JFrame frame;
    JPanel panel;
    JButton saveToFileBtn;
    JButton saveToClipboardBtn;
    JButton uploadBtn;
    int frameWidth;
    int frameHeight;

    SaveInterface() {
        frame = new JFrame();
        panel = new JPanel();

        frameWidth = 550;
        frameHeight = 80;
        frame.setSize(frameWidth, frameHeight);
        frame.setLocation(getCenterLocation());
        frame.setTitle("Save");

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        frame.setVisible(true);
    }

    Point getCenterLocation() {
        GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        int screen_width = gd[0].getDisplayMode().getWidth();
        int screen_height = gd[0].getDisplayMode().getHeight();
        int x = (screen_width/2) - frameWidth/2;
        int y = (screen_height/2) - frameHeight/2;
        return new Point(x, y);
    }
}
