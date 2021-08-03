import javax.swing.*;
import java.awt.*;

public class Map extends GameObjects {
    private Image image3, image4;

    public Map(int locX, int locY){
        super(locX, locY);
        loadImage("image/GamesMap.png");
        loadImage2("image/WithButton.png");
        loadImage3("image/VictoryScreen.png");
        loadImage4("image/HelpWithReturn.png");
    }
    public void loadImage3(String name) {
        ImageIcon i1 = new ImageIcon(name);
        image3 = i1.getImage();
    }

    public void loadImage4(String name) {
        ImageIcon i1 = new ImageIcon(name);
        image4 = i1.getImage();
    }

    public Image getImage3() {
        return image3;
    }

    public Image getImage4() {
        return image4;
    }
}
