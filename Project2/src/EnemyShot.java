public class EnemyShot extends GameObjects {
    private int shotSpeed;
    private double angle;
    private double xVel, yVel;
    private int x1, y1;

    public EnemyShot(int locX, int locY, int shotSpeed, int x1, int y1) {
        super(locX, locY);
        this.x1 = x1;
        this.y1 = y1;
        this.shotSpeed = shotSpeed;
        loadImage("image/EnemyShot.png");
        getImageDimension();

        angle = Math.atan2(y1 - locY, x1 - locX);
        xVel =Math.cos(angle);
        yVel = Math.sin(angle);

    }

    public void move() {
        locX += xVel * 20;
        locY += yVel * 20;

        if(this.locX > 1900 || this.locX < 0){
            visible = false;
        }
        if(this.locY < 0 || this.locY > 7400){
            visible = false;
        }
    }

    public void move2() {
        locY += shotSpeed;

        if(this.locX > 1900 || this.locX < 0){
            visible = false;
        }
        if(this.locY < 0 || this.locY > 7300){
            visible = false;
        }
    }

    public int getShotSpeed() {
        return shotSpeed;
    }

    public void setShotSpeed(int shotSpeed) {
        this.shotSpeed = shotSpeed;
    }
}
