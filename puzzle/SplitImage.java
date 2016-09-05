package puzzle;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class SplitImage{
	private ArrayList<ImageIcon> imgIcons = new ArrayList<ImageIcon>();
	private int numRows, numCols;

	//constructor
	SplitImage(BufferedImage img){
		//get dimensions
		ImageDetails det = new ImageDetails(img);
		int width = det.GetWidth();
		int height = det.GetHeight();

		numRows = 3;
		numCols = 3;
		
		//create chunk dimensions
		int chunkWidth = width / numCols;
		int chunkHeight = height / numRows;
		
		//Splitting the image into blocks and adding blocks into array of imageIcons
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				
				if(row == 0 && col == 0){
					//Set top left block to white
					BufferedImage whiteBlock = new BufferedImage(chunkWidth, chunkHeight, BufferedImage.TYPE_INT_ARGB);
					Color White = new Color(255, 255, 255); 
					int rgb = White.getRGB();

					for(int r = 0; r < chunkHeight; r++){
						for(int c = 0; c < chunkWidth; c++){
							whiteBlock.setRGB(r, c, rgb);
						}
					}
					
					imgIcons.add(new ImageIcon(whiteBlock));
				}
				
				else{
					imgIcons.add(new ImageIcon(img.getSubimage(col*chunkWidth, row*chunkHeight, chunkWidth, chunkHeight)));
				}
			}
		}
	}

	public ArrayList<ImageIcon> getImgIcons(){return imgIcons;}
	
	public int getNumRows(){return numRows;}
	public int getNumCols(){return numCols;}
}
