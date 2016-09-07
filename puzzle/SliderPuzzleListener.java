package puzzle;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

class SliderPuzzleListener implements MouseListener {
	
	int pos;
	SliderPuzzleLabel label;
	
	SliderPuzzleListener(int position, SliderPuzzleLabel inputLabel) {
		pos = position;
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
		if (SliderPuzzleUI.frame.splits.swapTiles((ImageIcon)(label.getIcon()))) {
			 
			SliderPuzzleUI.UpdateUI();
			
			if(SliderPuzzleUI.frame.splits.checkWin()){
				System.out.println("You Win!!");
				SliderPuzzleUI.gameWon = true;
			}
		}
	}
}