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
		boolean isSwapped = splits.swapTiles((ImageIcon)(label.getIcon()));
		if (isSwapped == true){System.out.println("SWAPPING"); 
			SliderPuzzleUI.UpdateUI();}
	}
}