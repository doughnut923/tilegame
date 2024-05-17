package dev.jonas.TileGame.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import dev.jonas.TileGame.ui.UIManager;

public class MouseMannager implements MouseListener, MouseMotionListener{

	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	private UIManager uimanager;
	
	public MouseMannager() {
		
	}
	
	public void setUIManager(UIManager um) {
		this.uimanager = um;
	}
	
	//getters
	
	public boolean isLeftPressed() {
		return leftPressed;
	}
	public boolean isRightPressed() {
		return rightPressed;
	}
	public int getMouseX() {
		return mouseX;
	}
	public int getMouseY() {
		return mouseY;
	}
	
	//Implemented method
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {               //left mouse button is been released
			leftPressed = false;
		}
		if(e.getButton() == MouseEvent.BUTTON3) {               //right mouse button is been realeased
			rightPressed = false;
		}
		if(!(uimanager == null)) {
			uimanager.onMouseRealease(e);                      //when mouse released calls the mouse release method in the ui mannager
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {               //left mouse button is been clicked
			leftPressed = true;
		}
		if(e.getButton() == MouseEvent.BUTTON3) {               //right mouse button is been clicked
			rightPressed = true;
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
	    mouseY = e.getY();
	    
	    if(!(uimanager == null)) {
			uimanager.onMouseMove(e);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
    
}
