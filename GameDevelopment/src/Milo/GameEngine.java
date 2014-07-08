package Milo;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class GameEngine extends Applet implements Runnable, KeyListener {

	private Player robot;
	private Image img,currentSprite, character, background, characterJumped, characterDown;
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
		currentSprite=character;
		background = getImage(base, "images/background.png");
	}

	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
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
			
			if(robot.isJumping()==false && robot.isDucking()==false)
				currentSprite=character;
			else if(robot.isJumping())
				currentSprite=characterJumped;
			else if(robot.isDucking())
				currentSprite=characterDown;
			
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
		g.drawImage(currentSprite, robot.getCenterX() - 61,
				robot.getCenterY() - 63, this);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Move up");
			break;
		case KeyEvent.VK_DOWN:
			currentSprite=characterDown;
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
		case KeyEvent.VK_SPACE:
			currentSprite=characterJumped;
			robot.jump();
			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			break;
		case KeyEvent.VK_DOWN:
			currentSprite=character;
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
		case KeyEvent.VK_SPACE:
			System.out.println("Stop JUMP");
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
