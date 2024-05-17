package dev.jonas.TileGame.Entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.jonas.TileGame.Handler;

public abstract class Entity {
    
	protected boolean damaged;
	protected Handler handler;
	protected float x , y;
	public static final int DEFAULT_HEALTH = 5;
	protected int width , height;                          //this is the size of the entity
	protected int health;
    protected boolean active = true;                       //dead or non dead
	protected Rectangle bounds;
	
	
	public Entity(Handler handler, int width , int height, float x , float y) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		health = DEFAULT_HEALTH;
		this.width = width;
		this.height = height;                              //set the entities w, h, x, y
		
		bounds = new Rectangle(0 , 0, width, height);      //create the bound rectangle object
	}
	
	//collision detection with creatures
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entity e : handler.getWorld().getEntitymannager().getEntities()) {
			if(e.equals(this)) {
				continue;                                  //if the entity that we was checking is true, skip these
			}
			
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) { return true; }  //if the creature intersects with this. return true
		}
		return false;                                      //else return false
	}
    
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x +  xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);     //gets the position of the object
	}
	
	public void damage(int amt) {
		health -= amt;
		setDamaged(true);                                  //change the state in of the entity to is damaged and this can let the animation class get the state of the entity
		if(health <= 0) {
			active = false;
			die();
		}
	}

	public abstract void die();
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isDamaged() {
		if(damaged) {
			return true;
		}
		return false;
	}

	public void setDamaged(boolean damaged) {
		this.damaged = damaged;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

	
	
}
