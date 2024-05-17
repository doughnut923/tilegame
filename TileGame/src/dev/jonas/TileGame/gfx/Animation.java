package dev.jonas.TileGame.gfx;

import java.awt.image.BufferedImage;

import dev.jonas.TileGame.Entity.Entity;
import dev.jonas.TileGame.Entity.Creature.player;
import dev.jonas.TileGame.Entity.statics.StaticEntity;

public class Animation {
	
	private int hurted;
	
    private int speed, index;                              //the speed of frame changing
    private long lastTime, timer;                          //the timer that supports the speed
	private BufferedImage[] frames;
	private Entity e;
	
    public Animation(Entity e, int speed, BufferedImage[] frames){
    	this.speed = speed;
    	this.frames = frames;
    	this.e = e;
    	index = 0;
    	timer = 0;
    	lastTime = System.currentTimeMillis();             //1 sec = 1000 mini sec
    }
    
    public void tick() {
    	if(e instanceof StaticEntity) {                       //if it is a static entity
    		staticTick();
    		return;
    	}
    	timer += System.currentTimeMillis() - lastTime;   
    	lastTime = System.currentTimeMillis();
    	
    	if(timer > speed) {	

    		index++;                                       //changes the frame
    		timer = 0;
    		if(index >= frames.length){
    			index = 0;                                 //loops the frame
    		}
    	}
    }
    
    public void staticTick() {
    	if(!(e instanceof player)) {                       //return the animation number of that entity
			if(e.isDamaged()) {
				hurted = 1;
			}else {
				hurted = 0;
			}	
		}
    }
    
    public BufferedImage getCurrentFrame() {
    	return frames[index];                              //returns the current running frame form the tick method
    }

	public int getHurted() {
		return hurted;
	}

	public void setHurted(int hurted) {
		this.hurted = hurted;
	}
}
