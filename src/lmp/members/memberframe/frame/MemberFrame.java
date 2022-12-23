package lmp.members.memberframe.frame;

import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import lmp.members.dao.ThemeDao;
import lmp.members.memberframe.button.LogButton;
import lmp.members.memberframe.button.MenuButton;
import lmp.members.memberframe.button.OptionButton;
import lmp.members.memberframe.button.listener.LogButtonListener;
import lmp.members.memberframe.button.listener.MenuButtonListener;
import lmp.members.memberframe.button.listener.OptionButtonListener;
import lmp.members.memberframe.label.ClockLabel;
import lmp.members.memberframe.panel.HomePanel;
import lmp.members.memberframe.panel.MenuButtonPanel;
import lmp.members.memberframe.panel.MenuCardPanel;
import lmp.members.menu.book.BookSearchPanel;
import lmp.members.menu.member.MemberMenu;
import lmp.members.menu.readingroom.ReadingRoomPanel;
import lmp.util.theme.Theme;


public class MemberFrame extends JFrame{
	
	JPanel panel = new JPanel();
	JScrollPane sp = new JScrollPane();
	
	MenuButtonPanel menuButtonPanel;
	private MenuCardPanel menuCardPanel;
	
	static MenuButton bookBtn = new MenuButton("book");
	static MenuButton readingroomBtn = new MenuButton("readingroom"); 
	static MenuButton memberBtn = new MenuButton("member");

	static HomePanel		homePanel = new HomePanel();
	BookSearchPanel bookSearchPanel = new BookSearchPanel();
	ReadingRoomPanel readingroomPanel = new ReadingRoomPanel();
	MemberMenu memberMenuPanel = new MemberMenu();
	
	private LogButton loginButton = new LogButton("로그인");
	private LogButton logoutButton = new LogButton("로그아웃");
	
	ClockLabel clockLabel = new ClockLabel();
	
	static OptionButton homeButton = new OptionButton("home");
	static OptionButton setupButton = new OptionButton("setup");
	
	ThemeDao themeDao;
	Theme theme = new Theme();
	
	public MemberFrame() throws SQLException {
		setLayout(null);
		setBounds(300, 100, 1200, 800);
		setTitle("회원 모드");
		MemberFrame memberFrame = this;
		themeDao = new ThemeDao();
		
		menuButtonPanel = new MenuButtonPanel();
		menuCardPanel = new MenuCardPanel();
		
		bookBtn.addActionListener(new MenuButtonListener(this));
		readingroomBtn.addActionListener(new MenuButtonListener(this));
		memberBtn.addActionListener(new MenuButtonListener(this));
		
		loginButton.addActionListener(new LogButtonListener(this));
		logoutButton.addActionListener(new LogButtonListener(this)); 
		homeButton.addActionListener(new OptionButtonListener(this));
		setupButton.addActionListener(new OptionButtonListener(this));
		
		menuButtonPanel.add(bookBtn);
		menuButtonPanel.add(readingroomBtn);
		menuButtonPanel.add(memberBtn);
		
		menuCardPanel.add("홈 화면", homePanel);
		menuCardPanel.add("도서검색", bookSearchPanel);
		menuCardPanel.add("열람실", readingroomPanel);
		menuCardPanel.add("회원정보", memberMenuPanel);
		
		panel.add(clockLabel);
		panel.add(homeButton);
		panel.add(setupButton);
		panel.add(loginButton);
		panel.add(logoutButton);
		panel.add(menuButtonPanel);
		panel.add(menuCardPanel);
		initialize();
		
		setExtendedState(this.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
		getContentPane().add(sp);
		setContentPane(sp);
		setVisible(true);
		
	}

	public void initialize() throws SQLException {
		String getTheme = themeDao.getTheme();
		theme.setTheme(getTheme);
	
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1900, 1000));
		panel.setBackground(theme.getMainColor());
		sp.setViewportView(panel);
		sp.getVerticalScrollBar().setUnitIncrement(16);
	}

	
	
	public MenuCardPanel getMenuCardPanel() {
		return menuCardPanel;
	}

	public LogButton getLoginButton() {
		return loginButton;
	}

	public MemberMenu getMemberMenuPanel() {
		return memberMenuPanel;
	}
	
	
	
}
