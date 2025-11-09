package game.bricks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game.Constant;
import game.GameContext;
import game.abstraction.Bricks;

public class BrickLoader {
    public static List<Bricks> loadBricks() {
        List<Bricks> bricks = new ArrayList<Bricks>();
        String path = "/game/map/level" + GameContext.getInstance().getCurrentLevel() + ".txt";
        // path = "/game/map/test.txt";
        BufferedReader reader = null;
        String line;

        try {
            InputStream is = BrickLoader.class.getResourceAsStream(path);
            if (is == null) {
                throw new IOException("File not found: " + path);
            }

            reader = new BufferedReader(new InputStreamReader(is));

            for (int i = 0; i < Constant.BRICK_ROWS; i++) {
                line = reader.readLine();
                if (line.trim().isEmpty())
                    continue;
                String[] values = line.split("\\s+");
                for (int j = 0; j < Constant.BRICK_COLUMNS; j++) {
                    String type = values[j];
                    Bricks brick = createBricks(type, j * Constant.BRICK_WIDTH, i * Constant.BRICK_HEIGHT);
                    if (brick != null) {
                        bricks.add(brick);
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Error loading brick layout: " + e.getMessage());
            e.printStackTrace();

            if (bricks.isEmpty()) {
                bricks.add(new StoneBrick(100, 100));
                bricks.add(new IronBrick(150, 100));
                bricks.add(new GoldBrick(200, 100));
                bricks.add(new DiamondBrick(250, 100));
                bricks.add(new NetheriteBrick(300, 100));
                bricks.add(new Bedrock(350, 100));
            }
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing reader: " + e.getMessage());
            }
        }

        return bricks;
    }

    public static Bricks createBricks(String type, double x, double y) {
        switch (type) {
            case "s":
                return new StoneBrick(x, y);
            case "i":
                return new IronBrick(x, y);
            case "g":
                return new GoldBrick(x, y);
            case "d":
                return new DiamondBrick(x, y);
            case "n":
                return new NetheriteBrick(x, y);
            case "b":
                return new Bedrock(x, y);
            case "c":
                return new Chest(x, y);
            default:
                return null;
        }
    }
}
