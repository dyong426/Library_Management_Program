package lmp.members.menu.readingroom;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import lmp.admin.dao.ReadingRoomDao;
import lmp.admin.dao.SeatUseDetailDao;
import lmp.admin.vo.ReadingRoomVO;
import lmp.admin.vo.SeatUseDetailVO;
import lmp.members.menu.readingroom.seatlist.SeatListPanel;
import lmp.members.menu.readingroom.seatlist.panel.StatusPanel;
import lmp.members.menu.readingroom.usagelist.UsageListPanel;
import lmp.members.menu.readingroom.usagelist.panel.UsageListTitlePanel;

public class ReadingRoomPanel extends JPanel {
	
	SeatListPanel seatListPanel;
	StatusPanel statusPanel;
	UsageListPanel usageListPanel;
	UsageListTitlePanel usageListTitlePanel;
	
	static SeatUseDetailDao sudDao = new SeatUseDetailDao();
	static ReadingRoomDao roomDao  = new ReadingRoomDao();
	ArrayList<SeatUseDetailVO> sudVOs;
	ArrayList<ReadingRoomVO> seatList;
	
	public ReadingRoomPanel() throws SQLException  {
		statusPanel = new StatusPanel();
		usageListPanel = new UsageListPanel();
		seatListPanel = new SeatListPanel(this);
		usageListTitlePanel = new UsageListTitlePanel(this);
		
		
		setBackground(new Color(126, 151, 148));  //--> 사이즈 수정 필요
		setLayout(null);
		
		JLabel label = new JLabel("|  |  :  칸막이");
		label.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 20));
		label.setForeground(Color.WHITE);
		label.setBounds(100, 125, 200, 150);
		
		add(label);
		usageListPanel.add(usageListTitlePanel);
		add(usageListPanel);
		add(statusPanel);
		add(seatListPanel);
	}
	public void refresh() throws SQLException {
		
		
		
		
		seatListPanel.refresh();
		statusPanel.refresh();
	}
	
	public SeatListPanel getSeatListPanel() {
		return seatListPanel;
	}

	public UsageListPanel getUsageListPanel() {
		return usageListPanel;
	}

	public StatusPanel getStatusPanel() {
		return statusPanel;
	}
	public ArrayList<SeatUseDetailVO> getSudVOs() {
		return sudVOs;
	}
	public ArrayList<ReadingRoomVO> getSeatList() {
		return seatList;
	}

	
	
	
}