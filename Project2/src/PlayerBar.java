import javax.swing.*;
import java.awt.*;

public class PlayerBar extends GameObjects {
    private Image image3;

    public PlayerBar(int locX, int locY) {
        super(locX, locY);
        loadImage("image/itemCannon2.png");
        loadImage2("image/itemMachine.png");
        loadImage3("image/itemBom.png");
    }

    public void loadImage3(String name) {
        ImageIcon i1 = new ImageIcon(name);
        image3 = i1.getImage();
    }

    public Image getImage3() {
        return image3;
    }
}
