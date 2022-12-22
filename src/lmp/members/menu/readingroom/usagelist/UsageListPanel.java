package lmp.members.menu.readingroom.usagelist;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import lmp.members.menu.readingroom.usagelist.panel.UsageListTitlePanel;

public class UsageListPanel extends JPanel{

	static BorderLayout borderLayout = new BorderLayout();
	
	UsageListTitlePanel usagListTitlePanel;
	
	public UsageListPanel() {
		
		System.out.println("usagelistpanel");
		setBounds(80, 50, 1300, 80);
		this.setLayout(borderLayout);
		
	}
		
}