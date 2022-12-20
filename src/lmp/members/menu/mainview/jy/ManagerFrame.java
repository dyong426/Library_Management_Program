package lmp.members.menu.mainview.jy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lmp.admin.dao.AdminLogHistoryDao;

public class ManagerFrame extends JFrame {

	AdminLogHistoryDao adminLogHistoryDao;
	public void ManagerFrame() {
		//		JFrame frame = new JFrame();
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
		ImageIcon rentImg = new ImageIcon("./images/대출반납.png");
		ImageIcon roomImg = new ImageIcon("./images/열람실.png");
		ImageIcon memImg = new ImageIcon("./images/회원관리.png");
		ImageIcon empImg = new ImageIcon("./images/직원관리.png");


		// 이미지 사이즈 변경
		Image bookimg = bookImg.getImage();
		Image bookimgs = bookimg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon changebook = new ImageIcon(bookimgs);

		Image rentimg = rentImg.getImage();
		Image rentimgs = rentimg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon changerent = new ImageIcon(rentimgs);

		Image roomimg = roomImg.getImage();
		Image roomimgs = roomimg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon changeroom = new ImageIcon(roomimgs);

		Image memimg = memImg.getImage();
		Image memimgs = memimg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon changemem = new ImageIcon(memimgs);

		Image empimg = empImg.getImage();
		Image empimgs = empimg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon changeemp = new ImageIcon(empimgs);


		// 버튼에 이미지아이콘 넣기
		JButton bookBtn = new JButton(changebook);
		JButton rentBtn = new JButton(changerent);
		JButton roomBtn = new JButton(changeroom);
		JButton memberBtn = new JButton(changemem);
		JButton empBtn = new JButton(changeemp);





		// 버튼 외곽선 가리기
		bookBtn.setBorderPainted(false);
		rentBtn.setBorderPainted(false);
		roomBtn.setBorderPainted(false);
		memberBtn.setBorderPainted(false);
		empBtn.setBorderPainted(false);

		// 버튼 내부영역 채우지 않기
		bookBtn.setContentAreaFilled(false);
		rentBtn.setContentAreaFilled(false);
		roomBtn.setContentAreaFilled(false);
		memberBtn.setContentAreaFilled(false);
		empBtn.setContentAreaFilled(false);


		JLabel bookLb = new JLabel("도서관리");
		JLabel rentLb = new JLabel("대출/반납");
		JLabel roomLb = new JLabel("열람실");
		JLabel memberLb = new JLabel("회원관리");
		JLabel empLb = new JLabel("직원관리");

		bookBtn.setBounds(150, 0, 100, 100);
		rentBtn.setBounds(350, 0, 100, 100);
		roomBtn.setBounds(550, 0, 100, 100);
		memberBtn.setBounds(750, 0, 100, 100);
		empBtn.setBounds(950, 0, 100, 100);

		bookLb.setBounds(175, 50, 100, 100);
		rentLb.setBounds(375, 50, 100, 100);
		roomLb.setBounds(580, 50, 100, 100);
		memberLb.setBounds(775, 50, 100, 100);
		empLb.setBounds(975, 50, 100, 100);


		// 버튼 및 라벨 폰트설정
		Font font = new Font("한컴 말랑말랑 Regular", Font.PLAIN, 15);

		bookBtn.setFont(font);
		rentBtn.setFont(font);
		roomBtn.setFont(font);
		memberBtn.setFont(font);
		empBtn.setFont(font);

		bookLb.setFont(font);
		rentLb.setFont(font);
		roomLb.setFont(font);
		memberLb.setFont(font);
		empLb.setFont(font);

		bookLb.setForeground(Color.WHITE);
		rentLb.setForeground(Color.WHITE);
		roomLb.setForeground(Color.WHITE);
		memberLb.setForeground(Color.WHITE);
		empLb.setForeground(Color.WHITE);


		add(bookBtn);
		add(rentBtn);
		add(roomBtn);
		add(memberBtn);
		add(empBtn);


		add(bookLb);
		add(rentLb);
		add(roomLb);
		add(memberLb);
		add(empLb);


		// 프레임에 패널을 추가
		add(panel1);
		add(panel2);



		setBounds(0, 0, 1200, 800);
		setBackground(Color.WHITE);
		setLayout(null);
		setVisible(true);
		setResizable(false);// 창 크기 고정
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				try {
					adminLogHistoryDao = new AdminLogHistoryDao();
					adminLogHistoryDao.update(adminLogHistoryDao.getLog());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	public void open() {
		this.ManagerFrame();
	}
}