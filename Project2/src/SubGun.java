

public class SubGun extends GameObjects {
	private int shotSpeed;
	private double angle;
	private double xVel, yVel;
	private int x1, y1;

	public SubGun(int locX, int locY, int shotSpeed, int x1, int y1) {
		super(locX, locY);
		this.x1 = x1;
		this.y1 = y1;
		this.shotSpeed = shotSpeed;
		angle = Math.atan2(y1 - locY, x1 - locX);
		xVel =Math.cos(angle);
		yVel = Math.sin(angle);
		initSubGun();
	}

	private void initSubGun() {
		loadImage("image/SubGun.png");
		getImageDimension();
	}

	public void move() {
		locX += xVel * 10;
		locY += yVel * 10;
		if(this.locX > 2000 || this.locX < 0){
			visible = false;
		}
		if(this.locY < 0 || this.locY > 7400){
			visible = false;
		}

	}

	public void setShotSpeed(int shotSpeed) {
		this.shotSpeed = shotSpeed;
	}

	public int getShotSpeed() {
		return shotSpeed;
	}


}
