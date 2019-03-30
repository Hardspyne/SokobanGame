package model;


import controller.EventListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Model {
    private EventListener eventListener;
    public final static int FIELD_CELL_SIZE = 20;
    private GameObjects gameObjects;
    private int currentLevel = 1;

    private LevelLoader levelLoader;
    //добавлено для того, чтобы можно было запускать как из IDE, так и с помощью jar файла
    {
        Path path = Paths.get("src\\main\\resources\\levels.txt");
        try {
            if (path.toFile().exists()) {
                levelLoader = new LevelLoader(Files.newInputStream(path));
            } else {
                levelLoader = new LevelLoader(this.getClass().getResourceAsStream("/levels.txt"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    public void move(Direction direction) {
        if (checkWallCollision(gameObjects.getPlayer(), direction)
                || checkBoxCollisionAndMoveIfAvaliable(direction)) {
            return;
        }
        switch (direction) {
            case UP:
                gameObjects.getPlayer().move(0, -FIELD_CELL_SIZE);
                break;
            case DOWN:
                gameObjects.getPlayer().move(0, FIELD_CELL_SIZE);
                break;
            case LEFT:
                gameObjects.getPlayer().move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                gameObjects.getPlayer().move(FIELD_CELL_SIZE, 0);
                break;
        }

        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        return getGameObjects().getWalls().stream().
                anyMatch(wall -> gameObject.isCollision(wall, direction));
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction) {
        for (Box box : getGameObjects().getBoxes()) {
            if (getGameObjects().getPlayer().isCollision(box, direction)) {

                if (checkWallCollision(box, direction)
                        || gameObjects.getBoxes().stream().
                        anyMatch(box1 -> box.isCollision(box1, direction))) {
                    return true;
                } else {
                    switch (direction) {
                        case UP:
                            box.move(0, -FIELD_CELL_SIZE);
                            break;
                        case DOWN:
                            box.move(0, FIELD_CELL_SIZE);
                            break;
                        case LEFT:
                            box.move(-FIELD_CELL_SIZE, 0);
                            break;
                        case RIGHT:
                            box.move(FIELD_CELL_SIZE, 0);
                            break;
                    }
                }
            }
        }
        return false;
    }

    public void checkCompletion() {
        Boolean[] isHomeWithBox = new Boolean[gameObjects.getHomes().size()];
        Arrays.fill(isHomeWithBox, false);

        int i = 0;
        for (Home home : gameObjects.getHomes()) {
            if (gameObjects.getBoxes().stream().anyMatch(box -> home.getX() == box.getX() && home.getY() == box.getY())) {
                isHomeWithBox[i] = true;
            }
            i++;
        }

        if (Arrays.stream(isHomeWithBox).allMatch(aBoolean -> aBoolean)) {
            eventListener.levelCompleted(currentLevel);
        }
    }

}
