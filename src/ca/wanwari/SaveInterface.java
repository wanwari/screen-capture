package ca.wanwari;

/*
 * SaveInterface.java
 * Author: Wiesa Anwari
 * Capture the screenshot based on the rectangle provided
 * Then display the interface to allow the user to save, copy, and/or upload the image
 */

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
    private final int frameHeight = 85;

    SaveInterface(Rectangle captureRectangle) throws AWTException {

        CaptureManager captureManager = new CaptureManager();
        captureManager.captureScreenShot(captureRectangle);

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (info.getName().equals("Nimbus")) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame();
        JPanel panel = new JPanel();

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
            /*
             * Take the image in the form of a bufferedimage and pass it to the TransferableImage
             * If the data is transferable it will wrap the image in a Transferable that can be saved to the clipboard
             *
             * NOTE: If the user is using a X11 system the clipboard will not persist as X11 systems only copy a
             * reference to the data that is copied. Once the program is closed that reference is no longer available
             * and the user will have nothing to paste. This is default X11 behaviour
             */
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
            String message = "There was a problem trying to upload the image. Please try again.";
            String title = "Error";
            try {
                UploadManager uploadManager = new UploadManager();
                String uploadUrlResponse = uploadManager.upload(captureManager.convertToBase64());
                if (!uploadUrlResponse.equals("error")) {
                    StringSelection selection = new StringSelection(uploadUrlResponse);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(selection, selection);
                    message = "The image has been uploaded and the URL has been copied to your clipboard.";
                    title = "Uploaded";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(frame,
                    message,
                    title,
                    JOptionPane.INFORMATION_MESSAGE);
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
        Rectangle gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int screen_width = (int)gd.getWidth();
        int screen_height = (int)gd.getHeight();
        int x = (screen_width/2) - frameWidth/2;
        int y = (screen_height/2) - frameHeight/2;
        return new Point(x, y);
    }
}
