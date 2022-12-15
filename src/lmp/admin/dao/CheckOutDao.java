package lmp.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lmp.admin.vo.BookVO;
import lmp.admin.vo.CheckOutVO;
import lmp.admin.vo.LocationVO;
import lmp.admin.vo.MemberVO;

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
		
		pstmt.setInt(1, checkOutVO.getBook().getId());
		pstmt.setInt(2, checkOutVO.getMember().getNum());
			
		pstmt.executeUpdate();
			
		pstmt.close();
		conn.close();
	}
	
	/**
	 * 반납 목록 업데이트
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
	public ArrayList get(int header, String searchStr) throws SQLException {
		
		StringBuilder sql = new StringBuilder(selectSql(header));

		Connection conn = getConnection();
		System.out.println(conn);
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, "%"+searchStr+"%");
		
		ResultSet rs = pstmt.executeQuery();
		ArrayList<CheckOutVO> checkOutList = new ArrayList<>();
		while (rs.next()) {
			checkOutList.add(new CheckOutVO(
								rs.getInt("check_out_id"),
								new BookVO(rs.getInt("book_id"),
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
								new MemberVO(rs.getInt("mem_num"),
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
	 * 해당하는 조건의 sql문 가져오기
	 * header
	 * 도서 등록번호 - 1
	 * 도서 제목 - 2
	 * 회원번호 - 3
	 * 회원이름 - 4
	 * 
	 * @param header
	 * @return StringBuilder sql
	 */
	public StringBuilder selectSql(int header) {
		StringBuilder sql = new StringBuilder();
		String id = "SELECT * FROM check_out_info JOIN members USING(mem_num) JOIN books USING(book_id) JOIN locations USING(location_id) WHERE book_id LIKE ?";
		String title = "SELECT * FROM check_out_info JOIN members USING(mem_num) JOIN books USING(book_id) JOIN locations USING(location_id) WHERE book_title LIKE ?";
		String num = "SELECT * FROM check_out_info JOIN members USING(mem_num) JOIN books USING(book_id) JOIN locations USING(location_id) WHERE mem_num LIKE ?";
		String name = "SELECT * FROM check_out_info JOIN members USING(mem_num) JOIN books USING(book_id) JOIN locations USING(location_id) WHERE mem_name LIKE ?";
		if (header == 1) {
			sql.append(id);
		} else if (header == 2) {
			sql.append(title);
		} else if (header == 3) {
			sql.append(num);
		} else if (header == 4) {
			sql.append(name);
		}
		return sql;
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
