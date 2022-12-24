package lmp.members;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import lmp.db.dao.MemberDao;
import lmp.db.dao.MemberLogHistoryDao;
import lmp.db.vo.MemberVO;
import lmp.members.menu.findId_Pw.FindID;
import lmp.members.menu.member.MemberMenu;
import lmp.members.menu.readingroom.usagelist.label.UsageListCheckOutLabel;
import lmp.util.ShaPasswordEncoder;

public class MemberLoginFrame extends JFrame {

	private JTextField idField;
	private JPasswordField pwField;

	MemberLoginFrame memberLoginFrame;
	MemberDao memberDao = new MemberDao();
	MemberLogHistoryDao memberLogHistoryDao = new MemberLogHistoryDao();

	Font font = new Font("한컴 말랑말랑 Regular", Font.PLAIN, 15);
	
	public MemberLoginFrame() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		memberLoginFrame = this;
		setAutoRequestFocus(false);
		setBounds(100, 100, 400, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);



		JPanel loginPanel = new JPanel();
		getContentPane().add(loginPanel, BorderLayout.CENTER);
		loginPanel.setLayout(null);
		loginPanel.setBackground(new Color(186, 206, 194));
		loginPanel.setFocusCycleRoot(true);



		idField = new JTextField("아이디");
		idField.setBounds(50, 75, 300, 35);
		loginPanel.add(idField);
		idField.setColumns(30);

		JTextField pwTField = new JTextField("비밀번호");

		pwTField.setBounds(50, 120, 300, 35);
		loginPanel.add(pwTField);


		pwField = new JPasswordField();
		pwField.setBounds(50, 120, 300, 35);
		loginPanel.add(pwField);
		pwField.setColumns(30);

		JButton loginBtn = new JButton("로그인");
		loginBtn.setBounds(49, 170, 302, 40);
		loginBtn.setBackground(new Color(87, 119, 119));
		loginBtn.setFont(font);
		loginBtn.setForeground(Color.WHITE);
		loginBtn.addMouseListener(getMouseListener());
		loginBtn.setFocusPainted(false);
		loginPanel.add(loginBtn);



		JLabel findIDLabel = new JLabel("아이디 찾기");
		findIDLabel.setBounds(50, 230, 80, 15);
		findIDLabel.setFont(font);
		findIDLabel.addMouseListener(getMouseListener());
		loginPanel.add(findIDLabel);
		
		JLabel joinLabel = new JLabel("회원가입");
		joinLabel.setBounds(290, 230, 70, 15);
		joinLabel.setFont(font);
		joinLabel.addMouseListener(getMouseListener());
		loginPanel.add(joinLabel);

		JLabel loginImageLabel = new JLabel("이미지");
		loginImageLabel.setFont(new Font("굴림", Font.PLAIN, 40));
		loginImageLabel.setBounds(129, 10, 146, 40);

		loginPanel.add(loginImageLabel);


		idField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				if (idField.getText().equals("아이디")) {
					idField.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {

				if (idField.getText().equals("")) {
					idField.setText("아이디");
				}

			}
		});
		
		pwTField.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent e) {
				pwTField.setVisible(false);
				pwField.setFocusable(true);
				pwField.requestFocusInWindow();

			}
		});
		
		pwField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (new String(pwField.getPassword()).equals("")) {               
					pwTField.setVisible(true);
				}
			}
		});
		
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkLogin(idField.getText(), new String(pwField.getPassword()))) {
						// 로그인 성공하면 로그인 기록 업데이트 후 5분 타이머 시작
						TimerTask task = new TimerTask() {
							@Override
							public void run() {
								try {
									memberLogHistoryDao.update(memberLogHistoryDao.getLog());
									memberLoginFrame.initialize();
									memberLoginFrame.validate();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
						};
						
						Timer timer = new Timer();
						long delay = 1000L * 60 * 5;
						
						timer.schedule(task, delay);
						
						memberLoginFrame.dispose();
//						MemberMenu memberMenu = new MemberMenu();
//						MemberFrame.menuCardPanel.add("3", memberMenu);
//						MemberFrame.card.show(MemberFrame.menuCardPanel, "3");
//						mem_id = idField.getText();
//						
					} else {
						JOptionPane.showMessageDialog(memberLoginFrame, "아이디/비밀번호를 확인하세요");
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				}
			}
		});

		findIDLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new FindID();
			}
		});

		joinLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MemberJoin();
			}
		});
		
		memberLoginFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				memberLoginFrame.initialize();
				memberLoginFrame.validate();
			}
		});
	}
	
	public MouseListener getMouseListener() {
		MouseListener mouse = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {				
				setCursor(null);
			}
		};
		return mouse;
	}
	
	public boolean checkLogin(String mem_id, String mem_pw) {
		ShaPasswordEncoder pwEncoder = new ShaPasswordEncoder();
		MemberVO memberVO = null;
		try {
			memberVO = (MemberVO) memberDao.get3(mem_id);
			if (memberVO == null) {
				return false;
			} else {
				if (pwEncoder.matches(mem_pw, memberVO.getPw())) {
					memberLogHistoryDao.add(memberVO);
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

