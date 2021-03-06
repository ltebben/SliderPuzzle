package puzzle;

import java.awt.GridBagConstraints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

// Class to hold a label and its attached constraints. Maybe can do without this as well.
public class SliderPuzzleLabel extends JLabel {
	
	/**
	 * Extension of JLabel to let it own a GridBagConstraints
	 */
	
	private static final long serialVersionUID = 1L;
	
	private GridBagConstraints gridCons;
	
	SliderPuzzleLabel(ImageIcon img) {
		super(img);
	}
	
	public void addConstraints(GridBagConstraints gridConstraints) {
		gridCons = gridConstraints;
	}
	
	public GridBagConstraints getConstraints() {
		return gridCons;
	}
}