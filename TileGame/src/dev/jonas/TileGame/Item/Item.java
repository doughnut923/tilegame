package dev.jonas.TileGame.Item;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.jonas.TileGame.Handler;
import dev.jonas.TileGame.gfx.Assets;

public class Item {
	
	//HANDLER
	
	public static Item[] items = new Item[256];
	public static Item woodItem = new Item(Assets.wood, "Wood", 0);
	public static Item rockItem = new Item(Assets.rockPiece, "Rock", 1);
	
	//CLASS
	
	public static final int ITEMWIDTH = 64, ITEMHEIGHT = 64, PICKED_UP = -1;
	
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final int id;
	
	protected int x,
	y, 
	count;       //amount of items in hand
	
	public Item(BufferedImage texture, String name, int id) {
		this.texture = texture;
		this.name = name;
		this.id = id;
		count = 1;
		
		items[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(handler == null) {
			return;
		}
		render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
		//stay the texture in position
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
	}
	
	public Item createNew(int x, int y) {
		Item item = new Item(texture, name, id);
		item.setPosition(x, y);
		return item;
	}
	 
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//getters and setters

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}
	
}
