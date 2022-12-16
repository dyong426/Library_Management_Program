package lmp.admin.vo;

public class AdminLogHistoryVO {

	Integer log_id;
	Integer admin_num;
	String  loginTime;
	String  logoutTime;
	
	public AdminLogHistoryVO(Integer log_id, Integer admin_num, String loginTime, String logoutTime) {
		
		this.log_id = log_id;
		this.admin_num = admin_num;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		
	}

	public Integer getLog_id() {
		return log_id;
	}

	public Integer getAdmin_num() {
		return admin_num;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}
	
	
	
}
