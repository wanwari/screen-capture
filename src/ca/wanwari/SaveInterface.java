package ca.wanwari;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;

public class SaveInterface {

    private final JFrame frame;
    private final int frameWidth = 550;
    private final int frameHeight = 80;
    private final CaptureManager captureManager;

    SaveInterface(Rectangle captureRectangle) throws AWTException {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
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
        saveToClipboardBtn.addActionListener(actionEvent -> {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(new TransferableImage(captureManager.getBufferedImage()), null);
            if (Main.operatingSystem.contains("Linux")) {
                JOptionPane.showMessageDialog(frame,
                        "If you are using X11, the clipboard will not persist after the program is terminated.\n" +
                           "Insure you paste prior to terminating the application. This is default X11 behaviour.",
                        "X11 Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton uploadBtn = new JButton("Upload");
        uploadBtn.setPreferredSize(new Dimension(150, 30));
        uploadBtn.addActionListener(actionEvent -> {
            UploadManager uploadManager = new UploadManager();
            try {
                String uploadUrlResponse = uploadManager.upload(captureManager.convertToBase64());
                if (!uploadUrlResponse.equals("error")) {
                    StringSelection selection = new StringSelection(uploadUrlResponse);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(selection, selection);
                    JOptionPane.showMessageDialog(frame,
                            "The image has been uploaded and the URL has been copied to your clipboard",
                            "Uploaded",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

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
