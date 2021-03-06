package view;


import controller.EventListener;
import model.Direction;
import model.Home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 500, 600);

        view.getGameObjects().getHomes().forEach(home -> home.draw(g));//нужно чтобы дома были обязательно под ящиками
        view.getGameObjects().getAll().forEach(gameObject -> {
            if (!(gameObject instanceof Home)) gameObject.draw(g);
        });

        g.setColor(Color.WHITE);
        g.drawString("Цель игры - переместить все ящики на заданные места", 20, 500);
        g.drawString("Управление:", 20, 520);
        g.drawString("Перемещение ящиков осуществляется с помощью стрелок", 20, 535);
        g.drawString("R - Начать заново", 20, 550);


    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    eventListener.move(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    eventListener.move(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    eventListener.move(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    eventListener.move(Direction.DOWN);
                    break;
                case KeyEvent.VK_R:
                    eventListener.restart();
                    break;
            }
        }
    }
}
