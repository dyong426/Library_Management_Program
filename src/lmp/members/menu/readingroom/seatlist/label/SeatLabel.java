package lmp.members.menu.readingroom.seatlist.label;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import lmp.members.dao.SeatUseDetailDao;
import lmp.members.menu.readingroom.ReadingRoomPanel;
import lmp.members.menu.readingroom.seatlist.listener.SeatMouseAdapter;
import lmp.members.menu.readingroom.seatlist.panel.SeatPanel;
import lmp.members.vo.ReadingRoomVO;

public class SeatLabel extends JLabel{

	SeatUseDetailDao sudDao = new SeatUseDetailDao();
	ArrayList<ReadingRoomVO> seatList;

	
	public SeatLabel(ReadingRoomPanel readingRoomPanel,int seatNum) throws SQLException {
		seatList = sudDao.getRoomInfo();
		
		if (seatList.get(seatNum -1).getTableDivider().equals("0")) {
			setText(Integer.toString(seatNum));			
		} else {
			setText("|" + Integer.toString(seatNum) + "|");
		}
		setOpaque(true);
		setForeground(Color.BLACK);
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		setHorizontalAlignment(JLabel.CENTER);
		setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 20));
		
		addMouseListener(new SeatMouseAdapter(readingRoomPanel));
	}
	
}