package lmp.members.menu.readingroom;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import lmp.members.menu.readingroom.seatlist.SeatListPanel;

public class ReadingRoomMenu extends JFrame{ 
	
	SeatListPanel seatListPanel = new SeatListPanel();
	
	public ReadingRoomMenu() {
		System.out.println("실행");
//		add("Center",seatListPanel);
		setBounds(300, 100, 1200, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLayout(new BorderLayout());
	}
	
	public static void main(String[] args) {
		new ReadingRoomMenu();
		
	}

}
