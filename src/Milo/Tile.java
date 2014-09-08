package Milo;

import java.awt.Image;

public class Tile {

	private int tileX, tileY;
	private int speedX;
	private int type;
	Image tileImage;

	private Player robot = GameEngine.getRobot();
	Background bg = GameEngine.getBg1();

	public Tile(int x, int y, int type) {
		this.tileX = x * 40;
		this.tileY = y * 40;
		this.type = type;
		 if (type == 5) {
	            tileImage = GameEngine.tiledirt;
	        } else if (type == 8) {
	            tileImage = GameEngine.tilegrassTop;
	        } else if (type == 4) {
	            tileImage = GameEngine.tilegrassLeft;

	        } else if (type == 6) {
	            tileImage = GameEngine.tilegrassRight;

	        } else if (type == 2) {
	            tileImage = GameEngine.tilegrassBot;
	        }
		
	}

	public void update() {
		speedX = bg.getSpeedX()*2;
        tileX += speedX;
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

}
