package lmp.admin.menu.readingroom.usagelist.label;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class UsageListTitleLabel extends JLabel{
	
	public UsageListTitleLabel() {
		
		this.setText("열람실 이용 내역");
		this.setHorizontalAlignment(JLabel.CENTER);
		setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 30));
		setForeground(Color.WHITE);
	}

}
