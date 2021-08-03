import javax.swing.*;
import java.awt.*;

public class Item {
    private Image image1, image2, image3 , image4;
    private boolean check1, check2, check3, check4;

    public Item() {
        loadImage1("image/itemAP.png");
        loadImage2("image/itemBullet.png");
        loadImage3("image/itemBom.png");
        loadImage4("image/itemRepair.png");
        check1 = true;
        check2 = true;
        check3 = true;
        check4 = true;
    }
    public void loadImage3(String name) {
        ImageIcon i1 = new ImageIcon(name);
        image3 = i1.getImage();
    }

    public void loadImage4(String name) {
        ImageIcon i1 = new ImageIcon(name);
        image4 = i1.getImage();
    }
    public void loadImage1(String name) {
        ImageIcon i1 = new ImageIcon(name);
        image1 = i1.getImage();
    }
    public void loadImage2(String name) {
        ImageIcon i1 = new ImageIcon(name);
        image2 = i1.getImage();
    }

    public Rectangle getBounds1(){
        return new Rectangle(970, 3100, 50, 50);
    }
    public Rectangle getBounds2(){
        return new Rectangle(970, 1950, 50, 50);
    }
    public Rectangle getBounds3(){
        return new Rectangle(870, 1250, 50, 50);
    }
    public Rectangle getBounds4(){
        return new Rectangle(1070, 1250, 50, 50);
    }

    public Image getImage2() {
        return image2;
    }

    public Image getImage1() {
        return image1;
    }

    public Image getImage3() {
        return image3;
    }

    public Image getImage4() {
        return image4;
    }

    public boolean isCheck1() {
        return check1;
    }

    public void setCheck1(boolean check1) {
        this.check1 = check1;
    }

    public boolean isCheck2() {
        return check2;
    }

    public void setCheck2(boolean check2) {
        this.check2 = check2;
    }

    public boolean isCheck3() {
        return check3;
    }

    public void setCheck3(boolean check3) {
        this.check3 = check3;
    }

    public boolean isCheck4() {
        return check4;
    }

    public void setCheck4(boolean check4) {
        this.check4 = check4;
    }
}
