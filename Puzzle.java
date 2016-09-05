import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class Puzzle {
	public class ImageDetails{
		private int width;
		private int height;
		public int GetWidth(BufferedImage img){return img.getWidth();}
		public int GetHeight(BufferedImage img){return img.getHeight();}
	}
	static BufferedImage readImage(){
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("cat.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not read Image");
		}
		return img;		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedImage img = readImage();
		Graphics graph = img.getGraphics();
		graph.drawImage(img, 0, 0, null);
		
	}

}
