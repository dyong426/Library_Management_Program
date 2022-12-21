package lmp.members.menu.readingroom.seatlist.panel;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import lmp.members.menu.readingroom.seatlist.label.SeatLabel;
import lmp.members.vo.SeatUseDetailVO;

public class SeatPanel extends JPanel{

	GridLayout gridLayout = new GridLayout(5, 2);
	SeatLabel[] seatLabels = new SeatLabel[gridLayout.getRows() * gridLayout.getColumns()];
	int tens;
	
	public SeatPanel(ArrayList<SeatUseDetailVO> sudVOs, int tensDigit) {
		System.out.println("seatPanel");
		this.setLayout(gridLayout);
		this.tens = tensDigit * 10;
		
		for (int i = 0 + tens; i < gridLayout.getRows() * gridLayout.getColumns() + tens; i++) {
			
			seatLabels[i - tens] = new SeatLabel(i + 1);
			seatLabels[i - tens].setBorder(new LineBorder(Color.BLACK));
			add(seatLabels[i - tens]);
		}
		
		for (SeatUseDetailVO sudVO : sudVOs) {
			int usageSeatNum = sudVO.getReadingroom().getSeatNum();
			String sex = sudVO.getMember().getSex();
			if (usageSeatNum >= tens + 1 && usageSeatNum <= tens + 10) {
				if (sex.equals("0")) {
					seatLabels[usageSeatNum - tens - 1].setBackground(new Color(138, 228, 255));
				} else {
					seatLabels[usageSeatNum - tens - 1].setBackground(new Color(255, 183, 210));
				}
			}
		}
		
	}
	
	public void refresh(ArrayList<SeatUseDetailVO> sudVOs) {
		
		for (int i = 0; i < seatLabels.length; i++) {
			seatLabels[i].setBackground(Color.WHITE);
		}
		
		for (SeatUseDetailVO sudVO : sudVOs) {
			int usageSeatNum = sudVO.getReadingroom().getSeatNum();
			String sex = sudVO.getMember().getSex();
			if (usageSeatNum >= tens + 1 && usageSeatNum <= tens + 10) {
				if (sex.equals("0")) {
					seatLabels[usageSeatNum - tens - 1].setBackground(new Color(138, 228, 255));
				} else {
					seatLabels[usageSeatNum - tens - 1].setBackground(new Color(255, 183, 210));
				}
			}
		}
		
		this.validate();
		
	}
}
