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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import lmp.admin.dao.SeatUseDetailDao;
import lmp.admin.menu.book.BookMgmt;
import lmp.admin.menu.checkin_out.Member_Searching_Panel;
import lmp.admin.menu.employees.EmployeesMgmt;
import lmp.admin.menu.member.MemberMgmt;
import lmp.admin.menu.readingroom.ReadingRoomPanel;
import lmp.admin.vo.SeatUseDetailVO;
import lmp.util.ImageConvert;


public class AdminFrame extends JFrame {

	JButton bookMgmt, checkIn_Out, employeeMgmt, memberMgmt, readingRoom, homeButton, settingButton;
	
	JFrame f = this;
	
	
	JPanel bookPanel = new BookMgmt();
	JPanel checkInOutPanel = new Member_Searching_Panel();
	JPanel employeePanel = new EmployeesMgmt();
	JPanel memberPanel = new MemberMgmt();
	JPanel readingRoomPanel = new ReadingRoomPanel();
//	JPanel readingRoomPanel = new ReadingRoomMgmt();
	
	static ImageConvert img = new ImageConvert();
	
	public AdminFrame() {
		
		setTitle("관리자 모드");
		setLayout(null);
		
		// 자정 되면 전좌석 강제 퇴실
		Calendar date = Calendar.getInstance();
		date.set(Calendar.HOUR_OF_DAY, 20);
		date.set(Calendar.MINUTE, 03);
		date.set(Calendar.SECOND, 30);
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				SeatUseDetailDao sDao = new SeatUseDetailDao();
				ArrayList<SeatUseDetailVO> sVo = new ArrayList<>();
				try {
					sVo.addAll(sDao.get());
					
					for (SeatUseDetailVO seat : sVo) {
						sDao.update(seat.getUse_id());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};
		timer.schedule(task, date.getTime());		
		
		JPanel menuButtonPanel = new JPanel(new GridLayout(1, 5, 100, 0));
		
		CardLayout card = new CardLayout();
		JPanel menuCardPanel = new JPanel(card);
		
		// 버튼 생성 메서드 테스트
		bookMgmt = getButton("도서 관리");
		bookMgmt.setIcon(img.scaledMenuImage("book"));
		bookMgmt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(menuCardPanel, "2");
			}
		});
		
		checkIn_Out = getButton("대출 / 반납");
		checkIn_Out.setIcon(img.scaledMenuImage("barcode"));
		checkIn_Out.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(menuCardPanel, "3");
			}
		});
		
		employeeMgmt = getButton("직원 관리");
		employeeMgmt.setIcon(img.scaledMenuImage("employee"));
		employeeMgmt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(menuCardPanel, "6");
			}
		});
		
		memberMgmt = getButton("회원 관리");
		memberMgmt.setIcon(img.scaledMenuImage("member"));
		memberMgmt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(menuCardPanel, "5");
			}
		});
		
		readingRoom = getButton("열람실 관리");
		readingRoom.setIcon(img.scaledMenuImage("readingroom"));
		readingRoom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(menuCardPanel, "4");
			}
		});
		
		homeButton = getButton("");
		homeButton.setIcon(img.scaledMgmtImage("home"));
		homeButton.setBounds(10, 10, 60, 60);
		homeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(menuCardPanel, "1");
			}
		});
		
		settingButton = getButton("");
		settingButton.setIcon(img.scaledMgmtImage("setup"));
		settingButton.setBounds(80, 10, 60, 60);
		
		menuButtonPanel.setBounds(350, 0, 1200, 200);
		menuButtonPanel.setBackground(new Color(49, 82, 91));
		
		menuCardPanel.setBounds(200, 220, 1500, 750);
		menuCardPanel.add("1", initialLabel());
		menuCardPanel.add("2", bookPanel);
		menuCardPanel.add("3", checkInOutPanel);
		menuCardPanel.add("4", readingRoomPanel);
		menuCardPanel.add("5", memberPanel);
		menuCardPanel.add("6", employeePanel);
		
		menuButtonPanel.add(bookMgmt);
		menuButtonPanel.add(checkIn_Out);
		menuButtonPanel.add(readingRoom);
		menuButtonPanel.add(memberMgmt);
		menuButtonPanel.add(employeeMgmt);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1900, 1000));
		panel.setBackground(new Color(49, 82, 91));
		panel.add(homeButton);
		panel.add(settingButton);
		panel.add(menuButtonPanel);
		panel.add(menuCardPanel);
		
//		sp.setPreferredSize(new Dimension(1200, 800));
//		sp.setBounds(0, 0, 1200, 800);
//		sp.setViewportView(panel);
//		add(homeButton);
//		add(menuButtonPanel);
//		add(menuCardPanel);
		
//		setExtendedState(MAXIMIZED_BOTH);
		JScrollPane sp = new JScrollPane();
		sp.setViewportView(panel);
		sp.getVerticalScrollBar().setUnitIncrement(16);
		getContentPane().add(sp);
		setContentPane(sp);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(300, 100, 1200, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	// 버튼 누르기 전 초기 화면 이미지 설정
	public static JPanel initialLabel() {
		JPanel initImagePanel = new JPanel();
		JLabel label = new JLabel();
		
		label.setIcon(img.scaledPanelImage("initial"));
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
