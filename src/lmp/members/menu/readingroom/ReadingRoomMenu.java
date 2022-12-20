package lmp.members.menu.readingroom;

import java.awt.Color;

import javax.swing.JFrame;

import lmp.members.menu.readingroom.seatlist.SeatListPanel;

public class ReadingRoomMenu extends JFrame{ 
	
	public ReadingRoomMenu() {
		setBounds(300, 100, 1200, 800);
		getContentPane().setBackground(new Color(49, 82, 91));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		getContentPane().add(new SeatListPanel());
	}
	
	public static void main(String[] args) {
		new ReadingRoomMenu();
		
	}

}
