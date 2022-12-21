package lmp.members.menu.readingroom.seatlist.label;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lmp.members.menu.readingroom.ReadingRoomPanel;

public class GenderLabel  extends JLabel {
	
	JLabel genderImgLabel;
	
	public GenderLabel(ReadingRoomPanel readingRoomPanel) {
		
		System.out.println("Changing the color of the seat by gender");
		ImageIcon icon = new ImageIcon("C:\\JavaFullStack\\lmpRepos\\Library_Management_Program\\src\\lmp\\members\\menu\\readingroom\\sj\\seatlist\\label\\gender.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(120, 50, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		genderImgLabel = new JLabel(changeIcon);
		//		genderImgLabel = new JLabel("", genderImg, JLabel.CENTER);
		
//		this.setText("성별");
		this.setIcon(new ImageIcon("C:\\JavaFullStack\\lmpRepos\\Library_Management_Program\\src\\lmp\\members\\menu\\readingroom\\sj\\seatlist\\label\\gender.png"));
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 15));
//		this.setBorder(BorderFactory.createLineBorder(null));
		this.setForeground(Color.WHITE);

	
	}
}