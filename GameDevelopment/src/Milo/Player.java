package Milo;

public class Player {

	final int JUMPSPEED = -15;
	final int GROUND = 382;
	final int MOVESPEED = 5;

	private int centerX = 100;
	private int centerY = 382;
	private boolean jumping = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducking = false;

	private int speedX;
	private int speedY;

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

		// Updates Y Position
		if (centerY + speedY > 382) {
			centerY = 382;
		} else {
			centerY += speedY;
		}

		// Handles Jumping
		if (jumping == true) {
			speedY++;
			if (centerY + speedY > 382) {
				centerY = 382;
				speedY = 0;
				jumping = false;
			}
		}

		// Prevents from going out of the screen
		if (centerX + speedX < 60) {
			centerX = 60;
		}

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

}
