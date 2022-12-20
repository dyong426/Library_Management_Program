package lmp.members.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import lmp.members.vo.BookVO;
import lmp.members.vo.MemberLogHistoryVO;
import lmp.members.vo.MemberVO;
import lmp.members.vo.SeatUseDetailVO;



public abstract class MenuDao {
	
	private static String url = "jdbc:oracle:thin:@192.168.0.100:1521:XE";
	private static String user = "library";
	private static String pw = "1234";
	
	/**
	 * 전체 회원 목록 가져오기
	 * 
	 * @return ArrayList<MemberVO> memberList
	 */
	public ArrayList get(int mem_num) throws SQLException {
		return null;
	}
	
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
	public void update(MemberVO memberVO) throws SQLException {
	}


	/**
	 * 회원 삭제
	 * 
	 * @param memberVO
	 * @throws SQLException
	 */
	public void delete(MemberVO memberVO) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public MemberVO get(String mem_id) throws SQLException {
		return null;
	}

	/**
	 * 회원 등록
	 * 
	 * @param memberVO
	 * @throws SQLException
	 */
	public void add(MemberVO memberVO) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public void delete(BookVO bookVO) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 도서 조건 검색
	 * 
	 * header
	 * 1 : id - 등록번호
	 * 2 : title - 제목
	 * 3 : author - 저자
	 * 4 : publisher - 출판사
	 * 5 : isbn - isbn 번호
	 * 6 : location - 위치
	 * 
	 * @param header
	 * @param searchStr
	 * @return ArrayList<BookVO> bookList
	 * @throws SQLException
	 */
	public ArrayList get(int header, String searchStr) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 열람실 이용내역 추가
	 * use_id, mem_num, seat_num 등록
	 * 
	 * start_time default 현재시간으로 등록
	 * 
	 * @param sudVO
	 * @throws SQLException
	 */
	public void add(SeatUseDetailVO sudVO) throws SQLException {
		// TODO Auto-generated method stub
		
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
	public void update(SeatUseDetailVO sudVO) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 열람실 이용중인 좌석 가져오기
	 * 
	 * @return ArrayList<SeatUseDetailVO> sudList
	 */
	public ArrayList getUse() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(MemberLogHistoryVO memLogVO) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 회원 정보 수정
	 * 
	 * @param memberVO
	 * @throws SQLException
	 */
	public void update(MemberLogHistoryVO memLogVO) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	public MemberLogHistoryVO getLog() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Integer mem_id) throws SQLException {
		// TODO Auto-generated method stub
		
	}


}
