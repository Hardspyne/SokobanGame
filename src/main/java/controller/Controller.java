package controller;

import model.Direction;
import model.GameObjects;
import model.Model;
import view.View;

public class Controller implements EventListener {
    private View view;
    private Model model;

    public Controller() {
        this.view = new View(this);
        this.model = new Model();

        model.restart();
        view.init();

        model.setEventListener(this);
        view.setEventListener(this);
    }


    public static void main(String[] args) {
        Controller controller = new Controller();
    }

    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();

    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }

}
