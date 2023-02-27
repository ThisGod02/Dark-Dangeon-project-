package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;

public class Coin extends GameObject{

    private boolean state;
    public Coin(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.COIN_SPRITE);
        state = false;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
