package dev.jonas.TileGame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static final int width = 30, height = 30;
	
	public static BufferedImage dirt , grass, stone;
	public static BufferedImage wood, rockPiece;
	public static BufferedImage[] player_down, player_up , player_left, player_right, player_still;
	public static BufferedImage[] tree, rock;
	public static BufferedImage[] button_start;
	
    public static void init() {  
    	spriteSheet sheet = new spriteSheet(ImageLoader.loadImage("/textures/sprite.png"));
    	
    	player_down = new BufferedImage[2];
    	player_up = new BufferedImage[2];
    	player_left = new BufferedImage[2];
    	player_right = new BufferedImage[2];
    	player_still = new BufferedImage[2];                    //the frames of the animation
    	button_start = new BufferedImage[2];
    	rock = new BufferedImage[2];
    	tree = new BufferedImage[2];

        player_down[0] = sheet.crop(1, 1, width, height);
        player_down[1] = sheet.crop(33, 1, width, height);
        player_up[0] = sheet.crop(65, 1, width, height);
        player_up[1] = sheet.crop(97, 1, width, height);
        player_left[0] = sheet.crop(65, 33, width, height);
        player_left[1] = sheet.crop(97, 33, width, height);
        player_right[0] = sheet.crop(1, 33, width, height);
        player_right[1] = sheet.crop(33, 33, width, height);
        player_still[0] = sheet.crop(65, 65, width, height);
        player_still[1] = sheet.crop(97, 65, width, height);    //set animation of each frame
        
        button_start[0] = sheet.crop(65, 96, width * 2, height);
        button_start[1] = sheet.crop(65, 128, width * 2, height);
       
    	grass = sheet.crop(128, 2, width, height);
    	dirt = sheet.crop(128 , 65, width, height);
    	stone = sheet.crop(128, 33, width, height);
    	
    	tree[0] = sheet.crop(33, 65, width, height);
    	tree[1] = sheet.crop(33, 96, width, height);
    	rock[0] = sheet.crop(1, 65, width, height);
    	rock[1] = sheet.crop(1, 96, width, height);
    	
    	wood = sheet.crop(33, 128, width, height);
    	rockPiece = sheet.crop(1, 128, width, height);
    }
    
}
