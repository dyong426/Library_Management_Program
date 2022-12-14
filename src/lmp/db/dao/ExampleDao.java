package lmp.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lmp.db.vo.AdminVO;
import lmp.db.vo.BookVO;

public class ExampleDao {
	
	
	private static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String user = "lmp";
	private static String pw = "1234";
	
public void add(BookVO bookVO) throws SQLException {
		
		Connection conn = getConnection(); 
		
		String sql = "INSERT INTO books("
									  + "book_id,"
									  + "book_title,"
									  + "book_authhor,"
									  + "book_publisher,"
									  + "book_isbn,"
									  + "book_bias,"
									  + "book_duplicates,"
									  + "book_price,"
									  + "location_id,"
									  + "book_note) VALUES('B' | book_id_seq.nextval,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
			
		pstmt.setString(1, bookVO.getTitle());
		pstmt.setString(2, bookVO.getAuthor());
		pstmt.setString(3, bookVO.getPublisher());
		pstmt.setString(4, bookVO.getIsbn());
		pstmt.setInt(5, bookVO.getBias());
		pstmt.setInt(6, bookVO.getDuplicates());//복권수 확인 절차
		pstmt.setInt(7, bookVO.getPrice());
		pstmt.setString(8, bookVO.getLocation().getLocID());
		pstmt.setString(9, bookVO.getNote());
			
		pstmt.executeUpdate();
			
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

}
