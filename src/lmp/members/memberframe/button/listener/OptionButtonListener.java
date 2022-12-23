package lmp.members.memberframe.button.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import lmp.members.dao.ThemeDao;
import lmp.members.memberframe.button.OptionButton;
import lmp.members.memberframe.frame.MemberFrame;
import lmp.members.menu.setting.SettingMenu;
import lmp.util.theme.Theme;

public class OptionButtonListener implements ActionListener{

	MemberFrame memberFrame;
	SettingMenu setMenu;
	ThemeDao themeDao = new ThemeDao();
	Theme theme = new Theme();
	
	public OptionButtonListener(MemberFrame memberFrame) {
		this.memberFrame = memberFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		OptionButton btn = (OptionButton) e.getSource();
		if (btn.getToolTipText().equals("홈 화면")) {
			this.memberFrame.getMenuCardPanel().getCard().show(this.memberFrame.getMenuCardPanel(), "홈 화면");
		} else {

			try {
				setMenu = new SettingMenu(this.memberFrame);
				setMenu.setVisible(true);
				theme.setTheme(themeDao.getTheme());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
