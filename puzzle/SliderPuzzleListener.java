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
<<<<<<< HEAD
		boolean isSwapped = frame.splits.swapTiles((ImageIcon)(label.getIcon()));
		if (isSwapped == true){System.out.println("SWAPPING"); SliderPuzzleUI.UpdateUI();}
=======
		if (SliderPuzzleUI.frame.splits.swapTiles((ImageIcon)(label.getIcon()))) {
			System.out.println("SWAPPING" + pos);
		}
		SliderPuzzleUI.UpdateUI();
>>>>>>> aabd0e2bc7ca8498acf20743423d87ade7e35699
	}
}
