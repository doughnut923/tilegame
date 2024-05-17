package dev.jonas.TileGame.state;

import java.awt.Graphics;

import dev.jonas.TileGame.Handler;
import dev.jonas.TileGame.gfx.Assets;
import dev.jonas.TileGame.input.MouseMannager;
import dev.jonas.TileGame.ui.ClickListener;
import dev.jonas.TileGame.ui.UIImageButton;
import dev.jonas.TileGame.ui.UIManager;

public class MenuState extends State{

	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		((MouseMannager) handler.getMouseMannager()).setUIManager(uiManager);                                   
		
		uiManager.addObject(new UIImageButton(230, 250, 128, 64, Assets.button_start, new ClickListener() {    //add the button object
			@Override
			public void onClick() {
				State.setGameState(handler.getGame().GameState);                                               //set the action when on click
			}
		}));
	}
	@Override
	public void tick() {
	    uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}

}
