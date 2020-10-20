package ca.wanwari;

import javax.swing.JPanel;
import java.awt.*;

public class DrawPanel extends JPanel {

    int clickedX;
    int clickedY;
    int width;
    int height;

    DrawPanel() {
        clickedX = 0;
        clickedY = 0;
        width = 0;
        height = 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(clickedX, clickedY, width, height);

    }

    void setClickedX(int x) {
        this.clickedX = x;
        repaint();
    }

    int getClickedX() {
        return clickedX;
    }

    int getClickedY() {
        return clickedY;
    }

    void setClickedY(int y) {
        this.clickedY = y;
        repaint();
    }

    void setWidth(int width) {
        if (width > clickedY)
            this.width = width - clickedX;
        repaint();
    }

    void setHeight(int height) {
        if (height > clickedY)
            this.height = height - clickedY;
        repaint();
    }
}
