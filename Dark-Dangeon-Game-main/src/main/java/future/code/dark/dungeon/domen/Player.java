package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;
import future.code.dark.dungeon.service.GameMaster;

public class Player extends DynamicObject {
    private static final int stepSize = 1;
    public Player(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.PLAYER_SPRITE, Configuration.PLAYER_SPRITE_TWO, Configuration.PLAYER_SPRITE_THREE, Configuration.PLAYER_SPRITE_FOUR);
    }

    public void move(Direction direction) {
        super.move(direction, stepSize);
        if(Coin(getXPosition(), getYPosition())){
            if(GameMaster.getInstance().getCollectedCoins().containsKey(getXPosition() + getYPosition())) return;
            else {
                GameMaster.getInstance().setPlayerCoins();
                GameMaster.getInstance().Collections(getXPosition()+getYPosition());
            }
        }
        if(Enemy(getXPosition(), getYPosition())){
            GameMaster.getInstance().GameOver();
        }
    }

    @Override
    public String toString() {
        return "Player{[" + xPosition + ":" + yPosition + "]} " +
                "Remaining coins: " + (GameMaster.getInstance().getAllCoins() - GameMaster.getInstance().getPlayerCoins()) +
                " Player coins: " + GameMaster.getInstance().getPlayerCoins();
    }

    private Boolean Coin(int x, int y) {
        if(GameMaster.getInstance().getMap().getMap()[y][x] == Configuration.COIN_CHARACTER){
            for(GameObject o : GameMaster.getInstance().getCoinObj()){
                if(o instanceof Coin){
                    if(o.getXPosition() == xPosition && o.getYPosition() == yPosition){
                        ((Coin) o).setState(true);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Boolean Enemy(int x, int y) {
        if(GameMaster.getInstance().getMap().getMap()[y][x] == Configuration.ENEMY_CHARACTER){
            GameMaster.getInstance().GameOver();
            return true;
        }
        return false;
    }
}
