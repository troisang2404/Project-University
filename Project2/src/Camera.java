import java.awt.event.KeyEvent;

public class Camera {
	private int x;
	private int y;
	private int dx;
	private int dy;


	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void moveCamera() {
		x += dx;
		y += dy;
	}

	public void moveXCamera() {
		x += dx;
		if(x > -17){
			x = - 17;
		}
		if(x < - 1013){
			x = - 1013;
		}
	}

	public void moveYCamera() {
		y += dy;
		if(y > -6){
			y = -6;
		}
		if(y < -6790){
			y = -6790;
		}
	}


	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_A) {
			dx = 3;
		}

		if (key == KeyEvent.VK_D) {
			dx = -3;
		}

		if (key == KeyEvent.VK_W) {
			dy = 3;
		}

		if (key == KeyEvent.VK_S) {
			dy = -3;
		}

	}

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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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
}
