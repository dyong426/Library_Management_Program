package lmp.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import lmp.admin.AdminFrame;
import lmp.admin.dao.AdminDao;
import lmp.admin.dao.AdminLogHistoryDao;
import lmp.admin.vo.AdminVO;
import lmp.members.dao.MemberDao;
import lmp.members.dao.MemberLogHistoryDao;
import lmp.members.vo.MemberVO;

public class AdminLoginFrame extends JFrame{

	SelectModeFrame selectModeFrame;
	AdminLoginFrame adminLoginFrame;
	AdminFrame adminFrame;						
	
	AdminLogHistoryDao adminLogHistoryDao = new AdminLogHistoryDao();
	AdminDao adminDao = new AdminDao();

	private JTextField idField;
	private JPasswordField pwField;

	public AdminLoginFrame(SelectModeFrame selectModeFrame) {
		this.selectModeFrame = selectModeFrame;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		adminLoginFrame = this;
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
		
		
		
		
		idField = new JTextField("사원번호");
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

		JLabel loginImageLabel = new JLabel("이미지");
		loginImageLabel.setFont(new Font("굴림", Font.PLAIN, 40));
		loginImageLabel.setBounds(129, 10, 146, 40);
		
		loginPanel.add(loginImageLabel);
		
		
		idField.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent e) {
				if (idField.getText().equals("사원번호")) {
					idField.setText("");
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				
				if (idField.getText().equals("")) {
					idField.setText("사원번호");
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
					if (checkLogin(idField.getText(), new String(pwField.getPassword()))) {
						adminLoginFrame.dispose();
						selectModeFrame.dispose();
						adminFrame = new AdminFrame();

					} else {
						JOptionPane.showMessageDialog(adminLoginFrame, "사원번호/비밀번호를 확인하세요");
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				}
			}

		});
	}

	public boolean checkLogin(String admin_num, String admin_pw) {
		AdminVO adminVO = null;
		try {
			
			adminVO = adminDao.getAdminInfo(Integer.parseInt(admin_num));
			System.out.println(adminVO);
			if (adminVO == null) {
				return false;
			} else {
				if (adminVO.getPw().equals(admin_pw)) {
					adminLogHistoryDao.add(adminVO);
					return true;
				} else {
					return false;
				}
			}
			
		}catch (NumberFormatException nfe) {
			System.out.println("사원번호 확인");
			return false;
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception");
			return false;
		}
	}
}
