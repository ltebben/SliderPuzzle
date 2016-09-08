package puzzle;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

// Class to hold mouse listener for slider puzzle
class SliderPuzzleListener implements MouseListener {
	
	SliderPuzzleLabel label;
	
	// TODO: remove position from constructor
	SliderPuzzleListener(int position, SliderPuzzleLabel inputLabel) {
		label = inputLabel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (SliderPuzzleUI.splits.swapTiles((ImageIcon)(label.getIcon()))) {
			
			if(SliderPuzzleUI.splits.checkWin()){
				SliderPuzzleUI.gameWon = true;
			}
			
			SliderPuzzleUI.UpdateUI();
		}
	}
}