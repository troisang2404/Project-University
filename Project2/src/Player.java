
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends GameObjects {
	private int dx;
	private int dy;
	private List<MainGun> mg = new ArrayList<>();
	private List<SubGun> sg = new ArrayList<>();
	private List<Bomb> bb = new ArrayList<>();
	private int moveSpeed;
	private int hp;
	private int mgSpeed;
	private int mgCount;
	private int sgCount;
	private int bbCount;
	private int bCount;
	private int reload = 0;
	private Timer t1;
	private int xMouse;
	private int yMouse;
	private Image curentImage;
	private Image cannon;
	private ImageIcon i1, i2, i3, i4;
	private double angle;
	private Camera camera;
	private boolean checkHorizontal;
	private boolean paused;


	public Player(int locX, int locY, Camera camera) {
		super(locX, locY);
		this.camera = camera;
		mgSpeed = 10;
		mgCount = 40;
		sgCount = 60;
		bbCount = 3;
		initPlayer();
		checkHorizontal = false;
		hp = 100;
		paused = false;

	}

	// This method genarates Constructor
	private void initPlayer() {
		loadImage("image/tankP3.png");
		loadImage2("image/cannon22.png");
		i1 = new ImageIcon("image/tankP3Right.png");
		i2 = new ImageIcon("image/tankP3Left.png");
		i3 = new ImageIcon("image/tankP3Down.png");
		i4 = new ImageIcon("image/tankP3D.png");
		curentImage = this.getImage();
		getImageDimension();
	}



	// This method move the player
	public void move() {

		locX += dx + moveSpeed;
		locY += dy + moveSpeed;

		if (locX < 170) {
			locX = 170;
		}
		if (locX > 1800) {
			locX = 1800;
		}

		if (locY < 20) {
			locY = 20;
		}
		if (locY > 7300) {
			locY = 7300;
		}

	}

	// This method updates the health points of the player
	public void update(int hp) {
		this.hp = hp;
	}

	// fire method
	public void fire() {
	    	if(mgCount > 0) {
				addSound("sound/shellfall.wav");
				mg.add(new MainGun(locX + (width / 2), locY + (height / 2), 2, xMouse, yMouse));
				mgCount--;

			}
	}
	public void fire2() {
			if (sgCount > 0) {
				addSound("sound/mg34.wav");
				sg.add(new SubGun(locX + (width/2), locY + (height/2), 20, xMouse, yMouse));
				sgCount--;
			}
	}

	public void fire3() {
			if(bbCount > 0){
				addSound("sound/bom.wav");
				bb.add(new Bomb(locX + (width/2), locY + (height/2), 20, xMouse, yMouse));
				bbCount--;
			}
	}

	public void loadImage2(String name) {
		ImageIcon i1 = new ImageIcon(name);
		cannon = i1.getImage();
	}
	// width and height for horizontal image
	public void getImageDimension2(){
	    width = i2.getImage().getWidth(null);
	    height = i2.getImage().getHeight(null);
    }

	// Press key to move the Player
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_SPACE){
			if (reload == 0) {
				if(mgCount > 0) {
					fire();
					setReload(50);
				}
			}
		}
		if(key == KeyEvent.VK_E){
				if(bbCount > 0) {
					fire3();
				}
		}
		if (key == KeyEvent.VK_P) {
			if (!isPaused())
				setPaused(true);
			else
				setPaused(false);
		}
		if (key == KeyEvent.VK_A) {
			dx = -3;
			curentImage = i2.getImage();
			checkHorizontal = true;
            getImageDimension2();
		}

		if (key == KeyEvent.VK_D) {
			dx = 3;
			curentImage = i1.getImage();
			checkHorizontal = true;
			getImageDimension2();
		}

		if (key == KeyEvent.VK_W) {
			dy = -3;
			curentImage = this.getImage();
			checkHorizontal = false;
			getImageDimension();
		}

		if (key == KeyEvent.VK_S) {
			dy = 3;
			curentImage = i3.getImage();
			checkHorizontal = false;
			getImageDimension();
		}

	}

	// Released key to stop moving
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) {
			dy = 0;
		}
		if (key == KeyEvent.VK_S) {
			dy = 0;
		}
		if (key == KeyEvent.VK_A) {
			dx = 0;
		}
		if (key == KeyEvent.VK_D) {
			dx = 0;
		}
	}


	//For firing subgun
	public void mousePressed(MouseEvent e){
			if (SwingUtilities.isLeftMouseButton(e)) {
				if (t1 == null) {
					t1 = new Timer();
				}
			}

		t1.schedule(new TimerTask() {
		    @Override
			public void run() {
				fire2();
			}
		}, 50, 200);

	}



	public void mouseReleased(MouseEvent e){
		if (t1 != null) {
			t1.cancel();
			t1 = null;
		}
	}

	public double getAngle(){
		return this.angle = (Math.atan2((yMouse  - locY) -45,
				(xMouse  - locX) -45));
	}

	public void mouseMoved(MouseEvent m) {
		xMouse = m.getX()-camera.getX();
		yMouse = m.getY()-camera.getY();
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMgSpeed() {
		return mgSpeed;
	}

	public void setMgSpeed(int mgSpeed) {
		this.mgSpeed = mgSpeed;
	}

	public List<MainGun> getMg() {
		return mg;
	}

	public List<SubGun> getSg() {
		return sg;
	}

	public List<Bomb> getBb() {
		return bb;
	}

	public int getReload() {
		return reload;
	}

	public void setReload(int reload) {
		this.reload = reload;
	}

	public int getMgCount() {
		return mgCount;
	}

	public void setMgCount(int mgCount) {
		this.mgCount = mgCount;
	}

	public int getSgCount() {
		return sgCount;
	}

	public void setSgCount(int sgCount) {
		this.sgCount = sgCount;
	}

	public int getBbCount() {
		return bbCount;
	}

	public void setBbCount(int bbCount) {
		this.bbCount = bbCount;
	}

	public Image getCurentImage() {
		return curentImage;
	}

    public Image getCannon() {
        return cannon;
    }

	public int getxMouse() {
		return xMouse;
	}

	public void setxMouse(int xMouse) {
		this.xMouse = xMouse;
	}

	public int getyMouse() {
		return yMouse;
	}

	public void setyMouse(int yMouse) {
		this.yMouse = yMouse;
	}

    public boolean isCheckHorizontal() {
        return checkHorizontal;
    }

    public ImageIcon getI4() {
        return i4;
    }

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
}
