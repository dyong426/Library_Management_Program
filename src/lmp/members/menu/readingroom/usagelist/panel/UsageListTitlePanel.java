package lmp.members.menu.readingroom.usagelist.panel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import lmp.members.menu.readingroom.ReadingRoomPanel;
import lmp.members.menu.readingroom.seatlist.label.GenderPanel;
import lmp.members.menu.readingroom.usagelist.label.UsageListCheckOutLabel;
import lmp.members.menu.readingroom.usagelist.label.UsageListTitleLabel;

public class UsageListTitlePanel extends JPanel{

	BorderLayout borderLayout = new BorderLayout();
	UsageListTitleLabel usageListTitleLabel;
	UsageListCheckOutLabel usageListCheckOutLabel;
	GenderPanel genderPanel;

	public UsageListTitlePanel(ReadingRoomPanel readingRoomPanel) {
		setBorder(new TitledBorder(new LineBorder(new Color(126, 151, 148), 20)));
		setBackground(new Color(126, 151, 148));

		usageListCheckOutLabel = new UsageListCheckOutLabel(readingRoomPanel);
		usageListTitleLabel = new UsageListTitleLabel();
		genderPanel = new GenderPanel();
		
		this.setLayout(borderLayout);
		this.add(usageListTitleLabel, "Center");
		this.add(usageListCheckOutLabel, "East");
		this.add(genderPanel, "West");

	}

}