package future.code.dark.dungeon.config;

public interface Configuration {

    String GAME_NAME="Dark Dungeon";
    String MAP_FILE_PATH = "src/main/resources/maps/map.ber";
    Boolean ENEMIES_ACTIVE = true;
    int GAME_FRAMES_PER_SECOND = 40;
    char WALL_CHARACTER = '1';
    char EXIT_CHARACTER = 'E';
    char LAND_CHARACTER = '0';
    char PLAYER_CHARACTER = 'P';
    char ENEMY_CHARACTER = 'G';
    char COIN_CHARACTER = 'C';
    Integer SPRITE_SIZE = 64;
    String PLAYER_SPRITE = "src/main/resources/assets/hero/tile000.png";
    String PLAYER_SPRITE_TWO = "src/main/resources/assets/hero/tile001.png";
    String PLAYER_SPRITE_THREE = "src/main/resources/assets/hero/tile002.png";
    String PLAYER_SPRITE_FOUR = "src/main/resources/assets/hero/tile003.png";

    String GHOST_SPRITE = "src/main/resources/assets/ghost/tile000.png";
    String GHOST_SPRITE_TWO = "src/main/resources/assets/ghost/tile001.png";
    String GHOST_SPRITE_THREE = "src/main/resources/assets/ghost/tile002.png";
    String GHOST_SPRITE_FOUR = "src/main/resources/assets/ghost/tile003.png";
    String WALL_SPRITE = "src/main/resources/assets/land/wall.png";
    String LAND_SPRITE = "src/main/resources/assets/land/ground.png";
    String EXIT_SPRITE = "src/main/resources/assets/land/out.png";
    String COIN_SPRITE = "src/main/resources/assets/land/collectible.png";
    String WIN_GAME = "src/main/resources/assets/victory.jpg";
    String GAME_OVER = "src/main/resources/assets/game_over_screen.jpeg";

}
