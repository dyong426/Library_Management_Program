package lmp.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lmp.db.vo.AdminVO;
import lmp.db.vo.MemberVO;

public class MemberDao extends MenuDao{

	/*
	 * 회원 정보 dao
	 */
	
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
										+ "mem_pw"
										+ "mem_birthday,"
										+ "mem_sex,"
										+ "mem_phone,"
										+ "mem_email,"
										+ "mem_address"
										+ "mem_note"
										+ ") VALUES('EZM' | member_num_seq.nextval,?,?,?,?,?,?,?,?,?)";
		
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
					+  "members "
					+ "SET "
					+ "mem_name = ?,"
					+ "mem_id = ?,"
					+ "mem_pw = ?,"
					+ "mem_birthday = ?,"
					+ "mem_sex = ?,"
					+ "mem_phone = ?,"
					+ "mem_email = ?"
					+ "mem_address = ?"
					+ "mem_note = ?"
					+ "WHERE "
					+ "mem_num = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
			
		pstmt.setString(1,memberVO.getName());
		pstmt.setString(2,memberVO.getId());
		pstmt.setString(3,memberVO.getPw());
		pstmt.setString(4,memberVO.getBirthDay());
		pstmt.setString(5,memberVO.getSex());
		pstmt.setString(6,memberVO.getPhone());
		pstmt.setString(7,memberVO.getEmail());
		pstmt.setString(8,memberVO.getAddress());
		pstmt.setString(9,memberVO.getNote());
		pstmt.setString(10,memberVO.getName());
			
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	/**
	 * 전체 회원 목록 가져오기
	 * 
	 * @return ArrayList<MemberVO> memberList
	 */
	@Override
	public ArrayList get() throws SQLException {
		String sql = "SELECT * FROM members";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<MemberVO> memberList = new ArrayList<>();
		while (rs.next()) {
			memberList.add(new MemberVO(
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
								rs.getString("mem_note")));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return memberList;
	}
	
	
	/**
	 * 회원 조건 검색
	 * header
	 * num - 회원번호
	 * name - 회원이름
	 * id - 회원 아이디
	 * phone - 회원 연락처
	 * 
	 * searchStr
	 * header에 해당하는 값
	 * 
	 * @param header
	 * @param searchStr
	 * @return ArrayList<MemberVO> memberList
	 */
	@Override
	public ArrayList get(String header, String searchStr) throws SQLException {
		String num = "SELECT * FROM members WHERE mem_num = ?";
		String name = "SELECT * FROM members WHERE mem_name = ?";
		String id = "SELECT * FROM members WHERE mem_id = ?";
		String phone = "SELECT * FROM members WHERE mem_phone = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(header);
		pstmt.setString(1, searchStr);
		
		ResultSet rs = pstmt.executeQuery();
		ArrayList<MemberVO> memberList = new ArrayList<>();
		while (rs.next()) {
			memberList.add(new MemberVO(
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
	 */
	@Override
	public void delete(MemberVO memberVO) throws SQLException {
		String sql = "DELETE FROM members WHERE mem_num = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,memberVO.getNum());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}

	
}
