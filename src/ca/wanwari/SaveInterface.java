package ca.wanwari;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.IOException;

public class SaveInterface {

    private final JFrame frame;
    private final int frameWidth = 550;
    private final int frameHeight = 80;
    private final CaptureManager captureManager;

    SaveInterface(Rectangle captureRectangle) throws AWTException {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getName());
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame();
        JPanel panel = new JPanel();

        captureManager = new CaptureManager();
        captureManager.captureScreenShot(captureRectangle);

        frame.setSize(frameWidth, frameHeight);
        frame.setLocation(getCenterLocation());
        frame.setResizable(false);
        frame.setTitle("Save");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JButton saveToFileBtn = new JButton("Save to File");
        saveToFileBtn.setPreferredSize(new Dimension(150, 30));
        saveToFileBtn.addActionListener(actionEvent -> {

            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "PNG & JPG Images", "png", "jpg", "jpeg");

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(filter);
            fileChooser.showSaveDialog(fileChooser.getParent());

            try {
                captureManager.saveCaptureToFile(fileChooser.getSelectedFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        JButton saveToClipboardBtn = new JButton("Copy to Clipboard");
        saveToClipboardBtn.setPreferredSize(new Dimension(200, 30));

        JButton uploadBtn = new JButton("Upload to Imgur");
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
}
