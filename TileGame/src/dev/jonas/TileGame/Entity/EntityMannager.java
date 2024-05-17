package dev.jonas.TileGame.Entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import dev.jonas.TileGame.Handler;
import dev.jonas.TileGame.Entity.Creature.player;

public class EntityMannager {
	
	private Handler handler;
	private player player;
	
	private ArrayList <Entity> entities;
	
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {

		@Override
		public int compare(Entity a, Entity b) {
			if(a.getY()+ a.getHeight() < b.getY() + b.getWidth())   //this is the rendering order, so it checks if the bottom of the entity is bigger				
				return -1;
			return 1;			
		}
		
	};
	
    public EntityMannager(Handler handler, player player) {
    	this.setHandler(handler);                          //set the player and handler
    	this.setPlayer(player); 	
    	
    	setEntities(new ArrayList<Entity>());              //set a new arraylist
    	addEntity(player);                                 //add a player entity
    }
    
    public void tick() {
    	Iterator<Entity> it = entities.iterator();
    	while(it.hasNext()) {
    		Entity e = it.next();
    		e.tick();
    		if(!e.isActive()) {
    			it.remove();
    		}
    	}
    	entities.sort(renderSorter);                       //can sort the entities into order by the front and the behind thingy
    }
    
    public void render(Graphics g) {
    	for(Entity e : entities){
    		e.render(g);
    	}
    }
    
    public void addEntity(Entity e) {
    	entities.add(e);
    }
    
    //GETTERS AND SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public player getPlayer() {
		return player;
	}

	public void setPlayer(player player) {
		this.player = player;
	}

	public ArrayList <Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList <Entity> entities) {
		this.entities = entities;
	}
}
