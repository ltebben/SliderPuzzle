package puzzle;

import java.awt.Color;
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
				
				if(row == 0 && col == 0){
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

}
