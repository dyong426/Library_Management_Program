package lmp.members.menu.readingroom.seatlist.label;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class StatusLabel extends JLabel{

	public StatusLabel() {

		this.setOpaque(true);
		this.setBackground(new Color(126, 151, 148));
		this.setHorizontalAlignment(JLabel.CENTER);
//		this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		this.setForeground(Color.WHITE);
		this.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 15));
	}

}