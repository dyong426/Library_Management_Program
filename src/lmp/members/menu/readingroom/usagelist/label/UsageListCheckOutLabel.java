package lmp.members.menu.readingroom.usagelist.label;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import lmp.members.menu.readingroom.ReadingRoomPanel;
//import lmp.members.menu.readingroom.sj.usagelist.scrollpane.table.UsageListTable;
import lmp.members.menu.readingroom.usagelist.listener.CheckOutMouseAdapter;

public class UsageListCheckOutLabel extends JLabel{
	
	public UsageListCheckOutLabel(ReadingRoomPanel readingRoomPanel) {
		
		this.setText("  퇴실하기  ");
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setOpaque(true);
		this.setBackground(new Color(49, 82, 91));
		this.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 15));
//		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		this.setForeground(Color.WHITE);
		this.addMouseListener(new CheckOutMouseAdapter(readingRoomPanel));
	}
}