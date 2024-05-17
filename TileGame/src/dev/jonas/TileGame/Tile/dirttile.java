package dev.jonas.TileGame.Tile;

import dev.jonas.TileGame.gfx.Assets;

public class dirttile extends Tile{

	public dirttile(int id) {
		super(Assets.dirt, id);
	}
	
	public boolean isSolid() {
		return false;
	}
}
