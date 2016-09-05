package puzzle;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

//import SliderPuzzleUI.java;

public class Puzzle {

	public class ImageDetails{
		ImageDetails(BufferedImage img){width = img.getWidth(); height = img.getHeight();}
		private int width;
		private int height;
		public int GetWidth(){return width;}
		public int GetHeight(){return height;}
	}


	public class SplitImage{
		public ArrayList<ImageIcon> imgIcons = new ArrayList<ImageIcon>();


		SplitImage(BufferedImage img){
			ImageDetails det = new ImageDetails(img);
			int width = det.GetWidth();
			int height = det.GetHeight();

			int numRows = 3;
			int numCols = 3;

			int chunkWidth = width / numCols;
			int chunkHeight = height / numRows;

			for (int row = 0; row < numRows; row++) {
				for (int col = 0; col < numCols; col++) {
					imgIcons.add(new ImageIcon(img.getSubimage(col*chunkWidth, row*chunkHeight, chunkWidth, chunkHeight)));

				}
			}
		}
		
		public ArrayList<ImageIcon> getImgIcons(){return imgIcons;}
		
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
