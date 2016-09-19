package puzzle;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;


public class SplitImage{
	private static ArrayList<ImageIcon> imgIcons = new ArrayList<ImageIcon>();
	private static ArrayList<ImageIcon> inOrder = new ArrayList<ImageIcon>();
	private static int whitePos;
	private static int numCols = SliderPuzzleUI.size;
	private static int numRows = SliderPuzzleUI.size;

	//constructor
	static void create(BufferedImage img, int size){
		//gets dimensions of original image
		int width = img.getWidth();
		int height = img.getHeight();

		//creates chunk dimensions
		int chunkWidth = width / numCols;
		int chunkHeight = height / numRows;
		int counter = 1;
		ImageIcon temp;

		//Splits the image into blocks and adding blocks into array of imageIcons
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {

				if(row == numRows-1 && col == numCols-1) {
					//Sets bottom right block to white
					BufferedImage whiteBlock = new BufferedImage(chunkWidth, chunkHeight, BufferedImage.TYPE_INT_ARGB);
					Color White = new Color(255, 255, 255); 
					int rgb = White.getRGB();

					for (int r = height-chunkHeight; r < chunkHeight; r++) {
						for (int c = width-chunkWidth; c < chunkWidth; c++) {
							whiteBlock.setRGB(c, r, rgb);
						}
					}
					//Creates an ImageIcon out of BufferedImage whiteBlock and add the ImageIcon to arraylist
					ImageIcon wb = new ImageIcon(whiteBlock);
					wb.setDescription("9");
					imgIcons.add(wb);
				}
				else {
					//Creates ImageIcons out of subimages of BufferedImage and add them to the ImageIcon arraylist
					temp = (new ImageIcon(img.getSubimage(col*chunkWidth, row*chunkHeight, chunkWidth, chunkHeight)));

					//Add description of the position in the array to make it easier to check if solvable
					String strCounter = Integer.toString(counter);
					temp.setDescription(strCounter);
					counter++;
					imgIcons.add(temp);
				}
			}
		}
		//creates copy of ordered array to check win condition
		for(ImageIcon e: imgIcons){
			inOrder.add(e);
		}

		//shuffles icons
		boolean solvable = false;

		//keep shuffling until puzzle is solvable
		do {
			ImageIcon whiteImage = imgIcons.remove(imgIcons.size() - 1);
			Collections.shuffle(imgIcons);
			imgIcons.add(whiteImage);
			solvable = checkSolvable();
		} while(!solvable);
		whitePos = imgIcons.size()-1;
	}

	public static boolean checkSolvable() {
		ImageIcon tempOut, tempIn;
		int posOut, posIn;
		int inversions = 0;

		for (int i=0; i<imgIcons.size()-2; i++) {
			//get initial position
			tempOut = imgIcons.get(i);
			posOut = Integer.parseInt(tempOut.getDescription());

			for (int inner = i + 1; inner<imgIcons.size()-1; inner++) {
				//get initial position
				tempIn = imgIcons.get(inner);
				posIn = Integer.parseInt(tempIn.getDescription());

				//keep track of number of values that are less than the current value
				if (posIn<posOut) {inversions++;}
			}
		}
		//solvable if number of inversions is even
		return ((inversions % 2) == 0);
	}

	public static boolean swapTiles(ImageIcon Icon1){
		//Sets adjacent positions to -1
		int right=-1;
		int left=-1;
		int above=-1;
		int below=-1;

		//Compares clicked image to image in array to determine location.
		for(int i=0; i<imgIcons.size();i++){
			int ghash = imgIcons.get(i).hashCode();
			int ihash = Icon1.hashCode();

			//if the images match
			if(ghash == ihash){
				//need value of i to be a double for computations below
				double x = i;

				//calculates the array indices of the tiles adjacent to the clicked tile
				if((i<(imgIcons.size())-1) && ((x+1)/numCols)!=1){right = i+1;}
				if((i>0) && x/numCols!=1){left = i-1;}
				if(i-numCols>=0){above = i-numCols;}
				if(i+numCols<(imgIcons.size())){below = i+numCols;}

				//if any of the adjacent tiles match the location of the white tile, swap the white tile and the clicked tile
				if(right == whitePos || left==whitePos || above==whitePos || below==whitePos){
					Collections.swap(imgIcons, i, whitePos);
					whitePos = i;
					return true;
				}
			}
		}
		return false;
	}

	//check if the player won by comparing the modified array to the original array
	public static boolean checkWin(){
		if(imgIcons.equals(inOrder)){
			return true;
		}
		return false;
	}

	//returns array of ImageIcons to be modified
	public static ArrayList<ImageIcon> getImgIcons(){return imgIcons;}

}
