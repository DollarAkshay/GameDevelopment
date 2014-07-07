package Milo;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine extends Applet implements Runnable, KeyListener {

	@Override
	public void init() {
		setSize(1000, 600);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Milo");
	}

	@Override
	public void start() {
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
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Move UP");
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("Move DOWN");
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("Move LEFT");
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("Move RIGHT");
			break;
		case KeyEvent.VK_SPACE:
			System.out.println("JUMP");
			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Stop UP");
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("Stop DOWN");
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("Stop LEFT");
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("Stop RIGHT");
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
}
