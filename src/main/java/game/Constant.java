package game;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Constant {
    // Screen settings
    public static final int WIDTH_SCREEN = 1280;
    public static final int HEIGHT_SCREEN = 720;

    // game settings
    public static final int MAX_LEVEL = 10;
    public static final long LEVEL_TRANSITION_DELAY = 3_000_000_000L; // in milliseconds

    // paddle settings
    // public static int PADDLE_SPEED = 10;
    // public static int PADDLE_WIDTH = 100;
    // public static final int PADDLE_HEIGHT = 20;

    // // ball settings
    // public static int BALL_RADIUS = 10;
    // public static int INITIAL_BALL_SPEED = 5;

    public static final String SLIME_BALL = "/game/images/ball/slime_ball.png";
    public static final String EYEOFDRAGON_BALL = "/game/images/ball/eyeofdragon_ball.png";
    public static final String FIRE_BALL = "/game/images/ball/fire_ball.png";
    public static final String SNOW_BALL = "/game/images/ball/snow_ball.png";
    public static final String CLAY_BALL = "/game/images/ball/clay_ball.png";
    public static final String DIRETION = "/game/images/spear.png";

    // level
    public static final String LEVEL_1 = "/game/map/level1.txt";
    public static final String LEVEL_2 = "/game/map/level2.txt";
    public static final String LEVEL_3 = "/game/map/level3.txt";
    public static final String LEVEL_4 = "/game/map/level4.txt";
    public static final String LEVEL_5 = "/game/map/level5.txt";
    public static final String LEVEL_6 = "/game/map/level6.txt";
    public static final String LEVEL_7 = "/game/map/level7.txt";
    public static final String LEVEL_8 = "/game/map/level8.txt";
    public static final String LEVEL_9 = "/game/map/level9.txt";
    public static final String LEVEL_10 = "/game/map/level10.txt";

    public static final String TEST = "/game/map/test.txt";

    // element
    public static final String HEART = "game/images/element/heart.png";
    public static final String COIN = "game/images/element/coin.png";

    // power up
    public static final String DIAMOND = "game/images/items/diamond.gif";
    public static final String EMARLD = "game/images/items/emarld.gif";
    public static final String EXPPOTION = "game/images/items/expPotion.gif";
    public static final String EYEOFDRAGON = "game/images/items/eyeOfDragon.gif";
    public static final String GOLDEN_APPLE = "game/images/items/golden_apple.gif";
    // brick settings
    public static final int BRICK_WIDTH = 50;
    public static final int BRICK_HEIGHT = 50;
    public static final int BRICK_ROWS = 10;
    public static final int BRICK_COLUMNS = 20;

    public static final String STONE_BRICK_IMAGE = "/game/images/bricks/stone_brick.png";
    public static final String IRON_BRICK_IMAGE = "/game/images/bricks/iron_brick.png";
    public static final String GOLD_BRICK_IMAGE = "/game/images/bricks/gold_brick.png";
    public static final String DIAMOND_BRICK_IMAGE = "/game/images/bricks/diamond_brick.png";
    public static final String NETHERITE_BRICK_IMAGE = "/game/images/bricks/netherite_brick.png";
    public static final String BED_ROCK_IMAGE = "/game/images/bricks/bedrock.png";
    public static final String CHEST_IMAGE = "/game/images/bricks/chest.png";
    public static final String PORTAL_IMAGE = "/game/images/portal.gif";

    public static final int STONE_DURABILITY = 10;
    public static final int IRON_DURABILITY = 20;
    public static final int GOLD_DURABILITY = 30;
    public static final int DIAMOND_DURABILITY = 40;
    public static final int NETHERITE_DURABILITY = 50;
    public static final int BEDROCK_DURABILITY = -1;
    public static final int CHEST_DURABILITY = 1;
    // scene path
    public static final String GAME_OVER_SCENE_PATH = "/game/scenes/GameOverScene.fxml";
    public static final String GAME_SCENE_PATH = "/game/scenes/GameScene.fxml";
    public static final String HOME_SCENE_PATH = "/game/scenes/HomeScene.fxml";
    public static final String SETTING_SCENE_PATH = "/game/scenes/SettingScene.fxml";
    public static final String SKIN_BALL_SCENE = "/game/scenes/SkinBallScene.fxml";
    public static final String TRANSITION_SCENE = "/game/scenes/TransitionScene.fxml";
    public static final String ADS_SCENE = "/game/scenes/ADSScene.fxml";
    public static final String START_SCENE = "/game/scenes/StartScene.fxml";
    public static final String ICON_PATH = "/game/images/icon.png";

    // paddle image path
    public static final String PADDLE_IMAGE_PATH = "/game/images/nem_chua_green.png";

    // images path
    public static final String DESTROY_STAGE = "/game/images/destroyStage/destroy_stage_";

    // sound paths
    public static final String BALL_COLLIDE = "/game/sounds/sfx/ball/ball_collide_";
    public static final String BRICK_BREAK = "/game/sounds/sfx/brick/brick_break_";
    public static final String BASE_SOUND = "/game/sounds/sfx/";

    // game info
    public static final String GAME_NAME = "Raumania: rise of kingdoms";
    public static final int FIRST_DIFFICULTY = 1;

    // Home scene music
    public static final String HOME_BACKGROUND_MUSIC = "/game/sounds/music/Home_background_music.mp3";
    public static final String GAME_BACKGROUND_MUSIC = "/game/sounds/music/Game_background_music/";

    // score
    public static final Path SCORE_PATH = Paths.get(
            System.getProperty("user.dir"),
            "src", "main", "resources", "game", "score", "score.txt");

}