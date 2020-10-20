package ca.wanwari;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CaptureInterface extends MouseAdapter {

    JFrame displayWindow;
    DrawPanel drawPanel;
    final int screen_width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    final int screen_height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    CaptureInterface() {
        displayWindow = new JFrame();
        displayWindow.setUndecorated(true);
        displayWindow.setLocation(new Point(0, 0));
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
        Rectangle captureRectangle = drawPanel.getRectangle();

        drawPanel.setVisible(false);
        drawPanel.setEnabled(false);
        displayWindow.remove(drawPanel);

        displayWindow.setVisible(false);
        displayWindow.setEnabled(false);
        displayWindow.dispose();

        System.out.println(captureRectangle);

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        super.mouseDragged(mouseEvent);
        drawPanel.setDraggedX(mouseEvent.getX());
        drawPanel.setDraggedY(mouseEvent.getY());
    }

}
