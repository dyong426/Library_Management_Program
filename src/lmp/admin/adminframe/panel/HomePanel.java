package lmp.admin.adminframe.panel;

import javax.swing.JLabel;
import javax.swing.JPanel;

import lmp.util.ImageConvert;

public class HomePanel extends JPanel{

	ImageConvert img = new ImageConvert();
	
	public HomePanel() {
		JLabel label = new JLabel();
		label.setSize(1500, 750);
		label.setIcon(img.scaledPanelImage("initial"));
		setLayout(null);
		add(label);
	}
}
