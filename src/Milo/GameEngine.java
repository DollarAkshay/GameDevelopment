package Milo;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import Milo.framework.Animation;

public class GameEngine extends Applet implements Runnable, KeyListener {

	private static Player robot;
	private Heliboy hb1, hb2;
	private Image img, currentSprite, character, character2, character3,
			background, characterJumped, characterDown, heliboy, heliboy2,
			heliboy3, heliboy4, heliboy5;
	public static Image tilegrassTop, tilegrassBot, tilegrassLeft,
			tilegrassRight, tiledirt;
	private Graphics secondary;
	private URL base;
	private static Background bg1, bg2;
	private Animation anim, hanim;
	private ArrayList<Tile> tilearray = new ArrayList<Tile>();;

	@Override
	public void init() {
		setSize(1000, 480);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Milo");
		// Fetching Image
		base = getDocumentBase();
		character = getImage(base, "images/character.png");
		character2 = getImage(base, "images/character2.png");
		character3 = getImage(base, "images/character3.png");

		characterDown = getImage(base, "images/down.png");
		characterJumped = getImage(base, "images/jump.png");

		heliboy = getImage(base, "images/heliboy.png");
		heliboy2 = getImage(base, "images/heliboy2.png");
		heliboy3 = getImage(base, "images/heliboy3.png");
		heliboy4 = getImage(base, "images/heliboy4.png");
		heliboy5 = getImage(base, "images/heliboy5.png");

		background = getImage(base, "images/background.png");
		tiledirt = getImage(base, "images/tiledirt.png");
		tilegrassTop = getImage(base, "images/tilegrasstop.png");
		tilegrassBot = getImage(base, "images/tilegrassbot.png");
		tilegrassLeft = getImage(base, "images/tilegrassleft.png");
		tilegrassRight = getImage(base, "images/tilegrassright.png");

		anim = new Animation();
		anim.addFrame(character, 1250);
		anim.addFrame(character2, 50);
		anim.addFrame(character3, 50);
		anim.addFrame(character2, 50);

		hanim = new Animation();
		hanim.addFrame(heliboy, 100);
		hanim.addFrame(heliboy2, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy5, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy2, 100);

		currentSprite = anim.getImage();
	}

	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);

		try {
			loadMap("images/map1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		robot = new Player();
		hb1 = new Heliboy(340, 360);
		hb2 = new Heliboy(760, 360);
		
		Thread thread = new Thread(this);
		thread.start();
	}

	private void loadMap(String filename) throws IOException {
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while(true){
			String line = reader.readLine();
			if(line==null){
				reader.close();
				break;
			}
			if(!line.startsWith("!")){
				lines.add(line);
				width = Math.max(width, line.length());
			}
		}
		height=lines.size();
		
		for(int j=0; j<12; j++){
			String line = (String) lines.get(j);
			for(int i=0;i<width;i++){
				System.out.println(i + "is i ");
				
				if (i < line.length()) {
					char ch = line.charAt(i);
	                Tile t = new Tile(i, j, Character.getNumericValue(ch));
	                tilearray.add(t);
	            }
	        }
	    }
	 }
	
	
	@Override
	public void stop() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void run() {
		while (true) {

			for (int i = 0; i < tilearray.size(); i++) {
				Tile t = tilearray.get(i);
				t.update();
			}

			robot.update();

			if (robot.isJumping() == false && robot.isDucking() == false)
				currentSprite = anim.getImage();
			else if (robot.isJumping())
				currentSprite = characterJumped;
			else if (robot.isDucking())
				currentSprite = characterDown;

			ArrayList projectiles = robot.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile p = (Projectile) projectiles.get(i);
				if (p.isVisible())
					p.update();
				else
					projectiles.remove(i);

			}

			hb1.update();
			hb2.update();
			bg1.update();
			bg2.update();

			animate();
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void update(Graphics g) {

		if (img == null) {
			img = createImage(this.getWidth(), this.getHeight());
			secondary = img.getGraphics();
		}

		secondary.setColor(this.getBackground());
		secondary.fillRect(0, 0, this.getWidth(), this.getHeight());
		secondary.setColor(this.getForeground());
		paint(secondary);

		g.drawImage(img, 0, 0, this);

	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
		g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);

		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = tilearray.get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
		}
		ArrayList projectiles = robot.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g.setColor(Color.black);
			g.fillOval(p.getX(), p.getY(), 8, 8);
		}

		g.drawRect((int)robot.r1.getX(), (int)robot.r1.getY(), (int)robot.r1.getWidth(), (int)robot.r1.getHeight());
		g.drawRect((int)robot.r2.getX(), (int)robot.r2.getY(), (int)robot.r2.getWidth(), (int)robot.r2.getHeight());
		
		
		g.drawImage(currentSprite, robot.getCenterX() - 61,
				robot.getCenterY() - 63, this);
		g.drawImage(hanim.getImage(), hb1.getCenterX() - 41,
				hb1.getCenterY() - 41, this);
		g.drawImage(hanim.getImage(), hb2.getCenterX() - 41,
				hb2.getCenterY() - 41, this);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			currentSprite = characterJumped;
			robot.jump();
			break;
		case KeyEvent.VK_DOWN:
			currentSprite = characterDown;
			robot.setDucking(true);
			break;
		case KeyEvent.VK_LEFT:
			robot.moveLeft();
			robot.setMovingLeft(true);
			break;
		case KeyEvent.VK_RIGHT:
			robot.moveRight();
			robot.setMovingRight(true);
			break;

		case KeyEvent.VK_CONTROL:
			if (robot.isDucking() == false){
				robot.shoot();
				robot.setReadyToFire(false);
			}
			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Stop JUMP");
			break;
		case KeyEvent.VK_DOWN:
			currentSprite = anim.getImage();
			robot.setDucking(false);
			break;
		case KeyEvent.VK_LEFT:
			robot.setMovingLeft(false);
			robot.stop();
			break;
		case KeyEvent.VK_RIGHT:
			robot.setMovingRight(false);
			robot.stop();
			break;
		case KeyEvent.VK_CONTROL:
			robot.setReadyToFire(true);
			break;

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static Background getBg1() {
		return bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public void animate() {
		anim.update(10);
		hanim.update(50);
	}

	public static Player getRobot() {
		return robot;
	}

	public static void setRobot(Player robot) {
		GameEngine.robot = robot;
	}
	
	
	
}
