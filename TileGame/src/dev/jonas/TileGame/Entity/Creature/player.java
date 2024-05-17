package dev.jonas.TileGame.Entity.Creature;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.jonas.TileGame.Handler;
import dev.jonas.TileGame.Entity.Entity;
import dev.jonas.TileGame.gfx.Animation;
import dev.jonas.TileGame.gfx.Assets;

public class player extends Creature{
		
	private Animation anidown, aniup, anileft, aniright, anistill;
	
	private int ARsize;
	
	//ATTACK TIMER
	private long lastAttackTimer, attackCooldown = 500, attackTimer = attackCooldown;
	
	public player(Handler handler, float x, float y) {
		super(handler, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT ,x , y); //this can get the construter variables from the super class to this class
	
	    bounds.x = 16;                   
	    bounds.y = 12;
	    bounds.width = 31;
	    bounds.height = 45;            //where to set the rectangle
	    
	    //Animations
	    anidown = new Animation(this, 200, Assets.player_down); //1000 mili sec in 1 sec
	    aniup = new Animation(this ,200, Assets.player_up);
	    anileft = new Animation(this, 200, Assets.player_left);
	    aniright = new Animation(this, 200, Assets.player_right);
	    anistill = new Animation (this, 500, Assets.player_still);
	    
	}

	@Override
	public void tick() {               //each entity, state etc. needs a tick and a render method to update the current state of the thing, like moving, button pressed...
	    //Animations
		anidown.tick();
		aniup.tick();
		aniright.tick();
		anileft.tick();
		anistill.tick();               //changes the picture of each animation
		//movement / display
		getInput();
	    move();                        //get and set the x and y form the getInput method
	    handler.getGameCamera().centreOnEntity(this);
	    
	    checkAttacks();
	                                   //centre on this entity
	}
	
	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		ARsize = 20;
		Rectangle collisionBounds = getCollisionBounds(0, 0);           //reset the rectangles or set the rectangles in the start
		Rectangle AR = new Rectangle();
		
		AR.width = ARsize;
		AR.height = ARsize;
		if(attackTimer < attackCooldown) {
			return;                                                     //if the cooldown is still going, return
		}
		
		//bla bla
		if(handler.getKeyMannager().aUp) {
			AR.x = collisionBounds.x + collisionBounds.width / 2 - ARsize / 2;
			AR.y = collisionBounds.y - ARsize;
		}else if(handler.getKeyMannager().aDown) {
			AR.x = collisionBounds.x + collisionBounds.width / 2 - ARsize / 2;
			AR.y = collisionBounds.y + collisionBounds.height;
		}
		else if(handler.getKeyMannager().aLeft) {
			AR.x = collisionBounds.x - ARsize;
			AR.y = collisionBounds.y + collisionBounds.height / 2 - ARsize / 2;
		}
		else if(handler.getKeyMannager().aRight) {
			AR.x = collisionBounds.x + collisionBounds.width;
			AR.y = collisionBounds.y + collisionBounds.height / 2 - ARsize / 2;
		}else {
			return;
		}
		
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntitymannager().getEntities()) {
			if(e.equals(this)) {                                                 //if it is comparing with the entity itself, skip this code
				continue;              
			}
			if(e.getCollisionBounds(0, 0).intersects(AR)) {
				e.damage(1);                                                     //damage the entity
				return;  
			}
		}
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;                     //reset the xMove and yMove
		
		if(handler.getKeyMannager().up) 
		    yMove = -speed;           
		if(handler.getKeyMannager().down) 
		    yMove = speed;
		if(handler.getKeyMannager().left) 
		    xMove = -speed;
		if(handler.getKeyMannager().right) 
		    xMove = speed;             //also simple coordinates
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()) , (int) (y - handler.getGameCamera().getyOffset()) , width, height, null); //draw the image by x & y and the width & height
		
//        g.setColor(Color.YELLOW);
//	    g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
//	    
	}
	
	@Override
	public void die() {
		System.out.println("You lose");
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(yMove < 0)                  //moving up
		    return aniup.getCurrentFrame();          
		if(yMove > 0)                  //moving down
		    return anidown.getCurrentFrame();
		if(xMove < 0)                  //moving left
		    return anileft.getCurrentFrame();
		if(xMove > 0)                  //moving right
		    return aniright.getCurrentFrame();
		
		return anistill.getCurrentFrame();
	}
//	if(yMove < 0) {                //moving up
//	    setAnilast(aniup.getCurrentFrame());
//	    return aniup.getCurrentFrame();          
//	}
//	if(yMove > 0) {                  //moving down
//	   setAnilast(anidown.getCurrentFrame()); 
//	   return anidown.getCurrentFrame();
//	}	
//	if(xMove < 0) {                  //moving left
//	    setAnilast(anileft.getCurrentFrame()); 
//	    return anileft.getCurrentFrame();
//		
//	}
//	if(xMove > 0) {                  //moving right
//	    setAnilast(aniright.getCurrentFrame());
//	    return aniright.getCurrentFrame();
//	}
//	return anilast;
}
