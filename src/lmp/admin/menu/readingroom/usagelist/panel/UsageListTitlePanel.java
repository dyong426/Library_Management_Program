package lmp.admin.menu.readingroom.usagelist.panel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import lmp.admin.menu.readingroom.ReadingRoomPanel;
import lmp.admin.menu.readingroom.usagelist.UsageListPanel;
import lmp.admin.menu.readingroom.usagelist.label.UsageListCheckOutButton;
import lmp.admin.menu.readingroom.usagelist.label.UsageListTitleLabel;

public class UsageListTitlePanel extends JPanel{
	
	BorderLayout borderLayout = new BorderLayout();
	UsageListTitleLabel usageListTitleLabel;
	UsageListCheckOutButton usageListCheckOutButton;
	
	
	public UsageListTitlePanel(ReadingRoomPanel readingRoomPanel) {
		
		usageListCheckOutButton = new UsageListCheckOutButton(readingRoomPanel);
		usageListTitleLabel = new UsageListTitleLabel();
		this.setLayout(borderLayout);
		this.add(usageListTitleLabel, "North");
		this.add(usageListCheckOutButton, "East");
		this.setBackground(new Color(87, 119, 119));
		
	}
}
