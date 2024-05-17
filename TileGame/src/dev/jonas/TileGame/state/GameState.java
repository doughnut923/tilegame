package dev.jonas.TileGame.state;

import java.awt.Graphics;

import dev.jonas.TileGame.Game;
import dev.jonas.TileGame.Handler;
import dev.jonas.TileGame.Entity.Creature.player;
import dev.jonas.TileGame.Entity.statics.Tree;
import dev.jonas.TileGame.worlds.World;

public class GameState extends State {           //this class will render all the stuff including creatures and tiles
    
	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/world/world1.txt");
		handler.setWorld(world);
		
	}
	
	@Override
	public void tick() {
		world.tick();                           //calls the tick method in the player class that can render set all the variables there and render it by the render method 
	} 

	@Override
	public void render(Graphics g) {
		world.render(g);
	}

}
