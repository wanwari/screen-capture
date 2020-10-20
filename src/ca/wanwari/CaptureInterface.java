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
        System.out.println("Pressed at: " + mouseEvent.getPoint());
        drawPanel.setClickedX((mouseEvent.getX()));
        drawPanel.setClickedY((mouseEvent.getY()));
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        super.mouseReleased(mouseEvent);
        System.out.println("Released at: " + mouseEvent.getPoint());
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        super.mouseDragged(mouseEvent);
        System.out.println("Dragged to: " + mouseEvent.getPoint());
        drawPanel.setWidth(mouseEvent.getX());
        drawPanel.setHeight(mouseEvent.getY());

    }


}
