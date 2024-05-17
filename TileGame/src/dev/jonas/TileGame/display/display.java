package dev.jonas.TileGame.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class display {
    
	private JFrame frame; 
	private Canvas canvas;                                        //the place to draw graphics
	
	private String title;
	private int width , height;
	
	public display(String title , int width , int height) {
		this.title = title; 
		this.width = width; 
		this.height = height;       
		
		createDisplay();
	    
		System.out.println("(!) Windows created!");
	}
	//use this keyword to let the computer this is the real thing
	
	private void createDisplay(){
		frame = new JFrame(title);                                //give the JFrame a title
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);                        //set the windows in the middle of the screen
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width , height));
		canvas.setMaximumSize(new Dimension(width , height));
		canvas.setMinimumSize(new Dimension(width , height));     //set the size of the canvas
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();                                             //resize the frame size to see the canvas
	}
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;		
	}
}
