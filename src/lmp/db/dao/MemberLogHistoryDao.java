package lmp.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lmp.db.vo.MemberLogHistoryVO;
import lmp.db.vo.MemberVO;

public class MemberLogHistoryDao extends MenuDao {
	
	@Override
	public void add(MemberVO memberVO) throws SQLException {

		Connection conn = getConnection();

		String sql = "INSERT INTO member_log_history(mem_log_id, mem_num) VALUES(mem_log_id_seq.nextval,?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, memberVO.getNum());

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

		String sql =  "Update member_log_history SET logout_time = to_char(sysdate, 'yyyy.mm.dd hh24:mi') WHERE mem_num = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1,memLogVO.getMemberVO().getNum());

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}


	@Override
	public MemberLogHistoryVO getLog(){
		String sql = "SELECT * FROM member_log_history INNER JOIN members "
				+ "USING(mem_num) WHERE logout_time IS NULL";
		
		MemberLogHistoryVO memLogVO = null;
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				){
			while (rs.next()) {
				memLogVO = new MemberLogHistoryVO(rs.getInt("mem_log_id"),
						new MemberVO(rs.getInt("mem_num"), rs.getString("mem_name"), rs.getString("mem_id"),
								rs.getString("mem_pw"), rs.getString("mem_birthday"), rs.getString("mem_sex"),
								rs.getString("mem_phone"), rs.getString("mem_email"), rs.getString("mem_address"),
								rs.getString("mem_registrationdate"), rs.getString("mem_updatedate"),
								rs.getString("mem_note")),
						rs.getString("login_time"), rs.getString("logout_time"));
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memLogVO;
	}
	
	@Override
	public MemberLogHistoryVO getLog(String mem_id) throws SQLException {
		String sql = "SELECT * FROM member_log_history INNER JOIN members "
				+ "USING(mem_num) WHERE logout_time IS NULL AND mem_id = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mem_id);
		ResultSet rs = pstmt.executeQuery();
		MemberLogHistoryVO memLogVO = null;
		while (rs.next()) {
			memLogVO = new MemberLogHistoryVO(rs.getInt("mem_log_id"),
					new MemberVO(
							rs.getInt("mem_num"),
							rs.getString("mem_name"),
							rs.getString("mem_id"),
							rs.getString("mem_pw"),
							rs.getString("mem_birthday"),
							rs.getString("mem_sex"),
							rs.getString("mem_phone"),
							rs.getString("mem_email"),
							rs.getString("mem_address"),
							rs.getString("mem_registrationdate"),
							rs.getString("mem_updatedate"),
							rs.getString("mem_note")),
							rs.getString("login_time"), 
							rs.getString("logout_time"));
		}
		rs.close();
		pstmt.close();
		conn.close();

		return memLogVO;
	}

	
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
