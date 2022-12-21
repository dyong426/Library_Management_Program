package lmp.members.menu.readingroom.usagelist.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import lmp.members.dao.SeatUseDetailDao;
import lmp.members.menu.readingroom.ReadingRoomPanel;
import lmp.members.menu.readingroom.seatlist.label.GenderLabel;
import lmp.members.menu.readingroom.usagelist.label.UsageListCheckOutLabel;
import lmp.members.menu.readingroom.usagelist.label.UsageListTitleLabel;
import lmp.members.vo.SeatUseDetailVO;

public class UsageListTitlePanel extends JPanel{

	BorderLayout borderLayout = new BorderLayout();
	UsageListTitleLabel usageListTitleLabel;
	UsageListCheckOutLabel usageListCheckOutLabel;
	GenderLabel genderLabel;

	public UsageListTitlePanel(ReadingRoomPanel readingRoomPanel) {
		System.out.println("titlePanel");
		setBorder(new TitledBorder(new LineBorder(new Color(126, 151, 148),20)));
		setBackground(new Color(126, 151, 148));

		usageListCheckOutLabel = new UsageListCheckOutLabel(readingRoomPanel);
		usageListTitleLabel = new UsageListTitleLabel();
		genderLabel = new GenderLabel(readingRoomPanel);

		this.setLayout(borderLayout);
		this.add(usageListTitleLabel, "Center");
		this.add(usageListCheckOutLabel, "East");
		this.add(genderLabel, "West");

	}

}