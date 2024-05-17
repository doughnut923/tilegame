package dev.jonas.TileGame.Entity.statics;

import dev.jonas.TileGame.Handler;
import dev.jonas.TileGame.Entity.Entity;

public abstract class StaticEntity extends Entity{              //things that don't move

	public StaticEntity(Handler handler, int width, int height, float x, float y) {
		super(handler, width, height, x, y);		
	}
	
}
