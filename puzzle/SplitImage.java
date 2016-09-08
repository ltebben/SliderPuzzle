package puzzle;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;


public class SplitImage{
	private ArrayList<ImageIcon> imgIcons = new ArrayList<ImageIcon>();
	private ArrayList<ImageIcon> inOrder = new ArrayList<ImageIcon>();
	private int numRows, numCols;
	private int whiteCode;
	private int whitePos;

	//constructor
	SplitImage(BufferedImage img, int size){
		//get dimensions
		ImageDetails det = new ImageDetails(img);
		int width = det.GetWidth();
		int height = det.GetHeight();

		numRows = size;
		numCols = size;

		//create chunk dimensions
		int chunkWidth = width / numCols;
		int chunkHeight = height / numRows;

		//Splitting the image into blocks and adding blocks into array of imageIcons
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {

				if(row == numRows-1 && col == numCols-1) {
					//Set bottom right block to white
					BufferedImage whiteBlock = new BufferedImage(chunkWidth, chunkHeight, BufferedImage.TYPE_INT_ARGB);
					Color White = new Color(255, 255, 255); 
					int rgb = White.getRGB();
					
					for (int r = height-chunkHeight; r < chunkHeight; r++) {
						for (int c = width-chunkWidth; c < chunkWidth; c++) {
							whiteBlock.setRGB(c, r, rgb);
						}
					}
					
					ImageIcon wb = new ImageIcon(whiteBlock);
					whiteCode = wb.hashCode();
					imgIcons.add(wb);
				}
				else {
					imgIcons.add(new ImageIcon(img.getSubimage(col*chunkWidth, row*chunkHeight, chunkWidth, chunkHeight)));
				}
			}
		}
		//creates copy of ordered array to check win condition
		for(ImageIcon e: imgIcons){
			inOrder.add(e);
		}
		
		//shuffles icons
		ImageIcon whiteImage = imgIcons.remove(imgIcons.size() - 1);
		Collections.shuffle(imgIcons);
		imgIcons.add(whiteImage);
		whitePos = imgIcons.size()-1;
	}

	public boolean swapTiles(ImageIcon Icon1){
		int right=-1;
		int left=-1;
		int above=-1;
		int below=-1;
		for(int i=0; i<imgIcons.size();i++){
			int ghash = imgIcons.get(i).hashCode();
			int ihash = Icon1.hashCode();
			if(ghash == ihash){
				double x = i;
				if((i<(imgIcons.size())-1) && ((x+1)/numCols)!=1){right = i+1;}
				if((i>0) && x/numCols!=1){left = i-1;}
				if(i-numCols>=0){above = i-numCols;}
				if(i+numCols<(imgIcons.size())){below = i+numCols;}

				if(right == whitePos || left==whitePos || above==whitePos || below==whitePos){
					Collections.swap(imgIcons, i, whitePos);
					whitePos = i;
					return true;
				}
			}
		}
		return false;
	}

	public boolean checkWin(){
		if(imgIcons.equals(inOrder)){
			return true;
		}
		return false;
	}

	public ArrayList<ImageIcon> getImgIcons(){return imgIcons;}

	public int getNumRows(){return numRows;}
	public int getNumCols(){return numCols;}
	public int getWhiteCode(){return whiteCode;}
}
