package Milo.framework;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Random;

public class TileMap extends Applet implements MouseWheelListener {

	static int[][] tilemap;
	static int rows, columns;
	int r, g, b;
	Image img;
	int size=2;
	Graphics secondary;
	
	long nextSecond = System.currentTimeMillis() + 1000;
	int frameInLastSecond = 0;
	int framesInCurrentSecond = 0;

	@Override
	public void init() {
		setSize(800,600);
		setBackground(Color.BLACK);
		addMouseWheelListener(this);
		setFocusable(true);
		
		createTilemap();

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
		
		g.setColor(Color.white);
		g.drawLine(400,0,400,600);
		g.drawLine(0,300,800,300);
		g.setColor(Color.red);
		
		float x,y=0;
		for(x=-400;x<400;x+=0.1){
			
			
		//Equation
			
			float deg = (float) (x*3.141/180);
			y= (float) (Math.cos(deg*Math.PI/4*r)*100);
			y=-y;
			
		
		g.drawLine((int)x+400,(int)y+300,(int)x+400,(int)y+300);
		}

	}

	private void createTilemap() {

		tilemap = new int[256][256];

		rows = tilemap.length;
		columns = tilemap[49].length;

		Random r = new Random();

		for (int i = 1; i < rows; i++) {
			for (int j = 1; j < columns; j++) {
				tilemap[i][j] = ((j * j / i) % i) % 5;
			}
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		r += e.getWheelRotation() ;
		System.out.println("R = " + r);
		repaint();

	}

}




