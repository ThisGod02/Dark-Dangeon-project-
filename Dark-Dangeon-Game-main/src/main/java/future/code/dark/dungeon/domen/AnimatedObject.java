package future.code.dark.dungeon.domen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static future.code.dark.dungeon.config.Configuration.SPRITE_SIZE;

public abstract class AnimatedObject extends GameObject {
    private final ArrayList<String> images = new ArrayList<>();
    private int anim;
    Timer timer = new Timer();
    private String s;
    public AnimatedObject(int xPosition, int yPosition, String image1, String image2, String image3, String image4) {
        super(xPosition, yPosition);


        images.add(image1);
        images.add(image2);
        images.add(image3);
        images.add(image4);
    }

    @Override
    public void render(Graphics graphics) {
        timer.schedule(new TimerTask() {
            public void run() {
                anim++;
                if(anim >= images.size()) anim = 0;
                s = images.get(anim);
            }
        }, 0, 999999999);

        graphics.drawImage(new ImageIcon(s).getImage(), xPosition * SPRITE_SIZE, yPosition  * SPRITE_SIZE, null);
    }
}
