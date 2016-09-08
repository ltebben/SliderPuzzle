package puzzle;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;

public class SliderPuzzleUI{
	
	static SliderPuzzleFrame frame;
	static JPanel panel;
	static boolean gameWon = false;
	private static Scanner scan = new Scanner(System.in);
	
	private static BufferedImage OpenAndCheckFile() {
		
		System.out.print("Please enter the name of your image file: ");
		String inputStr = scan.nextLine();
		File inputFile = new File(inputStr);
		FileInputStream istream = null;
		BufferedImage image = null;
		
		try {
			istream = new FileInputStream(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		try {
			image = ImageIO.read(istream);
		} catch (IOException e) {
			System.out.println("Could not read file");
			e.printStackTrace();
		}
		
		return image;
	};
	
	static void UpdateUI() {
		panel.removeAll();
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
			
			if (!gameWon) {
				label.addMouseListener(new SliderPuzzleListener(i, label));
			}

			label.addConstraints(newCons);
			panel.add(label, label.getConstraints());
			constraints.gridx++;
		}
		panel.validate();
	}
	
	private static void DoUI() {
		frame = new SliderPuzzleFrame("Slider Puzzle");
		panel = new JPanel(new GridBagLayout());
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		// TODO: allow input for number of rows and columns
		BufferedImage buffIm = OpenAndCheckFile();
		int size = 3;
		System.out.print("What size would you like the puzzle to be? (Enter 2 for 2x2, 3 for 3x3, etc.)");
		if(scan.hasNextInt()){
			size = scan.nextInt();
			}
		frame.splits = new SplitImage(buffIm, size);
		scan.close();
		UpdateUI();
		
		frame.setSize(buffIm.getWidth() + 50, buffIm.getHeight() + 50);
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(
				new Runnable() { public void run() {DoUI();} });
	}
}