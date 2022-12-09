package lmp.db.vo;

import java.util.Date;

public class AdminVO {
	
	/**
	 * 관리자 직원 정보
	 */
	
	private Integer num;
	private String	name;
	private String	pw;
	private String	phone;
	private String	email;
	private String	address;
	private Date	regDate;
	private String	note;
	
	
	/**
	 * 관리자 로그인시 생성자
	 * 
	 * @param num
	 * @param pw
	 */
	public AdminVO(Integer admin_num, String admin_pw) {
		this.num = admin_num;
		this.pw	 = admin_pw;
	}
	
	/**
	 * 관리자 등록, 조회 생성자
	 * 
	 * @param num
	 * @param name
	 * @param pw
	 * @param phone
	 * @param email
	 * @param address
	 * @param regdate
	 * @param note
	 */
	public AdminVO(
					Integer	admin_num, 
					String	admin_name, 
					String	admin_pw, 
					String	admin_phone, 
					String	admin_email, 
					String	admin_address, 
					Date	admin_registrationdate, 
					String	admin_note
					) {
		
		this.num	 = admin_num;
		this.name	 = admin_name;
		this.pw 	 = admin_pw;
		this.phone 	 = admin_phone;
		this.email 	 = admin_email;
		this.address = admin_address;
		this.regDate = admin_registrationdate;
		this.note 	 = admin_note;
	}

	public Integer getNum() {
		return num;
	}

	public String getName() {
		return name;
	}

	public String getPw() {
		return pw;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public Date getRegDate() {
		return regDate;
	}

	public String getNote() {
		return note;
	}
}
