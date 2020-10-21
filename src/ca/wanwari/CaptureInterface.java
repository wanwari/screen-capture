package ca.wanwari;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class CaptureInterface extends MouseAdapter {

    JFrame displayWindow;
    DrawPanel drawPanel;
    int minX = (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getMinX();
    int minY = (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getMinY();
    final int screen_width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    final int screen_height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    CaptureInterface() {
        displayWindow = new JFrame();
        displayWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        displayWindow.setUndecorated(true);
        displayWindow.setLocation(new Point(minX, minY));
        System.out.println(minX + " " + minY);
        displayWindow.setSize(new Dimension(screen_width, screen_height));
        displayWindow.setOpacity(0.3f);
        displayWindow.addMouseListener(this);
        displayWindow.addMouseMotionListener(this);
        drawPanel = new DrawPanel();
        displayWindow.add(drawPanel);
    }

    void showDisplay() {
        displayWindow.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        super.mousePressed(mouseEvent);
        drawPanel.setClickedX((mouseEvent.getX()));
        drawPanel.setClickedY((mouseEvent.getY()));
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        super.mouseReleased(mouseEvent);


        drawPanel.setVisible(false);
        drawPanel.setEnabled(false);
        displayWindow.remove(drawPanel);

        displayWindow.setVisible(false);
        displayWindow.setEnabled(false);
        displayWindow.dispose();
        Rectangle captureRectangle = drawPanel.getRectangle();
        captureRectangle.setLocation(drawPanel.getRectangle().x + minX, drawPanel.getRectangle().y + minY);
        System.out.println("CaptureInterface: " + captureRectangle);

        try {
            CaptureManager captureManager = new CaptureManager();
            captureManager.captureScreenShot(captureRectangle);
            captureManager.saveCaptureToFile("/home/wanwari/img.png");
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        super.mouseDragged(mouseEvent);
        drawPanel.setDraggedX(mouseEvent.getX());
        drawPanel.setDraggedY(mouseEvent.getY());
    }

}
