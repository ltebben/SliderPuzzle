package puzzle;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class SliderPuzzleUI {
	
	private static void show() {
		JFrame frame = new JFrame("Slider Puzzle");
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// TODO: allow file browser or some sort of input
		// Possibly also allow preselected images
		File inputFile = new File("/home/zach/Downloads/cat.jpg");
		FileInputStream istream = null;
		BufferedImage image = null;
		try {
			istream = new FileInputStream(inputFile);
		} catch (FileNotFoundException e) {
			// TODO: Catch block
			e.printStackTrace();
		}
		
		try {
			image = ImageIO.read(istream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO: allow input for number of rows and columns
		SplitImage splits = new SplitImage(image);
		ArrayList<ImageIcon> list = splits.getImgIcons();
		int width = splits.getNumCols();
		constraints.insets = new Insets(2,2,2,2);
		for (int i = 0; i < list.size(); i++) {
			if (constraints.gridx == width) {
				constraints.gridy++;
				constraints.gridx = 0;
			}
			JLabel label = new JLabel(list.get(i));
			panel.add(label, constraints);
			constraints.gridx++;
		}
		
		// TODO: set size to width and height of image plus a little bit
		frame.setSize(image.getWidth() + 50, image.getHeight() + 50);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(
				new Runnable() { public void run() {show();} }
				);
	}
}