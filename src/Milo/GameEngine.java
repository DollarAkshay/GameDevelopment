package Milo;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

public class GameEngine extends Applet implements Runnable, KeyListener {

	private Player robot;
	private Heliboy hb1, hb2;
	private Image img, currentSprite, character, background, characterJumped,
			characterDown, heliboy;
	private Graphics secondary;
	private URL base;
	private static Background bg1, bg2;

	@Override
	public void init() {
		setSize(1200, 675);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Milo");
		// Fetching Image
		base = getDocumentBase();
		character = getImage(base, "images/character.png");
		characterJumped = getImage(base, "images/jump.png");
		characterDown = getImage(base, "images/down.png");
		currentSprite = character;
		heliboy = getImage(base, "images/heliboy.png");
		background = getImage(base, "images/background.png");
	}

	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		hb1 = new Heliboy(340, 360);
		hb2 = new Heliboy(760, 360);
		robot = new Player();
		Thread thread = new Thread(this);
		thread.start();
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
			robot.update();

			if (robot.isJumping() == false && robot.isDucking() == false)
				currentSprite = character;
			else if (robot.isJumping())
				currentSprite = characterJumped;
			else if (robot.isDucking())
				currentSprite = characterDown;
			
			ArrayList projectiles = robot.getProjectiles();
			for(int i=0;i<projectiles.size();i++){
				Projectile p = (Projectile) projectiles.get(i);
				if(p.isVisible())
					p.update();
				else
					projectiles.remove(i);
				
			}
			
			hb1.update();
			hb2.update();
			bg1.update();
			bg2.update();

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
		
		ArrayList projectiles = robot.getProjectiles();
		for(int i=0;i<projectiles.size();i++){
			Projectile p = (Projectile) projectiles.get(i);
			g.setColor(Color.black);
			g.fillOval(p.getX(), p.getY(), 8, 8);
		}
		
		g.drawImage(currentSprite, robot.getCenterX() - 61,
				robot.getCenterY() - 63, this);
		g.drawImage(heliboy, hb1.getCenterX() - 41, hb1.getCenterY() - 41, this);
		g.drawImage(heliboy, hb2.getCenterX() - 41, hb2.getCenterY() - 41, this);

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
			if(robot.isDucking()==false)
				robot.shoot();
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
			currentSprite = character;
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
}
