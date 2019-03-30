package model;

import java.awt.*;

public class Home extends GameObject {
    public Home(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(177, 94, 79, 249));
        graphics.drawLine(getX(), getY(), getX() + getWidth(), getY() + getHeight());
        graphics.drawLine(getX(), getY()+getHeight(), getX() + getWidth(), getY());
    }
}
