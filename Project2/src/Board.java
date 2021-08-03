

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

	public class Board extends JPanel {
		private Player player;
		private Map map;
		private PlayerBar playerBar;
		private Item item;
		private Timer t1, t2, t3, t4, t5, t6;
		private List<MainGun> mg;
		private List<SubGun> sg;
		private List<Bomb> bb;
		private List<Enemy1> enemy1;
		private List<Enemy2> enemy2;
		private List<Enemy3> enemy3;
		private List<Enemy4> enemy4;
		private List<EnemyShot> es, es2;
		private List<Explosion> ex;
		private Random rd = new Random();
		private int value, value2;
		private Camera camera;
		private boolean game = true;
		private boolean time1, time2, time3, time4, time5, time6, time7, time8;
		private int currentFrame;
		JButton bt1, bt2, bt3, bt4;


	public Board() {
		addKeyListener(new TAdapter());
		addMouseListener(new TAdapter2());
		addMouseMotionListener(new MyMouseListener());
		setBackground(Color.BLACK);
		setFocusable(true);
		camera = new Camera(-485, -6790);
		player = new Player(940, 7300, camera);
		map = new Map(0, 0);
		playerBar = new PlayerBar(0, 0);
		item = new Item();
		currentFrame = 1;

		enemy1 = new ArrayList<>();
		enemy2 = new ArrayList<>();
		enemy3 = new ArrayList<>();
		enemy4 = new ArrayList<>();


		es = new ArrayList<>();
		es2 = new ArrayList<>();
		ex = new ArrayList<>();
		mg = player.getMg();
		sg = player.getSg();
		bb = player.getBb();

		bt1 = new JButton("");
		bt2 = new JButton("");
		bt3 = new JButton("");
		bt4 = new JButton("");

		add(bt1); add(bt2); add(bt3); add(bt3); add(bt4);

		bt3.setVisible(false);

		bt1.setBounds(370, 375, 250, 50);
		bt2.setBounds(447, 460, 100, 50);
		bt3.setBounds(20, 595, 170, 50);
		bt4.setBounds(447, 555, 100, 50);

		bt1.setOpaque(false); bt1.setContentAreaFilled(false); bt1.setBorderPainted(false);
		bt2.setOpaque(false); bt2.setContentAreaFilled(false); bt2.setBorderPainted(false);
		bt3.setOpaque(false); bt3.setContentAreaFilled(false); bt3.setBorderPainted(false);
		bt4.setOpaque(false); bt4.setContentAreaFilled(false); bt4.setBorderPainted(false);

		actionButton();

		addBgMusic("sound/Guitar Mayhem.wav");
		// Timer section
		t1 = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				repaint();
				upPlayer();
				checkCollision();
				moveGun();
				playerCollision();

                //Camera
				if((player.getLocX()>=470)&&(player.getLocX()<=1470)&&(player.getLocY()>=270)&&(player.getLocY()<=7200)) {
					camera.moveCamera();
				}
				if(((player.getLocY()<270)||(player.getLocY()>7200))&&((player.getLocX()<=1470)&&(player.getLocX()>=470))) {
					camera.moveXCamera();
				}
				if(((player.getLocX()<470)||(player.getLocX()>1470))&&((player.getLocY()>=270)&&(player.getLocY()<=7200))) {
					camera.moveYCamera();
				}

			}
		});
		t1.start();

		t2 = new Timer(40, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				upHpEnemy();
				reload();
				moveEnemyShot();
				addEnemy();
			}
		});
		t2.start();
		t3 = new Timer( 2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addEnemyShot();
			}
		});
		t3.start();

		t4 = new Timer(3000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				upExplosion();
				upEnemyShot();

			}
		});
		t4.start();

		t5 = new Timer(300, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// random shot
				value = rd.nextInt(enemy3.size() + 1);
				value2 = rd.nextInt(100);
				t5.setDelay(100+value2);

			}
		});
		t5.start();

		t6 = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkGame();
				checkPaused();
			}
		});
		t6.start();
		// End Timer section
	}

	//Add enemy method
	public void addEnemy(){
		if(!time1){
		if(player.getLocY() <= 7000) {
				enemy3.add(new Enemy3(300, 6400));
				enemy3.add(new Enemy3(200, 6450));
				enemy3.add(new Enemy3(700, 6350));
				enemy3.add(new Enemy3(500, 6550));
				enemy3.add(new Enemy3(400, 6300));
				enemy3.add(new Enemy3(360, 6400));
				enemy3.add(new Enemy3(1000, 6500));
				enemy3.add(new Enemy3(1500, 6450));
				enemy3.add(new Enemy3(1700, 6300));
				enemy3.add(new Enemy3(1660, 6200));
				time1 = true;
			}
		}
		else{
			if(!time2){
				if(player.getLocY() <= 6400) {
					enemy1.add(new Enemy1(1000, 5800));
					enemy1.add(new Enemy1(800, 5800));
					enemy1.add(new Enemy1(1200, 5800));
					enemy4.add(new Enemy4(200, 5600));
					time2 = true;
				}
			}
		}
		if(!time3){
			if(player.getLocY() <= 5900){
				enemy4.add(new Enemy4(200, 5100));
				enemy4.add(new Enemy4(1730, 5100));
				time3 = true;
			}
		}
		if(!time4){
			if(player.getLocY() <= 5000) {
				enemy3.removeAll(enemy3);
				enemy3.add(new Enemy3(170, 4600));
				enemy3.add(new Enemy3(170, 4550));
				enemy3.add(new Enemy3(170, 4500));
				enemy3.add(new Enemy3(170, 4450));
				enemy3.add(new Enemy3(170, 4400));
				enemy3.add(new Enemy3(1800, 4600));
				enemy3.add(new Enemy3(1800, 4550));
				enemy3.add(new Enemy3(1800, 4500));
				enemy3.add(new Enemy3(1800, 4450));
				enemy3.add(new Enemy3(1800, 4400));
				enemy2.add(new Enemy2(1000, 4500));
				enemy2.add(new Enemy2(800, 4500));
				time4 = true;
			}
		}
		if(!time5){
			if(player.getLocY() <= 4000){
				enemy4.add(new Enemy4(800, 3200));
				enemy4.add(new Enemy4(1000, 3200));
				time5 = true;
			}
		}
		if(!time6){
			if(player.getLocY() <= 2900){
				enemy1.add(new Enemy1(550, 2200));
				enemy1.add(new Enemy1(900, 2200));
				enemy1.add(new Enemy1(1250, 2200));
				enemy4.add(new Enemy4(860, 2050));
				time6 = true;
			}
		}
		if(!time7){
			if(player.getLocY() <= 1900){
				enemy3.removeAll(enemy3);
				enemy3.add(new Enemy3(900, 1300));
				enemy3.add(new Enemy3(950, 1300));
				enemy3.add(new Enemy3(800, 1300));
				enemy3.add(new Enemy3(850, 1300));
				enemy3.add(new Enemy3(1050, 1300));
				enemy3.add(new Enemy3(1100, 1300));
				enemy3.add(new Enemy3(1150, 1300));
				enemy3.add(new Enemy3(750, 1300));
				enemy3.add(new Enemy3(700, 1300));
				enemy3.add(new Enemy3(650, 1300));
				enemy3.add(new Enemy3(600, 1300));
				enemy3.add(new Enemy3(550, 1300));
				enemy3.add(new Enemy3(500, 1300));
				enemy3.add(new Enemy3(1200, 1300));
				enemy3.add(new Enemy3(1250, 1300));
				enemy3.add(new Enemy3(1300, 1300));
				enemy3.add(new Enemy3(1350, 1300));
				enemy3.add(new Enemy3(1400, 1300));
				time7 = true;
			}
		}
		if(!time8){
			if(player.getLocY() <= 1000){
				enemy1.removeAll(enemy1);
				enemy2.removeAll(enemy2);
				enemy3.removeAll(enemy3);
				enemy4.removeAll(enemy4);

				enemy2.add(new Enemy2(400, 350));
				enemy2.add(new Enemy2(670, 350));
				enemy2.add(new Enemy2(940, 350));
				enemy2.add(new Enemy2(1210, 350));
				enemy2.add(new Enemy2(1500, 350));
				enemy4.add(new Enemy4(200, 450));
				enemy4.add(new Enemy4(1700, 450));

				time8 = true;
			}
		}
	}

	// Draw section
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		switch (currentFrame) {
			case 0:
				bt1.setVisible(false);
				bt2.setVisible(false);
				bt3.setVisible(true);
				bt4.setVisible(false);
				drawHelp(g);
				break;
			case 1:
				bt1.setVisible(true);
				bt2.setVisible(true);
				bt3.setVisible(false);
				bt4.setVisible(true);
				drawMenu(g);
				break;
			case 2:
				bt1.setVisible(false);
				bt2.setVisible(false);
				bt4.setVisible(false);
				drawCamera(g);
				drawMap(g);
				drawEnemy1(g);
				drawEnemy2(g);
				drawEnemy3(g);
				drawEnemy4(g);
				drawEnemyShot(g);
				drawEnemyShot2(g);
				drawItem(g);
				drawPlayer(g);
				drawExplosion(g);
				drawMaingun(g);
				drawSubGun(g);
				drawBomb(g);
				drawPlayerBar(g);
				g.setColor(Color.GREEN);
				g.fillRect(200, 250, 100, 100);

				//Rotate imgae
				drawCannon(g);
				break;
			case 3:
				t1.stop();
				t2.stop();
				t3.stop();
				t4.stop();
				t5.stop();
				drawGameWin(g);
				break;

		}
	}

	// To make reload animation
	public void reload() {
		if (player.getReload() > 0) {
			player.setReload(player.getReload() - 1);
		}
	}

	public void drawPlayerBar(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(200 - camera.getX(), 670 - camera.getY(), 580, 80);

		g2d.setColor(Color.GREEN);
		g2d.fillRect(210-camera.getX(), 690-camera.getY(), player.getHp(), 30);

		g2d.setColor(Color.BLACK);
		g2d.drawImage(playerBar.getImage(), 420-camera.getX(), 680-camera.getY(), this);
		g2d.fillRect(420-camera.getX(), 680-camera.getY(), 54, player.getReload());

		g2d.drawImage(playerBar.getImage2(), 550-camera.getX(), 680-camera.getY(), this);

		g2d.drawImage(playerBar.getImage3(), 670-camera.getX(), 680-camera.getY(), this);

		g.setColor(Color.BLACK);
		Font f = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics fm = getFontMetrics(f);
		g.setFont(f);
		g.drawString("Hull points", 320-camera.getX(), 710-camera.getY() );
		g.drawString("Cannon", 480-camera.getX(), 700-camera.getY());
		g.drawString("Ammo: " + player.getMgCount(), 480-camera.getX(), 720-camera.getY());
		g.drawString("SubGun", 605-camera.getX(), 700-camera.getY());
		g.drawString("   " + player.getSgCount(), 605-camera.getX(), 720-camera.getY());
		g.drawString("Bom", 730-camera.getX(), 700-camera.getY());
		g.drawString("  " + player.getBbCount(), 730-camera.getX(), 720-camera.getY());



	}


	public void drawCannon(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if (player.isVisible()) {
			g2d.rotate(Math.toRadians(90), player.getLocX() + player.width/2, player.getLocY() + player.height/2);
			g2d.rotate(player.getAngle(), player.getLocX() + player.width/2, player.getLocY() + player.height/2);
			if(player.isCheckHorizontal()) {
				g2d.drawImage(player.getCannon(), player.getLocX() + 27, player.getLocY() - 50, this);
			}
			else{
				g2d.drawImage(player.getCannon(), player.getLocX() + 16, player.getLocY() - 35, this);
			}
		}
	}

	public void drawPlayer(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		if(player.isVisible()){
			g2d.drawImage(player.getCurentImage(), player.getLocX(), player.getLocY(), this);
		}
		else{
			g2d.drawImage(player.getI4().getImage(), player.getLocX(), player.getLocY(), this);
		}
	}

	public void drawEnemy1(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for (Enemy1 e : enemy1) {
			if(!e.isVisible()) {
				g2d.drawImage(e.getImage2(), e.getLocX()-3, e.getLocY()-10, this);
			}
			else{
				g2d.drawImage(e.getImage(), e.getLocX(), e.getLocY(), this);
			}
		}
	}

	public void drawEnemy2(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for (Enemy2 e : enemy2) {
			if(!e.isVisible()) {
				g2d.drawImage(e.getImage2(), e.getLocX()-3, e.getLocY()-10, this);
			}
			else{
				g2d.drawImage(e.getImage(), e.getLocX(), e.getLocY(), this);
			}
		}
	}

	public void drawEnemy3(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for (Enemy3 e : enemy3) {
			if(!e.isVisible()) {
				g2d.drawImage(e.getImage2(), e.getLocX(), e.getLocY(), this);
			}
			else{
				g2d.drawImage(e.getImage(), e.getLocX(), e.getLocY(), this);
			}
		}
	}

	public void drawEnemy4(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for (Enemy4 e : enemy4) {
			if(!e.isVisible()) {
				g2d.drawImage(e.getImage2(), e.getLocX()+20, e.getLocY()+13, this);
			}
			else{
				g2d.drawImage(e.getImage(), e.getLocX(), e.getLocY(), this);
			}
		}
	}

	public void drawEnemyShot(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for (EnemyShot e : es) {
			g2d.drawImage(e.getImage(), e.getLocX(), e.getLocY(), this);
		}
	}
	public void drawEnemyShot2(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for (EnemyShot e : es2) {
			g2d.drawImage(e.getImage(), e.getLocX(), e.getLocY(), this);
		}
	}

	public void drawMaingun(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for (MainGun m : mg) {
			g2d.drawImage(m.getImage(), m.getLocX()- 10, m.getLocY() -10, this);
		}

	}

	public void drawSubGun(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for (SubGun s : sg) {
			g2d.drawImage(s.getImage(), s.getLocX() -20 , s.getLocY() , this);
		}

	}

	public void drawBomb(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for (Bomb b : bb) {
			g2d.drawImage(b.getImage(), b.getLocX() -10 , b.getLocY() -10, this);
		}
	}

	public void drawItem(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		if(item.isCheck1()){
			g2d.drawImage(item.getImage1(), 970, 3100, this);
		}
		if(item.isCheck2()){
			g2d.drawImage(item.getImage2(), 970, 1950, this);
		}
		if(item.isCheck3()){
			g2d.drawImage(item.getImage3(), 870, 1250, this);
		}
		if(item.isCheck4()){
			g2d.drawImage(item.getImage4(), 1070, 1250, this);
		}
	}

	public void drawCamera(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(camera.getX(), camera.getY());
	}

	public void drawMap(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(map.getImage(), 0, 0, this);
	}

	public void drawMenu(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(map.getImage2(), 0, 0, this);
	}

	public void drawGameWin(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(map.getImage3(), 0, 0, this);
	}

	public void drawHelp(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(map.getImage4(), 0, 0, this);
	}

	public void drawExplosion(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for (Explosion s : ex) {
            g2d.drawImage(s.getImage(), s.getLocX() , s.getLocY() , null);
        }
    }
	// End Draw section

	// Method section

	// this method move and check hp player
	public void upPlayer() {
		player.move();

		if (player.getHp() <= 0) {
			addSound("sound/explosion.wav");
			player.setVisible(false);
			game = false;
			int n = JOptionPane.showConfirmDialog(this, "Do you want to restart?", "Tank has been destroyed",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				game = true;
			}
			if (n == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
		}
		if(player.getLocY() <= 300 && player.getLocX() <= 250){
			currentFrame = 3;
		}

	}

	// this method move the shot of player
	public void moveGun() {
		for (int i = 0; i < mg.size(); i++) {
			MainGun m = mg.get(i);
			if (m.isVisible()) {
				m.move();
			} else {
				mg.remove(m);
			}
		}
		for (int i = 0; i < sg.size(); i++) {
			SubGun s = sg.get(i);
			if (s.isVisible()) {
				s.move();
			} else {
				sg.remove(s);
			}
		}
		for (int i = 0; i < bb.size(); i++) {
			Bomb b = bb.get(i);
			if (b.isVisible()) {
				b.move();
			} else {
				bb.remove(b);
			}
		}
	}

	// updateEnemy
	public void upHpEnemy() {
		for (int i = 0; i < enemy1.size(); i++) {
			Enemy1 e = enemy1.get(i);
			if (e.getHp() <= 0) {
				e.setVisible(false);
			}
		}
		for (int i = 0; i < enemy2.size(); i++) {
			Enemy2 e = enemy2.get(i);
			if (e.getHp() <= 0) {
				e.setVisible(false);
			}
		}
		for (int i = 0; i < enemy3.size(); i++) {
			Enemy3 e = enemy3.get(i);
			if (e.getHp() <= 0) {
				e.setVisible(false);
			}
		}
		for (int i = 0; i < enemy4.size(); i++) {
			Enemy4 e = enemy4.get(i);
			if (e.getHp() <= 0) {
				e.setVisible(false);
			}
		}
	}

	//add enemy's shot
	public void addEnemyShot() {
		for(Enemy1 e : enemy1){
			if (e.isVisible()) {
				es2.add(new EnemyShot(e.getLocX()+ e.width/2 - 5, e.getLocY()+e.width/2, 5, 0, 0));
			}
		}

		for (Enemy2 e : enemy2) {
				if (e.isVisible()) {
					es2.add(new EnemyShot(e.getLocX()+ e.width/2 - 5, e.getLocY()+e.width/2, 6, 0, 0));
					es.add(new EnemyShot(e.getLocX() + e.width/2, e.getLocY()+e.width/2, 6, player.getLocX(), player.getLocY()));
				}
		}
		for (Enemy3 e : enemy3) {
			int rd1 = enemy3.indexOf(e);
			if(rd1 == value) {
				if (e.isVisible()) {
					es.add(new EnemyShot(e.getLocX()+5, e.getLocY()+5, 10, player.getLocX(), player.getLocY()));

				}
			}
		}
		for (Enemy4 e : enemy4) {
				if (e.isVisible()) {
					es.add(new EnemyShot(e.getLocX()+e.width/2, e.getLocY()+e.height/2, 7, player.getLocX(), player.getLocY()));
					es.add(new EnemyShot(e.getLocX()+e.width/2, e.getLocY()+e.height/2, 7, player.getLocX() + 45, player.getLocY() + 45));
					es.add(new EnemyShot(e.getLocX()+e.width/2, e.getLocY()+e.height/2, 7, player.getLocX() - 45, player.getLocY() - 45));

				}
		}

	}

	public void moveEnemyShot() {
		for(int i = 0; i < es.size(); i++){
			EnemyShot e = es.get(i);
			if(e.isVisible()){
				e.move();
			}
			else {
				es.remove(e);
			}
		}
		for(int i = 0; i < es2.size(); i++){
			EnemyShot e = es2.get(i);
			if(e.isVisible()){
				e.move2();
			}
			else {
				es2.remove(e);
			}
		}

	}

	// update Explosion
	public void upExplosion(){
		for (int i = 0; i < ex.size(); i++){
			Explosion e = ex.get(i);
			if(e.isVisible()){
				ex.remove(e);
			}
		}
	}

	// remove after 2s
	public void upEnemyShot(){
		for(int i = 0; i < es2.size(); i++){
			EnemyShot e = es2.get(i);
			if(e.isVisible()){
				es2.remove(e);
			}
		}

	}

	public  void addSound(String sound){
		player.addSound(sound);
	}

	private void addBgMusic(String file) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			Thread.sleep(10);
			clip.start();

		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	public void checkPaused() {
		if (player.isPaused()) {
			t1.stop();
			t2.stop();
			t3.stop();
			t4.stop();
			t5.stop();
			JOptionPane.showMessageDialog(this, "The game has been paused, click ok to continue");
			player.setPaused(false);
		} else {
			t1.start();
			t2.start();
			t3.start();
			t4.start();
			t5.start();
		}
	}

	public void checkGame() {
		if (!game) {
			t1.stop();
			t2.stop();
			t3.stop();
			t4.stop();
			t5.stop();
			time1 = false;
			time2 = false;
			time3 = false;
			time4 = false;
			time5 = false;
			time6 = false;
			time7 = false;
			time8 = false;

			for(EnemyShot e : es){
				e.setVisible(false);
				break;
			}
			for(EnemyShot e : es2){
				e.setVisible(false);
				break;
			}

			enemy1.removeAll(enemy1);
			enemy2.removeAll(enemy2);
			enemy3.removeAll(enemy3);
			enemy4.removeAll(enemy4);
			camera.setX(-485);
			camera.setY(-6790);
			player.setLocX(940);
			player.setLocY(7300);
			player.setVisible(true);
			player.setHp(100);
			player.setMgCount(40);
			player.setSgCount(60);
			player.setBbCount(3);
		} else {
			t1.start();
			t2.start();
			t3.start();
			t4.start();
			t5.start();
		}
	}

	// End method section

	// Collision section
	public void checkCollision(){

		for(int i = 0; i < mg.size(); i++){
			MainGun m = mg.get(i);
			Rectangle rb =  m.getBounds();
			for(Enemy1 e : enemy1) {
				Rectangle rt = e.getBounds();
				if (rb.intersects(rt)) {
					m.setVisible(false);
					ex.add(new Explosion(m.getLocX(), m.getLocY() - e.height/2));
					e.setHp(e.getHp() - 1);
					if(e.isVisible()){
					addSound("sound/hit.wav");
					}
				}
			}
		}

		for(int i = 0; i < mg.size(); i++){
			MainGun m = mg.get(i);
			Rectangle rb =  m.getBounds();
			for(Enemy2 e : enemy2) {
				Rectangle rt = e.getBounds();
				if (rb.intersects(rt)) {
					m.setVisible(false);
                    ex.add(new Explosion(m.getLocX(), m.getLocY() - e.height/2));
					e.setHp(e.getHp() - 1);
					if(e.isVisible()){
						addSound("sound/hit.wav");
					}
				}
			}
		}

		for(int i = 0; i < mg.size(); i++){
			MainGun m = mg.get(i);
			Rectangle rb =  m.getBounds();
			for(Enemy3 e : enemy3) {
				Rectangle rt = e.getBounds();
				if (rb.intersects(rt)) {
					if(e.isVisible()) {
						addSound("sound/death.wav");
						m.setVisible(false);
						ex.add(new Explosion(m.getLocX(), m.getLocY() - e.height/2));
					}
					e.setHp(e.getHp() - 1);
				}
			}
		}

		for(int i = 0; i < mg.size(); i++){
			MainGun m = mg.get(i);
			Rectangle rb =  m.getBounds();
			for(Enemy4 e : enemy4) {
				Rectangle rt = e.getBounds();
				if (rb.intersects(rt)) {
					m.setVisible(false);
                    ex.add(new Explosion(m.getLocX(), m.getLocY() - e.height/2));
					e.setHp(e.getHp() - 1);
					if(e.isVisible()){
						addSound("sound/hit.wav");
					}
				}
			}
		}

        for(int i = 0; i < sg.size(); i++){
            SubGun s = sg.get(i);
            Rectangle rb2 =  s.getBounds();
            for(Enemy3 e : enemy3) {
                Rectangle rt = e.getBounds();
                if (rb2.intersects(rt)) {
                	if(e.isVisible()) {
						addSound("sound/death.wav");
						s.setVisible(false);
						ex.add(new Explosion(s.getLocX(), s.getLocY()));
					}
					e.setHp(e.getHp() - 1);

                }

            }

        }
		for(int i = 0; i < sg.size(); i++){
			SubGun s = sg.get(i);
			Rectangle rb2 =  s.getBounds();
			for(Enemy1 e : enemy1) {
				Rectangle rt = e.getBounds();
				if (rb2.intersects(rt)) {
					s.setVisible(false);

				}

			}

		}
		for(int i = 0; i < sg.size(); i++){
			SubGun s = sg.get(i);
			Rectangle rb2 =  s.getBounds();
			for(Enemy2 e : enemy2) {
				Rectangle rt = e.getBounds();
				if (rb2.intersects(rt)) {
					s.setVisible(false);

				}

			}

		}
		for(int i = 0; i < sg.size(); i++){
			SubGun s = sg.get(i);
			Rectangle rb2 =  s.getBounds();
			for(Enemy4 e : enemy4) {
				Rectangle rt = e.getBounds();
				if (rb2.intersects(rt)) {
					s.setVisible(false);
				}

			}

		}
		for(int i = 0; i < bb.size(); i++){
			Bomb m = bb.get(i);
			Rectangle rb =  m.getBounds();
			for(Enemy1 e : enemy1) {
				Rectangle rt = e.getBounds();
				if (rb.intersects(rt)) {
					m.setVisible(false);
					ex.add(new Explosion(m.getLocX(), m.getLocY() - e.height/2));
					ex.add(new Explosion(m.getLocX() + 10, m.getLocY() - e.height/2 - 20));
					ex.add(new Explosion(m.getLocX() + 20, m.getLocY() - e.height/2 - 10));
					ex.add(new Explosion(m.getLocX() -10, m.getLocY() - e.height));
					ex.add(new Explosion(m.getLocX() -10, m.getLocY() - e.height/2));
					ex.add(new Explosion(m.getLocX(), m.getLocY() -10 - e.height/2 - 30));
					e.setHp(e.getHp() - 3);
				}
			}
		}
		for(int i = 0; i < bb.size(); i++){
			Bomb m = bb.get(i);
			Rectangle rb =  m.getBounds();
			for(Enemy2 e : enemy2) {
				Rectangle rt = e.getBounds();
				if (rb.intersects(rt)) {
					m.setVisible(false);
					ex.add(new Explosion(m.getLocX(), m.getLocY() - e.height/2));
					ex.add(new Explosion(m.getLocX(), m.getLocY() - e.height/2 - 20));
					ex.add(new Explosion(m.getLocX(), m.getLocY() - e.height/2 - 10));
					ex.add(new Explosion(m.getLocX() -10, m.getLocY() - e.height));
					ex.add(new Explosion(m.getLocX() -10, m.getLocY() - e.height/2));
					ex.add(new Explosion(m.getLocX(), m.getLocY() -10 - e.height/2 - 30));
					e.setHp(e.getHp() - 3);
				}
			}
		}
		for(int i = 0; i < bb.size(); i++){
			Bomb m = bb.get(i);
			Rectangle rb =  m.getBounds();
			for(Enemy3 e : enemy3) {
				Rectangle rt = e.getBounds();
				if (rb.intersects(rt)) {
					if(e.isVisible()) {
						addSound("sound/death.wav");
						m.setVisible(false);
						ex.add(new Explosion(m.getLocX(), m.getLocY() - e.height / 2));
						ex.add(new Explosion(m.getLocX(), m.getLocY() - e.height / 2 - 20));
						ex.add(new Explosion(m.getLocX(), m.getLocY() - e.height / 2 - 10));
						ex.add(new Explosion(m.getLocX() - 10, m.getLocY() - e.height));
						ex.add(new Explosion(m.getLocX() - 10, m.getLocY() - e.height / 2));
						ex.add(new Explosion(m.getLocX(), m.getLocY() - 10 - e.height / 2 - 30));
					}
					e.setHp(e.getHp() - 3);
				}
			}
		}
		for(int i = 0; i < bb.size(); i++){
			Bomb m = bb.get(i);
			Rectangle rb =  m.getBounds();
			for(Enemy4 e : enemy4) {
				Rectangle rt = e.getBounds();
				if (rb.intersects(rt)) {
					m.setVisible(false);
					ex.add(new Explosion(m.getLocX(), m.getLocY() - e.height/2));
					ex.add(new Explosion(m.getLocX(), m.getLocY() - e.height/2 - 20));
					ex.add(new Explosion(m.getLocX(), m.getLocY() - e.height/2 - 10));
					ex.add(new Explosion(m.getLocX() -10, m.getLocY() - e.height));
					ex.add(new Explosion(m.getLocX() -10, m.getLocY() - e.height/2));
					ex.add(new Explosion(m.getLocX(), m.getLocY() -10 - e.height/2 - 30));
					e.setHp(e.getHp() - 3);
				}
			}
		}


	}

	// this method check when player collision with enemy

	public void playerCollision() {
		Rectangle rp = player.getBounds();
		Rectangle i1 = item.getBounds1();
		Rectangle i2 = item.getBounds2();
		Rectangle i3 = item.getBounds3();
		Rectangle i4 = item.getBounds4();

		if(i1.intersects(rp)) {
			if(item.isCheck1()) {
				addSound("sound/pickup.wav");
				item.setCheck1(false);
				player.setMgCount(player.getMgCount() + 50);
			}
		}
		else{
			if(i2.intersects(rp)){
				if(item.isCheck2()) {
					addSound("sound/pickup.wav");
					item.setCheck2(false);
					player.setSgCount(player.getSgCount() + 100);
				}
			}
			else{
				if(i3.intersects(rp)){
					if(item.isCheck3()) {
						addSound("sound/pickup.wav");
						item.setCheck3(false);
						player.setBbCount(3);
					}
				}
				else{
					if(i4.intersects(rp)){
						if(item.isCheck4()) {
							addSound("sound/pickup.wav");
							item.setCheck4(false);
							player.setHp(100);
						}
					}
				}
			}
		}

		for(Enemy3 e : enemy3) {
			Rectangle rt = e.getBounds();
			if (rt.intersects(rp)) {
				if(e.isVisible()) {
					addSound("sound/death.wav");
					e.setVisible(false);
				}
			}
		}

		for(EnemyShot e : es){
			Rectangle res = e.getBounds();
			if(res.intersects(rp)){
				e.setVisible(false);
				player.setHp(player.getHp() - 1);
				ex.add(new Explosion(e.getLocX(), e.getLocY()));
			}
		}
		for(EnemyShot e : es2){
			Rectangle res = e.getBounds();
			if(res.intersects(rp)){
				e.setVisible(false);
				player.setHp(player.getHp() - 1);
				ex.add(new Explosion(e.getLocX(), e.getLocY()));
			}
		}
	}
	// End collision section

	// Button actionListener section
	public void actionButton(){
		bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == bt1){
					currentFrame++;
				}
			}
		});

		bt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == bt2){
					currentFrame--;
				}
			}
		});

		bt3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == bt3){
					currentFrame ++;
				}
			}
		});

		bt4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == bt4){
					System.exit(0);
				}
			}
		});
	}
	//End actionButton

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
			camera.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
			camera.keyPressed(e);
		}
	}

	private class TAdapter2 extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			player.mousePressed(e);

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			player.mouseReleased(e);
		}
	}

	private class MyMouseListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent m) {
			player.mouseMoved(m);
		}

	}
}
