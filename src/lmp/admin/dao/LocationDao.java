package lmp.admin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lmp.admin.vo.LocationVO;

public class LocationDao{
	
	private String url = "jdbc:oracle:thin:@192.168.0.100:1521:XE";
	private String user = "library";
	private String pw = "1234";
	
	public void add(LocationVO locationVO) throws SQLException{
		Connection conn = getConnection(); 
		
		String sql = "INSERT INTO locations VALUES(?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
			
		pstmt.setString(1, LocationVO.getLocID());
		pstmt.setString(2, LocationVO.getLocName());
		System.out.println("성공?");
		pstmt.executeUpdate();
		System.out.println("성공");	
		pstmt.close();
		conn.close();
	}
	
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
	
	public static void main(String[] args) {
		LocationDao dao = new LocationDao();

		try {
			
			String id;
			String[] name = { "철학", "종교", "사회과학", "자연과학", "기술과학", "예술", "언어", "문학", "역사" };
			
				
				dao.add(new LocationVO("A", name[0]));
				dao.add(new LocationVO("B", name[1]));
				dao.add(new LocationVO("C", name[2]));
				dao.add(new LocationVO("D", name[3]));
				dao.add(new LocationVO("E", name[4]));
				dao.add(new LocationVO("F", name[5]));
				dao.add(new LocationVO("G", name[6]));
				dao.add(new LocationVO("H", name[7]));
				dao.add(new LocationVO("I", name[8]));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
