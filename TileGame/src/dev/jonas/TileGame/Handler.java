package dev.jonas.TileGame;

import dev.jonas.TileGame.gfx.GameCamera;
import dev.jonas.TileGame.input.KeyMannager;
import dev.jonas.TileGame.input.MouseMannager;
import dev.jonas.TileGame.worlds.World;

public class Handler {
    
	private Game game;
	private World world;
	
	public Handler(Game game) {
    	 this.game = game;
    }

	//GETTERS AND SETTERS
	public MouseMannager getMouseMannager() {
		return game.getMouseMannager();
	}
	public KeyMannager getKeyMannager() {
		return game.getKeyMannager();
	}
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public int getHeight() {
		return game.getheight();
	}
	public int getWidth() {
		return game.getwidth();
	}
	
}
