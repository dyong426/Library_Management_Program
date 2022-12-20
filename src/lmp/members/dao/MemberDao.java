package lmp.members.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lmp.members.vo.MemberVO;



public class MemberDao extends MenuDao{
	
	
	/**
	 * 회원 등록
	 * 
	 * @param memberVO
	 * @throws SQLException
	 */
	@Override
	public void add(MemberVO memberVO) throws SQLException{
		
		Connection conn = getConnection();
		
		String sql = "INSERT INTO members("
										+ "mem_num,"
										+ "mem_name,"
										+ "mem_id,"
										+ "mem_pw,"
										+ "mem_birthday,"
										+ "mem_sex,"
										+ "mem_phone,"
										+ "mem_email,"
										+ "mem_address,"
										+ "mem_note"
										+ ") VALUES(member_num_seq.nextval,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, memberVO.getName());
		pstmt.setString(2, memberVO.getId());
		pstmt.setString(3, memberVO.getPw());
		pstmt.setString(4, memberVO.getBirthDay());
		pstmt.setString(5, memberVO.getSex());
		pstmt.setString(6, memberVO.getPhone());
		pstmt.setString(7, memberVO.getEmail());
		pstmt.setString(8, memberVO.getAddress());
		pstmt.setString(9, memberVO.getNote());
			
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
	public void update(MemberVO memberVO) throws SQLException {
		
		Connection conn = getConnection();
		
		String sql =  "Update "
					+ "members "
					+ "SET "
					+ "mem_name = ?,"
					+ "mem_id = ?,"
					+ "mem_pw = ?,"
					+ "mem_phone = ?,"
					+ "mem_email = ?,"
					+ "mem_address = ? "
					+ "WHERE "
					+ "mem_num = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
			
		pstmt.setString(1,memberVO.getName());
		pstmt.setString(2,memberVO.getId());
		pstmt.setString(3,memberVO.getPw());
		pstmt.setString(4,memberVO.getPhone());
		pstmt.setString(5,memberVO.getEmail());
		pstmt.setString(6,memberVO.getAddress());
		pstmt.setInt(7,memberVO.getNum());
			
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	@Override
	public MemberVO get(String mem_id) throws SQLException {
		String sql = "SELECT * FROM members WHERE mem_id = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mem_id);
		ResultSet rs = pstmt.executeQuery();
		MemberVO memberVO = new MemberVO();
		while (rs.next()) {
			memberVO = new MemberVO(
								rs.getInt("mem_num"),
								rs.getString("mem_id"),
								rs.getString("mem_pw")
								);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return memberVO;
	}

	/**
	 * 전체 회원 목록 가져오기
	 * 
	 * @return ArrayList<MemberVO> memberList
	 */
	@Override
	public ArrayList get(int mem_num) throws SQLException {
		String sql = "SELECT * FROM members WHERE mem_num = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, mem_num);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<MemberVO> memberList = new ArrayList<>();
		while (rs.next()) {
			memberList.add(new MemberVO(
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
								rs.getString("mem_note")));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return memberList;
	}
	
	/**
	 * 회원 삭제
	 * 
	 * @param memberVO
	 * @throws SQLException
	 */
	@Override
	public void delete(MemberVO memberVO) throws SQLException {
		String sql = "DELETE FROM members WHERE mem_num = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,memberVO.getNum());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	
	
}
