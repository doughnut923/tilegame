package dev.jonas.TileGame.Tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

	//STATIC STUFF
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grasstile = new grasstile(0); 
	public static Tile dirttile = new dirttile(1);
	public static Tile rocktile = new rocktile(2);                      //set the construstor of each tile and it will set the construsctor in this class    
	
	//CLASS
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id) {                        //after getting the id form the 'other-tiles' class
		this.texture = texture;                                         //get from the subclasses
		this.id = id;
		
		tiles[id] = this;                                               //this means the class that stored the tile
	}
	
	//NON STATIC(so can get called form different classes or tiles)
	
	public void tick() {
		
	}
	
	public void render(Graphics g , int x , int y) {
	    g.drawImage(texture, x , y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public int getId() {
		return id;
	}
	
}
