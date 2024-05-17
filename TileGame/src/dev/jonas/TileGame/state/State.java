package dev.jonas.TileGame.state;

import java.awt.Graphics;

import dev.jonas.TileGame.Game;
import dev.jonas.TileGame.Handler;

public abstract class State {  
    
	private static State currentGameState = null;
	
	protected Handler handler;
	
	public static void setGameState(State state) {
		currentGameState = state;
	}
	
	public static State getGameState() {
		return currentGameState;
	}		
	public State(Handler handler) {
		this.handler = handler;
	}
	
	//CLASS
	public abstract void tick();
	
	public abstract void render(Graphics g);
		
}
	

