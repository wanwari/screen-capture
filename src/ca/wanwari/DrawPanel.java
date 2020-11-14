package ca.wanwari;

import javax.swing.JPanel;
import java.awt.*;

public class DrawPanel extends JPanel {

    int clickedX;
    int clickedY;
    int currentX;
    int currentY;
    int rectangleX;
    int rectangleY;
    int rectangleWidth;
    int rectangleHeight;

    DrawPanel() {
        clickedX = -1;
        clickedY = -1;
        currentX = -1;
        currentY = -1;
        rectangleX = -1;
        rectangleY = -1;
        rectangleWidth = -1;
        rectangleHeight = -1;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);

        g2.setStroke(new BasicStroke(1.5f));
        g2.setFont(new Font(g.getFont().getFontName(), Font.BOLD + Font.ITALIC, 12));
        g2.setColor(new Color(0, 0, 0, 1f));
        g2.drawRect(rectangleX, rectangleY, rectangleWidth, rectangleHeight);
        g2.drawString(("Size: " + rectangleWidth + "x" + rectangleHeight), rectangleX, rectangleY-5);
    }

    void updateRectanglePoints() {
        if (currentX == -1 && currentY == -1) {
            rectangleX = clickedX;
            rectangleY = clickedY;
            rectangleWidth = 1;
            rectangleHeight = 1;
            repaint();
        } else if (currentX >= 0 && currentY >= 0) {
            if (currentX > clickedX) {
                rectangleX = clickedX;
                rectangleWidth = currentX - clickedX;
            } else {
                rectangleX = currentX;
                rectangleWidth = clickedX - currentX;
            }
            if (currentY > clickedY) {
                rectangleY = clickedY;
                rectangleHeight = currentY - clickedY;
            } else {
                rectangleY = currentY;
                rectangleHeight = clickedY - currentY;
            }
            repaint();
        }

    }

    void setClickedX(int clickedX) {
        this.clickedX = clickedX;
        updateRectanglePoints();
    }

    void setClickedY(int clickedY) {
        this.clickedY = clickedY;
        updateRectanglePoints();
    }

    void setDraggedX(int currentX) {
        this.currentX = currentX;
        updateRectanglePoints();
    }

    void setDraggedY(int currentY) {
        this.currentY = currentY;
        updateRectanglePoints();
    }

    Rectangle getRectangle() {
        if (rectangleX != -1 && rectangleY != -1 && rectangleWidth != -1 && rectangleHeight != -1)
            return new Rectangle(rectangleX, rectangleY, rectangleWidth, rectangleHeight);
        else
            return null;
    }

}
