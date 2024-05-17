package dev.jonas.TileGame.Tile;

import dev.jonas.TileGame.gfx.Assets;

public class rocktile extends Tile{

	public rocktile(int id) {
		super(Assets.stone, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}
