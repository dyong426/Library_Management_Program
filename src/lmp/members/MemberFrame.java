package lmp.members;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lmp.db.dao.MemberLogHistoryDao;
import lmp.db.vo.MemberLogHistoryVO;
import lmp.members.menu.member.MemberMenu;
import lmp.members.menu.readingroom.ReadingRoomPanel;

public class MemberFrame extends JFrame {

	JButton homeBtn, bookMgmt, memberMgmt, readingRoom;
	
	
	JFrame f = this;
	
//	static JPanel MemberPanel = new MemberMenu();
	
	public static CardLayout card = new CardLayout();
	
	public static JPanel menuCardPanel = new JPanel(card);
	
	MemberLogHistoryDao memLogDao = new MemberLogHistoryDao();
	MemberLogHistoryVO memLogVO;
	
	ReadingRoomPanel readingroomPanel = new ReadingRoomPanel();
	MemberMenu memberMenu = new MemberMenu();
	
	
	public MemberFrame() throws SQLException {

		JPanel menuButtonPanel = new JPanel(new GridLayout(1, 4, 200, 0));

		setTitle("회원 모드");
		setLayout(null);

		BufferedImage bufferedHome = null;
		BufferedImage bufferedBookMgmt = null;
		BufferedImage bufferedCheckIn_Out = null;
		BufferedImage bufferedEmployeeMgmt = null;
		BufferedImage befferedMemberMgmt = null;
		BufferedImage bufferedReadingRoom = null;
		try {
			bufferedHome = ImageIO.read(new File("src/lmp/admin/menuButtonImages/homeIcon.png"));
			bufferedBookMgmt = ImageIO.read(new File("src/lmp/admin/menuButtonImages/bookMgmtImage.png"));
			befferedMemberMgmt = ImageIO.read(new File("src/lmp/admin/menuButtonImages/memberMgmtImage.png"));
			bufferedReadingRoom = ImageIO.read(new File("src/lmp/admin/menuButtonImages/readingRoomMgmtImage.png"));

			Image homeIcon = bufferedHome.getScaledInstance(80, 80, Image.SCALE_SMOOTH);   
			Image bookMgmtIcon = bufferedBookMgmt.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			Image memberMgmtIcon = befferedMemberMgmt.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			Image readingRoomIcon = bufferedReadingRoom.getScaledInstance(80, 80, Image.SCALE_SMOOTH);         

			// 버튼 생성 메서드 테스트
			homeBtn = getButton("홈");
			homeBtn.setIcon(new ImageIcon(homeIcon));
			homeBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					card.show(menuCardPanel, "1");
				}
			});

			bookMgmt = getButton("도서");
			bookMgmt.setIcon(new ImageIcon(bookMgmtIcon));
			bookMgmt.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					card.show(menuCardPanel, "2");

				}
			});   

			readingRoom = getButton("열람실");
			readingRoom.setIcon(new ImageIcon(readingRoomIcon));
			readingRoom.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					card.show(menuCardPanel, "3");
				}
			});

			memberMgmt = getButton("회원정보");
			memberMgmt.setIcon(new ImageIcon(memberMgmtIcon));
			memberMgmt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						if (memLogDao.getLog() != null) {
							memberMenu.refresh();
							memberMenu.validate();
							card.show(menuCardPanel, "4");
						} else {
							MemberLoginFrame mlogFrame =new MemberLoginFrame();
							mlogFrame.setVisible(true);
							
							
							mlogFrame.addWindowListener(new WindowAdapter() {
								@Override
								public void windowClosed(WindowEvent e) {
									try {
										MemberLogHistoryDao memLogDao = new MemberLogHistoryDao();
										if (memLogDao.getLog() != null) {
											memberMenu.refresh();
											memberMenu.validate();
											card.show(menuCardPanel, "4");
										}
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
								}
							});
						}

					} catch (SQLException e1) {
					}
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}         

		menuButtonPanel.setBounds(350, 0, 1200, 200);
		menuButtonPanel.setBackground(new Color(42, 64, 61));

		menuCardPanel.setBounds(200, 220, 1500, 750);
		menuCardPanel.add("1", initialLabel());
//		menuCardPanel.add("2", new BookSearchFrame());
		
		menuCardPanel.add("3", readingroomPanel);
		menuCardPanel.add("4", memberMenu);
		


		menuButtonPanel.add(homeBtn);
		menuButtonPanel.add(bookMgmt);
		menuButtonPanel.add(readingRoom);
		menuButtonPanel.add(memberMgmt);

		add(menuButtonPanel);
		add(menuCardPanel);

		setBounds(300, 100, 1200, 800);
		getContentPane().setBackground(new Color(42, 64, 61));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setExtendedState(this.MAXIMIZED_BOTH);
		
		// 창 종료시 로그아웃
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					if(memLogDao.getLog() != null) {
					memLogDao.update(memLogDao.getLog());
					System.out.println("로그아웃");
					}
				} catch (SQLException e1) {
					return;
				}
			}
		});
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
				setFont(new Font("한컴 말랑말랑 Regular",Font.BOLD, 15));
				setText(text);
				setToolTipText(text);
				setBorderPainted(false);
				setFocusPainted(false);
				setContentAreaFilled(false);
			}
		};
	}


	public static void main(String[] args) {
			try {
				new MemberFrame();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}

