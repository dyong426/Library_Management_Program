package lmp.db.vo;

public class MemberLogHistoryVO {
	
	Integer log_id;
	MemberVO memberVO;
	String  loginTime;
	String  logoutTime;

	public MemberLogHistoryVO(MemberLogHistoryVO memberLogHistory) {
		this.log_id = memberLogHistory.getLog_id();
		this.memberVO = memberLogHistory.getMemberVO();
		this.loginTime = memberLogHistory.getLoginTime();
		this.logoutTime = memberLogHistory.getLogoutTime();
	}


	public MemberLogHistoryVO(Integer log_id,MemberVO memberVO, String loginTime, String logoutTime) {

		this.log_id = log_id;
		this.memberVO = memberVO;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;

	}

	public Integer getLog_id() {
		return log_id;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}
}
