package puzzle;

import java.awt.image.BufferedImage;

public class ImageDetails{
	ImageDetails(BufferedImage img){width = img.getWidth(); height = img.getHeight();}
	private int width;
	private int height;
	public int GetWidth(){return width;}
	public int GetHeight(){return height;}
}
