package Milo;

public class Enemy {

	private int maxHealth, currHealth;
	private int centerX, centerY;
	private int speedX, speedY;
	private int damage;
	private Background bg = GameEngine.getBg1();

	public void update() {
		speedX = bg.getSpeedX();
		centerX += speedX;
	}

	public void die() {

	}

	public void attack() {

	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getCurrHealth() {
		return currHealth;
	}

	public void setCurrHealth(int currHealth) {
		this.currHealth = currHealth;
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

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public Background getBg() {
		return bg;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}
	
}
