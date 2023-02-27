package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;
import future.code.dark.dungeon.service.GameMaster;

import java.util.Timer;
import java.util.TimerTask;

import static future.code.dark.dungeon.config.Configuration.LAND_CHARACTER;

public class Enemy extends DynamicObject{
    private int moveEnemyRandom = 0;
    Timer timer = new Timer();
    public Enemy(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.GHOST_SPRITE, Configuration.GHOST_SPRITE_TWO, Configuration.GHOST_SPRITE_THREE, Configuration.GHOST_SPRITE_FOUR);
        moveEnemy();
    }

    public void moveEnemy() {
        timer.schedule(new TimerTask() {
            public void run() {
                setMoveEnemyRandom();
                move();
            }
        }, 500, 1000);
    }

    public void move() {
        int tmpXPosition = getXPosition();
        int tmpYPosition = getYPosition();


        switch (moveEnemyRandom) {
            case 0 -> {
                if(GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] == Configuration.ENEMY_CHARACTER &&
                        GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] != Configuration.COIN_CHARACTER){
                    GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] = LAND_CHARACTER;
                }
                tmpYPosition -= 1;
                if (GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] != Configuration.WALL_CHARACTER  &&
                        GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] != Configuration.COIN_CHARACTER){
                    GameMaster.getInstance().getMap().setMapEnemy(tmpYPosition, tmpXPosition);
                }
                else {
                    GameMaster.getInstance().getMap().setMapEnemy(tmpYPosition += 1, tmpXPosition);
                }
            }
            case 1 -> {
                if(GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] == Configuration.ENEMY_CHARACTER &&
                GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] != Configuration.COIN_CHARACTER){
                    GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] = LAND_CHARACTER;
                }
                tmpYPosition += 1;
                if (GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] != Configuration.WALL_CHARACTER  &&
                        GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] != Configuration.COIN_CHARACTER){
                    GameMaster.getInstance().getMap().setMapEnemy(tmpYPosition, tmpXPosition);
                }
                else {
                    GameMaster.getInstance().getMap().setMapEnemy(tmpYPosition -= 1, tmpXPosition);
                }
            }
            case 2 -> {
                if(GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] == Configuration.ENEMY_CHARACTER &&
                        GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] != Configuration.COIN_CHARACTER&&
                        GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] != Configuration.EXIT_CHARACTER){
                    GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] = LAND_CHARACTER;
                }
                tmpXPosition -= 1;
                if (GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] != Configuration.WALL_CHARACTER  &&
                        GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] != Configuration.COIN_CHARACTER&&
                        GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] != Configuration.EXIT_CHARACTER){
                    GameMaster.getInstance().getMap().setMapEnemy(tmpYPosition, tmpXPosition);
                }
                else {
                    GameMaster.getInstance().getMap().setMapEnemy(tmpYPosition, tmpXPosition += 1);
                }
            }
            case 3 -> {
                if(GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] == Configuration.ENEMY_CHARACTER &&
                        GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] != Configuration.COIN_CHARACTER &&
                GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] != Configuration.EXIT_CHARACTER){
                    GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] = LAND_CHARACTER;
                }
                tmpXPosition += 1;
                if (GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] != Configuration.WALL_CHARACTER  &&
                        GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] != Configuration.COIN_CHARACTER&&
                        GameMaster.getInstance().getMap().getMap()[tmpYPosition][tmpXPosition] != Configuration.EXIT_CHARACTER){
                    GameMaster.getInstance().getMap().setMapEnemy(tmpYPosition, tmpXPosition);
                }
                else {
                    GameMaster.getInstance().getMap().setMapEnemy(tmpYPosition, tmpXPosition -= 1);
                }
            }

        }

        if (isAllowedSurface(tmpXPosition, tmpYPosition) && Exit(tmpXPosition, tmpYPosition)) {
            xPosition = tmpXPosition;
            yPosition = tmpYPosition;
        }
    }

    public void setMoveEnemyRandom() {
        this.moveEnemyRandom = (int) (Math.random()*4);
    }
}
