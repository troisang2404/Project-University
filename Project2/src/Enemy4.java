import javax.swing.*;
import java.awt.*;

public class Enemy4 extends GameObjects {
    private ImageIcon i;
    private int hp;

    public Enemy4(int locX, int locY) {
        super(locX, locY);
        loadImage("image/turret3.png");
        loadImage2("image/turret2.png");
        getImageDimension();
        hp = 4;

    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
