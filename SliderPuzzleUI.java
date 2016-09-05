import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SliderPuzzleUI {
	
	private static void show() {
		JFrame frame = new JFrame("Slider Puzzle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// TODO: allow file browser or some sort of input
		// Possibly also allow preselected images
		File inputFile = new File("/home/zach/Downloads/cat.jpg");
		FileInputStream istream = new FileInputStream(inputFile);
		BufferedImage image = ImageIO.read(istream);
		
		// TODO: allow input for number of rows and columns
		int numRows = 3;
		int numCols = 3;
		int numChunks = numRows*numCols;
		
		int chunkWidth = image.getWidth() / numCols;
		int chunkHeight = image.getHeight() / numRows;
		
		ArrayList<BufferedImage> imgs = new ArrayList<BufferedImage>();
		
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				imgs.add(new BufferedImage(chunkWidth, chunkHeight, image.getType()));
				
			}
		}
		
		JLabel label = new JLabel(icon);
		frame.getContentPane().add(label);
		
		// TODO: set size to width and height of image plus a little bit
		frame.setSize(icon.getIconWidth() + 50, icon.getIconHeight() + 50);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(
				new Runnable() { public void run() {show();} }
				);
	}
}