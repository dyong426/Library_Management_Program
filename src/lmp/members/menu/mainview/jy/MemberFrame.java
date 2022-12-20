package lmp.members.menu.mainview.jy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MemberFrame {


	public void MemberFrame() {
		JFrame frame = new JFrame();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();

		// panel1, panel2 배경색
		Color bgColor = new Color(49, 82, 91);

		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.setBackground(bgColor);
		panel1.setBounds(0, 0, 1200, 100);

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.setBackground(bgColor);
		panel2.setBounds(0, 100, 1200, 700);

		// 이미지 아이콘 만들기
		ImageIcon bookImg = new ImageIcon("./images/도서.png");
		ImageIcon roomImg = new ImageIcon("./images/열람실.png");
		ImageIcon memImg = new ImageIcon("./images/회원관리.png");


		// 이미지 사이즈 변경
		Image bookimg = bookImg.getImage();
		Image bookimgs = bookimg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon changebook = new ImageIcon(bookimgs);

		Image roomimg = roomImg.getImage();
		Image roomimgs = roomimg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon changeroom = new ImageIcon(roomimgs);

		Image memimg = memImg.getImage();
		Image memimgs = memimg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon changemem = new ImageIcon(memimgs);

		// 버튼에 이미지아이콘 넣기
		JButton bookBtn = new JButton(changebook);
		JButton roomBtn = new JButton(changeroom);
		JButton memberBtn = new JButton(changemem);





		// 버튼 외곽선 가리기
		bookBtn.setBorderPainted(false);
		roomBtn.setBorderPainted(false);
		memberBtn.setBorderPainted(false);

		// 버튼 내부영역 채우지 않기
		bookBtn.setContentAreaFilled(false);
		roomBtn.setContentAreaFilled(false);
		memberBtn.setContentAreaFilled(false);


		JLabel bookLb = new JLabel("도서");
		JLabel roomLb = new JLabel("열람실");
		JLabel memberLb = new JLabel("회원정보");

		bookBtn.setBounds(150, 0, 100, 100);
		roomBtn.setBounds(350, 0, 100, 100);
		memberBtn.setBounds(550, 0, 100, 100);

		bookLb.setBounds(185, 50, 100, 100);
		roomLb.setBounds(380, 50, 100, 100);
		memberLb.setBounds(576, 50, 100, 100);


		// 버튼 및 라벨 폰트설정
		Font font = new Font("한컴 말랑말랑 Regular", Font.PLAIN, 15);

		bookBtn.setFont(font);
		roomBtn.setFont(font);
		memberBtn.setFont(font);

		bookLb.setFont(font);
		roomLb.setFont(font);
		memberLb.setFont(font);

		bookLb.setForeground(Color.WHITE);
		roomLb.setForeground(Color.WHITE);
		memberLb.setForeground(Color.WHITE);


		frame.add(bookBtn);
		frame.add(roomBtn);
		frame.add(memberBtn);


		frame.add(bookLb);
		frame.add(roomLb);
		frame.add(memberLb);


		// 프레임에 패널을 추가
		frame.add(panel1);
		frame.add(panel2);



		frame.setBounds(0, 0, 1200, 800);
		frame.setBackground(Color.WHITE);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);// 창 크기 고정
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void open() {
		this.MemberFrame();
	}

}