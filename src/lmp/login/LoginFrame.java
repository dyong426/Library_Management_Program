package lmp.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import lmp.members.menu.mainview.jy.MemberFrame;

public class LoginFrame extends JFrame{
	
	AdminLoginFrame adminLoginframe;

	public LoginFrame() {
		LoginFrame loginFrame = this;

		JPanel panel = new JPanel();


		Color panelColor = new Color(49, 82, 91);
		Color btnColor = new Color(204, 139, 101);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(panelColor);
		panel.setSize(500, 400);

		Font font = new Font("한컴 말랑말랑 Regular", Font.PLAIN, 30);




		JButton memberBtn = new JButton("회원") {
			{
				setBounds(50, 100, 150, 150);
				addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent e) {
						MemberFrame memberframe = new MemberFrame();
						loginFrame.visible(false);
						memberframe.open();
						
					}
				});
			}
		};


		JButton managerBtn = new JButton("관리자") {
			{
				setBounds(280, 100, 150, 150);
				addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent e) {
						AdminLoginFrame AdminLoginFrame = new AdminLoginFrame(loginFrame);
						AdminLoginFrame.setVisible(true);
					}
				});
			}
		};


		memberBtn.setFont(font);
		managerBtn.setFont(font);
		memberBtn.setForeground(Color.WHITE);
		managerBtn.setForeground(Color.WHITE);

		memberBtn.setBackground(btnColor);
		managerBtn.setBackground(btnColor);

		memberBtn.setBorderPainted(false);
		managerBtn.setBorderPainted(false);


		panel.add(memberBtn);
		panel.add(managerBtn);
		add(panel);
		setTitle("도서관 관리 프로그램");
		setVisible(true);
		setResizable(false);
		setSize(new Dimension(500, 400));
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 화면 중앙에 띄우기
	}

	public void visible(boolean visible) {
		setVisible(visible);
	}
	
	
	public static void main(String[] args) {
		System.out.println("시작");
		new LoginFrame();
		
	}
}