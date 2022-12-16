package lmp.members.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lmp.members.vo.MemberLogHistoryVO;

public class MemberLogHistroyDao extends MenuDao{

	
	@Override
	public void add(MemberLogHistoryVO memLogVO) throws SQLException{
		
		Connection conn = getConnection();
		
		String sql = "INSERT INTO member_log_histroy VALUES(mem_log_id_seq.nextval,?,sysdate,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, memLogVO.getMem_num());
		pstmt.setString(2, null);
		
		pstmt.executeUpdate();
			
		pstmt.close();
		conn.close();
	}
	
	/**
	 * 회원 정보 수정
	 * 
	 * @param memberVO
	 * @throws SQLException
	 */
	@Override
	public void update(MemberLogHistoryVO memLogVO) throws SQLException {
		
		Connection conn = getConnection();
		
		String sql =  "Update members SET logout_time = to_char(sysdate, 'yyyy.mm.dd hh24:mi') WHERE mem_num = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
			
		pstmt.setInt(1,memLogVO.getMem_num());
			
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	@Override
	public MemberLogHistoryVO getLog() throws SQLException {
		String sql = "SELECT * FROM members WHERE logout_time IS NULL";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		MemberLogHistoryVO memLogVO = null;
		while (rs.next()) {
			memLogVO = new MemberLogHistoryVO(rs.getInt(0), rs.getInt(1), rs.getString(2), rs.getString(3));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return memLogVO;
	}

	@Override
	public void delete(Integer mem_id) throws SQLException {
		String sql = "DELETE FROM member_log_history WHERE mem_id = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, mem_id);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	
	
}
