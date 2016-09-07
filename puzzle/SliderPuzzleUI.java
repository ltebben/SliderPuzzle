package puzzle;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class SliderPuzzleUI{
	
	static SliderPuzzleFrame frame;
	static JPanel panel;
	
	private static BufferedImage OpenAndCheckFile() {
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
		
		return image;
	};
	
	static void UpdateUI() {
		ArrayList<ImageIcon> list = frame.splits.getImgIcons();
		int width = frame.splits.getNumCols();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(2,2,2,2);
		for (int i = 0; i < list.size(); i++) {
			GridBagConstraints newCons = new GridBagConstraints();
			if (constraints.gridx == width) {
				constraints.gridy++;
				constraints.gridx = 0;
			}
			newCons.gridx = constraints.gridx;
			newCons.gridy = constraints.gridy;
			newCons.insets = new Insets(2,2,2,2);
			
			SliderPuzzleLabel label = new SliderPuzzleLabel(list.get(i));
			label.addMouseListener(new SliderPuzzleListener(i, label));
			label.addConstraints(newCons);
			panel.add(label, label.getConstraints());
			constraints.gridx++;
		}
	}
	
	private static void DoUI() {
		frame = new SliderPuzzleFrame("Slider Puzzle");
		panel = new JPanel(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// TODO: allow input for number of rows and columns
		BufferedImage buffIm = OpenAndCheckFile();
		frame.splits = new SplitImage(buffIm);
		
		UpdateUI();
		
		// TODO: set size to width and height of image plus a little bit
		frame.setSize(buffIm.getWidth() + 50, buffIm.getHeight() + 50);
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(
				new Runnable() { public void run() {DoUI();} }
				);
	}
}