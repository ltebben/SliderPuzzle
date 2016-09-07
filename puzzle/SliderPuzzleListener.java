package puzzle;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

class SliderPuzzleListener implements MouseListener {
	int pos;
	SliderPuzzleLabel label;
	SliderPuzzleFrame frame;
	
	SliderPuzzleListener(int position, SliderPuzzleLabel inputLabel, SliderPuzzleFrame inputFrame) {
		pos = position;
		label = inputLabel;
		frame = inputFrame;
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
		System.out.println("SWAPPING");
		frame.splits.swapTiles((ImageIcon)(label.getIcon()));
		SliderPuzzleUI.UpdateUI();
	}
}