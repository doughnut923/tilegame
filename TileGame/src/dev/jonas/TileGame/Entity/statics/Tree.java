package dev.jonas.TileGame.Entity.statics;

import java.awt.Graphics;

import dev.jonas.TileGame.Handler;
import dev.jonas.TileGame.Item.Item;
import dev.jonas.TileGame.Tile.Tile;
import dev.jonas.TileGame.gfx.Animation;
import dev.jonas.TileGame.gfx.Assets;

public class Tree extends StaticEntity {
	
	Animation anidamaged = new Animation(this, 0, Assets.tree);
	
	public Tree(Handler handler, float x, float y) {
		super(handler, Tile.TILEWIDTH, Tile.TILEHEIGHT , x, y);

		bounds.x = 20;
		bounds.y = 30;
		bounds.width = 20;
		bounds.height = 10;                               //set the collision boarder of the a tree
	}

	@Override
	public void tick() {
		anidamaged.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree[anidamaged.getHurted()], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		damaged = false;
	}

	@Override
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) (x + Tile.TILEWIDTH / 3), (int) (y + Tile.TILEHEIGHT / 3)));
	}
    
}
