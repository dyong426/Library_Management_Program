package lmp.db.vo;

import java.util.Date;

public class SeatUseDetailVO {

	/**
	 * 열람실 이용 내역 VO
	 * 
	 * MEMBERS JOIN READINGROOM
	 */
	
	private MemberVO member;
	private ReadingRoomVO readingroom;
	private Date startTime;
	private Date endTime;
	
	/**
	 * 열람실 이용내역 정보 생성자
	 * 
	 * @param member
	 * @param readingroom
	 * @param startTime
	 * @param endTime
	 */
	public SeatUseDetailVO(
							MemberVO member,
							ReadingRoomVO readingroom,
							Date startTime,
							Date endTime
							) {
		
		this.member		 = member;
		this.readingroom = readingroom;
		this.startTime	 = startTime;
		this.endTime	 = endTime;
		
	}

	public MemberVO getMember() {
		return member;
	}

	public ReadingRoomVO getReadingroom() {
		return readingroom;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}
}
