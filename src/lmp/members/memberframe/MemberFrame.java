package lmp.members.memberframe;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import lmp.db.dao.MemberLogHistoryDao;
import lmp.db.vo.MemberLogHistoryVO;
import lmp.members.MemberLoginFrame;
import lmp.members.dao.ThemeDao;
import lmp.members.menu.book.BookSearchPanel;
import lmp.members.menu.member.MemberMenu;
import lmp.members.menu.readingroom.ReadingRoomPanel;
import lmp.members.menu.setting.SettingMenu;
import lmp.util.ImageConvert;
import lmp.util.theme.Theme;

public class MemberFrame extends JFrame {

	JButton homeBtn, bookMgmt, memberMgmt, readingRoom, settingBtn;
	
	JLabel clockLabel;
//	static JPanel MemberPanel = new MemberMenu();
	
	public static CardLayout card = new CardLayout();
	
	public static JPanel menuCardPanel = new JPanel(card);
	
	
	ReadingRoomPanel readingroomPanel = new ReadingRoomPanel();
	MemberMenu memberMenu = new MemberMenu();
	BookSearchPanel bookSearchPanel = new BookSearchPanel();
	SettingMenu setMenu;

	MemberLogHistoryDao memLogDao = new MemberLogHistoryDao();
	MemberLogHistoryVO memLogVO;
	
	ThemeDao themeDao = new ThemeDao();
	Theme theme = new Theme();
	
	static ImageConvert img = new ImageConvert();
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 a HH : mm");
	
	public MemberFrame() throws SQLException {
		
		setBounds(300, 100, 1200, 800);
		initialize();
	}
	
	public void initialize() throws SQLException {
		MemberFrame memberFrame = this;
		
		setTitle("회원 모드");
		setLayout(null);
		
		// 현재 시간 라벨 (분 단위 표시)
		clockLabel = new JLabel(sdf.format(new GregorianCalendar().getTime()));
		clockLabel.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 20));
		clockLabel.setForeground(Color.WHITE);
		clockLabel.setBounds(5, 5, 300, 30);
		
		javax.swing.Timer timers = new javax.swing.Timer(1000, updateClockAction);
		timers.start();
		
		JButton loginBtn = new JButton("로그인");
		loginBtn.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 20));
		loginBtn.setBackground(Color.WHITE);
		loginBtn.setForeground(Color.RED);
		loginBtn.setFocusable(false);
		loginBtn.setBounds(1770, 10, 120, 50);
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton logoutBtn = new JButton("로그아웃");
		logoutBtn.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 20));
		logoutBtn.setBackground(Color.WHITE);
		logoutBtn.setForeground(Color.RED);
		logoutBtn.setFocusable(false);
		logoutBtn.setBounds(1770, 10, 120, 50);


		theme.setTheme(themeDao.getTheme());
		JPanel menuButtonPanel = new JPanel(new GridLayout(1, 4, 100, 0));

		logoutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (memLogDao.getLog() == null) {
						return;
					} else {
						int var = JOptionPane.showConfirmDialog
								(null, "로그아웃 하시겠습니까?", "로그아웃",
										JOptionPane.YES_NO_OPTION,
										JOptionPane.INFORMATION_MESSAGE, null);
						if (var == JOptionPane.YES_OPTION) {
							memLogDao.update(memLogDao.getLog());
							System.out.println("로그아웃");
							MemberFrame.card.show(MemberFrame.menuCardPanel, "1");
						}
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});

//		theme.setTheme(themeDao.getTheme(1));

		settingBtn = getButton("설정");
		settingBtn.setBounds(30, 30, 30, 30);
		settingBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
//					setMenu = new SettingMenu(memberFrame);
//					setMenu.setVisible(true);
					theme.setTheme(themeDao.getTheme());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		// 버튼 생성 메서드 테스트
		homeBtn = getButton("홈");
		homeBtn.setIcon(img.scaledMenuImage("homeIcon.png"));
		homeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(menuCardPanel, "1");
			}
		});

		bookMgmt = getButton("도서");
		bookMgmt.setIcon(img.scaledMenuImage("bookMgmtImage.png"));
		bookMgmt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(menuCardPanel, "2");

			}
		});

		readingRoom = getButton("열람실");
		readingRoom.setIcon(img.scaledMenuImage("memberMgmtImage.png"));
		readingRoom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(menuCardPanel, "3");
			}
		});

		memberMgmt = getButton("회원정보");
		memberMgmt.setIcon(img.scaledMenuImage("readingRoomMgmtImage.png"));
		memberMgmt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (memLogDao.getLog() != null) {
						memberMenu.refresh();
						memberMenu.validate();
						card.show(menuCardPanel, "4");
					} else {
						MemberLoginFrame mlogFrame = new MemberLoginFrame();
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

		menuButtonPanel.setBounds(350, 0, 1200, 200);
		menuButtonPanel.setBackground(theme.getMainColor());
		
		menuButtonPanel.add(homeBtn);
		menuButtonPanel.add(bookMgmt);
		menuButtonPanel.add(readingRoom);
		menuButtonPanel.add(memberMgmt);

		menuCardPanel.setBounds(200, 220, 1500, 750);
		menuCardPanel.add("1", initialLabel());
		menuCardPanel.add("2", bookSearchPanel);
		menuCardPanel.add("3", readingroomPanel);
		menuCardPanel.add("4", memberMenu);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1900, 1000));
		panel.setBackground(theme.getMainColor());
		panel.add(menuButtonPanel);
		panel.add(menuCardPanel);

		panel.add(settingBtn);
		panel.add(clockLabel);
		panel.add(logoutBtn);

		
		JScrollPane sp = new JScrollPane();
		sp.setViewportView(panel);
		sp.getVerticalScrollBar().setUnitIncrement(16);
		getContentPane().add(sp);
		setContentPane(sp);
		
		
//		getContentPane().setBackground(defaultTheme.getMainColor());
		setExtendedState(this.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
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
		
	
		label.setIcon(img.scaledPanelImage("initialImage.jpg"));
	
		
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

	ActionListener updateClockAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			clockLabel.setText(sdf.format(new GregorianCalendar().getTime()));
		}
	};

	public static void main(String[] args) {
		try {
			new MemberFrame();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

