package lmp.members.menu.readingroom.seatlist.panel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import lmp.members.menu.readingroom.seatlist.label.SeatLabel;
import lmp.members.vo.SeatUseDetailVO;

public class SeatPanel extends JPanel{

	GridLayout gridLayout = new GridLayout(5,2);
	SeatLabel[] seatLabels = new SeatLabel[gridLayout.getRows() * gridLayout.getColumns()];
	int tens;
	
	public SeatPanel(ArrayList<SeatUseDetailVO> sudVOs,int tensDigit) {
		System.out.println("seatPanel");
		this.setLayout(gridLayout);
		this.tens = tensDigit * 10;
		
		for (int i = 0 + tens; i < gridLayout.getRows() * gridLayout.getColumns() + tens; i++) {
			
			seatLabels[i - tens] = new SeatLabel(i + 1);
			seatLabels[i - tens].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			add(seatLabels[i - tens]);
			
			//---------------------
			
//			seatLabels[i - tens].addMouseListener(new MouseAdapter() {
//				@Override
//				public void mouseClicked(MouseEvent e) {
//					super.mouseClicked(e);
//					int result = JOptionPane.showConfirmDialog(null, "좌석을 발권하겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
//					if (result == JOptionPane.NO_OPTION) {
//						setVisible(true);
//					} else if (result == JOptionPane.YES_OPTION) {
//						JOptionPane.showMessageDialog(null, "좌석 발권이 완료되었습니다.", "Message", 1);
//						// 로그인 정보 db로 어떻게 넘기나..... 에휴....ㅠㅠ
//						// yes option -> 해당 좌석 번호 누르면 로그인한 사람 정보가 이용자 목록에 추가?
//						
//					} 
//				}	
//			});
			
			//---------------------
		}
		
		for (SeatUseDetailVO sudVO : sudVOs) {
			int usageSeatNum = sudVO.getReadingroom().getSeatNum();
			String sex = sudVO.getMember().getSex();
			if (usageSeatNum > tens + 1 && usageSeatNum < tens + 10) {
				if (sex.equals("0")) {					
					seatLabels[usageSeatNum - tens - 1].setBackground(new Color(153,204,255));
				} else {
					seatLabels[usageSeatNum - tens - 1].setBackground(new Color(255,153,204));
				}
			}
		}
		
	}
	
	public void refresh(ArrayList<SeatUseDetailVO> sudVOs) {
		
		for (int i = 0; i < seatLabels.length; i++) {
			seatLabels[i].setBackground(Color.LIGHT_GRAY);
		}
		
		for (SeatUseDetailVO sudVO : sudVOs) {
			int usageSeatNum = sudVO.getReadingroom().getSeatNum();
			String sex = sudVO.getMember().getSex();
			if (usageSeatNum > tens + 1 && usageSeatNum < tens + 10) {
				if (sex.equals("0")) {					
					seatLabels[usageSeatNum - tens - 1].setBackground(new Color(153,204,255));
				} else {
					seatLabels[usageSeatNum - tens - 1].setBackground(new Color(255,153,204));
				}
			}
		}
		
		this.validate();
		
	}
}