package lmp.members.memberframe.button;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import lmp.login.LoginStatus;

public class LogButton extends JButton implements LoginStatus{

	static boolean log;
	
	public LogButton(String text) {
		setText(text);
		
		if (text.equals("로그인")) {
			setBounds(1620, 10, 120, 50);
		} else {
			setBounds(1770, 10, 120, 50);
		}
		initialize();
		System.out.println(log);
	}
	
	public void initialize() {
		
		setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 20));
		setBackground(Color.WHITE);
		setForeground(Color.RED);
		setFocusable(false);
		if (log) {
			setVisible(false);
		}
	}

	@Override
	public void fail() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void success() {
		// TODO Auto-generated method stub
		
	}	
}
