package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static model.Model.FIELD_CELL_SIZE;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        HashSet<Wall> walls = new HashSet<>();
        HashSet<Home> homes = new HashSet<>();
        HashSet<Box> boxes = new HashSet<>();
        Player player = null;

        int x0 = FIELD_CELL_SIZE / 2;
        int y0 = FIELD_CELL_SIZE / 2;

        ArrayList<String> levelLines = getLevelLines(level);

        for (int i = 0; i < levelLines.size(); i++) {
            String line = levelLines.get(i);
            for (int j = 0; j < line.length(); j++) {
                switch (line.charAt(j)) {
                    case ('X'):
                        walls.add(new Wall(x0 + j * FIELD_CELL_SIZE, y0 + i * FIELD_CELL_SIZE));
                        break;
                    case ('*'):
                        boxes.add(new Box(x0 + j * FIELD_CELL_SIZE, y0 + i * FIELD_CELL_SIZE));
                        break;
                    case ('.'):
                        homes.add(new Home(x0 + j * FIELD_CELL_SIZE, y0 + i * FIELD_CELL_SIZE));
                        break;
                    case ('&'):
                        homes.add(new Home(x0 + j * FIELD_CELL_SIZE, y0 + i * FIELD_CELL_SIZE));
                        boxes.add(new Box(x0 + j * FIELD_CELL_SIZE, y0 + i * FIELD_CELL_SIZE));
                        break;
                    case ('@'):
                        player = new Player(x0 + j * FIELD_CELL_SIZE, y0 + i * FIELD_CELL_SIZE);
                        break;
                }
            }
        }

        return new GameObjects(walls, boxes, homes, player);
    }

    public ArrayList<String> getLevelLines(int level) {
        List<String> listLine = new ArrayList<>();
        try {
            listLine = Files.readAllLines(levels);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> levelLine = new ArrayList<>();

        level = level % 60 == 0 ? 60 : level % 60;
        int firstIndex = listLine.indexOf("Maze: " + level) + 7;

        for (int i = firstIndex; !listLine.get(i).isEmpty(); i++) {
            levelLine.add(listLine.get(i));
        }

        return levelLine;
    }
}
