package lmp.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lmp.db.vo.BookVO;
import lmp.db.vo.CheckOutVO;
import lmp.db.vo.LocationVO;
import lmp.db.vo.MemberVO;

public class CheckOutDao extends MenuDao{
	
	/**
	 * 대여/반납 dao
	 */

	/**
	 * 대여 목록 추가
	 * 
	 * check_out_id 자동 증가
	 * 
	 * book_id, mem_num 등록
	 * 
	 * 대여 날짜 default 현재시간
	 * 반납 예정 날짜 default 현재 날짜 + 7
	 * 
	 * @param checkOutVO
	 * @throws SQLException
	 */
	@Override
	public void add(CheckOutVO checkOutVO) throws SQLException {
		Connection conn = getConnection();
		
		String sql = "INSERT INTO check_out_info(check_out_id, book_id, mem_num) VALUES(check_out_id_seq.nextval,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, checkOutVO.getBook().getId());
		pstmt.setString(2, checkOutVO.getMember().getNum());
			
		pstmt.executeUpdate();
			
		pstmt.close();
		conn.close();
	}
	
	/**
	 * 대여 목록 업데이트
	 * 
	 * 반납날짜 check_in_date 현재 날짜로 수정
	 * 
	 * @param checkOutVO
	 * @throws SQLException
	 */
	@Override
	public void update(CheckOutVO checkOutVO) throws SQLException {
		Connection conn = getConnection();
		
		String sql =  "UPDATE"
					+ " check_out_info"
					+ "SET "
					+ " check_in_date = sysdate,"
					+ "WHERE"
					+ " check_out_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1,checkOutVO.getCheckOutID());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	
	/**
	 * header 
	 * id - 등록번호
	 * title - 제목
	 * num - 회원번호
	 * name - 회원이름
	 * 
	 * searchStr
	 * header에 해당하는 값
	 * 
	 * @param header
	 * @param searchStr
	 * @return ArrayList<CheckOutVO> checkOutList
	 * @throws SQLException
	 */
	@Override
	public ArrayList get(String header, String searchStr) throws SQLException {
		String id = "SELECT * FROM check_out_info JOIN members USING(mem_num) JOIN books USING(book_id) JOIN locations USING(location_id) WHERE book_id = ?";
		String title = "SELECT * FROM check_out_info JOIN members USING(mem_num) JOIN books USING(book_id) JOIN locations USING(location_id) WHERE book_title = ?";
		String num = "SELECT * FROM check_out_info JOIN members USING(mem_num) JOIN books USING(book_id) JOIN locations USING(location_id) WHERE mem_num = ?";
		String name = "SELECT * FROM check_out_info JOIN members USING(mem_num) JOIN books USING(book_id) JOIN locations USING(location_id) WHERE mem_name = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(header);
		pstmt.setString(1, searchStr);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<CheckOutVO> checkOutList = new ArrayList<>();
		while (rs.next()) {
			checkOutList.add(new CheckOutVO(
								rs.getInt("check_out_info"),
								new BookVO(rs.getString("book_id"),
										rs.getString("book_title"),
										rs.getString("book_author"),
										rs.getString("book_publisher"),
										rs.getString("book_isbn"),
										rs.getInt("book_bias"),
										rs.getInt("book_duplicates"),
										rs.getString("book_registration_date"),
										rs.getInt("book_price"),
										new LocationVO(rs.getString("location_id"), rs.getString("location_name")),
										rs.getString("book_note")),
								new MemberVO(rs.getString("mem_num"),
										rs.getString("mem_name"),
										rs.getString("mem_id"),
										rs.getString("mem_pw"),
										rs.getString("mem_birthday"),
										rs.getString("mem_sex"),
										rs.getString("mem_phone"),
										rs.getString("mem_email"),
										rs.getString("mem_address"),
										rs.getString("mem_registrationdate"),
										rs.getString("mem_note")),
								rs.getString("check_out_date"),
								rs.getString("expect_return_date"),
								rs.getString("check_in_date")));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return checkOutList;
	}
	
	/**
	 * 대여 내역 삭제 (이용중인 좌석 제외) 
	 * 
	 */
	@Override
	public void delete() throws SQLException {
		String sql = "DELETE FROM check_out_info WHERE check_in_date is not null";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}

}
