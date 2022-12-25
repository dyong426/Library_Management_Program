package lmp.admin.menu.readingroom;

import java.awt.Color;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import lmp.admin.db.dao.SeatUseDetailDao;
import lmp.admin.menu.readingroom.seatlist.SeatListPanel;
import lmp.admin.menu.readingroom.usagelist.UsageListPanel;
import lmp.admin.menu.readingroom.usagelist.panel.UsageListTitlePanel;
import lmp.admin.menu.readingroom.usagelist.scrollpane.UsageListScrollPane;

public class ReadingRoomPanel extends JPanel {
	
	SeatUseDetailDao sudDao = new SeatUseDetailDao();
	
	SeatListPanel seatListPanel;
	UsageListPanel usageListPanel;
	UsageListScrollPane usageListScrollPane;
	UsageListTitlePanel usageListTitlePanel;
	
	GridLayout gridLayout = new GridLayout(2, 1, 0, 30);
	
	
	public ReadingRoomPanel() {
		
		setBackground(new Color(87, 119, 119));
		
		seatListPanel = new SeatListPanel();
		usageListPanel = new UsageListPanel();
		try {
			usageListScrollPane = new UsageListScrollPane();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		usageListTitlePanel = new UsageListTitlePanel(this);
		
		
		usageListPanel.add(usageListTitlePanel, "North");
		usageListPanel.setBackground(new Color(87, 119, 119));
		usageListPanel.add(usageListScrollPane, "Center");
		
		seatListPanel.setBorder(new LineBorder(new Color(87, 119, 119), 10));
		setLayout(gridLayout);
		add(usageListPanel);
		add(seatListPanel);
	}


	public SeatListPanel getSeatListPanel() {
		return seatListPanel;
	}
	
	public UsageListPanel getUsageListPanel() {
		return usageListPanel;
	}
	
	public UsageListScrollPane getUsageListScrollPane() {
		return usageListScrollPane;
	}
	
	
}
