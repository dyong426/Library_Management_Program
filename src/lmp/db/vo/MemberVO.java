package lmp.db.vo;

import java.util.Date;

import lmp.db.dao.CheckOutDao;

public class MemberVO {
	
	/**
	 * 	회원정보
	*/
	
	private	Integer	num;
	private	String	name;
	private	String	id;
	private	String	pw;
	private	String	birthDay;
	private	String	sex;
	private	String	phone;
	private	String	email;
	private	String	address;
	private	String	regDate;
	private	String	note;
	private String	availability;
	
	
	/**
	 * 회원 로그인시 생성자
	 * 
	 * @param mem_id
	 * @param mem_pw
	 */
	public MemberVO(String mem_id, String mem_pw) {
		
		this.id = mem_id;
		this.pw = mem_pw;
		
	}
	
	/**
	 * 회원 정보 생성자
	 * 
	 * @param mem_num
	 * @param mem_name
	 * @param mem_id
	 * @param mem_pw
	 * @param mem_birthDay
	 * @param mem_sex
	 * @param mem_phone
	 * @param mem_email
	 * @param mem_address
	 * @param mem_registrationDate
	 * @param mem_note
	 */
	public MemberVO(
					int		mem_num,
					String	mem_name,
					String	mem_id,
					String	mem_pw,
					String	mem_birthDay,
					String	mem_sex,
					String	mem_phone,
					String	mem_email,
					String	mem_address,
					String	mem_registrationDate,
					String	mem_note
					) {
		
		this.num		=	mem_num;
		this.name		=	mem_name;
		this.id			= 	mem_id;
		this.pw			=	mem_pw;
		this.birthDay	=	mem_birthDay;
		this.sex		=	mem_sex;
		this.phone		=	mem_phone;
		this.email		=	mem_email;
		this.address	=	mem_address;
		this.regDate	=	mem_registrationDate;
		this.note		=	mem_note;
	}
	
	public MemberVO(int mem_num, String mem_name, String mem_phone, String mem_email, String mem_address) {
		this.num = mem_num;
		this.name = mem_name;
		this.phone = mem_phone;
		this.email = mem_email;
		this.address = mem_address;
	}

	public Integer getNum() {
		return num;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public String getSex() {
		return sex;
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

	public String getRegDate() {
		return regDate;
	}

	public String getNote() {
		return note;
	}
	
	// 테이블에 데이터 넣기 쉽도록 getList 메서드 생성
	public Object[] getList() {
		Object[] list = new Object[11];
		list[0] = getNum();
		list[1] = getName();
		list[2] = getId();
		list[3] = getPw();
		list[4] = getBirthDay();
		list[5] = getSex();
		list[6] = getPhone();
		list[7] = getEmail();
		list[8] = getAddress();
		list[9] = getRegDate();
		list[10] = getNote();
		
		return list;
	}
	
	@Override
	public String toString() {
		
		return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", this.num,this.name,this.id,this.pw,this.birthDay,this.phone,this.email,this.address,this.regDate,this.note);
	}
	
}
