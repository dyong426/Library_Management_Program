package lmp.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lmp.db.vo.AdminVO;
import lmp.db.vo.MemberVO;
import lmp.db.vo.ReadingRoomVO;
import lmp.db.vo.SeatUseDetailVO;

public class SeatUseDetailDao extends MenuDao{
	
	/**
	 * 열람실 이용내역 dao
	 */

	/**
	 * 열람실 이용내역 추가
	 * use_id, mem_num, seat_num 등록
	 * 
	 * start_time default 현재시간으로 등록
	 * 
	 * @param sudVO
	 * @throws SQLException
	 */
	@Override
	public void add(SeatUseDetailVO sudVO) throws SQLException{
		Connection conn = getConnection();
		
		String sql = "INSERT INTO check_out_info(use_id, mem_num, seat_num) VALUES(check_out_id_seq.nextval,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, sudVO.getMember().getNum());
		pstmt.setInt(2, sudVO.getReadingroom().getSeatNum());
			
		pstmt.executeUpdate();
			
		pstmt.close();
		conn.close();
	}

	/**
	 * 열람실 이용내역 업데이트
	 * 
	 * 퇴실 , 강제퇴실 할경우
	 * end_time 현재시간으로 수정.
	 * 
	 * @param sudVO
	 * @throws SQLException
	 */
	@Override
	public void update(SeatUseDetailVO sudVO) throws SQLException {
		Connection conn = getConnection();
		
		String sql =  "UPDATE"
					+ " seat_use_details"
					+ "SET "
					+ " end_time = sysdate,"
					+ "WHERE"
					+ " use_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1,sudVO.getUse_id());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
	}
	
	/**
	 * 열람실 이용중인 좌석 가져오기
	 * 
	 * @return ArrayList<SeatUseDetailVO> sudList
	 */
	@Override
	public ArrayList get() throws SQLException {
		String sql = "SELECT * FROM seat_use_details JOIN members USING(mem_num) JOIN readingroom USING(seat_num) WHERE end_time is null";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<SeatUseDetailVO> sudList = new ArrayList<>();
		while (rs.next()) {
			sudList.add(
						new SeatUseDetailVO(
							rs.getInt("use_id"),
							new MemberVO(
								rs.getString("mem_num"),
								rs.getString("mem_name"),
								rs.getString("mem_id"),
								rs.getString("mem_pw"),
								rs.getString("mem_birthday"),
								rs.getString("mem_sex"),
								rs.getString("mem_phone"),
								rs.getString("mem_email"),
								rs.getString("mem_address"),
								rs.getString("mem_registrationdate"),
								rs.getString("mem_note")
								),
							new ReadingRoomVO(
								rs.getInt("seat_num"),
								rs.getString("table_divider")
								),
							rs.getString("start_time"),
							rs.getString("end_time")
							)
						);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return sudList;
	}
	
	
	/**
	 * 열람실 좌석 조건 검색
	 * 
	 * header
	 * seatNum - 좌석 번호
	 * 
	 * searchStr
	 * header에 해당하는 값
	 * 
	 * @param header
	 * @param searchStr
	 * @return ArrayList<SeatUseDetailVO> sudList
	 */
	@Override
	public ArrayList get(int header, String searchStr) throws SQLException {
		
		StringBuilder sql = new StringBuilder(selectSql(header));

		Connection conn = getConnection();
		System.out.println(conn);
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, searchStr);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<SeatUseDetailVO> sudList = new ArrayList<>();
		while (rs.next()) {
			sudList.add(
						new SeatUseDetailVO(
							rs.getInt("use_id"),
							new MemberVO(
								rs.getString("mem_num"),
								rs.getString("mem_name"),
								rs.getString("mem_id"),
								rs.getString("mem_pw"),
								rs.getString("mem_birthday"),
								rs.getString("mem_sex"),
								rs.getString("mem_phone"),
								rs.getString("mem_email"),
								rs.getString("mem_address"),
								rs.getString("mem_registrationdate"),
								rs.getString("mem_note")
								),
							new ReadingRoomVO(
								rs.getInt("seat_num"),
								rs.getString("table_divider")
								),
							rs.getString("start_time"),
							rs.getString("end_time")
							)
						);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return sudList;
	}
	
	
	public StringBuilder selectSql(int header) {
		StringBuilder sql = new StringBuilder();
		String seatNum = "SELECT * FROM seat_use_details JOIN members USING(mem_num) JOIN readingroom USING(seat_num) WHERE seat_num = ?";
		if (header == 1) {
			sql.append(seatNum);
		}
		return sql;
	}
	
	
	
	
	/**
	 * 열람실 이용 내역 삭제 (이용중인 좌석 제외)
	 * 
	 */
	@Override
	public void delete() throws SQLException {
		String sql = "DELETE FROM seat_use_details WHERE end_time is not null";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}

}
