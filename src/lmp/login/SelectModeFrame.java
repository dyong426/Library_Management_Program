package lmp.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import lmp.members.MemberFrame;

public class SelectModeFrame extends JFrame{
	
	static Font font = new Font("한컴 말랑말랑 Regular", Font.PLAIN, 15);
	static Color panelColor = new Color(49, 82, 91);
	static Color btnColor = new Color(204, 139, 101);

	AdminLoginFrame adminLogFrame;
		public SelectModeFrame() {

			SelectModeFrame selectModeFrame = this;

			JButton memberBtn = new JButton("회원용") {
				{
					setBounds(80, 50, 100, 60);
					addActionListener(new ActionListener() {	
						@Override
						public void actionPerformed(ActionEvent e) {
							selectModeFrame.dispose();
							MemberFrame memberFrame = new MemberFrame();
						}
					});
				}
			};


			JButton managerBtn = new JButton("관리자용") {
				{
					setBounds(220, 50, 100, 60);
					addActionListener(new ActionListener() {	
						@Override
						public void actionPerformed(ActionEvent e) {
							AdminLoginFrame adminLoginFrame = new AdminLoginFrame(selectModeFrame);
							adminLoginFrame.setVisible(true);
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


			
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBackground(panelColor);
			panel.add(memberBtn);
			panel.add(managerBtn);
			
			getContentPane().add(panel, BorderLayout.CENTER);
			setTitle("도서관 관리 프로그램");
			setVisible(true);
			setResizable(false);
			setSize(400,200);
			setDefaultCloseOperation(this.EXIT_ON_CLOSE);
			setLocationRelativeTo(null); // 화면 중앙에 띄우기
		}

}
