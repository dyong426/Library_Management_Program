package lmp.members.menu.readingroom;

import java.sql.SQLException;

import javax.swing.JFrame;

public class ReadingRoomMenu extends JFrame{ 
	
	
	
	public ReadingRoomMenu() throws SQLException {
		System.out.println("실행");
		add(new ReadingRoomPanel());
		setBounds(300, 100, 1200, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLayout(null);
	}
	
	public static void main(String[] args) {
		try {
			new ReadingRoomMenu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
