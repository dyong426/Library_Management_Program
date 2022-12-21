package lmp.members.menu.readingroom.usagelist.label;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import lmp.members.menu.readingroom.ReadingRoomPanel;
//import lmp.members.menu.readingroom.sj.usagelist.scrollpane.table.UsageListTable;

public class UsageListCheckOutLabel extends JLabel{
	
	public UsageListCheckOutLabel(ReadingRoomPanel readingRoomPanel) {
		
		System.out.println("checkOutlabel");
		this.setText("  퇴실하기  ");
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 15));
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		this.setForeground(Color.WHITE);
	
	}
}