package lmp.db.vo;

public class ReadingRoomVO {
	
	/**
	 * 열람실 VO
	 */
	
	private Integer	seatNum;
	private String	tableDivider;
	
	/**
	 * 열람실 정보 생성자
	 * 
	 * @param seatNum
	 * @param tableDivider
	 */
	public ReadingRoomVO(Integer seatNum, String tableDivider) {
		
		this.seatNum	  = seatNum;
		this.tableDivider = tableDivider;
		
	}

	public Integer getSeatNum() {
		return seatNum;
	}

	public String getTableDivider() {
		return tableDivider;
	}
	
}
