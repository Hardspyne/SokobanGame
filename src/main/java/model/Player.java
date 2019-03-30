package model;

import java.awt.*;

public class Player extends CollisionObject implements Movable {
    public Player(int x, int y) {
        super(x, y);
        setHeight(Model.FIELD_CELL_SIZE-2);
        setWidth(Model.FIELD_CELL_SIZE-2);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(0, 122, 10, 255));
        graphics.drawOval(getX()+1, getY()+1, getWidth(), getHeight());
        graphics.fillOval(getX()+1, getY()+1, getWidth(), getHeight());
    }

    @Override
    public void move(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }
}
