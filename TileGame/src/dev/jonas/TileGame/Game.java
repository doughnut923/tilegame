package dev.jonas.TileGame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.jonas.TileGame.display.display;
import dev.jonas.TileGame.gfx.Assets;
import dev.jonas.TileGame.gfx.GameCamera;
import dev.jonas.TileGame.input.KeyMannager;
import dev.jonas.TileGame.input.MouseMannager;
import dev.jonas.TileGame.state.GameState;
import dev.jonas.TileGame.state.MenuState;
import dev.jonas.TileGame.state.State;

public class Game implements Runnable{               //this is the main part of the game
	
	private display display;
	
	private int width, height;
	private String title;                          
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;                       //the way that the computer draw things to the screen
	private Graphics g;
	
	//STATE
	public State GameState;
	public State MenuState;
	
	private KeyMannager keymannager;
	private MouseMannager mousemannager;
	
	//CAMERA 
	private GameCamera gamecamera;
	private Handler handler;
	
	
    public Game(String title , int width , int height) {
    	this.width = width;
    	this.height = height;                        
    	this.title = title;                          //specify the width and height
    	keymannager = new KeyMannager();	
    	mousemannager = new MouseMannager();
    }
    
    private void render() {                          //the display change
		bs = display.getCanvas().getBufferStrategy();//buffer means a 'hidden' comp screen within your comp, can prevent flickering screen
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}                                            //if the canvas don't have a buffer strategy, then make one
		g = bs.getDrawGraphics();                    //let the graphics know the buffer stratagy
		
		g.clearRect(0, 0, width, height);            //you just need to do this
		
		if(State.getGameState() != null) {
       	    State.getGameState().render(g);          //render by the GameState class's tick method(sub class)
        }
		
		                                             //draw the image
		bs.show();                                   
		g.dispose();                                 //show graphics
		
	}

	private void tick() {       		
		keymannager.tick();
		
        if(State.getGameState() != null) {
        	State.getGameState().tick();
        }
	}
	private void init() {                            //initialize the things(let the game know there is a thing)			
		display = new display(title, width, height); 
		display.getFrame().addKeyListener(keymannager); //add the inputs into the display and canvas
		display.getFrame().addMouseListener(mousemannager);
		display.getFrame().addMouseMotionListener(mousemannager);
		display.getCanvas().addMouseListener(mousemannager);
		display.getCanvas().addMouseMotionListener(mousemannager);
		Assets.init();
		
		handler = new Handler(this);
		
		GameState = new GameState(handler);
		MenuState = new MenuState(handler);
		State.setGameState(MenuState); 
		
		gamecamera = new GameCamera(handler, 0, 0);  		
		
	}
	
	public void run() {                              //the main main game code		
		init();	
		
		int fps = 60;                                //fermat per second
		double timePerTick = 1000000000 / fps;       //1 billion nano sec = 1 sec
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		System.out.println("(!) Game running!");
		
		while(running) {                             //the game loop{set VAR , render}	
			now = System.nanoTime();                 //get computer's time by nano seconds
			delta += (now - lastTime) / timePerTick; //delta handles lag of slow computer, in dictionary means extra.
			timer += now - lastTime;                 //The amount of nano sec past
			lastTime = now;                          
			if(delta >= 1) {                         
				tick();
				render();	
				ticks++;
				delta--;
			}
			if(timer >= 1000000000) {                //each one second, print out how many ticks are in one second
				ticks = 0;
				timer = 0; 
			}
		}		
		stop();
	}
   
	public synchronized void start() { 
		if(running) {
			return;                                  //if it's already running then stop
		}
		running = true;                           
		thread = new Thread(this);
		thread.start();                              //call run method
				
	}
	
	//GETTER AND SETTERS
	public KeyMannager getKeyMannager() {
		return keymannager;                           //just a method to get the keymannager
	}
	public MouseMannager getMouseMannager() {
		return mousemannager;	
	}
	public GameCamera getGameCamera() {
		return gamecamera;
	}
	public int getwidth() {
		return width;
	}
	public int getheight() {
		return height;
	}
	
    public synchronized void stop() {
    	if(!running)
    		return;
    	running = false;
		try { 
			thread.join();                           //to close the game in a safe way
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
