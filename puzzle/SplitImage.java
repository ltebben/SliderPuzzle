package puzzle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;


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
