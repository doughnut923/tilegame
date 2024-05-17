package dev.jonas.TileGame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyMannager implements KeyListener {

	private boolean[] keys;
    public boolean up, down, right, left;
    public boolean aUp, aDown, aRight, aLeft;
	
	public KeyMannager() {
		keys = new boolean[256];
	}
	public void tick() {
		up = keys[KeyEvent.VK_W];            
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];         
		
		aUp = keys[KeyEvent.VK_UP];
		aDown = keys[KeyEvent.VK_DOWN];
		aLeft = keys[KeyEvent.VK_LEFT];
		aRight = keys[KeyEvent.VK_RIGHT];    //these can get the key codes of each key
	}
	
	@Override
	public void keyTyped(KeyEvent e) {       //these method are constantly enabled
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

}
