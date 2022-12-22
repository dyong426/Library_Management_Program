package lmp.admin;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import lmp.admin.menu.book.BookMgmt;
import lmp.admin.menu.checkin_out.Member_Searching_Panel;
import lmp.admin.menu.employees.EmployeesMgmt;
import lmp.admin.menu.member.MemberMgmt;
import lmp.admin.menu.readingroom.ReadingRoomPanel;


public class AdminFrame extends JFrame {

	JButton bookMgmt, checkIn_Out, employeeMgmt, memberMgmt, readingRoom, homeButton, logoutButton;
	
	JFrame f = this;
	
	
	JPanel bookPanel = new BookMgmt();
	JPanel checkInOutPanel = new Member_Searching_Panel();
	JPanel employeePanel = new EmployeesMgmt();
	JPanel memberPanel = new MemberMgmt();
	JPanel readingRoomPanel = new ReadingRoomPanel();
//	JPanel readingRoomPanel = new ReadingRoomMgmt();
	
	
	public AdminFrame() {
		
		setTitle("관리자 모드");
		setLayout(null);
		
		
		JPanel menuButtonPanel = new JPanel(new GridLayout(1, 5, 100, 0));
		
		CardLayout card = new CardLayout();
		JPanel menuCardPanel = new JPanel(card);
		
		
		BufferedImage bufferedBookMgmt = null;
		BufferedImage bufferedCheckIn_Out = null;
		BufferedImage bufferedEmployeeMgmt = null;
		BufferedImage befferedMemberMgmt = null;
		BufferedImage bufferedReadingRoom = null;
		BufferedImage bufferedHomeIcon = null;
		try {
			bufferedBookMgmt = ImageIO.read(new File("src/lmp/admin/menuButtonImages/bookMgmtImage.png"));
			bufferedCheckIn_Out = ImageIO.read(new File("src/lmp/admin/menuButtonImages/checkIn_OutMgmtImage.png"));
			bufferedEmployeeMgmt = ImageIO.read(new File("src/lmp/admin/menuButtonImages/employeeMgmtImage.png"));
			befferedMemberMgmt = ImageIO.read(new File("src/lmp/admin/menuButtonImages/memberMgmtImage.png"));
			bufferedReadingRoom = ImageIO.read(new File("src/lmp/admin/menuButtonImages/readingRoomMgmtImage.png"));
			bufferedHomeIcon = ImageIO.read(new File("src/lmp/admin/menuButtonImages/homeIcon.png"));
			
			Image bookMgmtIcon = bufferedBookMgmt.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			Image checkIn_OutIcon = bufferedCheckIn_Out.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			Image employeeMgmtIcon = bufferedEmployeeMgmt.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			Image memberMgmtIcon = befferedMemberMgmt.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			Image readingRoomIcon = bufferedReadingRoom.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			Image homeIcon = bufferedHomeIcon.getScaledInstance(30, 25, Image.SCALE_SMOOTH);		
			
			// 버튼 생성 메서드 테스트
			bookMgmt = getButton("도서 관리");
			bookMgmt.setIcon(new ImageIcon(bookMgmtIcon));
			bookMgmt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					card.show(menuCardPanel, "2");
				}
			});
//			bookMgmt = new JButton(new ImageIcon(bookMgmtIcon)) {
//				{
//				setBackground(Color.GRAY);
//				setBorderPainted(false);
//				setFocusPainted(false);
//				setContentAreaFilled(false);
//				addMouseListener(new MouseAdapter() {
//					// 버튼에 마우스 올리면 배경색 변경
//					@Override
//					public void mouseEntered(MouseEvent e) {
//						setContentAreaFilled(true);
//					}
//					// 버튼에서 마우스 떼면 배경색 투명
//					@Override
//					public void mouseExited(MouseEvent e) {
//						setContentAreaFilled(false);
//					}
//				});
//				}
//			};
			checkIn_Out = getButton("대출 / 반납");
			checkIn_Out.setIcon(new ImageIcon(checkIn_OutIcon));
			checkIn_Out.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					card.show(menuCardPanel, "3");
				}
			});
			
			employeeMgmt = getButton("직원 관리");
			employeeMgmt.setIcon(new ImageIcon(employeeMgmtIcon));
			employeeMgmt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					card.show(menuCardPanel, "4");
				}
			});
			
			memberMgmt = getButton("회원 관리");
			memberMgmt.setIcon(new ImageIcon(memberMgmtIcon));
			memberMgmt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					card.show(menuCardPanel, "5");
				}
			});
			
			readingRoom = getButton("열람실 관리");
			readingRoom.setIcon(new ImageIcon(readingRoomIcon));
			readingRoom.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					card.show(menuCardPanel, "6");
				}
			});
			
			homeButton = getButton("");
			homeButton.setIcon(new ImageIcon(homeIcon));
			homeButton.setBounds(5, 5, 50, 50);
			homeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					card.show(menuCardPanel, "1");
				}
			});
			
			logoutButton = getButton("로그아웃");
			logoutButton.setOpaque(true);
			logoutButton.setBackground(new Color(87, 119, 119));
			logoutButton.setBounds(1800, 10, 100, 30);
			logoutButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int num = JOptionPane.showConfirmDialog(
							f, "로그아웃 하시겠습니까?", "로그아웃 확인", JOptionPane.YES_NO_OPTION);
					switch (num) {
					case 0 :
						// 로그아웃
						break;
					case 1 :
						JOptionPane.showMessageDialog(f, "취소합니다.");
						break;
					}
				}
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		menuButtonPanel.setBounds(350, 0, 1200, 200);
		menuButtonPanel.setBackground(new Color(49, 82, 91));
		
		menuCardPanel.setBounds(200, 220, 1500, 750);
		menuCardPanel.add("1", initialLabel());
		menuCardPanel.add("2", bookPanel);
		menuCardPanel.add("3", checkInOutPanel);
		menuCardPanel.add("4", employeePanel);
		menuCardPanel.add("5", memberPanel);
		menuCardPanel.add("6", readingRoomPanel);
		
		menuButtonPanel.add(bookMgmt);
		menuButtonPanel.add(checkIn_Out);
		menuButtonPanel.add(employeeMgmt);
		menuButtonPanel.add(memberMgmt);
		menuButtonPanel.add(readingRoom);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1900, 1000));
		panel.setBackground(new Color(49, 82, 91));
		panel.add(homeButton);
		panel.add(logoutButton);
		panel.add(menuButtonPanel);
		panel.add(menuCardPanel);
		
		JScrollPane sp = new JScrollPane();
//		sp.setPreferredSize(new Dimension(1200, 800));
//		sp.setBounds(0, 0, 1200, 800);
//		sp.setViewportView(panel);
		sp.setViewportView(panel);
		// 스크롤 속도 조절
		sp.getVerticalScrollBar().setUnitIncrement(16);
//		add(homeButton);
//		add(menuButtonPanel);
//		add(menuCardPanel);
		
//		setExtendedState(MAXIMIZED_BOTH);
		getContentPane().add(sp);
		setContentPane(sp);
		
		
//		setMinimumSize(new Dimension(500, 400));
		// 최대화 사이즈 모니터 최대 사이즈에 맞추기
		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		setBounds(300, 100, 1200, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setResizable(false);
		setVisible(true);
		
		
	}
	
	// 버튼 누르기 전 초기 화면 이미지 설정
	public static JPanel initialLabel() {
		JPanel initImagePanel = new JPanel();
		JLabel label = new JLabel();
		try {
			BufferedImage buffer = ImageIO.read(new File("src/lmp/admin/menuButtonImages/initialImage.jpg"));
			Image image = buffer.getScaledInstance(1500, 750, Image.SCALE_SMOOTH);
			label.setIcon(new ImageIcon(image));
		} catch (IOException e) {
			e.printStackTrace();
		}
		label.setSize(1500, 750);
		
		initImagePanel.setLayout(null);
		initImagePanel.add(label);
		
		return initImagePanel;
	}
	
	// 버튼 생성 및 설정 메서드
	public static JButton getButton(String text) {
		 return new JButton() {
			 {
				setHorizontalTextPosition(CENTER);
				setVerticalTextPosition(BOTTOM);
				setForeground(Color.WHITE);
				setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 15));
				setText(text);
				if (!getText().equals("")) {
					setToolTipText(text);
				}
				setBorderPainted(false);
				setFocusPainted(false);
				setContentAreaFilled(false);
				addMouseListener(new MouseAdapter() {
					// 버튼에 마우스 올리면 테두리 생성
					@Override
					public void mouseEntered(MouseEvent e) {
						Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
						setCursor(cursor);
					}
					// 버튼에서 마우스 떼면 테두리 삭제
					@Override
					public void mouseExited(MouseEvent e) {
						Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
						setCursor(cursor);
					}
				});
				}
		 };
	}
	
	public static JTable getTable(DefaultTableModel model) {
		JTable table = new JTable(model);
		
		table.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 15));
		table.setRowHeight(25);
		// 테이블 컬럼 이동 안되게 설정
		table.getTableHeader().setReorderingAllowed(false);
		// 테이블에서 하나의 행만 선택되게 설정
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		return table;
	}
	
	
	public static void main(String[] args) {
		new AdminFrame();
	}
}
