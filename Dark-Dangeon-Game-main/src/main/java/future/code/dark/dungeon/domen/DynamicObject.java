package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;
import future.code.dark.dungeon.service.GameMaster;

public abstract class DynamicObject extends AnimatedObject {
    public DynamicObject(int xPosition, int yPosition, String image1, String image2, String image3, String image4) {
        super(xPosition, yPosition, image1, image2, image3, image4);
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    protected void move(Direction direction, int distance) {
        int tmpXPosition = getXPosition();
        int tmpYPosition = getYPosition();

        switch (direction) {
            case UP -> tmpYPosition -= distance;
            case DOWN -> tmpYPosition += distance;
            case LEFT -> tmpXPosition -= distance;
            case RIGHT -> tmpXPosition += distance;
        }

        if (isAllowedSurface(tmpXPosition, tmpYPosition) && Exit(tmpXPosition, tmpYPosition)) {
            xPosition = tmpXPosition;
            yPosition = tmpYPosition;
        }

    }

    public Boolean isAllowedSurface(int x, int y) {
        return GameMaster.getInstance().getMap().getMap()[y][x] != Configuration.WALL_CHARACTER ;
    }

    public Boolean Exit(int x, int y) {
        if(GameMaster.getInstance().getMap().getMap()[y][x] != Configuration.ENEMY_CHARACTER){
            if(GameMaster.getInstance().getMap().getMap()[y][x] == Configuration.EXIT_CHARACTER){
                if(GameMaster.getInstance().getPlayerCoins() == GameMaster.getInstance().getAllCoins()){
                    GameMaster.getInstance().Victory();
                    return true;
                }
            }
        }
        return GameMaster.getInstance().getMap().getMap()[y][x] != Configuration.EXIT_CHARACTER;
    }

}
