package dev.jonas.TileGame.Entity.Creature;

import dev.jonas.TileGame.Handler;
import dev.jonas.TileGame.Entity.Entity;
import dev.jonas.TileGame.Tile.Tile;

public abstract class Creature extends Entity{

	public static final float DEFAULT_SPEED = 4.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64 , DEFAULT_CREATURE_HEIGHT = 64;
	
	protected float speed;
	protected float xMove, yMove;
	
	
	public Creature(Handler handler, int width ,int height ,float x, float y) {
		super(handler, width, height, x, y);
		speed = DEFAULT_SPEED;
		xMove = 0;                    
		yMove = 0;                                         //the numbers of units that the object move in terms of x and y
	}
	
	public void move() {
		if(!checkEntityCollisions(xMove, 0f))
			movex();
		if(!checkEntityCollisions(0f, yMove))
			movey();     		                                       //and or subtract the radius that moves
	}
	
	public void movex() {
		if(xMove > 0) {           //moving right		
			int tx = (int) (x + xMove + bounds.x + bounds.width) /Tile.TILEWIDTH; //this is the x position of the top left corner
			
			if(!collisionWithTile(tx , (int) (y + bounds.y) / Tile.TILEHEIGHT /* the y position of the top right corner*/) 
					&& !collisionWithTile(tx, (int)(y + bounds.height) / Tile.TILEHEIGHT /* the y position of the bottom right corner*/)) {
				
				x += xMove;
			}else {  //perfect collision detection.
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;  //let it stick to the Tile
			}
			
		}else if(xMove < 0) {     //moving left
			int tx = (int) (x + xMove + bounds.x) /Tile.TILEWIDTH; //the x position of the top left corner
			
			if(!collisionWithTile(tx , (int) (y + bounds.y) / Tile.TILEWIDTH /*The y position of the top left corner */)
					&& !collisionWithTile(tx, (int)(y + bounds.height) / Tile.TILEHEIGHT /*The y position of the bottom left corner */)) {
				
				x += xMove;
			}
			else {
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
			}
		}
	}
	public void movey() {
		if(yMove < 0) {           //moving up		
			int ty = (int) (y + yMove + bounds.y) /Tile.TILEHEIGHT; //the y position of the top right corner
			
			if(!collisionWithTile ((int) (x + bounds.x) / Tile.TILEWIDTH, ty) 
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				
				y += yMove;
			}else {
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
			}
			
		}else if(yMove > 0) {     //moving down
			int ty = (int) (y + yMove + bounds.y + bounds.getHeight()) /Tile.TILEHEIGHT; //the y position of the top left corner
			
			if(!collisionWithTile ((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			}else {
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1	;		}
			
		}
	}
	
	protected boolean collisionWithTile(int x , int y) {     //check if the tile is solid
		return handler.getWorld().getTile(x, y).isSolid();   //returns the solid state of the tile in the isSolid method
	}
	
	//JUST GETTERS AND SETTERS

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	//That help the class for getting these data.
}
