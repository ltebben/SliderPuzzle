import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Puzzle {
	static BufferedImage readImage(){
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("cat.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedImage img = readImage();
		img.setVisible(true);
	}

}
