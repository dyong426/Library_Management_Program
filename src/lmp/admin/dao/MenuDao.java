package lmp.admin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import lmp.admin.vo.AdminLogHistoryVO;
import lmp.admin.vo.AdminVO;
import lmp.admin.vo.BookVO;
import lmp.admin.vo.CheckOutVO;
import lmp.admin.vo.MemberVO;
import lmp.admin.vo.SeatUseDetailVO;


public abstract class MenuDao {

	private static String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	private static String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	private static String user = "library";
	private static String user = "mydb";
	private static String pw = "1234";
	
	public void add(AdminVO adminVO) throws SQLException {}
	
	public void add(MemberVO memberVO) throws SQLException {}
	
	public void add(BookVO bookVO) throws SQLException {}
	
	public void add(CheckOutVO checkOutVO) throws SQLException {}
	
	public void add(SeatUseDetailVO sudVO) throws SQLException {}
	
	// 
	public void add(BookVO bookVO, String memberNum) throws SQLException {}
	
	public void update(AdminVO adminVO) throws SQLException {}

	public void update(MemberVO memberVO) throws SQLException {}
	
	public void update(BookVO bookVO) throws SQLException {}

	public void update(CheckOutVO checkOutVO) throws SQLException {}

	public void update(int use_id) throws SQLException {}

	
	public ArrayList get() throws SQLException {
		return null;
	}
	public ArrayList get(int header, String searchStr) throws SQLException {
		return null;
	}
	
	
	public void delete(String adminNum) throws SQLException {}
	
	public void delete(MemberVO memberVO) throws SQLException {}
	
	public void delete(int bookNum) throws SQLException {}
	
	public void delete() throws SQLException {}
	
	
	/**
	 * Connection 연결 부분 분리 메서드화
	 * 
	 * @return conn
	 */
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, pw);
			return conn;
		} catch (ClassNotFoundException e) {
			System.out.println("Class 못찾음");
		} catch (SQLException e) {
			System.out.println("DB 접속 실패");
		}
		return null;
	}

	/**
	 * 회원 정보 수정
	 * 
	 * @param memberVO
	 * @throws SQLException
	 */
	public void update(AdminLogHistoryVO adminLogVO) throws SQLException {}

	public AdminLogHistoryVO getLog() throws SQLException {
		return null;
	}

	public void delete(Integer admin_num) throws SQLException {}

	public ArrayList get(int admin_num) throws SQLException {
		return null;
	}

	public AdminVO getAdminInfo(int admin_num) throws SQLException {
		return null;
	}


	/**
	 * 도서 삭제
	 * 
	 * @param bookVO
	 * @throws SQLException
	 */

}
