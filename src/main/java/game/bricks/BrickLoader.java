package game.bricks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import game.Constant;

public class BrickLoader {
    private static final int colS = 20;
    private static final int rowS = 8;

    public static List<Bricks> loadBricks(String path) {
        List<Bricks> bricks = new ArrayList<Bricks>();

        try {
            for (int row = 1; row <= rowS; row++) {
                for (int col = 1; col <= colS; col++) {

                    double x = col * Constant.BRICK_WIDTH;
                    double y = row * Constant.BRICK_HEIGHT;

                    bricks.add(new Bricks(x, y, 0, 0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading brick layout from file: " + e.getMessage());

            if (bricks.isEmpty()) {
                bricks.add(new Bricks(120, 90, 2, 100));
                bricks.add(new Bricks(180, 90, 1, 50));
            }
        }

        return bricks;
    }

}
