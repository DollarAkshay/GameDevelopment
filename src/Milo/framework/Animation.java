package Milo.framework;

import java.awt.Image;
import java.util.ArrayList;

public class Animation {

	private ArrayList frames;
	private int currentFrame;
	private long animTime;
	private int duration;

	public Animation() {
		frames = new ArrayList();
		duration = 0;
		synchronized (this) {
			animTime = 0;
			currentFrame = 0;
		}
	}
	
	public synchronized void update(long time){
		if(frames.size()>1){
			animTime+=time;
			if(animTime>=duration){
				animTime%=duration;
				currentFrame=0;
			}
			
			while(animTime>getFrame(currentFrame).endTime){
				currentFrame++;
			}
		}
	}
	
	public synchronized void addFrame(Image img,long time){
		duration += time;
		frames.add(new AnimFrame(img, duration));
	}
	
	public synchronized Image getImage(){
		if(frames.size()==0)
			return null;
		else
			return getFrame(currentFrame).image;
	}
	
	private AnimFrame getFrame(int index){
		return (AnimFrame) frames.get(index);
	}

	
	//AnimFrame Class
	private class AnimFrame{
		
		Image image;
		long endTime;
		
		public AnimFrame(Image i,long time){
			this.image=i;
			this.endTime=time;
		}
	}
}








