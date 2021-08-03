import javax.swing.*;
import java.awt.*;

public class Enemy3 extends GameObjects {
    private int hp;

    public Enemy3(int locX, int locY) {
        super(locX, locY);
        loadImage("image/enemy3.png");
        loadImage2("image/enemy3a.png");
        getImageDimension();
        hp = 1;

    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
