package Milo;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Player {

	final int JUMPSPEED = -15;
	final int MOVESPEED = 5;

	private int centerX = 100;
	private int centerY = 377;
	private boolean jumping = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducking = false;
	private boolean readyToFire = true;

	private int speedX = 0;
	private int speedY = 0;
	
	public static Rectangle r1 = new Rectangle(0,0,0,0);
	public static Rectangle r2 = new Rectangle(0,0,0,0);

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	private static Background bg1 = GameEngine.getBg1();
	private static Background bg2 = GameEngine.getBg2();

	public void update() {

		if (centerX <= 350 || speedX <= 0) {
			centerX += speedX;
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);
		} else {
			bg1.setSpeedX(-MOVESPEED);
			bg2.setSpeedX(-MOVESPEED);
		}

		centerY += speedY;

		// Handles Jumping
		if (jumping == true) {
			speedY++;

		}

		// Prevents from going out of the screen
		if (centerX + speedX < 60) {
			centerX = 60;
		}
		r1.setRect(centerX - 34, centerY - 63	, 68, 63);
		r2.setRect(r1.getX(), r1.getY() + 63, 68, 64);

	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isDucking() {
		return ducking;
	}

	public void setDucking(boolean ducking) {
		this.ducking = ducking;
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public void moveRight() {
		speedX = MOVESPEED;
	}

	public void moveLeft() {
		speedX = -MOVESPEED;
	}

	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

	public void stop() {
		if (isMovingRight() == false && isMovingLeft() == false)
			speedX = 0;
		else if (isMovingRight() == false && isMovingLeft() == true)
			moveLeft();
		else if (isMovingRight() == true && isMovingLeft() == false)
			moveRight();

	}

	public void jump() {
		if (jumping == false) {
			speedY = JUMPSPEED;
			jumping = true;
		}
	}

	public void shoot() {
		if (readyToFire) {
			Projectile p = new Projectile(centerX + 50, centerY - 25);
			projectiles.add(p);
		}
	}

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

}
