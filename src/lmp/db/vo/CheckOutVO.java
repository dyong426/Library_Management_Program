package lmp.db.vo;

import java.util.Date;

public class CheckOutVO {
	/**
	 * 대여 정보 VO
	 * 
	 * CHECKOUT JOIN BOOKS, MEMBERS
	 * 
	 */
	
	private Integer	 checkOutID;
	private BookVO	 book;
	private MemberVO member;
	private Date	 checkOutDate;
	private Date	 expectReturnDate;
	private Date	 checkInDate;
	
	/**
	 * 도서 정보, 대여상태 생성자
	 * 
	 * @param book
	 * @param checkOutDate
	 * @param expectRetrunDate
	 * @param checkInDate
	 */
	public CheckOutVO(
					   BookVO book,
					   Date checkOutDate,
					   Date expectRetrunDate,
					   Date checkInDate
					   ) {
		this.book = book;
		this.checkOutDate = checkOutDate;
		this.expectReturnDate = expectRetrunDate;
		this.checkInDate = checkInDate;
	}
	
	/**
	 * 대여 내역 생성자
	 * 
	 * 회원정보, 도서정보, 대여/반납 날짜
	 * 
	 * @param checkOutID
	 * @param book
	 * @param member
	 * @param checkOutDate
	 * @param expectReturnDate
	 * @param checkInDate
	 */
	public CheckOutVO(
					   Integer	checkOutID,
					   BookVO   book,
					   MemberVO member,
					   Date		checkOutDate,
					   Date		expectReturnDate,
					   Date		checkInDate
					   ) {
		this.checkOutID = checkOutID;
		this.book = book;
		this.member = member;
		this.expectReturnDate = expectReturnDate;
		this.checkOutDate = checkOutDate;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public Integer getCheckOutID() {
		return checkOutID;
	}

	public BookVO getBook() {
		return book;
	}

	public MemberVO getMember() {
		return member;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public Date getExpectReturnDate() {
		return expectReturnDate;
	}
	
}
