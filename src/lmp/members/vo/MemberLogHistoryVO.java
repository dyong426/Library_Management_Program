package lmp.members.vo;

public class MemberLogHistoryVO {
	
	Integer log_id;
	Integer mem_num;
	String  loginTime;
	String  logoutTime;
	
	public MemberLogHistoryVO(Integer log_id,Integer mem_num, String loginTime, String logoutTime) {
		
		this.log_id = log_id;
		this.mem_num = mem_num;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		
	}

	public Integer getLog_id() {
		return log_id;
	}

	public Integer getMem_num() {
		return mem_num;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}
	
}
