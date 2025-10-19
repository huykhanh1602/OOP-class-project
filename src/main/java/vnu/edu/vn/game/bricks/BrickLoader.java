package vnu.edu.vn.game.bricks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BrickLoader {

    public static List<Bricks> loadBricks(String path) {
        List<Bricks> bricks = new ArrayList<Bricks>();

        try {
            InputStream is = BrickLoader.class.getResourceAsStream(path);
            if (is == null) {
                System.out.println("Couldn't find resource " + path);
                bricks.add(new Bricks(120, 90, 2, 100));
                bricks.add(new Bricks(180, 90, 1, 50));
                return bricks;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            int row = 0;
            double brickWidth = 60;
            double brickHeight = 30;

            while ((line = br.readLine()) != null) {
                String[] lineArray = line.trim().split("\\s+");
                for (int col = 0; col < lineArray.length; col++) {
                    int type = Integer.parseInt(lineArray[col]);
                    double x = col * brickWidth + 10;
                    double y = row * brickHeight + 40;
                    bricks.add(new Bricks(x, y, type, type * 50));
                }
                row++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Couldn't load bricks from " + path);
        }

        return bricks;
    }

}
