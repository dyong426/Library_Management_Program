package lmp.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lmp.admin.vo.AdminLogHistoryVO;
import lmp.admin.vo.AdminVO;

public class AdminLogHistoryDao extends MenuDao{


	
	@Override
	public void add(AdminVO adminVO) {
		
		
		Connection conn = getConnection();
		
		String sql = "INSERT INTO admin_log_history(admin_log_id,admin_num) VALUES(admin_log_id_seq.nextval,?)";
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, adminVO.getNum());
		System.out.println("실행");
		pstmt.executeUpdate();
			
		pstmt.close();
		conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 회원 정보 수정
	 * 
	 * @param memberVO
	 * @throws SQLException
	 */
	@Override
	public void update(AdminLogHistoryVO adminLogVO) throws SQLException {
		
		Connection conn = getConnection();
		
		String sql =  "Update admin_log_history SET logout_time = to_char(sysdate, 'yyyy.mm.dd hh24:mi') WHERE admin_log_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
			
		pstmt.setInt(1,adminLogVO.getLog_id());
			
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	@Override
	public AdminLogHistoryVO getLog() throws SQLException {
		String sql = "SELECT * FROM admin_log_history JOIN admins USING(admin_num) WHERE logout_time IS NULL";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		AdminLogHistoryVO adminLogVO = null;
		while (rs.next()) {
			adminLogVO = new AdminLogHistoryVO(rs.getInt("admin_log_id"), 
					new AdminVO(
					rs.getInt("admin_num"),
					rs.getString("admin_name"),
					rs.getString("admin_pw"),
					rs.getString("admin_phone"),
					rs.getString("admin_email"),
					rs.getString("admin_address"),
					rs.getString("admin_registrationdate"),
					rs.getString("admin_updatedate"),
					rs.getString("admin_note")), 
					rs.getString("login_Time"), rs.getString("logout_Time"));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return adminLogVO;
	}

	@Override
	public void delete(Integer admin_num) throws SQLException {
		String sql = "DELETE FROM member_log_history WHERE admin_num = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, admin_num);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	
}
