package puzzle;

import java.awt.image.BufferedImage;

public class ImageDetails{
	private int width;
	private int height;
	
	ImageDetails(BufferedImage img){
		width = img.getWidth(); 
		height = img.getHeight();
	}
	
	public int GetWidth(){return width;}
	public int GetHeight(){return height;}
}
