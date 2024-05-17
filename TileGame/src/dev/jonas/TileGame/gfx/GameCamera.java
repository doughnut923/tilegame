package dev.jonas.TileGame.gfx;

import dev.jonas.TileGame.Handler;
import dev.jonas.TileGame.Entity.Entity;
import dev.jonas.TileGame.Tile.Tile;

public class GameCamera {

	private Handler handler;
	private float xOffset, yOffset;                   //postition of the camera's left corner
	
	public GameCamera(Handler game, float xOffset, float yOffset) {
		this.handler = game;
		
		this.setxOffset(xOffset);
		this.setyOffset(yOffset);
	}
	
	public void checkBlankSpace() {
		if(xOffset < 0) {
			xOffset = 0;
		}
		
		if(yOffset < 0) {
			yOffset =0;
		}
		
		if(xOffset  > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()) {
			xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
		}
		
		if(yOffset  > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
			yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
		}
	}
	
	public void centreOnEntity(Entity e) {
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;            
	                                                  //the formula to get the camera to the entity
	    checkBlankSpace();
	}
	
	public void move(float xamt, float yamt) {	
		xOffset += xamt;
		yOffset += yamt;                              //set the camera by the entity's move amount
		
		checkBlankSpace();
	}

	//GETTERS AND SETTERS
	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}
	
}
