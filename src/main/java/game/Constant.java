package game;

public class Constant {
    // Screen settings
    public static final int WIDTH_SCREEN = 1280;
    public static final int HEIGHT_SCREEN = 720;

    // game settings
    public static final int MAX_LEVEL = 5;
    public static final long LEVEL_TRANSITION_DELAY = 3_000_000_000L; // in milliseconds

    // Collision settings
    // public static final int WALL_WIDTH = 10;
    // public static final int WALL_HEIGHT = 10;

    // paddle settings
    // public static int PADDLE_SPEED = 10;
    // public static int PADDLE_WIDTH = 100;
    // public static final int PADDLE_HEIGHT = 20;

    // // ball settings
    // public static int BALL_RADIUS = 10;
    // public static int INITIAL_BALL_SPEED = 5;
    // public static final int BRICK_ROWS = 10;
    // public static final int BRICK_COLUMNS = 20;
    public static final String BALL_PATH = "/game/images/ball.png";

    // level
    public static final String LEVEL_1 = "/game/map/level1.txt";
    public static final String LEVEL_2 = "/game/map/level2.txt";
    public static final String LEVEL_3 = "/game/map/level3.txt";
    public static final String LEVEL_4 = "/game/map/level4.txt";
    public static final String LEVEL_5 = "/game/map/level5.txt";
    public static final String TEST = "/game/map/test.txt";

    // element
    public static final String HEART = "game/images/element/heart.png";
    public static final String COIN = "game/images/element/coin.png";

    //power up
    public static final String DIAMOND = "game/images/items/diamond.png";
    public static final String EMARLD = "game/images/items/emarld.png";
    public static final String EXPPOTION = "game/images/items/expPotion.png";
    public static final String EYEOFDRAGON = "game/images/items/eyeOfDragon.png";
    public static final String GOLDEN_APPLE = "game/images/items/golden_apple.png";


    // brick settings
    public static final int BRICK_WIDTH = 50;
    public static final int BRICK_HEIGHT = 50;

    public static final String STONE_BRICK_IMAGE = "/game/images/bricks/stone_brick.png";
    public static final String IRON_BRICK_IMAGE = "/game/images/bricks/iron_brick.png";
    public static final String GOLD_BRICK_IMAGE = "/game/images/bricks/gold_brick.png";
    public static final String DIAMOND_BRICK_IMAGE = "/game/images/bricks/diamond_brick.png";
    public static final String NETHERITE_BRICK_IMAGE = "/game/images/bricks/netherite_brick.png";
    public static final String BED_ROCK_IMAGE = "/game/images/bricks/bedrock.png";

    public static final int STONE_DURABILITY = 10;
    public static final int IRON_DURABILITY = 20;
    public static final int GOLD_DURABILITY = 30;
    public static final int DIAMOND_DURABILITY = 40;
    public static final int NETHERITE_DURABILITY = 50;
    public static final int BEDROCK_DURABILITY = -1;
    // scene path
    public static final String GAME_OVER_SCENE_PATH = "/game/scenes/GameOverScene.fxml";
    public static final String GAME_SCENE_PATH = "/game/scenes/GameScene.fxml";
    public static final String HOME_SCENE_PATH = "/game/scenes/HomeScene.fxml";
    public static final String ICON_PATH = "/game/images/icon.png";

    // paddle image path
    public static final String PADDLE_IMAGE_PATH = "/game/images/nem_chua_green.png";

    // Destroy Stage image path
    public static final String DESTROY_STAGE_1 = "/game/images/destroyStage/destroy_stage_1.png";
    public static final String DESTROY_STAGE_2 = "/game/images/destroyStage/destroy_stage_2.png";
    public static final String DESTROY_STAGE_3 = "/game/images/destroyStage/destroy_stage_3.png";
    public static final String DESTROY_STAGE_4 = "/game/images/destroyStage/destroy_stage_4.png";
    public static final String DESTROY_STAGE_5 = "/game/images/destroyStage/destroy_stage_5.png";
    public static final String DESTROY_STAGE_6 = "/game/images/destroyStage/destroy_stage_6.png";
    public static final String DESTROY_STAGE_7 = "/game/images/destroyStage/destroy_stage_7.png";
    public static final String DESTROY_STAGE_8 = "/game/images/destroyStage/destroy_stage_8.png";
    public static final String DESTROY_STAGE_9 = "/game/images/destroyStage/destroy_stage_9.png";

    // game info
    public static final String GAME_NAME = "Raumania: rise of kingdoms";
    public static final int FIRST_DIFFICULTY = 1;

    // Home scene music
    public static final String HOME_BACKGROUND_MUSIC = "/game/sounds/Home_background_music.mp3";

}