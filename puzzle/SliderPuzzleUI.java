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
	
	static JFrame frame;
	static JPanel panel;
	static boolean gameWon = false;
	static SplitImage splits;
	private static Scanner scan = new Scanner(System.in);
	public static int size = 3;
	// Function to open the file and make sure it is actually a picture.
	private static BufferedImage OpenAndCheckFile() {
		
		System.out.print("Please enter the name of your image file: ");
		String inputStr = scan.nextLine();
		File inputFile = new File(inputStr);
		FileInputStream istream = null;
		BufferedImage image = null;
		
		// Open the file.
		try {
			istream = new FileInputStream(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("Image not found. Make sure that the image is in the working directory\n"
					+ "or you have included the path to the image.");
			System.exit(0);
		}
		
		// Make sure the file is a picture.
		try {
			image = ImageIO.read(istream);
		} catch (IOException e) {
			System.out.println("Could not read image. Are you sure that it was a picture?");
			System.exit(0);
		}
		
		return image;
	};
	
	// Function that updates the UI. Is called on initialization and each time a change is made to the puzzle.
	static void UpdateUI() {
		
		// Clear the panel before repainting it
		panel.removeAll();
		
		// Get new ArrayList of images
		ArrayList<ImageIcon> list = SplitImage.getImgIcons();
		int width = size;
		
		// Initialize constraints to format the panel
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(2,2,2,2);
		for (int i = 0; i < list.size(); i++) {
			
			GridBagConstraints newCons = new GridBagConstraints();
			
			// If we are at the x-max, reset to x-min and start a new row
			if (constraints.gridx == width) {
				constraints.gridy++;
				constraints.gridx = 0;
			}
			newCons.gridx = constraints.gridx;
			newCons.gridy = constraints.gridy;
			newCons.insets = new Insets(2,2,2,2);
			
			// Create label that holds the image
			SliderPuzzleLabel label = new SliderPuzzleLabel(list.get(i));
			
			// If the user won the game, disable moving pieces around
			if (!gameWon) {
				label.addMouseListener(new SliderPuzzleListener(i, label));
			}
			
			// Format label and add to panel
			label.addConstraints(newCons);
			panel.add(label, label.getConstraints());
			constraints.gridx++;
		}
		
		// Remake panel, display it
		panel.revalidate();
		panel.repaint();
		
		if (gameWon) {
			JOptionPane.showMessageDialog(null, "YOU WIN!");
		}
	}
	
	// Function that initializes the UI. Only is called once, from main.
	private static void DoUI() {
		
		// Initialize frame for puzzle
		frame = new JFrame("Slider Puzzle");
		
		// Initialize panel for puzzle; panel holds the images
		panel = new JPanel(new GridBagLayout());
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Open the input file
		BufferedImage buffIm = OpenAndCheckFile();
		
		// Get user input for puzzle size. Don't let them make it too big
		// TODO: inputting an integer greater than int max crashes eclipse. We should get a string
		// for input and cast it to an integer to be safe
		System.out.println("What size would you like the puzzle to be? (Enter 3 for 3x3, etc.)");
		System.out.print("Size must be 3 or 5: ");
		
		do {
			if(scan.hasNextInt()){
				size = scan.nextInt();
			}
			if (size < 3 || size > 5) {
				System.out.print("Size must be 3, 4, or 5: ");
			}
		} while (size > 5 || size < 3);
		scan.close();
		
		// SplitImage type holds images for puzzle pieces
		splits = new SplitImage(buffIm, size);
		
		// Make UI
		UpdateUI();
		
		// TODO: Limit panel size for pictures bigger than monitor and let the user deal with it
		// Alternately, don't allow pictures that big
		frame.setSize(buffIm.getWidth() + 50, buffIm.getHeight() + 50);
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(
				new Runnable() { public void run() {DoUI();} });
	}
}