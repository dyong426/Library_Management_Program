package lmp.members.menu.readingroom.seatlist;

import java.awt.Color;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import lmp.members.dao.SeatUseDetailDao;
import lmp.members.menu.readingroom.ReadingRoomPanel;
import lmp.members.menu.readingroom.seatlist.panel.SeatPanel;
import lmp.members.vo.SeatUseDetailVO;

public class SeatListPanel extends JPanel{
	
	public GridLayout gridLayout = new GridLayout(1,6,30,0);

//	StatusPanel statusPanel;
	SeatPanel[]	seatPanels = new SeatPanel[gridLayout.getColumns()];
	
	ReadingRoomPanel readingRoomPanel;
	
	public SeatListPanel() {}
		
	public SeatListPanel(ReadingRoomPanel readingRoomPanel) throws SQLException {
		this.readingRoomPanel = readingRoomPanel;
		setLayout(gridLayout);
		setBorder(new TitledBorder(new LineBorder(new Color(126, 151, 148), 20)));
		setBackground(new Color(126, 151, 148));

		for (int i = 0; i < gridLayout.getColumns(); i++) {
			seatPanels[i] = new SeatPanel(readingRoomPanel, i);
			add(seatPanels[i]);
		}

	}

	public void refresh() throws SQLException {
		for (SeatPanel seatPanel : seatPanels) {
			seatPanel.initialize();
		}
		this.validate();
	}
	
	

}