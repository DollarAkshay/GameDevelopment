package Milo;

public class Projectile {

	private int x,y;
	private int speedX;
	private boolean visible = false;
	
	
	public Projectile(int startX, int startY) {
		x=startX;
		y=startY;
		speedX=7;
		visible = true;
	}

	public void update(){
		x+=speedX;
		if(x>1200)
			visible=false;
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

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
}
