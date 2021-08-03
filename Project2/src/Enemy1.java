
import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Enemy1 extends GameObjects {
    private Timer t;
    private TimerTask tk;
    private ImageIcon i1;
    private int hp;

    public Enemy1(int locX, int locY) {
        super(locX, locY);
        loadImage("image/enemyt1a.png");
        loadImage2("image/enemy1a1.png");
        getImageDimension();
        hp = 2;

    }



//    public void move (){
//        if(this.isVisible()){
//            tk = new TimerTask() {
//                @Override
//                public void run() {
//                locY += 1;
//                }
//            };
//            if (locX < 10) {
//                locX = 10;
//            }
//            if (locX > 900) {
//                locX = 900;
//            }
//
//            if (locY < 10) {
//                locY = 10;
//            }
//            if (locY > 500) {
//                locY = 500;
//            }
//            t = new Timer();
//            t.schedule(tk,20);
//        }
//    }


    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}