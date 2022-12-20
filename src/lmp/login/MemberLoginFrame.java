package lmp.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import lmp.admin.AdminFrame;
import lmp.members.dao.MemberDao;
import lmp.members.dao.MemberLogHistoryDao;
import lmp.members.vo.MemberVO;

public class MemberLoginFrame extends JFrame {
	
	private JFrame frame;
	private JTextField idField;
	private JPasswordField pwField;
	
	MemberLoginFrame memberLoginFrame;
	MemberDao memberDao = new MemberDao();
	MemberLogHistoryDao memberLogHistoryDao = new MemberLogHistoryDao();

	public MemberLoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		loginBtn.setFocusPainted(false);
		loginPanel.add(loginBtn);
		
		
		
		JLabel findIDLabel = new JLabel("아이디 찾기");
		findIDLabel.setBounds(50, 236, 70, 15);
		loginPanel.add(findIDLabel);

		JLabel joinLabel = new JLabel("회원가입");
		joinLabel.setBounds(290, 236, 50, 15);
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
					System.out.println(idField.getText() + new String(pwField.getPassword()));
					System.out.println(checkLogin(idField.getText(), new String(pwField.getPassword())));

					if (checkLogin(idField.getText(), new String(pwField.getPassword()))) {
						memberLoginFrame.dispose();
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
				
				super.mouseClicked(e);
			}
		});

		joinLabel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				super.mouseClicked(e);
			}
		});
	}

	public boolean checkLogin(String mem_id, String mem_pw) {
		
		MemberVO memberVO = null;
		try {
			memberVO = (MemberVO) memberDao.get(mem_id);
			System.out.println(memberVO);
			if (memberVO == null) {
				return false;
			} else {
				if (memberVO.getPw().equals(mem_pw)) {
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

	public static void main(String[] args) {
		MemberLoginFrame ml = new MemberLoginFrame();
		ml.setVisible(true);
		
	}
	
}