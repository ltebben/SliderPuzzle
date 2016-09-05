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