package model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {

    public Box(int x, int y) {
        super(x, y);
        setHeight(Model.FIELD_CELL_SIZE-2);
        setWidth(Model.FIELD_CELL_SIZE-2);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(200, 203, 124, 250));
        graphics.drawRect(getX()+1, getY()+1, getWidth(), getHeight());
        graphics.fillOval(  getX()+1, getY()+1, getWidth(), getHeight());
    }

    @Override
    public void move(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }
}
