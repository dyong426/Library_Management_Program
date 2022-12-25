package lmp.admin.adminframe.frame.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import lmp.admin.dao.AdminDao;
import lmp.admin.dao.AdminLogHistoryDao;
import lmp.members.dao.MemberDao;
import lmp.members.dao.MemberLogHistoryDao;

public class AdminFrameWindowListener extends WindowAdapter {

	AdminLogHistoryDao adminLogDao = new AdminLogHistoryDao();
	AdminDao adminDao = new AdminDao();
	
	@Override
	public void windowClosing(WindowEvent e) {
		try {
			if(adminLogDao.getLog() != null) {
				adminLogDao.update(adminLogDao.getLog());
			}
		} catch (SQLException e1) {
			return;
		}
	}
	
}
