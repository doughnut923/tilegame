package dev.jonas.TileGame.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Util {
    
	public static String loadFileAsStirng(String path) {
		StringBuilder builder = new StringBuilder();          //a built in class that cane make you put things in  a stiring easily
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null){
				builder.append(line + "\n");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}
	
}
