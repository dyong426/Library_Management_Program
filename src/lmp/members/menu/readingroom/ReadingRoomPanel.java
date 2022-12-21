package lmp.members.menu.readingroom;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import lmp.members.dao.SeatUseDetailDao;
import lmp.members.menu.readingroom.seatlist.SeatListPanel;
import lmp.members.menu.readingroom.seatlist.panel.StatusPanel;
import lmp.members.menu.readingroom.usagelist.UsageListPanel;
import lmp.members.menu.readingroom.usagelist.panel.UsageListTitlePanel;
import lmp.members.vo.SeatUseDetailVO;

public class ReadingRoomPanel extends JPanel {
	
	SeatListPanel seatListPanel;
	UsageListPanel usageListPanel;
	StatusPanel statusPanel;
	UsageListTitlePanel usageListTitlaPanel;

//	GridLayout gridLayout = new GridLayout(0,1);
	
	SeatUseDetailDao sudDao = new SeatUseDetailDao();
	ArrayList<SeatUseDetailVO> sudVO;
	
	public ReadingRoomPanel() throws SQLException {
		
		System.out.println("rrpanel");
		seatListPanel = new SeatListPanel();
		usageListPanel = new UsageListPanel();
		statusPanel = new StatusPanel();
		usageListTitlaPanel = new UsageListTitlePanel(this);
		
		setBorder(new TitledBorder(new LineBorder(new Color(49, 82, 91),20)));
//		setBorder(new TitledBorder(new LineBorder(Color.BLUE)));
		setBackground(new Color(126, 151, 148));  //--> 사이즈 수정 필요
//		seatListPanel.setBackground(Color.BLUE);
		
		
		usageListPanel.add(usageListTitlaPanel);
//		setLayout(gridLayout);
//		usageListTitlaPanel.setBounds(100, 0, 500, 50);
		setLayout(null);
		add(usageListPanel);
		usageListPanel.setBounds(150, 50, 1000, 80);
		
		try {
			sudVO = sudDao.getUse();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		statusPanel = new StatusPanel(sudVO);
		add(statusPanel);
		statusPanel.setBounds(300, 180, 630, 50);
		
		add(seatListPanel);
		seatListPanel.setBounds(20,280, 1140, 390);
	}

	public void refresh(ArrayList<SeatUseDetailVO> sudVO) {
		statusPanel.refresh(sudVO);
		this.validate();
	}
	
	public SeatListPanel getSeatListPanel() {
		return seatListPanel;
	}

	public UsageListPanel getUsageListPanel() {
		return usageListPanel;
	}
	
	public StatusPanel getStatusPanel(ArrayList<SeatUseDetailVO> sudVO) {
		return statusPanel;
	}
	
	
}