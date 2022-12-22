package lmp.util;

import java.util.regex.Pattern;

public class Validator {
	
	
	/**
	 * 유효성 검사 정규표현신
	 * 
	 * 아이디 - 5~20자리 소문자, 숫자 조합
	 * 비밀번호 - 8~30자리 소문자, 대문자, 숫자, 특수문자 !@#$%^&+= 조합
	 * 이름 - 2~10자리 한글
	 * 이메일 - 
	 * 연락처 - 010으로 시작, 3~4자리, 4자리
	 *
	 */
	private final static String REGEX_ID = "^.*(?=^.{5,20}$)(?=.*\\d)(?=.*[a-z]).*$"; // 아이디
	private final static String REGEX_PASSWORD = "^.*(?=^.{8,30}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$"; // 비밀번호
	private final static String REGEX_NAME = "^.*[가-힣]$"; // 이름
	private final static String REGEX_EMAIL = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$"; // 이메일
	private final static String REGEX_PHONE = "^010-\\d{3,4}-\\d{4}$"; // 연락처
	
	public boolean isValidateID(String id) {
		return Pattern.matches(REGEX_ID, id);
	}

	public boolean isValidatePW(String password) {
		return Pattern.matches(REGEX_PASSWORD, password);
	}
	
	public boolean isValidateName(String name) {
		if (name.length() > 1) {			
			return Pattern.matches(REGEX_NAME, name);
		}
		return false;
	}
	
	public boolean isValidateEmail(String email) {
		return Pattern.matches(REGEX_EMAIL, email);
	}
	
	public boolean isValidatePhone(String phone) {
		return Pattern.matches(REGEX_PHONE, phone);
	}
}
