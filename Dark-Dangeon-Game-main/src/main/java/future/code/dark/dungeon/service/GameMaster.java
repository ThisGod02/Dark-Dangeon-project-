package future.code.dark.dungeon.service;

import future.code.dark.dungeon.config.Configuration;
import future.code.dark.dungeon.domen.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static future.code.dark.dungeon.config.Configuration.*;

public class GameMaster {

    private static GameMaster instance;

    private final Map map;
    private  List<GameObject> gameObjects;
    private int allCoins, playerCoins;
    private final java.util.Map<Integer, Integer> CollectedCoins = new HashMap<>();
    private int victoryOrLose;
    Image victory;
    {
        try {
            victory = ImageIO.read(new File(WIN_GAME));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    Image gameOver;

    {
        try {
            gameOver = ImageIO.read((new File(GAME_OVER)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized GameMaster getInstance() {
        if (instance == null) {
            instance = new GameMaster();
        }
        return instance;
    }

    private GameMaster() {
        try {
            this.map = new Map(Configuration.MAP_FILE_PATH);
            this.gameObjects = initGameObjects(map.getMap());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private List<GameObject> initGameObjects(char[][] map) {
        List<GameObject> gameObjects = new ArrayList<>();
        Consumer<GameObject> addGameObject = gameObjects::add;
        Consumer<Enemy> addEnemy = enemy -> {if (ENEMIES_ACTIVE) gameObjects.add(enemy);};

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case EXIT_CHARACTER -> addGameObject.accept(new Exit(j, i));
                    case COIN_CHARACTER -> {
                         addGameObject.accept(new Coin(j, i));
                         allCoins+=1;
                    }
                    case ENEMY_CHARACTER -> addEnemy.accept(new Enemy(j, i));
                    case PLAYER_CHARACTER -> addGameObject.accept(new Player(j, i));
                }
            }
        }
        return gameObjects;
    }

    public void renderFrame(Graphics graphics) {
        getMap().render(graphics);
        getExitObj().forEach(gameObject -> gameObject.render(graphics));
        getCoinObj().forEach(gameObject -> gameObject.render(graphics));
        getEnemies().forEach(gameObject -> gameObject.render(graphics));
        getPlayer().render(graphics);
        graphics.setColor(Color.WHITE);
        graphics.drawString(getPlayer().toString(), 10, 20);
        if(victoryOrLose == 1){
            graphics.drawImage(victory, 0, 0,1200, 800, null);
        }
        if(victoryOrLose == 2){
            graphics.drawImage(gameOver, 0, 0, 1200, 800, null);
        }
    }

    public Player getPlayer() {
        return (Player) gameObjects.stream()
                .filter(gameObject -> gameObject instanceof Player)
                .findFirst()
                .orElseThrow();
    }

    public List<GameObject> getExitObj() {
        return gameObjects.stream()
                .filter(gameObject -> (gameObject instanceof Exit))
                .collect(Collectors.toList());
    }

    public List<GameObject> getCoinObj() {
        return gameObjects.stream()
                .filter(gameObject -> (gameObject instanceof Coin))
                .filter(gameObject -> !((Coin) gameObject).isState())
                .collect(Collectors.toList());
    }

    public List<Enemy> getEnemies() {
        return gameObjects.stream()
                .filter(gameObject -> gameObject instanceof Enemy)
                .map(gameObject -> (Enemy) gameObject)
                .collect(Collectors.toList());
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }


    public Map getMap() {
        return map;
    }


    public int getAllCoins() {
        return allCoins;
    }

    public void Collections(int x) {
        this.CollectedCoins.putIfAbsent(x,null);
    }

    public java.util.Map<Integer, Integer> getCollectedCoins() {
        return CollectedCoins;
    }

    public int getPlayerCoins() {
        return playerCoins;
    }

    public void setPlayerCoins() {
        playerCoins+= 1;
    }

    public void Victory(){
        victoryOrLose++;
    }

    public void GameOver(){
        victoryOrLose = 2;
    }
}
