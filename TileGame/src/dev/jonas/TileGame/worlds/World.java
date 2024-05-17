package dev.jonas.TileGame.worlds;

import java.awt.Graphics;

import dev.jonas.TileGame.Handler;
import dev.jonas.TileGame.Entity.EntityMannager;
import dev.jonas.TileGame.Entity.Creature.player;
import dev.jonas.TileGame.Entity.statics.Rock;
import dev.jonas.TileGame.Entity.statics.Tree;
import dev.jonas.TileGame.Item.ItemMannager;
import dev.jonas.TileGame.Tile.Tile;
import dev.jonas.TileGame.Util.Util;

public class World {
    
	

	private Handler handler;
	private int width, height;  //how many tiles by how many tiles
    private int spawnx, spawny;
	private int[][] tiles;      //the position of each tile that store an id  
	
	//Items
	
	private ItemMannager itemManager;
	
	//Entities
	
	private EntityMannager entitymanager;
	
	public World(Handler handler, String path) {
		this.handler = handler;
		entitymanager = new EntityMannager(handler, new player(handler, 100, 100));       //add a new player object
		itemManager = new ItemMannager(handler);
		
		//The statics
		entitymanager.addEntity(new Tree(handler, 100, 300));      //add a new tree object
		entitymanager.addEntity(new Tree(handler, 500, 300));      //add a new tree object
		entitymanager.addEntity(new Tree(handler, 400, 400));      //add a new tree object
		entitymanager.addEntity(new Rock(handler, 700, 600));      //add a new tree object
		entitymanager.addEntity(new Rock(handler, 1300, 300));      //add a new tree object
		entitymanager.addEntity(new Rock(handler, 1000, 500));      //add a new tree object
		entitymanager.addEntity(new Rock(handler, 100, 600));      //add a new tree object
		
		loadWorld(path);
		
		entitymanager.getPlayer().setX(spawnx);
		entitymanager.getPlayer().setY(spawny);            //sets the spawn location of the player
	}
	
	public void tick() {
		itemManager.tick();
		entitymanager.tick();
	}
	
	public void render(Graphics g) {
		int xstart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH),
		xend = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1),
		ystart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT),
		yend = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		//these can increase the rendering efficiency by only rendering the part that the entity sees
		
		for(int y = ystart; y < yend; y++) {
			for(int x = xstart; x < xend; x++) {      //only load the start and end on the screen
                getTile(x, y).render(g,(int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset())
                		,(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));  				
			    //x and y multiplys the tile's w / h and substract it with by the camera offset
			}
		}
		//items
		itemManager.render(g);
		//entities
		entitymanager.render(g);
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x == width || y == height) {
			return Tile.grasstile;
		}
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null) {
			return Tile.dirttile;                                                   //if the id equals to the id besides than the tile that we made, return the dirt tile
		}
		return t;
	}
	
	private void loadWorld(String path) {
		String file = Util.loadFileAsStirng(path);
		String[] tokens = file.split("\\s+");
		width = Util.parseInt(tokens[0]);                                           //this method can convert string into int.
		height = Util.parseInt(tokens[1]);
		setSpawnx(Util.parseInt(tokens[2]));
		setSpawny(Util.parseInt(tokens[3]));                                        //^
		                                                                            //| this
		tiles = new int[width][height];                                             //|
		for(int y = 0; y < height; y++) {                                           //|
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Util.parseInt(tokens[(x + y * width) + 4]);           //4 is the variables that we set in the beginning
			}
		}
		
	}
	
	public EntityMannager getEntitymannager() {
		return entitymanager;
	}

	public int getSpawnx() {
		return spawnx;
	}

	public void setSpawnx(int spawnx) {
		this.spawnx = spawnx;
	}

	public int getSpawny() {
		return spawny;
	}

	public void setSpawny(int spawny) {
		this.spawny = spawny;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;		
	}

	public Handler getHandler() {
		return handler;
	}

	public ItemMannager getItemManager() {
		return itemManager;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public void setItemManager(ItemMannager itemManager) {
		this.itemManager = itemManager;
	}
	
}
