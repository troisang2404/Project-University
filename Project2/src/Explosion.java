public class Explosion extends GameObjects {

    public Explosion(int locX, int locY) {
        super(locX, locY);
        loadImage("image/explosion.png");
        getImageDimension();
    }
}
