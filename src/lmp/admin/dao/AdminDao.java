package lmp.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lmp.admin.vo.AdminVO;

public class AdminDao extends MenuDao{

	/**
	 * 관리자 dao
	 */
	
	/**
	 * 관리자 목록 추가
	 * 
	 * 등록날짜 default 현재날짜
	 * 
	 * @param adminVO
	 * @throws SQLException
	 */
	@Override
	public void add(AdminVO adminVO) throws SQLException {
		Connection conn = getConnection();
		
		String sql = "INSERT INTO admins("
										+ "admin_num,"
										+ "admin_name,"
										+ "admin_pw,"
										+ "admin_phone,"
										+ "admin_email,"
										+ "admin_address,"
										+ "admin_note) "
										+ "VALUES(admin_num_seq.nextval,?,?,?,?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
			
		pstmt.setString(1, adminVO.getName());
		pstmt.setString(2, adminVO.getPw());
		pstmt.setString(3, adminVO.getPhone());
		pstmt.setString(4, adminVO.getEmail());
		pstmt.setString(5, adminVO.getAddress());
		pstmt.setString(6, adminVO.getNote());
			
		pstmt.executeUpdate();
			
		pstmt.close();
		conn.close(); 
		
	}

	
	/**
	 * 관리자 정보 업데이트
	 * 
	 * 이름, 비밀번호, 연락처, 이메일, 주소, 비고
	 * 
	 * @param adminVO
	 * @throws SQLException
	 */
	@Override
	public void update(AdminVO adminVO) throws SQLException {
		Connection conn = getConnection();
		
		String sql =  "UPDATE"
					+ " admins"
					+ "SET "
					+ " admin_name = ?,"
					+ " admin_pw = ?,"
					+ " admin_phone = ?,"
					+ " admin_email = ?,"
					+ " admin_address = ?,"
					+ " admin_note = ?"
					+ "WHERE"
					+ " admin_num = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,adminVO.getName());
		pstmt.setString(2,adminVO.getPw());
		pstmt.setString(3,adminVO.getPhone());
		pstmt.setString(4,adminVO.getEmail());
		pstmt.setString(5,adminVO.getAddress());
		pstmt.setString(6,adminVO.getNote());
		pstmt.setInt(7,adminVO.getNum());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	/**
	 * 전체 직원 목록 가져오기
	 * 
	 * @return ArrayList<AdminVO> adminList
	 */
	@Override
	public ArrayList get() throws SQLException {
		String sql = "SELECT * FROM Admins";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<AdminVO> adminList = new ArrayList<>();
		while (rs.next()) {
			adminList.add(new AdminVO(
								rs.getInt("admin_num"),
								rs.getString("admin_name"),
								rs.getString("admin_pw"),
								rs.getString("admin_phone"),
								rs.getString("admin_email"),
								rs.getString("admin_address"),
								rs.getString("admin_registrationdate"),
								rs.getString("admin_updatedate"),
								rs.getString("admin_note")));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return adminList;
	}
	
	@Override
	public ArrayList<AdminVO> get(String searchStr) throws SQLException {
		
		String sql = "SELECT * FROM admins WHERE admin_num = ?";

		
		Connection conn = getConnection();
		System.out.println(conn);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(searchStr));
		
		ResultSet rs = pstmt.executeQuery();
		ArrayList<AdminVO> adminList = new ArrayList<>();
		while (rs.next()) {
				adminList.add(new AdminVO(
						rs.getInt("admin_num"),
						rs.getString("admin_name"),
						rs.getString("admin_pw"),
						rs.getString("admin_phone"),
						rs.getString("admin_email"),
						rs.getString("admin_address"),
						rs.getString("admin_registrationdate"),
						rs.getString("admin_updatedate"),
						rs.getString("admin_note")));
}
		rs.close();
		pstmt.close();
		conn.close();
		
		return adminList;
	}
	
	/**
	 * 해당하는 조건의 sql문 가져오기
	 * header
	 * 회원번호 - 1
	 * 회원이름 - 2
	 * 회원아이디 - 3
	 * 회원연락처 - 4
	 * 
	 * @param header
	 * @return StringBuilder sql
	 */
	public StringBuilder selectSql(int header) {
		StringBuilder sql = new StringBuilder();
		String num = "SELECT * FROM admins WHERE admin_num = ?";
		String name = "SELECT * FROM admins WHERE admin_name LIKE ?";
		String phone = "SELECT * FROM admins WHERE admin_phone LIKE ?";
		if (header == 1) {
			sql.append(num);
		} else if (header == 2) {
			sql.append(name);
		} else if (header == 3) {
			sql.append(phone);
		}
		return sql;
	}
	

	/**
	 * 직원 삭제
	 * 
	 * @param adminVO
	 * @throws SQLException
	 */
	@Override
	public void delete(AdminVO adminVO) throws SQLException {
		
		String sql = "DELETE FROM admins WHERE admin_num = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,adminVO.getNum());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
	}

}
