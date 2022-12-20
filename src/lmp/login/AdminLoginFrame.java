package lmp.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import lmp.admin.dao.AdminDao;
import lmp.admin.dao.AdminLogHistoryDao;
import lmp.admin.vo.AdminVO;
import lmp.members.menu.mainview.jy.ManagerFrame;

public class AdminLoginFrame extends JFrame{

	LoginFrame loginFrame;
	AdminLoginFrame adminLoginFrame;
	ManagerFrame managerframe;						
	
	AdminDao adminDao = new AdminDao();
	AdminLogHistoryDao adminLogHistoryDao = new AdminLogHistoryDao();

	public AdminLoginFrame(LoginFrame loginFrame) {

		this.loginFrame = loginFrame;
		adminLoginFrame = this;
		
		JPanel panel = new JPanel();

		Font font = new Font("한컴 말랑말랑 Regular", Font.BOLD, 14);

		Color panelColor = new Color(49, 82, 91);
		Color btnColor = new Color(204, 139, 101);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(panelColor);
		panel.setSize(400, 300);

		JLabel managerIdLabel = new JLabel("관리자번호");
		JLabel managerPwLabel = new JLabel("관리자암호");
		managerIdLabel.setBounds(40, 80, 100, 30);
		managerPwLabel.setBounds(40, 120, 100, 30);
		managerIdLabel.setFont(font);
		managerPwLabel.setFont(font);
		managerIdLabel.setForeground(Color.WHITE);
		managerPwLabel.setForeground(Color.WHITE);


		JTextField managerIdTf = new JTextField("관리자번호를 입력하세요");
		JPasswordField managerPwTf = new JPasswordField("관리자암호를 입력하세요");
		managerIdTf.setBounds(130, 80, 180, 30);
		managerPwTf.setBounds(130, 120, 180, 30);
		managerIdTf.setForeground(Color.LIGHT_GRAY);
		managerPwTf.setForeground(Color.LIGHT_GRAY);
		managerIdTf.setBorder(null);
		managerPwTf.setBorder(null);

		managerIdTf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				managerIdTf.setText("");
			}
		});
		
		managerPwTf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				managerPwTf.setText("");
			}
		});
		
		JButton loginBtn = new JButton("로그인");
		
		
		loginBtn.setBounds(90, 180, 80, 30);
		loginBtn.setFont(font);
		loginBtn.setBackground(Color.WHITE);
		loginBtn.setForeground(Color.GRAY);
		loginBtn.setBorderPainted(false);
		loginBtn.addActionListener(new ActionListener () {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(managerIdTf.getText() + new String(managerPwTf.getPassword()));
					if (checkLogin(managerIdTf.getText(),new String(managerPwTf.getPassword()))) {
						adminLoginFrame.dispose();
						loginFrame.dispose();
						managerframe = new ManagerFrame();
						managerframe.open();
					} else {
						JOptionPane.showMessageDialog(adminLoginFrame, "사원번호/비밀번호를 확인하세요");	
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				}
			}
			
		});

		JButton cancelBtn = new JButton("취소") {
			{
				setBounds(220, 180, 80, 30);
				addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent e) {
						adminLoginFrame.setVisible(false);
					}
				});
			}
		};
		
		cancelBtn.setFont(font);
		cancelBtn.setBackground(Color.WHITE);
		cancelBtn.setForeground(Color.GRAY);
		cancelBtn.setBorderPainted(false);





		add(loginBtn);
		add(cancelBtn);

		add(managerIdLabel);
		add(managerPwLabel);
		add(managerIdTf);
		add(managerPwTf);
		add(panel);
		setTitle("관리자 로그인");
		setVisible(true);
		setResizable(false);
		setSize(new Dimension(400, 300));
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 화면 중앙에 띄우기
	}

	public void visible(boolean visible) {
		setVisible(visible);
	}
	
	public boolean checkLogin(String admin_num, String admin_pw) {
		AdminVO adminVO;
		try {
			adminVO = adminDao.getAdminInfo(Integer.parseInt(admin_num));
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
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}