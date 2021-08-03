

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class GameObjects {
	protected int locX;
	protected int locY;
	protected int width;
	protected int height;
	protected boolean visible;
	protected Image image, image2;


	public GameObjects(int locX, int locY) {
		this.locX = locX;
		this.locY = locY;
		this.visible = true;

	}

	protected void loadImage(String name) {
		ImageIcon i1 = new ImageIcon(name);
		image = i1.getImage();
	}

	protected void loadImage2(String name) {
		ImageIcon i1 = new ImageIcon(name);
		image2 = i1.getImage();
	}

	protected void getImageDimension() {
		width = image.getWidth(null);
		height = image.getHeight(null);
	}

	protected Rectangle getBounds() {
		return new Rectangle(locX, locY, width, height);

	}

	protected void addSound(String sound) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();

		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}


	public int getLocX() {
		return locX;
	}

	public void setLocX(int locX) {
		this.locX = locX;
	}

	public int getLocY() {
		return locY;
	}

	public void setLocY(int locY) {
		this.locY = locY;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Image getImage() {
		return image;
	}

	public Image getImage2() {
		return image2;
	}
}
