package model;

import java.awt.*;

public class Player extends CollisionObject implements Movable {
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.drawOval(getX(), getY(), getWidth(), getHeight());
        graphics.fillOval(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void move(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }
}
